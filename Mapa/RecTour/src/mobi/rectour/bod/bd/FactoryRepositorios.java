package mobi.rectour.bod.bd;

import mobi.rectour.bod.repositorios.RepAtualizacao;
import mobi.rectour.bod.repositorios.RepE_;
import android.content.Context;

public class FactoryRepositorios {

	
	public static IRepositorio createRepositorios(int tipoBuffer,Context c){
		
		IRepositorio repositorio = null;
		
		switch (tipoBuffer) {
		
		case RepE_.REPOSITORIO_PARADA :
			
			repositorio =  new RepE_(c);
			break;
		case RepAtualizacao.REPOSITORIO_ATUALIZACAO :
			
			repositorio =  new RepAtualizacao(c);
			

		default:
			break;
		}
		
		return repositorio;
		
		
	}
	
}
