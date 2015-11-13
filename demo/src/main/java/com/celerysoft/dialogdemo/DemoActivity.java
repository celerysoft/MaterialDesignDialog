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
    // field
    private MaterialDesignDialog mDialog;
    private Context mContext;

    // declare view
    private Button mBtnShow;
    private Button mBtnShowNotitle;
    private Button mBtnChangeBackground;
    private Button mBtnDarkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo);

        mContext = this;

        defineView();
        defineListener();
    }

    private void defineView() {
        mBtnShow = (Button) findViewById(R.id.demo_btn_show);
        mBtnShowNotitle = (Button) findViewById(R.id.demo_btn_show_notitle);
        mBtnChangeBackground = (Button) findViewById(R.id.demo_btn_change_background);
        mBtnDarkTheme = (Button) findViewById(R.id.demo_btn_dark_theme);
    }

    private void defineListener() {
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
        mBtnDarkTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnDarkThemeClick();
            }
        });
    }

    private void onBtnShowClick() {
        String message = "This dialog use material-design to design it."
                + " Use it if you really like it,"
                + " make it better if you feel it suck."
                + "\nThis dialog has 2 themes and"
                + " 4 styles, hope you can like it.";
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
                        Toast.makeText(mContext, "click ACCEPT", Toast.LENGTH_SHORT).show();
                    }
                });
        mDialog.show();
        Toast.makeText(mContext, "show dialog", Toast.LENGTH_SHORT).show();
    }

    private void onBtnShowNotitleclick() {
        String message = "This is a dialog without title."
                + " Sometime you need a dialog without title, this is.";
        final MaterialDesignDialog dialog = new MaterialDesignDialog(this);
        dialog.setMessage(message)
                .setPositiveButton(android.R.string.yes, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    private void onBtnChangeBackgroundClick() {
        String message = "This is a dialog that changes background color."
                + " Google does not recommend you change the background of dialog,"
                + " I just offer this method for fun, do not use it :D"
                + "\n\nTry to change background again.";
        int r = (int) (Math.random() * 0xff);
        int g = (int) (Math.random() * 0xff);
        int b = (int) (Math.random() * 0xff);
        final MaterialDesignDialog dialog = new MaterialDesignDialog(mContext);
        dialog.setBackgroundColor(Color.rgb(r, g, b))
                .setTitle("Colorful background")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                })
                .setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void onBtnDarkThemeClick() {
        String message = "It is a dark theme dialog. Sometimes you need a dark theme dialog to"
                + " fit your own theme, it is."
                + "\nWe offer only 2 themes:"
                + "\nMaterialDesignDialog.Theme.Light"
                + "\nand"
                + "\nMaterialDesignDialog.Theme.Dark";
        final MaterialDesignDialog dialog = new MaterialDesignDialog(this, MaterialDesignDialog.Theme.DARK);
        dialog.setTitle("Dark theme dialog")
                .setMessage(message)
                .setCanceledOnTouchOutside(true)
                .setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("KO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }
}
