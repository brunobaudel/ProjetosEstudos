package mobi.rectour.mapa.funcoes.v2.funcTracarRotas;

import java.util.Locale;

import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.util.UtilRecTour;
import mobi.rectour.web.ConexaoHttpsPost;
import mobi.rectour.web.FactoryConexaoWeb;
import mobi.rectour.web.IConexaoWeb;
import mobi.rectour.web.InformacoesServidor;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;

import android.os.AsyncTask;

public class AutomatoBuscarRota {

	String urlRota = "http://maps.googleapis.com/maps/api/directions/json?origin=%f,%f&destination=%f,%f&sensor=true&mode=driving";   

	private LatLng start; 
	private LatLng dest;
	
	private CallBackAtualizacoesProntas cbap;

	public AutomatoBuscarRota(LatLng start , LatLng dest) {

		this.start = start;
		this.dest = dest;
	}

	public void executar() {

		new BuscarRotas().execute();
	}

	private class BuscarRotas extends AsyncTask<Void, Boolean, Boolean> {

		private InformacoesServidor is;

		@Override
		protected void onPreExecute() {
			super.onPreExecute(); // Rosenildo
			 is = new InformacoesServidor();

		}

		@Override
		protected Boolean doInBackground(Void... params) {
			
			String url = String.format(Locale.US,urlRota,
												 start.latitude, 
											     start.longitude, 
											     dest.latitude, 
											     dest.longitude);

			IConexaoWeb conWeb = FactoryConexaoWeb.getConexao( url, FactoryConexaoWeb.REQ_GET);

			String retorno = conWeb.buscarServidor();

			final Route route = new Route();
			try {
				// Obtém a String do JSON
				final String result = retorno;

				// Transforma a string em JSON
				JSONObject json = new JSONObject(result);
				// Pega a primeira rota retornada
				JSONObject jsonRoute = json.getJSONArray("routes").getJSONObject(0);
				JSONObject leg = jsonRoute.getJSONArray("legs").getJSONObject(0);

				// Obtém os passos do caminho
				JSONArray steps = leg.getJSONArray("steps");

				final int numSteps = steps.length();
				/*
				 * Itera através dos passos, decodificando a polyline e
				 * adicionando à rota.
				 */
				JSONObject step;
				for (int i = 0; i < numSteps; i++) {
					// Obtém o passo corrente
					step = steps.getJSONObject(i);
					// Decodifica a polyline e adiciona à rota
					route.addPoints(UtilRecTour.decodePolyLine(step.getJSONObject("polyline").getString("points")));
				}
			} catch (Exception e) {
				
				is.setFalhaRequisicao(true);
				is.setMsgErroServer(e.getMessage());
				is.setMsgUsuario("Verifique se seu aparelho tem conectividade com a Internet");
				is.setTituloMsgUsuario("Hove um probema");
			}
			
			is.setRetorno(route);

			return is.isFalhaRequisicao();
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);

			if (cbap != null) {
				cbap.operacaoConcluida(1,is);
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
