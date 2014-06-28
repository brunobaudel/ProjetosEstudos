package com.mobc.zaerecifeutimate.web;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class InformacoesServidor implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private boolean statusRequisicao;
	private String tituloMsgUsuario;
	private String msgUsuario;
	private String msgErroServer;
	private String versaoApp;

	// construtor da classe
	public InformacoesServidor() {

		this.statusRequisicao = false;
		this.tituloMsgUsuario = "";
		this.msgUsuario = "";
		this.msgErroServer = "";
		this.versaoApp = "";

	}

	// construtor da classe
	public InformacoesServidor(JSONObject j) {

		try {

			this.statusRequisicao = j.getBoolean("Erro");
			this.tituloMsgUsuario = j.getString("TituloMsgUsuario");
			this.msgUsuario = j.getString("MsgUsuario");
			this.msgErroServer = j.getString("MsgErroServer");
			this.versaoApp = j.getString("VersaoApp");

		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

	public boolean getStatusRequisicao() {
		return statusRequisicao;
	}

	public void setStatusRequisicao(boolean statusRequisicao) {
		this.statusRequisicao = statusRequisicao;
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

	public String getVersaoApp() {
		return versaoApp;
	}

	public void setVersaoApp(String versaoApp) {
		this.versaoApp = versaoApp;
	}

}
