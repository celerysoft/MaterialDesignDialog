package com.celerysoft.materialdesigndialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by CelerySoft on 2015-11-12.
 * Material Design Dialog
 */
public class MaterialDesignDialog {
    private Context mContext;
    private boolean mCancelable;
    private Dialog mAlertDialog;
    private MaterialDesignDialog.Builder mBuilder;
    private View mView;
    private int mTitleResId;
    private CharSequence mTitle;
    private int mMessageResId;
    private CharSequence mMessage;
    private Button mPositiveButton;
    private LinearLayout.LayoutParams mLayoutParams;
    private Button mNegativeButton;
    private boolean mHasShow = false;
    private Drawable mBackgroundDrawable;
    private int mBackgroundResId;
    private View mMessageContentView;
    private DialogInterface.OnDismissListener mOnDismissListener;

    public MaterialDesignDialog(Context context) {
        this.mContext = context;
    }

    public void show() {
        if (!mHasShow) {
            mBuilder = new Builder();
        } else {
            mAlertDialog.show();
        }
        mHasShow = true;
    }

    public MaterialDesignDialog setView(View view) {
        mView = view;
        if (mBuilder != null) {
            mBuilder.setView(view);
        }
        return this;
    }

    public MaterialDesignDialog setContentView(View view) {
        mMessageContentView = view;
        if (mBuilder != null) {
            mBuilder.setContentView(mMessageContentView);
        }
        return this;
    }

    public MaterialDesignDialog setBackground(Drawable drawable) {
        mBackgroundDrawable = drawable;
        if (mBuilder != null) {
            mBuilder.setBackground(mBackgroundDrawable);
        }
        return this;
    }

    public MaterialDesignDialog setBackgroundResource(int resId) {
        mBackgroundResId = resId;
        if (mBuilder != null) {
            mBuilder.setBackgroundResource(mBackgroundResId);
        }
        return this;
    }


    public void dismiss() {
        mAlertDialog.dismiss();
    }



    public MaterialDesignDialog setTitle(int resId) {
        mTitleResId = resId;
        if (mBuilder != null) {
            mBuilder.setTitle(resId);
        }
        return this;
    }

    public MaterialDesignDialog setTitle(CharSequence title) {
        mTitle = title;
        if (mBuilder != null) {
            mBuilder.setTitle(title);
        }
        return this;
    }

    public MaterialDesignDialog setMessage(int resId) {
        mMessageResId = resId;
        if (mBuilder != null) {
            mBuilder.setMessage(resId);
        }
        return this;
    }

    public MaterialDesignDialog setMessage(CharSequence message) {
        mMessage = message;
        if (mBuilder != null) {
            mBuilder.setMessage(message);
        }
        return this;
    }

    public MaterialDesignDialog setPositiveButton(int resId, final View.OnClickListener listener) {
        mPositiveButton = new Button(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                dip2px(88),
                dip2px(36)
        );
        mPositiveButton.setLayoutParams(params);
        mPositiveButton.setBackgroundResource(R.drawable.material_dialog_button);
        mPositiveButton.setTextColor(Color.argb(255, 35, 159, 242));
        mPositiveButton.setText(resId);
        mPositiveButton.setGravity(Gravity.CENTER);
        mPositiveButton.setTextSize(14);
        mPositiveButton.setOnClickListener(listener);
        if (isLollipop()) {
            mPositiveButton.setBackgroundResource(android.R.color.transparent);
        }
        return this;
    }


    public MaterialDesignDialog setPositiveButton(String text, final View.OnClickListener listener) {
        mPositiveButton = new Button(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                dip2px(88),
                dip2px(36)
        );
        mPositiveButton.setLayoutParams(params);
        mPositiveButton.setBackgroundResource(R.drawable.material_dialog_button);
        mPositiveButton.setTextColor(Color.argb(255, 35, 159, 242));
        mPositiveButton.setText(text);
        mPositiveButton.setGravity(Gravity.CENTER);
        mPositiveButton.setTextSize(14);
        mPositiveButton.setOnClickListener(listener);
        if (isLollipop()) {
            mPositiveButton.setBackgroundResource(android.R.color.transparent);
        }
        return this;
    }

    public MaterialDesignDialog setNegativeButton(int resId, final View.OnClickListener listener) {
        mNegativeButton = new Button(mContext);
        mLayoutParams = new LinearLayout.LayoutParams(
                dip2px(88),
                dip2px(36)
        );
        mNegativeButton.setLayoutParams(mLayoutParams);
        mNegativeButton.setBackgroundResource(R.drawable.material_dialog_button);
        mNegativeButton.setText(resId);
        mNegativeButton.setTextColor(Color.argb(222, 0, 0, 0));
        mNegativeButton.setTextSize(14);
        mNegativeButton.setGravity(Gravity.CENTER);
        mNegativeButton.setOnClickListener(listener);
        if (isLollipop()) {
            mNegativeButton.setBackgroundResource(android.R.color.transparent);
        }

        return this;
    }

    public MaterialDesignDialog setNegativeButton(String text, final View.OnClickListener listener) {
        mNegativeButton = new Button(mContext);
        mLayoutParams = new LinearLayout.LayoutParams(
                dip2px(88),
                dip2px(36)
        );
        mNegativeButton.setLayoutParams(mLayoutParams);
        mNegativeButton.setBackgroundResource(R.drawable.material_dialog_button);
        mNegativeButton.setText(text);
        mNegativeButton.setTextColor(Color.argb(222, 0, 0, 0));
        mNegativeButton.setTextSize(14);
        mNegativeButton.setGravity(Gravity.CENTER);
        mNegativeButton.setOnClickListener(listener);
        if (isLollipop()) {
            mNegativeButton.setBackgroundResource(android.R.color.transparent);
        }

        return this;
    }

    public MaterialDesignDialog setCanceledOnTouchOutside(boolean cancel) {
        this.mCancelable = cancel;
        if (mBuilder != null) {
            mBuilder.setCanceledOnTouchOutside(mCancelable);
        }
        return this;
    }

    public MaterialDesignDialog setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
        return this;
    }

    /**
     * 动态测量listview-Item的高度
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }


    private class Builder {

        private TextView     mTitleView;
        private TextView     mMessageView;
        private Window       mAlertDialogWindow;
        private LinearLayout mButtonLayout;

        private Builder() {
            mAlertDialog = new AlertDialog.Builder(mContext).create();
            mAlertDialog.show();

            mAlertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            mAlertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);

            mAlertDialogWindow = mAlertDialog.getWindow();
            View contv = LayoutInflater.from(mContext).inflate(R.layout.dialog, null);
            contv.setFocusable(true);
            contv.setFocusableInTouchMode(true);

            mAlertDialogWindow.setBackgroundDrawableResource(R.drawable.material_dialog_window);

            mAlertDialogWindow.setContentView(contv);

            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                    PixelFormat.TRANSLUCENT
            );

            mTitleView = (TextView) mAlertDialogWindow.findViewById(R.id.title);
            mMessageView = (TextView) mAlertDialogWindow.findViewById(R.id.message);
            mButtonLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.buttonLayout);
            if (mView != null) {
                LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.contentView);
                linearLayout.removeAllViews();
                linearLayout.addView(mView);
            }
            if (mTitleResId != 0) {
                setTitle(mTitleResId);
            }
            if (mTitle != null) {
                setTitle(mTitle);
            }
            if (mTitle == null && mTitleResId == 0) {
                mTitleView.setVisibility(View.GONE);
            }
            if (mMessageResId != 0) {
                setMessage(mMessageResId);
            }
            if (mMessage != null) {
                setMessage(mMessage);
            }
            if (mPositiveButton != null) {
                mButtonLayout.addView(mPositiveButton);
            }
            if (mLayoutParams != null && mNegativeButton != null) {
                if (mButtonLayout.getChildCount() > 0) {
                    mButtonLayout.addView(mNegativeButton, 1);
                } else {
                    mButtonLayout.addView(mNegativeButton);
                }
            }
            if (mBackgroundResId != 0) {
                LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.material_background);
                linearLayout.setBackgroundResource(mBackgroundResId);
            }
            if (mBackgroundDrawable != null) {
                LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.material_background);
                try {
                    linearLayout.setBackground(mBackgroundDrawable);
                } catch (NoSuchMethodError e) {
                    linearLayout.setBackgroundDrawable(mBackgroundDrawable);
                }

            }

            if (mMessageContentView != null) {
                this.setContentView(mMessageContentView);
            }
            mAlertDialog.setCanceledOnTouchOutside(mCancelable);
            if (mOnDismissListener != null) {
                mAlertDialog.setOnDismissListener(mOnDismissListener);
            }
        }

        public void setTitle(int resId) {
            mTitleView.setText(resId);
        }

        public void setTitle(CharSequence title) {
            mTitleView.setText(title);
        }

        public void setMessage(int resId) {
            mMessageView.setText(resId);
        }

        public void setMessage(CharSequence message) {
            mMessageView.setText(message);
        }

        /**
         * 设置确定按钮
         * @param text 按钮名字
         * @param listener 监听器
         */
        public void setPositiveButton(String text, final View.OnClickListener listener) {
            Button button = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(88), dip2px(36));
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.material_dialog_button);
            button.setTextColor(Color.argb(255, 35, 159, 242));
            button.setText(text);
            button.setGravity(Gravity.CENTER);
            button.setTextSize(14);
            button.setOnClickListener(listener);
            mButtonLayout.addView(button);
        }

        /**
         * 设置取消按钮
         * @param text 按钮文字
         * @param listener 监听器
         */
        public void setNegativeButton(String text, final View.OnClickListener listener) {
            Button button = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(88), dip2px(36));
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.material_dialog_button);
            button.setText(text);
            button.setTextColor(Color.argb(222, 0, 0, 0));
            button.setTextSize(14);
            button.setGravity(Gravity.CENTER);
            button.setOnClickListener(listener);
            if (mButtonLayout.getChildCount() > 0) {
                button.setLayoutParams(params);
                mButtonLayout.addView(button, 1);
            } else {
                button.setLayoutParams(params);
                mButtonLayout.addView(button);
            }
        }

        public void setView(View view) {
            LinearLayout l = (LinearLayout) mAlertDialogWindow.findViewById(R.id.contentView);
            l.removeAllViews();
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);

            view.setOnFocusChangeListener(
                    new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            System.out.println("-->" + hasFocus);
                            mAlertDialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                            // show imm
                            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.toggleSoftInput(
                                    InputMethodManager.SHOW_FORCED,
                                    InputMethodManager.HIDE_IMPLICIT_ONLY
                            );

                            if (hasFocus) {
                                imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
                            } else {
                                v.requestFocus();
                                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                            }
                        }
                    }
            );

            l.addView(view);

            if (view instanceof ViewGroup) {

                ViewGroup viewGroup = (ViewGroup) view;

                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(i) instanceof EditText) {
                        EditText editText = (EditText) viewGroup.getChildAt(i);
                        editText.setFocusable(true);
                        editText.requestFocus();
                        editText.setFocusableInTouchMode(true);
                    }
                }
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(i) instanceof AutoCompleteTextView) {
                        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) viewGroup.getChildAt(i);
                        autoCompleteTextView.setFocusable(true);
                        autoCompleteTextView.requestFocus();
                        autoCompleteTextView.setFocusableInTouchMode(true);
                    }
                }
            }
        }

        public void setContentView(View contentView) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            contentView.setLayoutParams(layoutParams);
            if (contentView instanceof ListView) {
                setListViewHeightBasedOnChildren((ListView) contentView);
            }
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.message_content_view);
            if (linearLayout != null) {
                linearLayout.removeAllViews();
                linearLayout.addView(contentView);

                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    if (linearLayout.getChildAt(i) instanceof AutoCompleteTextView) {
                        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) linearLayout.getChildAt(i);
                        autoCompleteTextView.setFocusable(true);
                        autoCompleteTextView.requestFocus();
                        autoCompleteTextView.setFocusableInTouchMode(true);
                    }
                }
            }
        }

        public void setBackground(Drawable drawable) {
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.material_background);
            try {
                linearLayout.setBackground(drawable);
            } catch (NoSuchMethodError e) {
                linearLayout.setBackgroundDrawable(drawable);
            }
        }

        public void setBackgroundResource(int resId) {
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.material_background);
            linearLayout.setBackgroundResource(resId);
        }


        public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            mAlertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }
    }
}
