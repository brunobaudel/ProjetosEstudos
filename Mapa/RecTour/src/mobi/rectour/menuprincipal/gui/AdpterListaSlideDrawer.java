package mobi.rectour.menuprincipal.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobi.rectour.R;
import mobi.rectour.configuracao.Configuracoes;
import mobi.rectour.geral.RecTourGeral;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdpterListaSlideDrawer extends BaseAdapter {
	
	private Map<Integer, List<String>>  mapItens;
	private LayoutInflater inflater;
	private Context c;
	private Configuracoes configuracoes;
	
	public AdpterListaSlideDrawer(Context c){
		
		this.c = c;
		
		configuracoes = new Configuracoes(c);
		
		inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		List<String> listaNavegacao  = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.nav_classes)));
		mapItens = new HashMap<Integer, List<String>>();
		
		for (int i = 0; i < listaNavegacao.size(); i++) {
			
			String[] parmArray = listaNavegacao.get(i).split("#"); 
			List<String> param = new ArrayList<String>();
			param.add(parmArray[0]);
			param.add(parmArray[1]);
			param.add(parmArray[2]);
			param.add( parmArray.length > 3 ? parmArray[3]:"" );
			mapItens.put(i, param);
		}
	}
	
	@Override
	public int getCount() {
		return mapItens.size();
	}

	@Override
	public Object getItem(int position) {
		return mapItens.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;  
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.menu_container_lista_item, null);
			holder = new ViewHolder();
			holder.tvTituloDrawer = (TextView) convertView.findViewById(R.id.tvTituloDrawer);
			holder.ivIconLista    = (ImageView) convertView.findViewById(R.id.ivIconLista);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		List<String> titulo = mapItens.get(position);
		holder.tvTituloDrawer.setText(titulo.get(1));
		holder.ivIconLista.setBackgroundResource(RecTourGeral.getIconMinhaLocalizacao( configuracoes ));
		//holder.tvTituloDrawer.setBackgroundDrawable(
		//		c.getResources().getDrawable( RecTourGeral.getIconMinhaLocalizacao( configuracoes )));//c.getResources().getIdentifier(titulo.get(2), null, c.getPackageName())));
		
		return convertView;
	}
	
	private static final class ViewHolder {
		public TextView tvTituloDrawer;
		public ImageView ivIconLista;
	}

}
