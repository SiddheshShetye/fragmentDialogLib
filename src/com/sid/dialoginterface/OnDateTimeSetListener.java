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
package com.sid.dialoginterface;

import android.widget.DatePicker;
import android.widget.TimePicker;

/**
 * The listener interface for receiving onDateTimeSet events.
 * The class that is interested in processing a onDateTimeSet
 * event implements this interface. When
 * the onDateTimeSet event occurs, that object's appropriate
 * method is invoked.
 *
 * @author Siddhesh S Shetye
 * @version 2013.2801
 * @since 1.0
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
