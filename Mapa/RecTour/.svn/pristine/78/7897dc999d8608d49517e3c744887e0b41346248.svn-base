package mobi.rectour.mapa.funcoes.v2.funcTracarRotas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobi.rectour.mapa.funcoes.FuncoesMenu.IFuncoesMenu;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class TracarRotasV2 implements IFuncoesMenu {

	private GoogleMap mMap;

	private Map<Integer, Polyline> mapPolyline = new HashMap<Integer, Polyline>();
	private List<Integer> idRotasBd = new ArrayList<Integer>();

	private Route rota;

	public TracarRotasV2(Route rota) {
		this.rota = rota;
	}

	public void removerRota(int IdRota, boolean visibilidade) {

		mapPolyline.get(idRotasBd.get(IdRota)).remove();//   setVisible(visibilidade);

		// mapPolyline.get(idRotasBd.get(IdRota)).getPoints().get(0); }
	}

	@Override
	public void execute(Object o) {
		mMap = (GoogleMap) o;
		new PlotarAsync().execute();
	}

	@Override
	public void cancel(Object o) {
		// TODO Auto-generated method stub

	}

	private class PlotarAsync extends AsyncTask<Void, Void, Void> {

		List<PolylineOptions> listPolyLines = new ArrayList<PolylineOptions>();

		@Override
		protected synchronized Void doInBackground(Void... params) {

			PolylineOptions polylineOptions = new PolylineOptions();

			polylineOptions.color(0xff000000);
			polylineOptions.width(5);

			idRotasBd.add(1);

			for (LatLng latlng : rota.getPoints()) {
				polylineOptions.add(latlng);
			}

			listPolyLines.add(polylineOptions);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			for (int i = 0; i < listPolyLines.size(); i++) {

				mapPolyline.put(idRotasBd.get(i),
						mMap.addPolyline(listPolyLines.get(i)));
			}
		}
	}
}