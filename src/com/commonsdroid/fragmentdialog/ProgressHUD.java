package com.commonsdroid.fragmentdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The Class ProgressHUD.
 * @author Rishi
 */
public class ProgressHUD extends Dialog {
	
	
	/** The lbl message. */
	static TextView lblMessage;
	
	/** The img spinner. */
	static ImageView imgSpinner;
	
	/**
	 * Instantiates a new progress hud.
	 *
	 * @param context the context
	 */
	private ProgressHUD(Context context) {
		super(context);
	}

	/**
	 * Instantiates a new progress hud.
	 *
	 * @param context the context
	 * @param theme the theme
	 */
	private ProgressHUD(Context context, int theme) {
		super(context, theme);
	}
	
	

	/* (non-Javadoc)
	 * @see android.app.Dialog#onWindowFocusChanged(boolean)
	 */
	public void onWindowFocusChanged(boolean hasFocus) {
		ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
		AnimationDrawable spinner = (AnimationDrawable) imageView
				.getBackground();
		spinner.start();
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(CharSequence message) {
		if (message != null && message.length() > 0) {
			findViewById(R.id.message).setVisibility(View.VISIBLE);
			TextView txt = (TextView) findViewById(R.id.message);
			txt.setText(message);
			txt.invalidate();
		}
	}

	/**
	 * Show.
	 *
	 * @param context the context
	 * @param message the message
	 * @param cancelable the cancelable
	 * @return the progress dialog
	 */
	public static ProgressHUD show(Context context, CharSequence message,
			boolean cancelable) {
		ProgressHUD dialog = new ProgressHUD(context, R.style.ProgressHUD);
		dialog.setTitle("");
		dialog.setContentView(R.layout.progress_hud);
		
		
		if (message == null || message.length() == 0) {
			dialog.findViewById(R.id.message).setVisibility(View.GONE);
		} else {
			TextView txt = (TextView) dialog.findViewById(R.id.message);
			txt.setText(message);
		}
		dialog.setCancelable(cancelable);
		dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.dimAmount = 0.2f;
		dialog.getWindow().setAttributes(lp);
		dialog.show();
		return dialog;
	}
}
