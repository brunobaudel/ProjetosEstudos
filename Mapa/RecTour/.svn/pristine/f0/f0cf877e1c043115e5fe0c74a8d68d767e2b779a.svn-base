package mobi.rectour.mapa.funcoes.foursquare.listtips;

import java.util.Date;
import java.util.List;

import com.squareup.picasso.Picasso;

import mobi.rectour.R;
import mobi.rectour.mapa.funcoes.foursquare.entidadestips.Items;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TipsADPT extends BaseAdapter {

	private List<Items>  listVenues;
	private LayoutInflater inflater;
	private Context c;
	
	public TipsADPT(List<Items> listVenuesTips,LayoutInflater infltr , Context c){
		
		this.listVenues = listVenuesTips;
		this.inflater = infltr;
	}
	
	@Override
	public int getCount() {
		
		return listVenues.size();
	}

	@Override
	public Object getItem(int position) {
		
		return listVenues.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return  0;//Long.valueOf( lstHistorico.get(position).getId().replaceAll("[a-zA-Z]", ""));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.hotel_informacoes_item_card, null);
			
			holder = new ViewHolder();
			holder.tvComentario = (TextView) convertView.findViewById(R.id.tvComentario);
			holder.ivFoto = (ImageView) convertView.findViewById(R.id.ivFoto);
			holder.tvNomeUsuario = (TextView) convertView.findViewById(R.id.tvNomeUsuario);
			holder.tvDataComentario = (TextView) convertView.findViewById(R.id.tvDataComentario);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Items tipsVenue = listVenues.get(position);
		
		holder.tvComentario.setText(tipsVenue.getText());
		holder.tvNomeUsuario.setText(tipsVenue.getUser().getFirstName()  + "\n" +tipsVenue.getUser().getLastName());
		
		
		Date date = new Date(tipsVenue.getCreatedAt().longValue()*1000);
		
		String data = DateUtils.getRelativeTimeSpanString(c, date.getTime() ).toString();
		
		holder.tvDataComentario.setText( String.valueOf(data));
		
		String urlFotoUser = tipsVenue.getUser().getPhoto().getPrefix() + "100x100" + tipsVenue.getUser().getPhoto().getSuffix();
		
		 Picasso.with(c) //
	        .load(urlFotoUser) //
	        .placeholder( mobi.rectour.R.drawable.icon_carregando) //
	        .error(mobi.rectour.R.drawable.icon_carregando ) //
	        .fit() //
	        .into(holder.ivFoto);
		
		
		//holder.ivFoto.setBackgroundResource(tipsVenue.getIdIcone());
		
		return convertView;
	}
	
	
	
	public static final class ViewHolder {
		public TextView  tvComentario;
		public ImageView ivFoto;
		public TextView  tvNomeUsuario;
		public TextView  tvDataComentario;
		
	}

	
}
