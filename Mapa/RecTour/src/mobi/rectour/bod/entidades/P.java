package mobi.rectour.bod.entidades;

import java.io.Serializable;

import mobi.rectour.bod.util.ConvertToLatLong;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;


public class P  implements Comparable<P>,Serializable {
	
	
	private String codigoParada ;
	private String logradouro   ;
	private String cidade       ;
	private String bairro       ;
	private String latitude     ;
	private String longitude    ;
	private GeoPoint latLong    ;
	private int distancia       ; 
	private  Point p ;
	public P() {}

	public P(String codigoParada, String logradouro, String cidade,
			String bairro, String latitude, String longitude) {
		super();
		this.codigoParada = codigoParada;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.bairro = bairro;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getCodigoParada() {
		return codigoParada;
	}
	public void setCodigoParada(String codigoParada) {
		this.codigoParada = codigoParada;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLatitude() {
		return ConvertToLatLong.convertSTR(latitude);
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {///////
		return ConvertToLatLong.convertSTR(longitude);
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	@Override
	public int compareTo(P another) {
		if (this.distancia < another.distancia) {
            return -1;
        }
        if (this.distancia > another.distancia) {
            return 1;
        }
        return 0;
		
	}

	
	
	
	
	
}
