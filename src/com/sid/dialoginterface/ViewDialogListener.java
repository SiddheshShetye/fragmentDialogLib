package com.sid.dialoginterface;

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
 * @version 2013.2801
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
