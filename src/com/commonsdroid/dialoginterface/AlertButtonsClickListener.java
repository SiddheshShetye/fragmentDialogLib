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

/**
 * The Interface AlertButtonsClickInterface. 
 * @author Siddhesh S Shetye
 * @version 2013.2801
 * @since 1.0
 */
public interface AlertButtonsClickListener {
	
	/**
	 * On positive button click.
	 */
	public void onPositiveButtonClick(int identifier);
	
	/**
	 * On negative button click.
	 */
	public void onNegativeButtonClick(int identifier);

}
