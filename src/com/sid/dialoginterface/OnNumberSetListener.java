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


/**
 * The listener interface for receiving onNumberSet events.
 * The class that is interested in processing a onNumberSet
 * event implements this interface. When
 * the onNumberSet event occurs, that object's appropriate
 * method is invoked.
 *
 * @author Siddhesh S Shetye
 * @version 2013.2801
 * @since 1.0
 */
public interface OnNumberSetListener {
	
	/**
	 * On number set.
	 *
	 * @param number the number
	 */
	public void onNumberSet(int number,int identifier);
}
