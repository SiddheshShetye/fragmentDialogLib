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


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.sid.dialoginterface.AlertButtonsClickInterface;

/**
 * The Class AlertFragmentDialog.
 * This class shows Alert Dialog according to passed type.
 * @author Siddhesh
 */
public final class AlertFragmentDialog extends DialogFragment{

	/** The m context. */
	private static Context mContext;

	/** The Constant TYPE. */
	private static final String TITLE="title",MESSAGE="message",POSITIVE="positive",NEGATIVE="negative",TYPE="type";


	/** The alert fragment. */
	private static AlertButtonsClickInterface sAlertFragment;

	/** Various Response codes and Constants. */
	public static final int DIALOG_TYPE_OK=988,/**Dialog with Positive button*/
			DIALOG_TYPE_YES_NO=888,/**Dialog with Positive & Negative button*/
			DIALOG_VIEW=654,/**Dialog with Custom View*/
			COLOR_DIALOG=214;/**Dialog with Colored Tint*/

	
	private static Integer mView,/** The View. */
							mStyle;/** The Style. */

	/**
	 * New instance of alert dialog.
	 *
	 * @param title the title for dialog
	 * @param message the message for dialog
	 * @param alert the AlertButtonsClickInterface object
	 * @param type the type of dialog
	 * @param positiveText the positive text
	 * @param negativeText the negative text
	 * @return the fragment dialog
	 * @author Siddhesh
	 */
	public static AlertFragmentDialog newInstance(int title,int message,AlertButtonsClickInterface alert,int type,String positiveText,String negativeText) {
		AlertFragmentDialog frag = new AlertFragmentDialog();
		sAlertFragment=alert;
		Bundle args = new Bundle();
		args.putInt(TITLE, title);
		args.putInt(MESSAGE, message);
		args.putInt(TYPE, type);
		args.putString(POSITIVE,positiveText);
		args.putString(NEGATIVE, negativeText);
		frag.setArguments(args);
		return frag;
	}

	/**
	 * New instance.
	 *
	 * @param ctx the ctx
	 * @param style the style
	 * @param view the view
	 * @param type the type
	 * @return the alert fragment dialog
	 */
	public static AlertFragmentDialog newInstance(Context ctx,Integer style,Integer view,int type) {

		mContext=ctx;
		mStyle=style;
		mView=view;
		Bundle args = new Bundle();
		args.putInt("type", type);
		AlertFragmentDialog frag = new AlertFragmentDialog();
		frag.setArguments(args);

		return frag;

	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.DialogFragment#onCreateDialog(android.os.Bundle)
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int title = getArguments().getInt(TITLE,0);
		int message = getArguments().getInt(MESSAGE,0);
		int type = getArguments().getInt(TYPE);
		String positive=getArguments().getString(POSITIVE);
		if(positive==null){
			positive=getResources().getString(R.string.ok);
		}
		String negative=getArguments().getString(NEGATIVE);
		if(negative==null){
			negative=getResources().getString(R.string.cancel);
		}
		switch(type){
		case DIALOG_TYPE_OK:
			return new AlertDialog.Builder(getActivity())
			.setTitle(title)
			.setMessage(message)
			.setPositiveButton(positive,
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					dialog.dismiss();
					if(sAlertFragment!=null)
						sAlertFragment.onPositiveButtonClick();

				}
			}
					)

					.create();
		case DIALOG_TYPE_YES_NO:
			return new AlertDialog.Builder(getActivity())
			.setTitle(title)
			.setMessage(message)
			.setPositiveButton(positive,
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					dialog.dismiss();
					if(sAlertFragment!=null)
						sAlertFragment.onPositiveButtonClick();

				}
			}
					)
					.setNegativeButton(negative,
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							dialog.dismiss();
							if(sAlertFragment!=null)
								sAlertFragment.onNegativeButtonClick();

						}
					}
							)
							.create();

		case DIALOG_VIEW:
			/*AlertDialog.Builder al=new AlertDialog.Builder(getActivity())
    		 //.setTitle(title)
             .setView(mView)
             .setPositiveButton(R.string.ok,
                 new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int whichButton) {
                     	 dialog.dismiss();
                     	if(alertFragment!=null)
                     		alertFragment.onPositiveButtonClick();

                     }
                 }
             )
             .setNegativeButton(R.string.cancel,
                 new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int whichButton) {
                     	dialog.dismiss();
                     	if(alertFragment!=null)
                     		alertFragment.onNegativeButtonClick();

                     }
                 }
             );
    		AlertDialog ald=al.create();*/
			final Dialog dialog = new Dialog(mContext,mStyle);
			dialog.setContentView(mView);
			//dialog.setTitle("Title...");
			return  dialog;

		case COLOR_DIALOG:

		}
		return null;
	}







}
