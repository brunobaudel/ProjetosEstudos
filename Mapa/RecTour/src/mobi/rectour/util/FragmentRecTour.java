package mobi.rectour.util;

import mobi.rectour.R;
import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.configuracao.Configuracoes;
import mobi.rectour.mapa.funcoes.v2.funcMyLocation.BuscarLocalizacaoAtualV2;
import mobi.rectour.mapa.funcoes.v2.funcMyLocation.BuscarLocalizacaoAtualV2.ICallBackMinhaLocalizacao;
import mobi.rectour.mapa.funcoes.v2.funcTracarRotas.AutomatoBuscarRota;
import mobi.rectour.mapa.funcoes.v2.funcTracarRotas.Route;
import mobi.rectour.mapa.funcoes.v2.funcTracarRotas.TracarRotasV2;
import mobi.rectour.recHoteis.gui.InformacoesHoteis;
import mobi.rectour.util.SupportMapFragmentRecTour.OnGoogleMapFragmentListener;
import mobi.rectour.web.InformacoesServidor;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public abstract class FragmentRecTour extends Fragment implements OnClickListener,
		OnItemClickListener, RadioGroup.OnCheckedChangeListener,
		ICallBackMinhaLocalizacao, CallBackAtualizacoesProntas,
		OnGoogleMapFragmentListener, OnMarkerClickListener {
	
	
	// Mapa
	public GoogleMap mMap;
	public FragmentTransaction tx;
	public SupportMapFragmentRecTour sMf;
	
	
	// Progress Bar Carregamento
	public ProgressBar carregando;
	public ProgressDialogRecTour progressRecTour;
	public LinearLayout llMenu;
	public LinearLayout llControleMapa;
	public RelativeLayout rlNavegacao;
	public Button btNavegacao;
	public Button btTracarRotas;
	public Button btFechar;

	public TracarRotasV2 trV2;
	
	// Marker minha localizacao
	public Marker myLocation;
	public Marker markerDestino;
	
	// Fliper
	public ViewFlipper mFlipper;
	public static final int STATE_MAP = 0;
	public static final int STATE_LIST = 1;
	public int mState;

	public RadioButton rb;
	
	public Configuracoes configuracoes;
	
	public String nomeSelecao;
	public String idSelecao;
	
	private LayoutInflater layoutInf;
	
	private boolean modoLocalizacao;

	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		configuracoes = new Configuracoes(activity);
		progressRecTour = new ProgressDialogRecTour(activity);
		modoLocalizacao = false;
	}
	
	@Override
	public boolean onMarkerClick(Marker marker) {
		return false;
	}

	@Override
	public void onMapReady(GoogleMap map) {
		
		mMap = map;
		// Hide the zoom controls as the button panel will cover it.
		mMap.getUiSettings().setZoomControlsEnabled(false);
		mMap.setOnMarkerClickListener(this);
	}

	@Override
	public void operacaoConcluida(int codigoAutomato, InformacoesServidor erro) {
		
		if (!erro.isFalhaRequisicao()) {
			LatLng minhaLocalizacao = myLocation.getPosition();

			trV2 = new TracarRotasV2((Route) erro.getRetorno());
			trV2.execute(mMap);

			animateCamera(minhaLocalizacao, 500 , 19.0f, null);
			
			btNavegacao.setVisibility(View.VISIBLE);
			btTracarRotas.setVisibility(View.GONE);
			
		} else {
			DialogInformacoesServidor.mostrarDialogInformacaoServidor(
					getActivity(), erro);
		}
		progressRecTour.dismiss();

	}

	@Override
	public void upDateLocalizacao(LatLng localizacaoAtual) {
		myLocation.setPosition(localizacaoAtual);
		
		if(modoLocalizacao){
			
			animateCamera(localizacaoAtual, 1000, 18, null);
			
		}
		
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		
		switch (checkedId) {
		case R.id.rbMapa:

			if (mState == STATE_MAP) {
				return;
			}
			mFlipper.setDisplayedChild(STATE_MAP);
			mState = STATE_MAP;
			
			if(llMenu.getVisibility() == View.INVISIBLE && markerDestino != null){
				llMenu.setVisibility(View.VISIBLE);
			}

			break;
		case R.id.rbLista:
			if (mState == STATE_LIST) {
				return;
			}
			mFlipper.setDisplayedChild(STATE_LIST);
			mState = STATE_LIST;

			if(llMenu.getVisibility() == View.VISIBLE){
				llMenu.setVisibility(View.INVISIBLE);
			}
			
			break;
			
		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		rb.setChecked(true);
		mFlipper.setDisplayedChild(STATE_MAP);
		mState = STATE_MAP;
		
		animateCamera((LatLng) arg1.getTag(), 1000, 19.0f, null);
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btTracarRotas:

			if (markerDestino != null) {

				LatLng minhaLocalizacao = myLocation.getPosition();

				progressRecTour.setCancelable(false);
				progressRecTour.show();

				AutomatoBuscarRota abr = new AutomatoBuscarRota(minhaLocalizacao, markerDestino.getPosition());
				abr.setCbap(this);
				abr.executar();
			}

			break;

		case R.id.btIrHotel:

			if (markerDestino != null) {
				animateCamera(markerDestino.getPosition(), 600, 19.0f, null);
			}
			break;
			
		case R.id.btNavegacao:
			
			if(rlNavegacao.isClickable()){
				rlNavegacao.setClickable(false);
				
				modoLocalizacao = false;
				llControleMapa.setVisibility(View.VISIBLE);
				btFechar.setVisibility(View.VISIBLE);
				myLocation.setTitle("oiiii");
				animateCamera(myLocation.getPosition(), 1000, 15, null);
			}else{
				modoLocalizacao = true;
				rlNavegacao.setClickable(true);
				rlNavegacao.setOnClickListener(this);
				llControleMapa.setVisibility(View.INVISIBLE);
				btFechar.setVisibility(View.INVISIBLE);
				myLocation.setTitle("Atenção voce esta em modo de navegação");
				
				animateCamera(myLocation.getPosition(), 1000, 18, null);
			}
			break;
			
		case R.id.btMinhaLocalizacao:
			
			animateCamera(myLocation.getPosition(), 1000, 18, null);
			break;
			
			case R.id.rlNavegacao:
				
				if(myLocation.isInfoWindowShown()){
					myLocation.hideInfoWindow();
				}else{
					myLocation.showInfoWindow();
				}
				break;
				
			case R.id.btFechar:
			
				if ( markerDestino != null) {
					
					visaoGeral();
					
					llMenu.setVisibility(View.INVISIBLE);
					markerDestino.setIcon(BitmapDescriptorFactory
							.fromResource(  getDrawable() )); //R.drawable.marker_hotel));  9988
					btNavegacao.setVisibility(View.GONE);
					btTracarRotas.setVisibility(View.VISIBLE);
					markerDestino = null;
				}
				
				if (trV2 != null){
					trV2.removerRota(0, true);
				}
			break;

			case R.id.btInformacoes:
				
				Intent it = new Intent(getActivity(), InformacoesHoteis.class);
				
				it.putExtra("types", "lodging");
				it.putExtra("latLong", markerDestino.getPosition().latitude + "," + markerDestino.getPosition().longitude);
				it.putExtra("keyword", nomeSelecao);
				it.putExtra("idRecTour", idSelecao);
				startActivity(it);
				
				
			break;	
			
		default:
			break;
		}

	}
	
	
	public void animateCamera(LatLng posicao, int duracao , float zoom, CancelableCallback callback){
		
		CameraPosition posicaoCameraClicada = new CameraPosition.Builder()
		.target(posicao).zoom(zoom).build();
		
		mMap.animateCamera(
				CameraUpdateFactory.newCameraPosition(posicaoCameraClicada),
				duracao, callback);
	}
	
	public boolean removerFR(String tagFragment) {

		boolean voltar = false;
		Fragment fragment = (getFragmentManager()
				.findFragmentByTag(tagFragment));

		if (fragment != null) {
			FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			ft.remove(fragment);
			ft.commit();
			voltar = true;
		}
		return voltar;
	}

	
	public LayoutInflater getLayoutInf() {
		return layoutInf;
	}

	public void setLayoutInf(LayoutInflater layoutInf) {
		this.layoutInf = layoutInf;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		BuscarLocalizacaoAtualV2.icallMinhaLocalizacao = null;
	}

	
	public abstract int getDrawable(); 
	
	public abstract void visaoGeral();
	
}
