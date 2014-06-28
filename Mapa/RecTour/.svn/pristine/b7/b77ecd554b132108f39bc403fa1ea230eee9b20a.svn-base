package mobi.rectour.mapa.funcoes.googleplaces.automato;

import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.mapa.funcoes.googleplaces.UrlConstantes;
import mobi.rectour.mapa.funcoes.googleplaces.entidades.PlaceLocal;
import mobi.rectour.web.FactoryConexaoWeb;
import mobi.rectour.web.IConexaoWeb;
import mobi.rectour.web.InformacoesServidor;
import android.os.AsyncTask;

public class AutomatoBuscarDetalhesLocalGooglePlace {

	public static final int idBuscarDetalhesLocalGP = 68;
	//public static final String nomeTabelaHoteis = "hotel";
	
	private String referencia;

	
	private PlaceLocal palceLocal;
	
	
	private CallBackAtualizacoesProntas cbap;
	
	public AutomatoBuscarDetalhesLocalGooglePlace(String referencia){
		this.referencia = referencia;
		
		
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
			
			String UrlAcesso = UrlConstantes.getUrlBuscaDetalheLocal(referencia);
			
			
			IConexaoWeb conWeb = FactoryConexaoWeb.getConexao(UrlAcesso, FactoryConexaoWeb.REQ_GET);
			
			
			String retorno = conWeb.buscarServidor();
			InformacoesServidor is = new InformacoesServidor();//conWeb.verificarObjRespostaServidorRetorno(retorno);

			if (!is.isFalhaRequisicao()) {
				try {
					
					is.setMsgErroServer("Ok");
					is.setMsgUsuario("Busca Concuida!");
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
				cbap.operacaoConcluida(idBuscarDetalhesLocalGP,result);
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
