package mobi.rectour.recRoteirosTurismoLazer.gui.shopping;

import mobi.rectour.R;
import mobi.rectour.mapa.funcoes.v2.funcMyLocation.BuscarLocalizacaoAtualV2;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class ShoppingMaker implements InfoWindowAdapter{

	private final View mWindow;
	private final View mContents;     
	
	public ShoppingMaker(LayoutInflater inflater ) {
		mWindow = inflater.inflate(R.layout.balloon_overlay, null);
		mContents = inflater.inflate(R.layout.balloon_overlay, null);
	}

	@Override
	public View getInfoWindow(Marker marker) {

		render(marker, mWindow);
		return mWindow;
	}

	@Override
	public View getInfoContents(Marker marker) {

		render(marker, mContents);
		return mContents;
	}
	
	private void render(Marker marker, View view) {

//		String title = marker.getTitle();
		String id = marker.getSnippet();
		LatLng myLocation = BuscarLocalizacaoAtualV2.getLocalizacao();

		if (id.equals("") || myLocation == null) {
			return;

		}

//		Cursor c = RecTourDatabase.recuperarShoppingsDistancia(myLocation, id);
//
//		if (c.moveToNext()) {
//
//			String rtEndereco = c.getString(c.getColumnIndex("rtLogradouro"));
//			String rtNome = c.getString(c.getColumnIndex("rtNome"));
//			String rtTelefone = c.getString(c.getColumnIndex("rtTelefone"));
//			String rtSite = c.getString(c.getColumnIndex("rtSite"));
//			
//			double distancia = c.getDouble(c.getColumnIndex("distancia"));
//			double distanciaKm = UtilRecTour.convertPartialDistanceToKm(distancia);
//
//			((TextView) view.findViewById(R.id.tvNome)).setText(rtNome);
//			((TextView) view.findViewById(R.id.tvTelefone)).setText(rtTelefone);
//			((TextView) view.findViewById(R.id.tvSite)).setText(rtSite);
//			((TextView) view.findViewById(R.id.tvEndereco)).setText(rtEndereco);
//			((TextView) view.findViewById(R.id.tvDistancia)).setText(UtilRecTour.getLegendaDistancia(distanciaKm));
//			
//		}
	}
	
}
