package mobi.rectour.bod.repositorios;

import java.util.Iterator;

import mobi.rectour.bod.bd.CriarBD;
import mobi.rectour.bod.bd.IRepositorio;
import mobi.rectour.bod.bd.TabelasBOD;
import mobi.rectour.bod.entidades.Atualizacao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class RepAtualizacao implements IRepositorio<Atualizacao, Iterator, Cursor> {

	public static final int REPOSITORIO_ATUALIZACAO = 001123;
	private CriarBD db;
	private Context c;

	public RepAtualizacao(Context c) {
		db = CriarBD.getBD(c);
		this.c = c;
	}
	
	
	
	@Override
	public long inserir(Atualizacao it) {
		ContentValues cv = new ContentValues();
		cv.put(TabelasBOD.CAMPO_NOME_TABELA , it.getNome_tabela());
		cv.put(TabelasBOD.CAMPO_DETERMINAR_ATUALIZACAO, it.getDeterminarAtualizacao());
		cv.put(TabelasBOD.CAMPO_DT_ULTIMA_ATUALIZACAO, it.getDataUltimaAtualizacao());
		
		return db.inserir(TabelasBOD.TBL_LogAtualizacao, cv);
	}

	@Override
	public String recuperarJson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator recuperarRegistro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor executarSql(String sql) {	
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Atualizacao recuperarItem(String campo) {
		
		String query = String.format("SELECT * FROM %s WHERE %s LIKE '%s'", TabelasBOD.TBL_LogAtualizacao , TabelasBOD.CAMPO_NOME_TABELA , campo);

		//query = String.format(query, TabelasBOD.TBL_LogAtualizacao);

		Cursor c = db.execSql(query);

		Atualizacao at = new  Atualizacao();
		
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				
				at.setDeterminarAtualizacao(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_DETERMINAR_ATUALIZACAO)));
				at.setDataUltimaAtualizacao(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_DT_ULTIMA_ATUALIZACAO)));
				at.setNome_tabela(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_NOME_TABELA)));
				
			}
			
		}
		
		return at;
	}

	@Override
	public void deletarItem(String codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserirItemSQL(String comando) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator recuperarRegistro(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
