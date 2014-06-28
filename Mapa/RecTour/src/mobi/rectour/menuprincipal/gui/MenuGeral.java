package mobi.rectour.menuprincipal.gui;

import mobi.rectour.R;
import mobi.rectour.atualizacoesremotas.AtualizarDadosApp;
import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.INotificarAtualizacoesFinalizadas;
import mobi.rectour.bod.ParserArquivoToInsertBD;
import mobi.rectour.bod.bd.FactoryRepositorios;
import mobi.rectour.bod.bd.IRepositorio;
import mobi.rectour.bod.bd.TabelasBOD;
import mobi.rectour.bod.repositorios.RepAtualizacao;
import mobi.rectour.geral.RecTourVariaveisGlobais;
import mobi.rectour.mapa.funcoes.v2.funcMyLocation.BuscarLocalizacaoAtualV2;
import mobi.rectour.recHoteis.automato.AutomatoBuscarHoteis;
import mobi.rectour.recHoteis.gui.HotelMapFr_Teste;
import mobi.rectour.recRestaurantes.automato.AutomatoBuscarRestaurante;
import mobi.rectour.recRestaurantes.gui.RestauranteMapaFr;
import mobi.rectour.recRoteirosTurismoLazer.automato.AutomatoBuscarCentrosCompras;
import mobi.rectour.recRoteirosTurismoLazer.automato.AutomatoBuscarMuseus;
import mobi.rectour.recRoteirosTurismoLazer.automato.AutomatoBuscarPontesRecife;
import mobi.rectour.recRoteirosTurismoLazer.automato.AutomatoBuscarTeatros;
import mobi.rectour.recRoteirosTurismoLazer.gui.museu.MuseuMapFr;
import mobi.rectour.recRoteirosTurismoLazer.gui.ponte.PonteMapFr;
import mobi.rectour.recRoteirosTurismoLazer.gui.shopping.ShoppingMapFr;
import mobi.rectour.recRoteirosTurismoLazer.gui.teatro.TeatroMapFr;
import mobi.rectour.util.DialogInformacoesServidor;
import mobi.rectour.util.DialogInformacoesServidor.DialogCallBack;
import mobi.rectour.web.InformacoesServidor;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MenuGeral extends Fragment implements OnClickListener,
		DialogCallBack, INotificarAtualizacoesFinalizadas {

	AtualizarDadosApp atualizarDadosGeral;

	private ImageView ivCarregarRestaurante;
	private ImageView ivCarregarHotel;
	private ImageView ivCarregarTeatro;
	private ImageView ivCarregarPonte;
	private ImageView ivCarregarMuseu;
	private ImageView ivCarregarSpp;
	
	
	private static IRepositorio repositorioAtualizacao;

	@Override
	public void onDestroy() {
		super.onDestroy();
		
	}
	
	public MenuGeral() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (RecTourVariaveisGlobais.atualizarTabelasEntrarApp) {// Atualiza apenas quando entra no sistema

			repositorioAtualizacao = FactoryRepositorios.createRepositorios(RepAtualizacao.REPOSITORIO_ATUALIZACAO, getActivity());
			mobi.rectour.bod.entidades.Atualizacao a = (mobi.rectour.bod.entidades.Atualizacao) repositorioAtualizacao.recuperarItem(TabelasBOD.TBL_Paradas);
			if(a.getDeterminarAtualizacao().equals("S")){
				
				AsyncParser ap = new AsyncParser(R.raw.p, getActivity());
				ap.execute();
				
			}
			
			BuscarLocalizacaoAtualV2 buscarLocalizacao = new BuscarLocalizacaoAtualV2(
					getActivity());

			atualizarDadosGeral = new AtualizarDadosApp(getActivity());
			atualizarDadosGeral.iAtualizacoesFinalizadas = this;

			InformacoesServidor is = atualizarDadosGeral
					.msgUsuarioTabelasaSeremAtualizadas();

			if (is.isFalhaRequisicao()) {// Verdadeiro mostrar dialog de aviso
											// para o usuario que sera
											// atualizado os dados
				DialogInformacoesServidor.mostrarDialogInformacaoServidor(
						getActivity(), is, this).setCancelable(false);
			}

			RecTourVariaveisGlobais.atualizarTabelasEntrarApp = false;
		}

	}
	
	public class AsyncParser extends AsyncTask<Void, Void, Void>{

		
		int res;
		Context c;
		
		public AsyncParser(int res , Context c){
			
			this.res = res;
			this.c = c;
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			
			ParserArquivoToInsertBD.execParseMutiTreadXML(res, c);
			
			return null;
		}
		
		
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.menu_geral_tela, null);
		
		((MenuContainer)getActivity()).setTitulo("Legion Tour\nRecife");
		
		((Button) v.findViewById(R.id.btHoteis)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btRestaurante)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btShoppings)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btTeatro)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btMuseus)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btPontes)).setOnClickListener(this);

		ivCarregarRestaurante = (ImageView) v
				.findViewById(R.id.ivCarregarRestaurante);
		ivCarregarHotel = (ImageView) v.findViewById(R.id.ivCarregarHotel);
		ivCarregarTeatro = (ImageView) v.findViewById(R.id.ivCarregarTeatro);
		ivCarregarPonte = (ImageView) v.findViewById(R.id.ivCarregarPonte);
		ivCarregarMuseu = (ImageView) v.findViewById(R.id.ivCarregarMuseu);
		ivCarregarSpp = (ImageView) v.findViewById(R.id.ivCarregarShopping);
		

		configurarLoad();

		return v;
	}

	private void configurarLoad() {

		if (!atualizarDadosGeral.atualizandoHotel) {
			ivCarregarHotel.setVisibility(View.GONE);
		}

		if (!atualizarDadosGeral.atualizandoMuseu) {
			ivCarregarMuseu.setVisibility(View.GONE);
		}

		if (!atualizarDadosGeral.atualizandoPonte) {
			ivCarregarPonte.setVisibility(View.GONE);
		}

		if (!atualizarDadosGeral.atualizandoRestaurante) {
			ivCarregarRestaurante.setVisibility(View.GONE);
		}

		if (!atualizarDadosGeral.atualizandoShopping) {
			ivCarregarSpp.setVisibility(View.GONE);
		}

		if (!atualizarDadosGeral.atualizandoTeatro) {
			ivCarregarTeatro.setVisibility(View.GONE);
		}

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btHoteis:
			((MenuContainer) getActivity()).refreshFragment(
					HotelMapFr_Teste.PKG_Mapa_Hotel, null, true);
			break;

		case R.id.btRestaurante:
			((MenuContainer) getActivity()).refreshFragment(
					RestauranteMapaFr.PKG_Mapa_Restaurante, null, true);
			//((MenuContainer) getActivity()).refreshFragment(
				//	MapaE_FR.PKG_Mapa_Parada, null, true);
			
			break;

		case R.id.btShoppings:
			((MenuContainer) getActivity()).refreshFragment(
					ShoppingMapFr.PKG_Mapa_Shopping, null, true);
			break;

		case R.id.btMuseus:
			((MenuContainer) getActivity()).refreshFragment(
					MuseuMapFr.PKG_Mapa_Museu, null, true);
			break;

		case R.id.btTeatro:
			((MenuContainer) getActivity()).refreshFragment(
					TeatroMapFr.PKG_Mapa_Teatro, null, true);
			break;

		case R.id.btPontes:
			((MenuContainer) getActivity()).refreshFragment(
					PonteMapFr.PKG_Mapa_Ponte, null, true);
			break;

		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void btSim(int idDialog) {

		atualizarDadosGeral.atualizarRestaurante();
		atualizarDadosGeral.atualizarHotel();
		atualizarDadosGeral.atualizarMuseu();
		atualizarDadosGeral.atualizarShoppings();
		atualizarDadosGeral.atualizarTeatros();
		atualizarDadosGeral.atualizarPontes();

	}

	@Override
	public void btNao(int idDialog) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notificar(int idAutomato) {

		switch (idAutomato) {
		case AutomatoBuscarHoteis.idHotel:

			ivCarregarHotel.setVisibility(View.GONE);

			break;

		case AutomatoBuscarMuseus.idMuseu:

			ivCarregarMuseu.setVisibility(View.GONE);

			break;

		case AutomatoBuscarPontesRecife.idPonte:

			ivCarregarPonte.setVisibility(View.GONE);

			break;
		case AutomatoBuscarRestaurante.idRestaurante:

			ivCarregarRestaurante.setVisibility(View.GONE);

			break;
		case AutomatoBuscarTeatros.idTeatro:

			ivCarregarTeatro.setVisibility(View.GONE);

			break;
		case AutomatoBuscarCentrosCompras.idShopping:

			ivCarregarSpp.setVisibility(View.GONE);

			break;

		default:
			break;
		}

	}
	
	
	
	
	
	
}
