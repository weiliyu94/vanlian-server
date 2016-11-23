package com.vanlian.server.utils;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.vanlian.server.R;
import com.vanlian.server.base.BaseDialog;

/**
 * Created by Administrator on 2016-10-28.
 */
public class TwoDialog extends BaseDialog implements View.OnClickListener {

    private TextView twodialog_textview_title,twodialog_textview_message;
    private TextView twodialog_textview_button1,twodialog_textview_button2;

    private String title = "";
    private String content = "";
    private String button1 = "";
    private String button2 = "";

    private OneDialogListener oneDialogListener;
    public interface OneDialogListener {
        void OneDialog ();
    }
    public void setOneDialogListenerr(OneDialogListener oneDialogListener) {
        this.oneDialogListener = oneDialogListener;
    }

    private TwoDialogListener twoDialogListener;
    public interface TwoDialogListener {
        void TwoDialog ();
    }
    public void setTwoDialogListenerr(TwoDialogListener twoDialogListener) {
        this.twoDialogListener = twoDialogListener;
    }

    public TwoDialog (String title, String content, String button1, String button2) {
        this.title = title;
        this.content = content;
        this.button1 = button1;
        this.button2 = button2;
    }

    @Override
    protected boolean cancelable() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_two;
    }

    @Override
    protected void init(Bundle savedInstanceState, View content) {
        twodialog_textview_title = (TextView) content.findViewById(R.id.twodialog_textview_title);
        twodialog_textview_message = (TextView) content.findViewById(R.id.twodialog_textview_message);
        twodialog_textview_button1 = (TextView) content.findViewById(R.id.twodialog_textview_button1);
        twodialog_textview_button1.setOnClickListener(this);
        twodialog_textview_button2 = (TextView) content.findViewById(R.id.twodialog_textview_button2);
        twodialog_textview_button2.setOnClickListener(this);

        if (!StringUtil.isEmpty(this.title)) {
            twodialog_textview_title.setText(title);
        }
        if (!StringUtil.isEmpty(this.content)) {
            twodialog_textview_message.setText(this.content);
        }
        if (!StringUtil.isEmpty(this.button1)) {
            twodialog_textview_button1.setText(this.button1);
        }
        if (!StringUtil.isEmpty(this.button2)) {
            twodialog_textview_button2.setText(this.button2);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.twodialog_textview_button1:
                if (this.oneDialogListener != null) {
                    oneDialogListener.OneDialog();
                }
                break;
            case R.id.twodialog_textview_button2:
                if (this.twoDialogListener != null) {
                    twoDialogListener.TwoDialog();
                }
                break;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        final DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        final WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.width = (int) (dm.widthPixels * 0.8);
        layoutParams.height = layoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER;
        getDialog().getWindow().setAttributes(layoutParams);
    }

}
