package mobi.rectour.recHoteis.automato;

import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.geral.RecTourDatabase;
import mobi.rectour.recHoteis.entidades.Hotel;
import mobi.rectour.web.FactoryConexaoWeb;
import mobi.rectour.web.IConexaoWeb;
import mobi.rectour.web.InformacoesServidor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class AutomatoBuscarHoteis {

	public static final int idHotel = 002;
	public static final String nomeTabelaHoteis = "hotel";
	
	private String UrlAcesso = "http://dados.recife.pe.gov.br/api/action/datastore_search?resource_id=%s";

	private String idBaseHoteis = "0d8fb090-2863-4d51-9b21-baae4bae5a11";

	private CallBackAtualizacoesProntas cbap;
	
	public AutomatoBuscarHoteis(){
		
		
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
			
			IConexaoWeb conWeb = FactoryConexaoWeb.getConexao(String.format(UrlAcesso, idBaseHoteis), FactoryConexaoWeb.REQ_GET);
			
			
			String retorno = conWeb.buscarServidor();
			InformacoesServidor is = conWeb.verificarObjRespostaServidorRetorno(retorno);

			if (!is.isFalhaRequisicao()) {
				try {
					JSONObject jsRetorno = new JSONObject(retorno).getJSONObject("result");
					JSONArray arrHoteis =  jsRetorno.getJSONArray("records");
					
					//So deleta se deu certo a busca na net e o parser do array
					RecTourDatabase.deleteAllHotel();
					
					for (int i = 0; i < arrHoteis.length(); i++) {
						JSONObject entRestaurante = arrHoteis.getJSONObject(i);
						Hotel hotel = new Hotel(entRestaurante);
						RecTourDatabase.inserirHotelParaTestes(hotel);
					}
					is.setMsgErroServer("Ok");
					is.setMsgUsuario("Dados atualizados!");
				} catch (JSONException e) {
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
				cbap.operacaoConcluida(idHotel,result);
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
