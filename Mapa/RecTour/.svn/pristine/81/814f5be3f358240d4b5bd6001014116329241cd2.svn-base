package mobi.rectour.recHoteis.entidades;

import java.io.Serializable;
import java.util.Iterator;

import mobi.rectour.util.UtilRecTour;

import org.json.JSONObject;


public class Hotel implements Serializable {

	private static final long serialVersionUID = 1L;

	int rtID;
	String rtNome;
	String rtEndereco;
	String rtTelefone;
	String rtSite;
	String rtImagem;
	double rtLatitude;
	double rtLongitude;
	

	// variaveis responsaveis pelo calculo de distancia
	private double CAMPO_TB_LATITUDE_SEN;
	private double CAMPO_TB_LATITUDE_COS;

	private double CAMPO_TB_LONGITUDE_SEN;
	private double CAMPO_TB_LONGITUDE_COS;
	
	
	
	
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
	
	
	public double getLatitude() {
		return rtLatitude;
	}
	
	public void setLatitude(double latitude) {
		this.rtLatitude = latitude;

		setCAMPO_TB_LATITUDE_SEN(Double.valueOf(latitude));
		setCAMPO_TB_LATITUDE_COS(Double.valueOf(latitude));
	}

	public double getLongitude() {
		return rtLongitude;
	}
	
	
	public void setLongitude(double longitude) {
		this.rtLongitude = longitude;
		setCAMPO_TB_LONGITUDE_SEN(Double.valueOf(longitude));
		setCAMPO_TB_LONGITUDE_COS(Double.valueOf(longitude));
	}
	
	//

	public Hotel() {
		super();

		this.rtID = 0;
		this.rtNome = "";
		this.rtEndereco = "";
		this.rtTelefone = "";
		this.rtSite = "";
		this.rtImagem = "";
		this.rtLatitude = 0.000000;
		this.rtLongitude = 0.000000;
	}

	public String getRtImagem() {
		return rtImagem;
	}

	public void setRtImagem(String rtImagem) {
		this.rtImagem = rtImagem;
	}

	public Hotel(JSONObject jsHoteis) {
		this();
		@SuppressWarnings("unchecked")
		Iterator<String> it = jsHoteis.keys();
		String keyReg = "";
		Object valReg = "";

		while (it.hasNext()) {
			try {

				keyReg = it.next();
				valReg = UtilRecTour.getObjectType(jsHoteis.get(keyReg)) ;
				
				if (keyReg.equalsIgnoreCase("_id")) {
					this.rtID = (Integer) valReg;

				} else if (keyReg.equalsIgnoreCase("nome")) {
					this.rtNome = (String) valReg;

				} else if (keyReg.equalsIgnoreCase("endereco")) {
					this.rtEndereco = (String) valReg;
				} else if (keyReg.equalsIgnoreCase("telefone")) {
					this.rtTelefone = (String) valReg;
				} else if (keyReg.equalsIgnoreCase("latitude")) {
					setLatitude((Double) valReg);
				} else if (keyReg.equalsIgnoreCase("site")) {
					this.rtSite = (String) valReg;
				} else if (keyReg.equalsIgnoreCase("longitude")) {
					setLongitude((Double) valReg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int getRtID() {
		return rtID;
	}

	public void setRtID(int rtID) {
		this.rtID = rtID;
	}

	public String getRtNome() {
		return rtNome;
	}

	public void setRtNome(String rtNome) {
		this.rtNome = rtNome;
	}

	public String getRtEndereco() {
		return rtEndereco;
	}

	public void setRtEndereco(String rtEndereco) {
		this.rtEndereco = rtEndereco;
	}

	public String getRtTelefone() {
		return rtTelefone;
	}

	public void setRtTelefone(String rtTelefone) {
		this.rtTelefone = rtTelefone;
	}

	public String getRtSite() {
		return rtSite;
	}

	public void setRtSite(String rtSite) {
		this.rtSite = rtSite;
	}

	

}
