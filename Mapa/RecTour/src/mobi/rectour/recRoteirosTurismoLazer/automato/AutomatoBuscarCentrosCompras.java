package mobi.rectour.recRoteirosTurismoLazer.automato;

import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.geral.RecTourDatabase;
import mobi.rectour.recRoteirosTurismoLazer.entidades.Shopping;
import mobi.rectour.web.FactoryConexaoWeb;
import mobi.rectour.web.IConexaoWeb;
import mobi.rectour.web.InformacoesServidor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class AutomatoBuscarCentrosCompras {
	
	public static final int idShopping = 005;

	private String UrlAcesso = "http://dados.recife.pe.gov.br/api/action/datastore_search?resource_id=%s";
	private String idBaseCentrosCompras = "81f406de-8468-4bb9-b038-0956d6684acd";

	private CallBackAtualizacoesProntas cbap;
	
	public AutomatoBuscarCentrosCompras(){
	}
	
	public void executar(){
		new BuscarCentrosCompras().execute();
	}
	
	private class BuscarCentrosCompras extends AsyncTask<Void, Boolean, InformacoesServidor> {

		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected InformacoesServidor doInBackground(Void... params) {

			
			IConexaoWeb conWeb = FactoryConexaoWeb.getConexao(String.format(UrlAcesso, idBaseCentrosCompras), FactoryConexaoWeb.REQ_GET);
			
			String retorno = conWeb.buscarServidor();

			InformacoesServidor is = conWeb.verificarObjRespostaServidorRetorno(retorno);

			if (!is.isFalhaRequisicao()) {

				try {
					JSONObject jsRetorno  = new JSONObject(retorno).getJSONObject("result");
					JSONArray arrShoppings =  jsRetorno.getJSONArray("records");
					
					//So deleta se deu certo a busca na net e o parser do array
					RecTourDatabase.deleteAllShopping();
					
					for (int i = 0; i < arrShoppings.length(); i++) {
						JSONObject entShopping = arrShoppings.getJSONObject(i);
						Shopping shopping = new Shopping(entShopping);
						RecTourDatabase.inserirShopping(shopping);
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
				cbap.operacaoConcluida(idShopping,result);
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
