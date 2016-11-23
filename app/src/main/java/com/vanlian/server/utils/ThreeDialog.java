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
public class ThreeDialog extends BaseDialog implements View.OnClickListener {

    private TextView threedialog_textview_title,threedialog_textview_message;
    private TextView threedialog_textview_button1,threedialog_textview_button2,threedialog_textview_button3;

    private String title = "";
    private String content = "";
    private String button1 = "";
    private String button2 = "";
    private String button3 = "";

    private OneDialogListener oneDialogListener;
    public interface OneDialogListener {
        void OnesDialog ();
    }
    public void setOneDialogListenerr(OneDialogListener oneDialogListener) {
        this.oneDialogListener = oneDialogListener;
    }

    private TwoDialogListener twoDialogListener;
    public interface TwoDialogListener {
        void TwosDialog ();
    }
    public void setTwoDialogListenerr(TwoDialogListener twoDialogListener) {
        this.twoDialogListener = twoDialogListener;
    }

    private ThreeDialogListener threeDialogListener;
    public interface ThreeDialogListener {
        void ThreesDialog ();
    }
    public void setThreeDialogListenerr(ThreeDialogListener threeDialogListener) {
        this.threeDialogListener = threeDialogListener;
    }

    public ThreeDialog (String title, String content, String button1, String button2, String button3) {
        this.title = title;
        this.content = content;
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;
    }

    @Override
    protected boolean cancelable() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_three;
    }

    @Override
    protected void init(Bundle savedInstanceState, View content) {
        threedialog_textview_title = (TextView) content.findViewById(R.id.threedialog_textview_title);
        threedialog_textview_message = (TextView) content.findViewById(R.id.threedialog_textview_message);
        threedialog_textview_button1 = (TextView) content.findViewById(R.id.threedialog_textview_button1);
        threedialog_textview_button1.setOnClickListener(this);
        threedialog_textview_button2 = (TextView) content.findViewById(R.id.threedialog_textview_button2);
        threedialog_textview_button2.setOnClickListener(this);
        threedialog_textview_button3 = (TextView) content.findViewById(R.id.threedialog_textview_button3);
        threedialog_textview_button3.setOnClickListener(this);

        if (!StringUtil.isEmpty(this.title)) {
            threedialog_textview_title.setText(title);
        }
        if (!StringUtil.isEmpty(this.content)) {
            threedialog_textview_message.setText(this.content);
        }
        if (!StringUtil.isEmpty(this.button1)) {
            threedialog_textview_button1.setText(this.button1);
        }
        if (!StringUtil.isEmpty(this.button2)) {
            threedialog_textview_button2.setText(this.button2);
        }
        if (!StringUtil.isEmpty(this.button3)) {
            threedialog_textview_button3.setText(this.button3);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.threedialog_textview_button1:
                if (this.oneDialogListener != null) {
                    oneDialogListener.OnesDialog();
                }
                break;
            case R.id.threedialog_textview_button2:
                if (this.twoDialogListener != null) {
                    twoDialogListener.TwosDialog();
                }
                break;
            case R.id.threedialog_textview_button3:
                if (this.threeDialogListener != null) {
                    threeDialogListener.ThreesDialog();
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
