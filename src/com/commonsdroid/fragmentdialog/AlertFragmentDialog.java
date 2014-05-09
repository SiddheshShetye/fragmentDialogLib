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


import java.io.Serializable;
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
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;

import com.commonsdroid.dialoginterface.AlertButtonsClickListener;
import com.commonsdroid.dialoginterface.ListDialogListener;
import com.commonsdroid.dialoginterface.OnDateTimeSetListener;


/**
 * The Class AlertFragmentDialog.
 * This class shows Alert Dialog according to passed type.
 * @author Siddhesh S Shetye
 * @since 1.0
 */
public final class AlertFragmentDialog extends DialogFragment implements OnDateSetListener,OnTimeSetListener{

	/** The Constant KEY. */
	private static final String KEY = "key";

	/** Various Response codes and Constants. */
	public static final int DIALOG_TYPE_OK=988,/**Dialog with Positive button*/
			DIALOG_TYPE_YES_NO=888,/**Dialog with Positive & Negative button*/
			SIMPLE_LIST_DIALOG=687,/**Simple List Dialog*/
			SINGLE_CHOICE_LIST_DIALOG=682,/**Radio List Dialog*/
			MULTI_CHOICE_LIST_DIALOG=852;
	/** Multi Choice List Dialog. */

	private static final int DATE_DIALOG=214,/**DATE picker dialog*/
			TIME_DIALOG=686;
	/** Time picker Dialog. */

	private static int sYear,/**Current year*/
	sMonth,/**Current month*/
	sDate,/**Current date*/
	sHour,/**Current hour*/
	sMinute;
	/** Current minutes. */

	/** The selected choice. */
	private String selectedChoice;

	//--------------------------------------------------------
	/** The title. */
	private String title;

	/** The message. */
	private String message;

	/** The alert button click listener. */
	private AlertButtonsClickListener alertButtonClickListener;

	/** The positive text. */
	private String positiveText;

	/** The negative text. */
	private String negativeText;

	/** The type. */
	private int type;

	/** The identifier. */
	private static int identifier;

	/** The dialog list. */
	private ArrayList<String> dialogList;

	/** The list dialog listener. */
	private ListDialogListener listDialogListener;

	/** The is24 hour. */
	private boolean is24Hour;

	/** The on date time set listener. */
	private OnDateTimeSetListener onDateTimeSetListener;

	/** The calendar. */
	private Calendar calendar;

	/** The is cancelable. */
	private boolean isCancelable = true;

	//--------------------------------------------------------


	/* (non-Javadoc)
	 * @see android.support.v4.app.DialogFragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Builder builder = (Builder) getArguments().getSerializable(KEY);
		this.title = builder.title;
		this.message = builder.message;
		this.type = builder.type;
		AlertFragmentDialog.identifier = builder.identifier;
		this.positiveText = builder.positiveText;
		this.negativeText = builder.negativeText;
		this.alertButtonClickListener = builder.alertButtonClickListener;
		this.listDialogListener = builder.listDialogListener;
		this.is24Hour = builder.is24Hour;
		this.onDateTimeSetListener = builder.onDateTimeSetListener;
		this.calendar = builder.calendar;
		if(this.calendar == null)
			this.calendar = Calendar.getInstance();
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
		this.dialogList = builder.dialogList;
		this.isCancelable = builder.isCancelable;
	}


	/* (non-Javadoc)
	 * @see android.support.v4.app.DialogFragment#onCreateDialog(android.os.Bundle)
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		setCancelable(isCancelable);
		if(TextUtils.isEmpty(positiveText)){
			positiveText="OK";
			Log.d("CHECK", ""+com.commonsdroid.fragmentdialog.R.string.ok);
		}

		if(TextUtils.isEmpty(negativeText)){
			negativeText="Cancel";
		}
		switch(type){
		case DIALOG_TYPE_OK:
			/*show dialog with positive button*/
			return new AlertDialog.Builder(getActivity())
			.setTitle(title)
			.setMessage(message)
			.setPositiveButton(positiveText,
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					dialog.dismiss();
					if(alertButtonClickListener!=null)
						alertButtonClickListener.onPositiveButtonClick(identifier);

				}
			}).create();
		case DIALOG_TYPE_YES_NO:
			/*show dialog with positive and negative button*/
			return new AlertDialog.Builder(getActivity())
			.setTitle(title)
			.setMessage(message)
			.setPositiveButton(positiveText,
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					dialog.dismiss();
					if(alertButtonClickListener!=null)
						alertButtonClickListener.onPositiveButtonClick(identifier);

				}
			}).setNegativeButton(negativeText,
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					dialog.dismiss();
					if(alertButtonClickListener!=null)
						alertButtonClickListener.onNegativeButtonClick(identifier);

				}
			}).create();
		case DATE_DIALOG:
			/*show date picker dialog*/
			return new DatePickerDialog(getActivity(), AlertFragmentDialog.this, sYear, sMonth, sDate);	
		case TIME_DIALOG:
			/*show time picker dialog*/
			return new TimePickerDialog(getActivity(), AlertFragmentDialog.this, sHour, sMinute, true);
		case SIMPLE_LIST_DIALOG:
			/**
			 * show simple list dialog
			 */
			return getAlertBuilder(title, dialogList, android.R.layout.select_dialog_item).create();
		case SINGLE_CHOICE_LIST_DIALOG:
			/*show single choice list dialog*/
			return getAlertBuilder(title, dialogList, android.R.layout.select_dialog_singlechoice).create();
		case MULTI_CHOICE_LIST_DIALOG:
			/*show multichoice list dialog*/
			Dialog multipleChoice = new Dialog(getActivity());
			multipleChoice.setContentView(R.layout.list_multichoice);
			multipleChoice.setTitle(title);
			ListView listView = (ListView) multipleChoice.findViewById(R.id.lstMultichoiceList);
			listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// Manage selected items here

					CheckedTextView textView = (CheckedTextView) view;
					if(textView.isChecked()) {

					} else {

					}
					Log.e("CHECK","clicked pos : " + position+" checked : "+textView.isChecked());
				}
			});

			final ArrayAdapter<String> arraySingleChoiceAdapter = new ArrayAdapter<String>(
					getActivity(),
					android.R.layout.select_dialog_multichoice,dialogList);

			listView.setAdapter(arraySingleChoiceAdapter);
			multipleChoice.show();
			/*multipleChoice.setNegativeButton(R.string.cancel,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
			multipleChoice.setPositiveButton(R.string.cancel, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dismiss();		
					sListDialogListener.onMultiChoiceSelected(identifier, alSelectedItem);
				}
			});*/
			/* multipleChoice.setPositiveButton(R.string.ok,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (alSelectedItem.size() != 0) {
                                sListDialogListener.onMultiChoiceSelected(identifier, alSelectedItem);
                            }
                        }
                    });*/
			//			multipleChoice.create();



			//			singleChoiceListDialog.setCancelable(false);


			/* multipleChoice.setMultiChoiceItems(items, null,
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
                    });*/
			return multipleChoice;//.create();

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
	private AlertDialog.Builder getAlertBuilder(String title,final ArrayList<String> list,int layout){
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
				listDialogListener.onListItemSelected(identifier, selectedChoice);	
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
		onDateTimeSetListener.onTimeSet(view, hourOfDay, minute,identifier);
	}

	/* (non-Javadoc)
	 * @see android.app.DatePickerDialog.OnDateSetListener#onDateSet(android.widget.DatePicker, int, int, int)
	 */
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		onDateTimeSetListener.onDateSet(view, year, monthOfYear, dayOfMonth,identifier);
	}


	/**
	 * The Class Builder.<br/>
	 * Builds a AlertFragmentDialog instance with the required choices and show the dialog.
	 * @since 3.0
	 */
	public static class Builder implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 5125364668450034812L;

		/** The title. */
		private String title;

		/** The message. */
		private String message;

		/** The alert button click listener. */
		private AlertButtonsClickListener alertButtonClickListener;

		/** The positive text. */
		private String positiveText;

		/** The negative text. */
		private String negativeText;

		/** The type. */
		private int type;

		/** The identifier. */
		private int identifier;

		/** The dialog list. */
		private ArrayList<String> dialogList;

		/** The list dialog listener. */
		private ListDialogListener listDialogListener;

		/** The is24 hour. */
		private boolean is24Hour;

		/** The on date time set listener. */
		private OnDateTimeSetListener onDateTimeSetListener;

		/** The calendar. */
		private Calendar calendar;

		/** The is cancelable. */
		private boolean isCancelable = true;

		/**
		 * Instantiates a new builder.
		 *
		 * @param type the type
		 * @param identifier the identifier
		 */
		public Builder(int type,int identifier) {
			this.type = type;
			this.identifier = identifier;
		}

		/**
		 * Sets the title.
		 *
		 * @param title the title
		 * @return the builder
		 */
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		/**
		 * Sets the message.
		 *
		 * @param message the message
		 * @return the builder
		 */
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Sets the alert button click listener.
		 *
		 * @param alertButtonClickListener the alert button click listener
		 * @return the builder
		 */
		public Builder setAlertButtonClickListener(
				AlertButtonsClickListener alertButtonClickListener) {
			this.alertButtonClickListener = alertButtonClickListener;
			return this;
		}

		/**
		 * Sets the positive text.
		 *
		 * @param positiveText the positive text
		 * @return the builder
		 */
		public Builder setPositiveText(String positiveText) {
			this.positiveText = positiveText;
			return this;
		}

		/**
		 * Sets the negative text.
		 *
		 * @param negativeText the negative text
		 * @return the builder
		 */
		public Builder setNegativeText(String negativeText) {
			this.negativeText = negativeText;
			return this;
		}

		/**
		 * Sets the dialog list.
		 *
		 * @param dialogList the dialog list
		 * @return the builder
		 */
		public Builder setDialogList(ArrayList<String> dialogList) {
			this.dialogList = dialogList;
			return this;
		}

		/**
		 * Sets the list dialog listener.
		 *
		 * @param listDialogListener the list dialog listener
		 * @return the builder
		 */
		public Builder setListDialogListener(ListDialogListener listDialogListener) {
			this.listDialogListener = listDialogListener;
			return this;
		}

		/**
		 * Sets the is24 hour.
		 *
		 * @param is24Hour the is24 hour
		 * @return the builder
		 */
		public Builder setIs24Hour(boolean is24Hour) {
			this.is24Hour = is24Hour;
			return this;
		}

		/**
		 * Sets the on date time set listener.
		 *
		 * @param onDateTimeSetListener the on date time set listener
		 * @return the builder
		 */
		public Builder setOnDateTimeSetListener(OnDateTimeSetListener onDateTimeSetListener) {
			this.onDateTimeSetListener = onDateTimeSetListener;
			return this;
		}

		/**
		 * Sets the calendar.
		 *
		 * @param calendar the calendar
		 * @return the builder
		 */
		public Builder setCalendar(Calendar calendar) {
			this.calendar = calendar;
			return this;
		}

		/**
		 * Sets the cancelable.
		 *
		 * @param isCancelable the new cancelable
		 */
		public Builder setCancelable(boolean isCancelable) {
			this.isCancelable = isCancelable;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @param fragMan the frag man
		 * @param tag the tag
		 * @return the builder
		 */
		public Builder build(FragmentManager fragMan,String tag){
			AlertFragmentDialog frag = new AlertFragmentDialog();
			Bundle b = new Bundle();
			b.putSerializable(KEY, this);
			frag.setArguments(b);
			frag.show(fragMan, tag);
			return this;
		}
	}


}
