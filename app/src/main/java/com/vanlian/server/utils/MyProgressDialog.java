package com.vanlian.server.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class MyProgressDialog extends ProgressDialog {

	private MyProgressDialog myProgressDialog;

	public MyProgressDialog(Context context,String text) {
		super(context);
		init(text);
	}

	private void init(String text) {
		if(myProgressDialog != null){
			myProgressDialog.dismiss();
		}else{
			myProgressDialog.setMessage(text);
			myProgressDialog.show();
		}
	}

	@Override
	public void cancel() {
		super.cancel();
	}
}
