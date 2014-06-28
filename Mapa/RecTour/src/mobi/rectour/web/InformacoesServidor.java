package mobi.rectour.web;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;


public class InformacoesServidor implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean falhaRequisicao;
	

	private String tituloMsgUsuario;
	private String msgUsuario;
	private String msgErroServer;
	private Object retorno;
	

	// construtor da classe
	public InformacoesServidor() {

		this.setFalhaRequisicao(false);
		this.tituloMsgUsuario = "";
		this.msgUsuario = "";
		this.msgErroServer = "";
	
	}

	// construtor da classe
	public InformacoesServidor(JSONObject j) {
		this();
		try {

			this.setFalhaRequisicao(j.getBoolean("Erro"));
			this.tituloMsgUsuario = j.getString("TituloMsgUsuario");
			this.msgUsuario = j.getString("MsgUsuario");
			this.msgErroServer = j.getString("MsgErroServer");
			

		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

	// construtor da classe
		public InformacoesServidor(String j) {
			this();
			JSONObject jsonObject  = null;
			try {
				jsonObject = new JSONObject(j);
				
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			
			try {

				this.setFalhaRequisicao(!jsonObject.getBoolean("success"));
				if(!isFalhaRequisicao()){//Requisicao Ok
					
					setMsgErroServer("ok");
					setMsgUsuario("ok");
					
				}else{
					setMsgErroServer("Falha no site da prefeitura.");
					setMsgUsuario("Ocorreu um erro na busca do site da prefeitura.Por favor tente atulizar os dados mais tarde.");
					
				}
				
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	
	

	public String getTituloMsgUsuario() {
		return tituloMsgUsuario;
	}

	public void setTituloMsgUsuario(String tituloMsgUsuario) {
		this.tituloMsgUsuario = tituloMsgUsuario;
	}

	public String getMsgUsuario() {
		return msgUsuario;
	}

	public void setMsgUsuario(String msgUsuario) {
		this.msgUsuario = msgUsuario;
	}

	public String getMsgErroServer() {
		return msgErroServer;
	}

	public void setMsgErroServer(String msgErroServer) {
		this.msgErroServer = msgErroServer;
	}

	public boolean isFalhaRequisicao() {
		return falhaRequisicao;
	}

	public void setFalhaRequisicao(boolean falhaRequisicao) {
		this.falhaRequisicao = falhaRequisicao;
	}

	public Object getRetorno() {
		return retorno;
	}

	public void setRetorno(Object retorno) {
		this.retorno = retorno;
	}

	

}
