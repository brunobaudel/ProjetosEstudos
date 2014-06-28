package mobi.rectour.recRoteirosTurismoLazer.gui.shopping;

import mobi.rectour.R;
import mobi.rectour.atualizacoesremotas.AtualizarDadosApp;
import mobi.rectour.geral.RecTourDatabase;
import mobi.rectour.geral.RecTourGeral;
import mobi.rectour.mapa.funcoes.FuncoesMenu;
import mobi.rectour.mapa.funcoes.FuncoesMenu.IFuncoesMenu;
import mobi.rectour.mapa.funcoes.v2.funcMyLocation.BuscarLocalizacaoAtualV2;
import mobi.rectour.mapa.funcoes.v2.plotarpontos.PlotarPontosV2;
import mobi.rectour.mapa.funcoes.v2.plotarpontos.PlotarPontosV2.IConstruirPontos;
import mobi.rectour.menuprincipal.gui.MenuContainer;
import mobi.rectour.util.FragmentRecTour;
import mobi.rectour.util.SupportMapFragmentRecTour;
import mobi.rectour.util.UtilRecTour;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShoppingMapFr extends FragmentRecTour {

	public static String PKG_Mapa_Shopping = "mobi.rectour.recRoteirosTurismoLazer.gui.shopping.ShoppingMapFr";

	// Listas
	private ShoppingListaADPT cAdptListaVagas;
	private ListView lvShopping;
	
	// Fun��es do menu
	FuncoesMenu fmPlotarPontos;
	
	// Views configura��o detalhes marker
	private TextView tvNomeShopping;
	private TextView tvDistancia;

	public ShoppingMapFr() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		tx  = getActivity().getSupportFragmentManager().beginTransaction();
		sMf = SupportMapFragmentRecTour.newInstance();
		sMf.setmCallback(this);

		tx.replace(R.id.fl_map_spp, sMf, "Mapa_Shopping");
		tx.commit();
	}

	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.shopping_mapa, container, false);

		((MenuContainer)getActivity()).setTitulo("Shoppings");
		
		mFlipper = (ViewFlipper) view.findViewById(R.id.vf);
		((RadioGroup) view.findViewById(R.id.rgMapaLista)).setOnCheckedChangeListener(this);
		
		((Button) view.findViewById(R.id.btIrHotel)).setOnClickListener(this);
		(btTracarRotas = (Button) view.findViewById(R.id.btTracarRotas)).setOnClickListener(this);
		(btNavegacao   = (Button) view.findViewById(R.id.btNavegacao)).setOnClickListener(this);
		((Button) view.findViewById(R.id.btMinhaLocalizacao)).setOnClickListener(this);
		((Button) view.findViewById(R.id.btInformacoes)).setOnClickListener(this);
		(btFechar      = (Button) view.findViewById(R.id.btFechar)).setOnClickListener(this);
		
		((Button) view.findViewById(R.id.btMinhaLocalizacao))
		.setBackgroundResource(RecTourGeral.getIconMinhaLocalizacao(configuracoes));
		
		tvNomeShopping = (TextView) view.findViewById(R.id.tvNomeHotel);
		tvDistancia = (TextView) view.findViewById(R.id.tvDistancia);

		setLayoutInf(inflater);

		carregando = (ProgressBar) view.findViewById(R.id.pbCarregando);

		rb = (RadioButton) view.findViewById(R.id.rbMapa);

		lvShopping = (ListView) view.findViewById(R.id.lvRestaurante);

		llMenu         = (LinearLayout) view.findViewById(R.id.llMenu);
		llControleMapa = (LinearLayout) view.findViewById(R.id.llControleMapa); 
		rlNavegacao    = (RelativeLayout) view.findViewById(R.id.rlNavegacao);
		
		return view;
		
	}

	@Override
	public void onMapReady(GoogleMap map) {
		super.onMapReady(map);
		
		LatLng setLatLog = BuscarLocalizacaoAtualV2.getLocalizacao();
		
		int drawable = configuracoes.getIconLocation().getIdIcone();

		MarkerOptions mo = new MarkerOptions().position(setLatLog).snippet("myLocation")
				.visible(true)
				.icon(BitmapDescriptorFactory.fromResource(drawable));

		myLocation = mMap.addMarker(mo);
		
		myLocation.setTitle("Oiii");

		animateCamera(setLatLog, 10, 16.0f,callback);
		
		//mMap.setOnMapClickListener(onMapClick);
		mMap.setInfoWindowAdapter(new ShoppingMaker(getLayoutInf()));
		
		// Se estiveratualizando os pontos n entra
		if (!AtualizarDadosApp.atualizandoShopping) {
			carregando.setVisibility(View.INVISIBLE);
		}
		
	}
	
	
	CancelableCallback callback = new CancelableCallback() {

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			if (!AtualizarDadosApp.atualizandoShopping) {

				Cursor cHoteis = RecTourDatabase.recuperarShoppingsDistancia(BuscarLocalizacaoAtualV2.getLocalizacao());

				IFuncoesMenu iFuncoesMenuPlotarPontos = new PlotarPontosV2(
						cHoteis, getActivity());
				fmPlotarPontos = new FuncoesMenu(iFuncoesMenuPlotarPontos);
				((PlotarPontosV2) fmPlotarPontos.getFuncoesMenu())
						.setIConstruirPontos(pontosShoppings);
				fmPlotarPontos.executarFuncao(mMap);

				cAdptListaVagas = new ShoppingListaADPT(getActivity(), cHoteis);
				lvShopping.setAdapter(cAdptListaVagas);
				lvShopping.setOnItemClickListener(ShoppingMapFr.this);

				BuscarLocalizacaoAtualV2.icallMinhaLocalizacao = ShoppingMapFr.this;
			}
		}

		@Override
		public void onCancel() {

		}
	};
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		removerFR("Mapa_Shopping");
	}

	IConstruirPontos pontosShoppings = new IConstruirPontos() {

		@Override
		public MarkerOptions getMarkeOptions(LatLng local, Cursor cursor) {

			String _id = cursor.getString(cursor.getColumnIndex("_id"));
			return new MarkerOptions().position(local).snippet(_id)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_shop));
		}
	};
	
	@Override
	public boolean onMarkerClick(Marker marker) {
		
		if("myLocation".equals( marker.getSnippet())){
			return false;
		}
		
		//Tira a marca��o do marke anterior
		if (markerDestino != null) {
			markerDestino.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_shop));
		}

		animateCamera(marker.getPosition(), 600, 19.0f, null);
		
		marker.showInfoWindow();
		llMenu.setVisibility(View.VISIBLE);
		markerDestino = marker;
		marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_shop_));

		configurarView(marker);

		return true;
	}
	
	
	public void iniciarProcesso() {
		carregando.setVisibility(View.VISIBLE);
	}

	public void pararProgress(boolean erro) {
		fmPlotarPontos.cancelarFuncao(1);
		fmPlotarPontos.executarFuncao(mMap);
		carregando.setVisibility(View.INVISIBLE);
	}
	
	private void configurarView(Marker marker) {

		String id = marker.getSnippet();

		LatLng myLocation = BuscarLocalizacaoAtualV2.getLocalizacao();

		if (id.equals("") || myLocation == null) {
			return;
		}

		Cursor c = RecTourDatabase.recuperarShoppingsDistancia(myLocation, id);
		
		if (c.moveToNext()) {

			//String rtEndereco = c.getString(c.getColumnIndex("rtEndereco"));
			String rtNome = c.getString(c.getColumnIndex("rtNome"));
			nomeSelecao = rtNome;
			//String rtTelefone = c.getString(c.getColumnIndex("rtTelefone"));
			//String rtSite = c.getString(c.getColumnIndex("rtSite"));

			double distancia = c.getDouble(c.getColumnIndex("distancia"));
			double distanciaKm = UtilRecTour.convertPartialDistanceToKm(distancia);

			tvNomeShopping.setText(rtNome);
			//tvTelefone.setText(rtTelefone);
			tvDistancia.setText(UtilRecTour.getLegendaDistancia(distanciaKm));
		}
	}
	
	public int getDrawable() {
		return R.drawable.marker_shop;
	}
	
	@Override
	public void visaoGeral() {
		
		
		((PlotarPontosV2)fmPlotarPontos.getFuncoesMenu()).mostrarTodosMarkesMapa(mMap);
		
	}
	
}
