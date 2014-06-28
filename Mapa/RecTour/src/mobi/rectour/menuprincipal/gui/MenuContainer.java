package mobi.rectour.menuprincipal.gui;

import java.util.List;

import mobi.rectour.R;
import mobi.rectour.geral.RecTourVariaveisGlobais;
import mobi.rectour.util.BaseFragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MenuContainer extends BaseFragmentActivity implements
		DrawerListener, OnClickListener {

	private DrawerLayout drawer;
	private LinearLayout ll;
	private int statusDrawer;

	private ImageButton menu;
	private ImageButton voltar;

	
	private boolean menuPrincipal;
		
	private boolean btPress;
	
	private FragmentTransaction tx;
	
	private TextView tvTituloGeral;
	
	private AdpterListaSlideDrawer alsd;
	ListView navList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

 		setContentView(R.layout.menu_container_tela);
 		
 		(tvTituloGeral = (TextView)findViewById(R.id.tvTituloGeral)).setText("RecTour");

 		
 		RecTourVariaveisGlobais.atualizarTabelasEntrarApp = true;
 		
 		
		alsd = new AdpterListaSlideDrawer(this);

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ll = (LinearLayout) findViewById(R.id.drawer);
	
		navList = (ListView) findViewById(R.id.lvMenu);
		navList.setAdapter(alsd);

		drawer.setDrawerListener(this);
		
		menuPrincipal = true;
		
		tx =   getSupportFragmentManager().beginTransaction();

		navList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int pos, long id) {
				final List<String> titulo = (List<String>) ((AdpterListaSlideDrawer) parent.getAdapter()).getItem(pos);

				if (titulo.get(0).equals("sair")) {
					finish();
					return;
				}
				
				if (titulo.get(0).equals("mapa")) {
					
//					Intent it = new  Intent(MenuContainer.this, MapaVagasEstacionamentoMapAct.class);
//					
//					
//					startActivity(it);
					
					return;
				}
				
				
				final Bundle b = new Bundle();
				b.putString("SelecionarTela", titulo.get(3));

				drawer.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
					@Override
					public void onDrawerClosed(View drawerView) {
						super.onDrawerClosed(drawerView);

						refreshFragment(titulo.get(0), null,
								false);
						
						drawer.setDrawerListener(MenuContainer.this);
						statusDrawer = 1;
					}
				});
				drawer.closeDrawer(ll);
			}
		});

		(menu = (ImageButton) findViewById(R.id.ibAbrirDrawer))
				.setOnClickListener(this);
		(voltar = (ImageButton) findViewById(R.id.ibVoltar))
				.setOnClickListener(this);

		tx.replace(R.id.main, Fragment.instantiate(MenuContainer.this,
				"mobi.rectour.menuprincipal.gui.MenuGeral"));
		tx.commit();
		statusDrawer = 1;// open

//		AtualizarDadosApp attDados = new AtualizarDadosApp(this);
//		attDados.atualizarVagas();
//		attDados.atualizarPontos();
		
	}

	public void refreshFragment(String fragmentLocal, Bundle parametros,
			boolean btVoltar) {

		tx =   getSupportFragmentManager().beginTransaction();
		
		if (parametros == null) {
			tx.replace(R.id.main,
					Fragment.instantiate(MenuContainer.this, fragmentLocal));
		} else {
			tx.replace(R.id.main, Fragment.instantiate(MenuContainer.this,
					fragmentLocal, parametros));
		}
		
		if ( menuPrincipal) {
			menuPrincipal = false;
		} else {

		}
		
		tx.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	@Override
	public IAsyncTask getTransactionTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initComponentes() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDrawerClosed(View arg0) {
		statusDrawer = 1;

	}

	@Override
	public void onDrawerOpened(View arg0) {
		statusDrawer = 2;

	}

	@Override
	public void onDrawerSlide(View arg0, float arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDrawerStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibAbrirDrawer:
			
			navList.setAdapter(null);
			navList.setAdapter(alsd);
			

			if (statusDrawer == 1) {
				drawer.openDrawer(ll);
				return;
			}
			drawer.closeDrawer(ll);

			break;
		case R.id.ibVoltar:
			configurarBtMenu();
			super.onBackPressed();
			break;

		default:
			break;
		}

	}

	private void configurarBtVoltar() {
		menu.setVisibility(View.GONE);
		voltar.setVisibility(View.VISIBLE);

	}

	private void configurarBtMenu() {
		menu.setVisibility(View.VISIBLE);
		voltar.setVisibility(View.GONE);

	}

	@Override
	public void onBackPressed() {

		//configurarBtMenu();
		if(!menuPrincipal){
			
			tx  =   getSupportFragmentManager().beginTransaction();
			
			tx.replace(R.id.main, Fragment.instantiate(MenuContainer.this,
					"mobi.rectour.menuprincipal.gui.MenuGeral"));
			tx.commit();
			   
			menuPrincipal = true;
		}else{
			super.onBackPressed();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		//RecTourVariaveisGlobais.atualizarTabelasEntrarApp = true;
 		
	}

	public boolean isBtPress() {
		return btPress;
	}

	public void setBtPress(boolean btPress) {
		this.btPress = btPress;
	}

	public void setTitulo(String titulo){
		tvTituloGeral.setText(titulo);
	}
	
}
