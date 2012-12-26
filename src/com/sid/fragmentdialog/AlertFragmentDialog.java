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


import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.sid.dialoginterface.AlertButtonsClickListener;
import com.sid.dialoginterface.OnDateTimeSetListener;

/**
 * The Class AlertFragmentDialog.
 * This class shows Alert Dialog according to passed type.
 * @author Siddhesh
 */
public final class AlertFragmentDialog extends DialogFragment implements OnDateSetListener,OnTimeSetListener{

	/** The m context. */
	private static Context sContext;

	/** The Constant TYPE. */
	private static final String TITLE="title",MESSAGE="message",POSITIVE="positive",NEGATIVE="negative",TYPE="type";


	/** The alert fragment. */
	private static AlertButtonsClickListener sAlertFragment;
	
	/** The date time set listener. */
	private static OnDateTimeSetListener sDateTimeSetListener;

	/** Various Response codes and Constants. */
	public static final int DIALOG_TYPE_OK=988,/**Dialog with Positive button*/
			DIALOG_TYPE_YES_NO=888,/**Dialog with Positive & Negative button*/
			DATE_DIALOG=214,/**DATE picker dialog*/
			TIME_DIALOG=686;/**Time picker Dialog*/
	
	private static int sYear,/**Current year*/
					   sMonth,/**Current month*/
					   sDate,/**Current date*/
					   sHour,/**Current hour*/
					   sMinute;/**Current minutes*/

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
	public static AlertFragmentDialog newInstance(int title,int message,AlertButtonsClickListener alert,int type,String positiveText,String negativeText) {
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
	 * @param ctx the context
	 * @param is24Hour the is24 hour
	 * @param dateTimeSetListener the date time set listener
	 * @param type the type of dialog
	 * @return the alert fragment dialog
	 */
	public static AlertFragmentDialog newInstance(Context ctx,boolean is24Hour,OnDateTimeSetListener dateTimeSetListener,int type) {

		sContext=ctx;
		AlertFragmentDialog.sDateTimeSetListener=dateTimeSetListener;
		Bundle args = new Bundle();
		args.putInt("type", type);
		AlertFragmentDialog frag = new AlertFragmentDialog();
		frag.setArguments(args);
		
		//initialize date
		Calendar calendar = Calendar.getInstance();
		sYear = calendar.get(Calendar.YEAR);
		sMonth = calendar.get(Calendar.MONTH);
		sDate = calendar.get(Calendar.DATE);
		
		//initialize Time
		if(is24Hour)
			sHour = calendar.get(Calendar.HOUR_OF_DAY);
		else
			sHour = calendar.get(Calendar.HOUR);
		sMinute = calendar.get(Calendar.MINUTE);
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
		case DATE_DIALOG:
			return new DatePickerDialog(sContext, AlertFragmentDialog.this, sYear, sMonth, sDate);	
			
		case TIME_DIALOG:
			return new TimePickerDialog(sContext, AlertFragmentDialog.this, sHour, sMinute, true);

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see android.app.TimePickerDialog.OnTimeSetListener#onTimeSet(android.widget.TimePicker, int, int)
	 */
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		sDateTimeSetListener.onTimeSet(view, hourOfDay, minute);
	}

	/* (non-Javadoc)
	 * @see android.app.DatePickerDialog.OnDateSetListener#onDateSet(android.widget.DatePicker, int, int, int)
	 */
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		sDateTimeSetListener.onDateSet(view, year, monthOfYear, dayOfMonth);
	}







}
