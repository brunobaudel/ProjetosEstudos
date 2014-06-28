package mobi.rectour.mapa.funcoes.googleplaces.entidades;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mobi.rectour.util.UtilRecTour;

public class Photos {
	
	private String  photo_reference;
	private long height;
	private long width;
	
	public Photos(){
		this.photo_reference =  "";
		this.height = 0;
		this.width = 0;
	}
	
	
	public Photos(JSONArray JsFotos){
		this();
		
		try {
			//JSONArray jsRetorno = new JSONObject(JsFotos).getJSONArray("photos");
			
			JSONObject arrPrimeiraFoto  =  JsFotos.getJSONObject(0);   //getJSONArray("photos");
			
			Iterator<String> it = arrPrimeiraFoto.keys();
			String keyReg = "";
			Object valReg = "";
	
			while (it.hasNext()) {
				try {
	
					keyReg = it.next();
					valReg = UtilRecTour.getObjectType(arrPrimeiraFoto.get(keyReg)) ;
					
					if (keyReg.equalsIgnoreCase("photo_reference")) {
						this.photo_reference = (String) valReg;
					} else if (keyReg.equalsIgnoreCase("width")) {
						this.width = (Integer) valReg;
					}else if (keyReg.equalsIgnoreCase("height")) {
						this.height = (Integer) valReg;
					}
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		} catch (JSONException e) {
			
		}
		
	}
	
	
	public String getPhoto_reference() {
		return photo_reference;
	}
	public void setPhoto_reference(String photo_reference) {
		this.photo_reference = photo_reference;
	}
	public long getHeight() {
		return height;
	}
	public void setHeight(long height) {
		this.height = height;
	}
	public long getWidth() {
		return width;
	}
	public void setWidth(long width) {
		this.width = width;
	}
	
	
	
	
	
	
}
