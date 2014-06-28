package mobi.rectour.recHoteis.gui;

import mobi.rectour.R;
import mobi.rectour.mapa.funcoes.v2.funcMyLocation.BuscarLocalizacaoAtualV2;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/** Demonstrates customizing the info window and/or its contents. */
public class HotelMarker implements InfoWindowAdapter {

	// These a both viewgroups containing an ImageView with id "badge" and
	// two TextViews with id
	// "title" and "snippet".
	private final View mWindow;
	private final View mContents;

	public HotelMarker(LayoutInflater inflater) {
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

		String title = marker.getTitle();

		String id = marker.getSnippet();
		
		((TextView) view.findViewById(R.id.tvNome)).setText(title);

		LatLng myLocation = BuscarLocalizacaoAtualV2.getLocalizacao();

		if (id.equals("") || myLocation == null) {
			return;

		}

		/*Cursor c = RecTourDatabase.recuperarHoteisDistancia(myLocation, id);

		if (c.moveToNext()) {

			String rtEndereco = c.getString(c.getColumnIndex("rtEndereco"));
			String rtNome = c.getString(c.getColumnIndex("rtNome"));
			String rtTelefone = c.getString(c.getColumnIndex("rtTelefone"));
			String rtSite = c.getString(c.getColumnIndex("rtSite"));

			double distancia = c.getDouble(c.getColumnIndex("distancia"));
			double distanciaKm = UtilRecTour
					.convertPartialDistanceToKm(distancia);

			((TextView) view.findViewById(R.id.tvNome)).setText(rtNome);
			((TextView) view.findViewById(R.id.tvTelefone)).setText(rtTelefone);
			((TextView) view.findViewById(R.id.tvSite)).setText(rtSite);
			((TextView) view.findViewById(R.id.tvEndereco)).setText(rtEndereco);
			((TextView) view.findViewById(R.id.tvDistancia))
					.setText(UtilRecTour.getLegendaDistancia(distanciaKm));

		}

	}*/
	}

	// TextView titleUi = ((TextView) view.findViewById(R.id.title));
	// if (title != null) {
	// // Spannable string allows us to edit the formatting of the text.
	// SpannableString titleText = new SpannableString(title);
	// titleText.setSpan(new ForegroundColorSpan(Color.RED), 0,
	// titleText.length(), 0);
	// titleUi.setText(titleText);
	// } else {
	// titleUi.setText("");
	// }

	// String snippet = marker.getSnippet();
	// TextView snippetUi = ((TextView)
	// view.findViewById(R.id.snippet));
	// if (snippet != null && snippet.length() > 12) {
	// SpannableString snippetText = new SpannableString(snippet);
	// snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0,
	// 10, 0);
	// snippetText.setSpan(new ForegroundColorSpan(Color.BLUE), 12,
	// snippet.length(), 0);
	// snippetUi.setText(snippetText);
	// } else {
	// snippetUi.setText("");
	// }

}
