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
package com.commonsdroid.dialoginterface;

import android.app.Dialog;
import android.view.View;

/**
 * The listener interface for receiving viewDialog events.
 * The class that is interested in processing a viewDialog
 * event implements this interface. When
 * the viewDialog event occurs, that object's appropriate
 * method is invoked.
 *
 * @author Siddhesh S Shetye
 * @since 1.0
 */
public interface ViewDialogListener {
	
	/**
	 * Gets the view.
	 * gets view of current custome dialog
	 * @param identifier the identifier
	 * @param view the view
	 * @param dialog the dialog
	 * @return the view
	 */
	public void getView(int identifier,View view,final Dialog dialog);
}
