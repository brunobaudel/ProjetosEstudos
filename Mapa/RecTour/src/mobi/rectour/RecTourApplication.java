package mobi.rectour;

import com.parse.Parse;

import mobi.rectour.geral.RecTourDatabase;
import mobi.rectour.geral.RecTourGeral;
import mobi.rectour.geral.RecTourVariaveisGlobais;
import android.app.Application;

public class RecTourApplication extends Application {
	
	
	private boolean criacaoBD;
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		RecTourVariaveisGlobais.diretorioApp = RecTourGeral.getDiretorioRecTour();
		criacaoBD = !RecTourDatabase.bancoJaCriado();
		
		if(criacaoBD){
			RecTourDatabase.criarTodasAsTabelas();
		}
		
		Parse.initialize(this, getResources().getString(R.string.YOUR_APPLICATION_ID),
                getResources().getString(R.string.YOUR_CLIENT_KEY));
		
		
	}
	

}
