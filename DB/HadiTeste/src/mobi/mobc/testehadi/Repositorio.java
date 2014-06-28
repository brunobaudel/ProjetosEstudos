package mobi.mobc.testehadi;

import java.util.List;

import android.content.Context;

import com.the9tcat.hadi.DefaultDAO;

public class Repositorio {

	 private DefaultDAO dao;

	    public Repositorio(Context context) {
	        dao = new DefaultDAO(context);
	        
	    }

	    public void insertEstacao(Estacao estacao) throws InsercaoNaoRealizadaException {
	       long id = dao.insert(estacao);
	        if(id < 0) {
//	            for (int i = 0; i <= father.getChilds().size()-1; i++) {
//	                Child child = father.getChilds().get(i);
//	                child.setFatherId(id);
//	                dao.insert(child);
//	            }
	        	
	        	throw  new InsercaoNaoRealizadaException("A operação de inserção não pode ser realizada");
	        	
	        }
	    }

	    @SuppressWarnings("unchecked")
		public List<Estacao> getEstacao() {
	        return (List<Estacao>)dao.  select(Estacao.class, false, null, null, null, null, null, null);
	    }
	    
	    
}
