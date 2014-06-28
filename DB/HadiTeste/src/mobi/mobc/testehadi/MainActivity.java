package mobi.mobc.testehadi;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	Repositorio repository;
	long i = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
		repository = new Repositorio(getApplicationContext());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
	
		String msg = "";
		
		switch (v.getId()) {
		case R.id.button1:
			
			i++;
			Estacao estacao = new Estacao();
			estacao.setId(i);
			estacao.setNome("Estacao" + i);
			
			try {
				repository.insertEstacao(estacao);
				msg = String.format("Dados salvos: %s , %s", estacao.getId(), estacao.getNome());
			} catch (Exception e) {
				msg = e.getMessage();
			}
				
			break;
			
		case R.id.button2:
			
			msg = "Dados Recuperados\n";
			List<Estacao> list  = repository.getEstacao();
			for (Estacao estacao2 : list) {
				msg += String.format("\n%s , %s", estacao2.getId(), estacao2.getNome());
			}
 			
			break;
			

		default:
			break;
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Teste");
		builder.setMessage(msg);
		builder.show();
		
		
	}

}
