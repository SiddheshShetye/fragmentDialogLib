package com.sid.dialoginterface;

import android.widget.DatePicker;
import android.widget.TimePicker;

public interface OnDateTimeSetListener {
	public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth);
	public void onTimeSet(TimePicker view, int hourOfDay, int minute);
}
