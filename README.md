========================================================================================================================
FragmentDialog Libreary
========================================================================================================================
<pre>This Library is developed to reduce redundunt code to show diffrenet types of Dialogs & Toasts.

This library can be used to show various types of 
1) Alert Dialogs
2) Custom View Dialogs
3) Number picker
4) Date Picker.
5) Number Picker.
6) List Dialogs.
7) Single Choice Dialogs.
8) Multichoice Dialogs.
9) Various types of toasts.
10) Progress Dialog.
11) IPhone like Progress Dialog.</pre>

To getCallBacks from AlertDialogs implement AlertButtonClickListener.<br/>
use 
```java
 DialogHelper.showDialog(....);
```

To getCallBacks from Custom View Dialogs implement ViewDialogListener.<br/>
Use
```java 
ViewDialogFragment.Builder for custome Custom View Dialog.
```

To getCallBacks from Custom Number Picker Dialog implement OnNumberSetListener.<br/>
Use 
```java
NumberPickerDialog.Builder for show a NumberPickerDialog
```

To getCallBacks from DatePickerDialog implement OnDateTimeSetListener.<br/>
use 
```java
DialogHelper.showDateDialog(...)
```

To getCallBacks from TimePickerDialog implement OnDateTimeSetListener.<br/>
use 
```java
DialogHelper.showTimeDialog(...)
```

To getCallBacks from SimpleListDialog, SingleChoiceListDialog and MultiChoiceListDialog implement
ListDialogListener.<br/>
use
```java
 DialogHelper.showSimpleListDialog(...)
 DialogHelper.showSingleChoiceListDialog(...)
 DialogHelper.showMultipleChoiceListDialog(...)
```

To Show ProgressDialog use<br/>
```java
DialogHelper.showProgressDialog(...)
DialogHelper.closeProgressDialog()
DialogHelper.showIPhoneProgressDialog(...)
DialogHelper.closeIPhoneProgressDialog()
```

To show Toasts use<br/>
```java
DialogHelper.showShortToast(...)
DialogHelper.showLongToast(...)
DialogHelper.showCustomToast(...)
```


