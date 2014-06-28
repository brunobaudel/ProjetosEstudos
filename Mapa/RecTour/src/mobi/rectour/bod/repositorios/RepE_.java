package mobi.rectour.bod.repositorios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mobi.rectour.bod.bd.CriarBD;
import mobi.rectour.bod.bd.IRepositorio;
import mobi.rectour.bod.bd.TabelasBOD;
import mobi.rectour.bod.entidades.P;
import mobi.rectour.bod.util.ConvertToLatLong;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class RepE_ implements IRepositorio<P, Iterator<P>, Cursor> {

	public static final int REPOSITORIO_PARADA = 001100;

	private CriarBD db;
	private Context c;

	public RepE_(Context c) {
		db = CriarBD.getBD(c);
		this.c = c;
	}
	
	public static double DistanciaCentral;

	@Override
	public long inserir(P it) {
		
		ContentValues cv = new ContentValues();
		cv.put(TabelasBOD.CAMPO_TB_BAIRRO, it.getBairro());
		cv.put(TabelasBOD.CAMPO_TB_CIDADE, it.getCidade());
		cv.put(TabelasBOD.CAMPO_TB_CODIGOPARADA, it.getCodigoParada());
		
		double lat = Double.valueOf("-" + it.getLatitude());
		double log = Double.valueOf("-" + it.getLongitude());
		
		cv.put(TabelasBOD.CAMPO_TB_LATITUDE, lat);
		cv.put(TabelasBOD.CAMPO_TB_LATITUDE_SEN, Math.sin(deg2rad(lat)));
		cv.put(TabelasBOD.CAMPO_TB_LATITUDE_COS, Math.cos(deg2rad(lat)));

		cv.put(TabelasBOD.CAMPO_TB_LONGITUDE, log);// "-" + it.getLongitude());
		cv.put(TabelasBOD.CAMPO_TB_LONGITUDE_SEN, Math.sin(deg2rad(log)));
		cv.put(TabelasBOD.CAMPO_TB_LONGITUDE_COS, Math.cos(deg2rad(log)));
		
		cv.put(TabelasBOD.CAMPO_TB_LOGRADOURO, it.getLogradouro());
		
		return db.inserir(TabelasBOD.TBL_Paradas, cv);
	}
	
	public static double deg2rad(double deg) {
	    return (deg * Math.PI / 180.0);
	}

	@Override
	public String recuperarJson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<P> recuperarRegistro() {
		return recTodos();
	}
	
	private Iterator<P> recTodos(){
		String query = "SELECT * FROM %s ";

		query = String.format(query, TabelasBOD.TBL_Paradas);

		Cursor c = db.execSql(query);

		List<P> listaParadas = new ArrayList<P>();

		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				P parada = new P();
				parada.setCodigoParada(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_CODIGOPARADA)));
				parada.setLogradouro(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LOGRADOURO)));
				parada.setCidade(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_CIDADE)));
				parada.setBairro(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_BAIRRO)));
				parada.setLatitude(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LATITUDE)));
				parada.setLongitude(c.getString(c.getColumnIndex(TabelasBOD.CAMPO_TB_LONGITUDE)));
				
				listaParadas.add(parada);			
			}
		}
		return listaParadas.iterator();
	}
	
	
	private Iterator<P> recWhere(){
		String query = "SELECT *   FROM %s WHERE distancia = 10000";

		query = String.format(query, TabelasBOD.TBL_Paradas);

		Cursor c = db.execSql(query);

		List<P> listaParadas = new ArrayList<P>();

		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				P parada = new P();
				parada.setCodigoParada(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_CODIGOPARADA)));
				parada.setLogradouro(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LOGRADOURO)));
				parada.setCidade(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_CIDADE)));
				parada.setBairro(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_BAIRRO)));
				parada.setLatitude(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LATITUDE)));
				parada.setLongitude(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LONGITUDE)));
				
				listaParadas.add(parada);			
			}
		}
		return listaParadas.iterator();
	}
	

	@Override
	public Cursor executarSql(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarItem(String codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public P recuperarItem(String codigo) {

		String query = "SELECT * FROM %s WHERE %s = %s";

		query = String.format(query, TabelasBOD.TBL_Paradas,
				TabelasBOD.CAMPO_TB_CODIGOPARADA, codigo);

		Cursor c = db.execSql(query);

		P parada = new P();

		if (c.getCount() > 0) {
			while (c.moveToNext()) {

				parada.setCodigoParada(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_CODIGOPARADA)));
				parada.setLogradouro(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LOGRADOURO)));
				parada.setCidade(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_CIDADE)));
				parada.setBairro(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_BAIRRO)));
				parada.setLatitude(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LATITUDE)));
				parada.setLongitude(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LONGITUDE)));

			}
		}

		return parada;
	}

	@Override
	public  void  inserirItemSQL(String comando) {

		db.execSqlInsert(comando);
	}
	
	
	
	
	
	

	@Override
	public Iterator<P> recuperarRegistro(Object o) {
		
		GeoPoint g = (GeoPoint) o;
		
		String query = "SELECT *,"+ buildDistanceQuery(g.getLatitudeE6()/ 1E6 , g.getLongitudeE6()/ 1E6 ) +" FROM %s WHERE distancia > " + DistanciaCentral;

		query = String.format(query, TabelasBOD.TBL_Paradas);

		Cursor c = db.execSql(query);

		List<P> listaParadas = new ArrayList<P>();

		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				P parada = new P();
				parada.setCodigoParada(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_CODIGOPARADA)));
				parada.setLogradouro(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LOGRADOURO)));
				parada.setCidade(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_CIDADE)));
				parada.setBairro(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_BAIRRO)));
				parada.setLatitude(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LATITUDE)));
				parada.setLongitude(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LONGITUDE)));
				double distancia = c.getDouble(c
						.getColumnIndex("distancia"));
				
				Double lat = Double.valueOf(parada.getLatitude())  * -1E6;
	  			Double log = Double.valueOf(parada.getLongitude()) * -1E6;
////				
				GeoPoint point = new GeoPoint(lat.intValue(), 
						                      log.intValue());
				
			
				
				//listaPontosParadas.addOverlay(oItemParada, this.c.getResources().getDrawable(R.drawable.mk_parada));
				
				
//				distancia = convertPartialDistanceToKm(distancia);
//				if(distancia < DistanciaCentral/1000)
					listaParadas.add(parada);			
			}
		}
		
		return listaParadas.iterator();
	}
	
	
	 
	
	public List<P> recuperarItemizedOverlay(LatLng centro , LatLng estremidadeMapa ) {
		
		List<P> lista = new ArrayList<P>();
		
		DistanciaCentral = ConvertToLatLong.getDistanciaCentroToOutherLatLng(centro , estremidadeMapa);
		
		String query = "SELECT *,"+ buildDistanceQuery(centro.latitude , centro.longitude ) +" FROM %s WHERE distancia > " + DistanciaCentral ;

		query = String.format(query, TabelasBOD.TBL_Paradas);

		Cursor c = db.execSql(query);
		
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				P parada = new P();
				parada.setCodigoParada(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_CODIGOPARADA)));
				parada.setLogradouro(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LOGRADOURO)));
				parada.setCidade(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_CIDADE)));
				parada.setBairro(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_BAIRRO)));
				parada.setLatitude(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LATITUDE)));
				parada.setLongitude(c.getString(c
						.getColumnIndex(TabelasBOD.CAMPO_TB_LONGITUDE)));
					
				lista.add(parada);
//               int marker = R.drawable.mk_parada;
//               oItemParada = new OverlayItemParada(point, parada);
//               listaPontosParadas.addOverlay(oItemParada, this.c.getResources().getDrawable(marker));
                    
			}
//			int marker = 0;
//			Set<String> chaves = mapP.keySet();  
//			for (String chave : chaves){
//				
//				 List<P> markerList = mapP.get(chave);
//				 if (markerList.size() > 1) {
//					 
//					    marker = R.drawable.agrupamento;
////	    				Double lat = Double.valueOf(markerList.get(0).getLatitude())  * -1E6;
////	    	  			Double log = Double.valueOf(markerList.get(0).getLongitude()) * -1E6;
//					    Collections.sort(markerList);
//	    				//GeoPoint point = proj.fromPixels( markerList.get(0).getP().x,markerList.get(0).getP().y); //Integer.valueOf( xy[0]), Integer.valueOf( xy[1]));  //new GeoPoint(lat.intValue(),log.intValue());
//	                    
//					    Double lat = Double.valueOf(markerList.get(0).getLatitude())  * -1E6;
//	    	  			Double log = Double.valueOf(markerList.get(0).getLongitude()) * -1E6;
//	    				
//	    				GeoPoint point = new GeoPoint(lat.intValue(),log.intValue());
//					    
//	    				if(point == null){
//	    					Log.d("PontoNulo", String.format("O ponto %s e nulo.",markerList.get(0).getCodigoParada()));
//	    				}
//	    				
//		    				OverlayItemJuncao oItemParada = new OverlayItemJuncao(point);
//		    			
//		    				listaPontosParadas.addOverlay(oItemParada, this.c.getResources().getDrawable(marker));
//	    				
//	                } else   {
//	                	
//	                	marker = R.drawable.mk_parada;
//	                	Double lat = Double.valueOf(markerList.get(0).getLatitude())  * -1E6;
//	    	  			Double log = Double.valueOf(markerList.get(0).getLongitude()) * -1E6;
//	    				
//	    				GeoPoint point = new GeoPoint(lat.intValue(), 
//	    						                      log.intValue());
//	                    
//	    				OverlayItemParada oItemParada = new OverlayItemParada(point, markerList.get(0));
//	    				
//	    				listaPontosParadas.addOverlay(oItemParada, this.c.getResources().getDrawable(marker));//R.drawable.mk_parada));
//	    				
//	                }
//			}
//			mapP.clear();

		}

		return lista;
	}
	
	public Cursor recuperarParadas(LatLng centro , LatLng estremidadeMapa ) {
		
		DistanciaCentral = ConvertToLatLong.getDistanciaCentroToOutherLatLng(centro , estremidadeMapa);
		
		String query = "SELECT *,"+ buildDistanceQuery(centro.latitude , centro.longitude ) +" FROM %s WHERE distancia > " + DistanciaCentral ;

		query = String.format(query, TabelasBOD.TBL_Paradas);

		Cursor c = db.execSql(query);
		
		return c;
		
	}
	
	
	
	
//	public ItemizedOverlayFrMapa joinItemizedOverlay(ItemizedOverlayFrMapa listaPontosParadas,MapView mapView) {
//		
//		
//		int marker = 0;
////			Set<String> chaves = mapP.keySet();  
//		
//		Projection proj = mapView.getProjection();
//		int densityX = 10;
//        int densityY = 10;
//        int binX;
//        int binY;
//        
//        List<OverlayItemOpenDocs> list = listaPontosParadas.getmOverlays();
//        Map<String, List<P>> mapP = new HashMap<String, List<P>>();
//        for (OverlayItemOpenDocs overlayItemOpenDocs : list) {
//			
//        	
//        	OverlayItemParada op = (OverlayItemParada) overlayItemOpenDocs;
//        	
//        	P parada = op.parada;
//
//    		Double lat = Double.valueOf(parada.getLatitude())  * -1E6;
//    		Double log = Double.valueOf(parada.getLongitude()) * -1E6;
//    		
//    		GeoPoint point = new GeoPoint(lat.intValue(),log.intValue());
//    		
//    		Point p = proj.toPixels(point, null);
//    		parada.setP(p);
//    		
//    		double fractionX = ((double)p.x / (double)mapView.getWidth());
//            binX =  (int) (Math.floor(densityX * fractionX));
//            
//            double fractionY = ((double)p.y / (double)mapView.getHeight());
//            binY =  (int) (Math.floor(densityY * fractionY));
//            
//            String key = binX + "," + binY;
//            
//	         if( mapP.containsKey(key)){
//	        	 mapP.get(key).add(parada);
//	        	 
//	      	}else{
//	      		List<P> listP = new ArrayList<P>();
//	      		listP.add(parada);
//	      		mapP.put(key, listP);
//	      	}
//        	
//		}
//        
//		Set<String> chaves = mapP.keySet();  
//		for (String chave : chaves){
//			
//			 List<P> markerList = mapP.get(chave);
//			 if (markerList.size() > 1) {
//				 	
//				    marker = R.drawable.agrupamento;
////    				Double lat = Double.valueOf(markerList.get(0).getLatitude())  * -1E6;
////    	  			Double log = Double.valueOf(markerList.get(0).getLongitude()) * -1E6;
//				    Collections.sort(markerList);
//    				GeoPoint point = proj.fromPixels( markerList.get(0).getP().x,markerList.get(0).getP().y); //Integer.valueOf( xy[0]), Integer.valueOf( xy[1]));  //new GeoPoint(lat.intValue(),log.intValue());
//                    
//    				oItemParada = new OverlayItemParada(point, markerList.get(0));
//    				
//    				listaPontosParadas.addOverlay(oItemParada, this.c.getResources().getDrawable(marker));
//                } else   {
//                	
//                	marker = R.drawable.mk_parada;
//                	Double lat = Double.valueOf(markerList.get(0).getLatitude())  * -1E6;
//    	  			Double log = Double.valueOf(markerList.get(0).getLongitude()) * -1E6;
//    				
//    				GeoPoint point = new GeoPoint(lat.intValue(), 
//    						                      log.intValue());
//                    
//    				oItemParada = new OverlayItemParada(point, markerList.get(0));
//                    
//    				listaPontosParadas.addOverlay(oItemParada, this.c.getResources().getDrawable(marker));//R.drawable.mk_parada));
//                }
//		}
			
			
			
			
//			for (String chave : chaves){
//				
//				 List<P> markerList = mapP.get(chave);
//				 if (markerList.size() > 1) {
//					 
//					 
//					 	
//					    marker = R.drawable.agrupamento;
////	    				Double lat = Double.valueOf(markerList.get(0).getLatitude())  * -1E6;
////	    	  			Double log = Double.valueOf(markerList.get(0).getLongitude()) * -1E6;
//					    Collections.sort(markerList);
//	    				GeoPoint point = proj.fromPixels( markerList.get(0).getP().x,markerList.get(0).getP().y); //Integer.valueOf( xy[0]), Integer.valueOf( xy[1]));  //new GeoPoint(lat.intValue(),log.intValue());
//	                    
//	    				if(point != null){
//		    				oItemParada = new OverlayItemParada(point, markerList.get(0));
//		    					
//		    				listaPontosParadas.addOverlay(oItemParada, this.c.getResources().getDrawable(marker));
//	    				}
//	    				
//	                } else   {
//	                	
//	                	marker = R.drawable.mk_parada;
//	                	Double lat = Double.valueOf(markerList.get(0).getLatitude())  * -1E6;
//	    	  			Double log = Double.valueOf(markerList.get(0).getLongitude()) * -1E6;
//	    				
//	    				GeoPoint point = new GeoPoint(lat.intValue(), 
//	    						                      log.intValue());
//	                    
//	    				
//	    				if(point != null){
//		    				oItemParada = new OverlayItemParada(point, markerList.get(0));
//		                    
//		    				
//		    				listaPontosParadas.addOverlay(oItemParada, this.c.getResources().getDrawable(marker));//R.drawable.mk_parada));
//	    				}
//	    				
//	    				
//	                }
//			}

		
//
//		return listaPontosParadas;
//	}
//	
	
	
	public static boolean isWithin(Point p, MapView mapView) {
        return (p.x > 0 & p.x < mapView.getWidth() & p.y > 0 & p.y < mapView
                .getHeight());
    }
	
	
	public static double convertPartialDistanceToKm(double result) {
	    return (Math.acos(result) * 6371);
	}
	
	
	public static String buildDistanceQuery(double latitude, double longitude) {
	    final double coslat = Math.cos(deg2rad(latitude));
	    final double sinlat = Math.sin(deg2rad(latitude));
	    final double coslng = Math.cos(deg2rad(longitude));
	    final double sinlng = Math.sin(deg2rad(longitude));
	    //@formatter:off
	    return "(" + coslat + "*" + TabelasBOD.CAMPO_TB_LATITUDE_COS 
	            + "*(" + TabelasBOD.CAMPO_TB_LONGITUDE_COS + "*" + coslng
	            + "+" + TabelasBOD.CAMPO_TB_LONGITUDE_SEN + "*" + sinlng
	            + ")+" + sinlat + "*" + TabelasBOD.CAMPO_TB_LATITUDE_SEN  
	            + ") as distancia";
	    //@formatter:on
	}
	

}
