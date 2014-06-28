
package mobi.rectour.mapa.funcoes.foursquare.entidadestips;

import java.util.List;

public class Items{
   	private String canonicalUrl;
   	private Number createdAt;
   	private String id;
   	private String lang;
   	private boolean like;
   	private Likes likes;
   	private String text;
   	private Todo todo;
   	private User user;

 	public String getCanonicalUrl(){
		return this.canonicalUrl;
	}
	public void setCanonicalUrl(String canonicalUrl){
		this.canonicalUrl = canonicalUrl;
	}
 	public Number getCreatedAt(){
		return this.createdAt;
	}
	public void setCreatedAt(Number createdAt){
		this.createdAt = createdAt;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getLang(){
		return this.lang;
	}
	public void setLang(String lang){
		this.lang = lang;
	}
 	public boolean getLike(){
		return this.like;
	}
	public void setLike(boolean like){
		this.like = like;
	}
 	public Likes getLikes(){
		return this.likes;
	}
	public void setLikes(Likes likes){
		this.likes = likes;
	}
 	public String getText(){
		return this.text;
	}
	public void setText(String text){
		this.text = text;
	}
 	public Todo getTodo(){
		return this.todo;
	}
	public void setTodo(Todo todo){
		this.todo = todo;
	}
 	public User getUser(){
		return this.user;
	}
	public void setUser(User user){
		this.user = user;
	}
}
