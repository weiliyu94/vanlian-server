package com.vanlian.server.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast类,防止连续多次调用toast时,toast超时显示. Created by zzk on 2014/7/21.
 */
public class ToastUtil {
	private static Toast t;
	private static int duration;

	private static void makeText(Context context, String msg, int duration) {
		if (ToastUtil.duration != duration) {
			if (t != null) {
				t.cancel();
			}
			t = Toast.makeText(context, msg, duration);
		} else {
			if (t == null) {
				t = Toast.makeText(context, msg, duration);
			} else {
				t.setText(msg);
			}
		}
		ToastUtil.duration = duration;
		t.show();
	}

	public static void makeText(Context context, int resId, int duration) {
		makeText(context, context.getResources().getString(resId), duration);
	}

	public static void makeShortText(Context context, String msg) {
		makeText(context, msg, Toast.LENGTH_SHORT);
	}

	public static void makeShortText(Context context, int resId) {
		makeText(context, resId, Toast.LENGTH_SHORT);
	}

	public static void makeLongText(Context context, String msg) {
		makeText(context, msg, Toast.LENGTH_LONG);
	}

	public static void makeLongText(Context context, int resId) {
		makeText(context, resId, Toast.LENGTH_LONG);
	}

	/**
	 * toast提示
	 * 
	 * @param context
	 *            上下文
	 * @param text
	 *            提示的内容
	 * */
	public static void toast(Context context, String text) {
		try {
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
