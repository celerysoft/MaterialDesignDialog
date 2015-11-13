package com.celerysoft.dialogdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.celerysoft.materialdesigndialog.MaterialDesignDialog;

public class DemoActivity extends Activity {
    private MaterialDesignDialog mDialog;
    private Context mContext;

    private Button mBtnInit;
    private Button mBtnShow;
    private Button mBtnShowNotitle;
    private Button mBtnChangeBackground;

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
        mBtnShowNotitle = (Button) findViewById(R.id.demo_btn_show_notitle);
        mBtnChangeBackground = (Button) findViewById(R.id.demo_btn_change_background);
    }

    private void defineListener() {
        mBtnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnInitClick();
            }
        });
        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnShowClick();
            }
        });
        mBtnShowNotitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnShowNotitleclick();
            }
        });
        mBtnChangeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnChangeBackgroundClick();
            }
        });
    }

    private void onBtnInitClick() {
        initDialog();
    }

    private void onBtnShowClick() {
        if (mDialog != null) {
            mDialog.show();
        }
        Toast.makeText(mContext, "show dialog", Toast.LENGTH_SHORT).show();
    }

    private void onBtnShowNotitleclick() {
        final MaterialDesignDialog dialog = new MaterialDesignDialog(this);
        dialog.setMessage("This is a dialog without title. Sometime you need a dialog without title, this is.")
                .setPositiveButton(android.R.string.yes, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    private void onBtnChangeBackgroundClick() {
        final MaterialDesignDialog dialog = new MaterialDesignDialog(mContext);
        int r = (int) (Math.random() * 0xff);
        int g = (int) (Math.random() * 0xff);
        int b = (int) (Math.random() * 0xff);
        dialog.setBackgroundColor(Color.rgb(r, g, b));
        dialog.setCanceledOnTouchOutside(true);
        dialog.setMessage("This is a dialog that changes background color. Google does not recommend you change the background of dialog, I just offer this method for fun, do not use it :D")
                .setPositiveButton(android.R.string.yes, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
        Toast.makeText(getApplicationContext(), "Try to change background again", Toast.LENGTH_SHORT).show();
    }

    private void initDialog() {
        String message = "This app determines your phone's location and shares" +
                " it with Google in order to serve personalized alerts to you." +
                " This allows for a better overall app experience.";
        mDialog = new MaterialDesignDialog(mContext);
        mDialog.setTitle("Permissions")
                .setMessage(message)
                .setNegativeButton("DECLINE", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        Toast.makeText(mContext, "click DECLINE", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("ACCEPT", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        Toast.makeText(mContext, "show dialog", Toast.LENGTH_SHORT).show();
                        Toast.makeText(mContext, "click ACCEPT", Toast.LENGTH_SHORT).show();
                    }
                });
        Toast.makeText(mContext, "init dialog", Toast.LENGTH_SHORT).show();
    }
}
