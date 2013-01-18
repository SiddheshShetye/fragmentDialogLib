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
import android.support.v4.app.FragmentManager;
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


	public int mUpperRange,/**Upper range for NumberPicker*/
	mLowerRange,/**Lower range for NumberPicker*/
	mInterval,/**Interval between two numbers in NumberPicker*/
	mDefaultValue;/**Default value for NumberPicker*/

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
	private OnNumberSetListener mNumberListener; 

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
	private static boolean isTitleBarVisible=true;

	private static int identifier;

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
	private static NumberPickerDialog newInstance(final Builder builder){//int title,String headerText,OnNumberSetListener numberListener,int lowerRange,int upperRange,int defaultRange,int interval,int identifier) {
		NumberPickerDialog frag = new NumberPickerDialog();
		/*mLowerRange=builder.lowerRange;
		mUpperRange=builder.upperRange;
		mInterval=builder.interval;
		mNumberListener=builder.numberListener;*/
		/*mTextColor = builder.textColor;
		mButtonTextColor = builder.buttonTextColor;
		mButtonBackground = builder.buttonBackgroundSelector;
		mButtonArrowUp = builder.arrowUpImage;
		mButtonArrowDown = builder.arrowDownImage;
		mBackground = builder.background;
		isTitleBarVisible = builder.isTitleBarVisible;*/
		NumberPickerDialog.identifier=builder.identifier;
		Bundle args = new Bundle();
		args.putInt(TITLE, builder.title);
		args.putString(HEADER, builder.headerText);
		args.putInt(UPPER_RANGE, builder.upperRange);
		args.putInt(LOWER_RANGE, builder.lowerRange);
		args.putInt(DEFAULT_VALUE, builder.defaultRange);
		args.putInt(INTERVAL, builder.interval);
		frag.setArguments(args);
		return frag;
	}
	
	private NumberPickerDialog() {}


	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v=inflater.inflate(R.layout.number_picker, null);
		Bundle b=getArguments();
		if(mLowerRange>=mUpperRange){
			Toast.makeText(getActivity(), "Lower Range Can Not be Greater than or Equal To Upper Range", Toast.LENGTH_LONG).show();
		}else{
			if(b.getInt(UPPER_RANGE)%b.getInt(INTERVAL)!=0){
				Toast.makeText(getActivity(), "Interval Not Correct. Interval set to 1", Toast.LENGTH_LONG).show();
				mInterval=1;
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
			if(defaultValue<mLowerRange){
				defaultValue=mLowerRange;
			}else if(defaultValue>mUpperRange){
				defaultValue=mUpperRange;
			}else if(defaultValue%mInterval!=0){
				defaultValue+=(defaultValue%mInterval);
			}
			mDefaultValue=defaultValue;
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
			if(defaultValue==mLowerRange){
				lblPrevValue.setText("");
			}else{
				lblPrevValue.setText(""+(defaultValue-mInterval));
			}
			lblNextValue=(TextView)v.findViewById(R.id.lblNextValue);
			if(defaultValue==mUpperRange){
				lblNextValue.setText("");
			}else{
				lblNextValue.setText(""+(defaultValue+mInterval));
			}
			
			if(defaultValue==mLowerRange){
				ibtnPrev.setEnabled(false);
			}
			if(defaultValue==mUpperRange){
				ibtnNext.setEnabled(false);
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
					if(newValue<mLowerRange || newValue>mUpperRange){
						edtSelectedValue.setText(""+mPrevValue);
					}else if(newValue%mInterval!=0){
						//edtSelectedValue.setText(""+mPrevValue);
					}else{
						if(newValue!=mLowerRange)
							lblPrevValue.setText(""+(newValue-mInterval));
						else
							lblPrevValue.setText("");
						if(newValue!=mUpperRange)
							lblNextValue.setText(""+(newValue+mInterval));
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
				if(selectedVal%mInterval!=0 || selectedVal<mLowerRange || selectedVal>mUpperRange){
					Toast.makeText(getActivity(), "Please select proper number.", Toast.LENGTH_LONG).show();
				}else{
					dismiss();
					mNumberListener.onNumberSet(Integer.parseInt(edtSelectedValue.getText().toString()),identifier);
				}
			}else{
				edtSelectedValue.setText(""+mDefaultValue);
				if(mDefaultValue+mInterval<=mUpperRange)
					lblNextValue.setText(""+(mDefaultValue+mInterval));
				else
					lblNextValue.setText("");
				if(mDefaultValue-mInterval>=mLowerRange)
					lblPrevValue.setText(""+(mDefaultValue-mInterval));
				else
					lblPrevValue.setText("");
			}

		}else if(v==btnCancel){
			dismiss();
		}else if(v==ibtnNext){
			if(edtSelectedValue.getText().toString().equals("") || (!edtSelectedValue.getText().toString().equals("") && Integer.parseInt(edtSelectedValue.getText().toString())%mInterval!=0)){
				edtSelectedValue.setText(""+mDefaultValue);
				if(mDefaultValue+mInterval<=mUpperRange)
					lblNextValue.setText(""+(mDefaultValue+mInterval));
				else
					lblNextValue.setText("");
				if(mDefaultValue-mInterval>=mLowerRange)
					lblPrevValue.setText(""+(mDefaultValue-mInterval));
				else
					lblPrevValue.setText("");
			}else{
				ibtnPrev.setEnabled(true);
				mValue=Integer.parseInt(edtSelectedValue.getText().toString())+mInterval;
				if(mValue==mUpperRange){
					ibtnNext.setEnabled(false);
					edtSelectedValue.setText(""+mValue);
					lblNextValue.setText("");
					lblPrevValue.setText(""+(mValue-mInterval));
				}else{
					edtSelectedValue.setText(""+mValue);
					lblNextValue.setText(""+(mValue+mInterval));
					lblPrevValue.setText(""+(mValue-mInterval));
				}
			}
		}else if(v==ibtnPrev){
			if(edtSelectedValue.getText().toString().equals("") || (!edtSelectedValue.getText().toString().equals("") && Integer.parseInt(edtSelectedValue.getText().toString())%mInterval!=0)){
				edtSelectedValue.setText(""+mDefaultValue);
				if(mDefaultValue+mInterval<=mUpperRange)
					lblNextValue.setText(""+(mDefaultValue+mInterval));
				else
					lblNextValue.setText("");
				if(mDefaultValue-mInterval>=mLowerRange)
					lblPrevValue.setText(""+(mDefaultValue-mInterval));
				else
					lblPrevValue.setText("");
			}else{
				ibtnNext.setEnabled(true);
				mValue=Integer.parseInt(edtSelectedValue.getText().toString())-mInterval;
				if(mValue==mLowerRange){
					ibtnPrev.setEnabled(false);
					edtSelectedValue.setText(""+mValue);
					lblPrevValue.setText("");
					lblNextValue.setText(""+(mValue+mInterval));
				}else{
					edtSelectedValue.setText(""+mValue);
					lblNextValue.setText(""+(mValue+mInterval));
					lblPrevValue.setText(""+(mValue-mInterval));
				}
			}
		}

	}


	/**
	 * Sets the text color.
	 *
	 * @param color the new text color
	 */
	private NumberPickerDialog setTextColor(int color){
		mTextColor=color;
		return this;
	}

	/**
	 * Sets the button text color.
	 *
	 * @param color the new button text color
	 */
	private NumberPickerDialog setButtonTextColor(int color){
		mButtonTextColor=color;
		return this;
	}

	/**
	 * Sets the button background selector.
	 *
	 * @param resid the new button background selector
	 */
	private NumberPickerDialog setButtonBackgroundSelector(int resid){
		mButtonBackground=resid;
		return this;
	}

	/**
	 * Sets the arrow up image.
	 *
	 * @param resId the new arrow up image
	 */
	private NumberPickerDialog setArrowUpImage(int resId){
		mButtonArrowUp=resId;
		return this;
	}

	/**
	 * Sets the arrow down image.
	 *
	 * @param resId the new arrow down image
	 */
	private NumberPickerDialog setArrowDownImage(int resId){
		mButtonArrowDown=resId;
		return this;
	}

	/**
	 * Sets the title bar visible.
	 *
	 * @param visible the new title bar visible
	 */
	private NumberPickerDialog setTitleBarVisible(boolean visible){
		isTitleBarVisible=visible;
		return this;
	}

	/**
	 * Sets the backgrond.
	 *
	 * @param resid the new backgrond
	 */
	private NumberPickerDialog setBackgrond(int resid){
		mBackground=resid;
		return this;
	}
	
	
	public NumberPickerDialog setmUpperRange(int mUpperRange) {
		this.mUpperRange = mUpperRange;
		return this;
	}

	public NumberPickerDialog setmLowerRange(int mLowerRange) {
		this.mLowerRange = mLowerRange;
		return this;
	}

	public NumberPickerDialog setmInterval(int mInterval) {
		this.mInterval = mInterval;
		return this;
	}

	public NumberPickerDialog setmNumberListener(OnNumberSetListener mNumberListener) {
		this.mNumberListener = mNumberListener;
		return this;
	}

	/**
	 * Sets the dialog style.
	 *
	 * @param style the style
	 * @param theme the theme
	 */
	public void setDialogStyle(int style,int theme){

	}
	
	public static class Builder{
		private String headerText;
		private OnNumberSetListener numberListener;
		private boolean isTitleBarVisible = true;
		private int title,lowerRange,upperRange,defaultRange, interval,identifier,textColor = 0,buttonTextColor = 0,buttonBackgroundSelector = 0,arrowUpImage = 0,arrowDownImage = 0,background = 0;
		
		public Builder(int title,String headerText,OnNumberSetListener numberListener,int lowerRange,int upperRange,int defaultRange,int interval,int identifier) {
			this.title=title;
			this.headerText=headerText;
			this.numberListener=numberListener;
			this.lowerRange=lowerRange;
			this.upperRange=upperRange;
			this.defaultRange=defaultRange;
			this.interval=interval;
			this.identifier=identifier;
		}
		/**
		 * Sets the text color.
		 *
		 * @param color the new text color
		 */
		public Builder setTextColor(int color){
			this.textColor=color;
			return this;
		}

		/**
		 * Sets the button text color.
		 *
		 * @param color the new button text color
		 */
		public Builder setButtonTextColor(int color){
			buttonTextColor=color;
			return this;
		}

		/**
		 * Sets the button background selector.
		 *
		 * @param resid the new button background selector
		 */
		public Builder setButtonBackgroundSelector(int resid){
			buttonBackgroundSelector=resid;
			return this;
		}

		/**
		 * Sets the arrow up image.
		 *
		 * @param resId the new arrow up image
		 */
		public Builder setArrowUpImage(int resId){
			arrowUpImage=resId;
			return this;
		}

		/**
		 * Sets the arrow down image.
		 *
		 * @param resId the new arrow down image
		 */
		public Builder setArrowDownImage(int resId){
			arrowDownImage=resId;
			return this;
		}

		/**
		 * Sets the title bar visible.
		 *
		 * @param visible the new title bar visible
		 */
		public Builder setTitleBarVisible(boolean visible){
			isTitleBarVisible=visible;
			return this;
		}

		/**
		 * Sets the backgrond.
		 *
		 * @param resid the new backgrond
		 */
		public Builder setBackgrond(int resid){
			background=resid;
			return this;
		}
		
		public void build(FragmentManager fragmentManager,String tag){
			newInstance(this)
			.setTextColor(textColor)
			.setButtonTextColor(buttonTextColor)
			.setButtonBackgroundSelector(buttonBackgroundSelector)
			.setArrowUpImage(arrowUpImage)
			.setArrowDownImage(arrowDownImage)
			.setBackgrond(background)
			.setTitleBarVisible(isTitleBarVisible)
			.setmUpperRange(upperRange)
			.setmLowerRange(lowerRange)
			.setmInterval(interval)
			.setmNumberListener(numberListener)
			.show(fragmentManager, tag);
		}
		
	}



}
