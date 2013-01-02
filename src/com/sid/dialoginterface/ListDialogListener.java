package com.sid.dialoginterface;

import java.util.ArrayList;

/**
 * ListDialogListener class
 * @author Siddhesh
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
