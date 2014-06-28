package mobi.rectour.util;

import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

public abstract class BaseFragmentActivity extends FragmentActivity{
	
	public interface IAsyncTask {
		public void preExecute(Bundle parametro);
		public void execute(Bundle parametro) throws Exception;
		public void updateView(Bundle parametro);
	}
	
	


	
	private TransactionTask task;
	private boolean primeira;
	
	
	public Status asyncTaskStatus(){
		return task.getStatus();
		
		
	}
	public TransactionTask getTransactionTaskClass(){
		return task;
	}
	
	public boolean getIsCancelled(){
		return task.isCancelled();
	}
	
	public abstract IAsyncTask getTransactionTask();
	
	public abstract void initComponentes();

	protected void startTask(Bundle b) {
		
		task = new TransactionTask(getTransactionTask());
		primeira = true;
		
		
		if(b == null){
			b = new Bundle();
		}
		task.setParametros(b);
		if(asyncTaskStatus().equals(AsyncTask.Status.FINISHED)|| primeira){
			task.execute();
			primeira = false;
		}
	}
	
	public class TransactionTask extends AsyncTask<Void, Void, Boolean> {
		private static final String TAG = "MobiTaxi";
		
		private final IAsyncTask transaction;
		private Bundle parametros;
		
				
		public TransactionTask(IAsyncTask transaction) {
			//this.context = context;
			this.transaction = transaction;
			
		}
		
		@Override
		// Antes de executar, vamos exibir uma janela de progresso
		protected void onPreExecute() {
			transaction.preExecute(parametros);
		}
		
		// Busca os Tempos em segundo plano dentro da thread
		protected Boolean doInBackground(Void... params) {
			
			try {
				transaction.execute(parametros);
				return true;
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
				
			} finally {
			}
			return false;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			
			
			try {
				if(result) {
					
				}
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
				
			} finally {
				transaction.updateView(parametros);
			}
		}
		
		public Bundle getParametros() {
			return parametros;
		}

		public void setParametros(Bundle parametros) {
			this.parametros = parametros;
		}
		
		/*private void showProgress() {
			try {
				progresso = ProgressDialog.show(context, getString(R.string.app_name),"Aguarde...",true,true);
			} catch (Exception e) {
				Log.e(TAG, "showProgress(): " + e.getMessage());
			}
		}
		private void hideProgress() {
			try {
				if(progresso != null) {
					progresso.dismiss();
					progresso = null;
				}
			} catch (Exception e) {
				Log.e(TAG, "hideProgress(): " + e.getMessage());
			}
		}*/
	}

}
