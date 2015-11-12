package com.celerysoft.dialogdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.celerysoft.materialdesigndialog.MaterialDesignDialog;

public class DemoActivity extends Activity {
    private MaterialDesignDialog mDialog;
    private Context mContext;

    private Button mBtnInit;
    private Button mBtnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo);

        mContext = this;

        defineView();
        defineListener();
    }

    private void defineView() {
        mBtnInit = (Button) findViewById(R.id.demo_btn_init);
        mBtnShow = (Button) findViewById(R.id.demo_btn_show);
    }

    private void defineListener() {
        mBtnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "This app determines your phone's location and shares" +
                        " it with Google in order to serve personalized alerts to you." +
                        " This allows for a better overall app experience.";
                mDialog = new MaterialDesignDialog(mContext);
                mDialog.setTitle("Permissions")
                        .setMessage(message)
                        .setNegativeButton("DECLINE", null)
                        .setPositiveButton("ACCEPT", null);
            }
        });
        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null) {
                    mDialog.show();
                }
            }
        });
    }
}
