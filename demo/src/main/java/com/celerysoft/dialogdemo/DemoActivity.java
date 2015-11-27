package com.celerysoft.dialogdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private Button mBtnSetItems;
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
        mBtnSetItems = (Button) findViewById(R.id.demo_btn_set_items);
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
        mBtnSetItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnSetItems();
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

    private void onBtnSetItems() {
        String one = "1. single line";
        String two = "2. multi linns here, i don't know what to say, let us see what will be happened.";
        String three = "3. lol";
        String[] items = new String[]{one, two, three};

        final MaterialDesignDialog dialog = new MaterialDesignDialog(this);
        dialog.setTitle("1, 2, 3, what your choice")
                .setItems(items, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position /** or switch id, they are the same **/) {
                            case 0:
                                Toast.makeText(mContext, "You choose 1", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(mContext, "You choose 2", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(mContext, "You choose 3", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
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
                });
        dialog.show();
    }
}
