package mobi.rectour.mapa.funcoes.googleplaces.automato;

import java.util.List;

import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.mapa.funcoes.googleplaces.UrlConstantes;
import mobi.rectour.mapa.funcoes.googleplaces.entidades.Photos;
import mobi.rectour.mapa.funcoes.googleplaces.entidades.PlaceLocal;
import mobi.rectour.web.FactoryConexaoWeb;
import mobi.rectour.web.IConexaoWeb;
import mobi.rectour.web.InformacoesServidor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.provider.SyncStateContract.Constants;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class AutomatoBuscarLocalGooglePlace {

	public static final int idBuscarLocalGP = 67;
	//public static final String nomeTabelaHoteis = "hotel";
	
	private String nomeLocal;
	private String tipoLocal;
	private String localizacao;
	private String idRecTour;
	
	private PlaceLocal palceLocal;
	
	
	private CallBackAtualizacoesProntas cbap;
	
	public AutomatoBuscarLocalGooglePlace(String nomeLocal,String tipoLocal, String localizacao, String idLocal){
		this.nomeLocal = nomeLocal;
		this.tipoLocal = tipoLocal;
		this.localizacao = localizacao;
		this.idRecTour = idLocal;
	}
	
	public void executar(){
		
		new BuscarHoteis().execute();
	}
	
	private class BuscarHoteis extends AsyncTask<Void, Boolean, InformacoesServidor> {

		
		protected void onPreExecute() {
			super.onPreExecute(); 
		}

		@Override
		protected InformacoesServidor doInBackground(Void... params) {
			
			InformacoesServidor is = new InformacoesServidor();
			ParseQuery<ParseObject> query = ParseQuery.getQuery("PlaceLocal");
			
			query.whereEqualTo("idRecTour", idRecTour);
			
			try {
				List<ParseObject> listPlaceLocal = query.find();
				if(listPlaceLocal.isEmpty()){
				
					String UrlAcesso = UrlConstantes.getUrlBuscaLocal(nomeLocal, tipoLocal, localizacao);
					
					
					IConexaoWeb conWeb = FactoryConexaoWeb.getConexao(UrlAcesso, FactoryConexaoWeb.REQ_GET);
					
					
					String retorno = conWeb.buscarServidor();
					is = new InformacoesServidor();//conWeb.verificarObjRespostaServidorRetorno(retorno);
		
					if (!is.isFalhaRequisicao()) {
						try {
							JSONArray jsRetorno = new JSONObject(retorno).getJSONArray("results");
							
							JSONObject arrPrimeiroHotel  =  jsRetorno.getJSONObject(0);   //getJSONArray("photos");
							
							palceLocal = new PlaceLocal(arrPrimeiroHotel);
							
							Photos ph = palceLocal.getListPhotos().size() > 0 ? palceLocal.getListPhotos() .get(0) :
								new Photos();
							
							ParseObject po = new ParseObject("PlaceLocal");
							po.put("id_Google", palceLocal.getId());
							po.put("google_photo_reference", ph.getPhoto_reference());
							po.put("reference", palceLocal.getReferencia());
							po.put("name", palceLocal.getNomeLocal());
							po.put("idRecTour" , idRecTour);
							po.save();
							
							
								
							is.setMsgErroServer("Ok");
							is.setMsgUsuario("Busca Concuida!");
						} catch (JSONException e) {
							e.printStackTrace();
							is.setFalhaRequisicao(true);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							is.setFalhaRequisicao(true);
						}
					}
					
				}else{
					
					palceLocal = new PlaceLocal(listPlaceLocal.get(0));
					
					String url = UrlConstantes.getUrlBuscarFoto(palceLocal.getListPhotos().get(0).getPhoto_reference());
					
					
					IConexaoWeb conWeb2 = FactoryConexaoWeb.getConexao(url, FactoryConexaoWeb.REQ_GET);
					
					conWeb2.salvarFotoServer();
					
				}
				
				
			
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				is.setFalhaRequisicao(true);
			}
			return is;
		}

		@Override
		protected void onPostExecute(InformacoesServidor result) {
			super.onPostExecute(result);

			if (cbap != null) {
				cbap.operacaoConcluida(idBuscarLocalGP,result);
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
