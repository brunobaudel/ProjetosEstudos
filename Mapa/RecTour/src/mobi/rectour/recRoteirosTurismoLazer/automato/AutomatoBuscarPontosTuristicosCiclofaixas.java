package mobi.rectour.recRoteirosTurismoLazer.automato;

import mobi.rectour.web.ConexaoHttpsPost;
import mobi.rectour.web.IConexaoWeb;
import mobi.rectour.web.InformacoesServidor;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class AutomatoBuscarPontosTuristicosCiclofaixas {

	private String UrlAcesso = "http://dados.recife.pe.gov.br/api/action/datastore_search?resource_id=%s";

	private String idBaseHoteis = "";

	public interface CallBackAtualizacoesProntas {

		void operacaoConcluida(int codigoAutomato);

	}

	private CallBackAtualizacoesProntas cbap;
	
	
	public AutomatoBuscarPontosTuristicosCiclofaixas(){
		
		
	}
	
	
	
	public void executar(){
		
		new BuscarPontosTuristicosCiclofaixas().execute();
	}
	
	
	

	private class BuscarPontosTuristicosCiclofaixas extends AsyncTask<Void, Boolean, Boolean> {

		private InformacoesServidor is;

		@Override
		protected void onPreExecute() {
			super.onPreExecute(); // Rosenildo

		}

		@Override
		protected Boolean doInBackground(Void... params) {

			IConexaoWeb conWeb = new ConexaoHttpsPost(String.format(UrlAcesso,
					idBaseHoteis));

			String retorno = conWeb.buscarServidor();
			

			is = conWeb.verificarObjRespostaServidorRetorno(retorno);

			if (!is.isFalhaRequisicao()) {

				JSONObject localizacaoJsonRespose;

				try {
					localizacaoJsonRespose = new JSONObject(retorno);

//					JSONArray arrLocalizacao = localizacaoJsonRespose
//							.getJSONArray("Cameras");
//
//					for (int i = 0; i < arrLocalizacao.length(); i++) {
//
//						JSONObject localizacao = arrLocalizacao
//								.getJSONObject(i);
//
//					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return is.isFalhaRequisicao();
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);

			if (cbap != null) {

				cbap.operacaoConcluida(2);

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
