package mobi.rectour.configuracao.selecionaIconLocation;

import java.util.ArrayList;
import java.util.List;

import mobi.rectour.R;
import mobi.rectour.configuracao.Configuracoes;
import mobi.rectour.configuracao.selecionaIconLocation.SelecionarIconLocationADPT.ViewHolder;
import mobi.rectour.configuracao.selecionaIconLocation.entidades.IconLocation;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SelecionarIconLocationFr extends Fragment implements OnItemClickListener{
	
	private ListView listaTabelas;
//	private SelecionarIconLocationADPT selecionarIconLocationADPT;
	//private AtualizarDadosApp atualizarDadosApp;
	
	Configuracoes configuracoes ;
	LayoutInflater inflater;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		configuracoes = new Configuracoes(activity);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		View view = inflater.inflate(R.layout.config_lista_tabelas_atualizar_tela, container, false);
          
		
		this.inflater = inflater;
		
		listaTabelas = (ListView) view.findViewById(R.id.lvTabelasAtualizacao);
		
		
		SelecionarIconLocationADPT selecionarIconLocationADPT = new SelecionarIconLocationADPT( getListIconLocation() ,inflater);
		
		listaTabelas.setAdapter(selecionarIconLocationADPT);
		listaTabelas.setOnItemClickListener(this);
		listaTabelas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listaTabelas.setSelector(new ColorDrawable(Color.GRAY));
		listaTabelas.setItemChecked(configuracoes.getIconLocation().getOrdemSelecionado(),true);
		
		//listaTabelas.setc
		
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		configuracoes.setIconLocation(  (IconLocation) ((ViewHolder)arg1.getTag()).ivLocalizador.getTag());
//		listaTabelas.setItemChecked(arg2, true);
		
		listaTabelas.setAdapter(null);
		
		SelecionarIconLocationADPT selecionarIconLocationADPT = new SelecionarIconLocationADPT( getListIconLocation() ,inflater);
		
		listaTabelas.setAdapter(selecionarIconLocationADPT);
		//listaTabelas.setOnItemClickListener(this);
		//listaTabelas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		//listaTabelas.setSelector(new ColorDrawable(Color.GRAY));
		
	}
	
	public static int idAlien = 0;
	public static int idBalao = 1;
	public static int idOlho  = 2;
	public static int idSeta  = 3;
	
	private List<IconLocation> getListIconLocation(){
		
		List<IconLocation> listIcon = new ArrayList<IconLocation>();
		
		
		IconLocation iconLoc = configuracoes.getIconLocation();
		
		IconLocation il1 = new IconLocation("Alien Sorvete", R.drawable.l_alien_sorvete,idAlien, iconLoc.selecionado("Alien Sorvete"));
		listIcon.add(il1);
		
		IconLocation il2 = new IconLocation("Bal�o Happy", R.drawable.l_balao_happy,idBalao, iconLoc.selecionado("Bal�o Happy"));
		listIcon.add(il2);

		IconLocation il3 = new IconLocation("Olho Alien", R.drawable.l_pitao_olho_alien,idOlho, iconLoc.selecionado("Olho Alien"));
		listIcon.add(il3);
		
		IconLocation il4 = new IconLocation("Seta Wolf", R.drawable.l_seta_wolverine,idSeta, iconLoc.selecionado("Seta Wolf"));
		listIcon.add(il4);
		
		return listIcon;
		
	}
	

}
