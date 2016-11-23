package com.vanlian.server.base;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-11-19.
 */

public class BaseApplication extends Application{

	private static BaseApplication application;

	public List<Activity> activities = new ArrayList<Activity>();

	@Override
	public void onCreate () {
		super.onCreate();
		application = this;
	}

	public List<Activity> getActivities () {
		return activities;
	}

	public void setActivities (List<Activity> activities) {
		this.activities = activities;
	}

	public static BaseApplication getApplication () {
		return application;
	}

	public static void setApplication (BaseApplication application) {
		BaseApplication.application = application;
	}
}
