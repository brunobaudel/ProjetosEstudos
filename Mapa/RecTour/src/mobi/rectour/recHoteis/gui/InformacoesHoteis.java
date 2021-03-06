package mobi.rectour.recHoteis.gui;

import java.util.ArrayList;
import java.util.List;

import mobi.rectour.R;
import mobi.rectour.atualizacoesremotas.AtualizarDadosApp.CallBackAtualizacoesProntas;
import mobi.rectour.mapa.funcoes.foursquare.AutomatoBuscarFotos;
import mobi.rectour.mapa.funcoes.foursquare.AutomatoBuscarTips;
import mobi.rectour.mapa.funcoes.foursquare.AutomatoBuscarVenueMapping;
import mobi.rectour.mapa.funcoes.foursquare.VenuesAdpt;
import mobi.rectour.mapa.funcoes.foursquare.VenuesAdpt.ViewHolder;
import mobi.rectour.mapa.funcoes.foursquare.entidadesfotos.Items;
import mobi.rectour.mapa.funcoes.foursquare.entidadesvenues.Venues;
import mobi.rectour.mapa.funcoes.foursquare.listtips.TipsADPT;
import mobi.rectour.util.DialogListagem;
import mobi.rectour.util.FragmentImg;
import mobi.rectour.util.ProgressDialogRecTour;
import mobi.rectour.web.InformacoesServidor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class InformacoesHoteis extends FragmentActivity implements CallBackAtualizacoesProntas,OnItemClickListener,RadioGroup.OnCheckedChangeListener{
	
	public ProgressDialogRecTour progressRecTour;
	
	public ViewPager vpImg ;
	
	
	// Fliper
		public ViewFlipper mFlipper;
		public static final int STATE_Fotos = 0;
		public static final int STATE_tips = 1;
		public int mState;
		public RadioButton rb;
		private ListView lvComentariosFS;
		public TextView tvTitulo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.hotel_informacoes_tela);
		
		// Set views
		mFlipper = (ViewFlipper) findViewById(R.id.vf);
		mFlipper.setDisplayedChild(STATE_Fotos);
		((RadioGroup) findViewById(R.id.rgFotosLista)).setOnCheckedChangeListener(this);
		rb = (RadioButton) findViewById(R.id.rbFotos);
		// lvVagas
	    lvComentariosFS = (ListView) findViewById(R.id.lvComentariosFS);

	    ((ImageButton) findViewById(R.id.ibAbrirDrawer)).setVisibility(View.GONE);

	    tvTitulo = (TextView) findViewById(R.id.tvTituloGeral);
	    
	    
		
		vpImg = (ViewPager) findViewById(R.id.vpFotos);
		
		
		progressRecTour = new ProgressDialogRecTour(this);
		progressRecTour.setCancelable(false);
		progressRecTour.show();
		
		String tipoLocal   = getIntent().getStringExtra("types");
		String localizacao = getIntent().getStringExtra("latLong");
		String nomeLocal   = getIntent().getStringExtra("keyword");
		String idLocal     = getIntent().getStringExtra("idRecTour");
		
//		AutomatoBuscarLocalGooglePlace automatoBuscarLocalGP = 
//				new AutomatoBuscarLocalGooglePlace(nomeLocal, tipoLocal, localizacao,idLocal);
//		
//		automatoBuscarLocalGP.setCbap(this);
//		
//		automatoBuscarLocalGP.executar();
		
		tvTitulo.setText(nomeLocal);
	    
		
		AutomatoBuscarVenueMapping automatoVenue = new AutomatoBuscarVenueMapping(localizacao, nomeLocal, this);
		automatoVenue.setCbap(this);
		automatoVenue.executar();
		
	}

	@Override
	public void operacaoConcluida(int codigoAutomato, InformacoesServidor erro) {
		
		
		switch (codigoAutomato) {
		case AutomatoBuscarVenueMapping.idBuscarVenueMapping:
			
			List<Venues> list = (List<Venues>) erro.getRetorno();
			
			if(list.size() == 0 ){
				progressRecTour.dismiss();
				break;
			}
			
			
			if(list.size() < 2 ){
				
				chamarAutomatoFotos(list.get(0).getId());
				
				chamarAutomatoTips(list.get(0).getId());
				
				break;
			}
			
			progressRecTour.dismiss();
			VenuesAdpt va = new VenuesAdpt( list , LayoutInflater.from(this));
			
			DialogListagem.showDialog(this, va, this);
			
			
			break;
			
		case AutomatoBuscarFotos.idBuscarFotos:
			
			List<Items> listVenues  = (List<Items>) erro.getRetorno();
			List<FragmentImg> lstFrImagens = new ArrayList<FragmentImg>();
			
			for (Items items : listVenues) {
				
				FragmentImg frImg =(FragmentImg) Fragment.instantiate(this, "mobi.rectour.util.FragmentImg") ;
				
				frImg.setUrlImg(items.getPrefix() +  items.getWidth() + "x" +items.getHeight() + items.getSuffix() );
				
				lstFrImagens.add(frImg);
			}
			
			ViewPagerAdapterFR vpaFotos = new ViewPagerAdapterFR(getSupportFragmentManager(), lstFrImagens,this);
			  
			vpImg.setAdapter(vpaFotos);
			
			//vpImg.setOnPageChangeListener(this);
			progressRecTour.dismiss();
			break;   
			
			
		case AutomatoBuscarTips.idBuscarTips :
			
			List<mobi.rectour.mapa.funcoes.foursquare.entidadestips.Items>  listVenuesTips = (List<mobi.rectour.mapa.funcoes.foursquare.entidadestips.Items>) erro.getRetorno();
			
			TipsADPT tipsAdpt = new TipsADPT(listVenuesTips, this.getLayoutInflater() , this);
			
			lvComentariosFS.setAdapter(tipsAdpt);
			
			break;
		
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		String id =  ((ViewHolder)arg1.getTag()).tvEstabelecimento.getTag().toString();
		
		chamarAutomatoFotos(id);
		chamarAutomatoTips(id);
		
		DialogListagem.fecharDialog();
		
	}
	
	private void chamarAutomatoFotos(String id){
		
		progressRecTour.show();
		
		AutomatoBuscarFotos buscarFotos = new AutomatoBuscarFotos( id, this);
		buscarFotos.setCbap(this);
		buscarFotos.executar();
	}
	
	
	
	private void chamarAutomatoTips(String id){
		
		progressRecTour.show();
		
		AutomatoBuscarTips buscarFotos = new AutomatoBuscarTips( id, this);
		buscarFotos.setCbap(this);
		buscarFotos.executar();
	}
	
	
	
	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		
		switch (checkedId) {
		case R.id.rbFotos:

			if (mState == STATE_Fotos) {
				return;
			}
			mFlipper.setDisplayedChild(STATE_Fotos);
			mState = STATE_Fotos;
			
			break;
		case R.id.rbLista:
			if (mState == STATE_tips) {
				return;
			}
			mFlipper.setDisplayedChild(STATE_tips);
			mState = STATE_tips;
			
			break;
			
		default:
			break;
		}

	}
	
	
}
