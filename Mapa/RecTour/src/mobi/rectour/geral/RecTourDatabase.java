package mobi.rectour.geral;

import java.io.File;

import mobi.rectour.atualizacoesremotas.entidade.Atualizacao;
import mobi.rectour.recHoteis.entidades.Hotel;
import mobi.rectour.recRestaurantes.entidades.Restaurante;
import mobi.rectour.recRoteirosTurismoLazer.entidades.Museu;
import mobi.rectour.recRoteirosTurismoLazer.entidades.Ponte;
import mobi.rectour.recRoteirosTurismoLazer.entidades.Shopping;
import mobi.rectour.recRoteirosTurismoLazer.entidades.Teatro;
import mobi.rectour.util.UtilRecTour;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Date;

import com.google.android.gms.maps.model.LatLng;


public class RecTourDatabase extends Object {

	//Variaveis para fazer o calculo da distancia
	public final static String CAMPO_TB_LATITUDE_SEN  = "latitude_sen";
	public final static String CAMPO_TB_LATITUDE_COS  = "latitude_cos";
	public final static String CAMPO_TB_LONGITUDE_SEN = "longitude_sen";
	public final static String CAMPO_TB_LONGITUDE_COS = "longitude_cos";
	
	public final static String strAddCampos =  CAMPO_TB_LATITUDE_SEN + " INTEGER  NULL," 
			+ CAMPO_TB_LATITUDE_COS  + " INTEGER  NULL," 
			+ CAMPO_TB_LONGITUDE_SEN + " INTEGER  NULL,"
			+ CAMPO_TB_LONGITUDE_COS + " INTEGER  NULL)";
	
	static int versaoDoBanco = 0;
	public static String NOME_DO_BANCO = "RecTourDB.db";
	
	static SQLiteDatabase db;

	public RecTourDatabase(){
		super();
	}

	public static SQLiteDatabase getBanco() {
		SQLiteDatabase db = SQLiteDatabase.openDatabase(RecTourVariaveisGlobais.diretorioApp + File.separator + NOME_DO_BANCO, null,
							SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.CREATE_IF_NECESSARY ); 

		return db;// Na primeira vez o bd esta vindo nullo 
	}
	
	public static boolean bancoJaCriado() {
		
		
		try {
			File f = new File(RecTourVariaveisGlobais.diretorioApp + File.separator + NOME_DO_BANCO);
			
			
			return f.exists();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}	
	
	public static void criarTodasAsTabelas() {

		try{
			getVersaoDoBanco();
		}catch(Exception e){
			versaoDoBanco = 0;
		}

		switch (versaoDoBanco) {
		case 0:
			criarTodasAsTabelasV1();

		default:
			break;
		}
		
	}
	
	public static void getVersaoDoBanco(){
		SQLiteDatabase db = RecTourDatabase.getBanco();
		Cursor cursor = db.rawQuery("SELECT versao_estrutura_bd FROM configuracoes", null);

		cursor.moveToFirst();
		versaoDoBanco = cursor.getInt(cursor.getColumnIndex("versao_estrutura_bd"));

		cursor.close();
		db.close();		
	}
	
	public static void salvarVersaoDoBanco(){
		try{
			SQLiteDatabase db = getBanco();
			ContentValues valoresIniciais = new ContentValues();
			valoresIniciais.put("versao_estrutura_bd", versaoDoBanco);
			
			Cursor cursor = db.rawQuery("SELECT versao_estrutura_bd FROM configuracoes", null);
			if (cursor.getCount() == 0) {
				valoresIniciais.put("ultimo_sequencial_gerado", 0);
				db.insert("configuracoes", null, valoresIniciais);
			} else {
				db.update("configuracoes", valoresIniciais, null, null);
			}			
			cursor.close();
			db.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private static void criarTodasAsTabelasV1(){
		
		try{
			db = getBanco();
			db.execSQL("BEGIN TRANSACTION");
			
			try{
				db.execSQL("CREATE TABLE restaurante(" + "_id INTEGER  NOT NULL PRIMARY KEY," 
						+ "rtId             		   INTEGER       NULL," 
						+ "rtNome             		   VARCHAR( 70)  NULL," 
						+ "rtEndereco          		   VARCHAR(150)  NULL,"
						+ "rtTelefone          		   VARCHAR( 50)  NULL," 
						+ "rtEspecialidade     		   VARCHAR( 50)  NULL,"
						+ "rtSite            		   VARCHAR(100)  NULL," 
						+ "rtEmail         			   VARCHAR( 80)  NULL," 
						+ "rtFavorito                  VARCHAR( 01)  NULL,"
						+ "rtLatitude            	   INTEGER NULL," 
						+ "rtLongitude         		   INTEGER NULL," + strAddCampos);

				db.execSQL("CREATE TABLE hotel(" + "_id INTEGER  NOT NULL PRIMARY KEY,"
						+ "rtId             		   INTEGER       NULL," 
						+ "rtNome             		   VARCHAR(  70) NULL," 
						+ "rtEndereco          		   VARCHAR( 150) NULL,"
						+ "rtTelefone          		   VARCHAR(  50) NULL," 
						+ "rtSite            		   VARCHAR( 100) NULL," 
						+ "rtFavorito                  VARCHAR(  01) NULL,"
						+ "rtImagem                    TEXT   ( 300) NULL,"
						+ "rtLatitude            	   DECIMAL(2,17) NULL,"  //INTEGER NULL," // DECIMAL(2,17)
						+ "rtLongitude         		   DECIMAL(2,17) NULL," + strAddCampos); //INTEGER NULL," + strAddCampos);//DECIMAL(2,17)

				db.execSQL("CREATE TABLE shopping(" + "_id INTEGER  NOT NULL PRIMARY KEY,"
						+ "rtID                        INTEGER       NULL,"
						+ "rtNome             		   VARCHAR(  50) NULL," 
						+ "rtDescricao         		   TEXT   ( 600) NULL,"
						+ "rtBairro          		   VARCHAR(  50) NULL,"
						+ "rtLogradouro                VARCHAR( 100) NULL,"
						+ "rtTelefone          		   VARCHAR(  50) NULL," 
						+ "rtSite            		   VARCHAR( 100) NULL," 
						+ "rtFavorito                  VARCHAR(  01) NULL,"
						+ "rtLatitude            	   DECIMAL(2,17) NULL," 
						+ "rtLongitude         		   DECIMAL(2,17) NULL,"
						+ "rtFuncionamento     		   VARCHAR(  20) NULL," 
						+ "rtFuncionamentoDomingo	   VARCHAR(  20) NULL," + strAddCampos); 

				db.execSQL("CREATE TABLE museu(" + "_id INTEGER  NOT NULL PRIMARY KEY," 
						+ "rtID                        INTEGER  NOT NULL ,"
						+ "rtNome             		   VARCHAR(  50) NULL," 
						+ "rtDescricao         		   TEXT   ( 600) NULL,"
						+ "rtBairro          		   VARCHAR(  50) NULL,"
						+ "rtLogradouro                VARCHAR( 100) NULL,"
						+ "rtTelefone          		   VARCHAR(  50) NULL," 
						+ "rtSite            		   VARCHAR( 100) NULL," 
						+ "rtFavorito                  VARCHAR( 01 ) NULL,"
						+ "rtLatitude            	   DECIMAL(2,17) NULL," 
						+ "rtLongitude         		   DECIMAL(2,17) NULL," + strAddCampos);

				db.execSQL("CREATE TABLE teatro(" + "_id INTEGER  NOT NULL PRIMARY KEY,"
						+ "rtID                        INTEGER  NOT NULL ,"
						+ "rtNome             		   VARCHAR(  50) NULL," 
						+ "rtDescricao         		   TEXT   ( 600) NULL,"
						+ "rtBairro          		   VARCHAR(  50) NULL,"
						+ "rtLogradouro                VARCHAR( 100) NULL,"
						+ "rtTelefone          		   VARCHAR(  50) NULL," 
						+ "rtFavorito                  VARCHAR(  01) NULL,"
						+ "rtLatitude            	   DECIMAL(2,17) NULL," 
						+ "rtLongitude         		   DECIMAL(2,17) NULL," + strAddCampos);

				db.execSQL("CREATE TABLE ponte(" + "_id INTEGER  NOT NULL PRIMARY KEY,"
						+ "rtID                        INTEGER  NOT NULL ,"
						+ "rtNome             		   VARCHAR(  50) NULL," 
						+ "rtDescricao         		   TEXT   ( 600) NULL,"
						+ "rtBairro          		   VARCHAR(  50) NULL,"
						+ "rtFavorito                  VARCHAR(  01) NULL,"
						+ "rtLatitude            	   DECIMAL(2,17) NULL," 
						+ "rtLongitude         		   DECIMAL(2,17) NULL," + strAddCampos);
				
				db.execSQL("CREATE TABLE descricao(" + "rtID INTEGER  NOT NULL PRIMARY KEY,"
						+ "rtCodigoIdioma      		   INTEGER NULL," 
						+ "rtIdioma         		   VARCHAR(  30) NULL,"
						+ "rtDescricao          	   TEXT   ( 600) NULL,"
						+ "rtShoppingFk                INTEGER   NOT NULL,"
						+ "rtMuseuFk          		   INTEGER   NOT NULL," 
						+ "rtTeatroFk            	   INTEGER   NOT NULL,"
						+ "FOREIGN KEY(rtShoppingFk) REFERENCES shopping(rtID) ON DELETE NO ACTION ON UPDATE NO ACTION,"
						+ "FOREIGN KEY(rtMuseuFk)    REFERENCES museu(rtID)    ON DELETE NO ACTION ON UPDATE NO ACTION,"
						+ "FOREIGN KEY(rtTeatroFk)   REFERENCES teatro(rtID)   ON DELETE NO ACTION ON UPDATE NO ACTION)");
						
				
				db.execSQL("CREATE TABLE configuracoes(" + "ultimo_sequencial_gerado INTEGER NOT NULL,"
						+ "id_instalacao 			  CHAR(6)         NULL,"
						+ "versao_estrutura_bd 		  INTEGER     NOT NULL)");
				
				
				db.execSQL("CREATE TABLE log_atualizacao(" + "_id INTEGER  NOT NULL PRIMARY KEY," 
						+ "rtID                             INTEGER   NULL ,"
						+ "rtTabela             		    TEXT     NULL," 
						+ "rtDt_ultima_att        		    NUMERIC  NULL,"
						+ "rtDescricao_Log_Usuario 		    TEXT     NULL," 
						+ "rtDescricao_Log_Dev   		    TEXT     NULL," 
						+ "rtAtualizar          		    TEXT(  50) NULL)");
				
				
				db.execSQL("COMMIT TRANSACTION");
				
				versaoDoBanco = 1;
				salvarVersaoDoBanco();
				inseriLogAtualizacao(1, "restaurante" , "Atualizar Informações");
				inseriLogAtualizacao(2, "hotel"       , "Atualizar Informações");
				inseriLogAtualizacao(3, "museu"       , "Atualizar Informações");
				inseriLogAtualizacao(4, "shopping"    , "Atualizar Informações");
				inseriLogAtualizacao(5, "teatro"      , "Atualizar Informações");
				inseriLogAtualizacao(6, "ponte"       , "Atualizar Informações");
				
			}catch(Exception e){
				db.execSQL("ROLLBACK");
				e.printStackTrace();
			}
			
		}catch(Exception e){
			/////// Verificar erro de quando o bd n esta criado
		}
	
	}
	
	
	//***********************************Inicio Restaurante***********************************
	public static void inserirRestauranteParaTestes(Restaurante restaurante ){
		try {
			SQLiteDatabase db = getBanco();
			ContentValues valoresIniciais = new ContentValues();

			valoresIniciais.put("rtID",            restaurante.getRtID());
			valoresIniciais.put("rtNome",          restaurante.getRtNome() );
			valoresIniciais.put("rtEndereco",      restaurante.getRtEndereco()     );
			valoresIniciais.put("rtTelefone",      restaurante.getRtTelefone()     );
			valoresIniciais.put("rtEspecialidade", restaurante.getRtEspecialidade());
			valoresIniciais.put("rtSite",          restaurante.getRtSite()         );
			valoresIniciais.put("rtEmail",         restaurante.getRtEmail()        );
			valoresIniciais.put("rtLatitude",      restaurante.getLatitude() );
			valoresIniciais.put("rtLongitude",     restaurante.getLongitude());
			   //	Setando os campos para o bd calcular a distancia	   
			valoresIniciais.put(CAMPO_TB_LATITUDE_SEN,     restaurante.getCAMPO_TB_LATITUDE_SEN() );
			valoresIniciais.put(CAMPO_TB_LATITUDE_COS,     restaurante.getCAMPO_TB_LATITUDE_COS() );
			valoresIniciais.put(CAMPO_TB_LONGITUDE_SEN,    restaurante.getCAMPO_TB_LONGITUDE_SEN() );
			valoresIniciais.put(CAMPO_TB_LONGITUDE_COS,    restaurante.getCAMPO_TB_LONGITUDE_COS() );

			db.insert("restaurante", null, valoresIniciais);
			db.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void deleteAllRestauranteParaTestes(){
		try {
			SQLiteDatabase db = getBanco();
			db.delete("restaurante", "", null);
			db.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	 public static Cursor recuperarRestaurantesDistancia(LatLng center , String id ){
			
		 SQLiteDatabase db = getBanco();
		 String query = "SELECT *, "
						+ UtilRecTour.buildDistanceQuery(center.latitude, center.longitude)
						+ " FROM restaurante WHERE _id = %s"  ;
		 
		 query = String.format(query, id);
		 
		return db.rawQuery(query, null);
		 
	 }
	
	
	
	public static Cursor recuperarRestaurantesDistancia(LatLng center){
		SQLiteDatabase db = getBanco();
		 String query = "SELECT *, "
					+ UtilRecTour.buildDistanceQuery(center.latitude, center.longitude)
					+ " FROM restaurante order by distancia DESC"  ;
		 
		 return db.rawQuery(query, null);
		 
	 }
	
	
	//***********************************Fim Restaurante***********************************
	
	//***********************************Inicio Hotel***********************************
	 
	/*
	 public static void inserirHotelParaTestes(int id, String nome, String endereco, String telefone, 
	               String site, double latitude, double longitude){
	  
	  try{
	   SQLiteDatabase db = getBanco();
	   ContentValues valoresIniciais = new ContentValues();

	   valoresIniciais.put("rtID",        id       );
	   valoresIniciais.put("rtNome",      nome     );
	   valoresIniciais.put("rtEndereco",  endereco );
	   valoresIniciais.put("rtTelefone",  telefone );
	   valoresIniciais.put("rtSite",      site     );
	   valoresIniciais.put("rtLatitude",  latitude );
	   valoresIniciais.put("rtLongitude", longitude);
	   
	   db.insert("hotel", null, valoresIniciais);
	   db.close();
	   
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	 }
	 */
	 public static void inserirHotelParaTestes(Hotel hotel ){
		  try {
		   SQLiteDatabase db = getBanco();
		   ContentValues valoresIniciais = new ContentValues();

		   valoresIniciais.put("rtID",            hotel.getRtID()      );
		   valoresIniciais.put("rtNome",          hotel.getRtNome()    );
		   valoresIniciais.put("rtEndereco",      hotel.getRtEndereco());
		   valoresIniciais.put("rtTelefone",      hotel.getRtTelefone());
		   valoresIniciais.put("rtSite",          hotel.getRtSite()    );
		   
		   if( hotel.getLatitude() == 0.0 ){
			   valoresIniciais.put("rtLatitude",      -8.131087  );
			   valoresIniciais.put("rtLongitude",     -34.900281 );
			
			   valoresIniciais.put(CAMPO_TB_LATITUDE_SEN,  -0.1414464375518437 );
			   valoresIniciais.put(CAMPO_TB_LATITUDE_COS,   0.9899459102920182 );
			   valoresIniciais.put(CAMPO_TB_LONGITUDE_SEN, -0.5721519284002278 );
			   valoresIniciais.put(CAMPO_TB_LONGITUDE_COS,  0.8201476518456299 );

		   }else{
			   valoresIniciais.put("rtLatitude",      hotel.getLatitude()  );
			   valoresIniciais.put("rtLongitude",     hotel.getLongitude() );

			   //	Setando os campos para o bd calcular a distancia	   
			   valoresIniciais.put(CAMPO_TB_LATITUDE_SEN,     hotel.getCAMPO_TB_LATITUDE_SEN() );
			   valoresIniciais.put(CAMPO_TB_LATITUDE_COS,     hotel.getCAMPO_TB_LATITUDE_COS() );
			   valoresIniciais.put(CAMPO_TB_LONGITUDE_SEN,    hotel.getCAMPO_TB_LONGITUDE_SEN() );
			   valoresIniciais.put(CAMPO_TB_LONGITUDE_COS,    hotel.getCAMPO_TB_LONGITUDE_COS() );
		   }
		   
		   
		   
		   
		   //
		   db.insert("hotel", null, valoresIniciais);
		   db.close();

		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }
	 
	 
	 public static void deleteAllHotel(){
	  try {
	   SQLiteDatabase db = getBanco();
	   db.delete("hotel", "", null);
	   db.close();

	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }
	 
	 
	 public static Cursor recuperarHoteisDistancia(LatLng center){
		
		 SQLiteDatabase db = getBanco();
		 String query = "SELECT *, "
						+ UtilRecTour.buildDistanceQuery(center.latitude, center.longitude)
						+ " FROM hotel order by distancia DESC"  ;
		 
		return db.rawQuery(query, null);
		 
	 }
	 
	 
	 public static Cursor recuperarHoteisDistancia(LatLng center , String id ){
			
		 SQLiteDatabase db = getBanco();
		 String query = "SELECT *, "
						+ UtilRecTour.buildDistanceQuery(center.latitude, center.longitude)
						+ " FROM hotel WHERE _id = %s"  ;
		 
		 query = String.format(query, id);
		 
		return db.rawQuery(query, null);
		 
	 }
	 
	//***********************************Fim Hotel***********************************	 
	
	//**********************************Inicio Museu*********************************
	
	 public static void inserirMuseu(Museu museu ){
			try {
				SQLiteDatabase db = getBanco();
				ContentValues valoresIniciais = new ContentValues();

				valoresIniciais.put("rtID",            museu.getRtID()        );
				valoresIniciais.put("rtNome",          museu.getRtNome()      );
				valoresIniciais.put("rtDescricao",     museu.getRtDescricao() );
				valoresIniciais.put("rtBairro",        museu.getRtBairro()    );
				valoresIniciais.put("rtLogradouro",    museu.getRtLogradouro());
				valoresIniciais.put("rtTelefone",      museu.getRtTelefone()  );
				valoresIniciais.put("rtSite",          museu.getRtSite()      );
				valoresIniciais.put("rtLatitude",      museu.getLatitude()    );
				valoresIniciais.put("rtLongitude",     museu.getLongitude()   );
				   //	Setando os campos para o bd calcular a distancia	   
				valoresIniciais.put(CAMPO_TB_LATITUDE_SEN,     museu.getCAMPO_TB_LATITUDE_SEN() );
				valoresIniciais.put(CAMPO_TB_LATITUDE_COS,     museu.getCAMPO_TB_LATITUDE_COS() );
				valoresIniciais.put(CAMPO_TB_LONGITUDE_SEN,    museu.getCAMPO_TB_LONGITUDE_SEN() );
				valoresIniciais.put(CAMPO_TB_LONGITUDE_COS,    museu.getCAMPO_TB_LONGITUDE_COS() );

				db.insert("museu", null, valoresIniciais);
				db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
	 
	 
	 public static Cursor recuperarMuseusDistancia(LatLng center){
			
		 SQLiteDatabase db = getBanco();
		 String query = "SELECT *, "
						+ UtilRecTour.buildDistanceQuery(center.latitude, center.longitude)
						+ " FROM museu order by distancia DESC"  ;
		 
		return db.rawQuery(query, null);
		 
	 }
	 
	 
	 public static Cursor recuperarMuseusDistancia(LatLng center , String id ){
			
		 SQLiteDatabase db = getBanco();
		 String query = "SELECT *, "
						+ UtilRecTour.buildDistanceQuery(center.latitude, center.longitude)
						+ " FROM museu WHERE _id = %s"  ;
		 
		 query = String.format(query, id);
		 
		return db.rawQuery(query, null);
	 }
	 
	 public static void deleteAllMuseu(){
		  try {
		   SQLiteDatabase db = getBanco();
		   db.delete("museu", "", null);
		   db.close();

		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }
	 
	//*********************************Fim Museu************************************

	//**********************************Inicio Shopping****************************
	 
	 public static void inserirShopping(Shopping shopping ){
			try {
				SQLiteDatabase db = getBanco();
				ContentValues valoresIniciais = new ContentValues();

				valoresIniciais.put("rtID",                   shopping.getRtID()                  );
				valoresIniciais.put("rtNome",                 shopping.getRtNome()                );
				valoresIniciais.put("rtDescricao",            shopping.getRtDescricao()           );
				valoresIniciais.put("rtBairro",               shopping.getRtBairro()              );
				valoresIniciais.put("rtLogradouro",           shopping.getRtLogradouro()          );
				valoresIniciais.put("rtLatitude",             shopping.getLatitude()              );
				valoresIniciais.put("rtLongitude",            shopping.getLongitude()             );
				valoresIniciais.put("rtTelefone",             shopping.getRtTelefone()            );
				valoresIniciais.put("rtSite",                 shopping.getRtSite()                );
				valoresIniciais.put("rtFuncionamento",        shopping.getRtFuncionamento()       );
				valoresIniciais.put("rtFuncionamentoDomingo", shopping.getRtFuncionamentoDomingo());
				   //	Setando os campos para o bd calcular a distancia	   
				valoresIniciais.put(CAMPO_TB_LATITUDE_SEN,     shopping.getCAMPO_TB_LATITUDE_SEN());
				valoresIniciais.put(CAMPO_TB_LATITUDE_COS,     shopping.getCAMPO_TB_LATITUDE_COS());
				valoresIniciais.put(CAMPO_TB_LONGITUDE_SEN,    shopping.getCAMPO_TB_LONGITUDE_SEN());
				valoresIniciais.put(CAMPO_TB_LONGITUDE_COS,    shopping.getCAMPO_TB_LONGITUDE_COS());

				db.insert("shopping", null, valoresIniciais);
				db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
	 
	 
	 public static Cursor recuperarShoppingsDistancia(LatLng center){
			
		 SQLiteDatabase db = getBanco();
		 String query = "SELECT *, "
						+ UtilRecTour.buildDistanceQuery(center.latitude, center.longitude)
						+ " FROM shopping order by distancia DESC"  ;
		 
		return db.rawQuery(query, null);
		 
	 }
	 
	 public static Cursor recuperarShoppingsDistancia(LatLng center , String id ){
			
		 SQLiteDatabase db = getBanco();
		 String query = "SELECT *, "
						+ UtilRecTour.buildDistanceQuery(center.latitude, center.longitude)
						+ " FROM shopping WHERE _id = %s"  ;
		 
		 query = String.format(query, id);
		 
		return db.rawQuery(query, null);
	 }

	 public static void deleteAllShopping(){
		  try {
			  SQLiteDatabase db = getBanco();
			  db.delete("shopping", "", null);
			  db.close();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	 
	//*********************************Fim Shopping************************************

	//**********************************Inicio Teatro****************************
	 
	 public static void inserirTeatro(Teatro teatro ){
			try {
				SQLiteDatabase db = getBanco();
				ContentValues valoresIniciais = new ContentValues();

				valoresIniciais.put("rtID",                   teatro.getRtID()                  );
				valoresIniciais.put("rtNome",                 teatro.getRtNome()                );
				valoresIniciais.put("rtDescricao",            teatro.getRtDescricao()           );
				valoresIniciais.put("rtBairro",               teatro.getRtBairro()              );
				valoresIniciais.put("rtLogradouro",           teatro.getRtLogradouro()          );
				valoresIniciais.put("rtTelefone",             teatro.getRtTelefone()            );
				valoresIniciais.put("rtLatitude",             teatro.getLatitude()              );
				valoresIniciais.put("rtLongitude",            teatro.getLongitude()             );
				
				   //	Setando os campos para o bd calcular a distancia	   
				valoresIniciais.put(CAMPO_TB_LATITUDE_SEN,     teatro.getCAMPO_TB_LATITUDE_SEN());
				valoresIniciais.put(CAMPO_TB_LATITUDE_COS,     teatro.getCAMPO_TB_LATITUDE_COS());
				valoresIniciais.put(CAMPO_TB_LONGITUDE_SEN,    teatro.getCAMPO_TB_LONGITUDE_SEN());
				valoresIniciais.put(CAMPO_TB_LONGITUDE_COS,    teatro.getCAMPO_TB_LONGITUDE_COS());

				db.insert("teatro", null, valoresIniciais);
			db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
	 
	 public static Cursor recuperarTeatrosDistancia(LatLng center){
			
		 SQLiteDatabase db = getBanco();
		 String query = "SELECT *, "
						+ UtilRecTour.buildDistanceQuery(center.latitude, center.longitude)
						+ " FROM teatro order by distancia DESC"  ;
		 
		return db.rawQuery(query, null);
		 
	 }
	 
	 public static Cursor recuperarTeatrosDistancia(LatLng center , String id ){
			
		 SQLiteDatabase db = getBanco();
		 String query = "SELECT *, "
						+ UtilRecTour.buildDistanceQuery(center.latitude, center.longitude)
						+ " FROM teatro WHERE _id = %s"  ;
		 
		 query = String.format(query, id);
		 
		return db.rawQuery(query, null);
	 }

	 public static void deleteAllTeatro(){
		  try {
			  SQLiteDatabase db = getBanco();
			  db.delete("teatro", "", null);
			  db.close();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	 
	//*********************************Fim Teatro************************************
	 

	//**********************************Inicio Ponte****************************
	 
	 public static void inserirPonte(Ponte ponte ){
			try {
				
				SQLiteDatabase db = getBanco();
				ContentValues valoresIniciais = new ContentValues();

				valoresIniciais.put("rtID",                   ponte.getRtID()                  );
				valoresIniciais.put("rtNome",                 ponte.getRtNome()                );
				valoresIniciais.put("rtDescricao",            ponte.getRtDescricao()           );
				valoresIniciais.put("rtLatitude",             ponte.getLatitude()              );
				valoresIniciais.put("rtLongitude",            ponte.getLongitude()             );
				
				   //	Setando os campos para o bd calcular a distancia	   
				valoresIniciais.put(CAMPO_TB_LATITUDE_SEN,     ponte.getCAMPO_TB_LATITUDE_SEN());
				valoresIniciais.put(CAMPO_TB_LATITUDE_COS,     ponte.getCAMPO_TB_LATITUDE_COS());
				valoresIniciais.put(CAMPO_TB_LONGITUDE_SEN,    ponte.getCAMPO_TB_LONGITUDE_SEN());
				valoresIniciais.put(CAMPO_TB_LONGITUDE_COS,    ponte.getCAMPO_TB_LONGITUDE_COS());

				db.insert("ponte", null, valoresIniciais);
			db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
	 
	 public static Cursor recuperarPontesDistancia(LatLng center){
			
		 SQLiteDatabase db = getBanco();
		 String query = "SELECT *, "
						+ UtilRecTour.buildDistanceQuery(center.latitude, center.longitude)
						+ " FROM ponte order by distancia DESC"  ;
		 
		return db.rawQuery(query, null);
		 
	 }
	 
	 public static Cursor recuperarPontesDistancia(LatLng center , String id ){
			
		 SQLiteDatabase db = getBanco();
		 String query = "SELECT *, "
						+ UtilRecTour.buildDistanceQuery(center.latitude, center.longitude)
						+ " FROM ponte WHERE _id = %s"  ;
		 
		 query = String.format(query, id);
		 
		return db.rawQuery(query, null);
	 }

	 public static void deleteAllPonte(){
		  try {
			  SQLiteDatabase db = getBanco();
			  db.delete("ponte", "", null);
			  db.close();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	 
	//*********************************Fim Ponte************************************
	 
	 
	//***********************************Inicio Log Atualização***********************************
		
	 /*
	 public static void inserirAtualizacao(Atualizacao it) {
		 SQLiteDatabase db = getBanco();
		 ContentValues cv = new ContentValues();
		try {
				cv.put("rtTabela" , it.getNome_tabela());
				cv.put("rtAtualizar", it.getDeterminarAtualizacao());
				cv.put("rtDt_ultima_att", it.getDataUltimaAtualizacao());
				
				long i = db.insert("log_atualizacao", null, cv);
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	 */

	 
		public static void inseriLogAtualizacao(int id, String tabela, String logUsuario){
			try{
				
				Date   vData  = new Date();
				//String dt = (String) DateFormat.format( "dd/MM/yyyy hh:mm", vData);
				
				SQLiteDatabase db = getBanco();
				ContentValues valoresIniciais = new ContentValues();

				valoresIniciais.put("rtID",           id     );
				valoresIniciais.put("rtTabela",       tabela );
				valoresIniciais.put("rtAtualizar",    "S"    );
				valoresIniciais.put("rtDT_ultima_att",  vData.getTime()    );
				valoresIniciais.put("rtDescricao_Log_Usuario", logUsuario   );
				

				db.insert("log_atualizacao", null, valoresIniciais);
				db.close();
				
			}catch(Exception e){
				
			}
			
		}
	 
	 
	 public static void inserirAtualizacao(String it) {
		try {
			
			String sqlTexto = "UPDATE log_atualizacao SET rtAtualizar = 'N',  " 
					+ "WHERE rtTabela = '"  + it + "'  ";

			db.execSQL(sqlTexto);
			db.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
	 
	 public static void inserirAtualizacao( Atualizacao it) {
			try {
				SQLiteDatabase db = getBanco();
				
				String sqlTexto = String.format("UPDATE log_atualizacao SET " +
						"rtAtualizar = '%s', " +
						"rtDescricao_Log_Dev = '%s', " +
						"rtDescricao_Log_Usuario = '%s', " +
						"rtDt_ultima_att = '%s' " 
						+ "WHERE rtTabela = '%s'" , 
						it.getDeterminarAtualizacao(),
						it.getDescricaoLogDev(),
						it.getDescricaoLogUsuario(),
						it.getDataUltimaAtualizacao(),
						it.getNome_tabela());

				
				db.execSQL(sqlTexto);
				db.close();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	 
	
	 
	 
	public static Atualizacao recuperarItemAtualizacao(String campo) {
		SQLiteDatabase db = getBanco();
		String query = String.format("SELECT * FROM log_atualizacao WHERE rtTabela LIKE '%s'" , campo);

			Cursor c = db.rawQuery(query, null);

			Atualizacao at = new  Atualizacao();
			
			if (c.getCount() > 0) {
				while (c.moveToNext()) {
					
					at.setDeterminarAtualizacao(c.getString(c
							.getColumnIndex("rtAtualizar")));
					at.setDataUltimaAtualizacao(c.getString(c
							.getColumnIndex("rtDt_ultima_att")));
					at.setNome_tabela(c.getString(c
							.getColumnIndex("rtTabela")));
					at.setDescricaoLogDev(c.getString(c
							.getColumnIndex("rtDescricao_Log_Dev")));
					at.setDescricaoLogUsuario(c.getString(c
							.getColumnIndex("rtDescricao_Log_Usuario")));
					
				}
				
			}
			
			return at;
	}
	
	public static Cursor recuperarTabelasAtualizacao(){
		
		SQLiteDatabase db = getBanco();
		String query = "SELECT * FROM log_atualizacao" ;
			
		Cursor c = db.rawQuery(query, null);
			
		return c;
	}
	
	
	 //***********************************Fim Log Atualização***********************************	 
	 
	
}
