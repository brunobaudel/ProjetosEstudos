package mobi.rectour.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ConexaoAndroidIceCreamPost implements IConexaoWeb{

	private String url ;
	private List<NameValuePair> parametrros;
	
	
	
	public ConexaoAndroidIceCreamPost(String url){
			this.url = url;
	}
	

	public String buscarServidor() {
		
		String singleLine = "";
		 HttpClient httpclient = new DefaultHttpClient();

		    // Prepare a request object
		   HttpPost httpPost = new HttpPost(url);

		    // Execute the request
		    HttpResponse response;
		    
		    if(parametrros != null){
			    UrlEncodedFormEntity urlEntity  = null;
			    try {
			    	urlEntity = new  UrlEncodedFormEntity(getParam());
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          
			    httpPost.setEntity(urlEntity);
		    }
		    
		    try {
		        response = httpclient.execute(httpPost);
		        Log.i("Praeda",response.getStatusLine().toString());

		        HttpEntity entity = response.getEntity();
		       
		        if (entity != null) {
    
		            BufferedReader reader = new BufferedReader(new InputStreamReader( entity.getContent(),"UTF-8"));
		            StringBuilder content = new StringBuilder();
		            while ((singleLine = reader.readLine()) != null)
						content.append(singleLine);

					reader.close();
					singleLine = content.toString();
		            
		        }


		    } catch (Exception e) {
		    	
		    	e.printStackTrace();
		    	
		    }
		
		return singleLine;
		
	}

	@Override
	public String getErro(String erro) {
		
		return String.format("{\"InformacaoServidor\": {\"Erro\": true," +
										  "\"MsgErroServer\": \"%s\"," +
										  "\"MsgUsuario\": \"Erro ao tentar conectar\"," +
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


	public List<NameValuePair> getParametrros() {
		return parametrros;
	}


	public void setParametrros(List<NameValuePair> parametrros) {
		this.parametrros = parametrros;
	}


	@Override
	public void salvarFotoServer() {
		// TODO Auto-generated method stub
		
	}

}
