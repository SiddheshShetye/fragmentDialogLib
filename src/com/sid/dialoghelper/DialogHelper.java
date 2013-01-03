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
package com.sid.dialoghelper;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;

import com.sid.dialoginterface.AlertButtonsClickListener;
import com.sid.dialoginterface.ListDialogListener;
import com.sid.dialoginterface.OnDateTimeSetListener;
import com.sid.dialoginterface.OnNumberSetListener;
import com.sid.dialoginterface.ViewDialogListener;
import com.sid.fragmentdialog.AlertFragmentDialog;
import com.sid.fragmentdialog.NumberPickerDialog;
import com.sid.fragmentdialog.ViewDialogFragment;

/**
 * The Class DialogHelper.
 * This class acts as a helper to to show specified dialog
 * @author Siddhesh
 */
public final class DialogHelper {
	
	/** The new fragment. */
	private static ViewDialogFragment sNewFragment;
	private static DialogFragment currentNumberPickerDialog;

	/**
	 * Show dialog. 
	 * Shows a dialog with given title and message with OK button and cancelable true.
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @author Siddhesh
	 */
	public static void showDialog(FragmentManager fm,int title,int message,int identifier){
		showDialog(fm, title, message, null, AlertFragmentDialog.DIALOG_TYPE_OK, true,null,null,identifier);
	}
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with OK button,cancelable true and specified positive
	 * button text.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param positiveText the text for positive button 
	 * @author Siddhesh
	 */
	public static void showDialog(FragmentManager fm,int title,int message,String positiveText,int identifier){
		showDialog(fm, title, message, null, AlertFragmentDialog.DIALOG_TYPE_OK, true,positiveText,null,identifier);
	}
	
	/**
	 * Show dialog. 
	 * Shows a dialog with given title and message with OK button, 
	 * cancelable true and AlertButtonsClickInterface object for invoking 
	 * code on alert button clicks .
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the AlertButtonsClickInterface object
	 * @author Siddhesh
	 */
	public static void showDialog(FragmentManager fm,int title,int message,AlertButtonsClickListener alertFrag,int identifier){
		showDialog(fm, title, message, alertFrag, AlertFragmentDialog.DIALOG_TYPE_OK, true,null,null,identifier);
	}
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with positive button with specified text,
	 * cancelable true and AlertButtonsClickInterface object for invoking
	 * code on alert button clicks .
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the AlertButtonsClickInterface object
	 * @param positiveText the text for positive button 
	 * @author Siddhesh
	 */
	public static void showDialog(FragmentManager fm,int title,int message,AlertButtonsClickListener alertFrag,String positiveText,int identifier){
		showDialog(fm, title, message, alertFrag, AlertFragmentDialog.DIALOG_TYPE_OK, true,positiveText,null,identifier);
	}
	
	/**
	 * Show dialog. 
	 * Shows a dialog with given title and message with OK button, 
	 * Cancelable true, AlertButtonsClickInterface object for invoking 
	 * code on alert button clicks, type OK or OK_Cancel and isCancelable.
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the AlertButtonsClickInterface object
	 * @param type the type
	 * @param cancelable the cancelable
	 * @author Siddhesh
	 */
	public static void showDialog(FragmentManager fm,int title,int message,AlertButtonsClickListener alertFrag,int type,boolean cancelable,int identifier) {
	    DialogFragment newFragment = AlertFragmentDialog.newInstance(title,message,alertFrag,type,null,null,identifier);
	    newFragment.setCancelable(cancelable);
	    newFragment.show(fm, "dialog");
	}
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with positive button with specified 
	 * text & negative button with specified text,
	 * cancelable true, AlertButtonsClickInterface object for invoking
	 * code on alert button clicks, type OK or OK_Cancel and isCancelable.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the AlertButtonsClickInterface object
	 * @param type the type e.g AlertFragmentDialog
	 * @param cancelable the cancelable
	 * @param positiveText the text for positive button
	 * @param negativeText the text for negative button
	 * @author Siddhesh
	 */
	public static void showDialog(FragmentManager fm,int title,int message,AlertButtonsClickListener alertFrag,int type,boolean cancelable,String positiveText,String negativeText,int identifier) {
	    DialogFragment newFragment = AlertFragmentDialog.newInstance(title,message,alertFrag,type,positiveText,negativeText,identifier);
	    newFragment.setCancelable(cancelable);
	    newFragment.show(fm, "dialog");
	}
	
	/**
	 * Gets the view dialog.
	 * this function is used to get the object of AlertFragmentDialog for custome view.
	 * @param fm the fragment manager
	 * @param ctx the context
	 * @param view the view for dialog
	 * @param vd the ViewDialogInterface
	 * @return the inflated view of dialog
	 * @author Siddhesh
	 */
	public static ViewDialogFragment getViewDialog(Integer view,ViewDialogListener viewDialogListener,int identifier){
		sNewFragment = ViewDialogFragment.newInstance(view,viewDialogListener,identifier);
		return sNewFragment;
	}
	
	/**
	 * Shows the date picker dialog.
	 * this function is used to show the date picker dialog.
	 * @param fm the fragment manager
	 * @param ctx the context
	 * @param dateListener the OnDateTimeSetListener
	 * @author Siddhesh
	 */
	public static void showDateDialog(FragmentManager fm,OnDateTimeSetListener dateListener,int identifier){
		DialogFragment newFragment = AlertFragmentDialog.newInstance(true,dateListener,AlertFragmentDialog.DATE_DIALOG,identifier);
		newFragment.show(fm, "dialog");
	}
	
	/**
	 * Shows the time picker dialog.
	 * this function is used to show the time picker dialog.
	 * @param fm the fragment manager
	 * @param ctx the context
	 * @param is24Hour the time should be consider 24 hour or not
	 * @param dateListener the OnDateTimeSetListener
	 * @author Siddhesh
	 */
	public static void showTimeDialog(FragmentManager fm,boolean is24Hour,OnDateTimeSetListener dateListener,int identifier){
		DialogFragment newFragment = AlertFragmentDialog.newInstance(is24Hour,dateListener,AlertFragmentDialog.TIME_DIALOG,identifier);
		newFragment.show(fm, "dialog");
	}
	
	/**
	 * Show number picker dialog.
	 *
	 * @param fm the fragment manager
	 * @param title the title for dialog
	 * @param headerText the header text it can also be kept null if not needed
	 * @param numberListener the number listener
	 * @param lowerRange the lower range
	 * @param upperRange the upper range
	 * @param defaultRange the default value
	 * @param interval the interval
	 * @return the dialog fragment
	 */
	public static DialogFragment showNumberPickerDialog(int title,String headerText,OnNumberSetListener numberListener,int lowerRange,int upperRange,int defaultRange,int interval,int identifier){
		currentNumberPickerDialog = NumberPickerDialog.newInstance(title,headerText,numberListener,lowerRange,upperRange,defaultRange,interval,identifier);
		return currentNumberPickerDialog;
	}
	
	/**
	 * Show simple list dialog.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param list the list
	 * @param listDialogListener the list dialog listener
	 * @param identifier the identifier
	 */
	public static void showSimpleListDialog(FragmentManager fm,int title,ArrayList<String> list,ListDialogListener listDialogListener,int identifier){
		DialogFragment newFragment = AlertFragmentDialog.newInstance(title,list,listDialogListener,AlertFragmentDialog.SIMPLE_LIST_DIALOG,identifier);
		newFragment.show(fm, "dialog");
	}
	
	/**
	 * Show single choice list dialog.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param list the list
	 * @param listDialogListener the list dialog listener
	 * @param identifier the identifier
	 */
	public static void showSingleChoiceListDialog(FragmentManager fm,int title,ArrayList<String> list,ListDialogListener listDialogListener,int identifier){
		DialogFragment newFragment = AlertFragmentDialog.newInstance(title,list,listDialogListener,AlertFragmentDialog.SINGLE_CHOICE_LIST_DIALOG,identifier);
		newFragment.show(fm, "dialog");
	}
	
	/**
	 * Show multiple choice list dialog.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param list the list
	 * @param listDialogListener the list dialog listener
	 * @param identifier the identifier
	 */
	public static void showMultipleChoiceListDialog(FragmentManager fm,int title,ArrayList<String> list,ListDialogListener listDialogListener,int identifier){
		DialogFragment newFragment = AlertFragmentDialog.newInstance(title,list,listDialogListener,AlertFragmentDialog.MULTI_CHOICE_LIST_DIALOG,identifier);
		newFragment.show(fm, "dialog");
	}
	
	
	/**
	 * Show toast for <code>Toast.LENGTH_SHORT</code>.
	 *
	 * @param ctx the context
	 * @param msg the message to show
	 */
	public static void showShortToast(Context ctx,String msg){
		Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Show toast for <code>Toast.LENGTH_LONG</code>.
	 *
	 * @param ctx the context
	 * @param msg the message to show
	 */
	public static void showLongToast(Context ctx,String msg){
		Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
	}
	
	/**
	 * Show custom toast.
	 *
	 * @param context the context
	 * @param view the view
	 */
	public static void showCustomToast(Context context,View view){
		Toast newCustomToast=new Toast(context);
		newCustomToast.setView(view);
		newCustomToast.setDuration(Toast.LENGTH_LONG);
		newCustomToast.show();
	}
	
	/**
	 * Show custom toast.
	 *
	 * @param context the context
	 * @param view the view
	 * @param Duration the duration e.g <code>Toast.LENGTH_SHORT</code> , <code>Toast.LENGTH_LONG</code>
	 */
	public static void showCustomToast(Context context,View view,int Duration){
		Toast newCustomToast=new Toast(context);
		newCustomToast.setView(view);
		newCustomToast.setDuration(Duration);
		newCustomToast.show();
	}
	
	/**
	 * Show custom toast.
	 *
	 * @param context the context
	 * @param view the view
	 * @param gravity the gravity e.g <code>GRAVITY.BOTTOM</code> , <code>GRAVITY.CENTER_VERTICAL</code> , etc
	 * @param xoffset the x offset
	 * @param yoffset the y offset
	 */
	public static void showCustomToast(Context context,View view,int gravity,int xoffset,int yoffset){
		Toast newCustomToast=new Toast(context);
		newCustomToast.setView(view);
		newCustomToast.setDuration(Toast.LENGTH_LONG);
		newCustomToast.setGravity(gravity, xoffset, yoffset);
		newCustomToast.show();
	}
	
	/**
	 * Show custom toast.
	 *
	 * @param context the context
	 * @param view the view
	 * @param Duration the duration e.g <code>Toast.LENGTH_SHORT</code> , <code>Toast.LENGTH_LONG</code>
	 * @param gravity the gravity e.g <code>GRAVITY.BOTTOM</code> , <code>GRAVITY.CENTER_VERTICAL</code> , etc
	 * @param xoffset the x offset
	 * @param yoffset the y offset
	 */
	public static void showCustomToast(Context context,View view,int Duration,int gravity,int xoffset,int yoffset){
		Toast newCustomToast=new Toast(context);
		newCustomToast.setView(view);
		newCustomToast.setDuration(Duration);
		newCustomToast.setGravity(gravity, xoffset, yoffset);
		newCustomToast.show();
	}
	
	
	
}
