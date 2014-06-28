package mobi.rectour.recRestaurantes.automato;

import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.geral.RecTourDatabase;
import mobi.rectour.recRestaurantes.entidades.Restaurante;
import mobi.rectour.util.UtilRecTour;
import mobi.rectour.web.FactoryConexaoWeb;
import mobi.rectour.web.IConexaoWeb;
import mobi.rectour.web.InformacoesServidor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

public class AutomatoBuscarRestaurante {
	
	public static final int idRestaurante = 001;

	private String UrlAcesso = "http://dados.recife.pe.gov.br/api/action/datastore_search?resource_id=%s";

	private String idBaseRestaurante = "d85bf4e3-637e-4e1b-9b03-970dca4403c7";

	private Context c;

	private CallBackAtualizacoesProntas cbap;
	
	
	public AutomatoBuscarRestaurante(){
		
		
	}
		
	public void executar(Context c){
		this.c = c;
		new BuscarRestaurantes().execute();
	}
	
	private class BuscarRestaurantes extends AsyncTask<Void, Boolean, InformacoesServidor> {

		
		@Override
		protected void onPreExecute() {
			super.onPreExecute(); 

		}

		@Override
		protected InformacoesServidor doInBackground(Void... params) {

			IConexaoWeb conWeb = FactoryConexaoWeb.getConexao(String.format(UrlAcesso,idBaseRestaurante), FactoryConexaoWeb.REQ_GET);
			
			String retorno = conWeb.buscarServidor();
			
			InformacoesServidor is = conWeb.verificarObjRespostaServidorRetorno(retorno);

			if (!is.isFalhaRequisicao()) {

				
				try {
					
					JSONObject jsRetorno = new JSONObject(retorno).getJSONObject("result");
					
					JSONArray arrRestaurantes =  jsRetorno.getJSONArray("records");
					
					RecTourDatabase.deleteAllRestauranteParaTestes();
					

					for (int i = 0; i < arrRestaurantes.length(); i++) {

						JSONObject entRestaurante = arrRestaurantes.getJSONObject(i);

						Restaurante restaurante = new Restaurante(entRestaurante);
						
						
						LatLng retLatLgn =  UtilRecTour.getLatLng(c, restaurante.getRtEndereco());
						
						restaurante.setLatitude(retLatLgn.latitude);
						restaurante.setLongitude(retLatLgn.longitude);
						
						RecTourDatabase.inserirRestauranteParaTestes(restaurante);
						
					}
					
					is.setMsgErroServer("Ok");
					is.setMsgUsuario("Dados atualizados!");

				} catch (JSONException e) {
					e.printStackTrace();//Analisar n pode cair aqui se der erro no for
					is.setFalhaRequisicao(true);
				}
			}
			return is;
		}

		@Override
		protected void onPostExecute(InformacoesServidor result) {
			super.onPostExecute(result);

			if (cbap != null) {

				cbap.operacaoConcluida(idRestaurante,result);

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
