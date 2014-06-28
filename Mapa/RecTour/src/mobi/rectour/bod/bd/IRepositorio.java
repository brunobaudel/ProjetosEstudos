package mobi.rectour.bod.bd;



public interface IRepositorio <Item, Iterator, Cursor>{
	
	long inserir(Item it);
	String recuperarJson();
	Iterator recuperarRegistro();
	Iterator recuperarRegistro(Object o);
	Cursor executarSql(String sql);
	Item recuperarItem(String codigo);
	void deletarItem(String codigo);
	void inserirItemSQL(String comando);
	
	
}
