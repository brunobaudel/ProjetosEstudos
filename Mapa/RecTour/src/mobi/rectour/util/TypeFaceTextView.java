package mobi.rectour.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TypeFaceTextView extends TextView {

	public TypeFaceTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public TypeFaceTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TypeFaceTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		
//		Typeface fonteTexto = Typeface.createFromAsset(this.getContext().getAssets(), "fonts/signikalight.ttf"); 
//		setTypeface(fonteTexto);
	}

}
