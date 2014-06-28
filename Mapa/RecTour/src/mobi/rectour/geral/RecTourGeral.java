package mobi.rectour.geral;

import java.io.File;

import com.google.android.gms.internal.eq;

import mobi.rectour.R;
import mobi.rectour.configuracao.Configuracoes;

import android.os.Environment;

public class RecTourGeral {

	public static final String RT_NOME_DIRETORIO = "RT";
	public static final String RT_NOME_DIRETORIO_TEMPORARIO = "temp";
	public static final String RT_NOME_DIRETORIO_ARQUIVOS = "arquivos";	

	
	public static String getExternalStorageDir() {
		String str;
		if (!"mounted".equals(Environment.getExternalStorageState()))
			str = "";
		else
			str = Environment.getExternalStorageDirectory().getAbsolutePath();
		return str;
	}

	public static String getDiretorioRecTour() {
		String str = getExternalStorageDir();
		if ("".equals(str))
			str = "";
		else
			str = str + "/" + RT_NOME_DIRETORIO;
		
		File dir = new File(str);
		dir.mkdirs();
		
		dir = new File(str + "/" + RT_NOME_DIRETORIO_ARQUIVOS);
		dir.mkdirs();
		
		return str;
	}
	
	
	public static int getIconMinhaLocalizacao(Configuracoes configuracao){
		
		
		int retorno = 0;
		
		
		switch (configuracao.getIconLocation().getIdIcone()) {
		case R.drawable.l_alien_sorvete:
			
			retorno = R.drawable.comic_sorve;
			
			break;
			
			
		case R.drawable.l_balao_happy:
					
					retorno = R.drawable.comic_balao;
					
					break;
		case R.drawable.l_pitao_olho_alien:
			
			retorno = R.drawable.comic_peao;
			
			break;
		case R.drawable.l_seta_wolverine:
			
			retorno = R.drawable.comic_seta_wolf;
			
			break;

		default:
			break;
		}
		
		
		return retorno;
		
		
	}
	
	
	
}
