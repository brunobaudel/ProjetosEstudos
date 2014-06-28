package mobi.rectour.bod.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CriarBD extends SQLiteOpenHelper {

	private static SQLiteDatabase db;
	private static CriarBD dbCriado;

	
	private CriarBD(Context context) {
		super(new DataBaseContext(context), "BD_BUS_OPENDOCS", null, 1);
	}
	
	
	public static CriarBD getBD(Context context) {
		if (db == null) {
			dbCriado =  new CriarBD(context);
			db = dbCriado.getWritableDatabase();
		}
		return dbCriado;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		for (int i = 0; i <  TabelasBOD.getTables().length; i++) {
			db.execSQL( TabelasBOD.getTables()[i]);
			if(TabelasBOD.getTables()[i].contains("tbl_log_atualizacao")){
				for (int j = 0; j <  TabelasBOD.getTables().length; j++){
					
					//execSqlInsert("INSERT INTO tbl_log_atualizacao () VALUES ()");
				}
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}
	
	private void open(){
		db =  getWritableDatabase();
	}

	public long inserir(String tabela, ContentValues cv){
		if(!db.isOpen()) open();
		long retorno = db.insert(tabela,null, cv);
		//db.close();
		return retorno;
	}
	
	public long atualizar(String tabela, ContentValues cv,String whereClausule){
		if(!db.isOpen()) open();
		long retorno = db.update(tabela, cv, whereClausule, null);
		//db.close();
		return retorno;
	}
	
	public long remover(String tabela,String whereClausule){
		if(!db.isOpen()) open();
		long retorno = db.delete(tabela,whereClausule , null);
		//db.close();
		return retorno;
	}
	
	public Cursor listar(String tabela,String whereClausule){
		if(!db.isOpen()) open();
		return db.query(tabela, null, whereClausule , null, null, null, "_id");  
	}
	
	public Cursor execSql(String sql){
		if(!db.isOpen()) open();
		return db.rawQuery(sql, null);
	}
	
	public void execSqlInsert(String sql){
		if(!db.isOpen()) open();
		try{
		 db.execSQL(sql);
		}catch (Exception e) {
			Log.d("Bus","Erro ao inserir!" + sql);
		}
	}
	
}
