package com.appbase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
	public abstract interface onSetToolBar{
		public abstract void onToolBar(final Toolbar toolbar);
	}
	private FrameLayout rootLayout=null;
	private boolean customFlag=false;

	public void toastShow(String text){
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}
	public void initInjectedView(Activity activity){
		try {
			if (rootLayout==null) {
				View view=getWindow().getDecorView().getRootView();
				if (view instanceof FrameLayout) {
					rootLayout = (FrameLayout)view;
				}
				useDefaultBackColor();
			}

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//				// create our manager instance after the content view is set
//				SystemBarTintManager tintManager = new SystemBarTintManager(this);
//				// enable status bar tint
//				tintManager.setStatusBarTintEnabled(true);
//				// enable navigation bar tint
//				tintManager.setNavigationBarTintEnabled(true);
//				tintManager.setTintColor(Color.parseColor("#99000FF"));
//				tintManager.setStatusBarTintResource(Res.color.colorPrimary);
//				tintManager.setStatusBarTintColor(Color.RED);
//				//tintManager.setStatusBarTintDrawable(getDrawable(Res.color.colorPrimary));
//				//tintManager.setNavigationBarTintResource(Res.color.colorPrimary);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public FrameLayout getRootLayout() {
		return rootLayout;
	}
	
	public Activity getActivity(){
		return this;
	}
	
	public Context getContext(){
		return this;
	}
	
    public void closeActivity() {
    	this.finish();
	}

    public void closeActivity(int resultCode) {
    	setResult(resultCode);
    	closeActivity();
	}
	public void closeActivity(int resultCode,Intent intent) {
		if (intent!=null) {
			setResult(resultCode, intent);
			closeActivity();
		}else {
			closeActivity(resultCode);
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}
	
	public void setContentView(int layoutResID) {
		try {
			if (this.customFlag) {
			}else{
				super.setContentView(layoutResID);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		initInjectedView(this);
	}
	
	
	public void setContentView(View view, ViewGroup.LayoutParams params) {
		try {
			if (this.customFlag) {
		
			}else{
				super.setContentView(view, params);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		initInjectedView(this);
	}
	
	
	public void setContentView(View view) {
		try {
			if (this.customFlag) {
				
			}else{
				super.setContentView(view);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		initInjectedView(this);
	}

	public void setToolBar(final int resid,final onSetToolBar callBack){
//		Toolbar toolbar=null;
//		View view=findViewById(resid);
//		if (view!=null&&view instanceof Toolbar) {
//			toolbar = (Toolbar) view;
//			setSupportActionBar(toolbar);
//		}
//		if (callBack!=null){
//			callBack.onToolBar(toolbar);
//		}
		setToolBar(resid, 0, callBack);
	}

	/**
	 * @param resid     toolbar的resid
	 * @param layoutId  自定义toolbar的layout
	 * @param callBack  回调中设置toolbar
	 */
	public void setToolBar(final int resid,final int layoutId,final onSetToolBar callBack){
		Toolbar toolbar=null;
		View view=findViewById(resid);
		if (view!=null&&view instanceof Toolbar) {
			toolbar = (Toolbar) view;
			setSupportActionBar(toolbar);
			toolbar.showOverflowMenu();
			if (layoutId>0){
				//判断layout是否存在，如有其它判断方式当替换
				View v=View.inflate(getActivity(),layoutId,null);
				if (v!=null) {
					//layout自定义toolbar
					getLayoutInflater().inflate(layoutId, toolbar);
				}
			}
		}
		if (callBack!=null){
			callBack.onToolBar(toolbar);
		}
	}
//	public void setToolElseExample(){
//		// App Logo
//		//toolbar.setLogo(Res.drawable.ic_launcher);
//		// Title
//		//toolbar.setTitle("My Title");
//		// Sub Title
//		//toolbar.setSubtitle("Sub title");
//	}
	public void setToolBarNavigation(@Nullable final Toolbar toolbar, final int resid, View.OnClickListener listener){
		// Navigation Icon 要設定在 setSupoortActionBar 才有作用
		// 否則會出現 prev button
		toolbar.setNavigationIcon(resid);
		toolbar.setNavigationOnClickListener(listener);
	}
	public void setToolBarMenu(@Nullable final Toolbar toolbar, final int resid, Toolbar.OnMenuItemClickListener listener){
		// Menu item click 的監聽事件一樣要設定在 setSupportActionBar后 才有作用
		toolbar.setOnMenuItemClickListener(listener);
		toolbar.inflateMenu(resid);
	}

	public void showActionBar(boolean showFlag){
		ActionBar bar= getSupportActionBar();
		if (bar!=null){
			if (showFlag){
				bar.show();
			}else{
				bar.show();
			}
		}
	}

	public void useDefaultBackColor(){
		//getRootLayout().setBackgroundResource(Res.color.activity_gray_back);
		//getRootLayout().setBackgroundResource(Res.mipmap.bac);
	}
//
//	public void setActionBar(int resid){
//		View view=View.inflate(getActivity(),resid,null);
//		if (view!=null){
//			setActionBar(view);
//		}
//	}
//
//	public void setActionBar(@Nullable View view){
//		final ActionBar actionBar = getSupportActionBar();
//		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//		actionBar.setCustomView(view);
//	}
}
