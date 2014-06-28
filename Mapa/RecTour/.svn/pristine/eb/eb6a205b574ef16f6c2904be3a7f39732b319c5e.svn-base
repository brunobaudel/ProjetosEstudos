package mobi.rectour.traducao;

import org.json.JSONException;
import org.json.JSONObject;

import mobi.rectour.web.ConexaoHttpClient;
import mobi.rectour.web.ConexaoHttpGet;
import mobi.rectour.web.ConexaoHttpsPost;
import mobi.rectour.web.IConexaoWeb;
import android.os.AsyncTask;

public class TraducaoMicrosoft implements ITraducao{

	
	private String url = "http://api.microsofttranslator.com/V2/Http.svc/Translate?%s";
	
	private String appId 	   = "appId=Bearer%s";
	private String text  	   = "text=%s" ;
	private String to    	   = "to=en"  ;
	private String contentType = "contentType=text/html";
	
	IConexaoWeb iConexao;
	
	
	private static String token;
	
	@Override
	public String traduzir(String texto) {
		// TODO Auto-generated method stub
		
		if(token == null){
			
			getAcessTokenApiTradutor();
			
			return "";
		}
		
		text = String.format(text, texto);
		
		appId = String.format(appId, token );
		
		String parametros = String.format("%s&%s&%s&%s", appId,text,to,contentType);
		
		String uri = String.format(url, parametros);
		
		iConexao = new ConexaoHttpGet(uri);
		
		getTraducaoMicrosoft();
		
		return"";
		
	}
	
	private void getTraducaoMicrosoft() {
		
		new GetTraducao().execute();
	}

	private String getAcessTokenApiTradutor(){
		
		
		String DatamarketAccessUri = "https://datamarket.accesscontrol.windows.net/v2/OAuth2-13";
		
		iConexao = new ConexaoHttpClient(DatamarketAccessUri);//ConexaoHttpPost(DatamarketAccessUri, parametros);
		
		new GetAcessToken().execute();
		
		return "";
	}
	
	
	private class GetAcessToken extends AsyncTask<Void, Void, Void>{

		
		
		@Override
		protected Void doInBackground(Void... params) {
			
			token = iConexao.buscarServidor();
			
			try {
				JSONObject accessToken = new JSONObject(token);
				
				token = accessToken.getString("access_token");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			/*
			{
			    "token_type": "http://schemas.xmlsoap.org/ws/2009/11/swt-token-profile-1.0",
			    "access_token": "http%3a%2f%2fschemas.xmlsoap.org%2fws%2f2005%2f05%2fidentity%2fclaims%2fnameidentifier=TradutorTextosRecTour&http%3a%2f%2fschemas.microsoft.com%2faccesscontrolservice%2f2010%2f07%2fclaims%2fidentityprovider=https%3a%2f%2fdatamarket.accesscontrol.windows.net%2f&Audience=http%3a%2f%2fapi.microsofttranslator.com&ExpiresOn=1377960692&Issuer=https%3a%2f%2fdatamarket.accesscontrol.windows.net%2f&HMACSHA256=euTd7J6QKt0C8IGmqPTKLEKer7cURTA%2fAP4lGOCAshc%3d",
			    "expires_in": "599",
			    "scope": "http://api.microsofttranslator.com"
			}*/
			
			
			return null;
		}
		
		
		
		
	}
	
	
	private class GetTraducao extends AsyncTask<Void, Void, Void>{

		
		
		@Override
		protected Void doInBackground(Void... params) {
			
	
			
			String traducao = iConexao.buscarServidor();
			
			String aux = traducao;
			
		
			
			return null;
		}
		
		
		
		
	}
	
	

}
