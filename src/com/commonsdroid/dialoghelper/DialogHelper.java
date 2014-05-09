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
package com.commonsdroid.dialoghelper;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;

import com.commonsdroid.dialoginterface.AlertButtonsClickListener;
import com.commonsdroid.dialoginterface.ListDialogListener;
import com.commonsdroid.dialoginterface.OnDateTimeSetListener;
import com.commonsdroid.dialoginterface.OnNumberSetListener;
import com.commonsdroid.dialoginterface.ViewDialogListener;
import com.commonsdroid.fragmentdialog.AlertFragmentDialog;
import com.commonsdroid.fragmentdialog.AlertFragmentDialog.Builder;
import com.commonsdroid.fragmentdialog.NumberPickerDialog;
import com.commonsdroid.fragmentdialog.ProgressHUD;
import com.commonsdroid.fragmentdialog.ViewDialogFragment;

/**
 * The Class DialogHelper.
 * This class acts as a helper to to show specified dialog
 * @author Siddhesh S Shetye
 * @since 1.0
 */
public final class DialogHelper {
	
	/** The Constant CANCELABLE and NOT_CANCELABLE. */
	public static final boolean CANCELABLE=true,NOT_CANCELABLE=false;
	
	private static final int DATE_DIALOG=214,/**DATE picker dialog*/
			 TIME_DIALOG=686;

	/** Time picker Dialog. */
	private static ProgressDialog sProgressDialog;
	
	/** The progress dialog. */
	private static ProgressHUD progressDialogHUD;
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with OK button and cancelable true.
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showDialog(FragmentManager fm,int title,int message,int identifier,Context context){
		showDialog(fm, context.getResources().getString(title), context.getResources().getString(message), null, AlertFragmentDialog.DIALOG_TYPE_OK, CANCELABLE,null,null,identifier);
	}
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with OK button and cancelable true.
	 * @param fm the fragment manager
	 * @param title the title text
	 * @param message the message
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showDialog(FragmentManager fm,String title,String message,int identifier){
		showDialog(fm, title, message, null, AlertFragmentDialog.DIALOG_TYPE_OK, CANCELABLE,null,null,identifier);
	}
	
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title,message with OK button,cancelable true and specified positive
	 * button text.
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param positiveText the positive text
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showDialog(FragmentManager fm,int title,int message,String positiveText,int identifier,Context context){
		showDialog(fm, context.getResources().getString(title), context.getResources().getString(message), null, AlertFragmentDialog.DIALOG_TYPE_OK, true,positiveText,null,identifier);
		
	}
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title,message with OK button,cancelable true and specified positive
	 * button text.
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param positiveText the positive text
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showDialog(FragmentManager fm,String title,String message,String positiveText,int identifier){
		showDialog(fm, title, message, null, AlertFragmentDialog.DIALOG_TYPE_OK, true,positiveText,null,identifier);
	}
	
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with OK button, 
	 * cancelable true and <code>AlertButtonsClickInterface</code> object for invoking 
	 * code on alert button clicks .
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the alert AlertButtonsClickInterface
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showDialog(FragmentManager fm,int title,int message,AlertButtonsClickListener alertFrag,int identifier,Context context){
		showDialog(fm, context.getResources().getString(title), context.getResources().getString(message), alertFrag, AlertFragmentDialog.DIALOG_TYPE_OK, true,null,null,identifier);
	}
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with OK button, 
	 * cancelable true and <code>AlertButtonsClickInterface</code> object for invoking 
	 * code on alert button clicks .
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the alert AlertButtonsClickInterface
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showDialog(FragmentManager fm,String title,String message,AlertButtonsClickListener alertFrag,int identifier){
		showDialog(fm, title, message, alertFrag, AlertFragmentDialog.DIALOG_TYPE_OK, true,null,null,identifier);
	}
	
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with positive button with specified text,
	 * cancelable true and <code>AlertButtonsClickInterface</code> object for invoking
	 * code on alert button clicks .
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the alert <code>AlertButtonsClickInterface</code>
	 * @param positiveText the positive text
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showDialog(FragmentManager fm,int title,int message,AlertButtonsClickListener alertFrag,String positiveText,int identifier,Context context){
		showDialog(fm, context.getResources().getString(title), context.getResources().getString(message), alertFrag, AlertFragmentDialog.DIALOG_TYPE_OK, true,positiveText,null,identifier);
	}
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with positive button with specified text,
	 * cancelable true and <code>AlertButtonsClickInterface</code> object for invoking
	 * code on alert button clicks .
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the alert <code>AlertButtonsClickInterface</code>
	 * @param positiveText the positive text
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showDialog(FragmentManager fm,String title,String message,AlertButtonsClickListener alertFrag,String positiveText,int identifier){
		showDialog(fm, title, message, alertFrag, AlertFragmentDialog.DIALOG_TYPE_OK, true,positiveText,null,identifier);
	}
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with OK button, 
	 * Cancelable true, <code>AlertButtonsClickInterface</code> object for invoking 
	 * code on alert button clicks, type OK or OK_Cancel and isCancelable.
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the alert AlertButtonsClickInterface
	 * @param type the type
	 * @param cancelable the cancelable use <code>DialogHelper.CANCELABLE</code> or <code>DialogHelper.NOT_CANCELABLE</code>
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showDialog(FragmentManager fm,String title,String message,AlertButtonsClickListener alertFrag,int type,boolean cancelable,int identifier) {
//	    DialogFragment newFragment = AlertFragmentDialog.newInstance(title,message,alertFrag,type,null,null,identifier);
//	    newFragment.setCancelable(cancelable);
//	    newFragment.show(fm, "dialog");
		new Builder(type, identifier)
		.setTitle(title)
		.setMessage(message)
		.setAlertButtonClickListener(alertFrag)
		.setCancelable(cancelable)
		.build(fm, "");
	}
	
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with positive button with specified 
	 * text & negative button with specified text,
	 * cancelable true, AlertButtonsClickInterface object for invoking
	 * code on alert button clicks, type <code>AlertFragmentDialog.DIALOG_TYPE_OK</code> or <code>AlertFragmentDialog.DIALOG_TYPE_YES_NO</code> and isCancelable.
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the alert AlertButtonsClickInterface
	 * @param type the type
	 * @param cancelable the cancelable use <code>DialogHelper.CANCELABLE</code> or <code>DialogHelper.NOT_CANCELABLE</code>
	 * @param positiveText the positive text
	 * @param negativeText the negative text
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showDialog(FragmentManager fm,String title,String message,AlertButtonsClickListener alertFrag,int type,boolean cancelable,String positiveText,String negativeText,int identifier) {
//	    DialogFragment newFragment = AlertFragmentDialog.newInstance(title,message,alertFrag,type,positiveText,negativeText,identifier);
//	    newFragment.setCancelable(cancelable);
//	    newFragment.show(fm, "dialog");
		new Builder(type, identifier)
		.setTitle(title)
		.setMessage(message)
		.setAlertButtonClickListener(alertFrag)
		.setCancelable(cancelable)
		.setPositiveText(positiveText)
		.setNegativeText(negativeText)
		.build(fm, "");
		
	}
	
	
	/**
	 * Gets the view dialog builder.
	 * This function is used to get the object of <code>AlertFragmentDialog.Builder</code> for custom view.
	 * @param view the view
	 * @param viewDialogListener the view dialog listener
	 * @param identifier the identifier
	 * @return the view dialog builder
	 */
	public static ViewDialogFragment.Builder getViewDialogBuilder(Integer view,ViewDialogListener viewDialogListener,int identifier){
		ViewDialogFragment.Builder viewDialogFragmentBuilder = new ViewDialogFragment.Builder(view,viewDialogListener,identifier);
		return viewDialogFragmentBuilder;
	}
	
	
	/**
	 * Show date dialog.
	 * This function is used to show the date picker dialog.
	 * @param fm the fragment manager
	 * @param dateListener the date listener
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showDateDialog(FragmentManager fm,OnDateTimeSetListener dateListener,int identifier){
//		DialogFragment newFragment = AlertFragmentDialog.newInstance(true,dateListener,DATE_DIALOG,identifier);
//		newFragment.show(fm, "dialog");
		new Builder(DATE_DIALOG, identifier)
		.setIs24Hour(true)
		.setOnDateTimeSetListener(dateListener)
		.build(fm, "");
	}
	
	/**
	 * Show date dialog.
	 * This function is used to show the date picker dialog.
	 * @param fm the fragment manager
	 * @param dateListener the date listener
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showDateDialog(FragmentManager fm,OnDateTimeSetListener dateListener,int identifier,Calendar cal){
//		DialogFragment newFragment = AlertFragmentDialog.newInstance(true,dateListener,DATE_DIALOG,identifier,cal);
//		newFragment.show(fm, "dialog");
		new Builder(DATE_DIALOG, identifier)
		.setIs24Hour(true)
		.setCalendar(cal)
		.setOnDateTimeSetListener(dateListener)
		.build(fm, "");
	}
	
	
	/**
	 * Show time dialog.
	 * This function is used to show the time picker dialog.
	 * @param fm the fragment manager
	 * @param is24Hour the is24 hour
	 * @param dateListener the date listener
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showTimeDialog(FragmentManager fm,boolean is24Hour,OnDateTimeSetListener dateListener,int identifier){
//		DialogFragment newFragment = AlertFragmentDialog.newInstance(is24Hour,dateListener,TIME_DIALOG,identifier);
//		newFragment.show(fm, "dialog");
		new Builder(TIME_DIALOG, identifier)
		.setIs24Hour(is24Hour)
		.setOnDateTimeSetListener(dateListener)
		.build(fm, "");
	}
	
	
	/**
	 * Gets the number picker dialog builder.
	 *
	 * @param title the title
	 * @param headerText the header text
	 * @param numberListener the number listener
	 * @param lowerRange the lower range
	 * @param upperRange the upper range
	 * @param defaultRange the default range
	 * @param interval the interval
	 * @param identifier the identifier
	 * @return the number picker dialog builder
	 */
	public static NumberPickerDialog.Builder getNumberPickerDialogBuilder(int title,String headerText,OnNumberSetListener numberListener,int lowerRange,int upperRange,int defaultRange,int interval,int identifier){
		NumberPickerDialog.Builder numberPickerDialogBuilder =new NumberPickerDialog.Builder(title,headerText,numberListener,lowerRange,upperRange,defaultRange,interval,identifier);
		return numberPickerDialogBuilder;
	}
	
	
	/**
	 * Show simple list dialog.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param list the list
	 * @param listDialogListener the list dialog listener
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showSimpleListDialog(FragmentManager fm,int title,ArrayList<String> list,ListDialogListener listDialogListener,int identifier,Context context){
//		DialogFragment newFragment = AlertFragmentDialog.newInstance(context.getResources().getString(title),list,listDialogListener,AlertFragmentDialog.SIMPLE_LIST_DIALOG,identifier);
//		newFragment.show(fm, "dialog");
		new Builder(AlertFragmentDialog.SIMPLE_LIST_DIALOG, identifier)
		.setTitle(context.getResources().getString(title))
		.setDialogList(list)
		.setListDialogListener(listDialogListener)
		.build(fm, "");
	}
	
	/**
	 * Show simple list dialog.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param list the list
	 * @param listDialogListener the list dialog listener
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showSimpleListDialog(FragmentManager fm,String title,ArrayList<String> list,ListDialogListener listDialogListener,int identifier){
//		DialogFragment newFragment = AlertFragmentDialog.newInstance(title,list,listDialogListener,AlertFragmentDialog.SIMPLE_LIST_DIALOG,identifier);
//		newFragment.show(fm, "dialog");
		new Builder(AlertFragmentDialog.SIMPLE_LIST_DIALOG, identifier)
		.setTitle(title)
		.setDialogList(list)
		.setListDialogListener(listDialogListener)
		.build(fm, "");
	}
	
	
	/**
	 * Show single choice list dialog.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param list the list
	 * @param listDialogListener the list dialog listener
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showSingleChoiceListDialog(FragmentManager fm,int title,ArrayList<String> list,ListDialogListener listDialogListener,int identifier,Context context){
//		DialogFragment newFragment = AlertFragmentDialog.newInstance(context.getResources().getString(title),list,listDialogListener,AlertFragmentDialog.SINGLE_CHOICE_LIST_DIALOG,identifier);
//		newFragment.show(fm, "dialog");
		new Builder(AlertFragmentDialog.SINGLE_CHOICE_LIST_DIALOG, identifier)
		.setTitle(context.getResources().getString(title))
		.setDialogList(list)
		.setListDialogListener(listDialogListener)
		.build(fm, "");
	}
	
	/**
	 * Show single choice list dialog.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param list the list
	 * @param listDialogListener the list dialog listener
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showSingleChoiceListDialog(FragmentManager fm,String title,ArrayList<String> list,ListDialogListener listDialogListener,int identifier){
//		DialogFragment newFragment = AlertFragmentDialog.newInstance(title,list,listDialogListener,AlertFragmentDialog.SINGLE_CHOICE_LIST_DIALOG,identifier);
//		newFragment.show(fm, "dialog");
		new Builder(AlertFragmentDialog.SINGLE_CHOICE_LIST_DIALOG, identifier)
		.setTitle(title)
		.setDialogList(list)
		.setListDialogListener(listDialogListener)
		.build(fm, "");
	}
	
	/**
	 * Show multiple choice list dialog.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param list the list
	 * @param listDialogListener the list dialog listener
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showMultipleChoiceListDialog(FragmentManager fm,int title,ArrayList<String> list,ListDialogListener listDialogListener,int identifier,Context context){
//		DialogFragment newFragment = AlertFragmentDialog.newInstance(context.getResources().getString(title),list,listDialogListener,AlertFragmentDialog.MULTI_CHOICE_LIST_DIALOG,identifier);
//		newFragment.show(fm, "dialog");
		new Builder(AlertFragmentDialog.MULTI_CHOICE_LIST_DIALOG, identifier)
		.setTitle(context.getResources().getString(title))
		.setDialogList(list)
		.setListDialogListener(listDialogListener)
		.build(fm, "");
	}
	
	/**
	 * Show multiple choice list dialog.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param list the list
	 * @param listDialogListener the list dialog listener
	 * @param identifier the identifier
	 * @deprecated
	 */
	public static void showMultipleChoiceListDialog(FragmentManager fm,String title,ArrayList<String> list,ListDialogListener listDialogListener,int identifier){
//		DialogFragment newFragment = AlertFragmentDialog.newInstance(title,list,listDialogListener,AlertFragmentDialog.MULTI_CHOICE_LIST_DIALOG,identifier);
//		newFragment.show(fm, "dialog");
		new Builder(AlertFragmentDialog.MULTI_CHOICE_LIST_DIALOG, identifier)
		.setTitle(title)
		.setDialogList(list)
		.setListDialogListener(listDialogListener)
		.build(fm, "");
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
	
	/**
	 * Show progress dialog.
	 *
	 * @param context the context
	 */
	public static void showProgressDialog(Context context){
		showProgressDialog(context,true,"");
	}
	
	/**
	 * Show progress dialog.
	 *
	 * @param context the context
	 * @param isCancelable cancelable
	 */
	public static void showProgressDialog(Context context,boolean isCancelable){
		showProgressDialog(context,isCancelable,"");
	}
	
	/**
	 * Show progress dialog.
	 *
	 * @param context the context
	 * @param message the message
	 */
	public static void showProgressDialog(Context context,String message){
		showProgressDialog(context,true,message);
	}
		
	/**
	 * Show progress dialog.
	 *
	 * @param context the context
	 * @param isCancelable the is cancelable
	 * @param message the message
	 */
	public static void showProgressDialog(Context context,boolean isCancelable,String message){
		if(sProgressDialog == null)
			sProgressDialog = new ProgressDialog(context);
		if(sProgressDialog.isShowing())
			sProgressDialog.dismiss();
		sProgressDialog.setCancelable(isCancelable);
		sProgressDialog.setMessage(message);
		sProgressDialog.show();
	}
	
	/**
	 * Show i phone progress dialog.
	 *
	 * @param context the context
	 */
	public static void showIPhoneProgressDialog(Context context) {
		showIPhoneProgressDialog(context,
				"", true);
	}
	
	/**
	 * Show i phone progress dialog.
	 *
	 * @param context the context
	 * @param message the message
	 */
	public static void showIPhoneProgressDialog(Context context,String message) {
		showIPhoneProgressDialog(context,
				message, true);
	}
	
	/**
	 * Show i phone progress dialog.
	 *
	 * @param context the context
	 * @param isCancelable the is cancelable
	 */
	public static void showIPhoneProgressDialog(Context context,boolean isCancelable) {
		showIPhoneProgressDialog(context,
				"", isCancelable);
	}
	
	/**
	 * Show i phone progress dialog.
	 *
	 * @param context the context
	 * @param message the message
	 * @param isCancelable the is cancelable
	 */
	public static void showIPhoneProgressDialog(Context context, String message,boolean isCancelable) {
		progressDialogHUD = ProgressHUD.show(context,
				message, isCancelable );
	}
	
	/**
	 * Close progress dialog.
	 */
	public static void closeIPhoneProgressDialog() {
		if (progressDialogHUD != null && progressDialogHUD.isShowing()) {
			progressDialogHUD.dismiss();
			progressDialogHUD = null;
		}
	}
	
	public static void closeProgressDialog() {
		if (sProgressDialog != null && sProgressDialog.isShowing()) {
			sProgressDialog.dismiss();
			sProgressDialog = null;
		}
	}
	
}
