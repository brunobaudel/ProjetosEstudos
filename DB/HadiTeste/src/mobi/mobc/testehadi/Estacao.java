package mobi.mobc.testehadi;

import java.util.Iterator;

import org.json.JSONObject;

import com.the9tcat.hadi.annotation.Column;
import com.the9tcat.hadi.annotation.Table;



@Table(name="Estacao")
public class Estacao {

	
	@Column(name="id", primary=true, autoincrement=true)
	private long id;
	
	
	@Column(name="IdEstacao")
	private String IdEstacao;
	@Column(name="Nome")
	private String Nome;
	private String Endereco;
	private String Referencia;
	private String CodTipo;
	private String CodArea;
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getIdEstacao() {
		return IdEstacao;
	}


	public void setIdEstacao(String idEstacao) {
		IdEstacao = idEstacao;
	}


	public String getNome() {
		return Nome;
	}


	public void setNome(String nome) {
		Nome = nome;
	}


	public String getEndereco() {
		return Endereco;
	}


	public void setEndereco(String endereco) {
		Endereco = endereco;
	}


	public String getReferencia() {
		return Referencia;
	}


	public void setReferencia(String referencia) {
		Referencia = referencia;
	}


	public String getCodTipo() {
		return CodTipo;
	}


	public void setCodTipo(String codTipo) {
		CodTipo = codTipo;
	}


	public String getCodArea() {
		return CodArea;
	}


	public void setCodArea(String codArea) {
		CodArea = codArea;
	}


	public String getStatusOnline() {
		return StatusOnline;
	}


	public void setStatusOnline(String statusOnline) {
		StatusOnline = statusOnline;
	}


	public String getStatusOperacao() {
		return StatusOperacao;
	}


	public void setStatusOperacao(String statusOperacao) {
		StatusOperacao = statusOperacao;
	}


	public String getQtdBicicletas() {
		return QtdBicicletas;
	}


	public void setQtdBicicletas(String qtdBicicletas) {
		QtdBicicletas = qtdBicicletas;
	}


	public String getQtdPosicaoLivre() {
		return QtdPosicaoLivre;
	}


	public void setQtdPosicaoLivre(String qtdPosicaoLivre) {
		QtdPosicaoLivre = qtdPosicaoLivre;
	}


	public String getDistanceKm() {
		return distanceKm;
	}


	public void setDistanceKm(String distanceKm) {
		this.distanceKm = distanceKm;
	}


	private String StatusOnline;
	private String StatusOperacao;
	private String QtdBicicletas;
	private String QtdPosicaoLivre;
	private String distanceKm;
	
	
	// variaveis responsaveis pelo calculo de distancia
	private double latitude;
	private double longitude;
	private double CAMPO_TB_LATITUDE_SEN;
	private double CAMPO_TB_LATITUDE_COS;

	private double CAMPO_TB_LONGITUDE_SEN;
	private double CAMPO_TB_LONGITUDE_COS;
	//
	
	
	public Estacao(){
		 id = 1;
		 IdEstacao = "";
		 Nome = "";
		 Endereco = "";
		 latitude = 0;
		 longitude = 0;
		 Referencia = "";
		 CodTipo = "";
		 CodArea = "";
		 StatusOnline = "";
		 StatusOperacao = "";
		 QtdBicicletas = "";
		 QtdPosicaoLivre = "";
		 distanceKm = "";
	}
	
	
	public Estacao(JSONObject jsEstacoes){
		this();
		Iterator<String> it = jsEstacoes.keys();
		String keyReg = "";
		Object valReg = "";

		while (it.hasNext()) {
			try {

				keyReg = it.next();
				valReg = getType(jsEstacoes.get(keyReg));
				
				if (keyReg.equalsIgnoreCase("IdEstacao")) {
					this.IdEstacao = String.valueOf( valReg);
				
				} else if (keyReg.equalsIgnoreCase("Nome")) {
					this.Nome = (String)valReg;
				
				} else if (keyReg.equalsIgnoreCase("Endereco")) {
					this.Endereco = (String) valReg;
				
				}  else if (keyReg.equalsIgnoreCase("Latitude")) {
					setLatitude( (Double.valueOf(String.valueOf(valReg).trim())));
				
				} else if (keyReg.equalsIgnoreCase("Longitude")) {
					setLongitude( (Double.valueOf(String.valueOf(valReg).trim())));
				
				}  else if (keyReg.equalsIgnoreCase("Referencia")) {
					this.Referencia = (String) valReg;
				
				}  else if (keyReg.equalsIgnoreCase("CodTipo")) {
					this.CodTipo = (String) valReg;
				
				}  else if (keyReg.equalsIgnoreCase("CodArea")) {
					this.CodArea = (String) valReg;
				
				}  else if (keyReg.equalsIgnoreCase("StatusOnline")) {
					this.StatusOnline = (String) valReg;
				
				}  else if (keyReg.equalsIgnoreCase("StatusOperacao")) {
					this.StatusOperacao = (String) valReg;
				
				}  else if (keyReg.equalsIgnoreCase("QtdBicicletas")) {
					this.QtdBicicletas = (String) valReg;
				
				} else if (keyReg.equalsIgnoreCase("QtdPosicaoLivre")) {
					this.QtdPosicaoLivre = (String) valReg;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		CAMPO_TB_LATITUDE_SEN = Math.sin(deg2rad(latitude));
	}

	public double getCAMPO_TB_LATITUDE_COS() {
		return CAMPO_TB_LATITUDE_COS;
	}

	private void setCAMPO_TB_LATITUDE_COS(double latitude) {
		CAMPO_TB_LATITUDE_COS = Math.cos(deg2rad(latitude));
	}

	public double getCAMPO_TB_LONGITUDE_SEN() {
		return CAMPO_TB_LONGITUDE_SEN;
	}

	private void setCAMPO_TB_LONGITUDE_SEN(double longitude) {
		CAMPO_TB_LONGITUDE_SEN = Math.sin(deg2rad(longitude));
	}

	public double getCAMPO_TB_LONGITUDE_COS() {
		return CAMPO_TB_LONGITUDE_COS;
	}

	private void setCAMPO_TB_LONGITUDE_COS(double longitude) {
		CAMPO_TB_LONGITUDE_COS = Math.cos(deg2rad(longitude));
	}
	
	
	public static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	
	public static Object getType(Object obj){
		
		
		if(obj instanceof String){
			obj = (String) obj;
		}else if( obj instanceof Double){
			
			obj = (Double) obj;
		}else if( obj instanceof Integer){
			obj = (Integer) obj;
		}
		
		return obj;
	}
	
}
