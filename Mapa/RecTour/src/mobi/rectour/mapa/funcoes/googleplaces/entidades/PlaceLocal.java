package mobi.rectour.mapa.funcoes.googleplaces.entidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mobi.rectour.util.UtilRecTour;

import org.json.JSONArray;
import org.json.JSONObject;

import com.parse.ParseObject;

public class PlaceLocal {

	private String id;
	private String nomeLocal;
	private String referencia;
	private List<Photos> listPhotos ;
	
	public PlaceLocal(){
		
		this.id = "";
		this.nomeLocal = "";
		this.referencia = "";
		this.listPhotos = new ArrayList<Photos>();
		
	}
	
	public PlaceLocal(ParseObject po){
		this();
		this.id 		= po.getString("id_Google");
		this.nomeLocal  =  po.getString("name");
		this.referencia =  po.getString("reference");
		Photos ph = new Photos();
		ph.setPhoto_reference(po.getString("google_photo_reference"));
		this.listPhotos.add(ph);
		
	}
	
	
	public PlaceLocal(JSONObject ObjPlace){
		this();
		Iterator<String> it = ObjPlace.keys();
		String keyReg = "";
		Object valReg = "";

		while (it.hasNext()) {
			try {

				keyReg = it.next();
				valReg = UtilRecTour.getObjectType(ObjPlace.get(keyReg)) ;
				
				if (keyReg.equalsIgnoreCase("id")) {
					this.id = (String) valReg;
				} else if (keyReg.equalsIgnoreCase("name")) {
					this.nomeLocal = (String) valReg;
				}else if(keyReg.equalsIgnoreCase("reference")){
					this.referencia = (String) valReg;
				}else if(keyReg.equalsIgnoreCase("photos")){
					listPhotos.add( new Photos((JSONArray) valReg));
				}
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomeLocal() {
		return nomeLocal;
	}
	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public List<Photos> getListPhotos() {
		return listPhotos;
	}
	public void setListPhotos(List<Photos> listPhotos) {
		this.listPhotos = listPhotos;
	}
	
	
	
	
	
	
}
