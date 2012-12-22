package com.sid.fragmentdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.sid.dialoginterface.ViewDialogInterface;


public class ViewDialogFragment extends DialogFragment{
	
	static Context mContext;
	static ViewDialogInterface interfaceDialog;
	boolean mTitleBar;
	String mTitle;
	Integer mAnim;
public static ViewDialogFragment newInstance(Context ctx,Integer view,int style,Integer theme,ViewDialogInterface viewDialogInterface,boolean cancelable) {
		mContext=ctx;
		interfaceDialog=viewDialogInterface;
		Bundle args = new Bundle();
		args.putInt("view", view);
		args.putInt("theme", theme);
		args.putInt("style", style);
		ViewDialogFragment frag = new ViewDialogFragment();
		frag.setArguments(args);
		
		return frag;
		
	}


public static ViewDialogFragment newInstance(Context ctx,Integer view,ViewDialogInterface viewDialogInterface) {
	mContext=ctx;
	interfaceDialog=viewDialogInterface;
	Bundle args = new Bundle();
	args.putInt("view", view);
	ViewDialogFragment frag = new ViewDialogFragment();
	frag.setArguments(args);
	
	return frag;
	
}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(!mTitleBar)
			getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		else if(mTitle!=null)
			getDialog().setTitle(mTitle);	
		if(mAnim!=null)
		getDialog().getWindow().getAttributes().windowAnimations=mAnim;
		Bundle bundle=getArguments();
		int v=bundle.getInt("view");
		View view=inflater.inflate(v, null);
		if(interfaceDialog!=null)
		interfaceDialog.getView(view);
		return view;
	}
	
	public void setDialogTitle(boolean isTitleBarVisible,String title){
		mTitleBar=isTitleBarVisible;
		mTitle=title;
	}
	public void setDialogAnimation(Integer animation){
		mAnim=animation;
	}
}
