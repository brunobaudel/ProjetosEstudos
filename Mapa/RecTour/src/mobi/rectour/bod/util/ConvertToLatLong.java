package mobi.rectour.bod.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class ConvertToLatLong {
	
	
    private int cellID, lac;
    
	public ConvertToLatLong(int cellid, int lac)  {
		this.cellID = cellid;
		this.lac = lac;
	}
	 
	public double[] getLatLong() {	
		String url = "http://www.google.com/glm/mmap";
		double lat = 0;
		double lng = 0;
	    try{
			
			HttpURLConnection conWS = (HttpURLConnection) (new URL(url)).openConnection();
			
			conWS.setDoInput(true);
			conWS.setDoOutput(true);
			
			conWS.setRequestMethod("POST");
		
			MyRequestEntity mre = new MyRequestEntity(cellID, lac);
			
			mre.writeRequest(conWS.getOutputStream());
			
			DataInputStream dis = new DataInputStream(conWS.getInputStream());
			dis.readShort();
		    dis.readByte();
		    int code = dis.readInt();
		    if (code == 0) {
		        lat = (double) dis.readInt() / 1000000D;
		        lng = (double) dis.readInt() / 1000000D;
		        dis.readInt();
		        dis.readInt();
		        dis.readUTF();
		    }
			//result = toStringSW(conWS.getInputStream());
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		double[] x= {lat,lng};
	    return x ;
	}

	class MyRequestEntity 
	{
	    int cellId, lac;

	    public MyRequestEntity(int cellId, int lac) {
	        this.cellId = cellId;
	        this.lac = lac;
	    }

	    public void writeRequest(OutputStream outputStream) throws
			IOException {
	        DataOutputStream os = new DataOutputStream(outputStream);
	        os.writeShort(21);
	        os.writeLong(0);
	        os.writeUTF("br");
	        os.writeUTF("Android");
	        os.writeUTF("1.3.1");
	        os.writeUTF("Web");
	        os.writeByte(27);

	        os.writeInt(0);
	        os.writeInt(0);
	        os.writeInt(3);
	        os.writeUTF("");
	        os.writeInt(cellId);  // CELL-ID
	        os.writeInt(lac);     // LAC
	        os.writeInt(0);
	        os.writeInt(0);
	        os.writeInt(0);
	        os.writeInt(0);
	        os.flush();
	    }
	}
	
	
	public static Double convert(String graus){
		
		
		graus = graus.replace("-","");
		
		
		List<String> grausMin = new ArrayList<String>(Arrays.asList(graus.split(" ")));
		
		List<Double> doubles = new ArrayList<Double>();
		Double retorno = 1.0;
		try{
			for (String string : grausMin) {
				
				if(TextUtils.isEmpty(string)){ continue; }
				doubles.add(Double.valueOf(string));
				
			}
			Double	decimal = (((doubles.get(1) * 60)+doubles.get(2)) / (60*60));
			retorno = doubles.get(0) + decimal;
		}catch (Exception e) {
			 
			 try{
			 retorno = Double.valueOf(graus.replace("\"", ""));
			 }catch (Exception e2) {
				 retorno = 1.0;
				 Log.d("Erro no ponto ",  graus);
			}
		}
		
		return retorno  ;
		
	}
	
	
	public static String convertSTR(String graus){
		
		
		graus = graus.replace("-","");
		
		
		List<String> grausMin = new ArrayList<String>(Arrays.asList(graus.split(" ")));
		
		List<Double> doubles = new ArrayList<Double>();
		Double retorno = 1.0;
		try{
			for (String string : grausMin) {
				
				if(TextUtils.isEmpty(string)){ continue; }
				doubles.add(Double.valueOf(string));
				
			}
			Double	decimal = (((doubles.get(1) * 60)+doubles.get(2)) / (60*60));
			retorno = doubles.get(0) + decimal;
		}catch (Exception e) {
			 
			 try{
			 retorno = Double.valueOf(graus.replace("\"", ""));
			 }catch (Exception e2) {
				 retorno = 1.0;
				 Log.d("Erro no ponto ",  graus);
			}
		}
		
		return String.valueOf(retorno)  ;
		
	}
	
	//Pega a distancia partial entre os pontos para fazer a comparacação na hora da query
	public static double getDistanciaCentroToTopLeftMap(MapView mapView){
		
		GeoPoint center  = mapView.getMapCenter();
		GeoPoint topLeft = mapView.getProjection().fromPixels(0, 0);
		
		final double coslatCenter = Math.cos(deg2rad(center.getLatitudeE6()/ 1E6));
		final double sinlatCenter = Math.sin(deg2rad(center.getLatitudeE6()/ 1E6));
		final double coslngCenter = Math.cos(deg2rad(center.getLongitudeE6()/ 1E6));
		final double sinlngCenter = Math.sin(deg2rad(center.getLongitudeE6()/ 1E6));
		
		final double coslatTopLeft = Math.cos(deg2rad(topLeft .getLatitudeE6()/ 1E6));
		final double sinlatTopLeft = Math.sin(deg2rad(topLeft .getLatitudeE6())/ 1E6);
		final double coslngTopLeft = Math.cos(deg2rad(topLeft .getLongitudeE6()/ 1E6));
		final double sinlngTopLeft = Math.sin(deg2rad(topLeft .getLongitudeE6()/ 1E6));
		  
		//@formatter:off
		double t =  (double) (coslatTopLeft*coslatCenter*(coslngCenter * coslngTopLeft + sinlngCenter * sinlngTopLeft) + sinlatTopLeft * sinlatCenter);
		return t; 
		//@formatter:on
	}
	
	
	//Pega a distancia partial entre os pontos para fazer a comparacação na hora da query
	public static double getDistanciaCentroToOutherLatLng(LatLng center , LatLng distanciaDesejada){
			
			//GeoPoint center  = mapView.getMapCenter();
			//GeoPoint topLeft = mapView.getProjection().fromPixels(0, 0);
			
			final double coslatCenter = Math.cos(deg2rad(center.latitude));//getLatitudeE6()/ 1E6));
			final double sinlatCenter = Math.sin(deg2rad(center.latitude));//getLatitudeE6()/ 1E6));
			final double coslngCenter = Math.cos(deg2rad(center.longitude));//getLongitudeE6()/ 1E6));
			final double sinlngCenter = Math.sin(deg2rad(center.longitude));//getLongitudeE6()/ 1E6));
			
			final double coslatTopLeft = Math.cos(deg2rad(distanciaDesejada .latitude));
			final double sinlatTopLeft = Math.sin(deg2rad(distanciaDesejada .latitude));
			final double coslngTopLeft = Math.cos(deg2rad(distanciaDesejada .longitude));
			final double sinlngTopLeft = Math.sin(deg2rad(distanciaDesejada .longitude));
			  
			//@formatter:off
			double t =  (double) (coslatTopLeft*coslatCenter*(coslngCenter * coslngTopLeft + sinlngCenter * sinlngTopLeft) + sinlatTopLeft * sinlatCenter);
			return t; 
			//@formatter:on
		}
	
	//Transforma para a distancia em Km
	public static double deg2rad(double deg) {
	    return (deg * Math.PI / 180.0);
	}
	
	
	
}
