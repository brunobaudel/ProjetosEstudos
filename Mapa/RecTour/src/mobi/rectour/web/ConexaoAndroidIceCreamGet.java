package mobi.rectour.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import mobi.rectour.geral.RecTourGeral;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class ConexaoAndroidIceCreamGet implements IConexaoWeb{

	private String url ;
	
	public ConexaoAndroidIceCreamGet(String url){
			this.url = url;
	}

	public String buscarServidor() {
		
		String singleLine = "";
		    
		try {
		     	HttpClient httpclient = new DefaultHttpClient();
		    	HttpResponse response = httpclient.execute(new HttpGet(url));
		       
		        BufferedReader reader = new BufferedReader(new InputStreamReader( response.getEntity().getContent(),"UTF-8"));
		        StringBuilder content = new StringBuilder();
		        while ((singleLine = reader.readLine()) != null)
					content.append(singleLine);
	
				reader.close();
				singleLine = content.toString();
		     } catch (Exception e) {
		    	
		    	e.printStackTrace();
		    	singleLine = getErro(e.getMessage().replaceAll("\"", ""));
		    }
		
		return singleLine;
		
	}

	@Override
	public String getErro(String erro) {
		
		return String.format("{\"InformacaoServidor\": {\"Erro\": true," +
										  "\"MsgErroServer\": \"%s\"," +
										  "\"MsgUsuario\": \"Erro ao tentar atualizar a tabela.\nProblenas na conex�o com a internet.\"," +
										  "\"MsgVersaoApp\":\"\"," +
										  "\"TituloMsgUsuario\":\"Aten��o\"}}",erro);
	}

	@Override
	public InformacoesServidor verificarObjRespostaServidorRetorno(String informacoesSevidor) {
		JSONObject jsonObject ;
		JSONObject jsonInfoServidor = new JSONObject();
		
		
		
		InformacoesServidor is = new InformacoesServidor();
		
		try {
			jsonObject = new JSONObject(informacoesSevidor);
			//Se der erro e pq a conexao n falhou pois o obj retornado foi o do prorio programa
			jsonInfoServidor = jsonObject.getJSONObject("InformacaoServidor");
			
			is = new InformacoesServidor(jsonInfoServidor);
			
		} catch (JSONException e) {
			
			is = new InformacoesServidor(informacoesSevidor);
			
			
			e.printStackTrace();
		}

		
		return is ;
	}
	
	
	private List<NameValuePair> getParam(){
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        
		
		nameValuePairs.add(new BasicNameValuePair("grant_type","client_credentials"));
		
		nameValuePairs.add(new BasicNameValuePair("client_id","TradutorTextosRecTour"));
		
		nameValuePairs.add(new BasicNameValuePair("client_secret","9yjD7eGbpaHJuC2rPyXNPCEwr8sOcnSut9g3bl7BWOo="));
		
		nameValuePairs.add(new BasicNameValuePair("scope","http://api.microsofttranslator.com"));
	
		
		return nameValuePairs;
	}

	@Override
	public void salvarFotoServer() {
		String singleLine = "";
	    
		try {
		     	HttpClient httpclient = new DefaultHttpClient();
		    	HttpResponse response = httpclient.execute(new HttpGet(url));
		       
		       // BufferedReader reader = new BufferedReader(new InputStreamReader( response.getEntity().getContent(),"UTF-8"));
		    	InputStream is = response.getEntity().getContent();
		    	
		    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		    	StringBuilder content = new StringBuilder();
		        
		    	while ((singleLine = reader.readLine()) != null)
						content.append(singleLine);
		    	 
		    	 File f = new File(RecTourGeral.getDiretorioRecTour() + "/teste.jpg");
		    	 
		    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		    	 writer.write(content.toString());
		    	 writer.close();
		    	

		    	
			} catch (Exception e) {
		    	
		    	e.printStackTrace();
		    	singleLine = getErro(e.getMessage().replaceAll("\"", ""));
		    }
		
	}

}
