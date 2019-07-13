package com.humor.debugactivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class BaseDebugActivity extends Activity {

	private ViewGroup mFunctions;

	/**
	 * 进入demo演示模块
	 * 
	 * @param moduleName
	 * @param clsActivity
	 */
	protected void startDebugModule(CharSequence moduleName,
			Class<? extends Activity> clsActivity) {
		Intent intentModule = new Intent();
		ComponentName cn = new ComponentName(this, clsActivity);
		intentModule.setComponent(cn);
		intentModule.putExtra("moduleTitle", moduleName);
		intentModule.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intentModule);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.debug_main);

		Intent intent = this.getIntent();
		String moduleName = "";
		if (intent != null) {
			moduleName = intent.getStringExtra("moduleTitle");
		}
		if (TextUtils.isEmpty(moduleName)) {
			moduleName = getApplication().getString(R.string.app_name);
		}

		((TextView) findViewById(R.id.txtTitleName)).setText(moduleName);

		findViewById(R.id.back).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BaseDebugActivity.this.finish();
			}
		});

		mFunctions = (ViewGroup) this.findViewById(R.id.llFunctions);

		mFunctions.addOnLayoutChangeListener(new OnLayoutChangeListener() {
			@Override
			public void onLayoutChange(View v, int left, int top, int right,
					int bottom, int oldLeft, int oldTop, int oldRight,
					int oldBottom) {
				if (initedButtons == false) {
					onInitButtons();
					initedButtons = true;
				}
			}
		});
	}

	protected boolean initedButtons = false;

	protected void onInitButtons() {

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	static final int PADDING = 10;

	public void addDemoButtons(DemoButton... bts) {
		LinearLayout llButtons = new LinearLayout(this);
		mFunctions.addView(llButtons);

		int widthScreen = ScreenUtils.getScreenWidth(BaseDebugActivity.this);
		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(widthScreen / bts.length - 2
				* PADDING, ScreenUtils.px2dp(80));

		for (int i = 0; i < bts.length; ++i) {
			DemoButton bt = bts[i];
			FrameLayout fbt = new FrameLayout(this);
			fbt.setPadding(PADDING, PADDING, PADDING, PADDING);
			fbt.addView(bt, lp);
			llButtons.addView(fbt);
		}

		DemoButton.nextColor();
	}

    private static Toast mLastToast = null;
    public void showTips(final CharSequence content) {
        Log.i("DebugUtil", "showTips: " + content);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mLastToast != null) {
                    mLastToast.cancel();
                }
                mLastToast = Toast.makeText(BaseDebugActivity.this, content,
                        Toast.LENGTH_LONG);
                mLastToast.show();
            }
        });
    }

}
