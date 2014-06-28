package mobi.rectour.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class TouchableWrapper extends FrameLayout {

	public interface onTouchNotification{
		
		void actionDown();
		void actionUP();
		void actionMove();
	}
	
	private onTouchNotification onTouchNotification;
	
	
	public TouchableWrapper(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public TouchableWrapper(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TouchableWrapper(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
				if(onTouchNotification != null) 
					onTouchNotification.actionDown();
					
			break;
		case MotionEvent.ACTION_UP:
			if(onTouchNotification != null) 
				onTouchNotification.actionUP();
			
			break;
		
		case MotionEvent.ACTION_MOVE :
			if(onTouchNotification != null) 
				onTouchNotification.actionMove();
			
			break;
		}

		return super.dispatchTouchEvent(event);
	}

	public onTouchNotification getOnTouchNotification() {
		return onTouchNotification;
	}

	public void setOnTouchNotification(onTouchNotification onTouchNotification) {
		this.onTouchNotification = onTouchNotification;
	}

}
