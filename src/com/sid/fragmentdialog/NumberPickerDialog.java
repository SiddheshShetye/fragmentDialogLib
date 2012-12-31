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
package com.sid.fragmentdialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sid.dialoginterface.OnNumberSetListener;

/**
 * The Class NumberPickerDialog.
 * This class is used to show NumberPickerDialog
 * @author Siddhesh
 */
public class NumberPickerDialog extends DialogFragment implements OnClickListener{


	private static int sUpperRange,/**Upper range for NumberPicker*/
	sLowerRange,/**Lower range for NumberPicker*/
	sInterval,/**Interval between two numbers in NumberPicker*/
	sDefaultValue;/**Default value for NumberPicker*/

	/** The m value. */
	private int mValue;

	/** The Constants passing values to fragment. */
	private static final String TITLE="title",
			HEADER="header",
			UPPER_RANGE="upper_range",
			LOWER_RANGE="lower_range",
			DEFAULT_VALUE="default_value",
			INTERVAL="interval";

	/** The number listener. */
	private static OnNumberSetListener sNumberListener; 

	/** The TextViews for next value, preveious value and headertext. */
	private TextView lblHeaderText,lblPrevValue,lblNextValue;

	/** The btn set & cancel. */
	private Button btnSet,btnCancel;

	/** The ibtn next. */
	private ImageButton ibtnPrev,ibtnNext;

	/** The edt selected value. */
	private EditText edtSelectedValue;

	/** The m view. */
	private View mSeparatorView;


	private int mTextColor=0,/**Text Color for views on dialog*/
			mButtonTextColor=0,/**Text Color for Positive and negative button*/
			mButtonBackground=0,/**Background selector for positive and negative button*/
			mButtonArrowUp=0,/**Image for up arrow*/
			mButtonArrowDown=0,/**Image for down arrow*/
			mBackground=0;/**Background for dialog*/

	private String mPrevValue; 

	/** The is title bar visible. */
	private boolean isTitleBarVisible=true;

	/**
	 * Gets the single instance of NumberPickerDialog.
	 *
	 * @param title the title for dialog
	 * @param headerText the header text
	 * @param numberListener the number listener
	 * @param lowerRange the lower range
	 * @param upperRange the upper range
	 * @param defaultRange the default range
	 * @param interval the interval
	 * @return single instance of NumberPickerDialog
	 */
	public static NumberPickerDialog getInstance(int title,String headerText,OnNumberSetListener numberListener,int lowerRange,int upperRange,int defaultRange,int interval) {
		NumberPickerDialog frag = new NumberPickerDialog();
		sLowerRange=lowerRange;
		sUpperRange=upperRange;
		sInterval=interval;
		sNumberListener=numberListener;
		Bundle args = new Bundle();
		args.putInt(TITLE, title);
		args.putString(HEADER, headerText);
		args.putInt(UPPER_RANGE, upperRange);
		args.putInt(LOWER_RANGE, lowerRange);
		args.putInt(DEFAULT_VALUE, defaultRange);
		args.putInt(INTERVAL, interval);
		frag.setArguments(args);
		return frag;
	}


	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v=inflater.inflate(R.layout.number_picker, null);
		Bundle b=getArguments();
		if(sLowerRange>=sUpperRange){
			Toast.makeText(getActivity(), "Lower Range Can Not be Greater than or Equal To Upper Range", Toast.LENGTH_LONG).show();
		}else{
			if(b.getInt(UPPER_RANGE)%b.getInt(INTERVAL)!=0){
				Toast.makeText(getActivity(), "Interval Not Correct. Interval set to 1", Toast.LENGTH_LONG).show();
				sInterval=1;
			}
			mSeparatorView=(View)v.findViewById(R.id.headSeparate);
			if(isTitleBarVisible){
				mSeparatorView.setVisibility(View.VISIBLE);
				getDialog().setTitle(b.getInt(TITLE));
			}else{

				mSeparatorView.setVisibility(View.INVISIBLE);
				getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
			}

			getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_full_holo_dark);
			int defaultValue=b.getInt(DEFAULT_VALUE);
			if(defaultValue<sLowerRange){
				defaultValue=sLowerRange;
			}else if(defaultValue>sUpperRange){
				defaultValue=sUpperRange;
			}else if(defaultValue%sInterval!=0){
				defaultValue+=(defaultValue%sInterval);
			}
			sDefaultValue=defaultValue;
			btnSet=(Button)v.findViewById(R.id.btnSet);
			btnSet.setOnClickListener(this);
			btnCancel=(Button)v.findViewById(R.id.btnCancel);
			btnCancel.setOnClickListener(this);
			ibtnNext=(ImageButton)v.findViewById(R.id.ibtnNext);
			ibtnNext.setOnClickListener(this);
			ibtnPrev=(ImageButton)v.findViewById(R.id.ibtnPrev);
			ibtnPrev.setOnClickListener(this);
			edtSelectedValue=(EditText)v.findViewById(R.id.edtSelectedValue);
			lblHeaderText=(TextView)v.findViewById(R.id.lblHeaderMessage);
			String text=b.getString(HEADER);
			if(text != null)
				lblHeaderText.setText(text);
			else
				lblHeaderText.setText("");
			edtSelectedValue.setText(""+defaultValue);
			lblPrevValue=(TextView)v.findViewById(R.id.lblPrevValue);
			if(defaultValue==sLowerRange){
				lblPrevValue.setText("");
			}else{
				lblPrevValue.setText(""+(defaultValue-sInterval));
			}
			lblNextValue=(TextView)v.findViewById(R.id.lblNextValue);
			if(defaultValue==sUpperRange){
				lblNextValue.setText("");
			}else{
				lblNextValue.setText(""+(defaultValue+sInterval));
			}
		}
		if(mBackground!=0){
			getDialog().getWindow().setBackgroundDrawableResource(mBackground);
		}
		if(mTextColor!=0){
			lblHeaderText.setTextColor(mTextColor);
			lblNextValue.setTextColor(mTextColor);
			lblPrevValue.setTextColor(mTextColor);
			edtSelectedValue.setTextColor(mTextColor);
		}
		if(mButtonTextColor!=0){
			btnCancel.setTextColor(mButtonTextColor);
			btnSet.setTextColor(mButtonTextColor);
		}
		if(mButtonArrowUp!=0 && mButtonArrowDown!=0){
			ibtnNext.setImageResource(mButtonArrowDown);
			ibtnPrev.setImageResource(mButtonArrowUp);
		}
		if(mButtonBackground!=0){
			btnCancel.setBackgroundResource(mButtonBackground);
			btnSet.setBackgroundResource(mButtonBackground);
		}
		edtSelectedValue.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(edtSelectedValue.getText().toString().equals("")){
				}else{
					int newValue=Integer.parseInt(edtSelectedValue.getText().toString());
					if(newValue<sLowerRange || newValue>sUpperRange){
						edtSelectedValue.setText(""+mPrevValue);
					}else if(newValue%sInterval!=0){
						//edtSelectedValue.setText(""+mPrevValue);
					}else{
						if(newValue!=sLowerRange)
							lblPrevValue.setText(""+(newValue-sInterval));
						else
							lblPrevValue.setText("");
						if(newValue!=sUpperRange)
							lblNextValue.setText(""+(newValue+sInterval));
						else
							lblNextValue.setText("");
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				if(!edtSelectedValue.getText().toString().equals("")){
					mPrevValue=edtSelectedValue.getText().toString();
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		return v;
	}


	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		if(v==btnSet){
			if(!edtSelectedValue.getText().toString().equals("")){
				int selectedVal=Integer.parseInt(edtSelectedValue.getText().toString());
				if(selectedVal%sInterval!=0 || selectedVal<sLowerRange || selectedVal>sUpperRange){
					Toast.makeText(getActivity(), "Please select proper number.", Toast.LENGTH_LONG).show();
				}else{
					dismiss();
					sNumberListener.onNumberSet(Integer.parseInt(edtSelectedValue.getText().toString()));
				}
			}else{
				edtSelectedValue.setText(""+sDefaultValue);
				if(sDefaultValue+sInterval<=sUpperRange)
					lblNextValue.setText(""+(sDefaultValue+sInterval));
				else
					lblNextValue.setText("");
				if(sDefaultValue-sInterval>=sLowerRange)
					lblPrevValue.setText(""+(sDefaultValue-sInterval));
				else
					lblPrevValue.setText("");
			}

		}else if(v==btnCancel){
			dismiss();
		}else if(v==ibtnNext){
			if(edtSelectedValue.getText().toString().equals("") || (!edtSelectedValue.getText().toString().equals("") && Integer.parseInt(edtSelectedValue.getText().toString())%sInterval!=0)){
				edtSelectedValue.setText(""+sDefaultValue);
				if(sDefaultValue+sInterval<=sUpperRange)
					lblNextValue.setText(""+(sDefaultValue+sInterval));
				else
					lblNextValue.setText("");
				if(sDefaultValue-sInterval>=sLowerRange)
					lblPrevValue.setText(""+(sDefaultValue-sInterval));
				else
					lblPrevValue.setText("");
			}else{
				ibtnPrev.setEnabled(true);
				mValue=Integer.parseInt(edtSelectedValue.getText().toString())+sInterval;
				if(mValue==sUpperRange){
					ibtnNext.setEnabled(false);
					edtSelectedValue.setText(""+mValue);
					lblNextValue.setText("");
					lblPrevValue.setText(""+(mValue-sInterval));
				}else{
					edtSelectedValue.setText(""+mValue);
					lblNextValue.setText(""+(mValue+sInterval));
					lblPrevValue.setText(""+(mValue-sInterval));
				}
			}
		}else if(v==ibtnPrev){
			if(edtSelectedValue.getText().toString().equals("") || (!edtSelectedValue.getText().toString().equals("") && Integer.parseInt(edtSelectedValue.getText().toString())%sInterval!=0)){
				edtSelectedValue.setText(""+sDefaultValue);
				if(sDefaultValue+sInterval<=sUpperRange)
					lblNextValue.setText(""+(sDefaultValue+sInterval));
				else
					lblNextValue.setText("");
				if(sDefaultValue-sInterval>=sLowerRange)
					lblPrevValue.setText(""+(sDefaultValue-sInterval));
				else
					lblPrevValue.setText("");
			}else{
				ibtnNext.setEnabled(true);
				mValue=Integer.parseInt(edtSelectedValue.getText().toString())-sInterval;
				if(mValue==sLowerRange){
					ibtnPrev.setEnabled(false);
					edtSelectedValue.setText(""+mValue);
					lblPrevValue.setText("");
					lblNextValue.setText(""+(mValue+sInterval));
				}else{
					edtSelectedValue.setText(""+mValue);
					lblNextValue.setText(""+(mValue+sInterval));
					lblPrevValue.setText(""+(mValue-sInterval));
				}
			}
		}

	}


	/**
	 * Sets the text color.
	 *
	 * @param color the new text color
	 */
	public void setTextColor(int color){
		mTextColor=color;

	}

	/**
	 * Sets the button text color.
	 *
	 * @param color the new button text color
	 */
	public void setButtonTextColor(int color){
		mButtonTextColor=color;

	}

	/**
	 * Sets the button background selector.
	 *
	 * @param resid the new button background selector
	 */
	public void setButtonBackgroundSelector(int resid){
		mButtonBackground=resid;

	}

	/**
	 * Sets the arrow up image.
	 *
	 * @param resId the new arrow up image
	 */
	public void setArrowUpImage(int resId){
		mButtonArrowUp=resId;

	}

	/**
	 * Sets the arrow down image.
	 *
	 * @param resId the new arrow down image
	 */
	public void setArrowDownImage(int resId){
		mButtonArrowDown=resId;
	}

	/**
	 * Sets the title bar visible.
	 *
	 * @param visible the new title bar visible
	 */
	public void setTitleBarVisible(boolean visible){
		isTitleBarVisible=visible;
	}

	/**
	 * Sets the backgrond.
	 *
	 * @param resid the new backgrond
	 */
	public void setBackgrond(int resid){
		mBackground=resid;
	}


	/**
	 * Sets the dialog style.
	 *
	 * @param style the style
	 * @param theme the theme
	 */
	public void setDialogStyle(int style,int theme){

	}



}
