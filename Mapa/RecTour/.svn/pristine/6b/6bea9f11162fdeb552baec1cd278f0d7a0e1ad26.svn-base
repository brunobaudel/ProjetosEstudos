package mobi.rectour.mapa.funcoes;

public class FuncoesMenu {
	
	public interface IFuncoesMenu{
		
		void execute(Object o);
		void cancel (Object o);
		
	}
	
	private IFuncoesMenu funcoesMenu;
	
	
	public IFuncoesMenu getFuncoesMenu() {
		return funcoesMenu;
	}


	public FuncoesMenu(IFuncoesMenu ifm){
		this.funcoesMenu = ifm;
	}
	
	
	public void executarFuncao(Object o){
		if(funcoesMenu != null)
			funcoesMenu.execute(o);
	}
	
	
	public void cancelarFuncao(Object o){
		if(funcoesMenu != null)
			funcoesMenu.cancel(o);
	}
	
}
