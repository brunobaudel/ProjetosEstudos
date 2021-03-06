package mobi.rectour.recRoteirosTurismoLazer.gui.teatro;

import mobi.rectour.R;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TeatroListaNavegacaoADPT extends CursorAdapter{

	public TeatroListaNavegacaoADPT(Context context, Cursor cursor) {
		super(context, cursor, true);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
			String rtNome = cursor.getString(cursor.getColumnIndex("rtNome"));
			((TextView) view.findViewById(R.id.tvNome)).setText(rtNome);
			LatLng pontoMapa = new LatLng( cursor.getDouble(cursor.getColumnIndex("rtLatitude")),cursor.getDouble(cursor.getColumnIndex("rtLongitude")));
		    view.setTag(pontoMapa);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.ponte_lista_navegacao_item, parent, false);
		return retView;
	}
}

