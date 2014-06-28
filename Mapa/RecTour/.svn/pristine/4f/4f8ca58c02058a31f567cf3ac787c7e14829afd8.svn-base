package mobi.rectour.web;

public class FactoryConexaoWeb {
	
	
	public static final int REQ_GET  = 002;
	public static final int REQ_POST = 003;
	
	public static IConexaoWeb getConexao( String url , int tipoRequest ){
		
		
		
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		
		IConexaoWeb iConnWeb = null;
		
		switch (currentapiVersion) {
		
		
		case android.os.Build.VERSION_CODES.BASE:
		case android.os.Build.VERSION_CODES.BASE_1_1:
		case android.os.Build.VERSION_CODES.CUPCAKE:
		case android.os.Build.VERSION_CODES.DONUT:
		case android.os.Build.VERSION_CODES.ECLAIR:
		case android.os.Build.VERSION_CODES.ECLAIR_0_1:
		case android.os.Build.VERSION_CODES.ECLAIR_MR1:
		case  android.os.Build.VERSION_CODES.FROYO:
			
			switch (tipoRequest) {
			case REQ_GET:
				iConnWeb = new ConexaoHttpGet(url);
				break;
			case REQ_POST:
				iConnWeb = new ConexaoHttpsPost(url);
				break;

			
			}
			
			break;

		default:
			
			switch (tipoRequest) {
			case REQ_GET:
				iConnWeb = new ConexaoAndroidIceCreamGet(url);
				break;
			case REQ_POST:
				iConnWeb = new ConexaoAndroidIceCreamPost(url);
				break;

			default:
				break;
			}
			
			
			
			break;
		}
		
		
		return iConnWeb;
	}

}
