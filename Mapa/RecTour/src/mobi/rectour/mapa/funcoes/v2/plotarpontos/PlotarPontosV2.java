package mobi.rectour.mapa.funcoes.v2.plotarpontos;

import java.util.ArrayList;
import java.util.List;

import mobi.rectour.mapa.funcoes.FuncoesMenu.IFuncoesMenu;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlotarPontosV2 implements IFuncoesMenu{
	
	public interface IConstruirPontos{
		
		MarkerOptions getMarkeOptions(LatLng local,Cursor cursor );
	}
	
	private Cursor cursor;
	//private RepositorioLocalizacao rl;
	private GoogleMap mMap;
	private Context c ;
	private List<Marker> listMarker = new ArrayList<Marker>();

	private CameraUpdate cu;
	
	private IConstruirPontos iConstruirPontos;
	
	public void setIConstruirPontos(IConstruirPontos icp){
		this.iConstruirPontos = icp;
	}
	
	public PlotarPontosV2(Cursor c,Context context){
		this.cursor = c;
		this.c = context;
	}
	
	
	public void mostrarTodosMarkesMapa( GoogleMap mMap){
		
		mMap.animateCamera(cu);
	}
	
	@Override
	public void execute(Object o) {
		
		if(iConstruirPontos != null){
			mMap = (GoogleMap) o;
			
			new PlotarAsync().execute();
		}
	}

	@Override
	public void cancel(Object o) {
		// TODO Auto-generated method stub
		
		if(o instanceof Integer){
			
			Integer key  = (Integer) o;
			
			switch (key) {
			case 1:
				
				for (int i = 0; i < listMarker.size(); i++) {
					listMarker.get(i).remove();
				}
				
				break;

			default:
				break;
			}
		}
	}
	
	public Cursor getCursor() {
		return cursor;
	}

	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	private class PlotarAsync extends AsyncTask<Void, Void, Void>{
		
		private List<MarkerOptions> lMarker = new ArrayList<MarkerOptions>();
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
		
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			
			while (cursor.moveToNext()) {
				
				LatLng pontoPlotar = new LatLng( cursor.getDouble(cursor.getColumnIndex("rtLatitude")),cursor.getDouble(cursor.getColumnIndex("rtLongitude")));
			    
				lMarker.add(iConstruirPontos.getMarkeOptions(pontoPlotar, cursor)  );
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			LatLngBounds.Builder builder = new LatLngBounds.Builder();
			
			for (int i = 0; i < lMarker.size(); i++) {
				
				Marker marker = mMap.addMarker(lMarker.get(i));
				listMarker.add(marker );
				builder.include(marker.getPosition());
			}
			
			LatLngBounds bounds = builder.build();
			int padding = 50; // offset from edges of the map in pixels
			cu = CameraUpdateFactory.newLatLngBounds(bounds,70);
			
			
		}
		private Bitmap getBitmapMarker(int vagasLivres){
			Bitmap bMapRetorno = null;
			
			if (vagasLivres <= 0) {
				//bMapRetorno = drawTextToBitmap(mobi.mobc.portoleveutimate.R.drawable.icon_mapa_vermelho,vagasLivres + "");
			} else if (vagasLivres <= 2) {
				//bMapRetorno = drawTextToBitmap(mobi.mobc.portoleveutimate.R.drawable.icon_mapa_amarelo, vagasLivres + "");
			} else if (vagasLivres >= 3) {
					
				//bMapRetorno = drawTextToBitmap(mobi.mobc.portoleveutimate.R.drawable.icon_mapa_verde, vagasLivres + "");
			}
			return bMapRetorno;
		}
		
		public Bitmap drawTextToBitmap(int gResId, String gText) {
			  Resources resources = c.getResources();
			  
			  //float scale = resources.getDisplayMetrics().density;
			  Bitmap bitmap = BitmapFactory.decodeResource(resources, gResId);
			  
			  android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();
			  // set default bitmap config if none
			  if(bitmapConfig == null) {
			    bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
			  }
			  // resource bitmaps are imutable, 
			  // so we need to convert it to mutable one
			  bitmap = bitmap.copy(bitmapConfig, true);
			 
			  Canvas canvas = new Canvas(bitmap);
			  // new antialised Paint
			  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			  // text color - #3D3D3D
			  paint.setColor(Color.rgb(61, 61, 61));
			  // text size in pixels
			  paint.setTextSize((int) (19 ));
			  // text shadow
			  paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
			 
			  // draw text to the Canvas center
			  Rect bounds = new Rect();
			  paint.getTextBounds(gText, 0, gText.length(), bounds);
			  
			  
			  int x = (bitmap.getWidth() - bounds.width())/2;
			  
			  int y = ((bitmap.getHeight() + bounds.height() )/2) - ((bitmap.getHeight() + bounds.height() )/2)/4 ;
			 			  
			 // canvas.drawText(gText, x * scale, y * scale, paint);
			  canvas.drawText(gText, x, y, paint);
			  return bitmap;
			}
	}
}
