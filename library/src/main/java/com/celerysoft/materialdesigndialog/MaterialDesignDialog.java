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
    private boolean mIsShowed = false;
    private Dialog mDialog;
    private MaterialDesignDialog.Builder mBuilder;
    private View mView;
    private View mMessageContentView;
    private CharSequence mTitle;
    private CharSequence mMessage;
    private Button mPositiveButton;
    private CharSequence mPositiveButtonText;
    private View.OnClickListener mPositiveButtonOnClickListener;
    private Button mNegativeButton;
    private CharSequence mNegativeButtonText;
    private View.OnClickListener mNegativeButtonOnClickListener;
    private Drawable mBackgroundDrawable;
    private int mBackgroundResId;
    private int mBackgroundColor;
    private DialogInterface.OnDismissListener mOnDismissListener;

    /** dialog theme **/
    private Theme mTheme;
    public enum Theme {
        /** light theme **/
        LIGHT,
        /** dark theme **/
        DARK
    };
    /** dialog style **/
    private Style mStyle;
    public enum Style {
        /** normal style **/
        NORMAL,
        /** buttons in different row **/
        STACKED_FULL_WIDTH_BUTTONS,
        /** only 2 buttons, and in the same row **/
        SIDE_BY_SIDE_BUTTONS,
        /** scrollable, use this style when dialog contain a listview **/
        SCROLLABLE
    }

    public MaterialDesignDialog(Context context) {
        this(context, Style.NORMAL, Theme.LIGHT);
    }

    public MaterialDesignDialog(Context context, Style style) {
        this(context, style, Theme.LIGHT);
    }

    public MaterialDesignDialog(Context context, Theme theme) {
        this(context, Style.NORMAL, theme);
    }

    public MaterialDesignDialog(Context context, Style style, Theme theme) {
        mContext = context;
        mStyle = style;
        mTheme = theme;
    }

    public void show() {
        if (!mIsShowed) {
            mBuilder = new Builder();
        } else {
            mDialog.show();
        }
        mIsShowed = true;
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
        mBackgroundResId = 0;
        mBackgroundColor = 0;
        if (mBuilder != null) {
            mBuilder.setBackground(mBackgroundDrawable);
        }
        return this;
    }

    public MaterialDesignDialog setBackgroundResource(int resId) {
        mBackgroundResId = resId;
        mBackgroundDrawable = null;
        mBackgroundColor = 0;
        if (mBuilder != null) {
            mBuilder.setBackgroundResource(mBackgroundResId);
        }
        return this;
    }

    public MaterialDesignDialog setBackgroundColor(int color) {
        if (mBuilder != null) {
            mBuilder.setBackgroundColor(mBackgroundResId);
        }
        mBackgroundColor = color;
        mBackgroundDrawable = null;
        mBackgroundResId = 0;
        return this;
    }


    public void dismiss() {
        mDialog.dismiss();
    }



    public MaterialDesignDialog setTitle(int resId) {
        mTitle = mContext.getString(resId);
        if (mBuilder != null) {
            mBuilder.setTitle(mTitle);
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
        mMessage = mContext.getString(resId);
        if (mBuilder != null) {
            mBuilder.setMessage(mMessage);
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
        mPositiveButtonText = mContext.getString(resId);
        mPositiveButtonOnClickListener = listener;
        if (mBuilder != null) {
            mBuilder.setPositiveButton(mPositiveButtonText, listener);
        }
        return this;
    }


    public MaterialDesignDialog setPositiveButton(String text, final View.OnClickListener listener) {
        mPositiveButtonText = text;
        mPositiveButtonOnClickListener = listener;
        if (mBuilder != null) {
            mBuilder.setPositiveButton(text, listener);
        }
        return this;
    }

    public MaterialDesignDialog setNegativeButton(int resId, final View.OnClickListener listener) {
        mNegativeButtonText = mContext.getString(resId);
        mNegativeButtonOnClickListener = listener;
        if (mBuilder != null) {
            mBuilder.setNegativeButton(mNegativeButtonText, listener);
        }
        return this;
    }

    public MaterialDesignDialog setNegativeButton(String text, final View.OnClickListener listener) {
        mNegativeButtonText = text;
        mNegativeButtonOnClickListener = listener;
        if (mBuilder != null) {
            mBuilder.setNegativeButton(text, listener);
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
     * caculate the height of listview dynamically
     * @param listView that need to caculate
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
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

        private TextView mTitleView;
        private TextView mMessageView;
        private Window mAlertDialogWindow;
        private LinearLayout mButtonLayout;

        private Builder() {
            mDialog = new AlertDialog.Builder(mContext).create();
            mDialog.show();

            mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);

            mAlertDialogWindow = mDialog.getWindow();
            View contentView;
            if (mStyle == Style.STACKED_FULL_WIDTH_BUTTONS) {
                contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog, null);
            } else if (mStyle == Style.SIDE_BY_SIDE_BUTTONS) {
                contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog, null);
            } else if (mStyle == Style.SCROLLABLE) {
                contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog, null);
            } else {
                contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog, null);
            }
            contentView.setFocusable(true);
            contentView.setFocusableInTouchMode(true);

            mAlertDialogWindow.setBackgroundDrawableResource(R.drawable.material_dialog_window);

            mAlertDialogWindow.setContentView(contentView);

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
            if (mTitle != null) {
                setTitle(mTitle);
            } else {
                mTitleView.setVisibility(View.GONE);
            }
            if (mMessage != null) {
                setMessage(mMessage);
            }
            if (mPositiveButtonText != null) {
                Builder.this.setPositiveButton(mPositiveButtonText, mPositiveButtonOnClickListener);
            }
            if (mNegativeButtonText != null) {
                Builder.this.setNegativeButton(mNegativeButtonText, mNegativeButtonOnClickListener);
            }
            if (mBackgroundDrawable != null) {
                Builder.this.setBackground(mBackgroundDrawable);
            } else if(mBackgroundResId != 0) {
                Builder.this.setBackgroundResource(mBackgroundResId);
            } else if (mBackgroundColor != 0) {
                Builder.this.setBackgroundColor(mBackgroundColor);
            } else {
                Drawable defaultBackground;
                if (mTheme == Theme.LIGHT) {
                    defaultBackground = mContext.getResources()
                            .getDrawable(R.drawable.material_dialog_background_light_theme);
                } else {
                    defaultBackground = mContext.getResources()
                            .getDrawable(R.drawable.material_dialog_background_dark_theme);
                }
                Builder.this.setBackground(defaultBackground);
            }

            if (mMessageContentView != null) {
                this.setContentView(mMessageContentView);
            }
            mDialog.setCanceledOnTouchOutside(mCancelable);
            if (mOnDismissListener != null) {
                mDialog.setOnDismissListener(mOnDismissListener);
            }
        }

        public void setTitle(CharSequence title) {
            mTitleView.setText(title);
            if (mTheme == Theme.LIGHT) {
                mTitleView.setTextColor(Color.argb(0xDE, 0x00, 0x00, 0x00));
            } else if (mTheme == Theme.DARK) {
                mTitleView.setTextColor(Color.argb(0xDE, 0xff, 0xff, 0xff));
            }
        }

        public void setMessage(CharSequence message) {
            mMessageView.setText(message);
            if (mTheme == Theme.LIGHT) {
                mMessageView.setTextColor(Color.argb(0x8A, 0x00, 0x00, 0x00));
            } else if (mTheme == Theme.DARK) {
                mMessageView.setTextColor(Color.argb(0x8A, 0xff, 0xff, 0xff));
            }
        }

       /**
         * set positive button
         * @param text text fo button
         * @param listener OnclickListener of button
         */
        public void setPositiveButton(CharSequence text, final View.OnClickListener listener) {
            mPositiveButton = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    dip2px(88),
                    dip2px(36));
            mPositiveButton.setLayoutParams(params);
            int backgroundResId;
            if (mTheme == Theme.LIGHT) {
                backgroundResId = R.drawable.material_dialog_button_light_theme;
            } else {
                backgroundResId = R.drawable.material_dialog_button_dark_theme;
            }
            mPositiveButton.setBackgroundResource(backgroundResId);
            mPositiveButton.setText(text);
            mPositiveButton.setTextSize(14);
            mPositiveButton.setTextColor(mContext.getResources().getColor(R.color.dialog_button_positive));
            mPositiveButton.setGravity(Gravity.CENTER);
            mPositiveButton.setOnClickListener(listener);
            if (isLollipop()) {
                mPositiveButton.setBackgroundResource(android.R.color.transparent);
            }
            mButtonLayout.addView(mPositiveButton);
        }



        /**
         * set negative button
         * @param text text of button
         * @param listener OnClickListener of button
         */
        public void setNegativeButton(CharSequence text, final View.OnClickListener listener) {
            mNegativeButton = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    dip2px(88),
                    dip2px(36)
            );
            mNegativeButton.setLayoutParams(params);
            mNegativeButton.setText(text);
            mNegativeButton.setTextSize(14);
            int textColor;
            int backgroundResId;
            if (mTheme == Theme.LIGHT) {
                textColor = mContext.getResources().getColor(R.color.dialog_button_light_theme);
                backgroundResId = R.drawable.material_dialog_button_light_theme;
            } else {
                textColor = mContext.getResources().getColor(R.color.dialog_button_dark_theme);
                backgroundResId = R.drawable.material_dialog_button_dark_theme;
            }
            mNegativeButton.setTextColor(textColor);
            mNegativeButton.setBackgroundResource(backgroundResId);
            mNegativeButton.setGravity(Gravity.CENTER);
            mNegativeButton.setOnClickListener(listener);
            if (isLollipop()) {
                mNegativeButton.setBackgroundResource(android.R.color.transparent);
            }
            if (mButtonLayout.getChildCount() > 0) {
                mButtonLayout.addView(mNegativeButton, 1);
            } else {
                mButtonLayout.addView(mNegativeButton);
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
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.dialog_background);
            try {
                linearLayout.setBackground(drawable);
            } catch (NoSuchMethodError e) {
                linearLayout.setBackgroundDrawable(drawable);
            }
        }

        public void setBackgroundResource(int resId) {
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.dialog_background);
            linearLayout.setBackgroundResource(resId);
        }

        public void setBackgroundColor(int color) {
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.dialog_background);
            linearLayout.setBackgroundColor(color);
        }

        public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            mDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }
    }
}
