package com.humor.jni;

import android.os.Bundle;

import com.humor.debugactivity.BaseDebugActivity;
import com.humor.debugactivity.DebugButton;

public class MainActivity extends BaseDebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addDemoButtons(new DebugButton(this, "test1", null));
    }
}

