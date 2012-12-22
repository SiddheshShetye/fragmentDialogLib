package com.sid.fragmentdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.sid.dialoginterface.ViewDialogInterface;

/**
 * The Class ViewDialogFragment.
 * This clas gives a fragment dialog with users custome view
 * @author Siddhesh
 */
public class ViewDialogFragment extends DialogFragment{

	/** The m context. */
	static Context mContext;
	
	/** The ViewDialogInterface object */
	static ViewDialogInterface interfaceDialog;
	
	/** The title bar flag. */
	boolean isTitleBarVisible;
	
	/** The title bar text. */
	String mTitle;
	
	/** The anim for dialog. */
	Integer mAnim;
	
	/**
	 * New instance.
	 *
	 * @param ctx the ctx
	 * @param view the view
	 * @param style the style
	 * @param theme the theme
	 * @param viewDialogInterface the view dialog interface
	 * @param cancelable the cancelable
	 * @return the view dialog fragment
	 */
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


	/**
	 * New instance.
	 *
	 * @param ctx the ctx
	 * @param view the view
	 * @param viewDialogInterface the view dialog interface
	 * @return the view dialog fragment
	 */
	public static ViewDialogFragment newInstance(Context ctx,Integer view,ViewDialogInterface viewDialogInterface) {
		mContext=ctx;
		interfaceDialog=viewDialogInterface;
		Bundle args = new Bundle();
		args.putInt("view", view);
		ViewDialogFragment frag = new ViewDialogFragment();
		frag.setArguments(args);

		return frag;

	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(!isTitleBarVisible)
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

	/**
	 * Sets the dialog title.
	 *
	 * @param isTitleBarVisible the is title bar visible
	 * @param title the title
	 */
	public void setDialogTitle(boolean isTitleBarVisible,String title){
		this.isTitleBarVisible=isTitleBarVisible;
		mTitle=title;
	}
	
	/**
	 * Sets the dialog animation.
	 *
	 * @param animation the new dialog animation
	 */
	public void setDialogAnimation(Integer animation){
		mAnim=animation;
	}
}
