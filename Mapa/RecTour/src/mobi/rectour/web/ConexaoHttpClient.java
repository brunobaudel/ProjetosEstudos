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


public class ConexaoHttpClient implements IConexaoWeb{

	private String url ;
	
	public ConexaoHttpClient(String url){
			this.url = url;
	}

	public String buscarServidor() {
		
		String singleLine = "";
		 HttpClient httpclient = new DefaultHttpClient();

		    // Prepare a request object
		   HttpPost httpget = new HttpPost(url);

		    // Execute the request
		    HttpResponse response;
		    
		    UrlEncodedFormEntity urlEntity  = null;
		    try {
		    	urlEntity = new  UrlEncodedFormEntity(getParam());
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	          
		    httpget.setEntity(urlEntity);
		    
		    
		    try {
		        response = httpclient.execute(httpget);
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
										  "\"MsgVersaoApp\":\"\"," +
										  "\"TituloMsgUsuario\":\"Atenção\"}}",erro);
	}

	@Override
	public InformacoesServidor verificarObjRespostaServidorRetorno(String informacoesSevidor) {
		JSONObject jsonObject ;
		JSONObject jsonInfoServidor = new JSONObject();
		try {
			jsonObject = new JSONObject(informacoesSevidor);
			jsonInfoServidor = jsonObject.getJSONObject("InformacaoServidor");
			
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}

		
		return new InformacoesServidor(jsonInfoServidor);
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
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
