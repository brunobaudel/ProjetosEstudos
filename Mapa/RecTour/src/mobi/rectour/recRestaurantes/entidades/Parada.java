package mobi.rectour.recRestaurantes.entidades;

import java.util.Iterator;

import mobi.rectour.util.UtilRecTour;

import org.json.JSONObject;

public class Parada {
	
	
	private long _id;
	private String codParada;	
	private String numeroParada	;
	private String descricao;
	
	private double latitude;
	private double longitude;
	
	// variaveis responsaveis pelo calculo de distancia
	private double CAMPO_TB_LATITUDE_SEN;
	private double CAMPO_TB_LATITUDE_COS;

	private double CAMPO_TB_LONGITUDE_SEN;
	private double CAMPO_TB_LONGITUDE_COS;
	//
	
	public Parada(){
		this._id   		 = -1 ;
		this.codParada   = "";
		this.numeroParada   	 = "";
		this.descricao   = "";
		this.latitude    = 0 ;
		this.longitude   = 0 ;
	}
	
	public Parada(JSONObject jsParada){
		this();
		Iterator<String> it = jsParada.keys();
		String keyReg = "";
		Object valReg = "";

		while (it.hasNext()) {
			try {

				keyReg = it.next();
				valReg = UtilRecTour.getObjectType(jsParada.get(keyReg));
				
				if (keyReg.equalsIgnoreCase("_id")) {
					this.codParada = (String) valReg;
				} else if (keyReg.equalsIgnoreCase("nome")) {
					this.numeroParada = (String)valReg;
				} else if (keyReg.equalsIgnoreCase("endereco")) {
					this.descricao = (String) valReg;
				} else if (keyReg.equalsIgnoreCase("latitude")) {
					setLatitude( (Double) valReg);
				} else if (keyReg.equalsIgnoreCase("longitude")) {
					setLongitude( (Double) valReg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getCodParada() {
		return codParada;
	}

	public void setCodParada(String codParada) {
		this.codParada = codParada;
	}

	public String getNumeroParada() {
		return numeroParada;
	}

	public void setNumeroParada(String numero) {
		this.numeroParada = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;

		setCAMPO_TB_LATITUDE_SEN(Double.valueOf(latitude));
		setCAMPO_TB_LATITUDE_COS(Double.valueOf(latitude));
	}

	public double getLongitude() {
		return longitude;
	}
	
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
		setCAMPO_TB_LONGITUDE_SEN(Double.valueOf(longitude));
		setCAMPO_TB_LONGITUDE_COS(Double.valueOf(longitude));

	}

	public double getCAMPO_TB_LATITUDE_SEN() {
		return CAMPO_TB_LATITUDE_SEN;
	}

	private void setCAMPO_TB_LATITUDE_SEN(double latitude) {
		CAMPO_TB_LATITUDE_SEN = Math.sin(UtilRecTour.deg2rad(latitude));
	}

	public double getCAMPO_TB_LATITUDE_COS() {
		return CAMPO_TB_LATITUDE_COS;
	}

	private void setCAMPO_TB_LATITUDE_COS(double latitude) {
		CAMPO_TB_LATITUDE_COS = Math.cos(UtilRecTour.deg2rad(latitude));
	}

	public double getCAMPO_TB_LONGITUDE_SEN() {
		return CAMPO_TB_LONGITUDE_SEN;
	}

	private void setCAMPO_TB_LONGITUDE_SEN(double longitude) {
		CAMPO_TB_LONGITUDE_SEN = Math.sin(UtilRecTour.deg2rad(longitude));
	}

	public double getCAMPO_TB_LONGITUDE_COS() {
		return CAMPO_TB_LONGITUDE_COS;
	}

	private void setCAMPO_TB_LONGITUDE_COS(double longitude) {
		CAMPO_TB_LONGITUDE_COS = Math.cos(UtilRecTour.deg2rad(longitude));
	}
	
	

}
