/*
 * Copyright (C) 2010 The Android Open Source Project 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.sid.fragmentdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.sid.dialoginterface.ViewDialogListener;

/**
 * The Class ViewDialogFragment.
 * This clas gives a fragment dialog with users custome view
 * @author Siddhesh
 */
public class ViewDialogFragment extends DialogFragment{

	/** The m context. */
	//private static Context mContext;
	
	/** The ViewDialogInterface object */
	private static ViewDialogListener interfaceDialog;
	
	/** The title bar flag. */
	private boolean isTitleBarVisible;
	
	/** The title bar text. */
	private String mTitle;
	
	/** The anim for dialog. */
	private Integer mAnim;
	
	private View mView;
	
	private int identifier;
	
	private static final String VIEW="view",THEME="theme",STYLE="style",IDENTIFIER="identifier"; 
	
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
	public static ViewDialogFragment newInstance(Context ctx,Integer view,int style,Integer theme,boolean cancelable,ViewDialogListener viewDialogListener,int identifier) {
		//mContext=ctx;
		interfaceDialog=viewDialogListener;
		Bundle args = new Bundle();
		args.putInt(VIEW, view);
		args.putInt(THEME, theme);
		args.putInt(STYLE, style);
		args.putInt(IDENTIFIER, identifier);
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
	public static ViewDialogFragment newInstance(Context ctx,Integer view,ViewDialogListener viewDialogListener,int identifier) {
		interfaceDialog=viewDialogListener;
		Bundle args = new Bundle();
		args.putInt(VIEW, view);
		args.putInt(IDENTIFIER, identifier);
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
		int v=bundle.getInt(VIEW);
		View view=inflater.inflate(v, null);
		mView=view;
		interfaceDialog.getView(identifier, mView);
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
	
	
	/**
	 * Gets the dialog view.
	 *
	 * @return the dialog view
	 */
	/*public View getDialogView(){
		return mView;
	}*/
}
