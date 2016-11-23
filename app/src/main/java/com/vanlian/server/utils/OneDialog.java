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
public class OneDialog extends BaseDialog implements View.OnClickListener {

    private TextView onedialog_textview_title,onedialog_textview_message,onedialog_textview_button;

    private String title = "";
    private String content = "";
    private String button = "";

    private OneDialogListener oneDialogListener;

    public interface OneDialogListener {
        void OneDialog();
    }
    public void setOneDialogListenerr(OneDialogListener oneDialogListener) {
        this.oneDialogListener = oneDialogListener;
    }

    public OneDialog(String title, String content, String button) {
        this.title = title;
        this.content = content;
        this.button = button;
    }

    @Override
    protected boolean cancelable() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_one;
    }

    @Override
    protected void init(Bundle savedInstanceState, View content) {
        onedialog_textview_title = (TextView) content.findViewById(R.id.onedialog_textview_title);
        onedialog_textview_message = (TextView) content.findViewById(R.id.onedialog_textview_message);
        onedialog_textview_button = (TextView) content.findViewById(R.id.onedialog_textview_button);
        onedialog_textview_button.setOnClickListener(this);

        if (!StringUtil.isEmpty(this.title)) {
            onedialog_textview_title.setText(this.title);
        }
        if (!StringUtil.isEmpty(this.content)) {
            onedialog_textview_message.setText(this.content);
        }
        if (!StringUtil.isEmpty(this.button)) {
            onedialog_textview_button.setText(this.button);
        }
    }

    @Override
    public void onClick(View v) {
        if (this.oneDialogListener != null) {
            oneDialogListener.OneDialog();
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
