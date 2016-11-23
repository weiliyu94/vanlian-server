package com.vanlian.server.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2016-11-19.
 */

public abstract class BaseHomeActivity extends BaseActivity {
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序",Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				List<Activity> list = ((BaseApplication) getApplication()).getActivities();
				for (int i = 0; i < list.size(); i++) {
					Activity activity = (Activity) list.get(i);
					activity.finish();
				}
				this.finish();
				ActivityManager activityMgr= (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);
				//关闭应用
				//与当前应用相关的应用、进程、服务等也会被关闭。
				//会发送 ACTION_PACKAGE_RESTARTED广播。
				activityMgr.restartPackage(this.getPackageName());
				System.exit(0);//退出应用
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected abstract int getLayoutID ();

	@Override
	protected abstract void init (Bundle savedInstanceState, View contentView);

	@Override
	public abstract void initListener ();

	@Override
	public abstract void onClick (View v);
}
