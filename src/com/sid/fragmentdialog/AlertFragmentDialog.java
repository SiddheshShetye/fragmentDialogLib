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


import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.sid.dialoginterface.AlertButtonsClickListener;
import com.sid.dialoginterface.ListDialogListener;
import com.sid.dialoginterface.OnDateTimeSetListener;

/**
 * The Class AlertFragmentDialog.
 * This class shows Alert Dialog according to passed type.
 * @author Siddhesh S Shetye
 * @version 2013.2801
 * @since 1.0
 */
public final class AlertFragmentDialog extends DialogFragment implements OnDateSetListener,OnTimeSetListener{

	/** The Constant TYPE. */
	private static final String TITLE="title",MESSAGE="message",POSITIVE="positive",NEGATIVE="negative",TYPE="type",LIST="list";

	/** The alert fragment. */
	private static AlertButtonsClickListener sAlertFragment;
	
	/** The s list dialog listener. */
	private static ListDialogListener sListDialogListener;
	
	/** The date time set listener. */
	private static OnDateTimeSetListener sDateTimeSetListener;

	/** Various Response codes and Constants. */
	public static final int DIALOG_TYPE_OK=988,/**Dialog with Positive button*/
			DIALOG_TYPE_YES_NO=888,/**Dialog with Positive & Negative button*/
			SIMPLE_LIST_DIALOG=687,/**Simple List Dialog*/
			SINGLE_CHOICE_LIST_DIALOG=682,/**Radio List Dialog*/
			MULTI_CHOICE_LIST_DIALOG=852;/**Multi Choice List Dialog*/
	
	private static final int DATE_DIALOG=214,/**DATE picker dialog*/
							 TIME_DIALOG=686;/**Time picker Dialog*/
	
	private static int sYear,/**Current year*/
					   sMonth,/**Current month*/
					   sDate,/**Current date*/
					   sHour,/**Current hour*/
					   sMinute;/**Current minutes*/
	
	/** The identifier. */
	private static int identifier;
	
	/** The selected choice. */
	private String selectedChoice;

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
	 */
	public static AlertFragmentDialog newInstance(int title,int message,AlertButtonsClickListener alert,int type,String positiveText,String negativeText,int identifier) {
		AlertFragmentDialog frag = new AlertFragmentDialog();
		sAlertFragment=alert;
		AlertFragmentDialog.identifier=identifier;
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
	 * New instance of alert dialog.
	 *
	 * @param title the title
	 * @param list the list of content
	 * @param alert the ListDialogListener
	 * @param type the type
	 * @param identifier the identifier
	 * @return the alert fragment dialog
	 */
	public static AlertFragmentDialog newInstance(int title,ArrayList<String> list,ListDialogListener alert,int type,int identifier) {
		AlertFragmentDialog frag = new AlertFragmentDialog();
		sListDialogListener=alert;
		Bundle args = new Bundle();
		args.putInt(TITLE, title);
		AlertFragmentDialog.identifier=identifier;
		args.putStringArrayList(LIST, list);
		args.putInt(TYPE, type);
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
	public static AlertFragmentDialog newInstance(boolean is24Hour,OnDateTimeSetListener dateTimeSetListener,int type,int identifier) {
		AlertFragmentDialog.sDateTimeSetListener=dateTimeSetListener;
		Bundle args = new Bundle();
		args.putInt("type", type);
		AlertFragmentDialog frag = new AlertFragmentDialog();
		frag.setArguments(args);
		AlertFragmentDialog.identifier=identifier;
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
		Bundle details=getArguments();
		int title = details.getInt(TITLE,0);
		int message = details.getInt(MESSAGE,0);
		int type = details.getInt(TYPE);
		final ArrayList<String> list= details.getStringArrayList(LIST);
		String positive=getArguments().getString(POSITIVE);
		if(positive==null){
			positive="OK";
			Log.d("CHECK", ""+com.sid.fragmentdialog.R.string.ok);
		}
		String negative=getArguments().getString(NEGATIVE);
		if(negative==null){
			negative="Cancel";
		}
		switch(type){
		case DIALOG_TYPE_OK:
			/*show dialog with positive button*/
			return new AlertDialog.Builder(getActivity())
			.setTitle(title)
			.setMessage(message)
			.setPositiveButton(positive,
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					dialog.dismiss();
					if(sAlertFragment!=null)
						sAlertFragment.onPositiveButtonClick(identifier);

				}
			}
					)

					.create();
		case DIALOG_TYPE_YES_NO:
			/*show dialog with positive and negative button*/
			return new AlertDialog.Builder(getActivity())
			.setTitle(title)
			.setMessage(message)
			.setPositiveButton(positive,
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					dialog.dismiss();
					if(sAlertFragment!=null)
						sAlertFragment.onPositiveButtonClick(identifier);

				}
			}
					)
					.setNegativeButton(negative,
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							dialog.dismiss();
							if(sAlertFragment!=null)
								sAlertFragment.onNegativeButtonClick(identifier);

						}
					}
							)
							.create();
		case DATE_DIALOG:
			/*show date picker dialog*/
			return new DatePickerDialog(getActivity(), AlertFragmentDialog.this, sYear, sMonth, sDate);	
		case TIME_DIALOG:
			/*show time picker dialog*/
			return new TimePickerDialog(getActivity(), AlertFragmentDialog.this, sHour, sMinute, true);
		case SIMPLE_LIST_DIALOG:
			/*show simple list dialog*/
			return getAlertBuilder(title, list, android.R.layout.select_dialog_item).create();
		case SINGLE_CHOICE_LIST_DIALOG:
			/*show single choice list dialog*/
			return getAlertBuilder(title, list, android.R.layout.select_dialog_singlechoice).create();
		case MULTI_CHOICE_LIST_DIALOG:
			/*show multichoice list dialog*/
			AlertDialog.Builder multipleChoice = new AlertDialog.Builder(getActivity());
            multipleChoice.setTitle(title);
            final ArrayList<String> alSelectedItem = new ArrayList<String>();
            final String[] items = (String[]) list.toArray(new String[list.size()]);
            multipleChoice.setMultiChoiceItems(items, null,
                    new DialogInterface.OnMultiChoiceClickListener() {
            			@Override
                        public void onClick(DialogInterface dialog, int which,
                                boolean isChecked) {
                            if (isChecked) {
                                alSelectedItem.add(items[which]);
                            } else {
                                alSelectedItem.remove(items[which]);
                            }
                        }
                    });
            multipleChoice.setNegativeButton(R.string.cancel,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            multipleChoice.setPositiveButton(R.string.ok,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (alSelectedItem.size() != 0) {
                                sListDialogListener.onMultiChoiceSelected(identifier, alSelectedItem);
                            }
                        }
                    });
            return multipleChoice.create();

		}
		return null;
	}
	
	/**
	 * Gets the alert builder.
	 *
	 * @param title the title
	 * @param list the list
	 * @param layout the layout
	 * @return the alert builder
	 */
	private AlertDialog.Builder getAlertBuilder(int title,final ArrayList<String> list,int layout){
		AlertDialog.Builder singleChoiceListDialog = new AlertDialog.Builder(getActivity());
		singleChoiceListDialog.setTitle(title);
		final ArrayAdapter<String> arraySingleChoiceAdapter = new ArrayAdapter<String>(
                getActivity(),
                layout,list);
		singleChoiceListDialog.setPositiveButton(R.string.cancel, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dismiss();		
				
			}
		});
		singleChoiceListDialog.setAdapter(arraySingleChoiceAdapter,new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				selectedChoice=list.get(which);
				sListDialogListener.onListItemSelected(identifier, selectedChoice);	
			}
		});
		singleChoiceListDialog.setCancelable(false);
		return singleChoiceListDialog;
	}

	/* (non-Javadoc)
	 * @see android.app.TimePickerDialog.OnTimeSetListener#onTimeSet(android.widget.TimePicker, int, int)
	 */
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		sDateTimeSetListener.onTimeSet(view, hourOfDay, minute,identifier);
	}

	/* (non-Javadoc)
	 * @see android.app.DatePickerDialog.OnDateSetListener#onDateSet(android.widget.DatePicker, int, int, int)
	 */
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		sDateTimeSetListener.onDateSet(view, year, monthOfYear, dayOfMonth,identifier);
	}
}
