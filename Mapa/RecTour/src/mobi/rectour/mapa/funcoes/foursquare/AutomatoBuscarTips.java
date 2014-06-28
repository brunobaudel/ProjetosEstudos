package mobi.rectour.mapa.funcoes.foursquare;

import java.util.ArrayList;
import java.util.List;

import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.mapa.funcoes.foursquare.entidadestips.*;
import mobi.rectour.web.FactoryConexaoWeb;
import mobi.rectour.web.IConexaoWeb;
import mobi.rectour.web.InformacoesServidor;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AutomatoBuscarTips {
	
	
	public static final int idBuscarTips = 522;
	public static final String nomeTabelaHoteis = "hotel";
	
	private String UrlAcesso = "https://api.foursquare.com/v2/venues/%s/tips?sort=recent&oauth_token=%s&v=20131031";
	//Teste
	//https://api.foursquare.com/v2/venues/4cb4a176c5e6a1cde1a6f4f6/tips?sort=recent&oauth_token=214MAA3S1W1AFJ3XXRWB4BVXHHJGLXDIUKX2HMZPXJQ35HZ4&v=20131031
	
	private Context context;

	private String idVenue = "";
	private String token     = "214MAA3S1W1AFJ3XXRWB4BVXHHJGLXDIUKX2HMZPXJQ35HZ4";

	private CallBackAtualizacoesProntas cbap;
	
	public AutomatoBuscarTips(String nomeLocal , Context context){
		
		this.idVenue  = nomeLocal;
		this.context = context;
		
	}
	
	public AutomatoBuscarTips(String latLng , String nomeLocal , Context context){
		
		this.idVenue  = nomeLocal;
		this.context = context;
		
	}
	
	public void executar(){
		
		new BuscarFotos().execute();
	}
	
	private class BuscarFotos extends AsyncTask<Void, Boolean, InformacoesServidor> {
		
		protected void onPreExecute() {
			super.onPreExecute(); 
		}

		@Override
		protected InformacoesServidor doInBackground(Void... params) {
			
			IConexaoWeb conWeb = FactoryConexaoWeb.getConexao(String.format(UrlAcesso,idVenue,token), FactoryConexaoWeb.REQ_GET);
			
			String retorno =  conWeb.buscarServidor();//loadDataFromAsset(); //
			InformacoesServidor is = conWeb.verificarObjRespostaServidorRetorno(retorno);

			if (!is.isFalhaRequisicao()) {
				try {
					
					JsonParser parser = new JsonParser();
					JsonObject objJson = parser.parse(retorno).getAsJsonObject();
					
					JsonObject response =  objJson.getAsJsonObject("response");
					
					JsonObject objFotos = response.getAsJsonObject("tips");
					
					JsonArray arrItems = objFotos.getAsJsonArray("items");
					
					List<Items> listVenues = new ArrayList<Items>();
					
					Gson gson = new Gson();
					for (JsonElement jsonElement : arrItems) {
						
						Items item = gson.fromJson(jsonElement, Items.class);
						listVenues.add(item);
						
					}
					
					is.setRetorno(listVenues);
					is.setMsgErroServer("Ok");
					is.setMsgUsuario("Dados atualizados!");
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
				cbap.operacaoConcluida(idBuscarTips,result);
			}
			
		}
	}

	public CallBackAtualizacoesProntas getCbap() {
		return cbap;
	}

	public void setCbap(CallBackAtualizacoesProntas cbap) {
		this.cbap = cbap;
	}
	

}
