/*
 * Copyright (C) 2013 Siddhesh S Shetye 
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
package com.commonsdroid.fragmentdialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.commonsdroid.dialoginterface.ViewDialogListener;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * The Class ViewDialogFragment.
 * This class gives a fragment dialog with users custom view
 * @author Siddhesh S Shetye
 * @version 2013.2801
 * @since 1.0
 */
@SuppressLint("ValidFragment")
public class ViewDialogFragment extends DialogFragment{

	/** The ViewDialogInterface object */
	private ViewDialogListener interfaceDialog;

	/** The identifier. */
	private int identifier;

	/** The map fragment. */
	private SupportMapFragment fragment;

	/** The Constant VIEW. */
	private static final String VIEW="view",THEME="theme",STYLE="style",IDENTIFIER="identifier",
			CANCELABLE="cancelable",TITLE_BAR_VISIBLE="title_bar_visible",TITLE="title",ANIMATION="animation",
			BUNDLE="bundle";

	private static final String WIDTH = "width";
	private static final String HEIGHT = "height";
	private static final String MAP = "map";
	
	private static DialogStateListener stateListener;

	/**
	 * New instance.
	 *
	 * @param ctx the context
	 * @param view the view
	 * @param viewDialogInterface the view dialog interface
	 * @return the view dialog fragment
	 */
	public static ViewDialogFragment newInstance(final Builder builder){//Integer view,ViewDialogListener viewDialogListener,int identifier) {
		Bundle args = new Bundle();
		stateListener = builder.listener;
		args.putInt(VIEW, builder.view);
		args.putInt(IDENTIFIER, builder.identifier);
		args.putBoolean(CANCELABLE, builder.isCancelable);
		args.putInt(STYLE, builder.style);
		args.putInt(THEME, builder.theme);
		args.putBoolean(TITLE_BAR_VISIBLE, builder.isTitleBarVisible);
		args.putString(TITLE, builder.mTitle);
		args.putInt(ANIMATION, builder.mAnim);
		args.putBundle(BUNDLE, builder.bundle);
		args.putInt(WIDTH, builder.width);
		args.putInt(HEIGHT, builder.height);
		args.putInt(MAP, builder.mContainer);
		ViewDialogFragment frag = new ViewDialogFragment();
		frag.setArguments(args);

		return frag;

	}
	
	


	private ViewDialogFragment() {
		fragment = new SupportMapFragment();
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle bundle=getArguments();
//		LayoutParams params = getDialog().getWindow().getAttributes(); 
//	    params.height = bundle.getInt(HEIGHT); //fixed height
//	    params.width = bundle.getInt(WIDTH); //fixed width
//	    params.alpha = 1.0f;
//	    params.dimAmount = 0.5f;
	    if(bundle.getInt(WIDTH) != 0 && bundle.getInt(HEIGHT) != 0)
	    	getDialog().getWindow().setLayout(bundle.getInt(WIDTH), bundle.getInt(HEIGHT));
	    else{
	    	Log.d("CHECK DIALOG", "0 0");
	    }
//	    getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params); 
	    
		
		if(!bundle.getBoolean(TITLE_BAR_VISIBLE,true))
			getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		else if(bundle.getString(TITLE) != null)
			getDialog().setTitle(bundle.getString(TITLE));	
		if(bundle.getInt(ANIMATION) != 0)
			getDialog().getWindow().getAttributes().windowAnimations = bundle.getInt(ANIMATION);
		if(bundle.getInt(STYLE) != 0 && bundle.getInt(THEME) != 0)
			setStyle(bundle.getInt(STYLE), bundle.getInt(THEME));

		setCancelable(bundle.getBoolean(CANCELABLE));
		
		if(bundle.getInt(MAP) > 0 ){
			FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
			transaction.add(bundle.getInt(MAP), fragment).commit();
		}

		int v=bundle.getInt(VIEW);
		View view=inflater.inflate(v, null);
		identifier = bundle.getInt(IDENTIFIER);
		interfaceDialog.getView(identifier, view, getDialog());
		
		return view;
	}

	public Bundle getBundle(){
		return getArguments().getBundle(BUNDLE);
	}

	/**
	 * Sets the interface dialog.
	 *
	 * @param interfaceDialog the interface dialog
	 * @return the view dialog fragment
	 */
	public ViewDialogFragment setInterfaceDialog(ViewDialogListener interfaceDialog) {
		this.interfaceDialog = interfaceDialog;
		return this;
	}
	
	public SupportMapFragment getSupportMap(){
		return ViewDialogFragment.this.fragment;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if(stateListener != null){
			stateListener.onDialogResume(identifier);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if(stateListener != null){
			stateListener.onDialogPause(identifier);
		}
	}
	
	public interface DialogStateListener{
		public void onDialogPause(int identifier);
		public void onDialogResume(int identifier);
	}


	/**
	 * The Class Builder.
	 * This class builds a custom alert dialog for user.
	 * @author Siddhesh S Shetye
	 * @version 2013.2801
	 * @since 1.0
	 */
	public static class Builder{

		/** The view. */
		private Integer view,mAnim=0;

		/** The theme. */
		private int theme = 0,style = 0;

		/** The view dialog listener. */
		private ViewDialogListener viewDialogListener;

		/** The identifier. */
		private int identifier;

		/** The is title bar visible. */
		private boolean isTitleBarVisible=true,isCancelable=true;

		/** The m title. */
		private String mTitle;

		/** The bundle. */

		private Bundle bundle;

		private int width;

		private int height;

		private int mContainer;
		
		private SupportMapFragment mMapFragment;
		
		private DialogStateListener listener;

		/**
		 * Instantiates a new builder.
		 *
		 * @param view the view
		 * @param viewDialogListener the view dialog listener
		 * @param identifier the identifier
		 */
		public Builder(Integer view,ViewDialogListener viewDialogListener,int identifier) {
			this.view=view;
			this.viewDialogListener=viewDialogListener;
			this.identifier=identifier;
		}

		/**
		 * Sets the dialog title bar.
		 *
		 * @param isTitleBarVisible the is title bar visible
		 * @return the builder
		 */
		public Builder setDialogTitleBar(boolean isTitleBarVisible){
			this.isTitleBarVisible=isTitleBarVisible;
			return this;
		}

		/**
		 * Sets the dialog title.
		 *
		 * @param title the title
		 * @return the builder
		 */
		public Builder setDialogTitle(String title){
			mTitle=title;
			return this;
		}

		/**
		 * Sets the is cancelable.
		 *
		 * @param cancelable the cancelable
		 * @return the builder
		 */
		public Builder setIsCancelable(boolean cancelable){
			isCancelable=cancelable;
			return this;
		}

		/**
		 * Sets the style.
		 *
		 * @param style the style
		 * @param theme the theme
		 * @return the builder
		 */
		public Builder setStyle(int style,int theme){
			this.style=style;
			this.theme=theme;
			return this;
		}

		/**
		 * Sets the dialog animation.
		 *
		 * @param animation the animation
		 * @return the builder
		 */
		public Builder setDialogAnimation(Integer animation){
			mAnim=animation;
			return this;
		}

		/**
		 * Sets the bundle.
		 *
		 * @param bundle the bundle
		 * @return the builder
		 */
		public Builder setBundle(Bundle bundle){
			this.bundle=bundle;
			return this;
		}
		
		/**
		 * Sets the dimens.
		 *
		 * @param width the width
		 * @param height the height
		 * @return the builder
		 */
		public Builder setDimens(int width,int height){
			this.width = width;
			this.height = height;
			return this;
		}
		
		/**
		 * Adds the support map to specified container.
		 *
		 * @param container the container
		 * @return the builder
		 */
		public Builder addSupportMap(int container){
			mContainer = container;
			return this;
		}
		
		/**
		 * Gets the support map.<br/>Use this method after build
		 *
		 * @return the support map
		 */
		public SupportMapFragment getSupportMap(){
			return mMapFragment;
		}
		
		/**
		 * Sets the dialog state listener.
		 *
		 * @param listener the new dialog state listener
		 */
		public Builder setDialogStateListener(DialogStateListener listener){
			this.listener = listener;
			return this;
		}

		/**
		 * Builds the custom dialog according to specified options.
		 *
		 * @param fm the fragment manager
		 * @param tag the tag
		 */
		public void build(FragmentManager fm,String tag){
			ViewDialogFragment frag = newInstance(this);
			mMapFragment = frag.getSupportMap();
			frag.setInterfaceDialog(viewDialogListener).show(fm, tag);
		}
		
		
	}

}
