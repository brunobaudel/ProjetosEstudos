
package mobi.rectour.recRoteirosTurismoLazer.automato;


import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.geral.RecTourDatabase;
import mobi.rectour.recRoteirosTurismoLazer.entidades.Museu;
import mobi.rectour.web.FactoryConexaoWeb;
import mobi.rectour.web.IConexaoWeb;
import mobi.rectour.web.InformacoesServidor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;


public class AutomatoBuscarMuseus {


	public static final int idMuseu = 003;
	
	private String UrlAcesso    = "http://dados.recife.pe.gov.br/api/action/datastore_search?resource_id=%s";
	private String idBaseMuseus = "97ab18da-f940-43b1-b0d4-a9e93e90bed5";


	private CallBackAtualizacoesProntas cbap;
	
	public AutomatoBuscarMuseus(){
	}
	
	public void executar(){
		
		new BuscarMuseus().execute();
	}
	
	private class BuscarMuseus extends AsyncTask<Void, Boolean, InformacoesServidor> {

		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}


		@Override
		protected InformacoesServidor doInBackground(Void... params) {

			IConexaoWeb conWeb = FactoryConexaoWeb.getConexao(String.format(UrlAcesso, idBaseMuseus), FactoryConexaoWeb.REQ_GET); // new ConexaoHttpsPost(String.format(UrlAcesso, idBaseMuseus));
			String retorno = conWeb.buscarServidor();
			
			InformacoesServidor is = conWeb.verificarObjRespostaServidorRetorno(retorno);


			if (!is.isFalhaRequisicao()) {
				try {
					JSONObject jsRetorno = new JSONObject(retorno).getJSONObject("result");
					JSONArray arrMuseus =  jsRetorno.getJSONArray("records");
					
					//So deleta se deu certo a busca na net e o parser do array
					RecTourDatabase.deleteAllMuseu();
					
					for (int i = 0; i < arrMuseus.length(); i++) {
						JSONObject entMuseu = arrMuseus.getJSONObject(i);
						Museu museu = new Museu(entMuseu);
						RecTourDatabase.inserirMuseu(museu);
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
				cbap.operacaoConcluida(idMuseu,result);
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
