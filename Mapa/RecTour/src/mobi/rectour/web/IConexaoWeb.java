package mobi.rectour.web;

public interface IConexaoWeb {
	
	String buscarServidor();
	String getErro(String erro);
	InformacoesServidor verificarObjRespostaServidorRetorno(String informacoesSevidor);
	void salvarFotoServer();
	
}
