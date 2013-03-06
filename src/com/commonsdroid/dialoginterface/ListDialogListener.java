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
package com.commonsdroid.dialoginterface;

import java.util.ArrayList;

/**
 * ListDialogListener class
 * @author Siddhesh S Shetye
 * @version 2013.2801
 * @since 1.0
 */
public interface ListDialogListener {
	
	/**
	 * On list item selected.
	 *
	 * @param identifier the identifier
	 * @param selected the selected
	 */
	public void onListItemSelected(int identifier,String selected);
	
	/**
	 * On multi choice selected.
	 *
	 * @param identifier the identifier
	 * @param selectedValues the selected values
	 */
	public void onMultiChoiceSelected(int identifier,ArrayList<String> selectedValues);
}
