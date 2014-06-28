
package mobi.rectour.mapa.funcoes.foursquare.entidadesfotos;


public class Items{
   	private Number createdAt;
   	private Number height;
   	private String id;
   	private String prefix;
   	private Source source;
   	private String suffix;
   	private User user;
   	private String visibility;
   	private Number width;

 	public Number getCreatedAt(){
		return this.createdAt;
	}
	public void setCreatedAt(Number createdAt){
		this.createdAt = createdAt;
	}
 	public Number getHeight(){
		return this.height;
	}
	public void setHeight(Number height){
		this.height = height;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getPrefix(){
		return this.prefix;
	}
	public void setPrefix(String prefix){
		this.prefix = prefix;
	}
 	public Source getSource(){
		return this.source;
	}
	public void setSource(Source source){
		this.source = source;
	}
 	public String getSuffix(){
		return this.suffix;
	}
	public void setSuffix(String suffix){
		this.suffix = suffix;
	}
 	public User getUser(){
		return this.user;
	}
	public void setUser(User user){
		this.user = user;
	}
 	public String getVisibility(){
		return this.visibility;
	}
	public void setVisibility(String visibility){
		this.visibility = visibility;
	}
 	public Number getWidth(){
		return this.width;
	}
	public void setWidth(Number width){
		this.width = width;
	}
}
