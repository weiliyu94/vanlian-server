package com.vanlian.server.base;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.vanlian.server.R;
import com.vanlian.server.receiver.NetReceiver;
import com.vanlian.server.utils.ToastUtil;

import java.util.Stack;

/**
 * Created by Administrator on 2016-10-25.
 */
public abstract class BaseActivity extends AppCompatActivity implements NetReceiver.NetEventHandle, View.OnClickListener {
	private static final String TAG = "BaseActivity";

	public static final int NO_LAYOUT = 0;

	private static Stack<BaseActivity> mActivities;

	/**
	 * 标记标题左右两边的类型:文字
	 */
	protected final int TITLE_TYPE_TEXT = 0;

	/**
	 * 标记标题左右两边的类型:图片
	 */
	protected final int TITLE_TYPE_IMG = 1;

	private View contentView;
	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	private GoogleApiClient client;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		if ( mActivities == null ) {
			mActivities = new Stack<BaseActivity>();
		}
		mActivities.push(this);

		InitView();
		int id = getLayoutID();
		if ( id != NO_LAYOUT ) {
			//R.layout.activity_base
			contentView = getLayoutInflater().inflate(id, null);
			setContentView(contentView);
			//透明状态栏
			// initstatusbar();
			init(savedInstanceState, contentView);
		} else {
			Log.e(TAG, "contentView is Null!");
		}

		//添加activity到applocation的activity集合中，方便一次性关闭
		BaseApplication.getApplication().activities.add(this);

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
		initListener();
	}

	//显示dialog
	public void showDialog(BaseDialog dialog, String tag) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		if (!dialog.isAdded()){
			transaction.add(dialog,tag);
			transaction.commit();
		}
	}

	//在setContentView前初始化的东西
	protected void InitView () {

	}

	/**
	 * 获取当前activity 对应的layout R.layout id
	 *
	 * @return id
	 */
	protected abstract int getLayoutID ();

	/**
	 * 类似onCreate()方法
	 */
	protected abstract void init (Bundle savedInstanceState, View contentView);


	/**
	 * onclick 监听事件方法
	 */
	public abstract void initListener ();

	/**
	 * 需在setContentView方法之后调用. 设置后,如果不对左侧的事件进行监听,默认的点击事件是结束当前界面.
	 * <p>
	 * 标题传资源id和字符串皆可.
	 * <p>
	 * 如果某一侧显示的是图片,则那一侧只能传对应的图片资源id.如果是文字,则资源id和字符串皆可.
	 *
	 * @param title     标题
	 * @param left      是否显示左侧的部分
	 * @param leftType  左侧的类型
	 * @param l         左侧部分内容
	 * @param right     是否显示右侧的部分
	 * @param rightType 右侧的类型
	 * @param r         右侧部分的内容
	 */
	protected void setTitle (Object title, boolean left, int leftType, Object l,
							 boolean right, int rightType, Object r) {
		try {
			TextView tvTitle = (TextView) findViewById(R.id.tv_title);
			TextView tvLeft = (TextView) findViewById(R.id.tv_title_left);
			LinearLayout llLeft = (LinearLayout) findViewById(R.id.ll_title_left);
			ImageView ivLeft = (ImageView) findViewById(R.id.iv_title_left);
			TextView tvRight = (TextView) findViewById(R.id.tv_title_right);
			ImageView ivRight = (ImageView) findViewById(R.id.iv_title_right);
			LinearLayout llRight = (LinearLayout) findViewById(R.id.ll_title_right);
			if ( title != null && title instanceof String ) {
				if ( !TextUtils.isEmpty((String) title) ) {
					tvTitle.setVisibility(View.VISIBLE);
					tvTitle.setText((String) title);
				} else {
					tvTitle.setVisibility(View.INVISIBLE);
				}
			} else if ( title != null && title instanceof Integer ) {
				if ( ( (Integer) title ) > 0 ) {
					tvTitle.setVisibility(View.VISIBLE);
					tvTitle.setText((Integer) title);
				} else {
					tvTitle.setVisibility(View.INVISIBLE);
				}
			}
			if ( left ) {
				llLeft.setVisibility(View.VISIBLE);
				if ( leftType == TITLE_TYPE_TEXT ) {
					ivLeft.setVisibility(View.GONE);
					tvLeft.setVisibility(View.VISIBLE);
					if ( l instanceof String ) {
						tvLeft.setText((String) l);
					} else if ( l instanceof Integer ) {
						tvLeft.setText((Integer) l);
					}
				} else if ( leftType == TITLE_TYPE_IMG ) {
					ivLeft.setVisibility(View.VISIBLE);
					tvLeft.setVisibility(View.GONE);
					if ( l instanceof Integer ) {
						ivLeft.setImageResource((Integer) l);
					}

				}
			} else {
				llLeft.setVisibility(View.INVISIBLE);
			}
			if ( right ) {
				llRight.setVisibility(View.VISIBLE);
				if ( rightType == TITLE_TYPE_TEXT ) {
					ivRight.setVisibility(View.GONE);
					tvRight.setVisibility(View.VISIBLE);
					if ( r instanceof String ) {
						tvRight.setText((String) r);
					} else if ( r instanceof Integer ) {
						tvRight.setText((Integer) r);
					}
				} else if ( rightType == TITLE_TYPE_IMG ) {
					ivRight.setVisibility(View.VISIBLE);
					tvRight.setVisibility(View.GONE);
					if ( r instanceof Integer ) {
						ivRight.setImageResource((Integer) r);
					}
				}
			} else {
				llRight.setVisibility(View.INVISIBLE);
			}

		} catch (Exception e) {

		}
	}

	/**
	 * 设置点击左上角的返回事件.默认是finish界面
	 */
	protected void registerBack () {
		LinearLayout llLeft = (LinearLayout) findViewById(R.id.ll_title_left);
		llLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View view) {
				leftDoWhat();
			}
		});
	}

	/**
	 * 设置点击右上角的返回事件.
	 */
	protected View rightDo () {
		LinearLayout llRight = (LinearLayout) findViewById(R.id.ll_title_right);
		llRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View view) {
				rightDoWhat();
			}
		});
		return llRight;
	}

	protected void rightDoWhat () {

	}

	protected void leftDoWhat () {
		finishActivities(this.getClass());
	}

	/**
	 * 关闭所有指定tag的activity
	 * activity 的 tag 可重载getTAG进行设置;
	 *
	 * @param cls 需要关闭的tag值
	 */
	protected void finishActivities (Class<? extends BaseActivity> cls) {
		if ( mActivities.empty() ) {
			return;
		}
		Stack<BaseActivity> temp = new Stack();

		for (BaseActivity activity : mActivities) {
			if ( activity != null && activity.getClass() == cls ) {
				temp.push(activity);
			}
		}

		for (BaseActivity activity : temp) {
			mActivities.remove(activity);
			activity.finish();
		}
		temp = null;

	}

	@Override
	public void netState (NetReceiver.NetState netCode) {
		switch (netCode) {
			case NET_NO:
				ToastUtil.makeShortText(BaseActivity.this, "没有网络连接");
				break;
			case NET_2G:
				ToastUtil.makeShortText(BaseActivity.this, "2g网络");
				break;
			case NET_3G:
				ToastUtil.makeShortText(BaseActivity.this, "3g网络");
				break;
			case NET_4G:
				ToastUtil.makeShortText(BaseActivity.this, "4g网络");
				break;
			case NET_WIFI:
				ToastUtil.makeShortText(BaseActivity.this, "WIFI网络");
				break;
			case NET_UNKNOWN:
				ToastUtil.makeShortText(BaseActivity.this, "未知网络");
				break;
			default:
				ToastUtil.makeShortText(BaseActivity.this, "不知道什么情况~>_<~");
		}
	}

	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	public Action getIndexApiAction () {
		Thing object = new Thing.Builder()
				.setName("Base Page") // TODO: Define a title for the content shown.
				// TODO: Make sure this auto-generated URL is correct.
				.setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
				.build();
		return new Action.Builder(Action.TYPE_VIEW)
				.setObject(object)
				.setActionStatus(Action.STATUS_TYPE_COMPLETED)
				.build();
	}

	@Override
	public void onStart () {
		super.onStart();
		client.connect();
		AppIndex.AppIndexApi.start(client, getIndexApiAction());
	}

	@Override
	public void onStop () {
		super.onStop();
		AppIndex.AppIndexApi.end(client, getIndexApiAction());
		client.disconnect();
	}

}
