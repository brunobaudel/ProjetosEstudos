package mobi.rectour.recRoteirosTurismoLazer.automato;

import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.geral.RecTourDatabase;
import mobi.rectour.recRoteirosTurismoLazer.entidades.Ponte;
import mobi.rectour.web.FactoryConexaoWeb;
import mobi.rectour.web.IConexaoWeb;
import mobi.rectour.web.InformacoesServidor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class AutomatoBuscarPontesRecife {

	public static final int idPonte = 501;

	private String UrlAcesso    = "http://dados.recife.pe.gov.br/api/action/datastore_search?resource_id=%s";
	private String idBasePontes = "61fcd098-058b-4bb1-9918-f46cfbac3261";

	private CallBackAtualizacoesProntas cbap;
	
	public AutomatoBuscarPontesRecife(){
	}
	
	public void executar(){
		new BuscarBuscarPontesRecife().execute();
	}
	
	private class BuscarBuscarPontesRecife extends AsyncTask<Void, Boolean, InformacoesServidor> {

		
		@Override
		protected void onPreExecute() {
			super.onPreExecute(); 
		}

		@Override
		protected InformacoesServidor doInBackground(Void... params) {

			IConexaoWeb conWeb = FactoryConexaoWeb.getConexao(String.format(UrlAcesso, idBasePontes), FactoryConexaoWeb.REQ_GET);
			
			String retorno = conWeb.buscarServidor();
			
			InformacoesServidor is = conWeb.verificarObjRespostaServidorRetorno(retorno);

			if (!is.isFalhaRequisicao()) {
				try {
					JSONObject jsRetorno = new JSONObject(retorno).getJSONObject("result");
					JSONArray arrPontes  =  jsRetorno.getJSONArray("records");
					
					//So deleta se deu certo a busca na net e o parser do array
					RecTourDatabase.deleteAllPonte();
					
					for (int i = 0; i < arrPontes.length(); i++) {
						JSONObject entPonte = arrPontes.getJSONObject(i);
						Ponte ponte = new Ponte(entPonte);
						RecTourDatabase.inserirPonte(ponte);
					}
					is.setMsgErroServer("Ok");
					is.setMsgUsuario("Dados atualizados!");

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return is;
		}

		@Override
		protected void onPostExecute(InformacoesServidor result) {
			super.onPostExecute(result);

			if (cbap != null) {
				cbap.operacaoConcluida(idPonte, result);
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
