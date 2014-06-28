package mobi.rectour.util;

import mobi.rectour.util.TouchableWrapper.onTouchNotification;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class SupportMapFragmentRecTour extends SupportMapFragment{

	private onTouchNotification onTouchNotification;
	public TouchableWrapper mTouchView;   
	
	public static interface OnGoogleMapFragmentListener {
        void onMapReady(GoogleMap map);
    }
	
	private OnGoogleMapFragmentListener mCallback;
	
	public OnGoogleMapFragmentListener getmCallback() {
		return mCallback;
	}

	public void setmCallback(OnGoogleMapFragmentListener mCallback) {
		this.mCallback = mCallback;
	}

	
	public static SupportMapFragmentRecTour newInstance() {
        return new SupportMapFragmentRecTour();
    }
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       /* try {
            mCallback = (OnGoogleMapFragmentListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().getClass().getName() + " must implement OnGoogleMapFragmentListener");
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mTouchView = new TouchableWrapper(getActivity());
        mTouchView.addView(view);
        mTouchView.setOnTouchNotification(getOnTouchNotification());
        
        if (mCallback != null) {
            mCallback.onMapReady(getMap());
        }
        return mTouchView;
    }

	public onTouchNotification getOnTouchNotification() {
		return onTouchNotification;
	}

	public void setOnTouchNotification(onTouchNotification onTouchNotification) {
		this.onTouchNotification = onTouchNotification;
	}
	
	
	
}
