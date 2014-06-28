package mobi.rectour.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ListView;

public class LvNavegacaoMapa extends ListView {

	public LvNavegacaoMapa(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public LvNavegacaoMapa(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public LvNavegacaoMapa(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override 
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		
		WindowManager wm = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
	   
		int width = display.getWidth();  
		int height = display.getHeight();  
	   
	   this.setMeasuredDimension(width/2, heightMeasureSpec);
	   
	   super.onMeasure(width/2, heightMeasureSpec);
	}
	
	

}
