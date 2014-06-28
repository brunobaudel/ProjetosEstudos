package mobi.rectour.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONException;
import org.json.JSONObject;


public class ConexaoHttpGet implements IConexaoWeb{

	private URL url ;
	
	private String urlParameters;
	
	public ConexaoHttpGet(String url){
		
		try {
			url = url.replaceAll(" ", "%20");
			this.url = new URL(url);

		} catch (MalformedURLException e) {

		}
		
	}
	
	// always verify the host - dont check for certificate
		final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		/**
		 * Trust every server - dont check for any certificate
		 */
		private static void trustAllHosts() {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[] {};
				}

				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}
			} };

			// Install the all-trusting trust manager
			try {
				SSLContext sc = SSLContext.getInstance("TLS");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection
						.setDefaultSSLSocketFactory(sc.getSocketFactory());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public String buscarServidor() {
		
		//String urlParameters = "/";

		HttpURLConnection http = null;

		String singleLine = "";
		try {
			if (url.getProtocol().toLowerCase().equals("https")) {
				//trustAllHosts();  
				HttpsURLConnection https = (HttpsURLConnection) url
						.openConnection();

				https.setDoOutput(true);
				https.setDoInput(true);
				https.setRequestMethod("GET");
				//https.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
				
				//https.setRequestProperty("HTTP_USER_AGENT", "Teste");

				//https.setRequestProperty("Content-Length",
				//		"" + Integer.toString(urlParameters.getBytes().length));
				//https.setUseCaches(false);
				/*
				https.setRequestProperty("client_id", "TradutorTextosRecTour");
				https.setRequestProperty("client_secret", "9yjD7eGbpaHJuC2rPyXNPCEwr8sOcnSut9g3bl7BWOo");
				https.setRequestProperty("scope", "http://api.microsofttranslator.com");
				https.setRequestProperty("grant_type", "client_credentials");
				*/
				
				
				//https.setHostnameVerifier(DO_NOT_VERIFY);
				http = https;

			} else {
				
				http = (HttpURLConnection) url.openConnection();
				http.setDoOutput(true);
				http.setDoInput(true);
				http.setRequestMethod("GET");
			}

			//DataOutputStream wr = new DataOutputStream(http.getOutputStream());
			//wr.writeBytes(urlParameters);
			//wr.flush();

			StringBuilder content = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader( http.getInputStream(),"UTF-8"));

			while ((singleLine = reader.readLine()) != null)
				content.append(singleLine);

			reader.close();
			singleLine = content.toString();

		} catch (Exception e) {
			singleLine = getErro(e.getMessage());
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

	@Override
	public void salvarFotoServer() {
		// TODO Auto-generated method stub
		
	}
	
}
