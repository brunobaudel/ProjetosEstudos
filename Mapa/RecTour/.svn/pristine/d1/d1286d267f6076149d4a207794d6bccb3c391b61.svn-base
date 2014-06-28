package mobi.rectour.mapa.funcoes.foursquare;

import java.util.List;

import mobi.rectour.R;
import mobi.rectour.mapa.funcoes.foursquare.entidadesvenues.Venues;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class VenuesAdpt extends BaseAdapter {

	
	private List<Venues> lstEstabelecimentos;
	private LayoutInflater inflater;
	
	
	public VenuesAdpt(List<Venues> lstVenues,LayoutInflater infltr){
		
		this.lstEstabelecimentos = lstVenues;
		this.inflater = infltr;
	}
	
	@Override
	public int getCount() {
		
		return lstEstabelecimentos.size();
	}

	@Override
	public Object getItem(int position) {
		
		return lstEstabelecimentos.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return  0;//Long.valueOf( lstEstabelecimentos.get(position).getId().replaceAll("[a-zA-Z]", ""));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.dialog_listagem_item, null);
			
			holder = new ViewHolder();
			holder.tvEstabelecimento = (TextView) convertView.findViewById(R.id.tvEstabelecimento);
//			holder.imgPinoNumVagas = (ImageView) convertView.findViewById(R.id.);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Venues est = lstEstabelecimentos.get(position);
		
		holder.tvEstabelecimento.setText(est.getName());	
		holder.tvEstabelecimento.setTag(est.getId());
		
		return convertView;
	}
	
	
	public static final class ViewHolder {
		public TextView tvEstabelecimento;
//		public ImageView imgPinoNumVagas;
	}

}
