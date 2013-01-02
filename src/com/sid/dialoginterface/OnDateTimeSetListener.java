package com.sid.dialoginterface;

import android.widget.DatePicker;
import android.widget.TimePicker;

/**
 * @author Siddhesh
 */
public interface OnDateTimeSetListener {
	
	/**
	 * On date set.
	 *
	 * @param view the view
	 * @param year the year
	 * @param monthOfYear the month of year
	 * @param dayOfMonth the day of month
	 */
	public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth,int identifier);
	
	/**
	 * On time set.
	 *
	 * @param view the view
	 * @param hourOfDay the hour of day
	 * @param minute the minute
	 */
	public void onTimeSet(TimePicker view, int hourOfDay, int minute,int identifier);
}
