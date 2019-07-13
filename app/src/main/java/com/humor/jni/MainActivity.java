package com.humor.jni;

import android.os.Bundle;

import com.humor.debugactivity.BaseDebugActivity;
import com.humor.debugactivity.DemoButton;

public class MainActivity extends BaseDebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addDemoButtons(new DemoButton(this, "test1", null), new DemoButton(this, "test2",null));
        addDemoButtons(new DemoButton(this, "test3", null));
    }
}

