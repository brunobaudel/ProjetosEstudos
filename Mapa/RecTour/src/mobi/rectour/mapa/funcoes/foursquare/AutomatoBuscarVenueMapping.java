package mobi.rectour.mapa.funcoes.foursquare;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.mapa.funcoes.foursquare.entidadesvenues.Venues;
import mobi.rectour.web.FactoryConexaoWeb;
import mobi.rectour.web.IConexaoWeb;
import mobi.rectour.web.InformacoesServidor;
import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AutomatoBuscarVenueMapping {
	
	public static final int idBuscarVenueMapping = 454;
	public static final String nomeTabelaHoteis = "hotel";
	
	private String UrlAcesso = "http://api.foursquare.com/v2/venues/search?intent=match&ll=%s&query=%s&limit=3&oauth_token=%s&v=20131017";
	
	private Context context;

	private String latLong   = "";
	private String nomeLocal = "";
	private String token     = "214MAA3S1W1AFJ3XXRWB4BVXHHJGLXDIUKX2HMZPXJQ35HZ4";

	private CallBackAtualizacoesProntas cbap;
	
	List<Venues> listVenues;
	
	public AutomatoBuscarVenueMapping(LatLng latLng , String nomeLocal , Context context){
		
		this.latLong = String.format("%s,%s",  latLng.latitude , latLng.longitude);
		this.nomeLocal  = nomeLocal;
		this.context = context;
		
	}
	
	public AutomatoBuscarVenueMapping(String latLng , String nomeLocal , Context context){
		
		this.latLong = latLng;
		this.nomeLocal  = nomeLocal;
		this.context = context;
		
	}
	
	public void executar(){
		
		new BuscarVenueMapping().execute();
	}
	
	private class BuscarVenueMapping extends AsyncTask<Void, Boolean, InformacoesServidor> {
		
		protected void onPreExecute() {
			super.onPreExecute(); 
		}

		@Override
		protected InformacoesServidor doInBackground(Void... params) {
			
			IConexaoWeb conWeb = FactoryConexaoWeb.getConexao(String.format(UrlAcesso, latLong,nomeLocal.replaceAll(" ", "%20"),token), FactoryConexaoWeb.REQ_GET);
			
			String retorno =  conWeb.buscarServidor();//loadDataFromAsset(); //
			InformacoesServidor is = conWeb.verificarObjRespostaServidorRetorno(retorno);

			if (!is.isFalhaRequisicao()) {
				try {
					
					JsonParser parser = new JsonParser();
					JsonObject objJson = parser.parse(retorno).getAsJsonObject();
					
					JsonObject response =  objJson.getAsJsonObject("response");
					JsonArray arrVenues = response.getAsJsonArray("venues");
					
					listVenues = new ArrayList<Venues>();
					
					Gson gson = new Gson();
					for (JsonElement jsonElement : arrVenues) {
						
						Venues venues = gson.fromJson(jsonElement, Venues.class);
						listVenues.add(venues);
						
					}
					
					is.setMsgErroServer("Ok");
					is.setMsgUsuario("Dados atualizados!");
					is.setRetorno(listVenues);
				} catch (Exception e) {
					e.printStackTrace();
					is.setFalhaRequisicao(true);
				}
			}
			return is;
		}

		@Override
		protected void onPostExecute(InformacoesServidor result) {
			super.onPostExecute(result);

			if (cbap != null) {
				cbap.operacaoConcluida(idBuscarVenueMapping,result);
			}
			
		}
	}

	public CallBackAtualizacoesProntas getCbap() {
		return cbap;
	}

	public void setCbap(CallBackAtualizacoesProntas cbap) {
		this.cbap = cbap;
	}
	
	
	
	public String loadDataFromAsset() {
	
		
		String sb = "";
		
        // load text
        try {
        	 InputStream is = context.getAssets().open("jsonTeste1.txt");
             // check size
             int size = is.available();
             // create buffer for IO
             byte[] buffer = new byte[size];
             // get data to buffer
             is.read(buffer);
             // close stream
             is.close();
             // set result to TextView
             sb = new String(buffer);
        }
        catch (IOException ex) {
           // return;
        }
 
        // load image
//        try {
//            // get input stream
////            InputStream ims = getAssets().open("avatar.jpg");
////            // load image as Drawable
////            Drawable d = Drawable.createFromStream(ims, null);
////            // set image to ImageView
////            mImage.setImageDrawable(d);
//        }
//        catch(IOException ex) {
//            return;
//        }
        
        return sb.toString();
 
    }
	

}
