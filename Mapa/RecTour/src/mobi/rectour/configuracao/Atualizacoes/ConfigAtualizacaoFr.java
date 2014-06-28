package mobi.rectour.configuracao.Atualizacoes;

import mobi.rectour.R;
import mobi.rectour.atualizacoesremotas.AtualizarDadosApp;
import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.INotificarAtualizacoesFinalizadas;
import mobi.rectour.util.ProgressDialogRecTour;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ConfigAtualizacaoFr extends Fragment implements OnItemClickListener,INotificarAtualizacoesFinalizadas{
	
	private ListView listaTabelas;
	private AtualizacaoADPT atualizacaoADPT;
	private AtualizarDadosApp atualizarDadosApp;
	
	public ProgressDialogRecTour progressRecTour;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		progressRecTour = new ProgressDialogRecTour(activity);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	
		View view = inflater.inflate(R.layout.config_lista_tabelas_atualizar_tela, container, false);
          
		listaTabelas = (ListView) view.findViewById(R.id.lvTabelasAtualizacao);
		
		atualizarDadosApp = new AtualizarDadosApp(getActivity());
		atualizarDadosApp.iAtualizacoesFinalizadas = this;
		
		atualizacaoADPT = new AtualizacaoADPT(getActivity(), atualizarDadosApp.listarTabelasAtualizacao() );
		
		listaTabelas.setAdapter(atualizacaoADPT);
		listaTabelas.setOnItemClickListener(this);
		
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		String descricao = (((TextView) arg1.findViewById(R.id.tvNomeLocalizador)) .getTag().toString() );
		
		atualizarDadosApp.escolherAutomatoAtualizar(descricao);
		progressRecTour.setCancelable(false);
		progressRecTour.show();
		
		
	}

	@Override
	public void notificar(int idAutomato) {
		
		atualizacaoADPT = new AtualizacaoADPT(getActivity(), atualizarDadosApp.listarTabelasAtualizacao() );
		
		listaTabelas.setAdapter(atualizacaoADPT);
		listaTabelas.setOnItemClickListener(this);
		
		progressRecTour.dismiss();
		
	}
	
}
