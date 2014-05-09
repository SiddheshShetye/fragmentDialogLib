package com.commonsdroid.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.commonsdroid.dialoginterface.AlertButtonsClickListener;
import com.commonsdroid.fragmentdialog.AlertFragmentDialog;
import com.commonsdroid.fragmentdialog.AlertFragmentDialog.Builder;

public class TestActivity extends FragmentActivity implements AlertButtonsClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new TextView(this));
		new Builder(AlertFragmentDialog.DIALOG_TYPE_OK, 777)
		.setAlertButtonClickListener(this)
		.setMessage("TESTING BUILDER PATTEREN")
		.setTitle("BUILDER")
		.setPositiveText("POSITIVE")
		.build(getSupportFragmentManager(), "test");
	}

	@Override
	public void onPositiveButtonClick(int identifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNegativeButtonClick(int identifier) {
		// TODO Auto-generated method stub
		
	}

}
