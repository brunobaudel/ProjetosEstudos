package mobi.rectour.configuracao.selecionaIconLocation;

import java.util.List;

import mobi.rectour.R;
import mobi.rectour.configuracao.selecionaIconLocation.entidades.IconLocation;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SelecionarIconLocationADPT extends BaseAdapter {

	private List<IconLocation> lstiConLocation;
	private LayoutInflater inflater;
	
	
	public SelecionarIconLocationADPT(List<IconLocation> lstLocalizador,LayoutInflater infltr){
		
		this.lstiConLocation = lstLocalizador;
		this.inflater = infltr;
	}
	
	@Override
	public int getCount() {
		
		return lstiConLocation.size();
	}

	@Override
	public Object getItem(int position) {
		
		return lstiConLocation.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return  0;//Long.valueOf( lstHistorico.get(position).getId().replaceAll("[a-zA-Z]", ""));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.config_lista_tabelas_selecionar_icon_location_item, null);
			
			holder = new ViewHolder();
			holder.tvNomeLocalizador = (TextView) convertView.findViewById(R.id.tvNomeLocalizador);
			holder.ivLocalizador = (ImageView) convertView.findViewById(R.id.ivLocalizador);
			holder.rlSelecionado = (LinearLayout)  convertView.findViewById(R.id.llSelecionado);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		IconLocation iconLocalizador = lstiConLocation.get(position);
		
		holder.tvNomeLocalizador.setText(iconLocalizador.getNomeIcone());
		holder.ivLocalizador.setBackgroundResource(iconLocalizador.getIdIcone());
		holder.ivLocalizador.setTag(iconLocalizador);
		if(iconLocalizador.isSelecionado()){
			holder.rlSelecionado.setBackgroundColor(Color.GRAY);
		}else {
			holder.rlSelecionado.setBackgroundColor(Color.WHITE);
		}
		
		
		return convertView;
	}
	
	
	
	public static final class ViewHolder {
		public TextView  tvNomeLocalizador;
		public ImageView ivLocalizador;
		public LinearLayout rlSelecionado;
		
	}

	
}
