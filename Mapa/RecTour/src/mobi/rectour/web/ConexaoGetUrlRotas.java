package mobi.rectour.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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


public class ConexaoGetUrlRotas implements IConexaoWeb{

	private URL url ;
	
	public ConexaoGetUrlRotas(String url){
		
		try {
			url = url.replaceAll(" ", "%20");
			this.url = new URL(url);

		} catch (MalformedURLException e) {

		}
		
	}
	
	
	public String buscarServidor() {
		
		

		HttpURLConnection http = null;

		String singleLine = "";
		try {
		
			http = (HttpURLConnection) url.openConnection();
			
		
			
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
		
		return "colocar o json de erro";
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
