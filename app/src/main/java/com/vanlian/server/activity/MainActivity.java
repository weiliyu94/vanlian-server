package com.vanlian.server.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vanlian.server.R;
import com.vanlian.server.base.BaseHomeActivity;
import com.vanlian.server.utils.OneDialog;
import com.vanlian.server.utils.ThreeDialog;
import com.vanlian.server.utils.ToastUtil;
import com.vanlian.server.utils.TwoDialog;

public class MainActivity extends BaseHomeActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView building_listview_list1 = (TextView) findViewById(R.id.building_listview_list1);
        TextView building_listview_list2 = (TextView) findViewById(R.id.building_listview_list2);
        TextView building_listview_list3 = (TextView) findViewById(R.id.building_listview_list3);
        building_listview_list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                final OneDialog oneDialog = new OneDialog("第一个","1.0","1-1");
                showDialog(oneDialog, "oneDialog");
                oneDialog.setOneDialogListenerr(new OneDialog.OneDialogListener() {
                    @Override
                    public void OneDialog () {
                        ToastUtil.makeShortText(MainActivity.this,"我点击了1-1");
                        oneDialog.dismiss();
                    }
                });
            }
        });
        building_listview_list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                final TwoDialog twoDialog = new TwoDialog("第二个","2.0","2-1","2-2");
                showDialog(twoDialog, "oneDialog");
                twoDialog.setOneDialogListenerr(new TwoDialog.OneDialogListener() {
                    @Override
                    public void OneDialog () {
                        ToastUtil.makeShortText(MainActivity.this,"我点击了2-1");
                        twoDialog.dismiss();
                    }
                });
                twoDialog.setTwoDialogListenerr(new TwoDialog.TwoDialogListener() {
                    @Override
                    public void TwoDialog () {
                        ToastUtil.makeShortText(MainActivity.this,"我点击了2-2");
                        twoDialog.dismiss();
                    }
                });
            }
        });
        building_listview_list3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                final ThreeDialog threeDialog = new ThreeDialog("第三个","3.0","3-1","3-2","3-3");
                showDialog(threeDialog, "threeDialog");
                threeDialog.setOneDialogListenerr(new ThreeDialog.OneDialogListener() {
                    @Override
                    public void OnesDialog () {
                        ToastUtil.makeShortText(MainActivity.this,"我点击了3-1");
                        threeDialog.dismiss();
                    }
                });
                threeDialog.setTwoDialogListenerr(new ThreeDialog.TwoDialogListener() {
                    @Override
                    public void TwosDialog () {
                        ToastUtil.makeShortText(MainActivity.this,"我点击了3-2");
                        threeDialog.dismiss();
                    }
                });
                threeDialog.setThreeDialogListenerr(new ThreeDialog.ThreeDialogListener() {
                    @Override
                    public void ThreesDialog () {
                        ToastUtil.makeShortText(MainActivity.this,"我点击了3-3");
                        threeDialog.dismiss();
                    }
                });
            }
        });
    }

    public void init() {

    }

    @Override
    protected int getLayoutID () {
        return 0;
    }

    @Override
    protected void init (Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void initListener () {

    }

    @Override
    public void onClick (View v) {

    }
}
