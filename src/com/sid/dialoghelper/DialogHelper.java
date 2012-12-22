package com.sid.dialoghelper;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.sid.dialoginterface.AlertButtonsClickInterface;
import com.sid.dialoginterface.ViewDialogInterface;
import com.sid.fragmentdialog.AlertFragmentDialog;
import com.sid.fragmentdialog.ViewDialogFragment;

/**
 * The Class DialogHelper.
 * This class acts as a helper to to show specified dialog
 * @author Siddhesh
 */
public final class DialogHelper {
	
	/** The new fragment. */
	private static ViewDialogFragment sNewFragment;

	/**
	 * Show dialog. 
	 * Shows a dialog with given title and message with OK button and cancelable true.
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @author Siddhesh
	 */
	public static void showDialog(FragmentManager fm,int title,int message){
		showDialog(fm, title, message, null, AlertFragmentDialog.DIALOG_TYPE_OK, true,null,null);
	}
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with OK button,cancelable true and specified positive
	 * button text.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param positiveText the text for positive button 
	 * @author Siddhesh
	 */
	public static void showDialog(FragmentManager fm,int title,int message,String positiveText){
		showDialog(fm, title, message, null, AlertFragmentDialog.DIALOG_TYPE_OK, true,positiveText,null);
	}
	
	/**
	 * Show dialog. 
	 * Shows a dialog with given title and message with OK button, 
	 * cancelable true and AlertButtonsClickInterface object for invoking 
	 * code on alert button clicks .
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the AlertButtonsClickInterface object
	 * @author Siddhesh
	 */
	public static void showDialog(FragmentManager fm,int title,int message,AlertButtonsClickInterface alertFrag){
		showDialog(fm, title, message, alertFrag, AlertFragmentDialog.DIALOG_TYPE_OK, true,null,null);
	}
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with positive button with specified text,
	 * cancelable true and AlertButtonsClickInterface object for invoking
	 * code on alert button clicks .
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the AlertButtonsClickInterface object
	 * @param positiveText the text for positive button 
	 * @author Siddhesh
	 */
	public static void showDialog(FragmentManager fm,int title,int message,AlertButtonsClickInterface alertFrag,String positiveText){
		showDialog(fm, title, message, alertFrag, AlertFragmentDialog.DIALOG_TYPE_OK, true,positiveText,null);
	}
	
	/**
	 * Show dialog. 
	 * Shows a dialog with given title and message with OK button, 
	 * Cancelable true, AlertButtonsClickInterface object for invoking 
	 * code on alert button clicks, type OK or OK_Cancel and isCancelable.
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the AlertButtonsClickInterface object
	 * @param type the type
	 * @param cancelable the cancelable
	 * @author Siddhesh
	 */
	public static void showDialog(FragmentManager fm,int title,int message,AlertButtonsClickInterface alertFrag,int type,boolean cancelable) {
	    DialogFragment newFragment = AlertFragmentDialog.newInstance(title,message,alertFrag,type,null,null);
	    newFragment.setCancelable(cancelable);
	    newFragment.show(fm, "dialog");
	}
	
	/**
	 * Show dialog.
	 * Shows a dialog with given title and message with positive button with specified 
	 * text & negative button with specified text,
	 * cancelable true, AlertButtonsClickInterface object for invoking
	 * code on alert button clicks, type OK or OK_Cancel and isCancelable.
	 *
	 * @param fm the fragment manager
	 * @param title the title
	 * @param message the message
	 * @param alertFrag the AlertButtonsClickInterface object
	 * @param type the type
	 * @param cancelable the cancelable
	 * @param positiveText the text for positive button
	 * @param negativeText the text for negative button
	 * @author Siddhesh
	 */
	public static void showDialog(FragmentManager fm,int title,int message,AlertButtonsClickInterface alertFrag,int type,boolean cancelable,String positiveText,String negativeText) {
	    DialogFragment newFragment = AlertFragmentDialog.newInstance(title,message,alertFrag,type,positiveText,negativeText);
	    newFragment.setCancelable(cancelable);
	    newFragment.show(fm, "dialog");
	}
	
	/**
	 * Gets the view dialog.
	 * this function is used to get the object of AlertFragmentDialog for custome view.
	 * @param fm the fragment manager
	 * @param ctx the context
	 * @param view the view for dialog
	 * @param vd the ViewDialogInterface
	 * @return the inflated view of dialog
	 * @author Siddhesh
	 */
	public static ViewDialogFragment getViewDialog(FragmentManager fm,Context ctx,Integer view,ViewDialogInterface vd){
		sNewFragment = ViewDialogFragment.newInstance(ctx,view,vd);
		return sNewFragment;
	}
	
}
