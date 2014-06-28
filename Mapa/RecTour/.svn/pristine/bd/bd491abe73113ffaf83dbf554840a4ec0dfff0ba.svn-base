package mobi.rectour.recRoteirosTurismoLazer.automato;

import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.geral.RecTourDatabase;
import mobi.rectour.recRoteirosTurismoLazer.entidades.Teatro;
import mobi.rectour.web.FactoryConexaoWeb;
import mobi.rectour.web.IConexaoWeb;
import mobi.rectour.web.InformacoesServidor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class AutomatoBuscarTeatros {
	
	public static final int idTeatro = 004;

	private String UrlAcesso     = "http://dados.recife.pe.gov.br/api/action/datastore_search?resource_id=%s";
	private String idBaseTeatros = "16d45f07-1fab-4b8c-95d1-dbf555b6f913";
	
	private CallBackAtualizacoesProntas cbap;

	public AutomatoBuscarTeatros(){
	}
	
	public void executar(){
		new BuscarBuscarTeatros().execute();
	}
	
	private class BuscarBuscarTeatros extends AsyncTask<Void, Boolean, InformacoesServidor> {

		
		@Override
		protected void onPreExecute() {
			super.onPreExecute(); 

		}

		@Override
		protected InformacoesServidor doInBackground(Void... params) {

			
			IConexaoWeb conWeb = FactoryConexaoWeb.getConexao(String.format(UrlAcesso, idBaseTeatros), FactoryConexaoWeb.REQ_GET);
			String retorno = conWeb.buscarServidor();
			
			InformacoesServidor is = conWeb.verificarObjRespostaServidorRetorno(retorno);

			if (!is.isFalhaRequisicao()) {

				
				try {
					JSONObject jsRetorno  = new JSONObject(retorno).getJSONObject("result");
					JSONArray arrTeatros =  jsRetorno.getJSONArray("records");
					
					//So deleta se deu certo a busca na net e o parser do array
					RecTourDatabase.deleteAllTeatro();
					
					for (int i = 0; i < arrTeatros.length(); i++) {
						JSONObject entTeatro = arrTeatros.getJSONObject(i);
						Teatro teatro = new Teatro(entTeatro);
						RecTourDatabase.inserirTeatro(teatro);
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
				cbap.operacaoConcluida(idTeatro,result);
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
