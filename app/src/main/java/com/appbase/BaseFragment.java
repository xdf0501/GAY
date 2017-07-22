package com.appbase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


public class BaseFragment extends Fragment {
	public boolean isDetachState=true;
	private AnimationListener listener=null;
	public void toastShow(String text){
		if (getActivity()!=null) {
			Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
		}
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		isDetachState=false;
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	//only can listen custom animation
	@Override
	public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
		// TODO Auto-generated method stub
		Animation anim = null;
		if (nextAnim>0) {
			anim = AnimationUtils.loadAnimation(getActivity(), nextAnim);
			if (anim!=null) {
				anim.setAnimationListener(listener);
			 }
		}
	    return anim;
	}

	public void onResume() {
		super.onResume();
	}
	
	public void onPause() {
		super.onPause();
	}
	
	public void onDestroy() {
		super.onDestroy();
	}
	
	public boolean dispatchKeyEvent(KeyEvent event) {
		return false;
	}
	
	public AnimationListener getAnimationListener() {
		return listener;
	}

	public void setAnimationListener(AnimationListener listenner) {
		this.listener = listenner;
	}
}
