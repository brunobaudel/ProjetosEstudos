package mobi.rectour.mapa.funcoes.googleplaces;


public class UrlConstantes {
	
	private static String key = "AIzaSyB40WBnl6j2USc41yKT2W8pEz6g0xgmh00";
	
	
//	https://maps.googleapis.com/maps/api/place/search/json?keyword=Recife+Praia+Hotel&types=lodging&location=-8.091448,-34.882141&radius=5000&sensor=false&key=AIzaSyB40WBnl6j2USc41yKT2W8pEz6g0xgmh00

	// tipo hotel - lodging
	private static String buscarLocais = "https://maps.googleapis.com/maps/api/place/search/json?" +
			"keyword=%s&" +
			"types=%s&" +
			"location=%s&" +
			"radius=1000&" +
			"sensor=false" +
			"&key=%s";
	
	private static String buscarDetalhesLocal = "https://maps.googleapis.com/maps/api/place/details/json?" +
			"reference=%s" +
			"&sensor=true&" +
			"key=%s";
	
	private static String buscarFoto = "https://maps.googleapis.com/maps/api/place/photo?" +
			"maxwidth=400&" +
			"photoreference=%s" +
			"&sensor=true&" +
			"key=%s";
	

	public static String getUrlBuscaLocal(String nomeLocal,String tipoLocal, String localizacao){
		return String.format(buscarLocais ,nomeLocal.replaceAll(" ", "+"),tipoLocal ,localizacao,key);
	}
	
	public static String getUrlBuscaDetalheLocal(String referencia){
		return String.format(buscarDetalhesLocal, referencia,key);
	}
	
	public static String getUrlBuscarFoto(String referencia){
		
		return String.format(buscarFoto, referencia,key);
		
		
	}

}
