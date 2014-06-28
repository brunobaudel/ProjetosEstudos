package mobi.rectour.configuracao;

import mobi.rectour.R;
import mobi.rectour.configuracao.selecionaIconLocation.SelecionarIconLocationFr;
import mobi.rectour.configuracao.selecionaIconLocation.entidades.IconLocation;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Configuracoes {
	
	private SharedPreferences sp;
	private Editor e;

	public Configuracoes(Context c) {
		sp = c.getSharedPreferences("confRecTour",
				Context.MODE_PRIVATE);
		e = sp.edit();
	}

	//Localizador
	
	public boolean isIconLocalizacaoSalvo(){
		return sp.getBoolean("recTourIconLocalizacaoSalvo", false);
	}
	
	public IconLocation getIconLocation(){
		
		IconLocation il1 = new IconLocation("Alien Sorvete", R.drawable.l_alien_sorvete,SelecionarIconLocationFr.idAlien, true);
		
		il1.setIdIcone(sp.getInt("recTourIconLocalizacao", il1.getIdIcone()));
		il1.setNomeIcone(sp.getString("recTourNomeLocalizacao", il1.getNomeIcone()));
		il1.setOrdemSelecionado(sp.getInt("recTourOrdemLocalizacao", il1.getOrdemSelecionado()));
		il1.setSelecionado(sp.getBoolean("recTourItemSelecionado", il1.isSelecionado()));
		
		return il1;
		
	}
	public void setIconLocation(IconLocation iconLocation){
		
		e.putInt("recTourIconLocalizacao", iconLocation.getIdIcone());
		e.putString("recTourNomeLocalizacao",  iconLocation.getNomeIcone());
		e.putInt("recTourOrdemLocalizacao", iconLocation.getOrdemSelecionado());
		e.putBoolean("recTourItemSelecionado", iconLocation.isSelecionado());
		e.commit();
	}
	
	//Fim localizador
	
}
