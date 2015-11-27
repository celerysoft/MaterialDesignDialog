package com.celerysoft.materialdesigndialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
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
    //private Button mPositiveButton;
    private CharSequence mPositiveButtonText;
    private View.OnClickListener mPositiveButtonOnClickListener;
    //private Button mNegativeButton;
    private CharSequence mNegativeButtonText;
    private View.OnClickListener mNegativeButtonOnClickListener;
    private String[] mItems;
    private AdapterView.OnItemClickListener mItemsOnClickListener;
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
    }
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
        SCROLLABLE,
        /** without buttons on the bottom of dialog **/
        NO_BUTTONS
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

    @SuppressWarnings("unused")
    public void show() {
        if (!mIsShowed) {
            mBuilder = new Builder();
        } else {
            mDialog.show();
        }
        mIsShowed = true;
    }

    @SuppressWarnings("unused")
    public void hide() {
        if (mDialog != null && mIsShowed) {
            mDialog.hide();
        }
    }

    @SuppressWarnings("unused")
    public void dismiss() {
        if (mDialog != null && mIsShowed) {
            mDialog.dismiss();
        }
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setView(View view) {
        mView = view;
        if (mBuilder != null) {
            mBuilder.setView(view);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setContentView(View view) {
        mMessageContentView = view;
        if (mBuilder != null) {
            mBuilder.setContentView(mMessageContentView);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setBackground(Drawable drawable) {
        mBackgroundDrawable = drawable;
        mBackgroundResId = 0;
        mBackgroundColor = 0;
        if (mBuilder != null) {
            mBuilder.setBackground(mBackgroundDrawable);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setBackgroundResource(int resId) {
        mBackgroundResId = resId;
        mBackgroundDrawable = null;
        mBackgroundColor = 0;
        if (mBuilder != null) {
            mBuilder.setBackgroundResource(mBackgroundResId);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setBackgroundColor(int color) {
        if (mBuilder != null) {
            mBuilder.setBackgroundColor(mBackgroundResId);
        }
        mBackgroundColor = color;
        mBackgroundDrawable = null;
        mBackgroundResId = 0;
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setTitle(int resId) {
        mTitle = mContext.getString(resId);
        if (mBuilder != null) {
            mBuilder.setTitle(mTitle);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setTitle(CharSequence title) {
        mTitle = title;
        if (mBuilder != null) {
            mBuilder.setTitle(title);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setMessage(int resId) {
        mMessage = mContext.getString(resId);
        if (mBuilder != null) {
            mBuilder.setMessage(mMessage);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setMessage(CharSequence message) {
        mMessage = message;
        if (mBuilder != null) {
            mBuilder.setMessage(message);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setPositiveButton(int resId, final View.OnClickListener listener) {
        mPositiveButtonText = mContext.getString(resId);
        mPositiveButtonOnClickListener = listener;
        if (mBuilder != null) {
            mBuilder.setPositiveButton(mPositiveButtonText, listener);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setPositiveButton(String text, final View.OnClickListener listener) {
        mPositiveButtonText = text;
        mPositiveButtonOnClickListener = listener;
        if (mBuilder != null) {
            mBuilder.setPositiveButton(text, listener);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setNegativeButton(int resId, final View.OnClickListener listener) {
        mNegativeButtonText = mContext.getString(resId);
        mNegativeButtonOnClickListener = listener;
        if (mBuilder != null) {
            mBuilder.setNegativeButton(mNegativeButtonText, listener);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setNegativeButton(String text, final View.OnClickListener listener) {
        mNegativeButtonText = text;
        mNegativeButtonOnClickListener = listener;
        if (mBuilder != null) {
            mBuilder.setNegativeButton(text, listener);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setItems(String[] items, AdapterView.OnItemClickListener listener) {
        mItems = items;
        mItemsOnClickListener = listener;
        if (mBuilder != null) {
            mBuilder.setItems(items, listener);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setCanceledOnTouchOutside(boolean cancel) {
        this.mCancelable = cancel;
        if (mBuilder != null) {
            mBuilder.setCanceledOnTouchOutside(mCancelable);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public MaterialDesignDialog setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
        return this;
    }

    /**
     * caculate the height of listview dynamically
     * @param listView that need to caculate
     */
    public void autoAdjustContentListView(ListView listView, int fixHeight) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            if (listItem instanceof TextView) {
                if (mTheme == Theme.LIGHT) {
                    ((TextView) listItem).setTextColor(Color.argb(0xDE, 0x00, 0x00, 0x00));
                } else if (mTheme == Theme.DARK) {
                    ((TextView) listItem).setTextColor(Color.argb(0xDE, 0xff, 0xff, 0xff));
                }
            }
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int fix = dip2px(26);// SrollView contain ListView, must fix the height
        params.height = fixHeight + totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
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
                contentView = LayoutInflater.from(mContext).inflate(R.layout.material_design_dialog, null);
            } else if (mStyle == Style.SIDE_BY_SIDE_BUTTONS) {
                contentView = LayoutInflater.from(mContext).inflate(R.layout.material_design_dialog, null);
            } else if (mStyle == Style.SCROLLABLE) {
                contentView = LayoutInflater.from(mContext).inflate(R.layout.material_design_dialog, null);
            } else if (mStyle == Style.NO_BUTTONS) {
                contentView = LayoutInflater.from(mContext).inflate(R.layout.material_design_dialog, null);
            } else {
                contentView = LayoutInflater.from(mContext).inflate(R.layout.material_design_dialog, null);
            }
            contentView.setFocusable(true);
            contentView.setFocusableInTouchMode(true);

            mAlertDialogWindow.setBackgroundDrawableResource(R.drawable.material_dialog_window);
            mAlertDialogWindow.setContentView(contentView);

//            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
//                    WindowManager.LayoutParams.WRAP_CONTENT,
//                    WindowManager.LayoutParams.WRAP_CONTENT,
//                    WindowManager.LayoutParams.TYPE_PHONE,
//                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
//                    PixelFormat.TRANSLUCENT
//            );
            // define view
            mTitleView = (TextView) mAlertDialogWindow.findViewById(R.id.dialog_tv_title);
            mMessageView = (TextView) mAlertDialogWindow.findViewById(R.id.dialog_tv_message);
            mButtonLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.dialog_button_layout);
            // set up dialog
            if (mView != null) {
                LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.dialog_view);
                linearLayout.removeAllViews();
                linearLayout.addView(mView);
            }
            // set up title
            if (mTitle != null) {
                setTitle(mTitle);
            } else {
                mTitleView.setVisibility(View.GONE);
            }
            // set up message
            if (mMessage != null) {
                setMessage(mMessage);
            }
            // set up positive button
            if (mPositiveButtonText != null) {
                Builder.this.setPositiveButton(mPositiveButtonText, mPositiveButtonOnClickListener);
            }
            // set up negative button
            if (mNegativeButtonText != null) {
                Builder.this.setNegativeButton(mNegativeButtonText, mNegativeButtonOnClickListener);
            }
            if (mPositiveButtonText == null && mNegativeButtonText == null) {
                mButtonLayout.setVisibility(View.GONE);
            }
            // set up items
            if (mItems != null) {
                Builder.this.setItems(mItems, mItemsOnClickListener);
            }
            // set up background
            if (mBackgroundDrawable != null) {
                Builder.this.setBackground(mBackgroundDrawable);
            } else if(mBackgroundResId != 0) {
                Builder.this.setBackgroundResource(mBackgroundResId);
            } else if (mBackgroundColor != 0) {
                Builder.this.setBackgroundColor(mBackgroundColor);
            } else {
                Drawable defaultBackground;
                int defaultBackgroundResId;
                if (mTheme == Theme.LIGHT) {
                    defaultBackgroundResId = R.drawable.material_dialog_background_light_theme;
                } else {
                    defaultBackgroundResId = R.drawable.material_dialog_background_dark_theme;
                }
                try {
                    defaultBackground = mContext.getResources().getDrawable(defaultBackgroundResId, null);
                } catch (NoSuchMethodError e) {
                    defaultBackground = mContext.getResources().getDrawable(defaultBackgroundResId);
                }
                Builder.this.setBackground(defaultBackground);
            }
            // set up contentView
            if (mMessageContentView != null) {
                this.setContentView(mMessageContentView);
            }
            // set up CanceledOnTouchOutsid
            mDialog.setCanceledOnTouchOutside(mCancelable);
            // set up OnDismissListener
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
            Button positiveButton = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    dip2px(36));
            positiveButton.setLayoutParams(params);
            positiveButton.setPadding(dip2px(8), 0, dip2px(8), 0);
            positiveButton.setMinWidth(dip2px(64));
            int backgroundResId;
            if (mTheme == Theme.LIGHT) {
                backgroundResId = R.drawable.material_dialog_button_light_theme;
            } else {
                backgroundResId = R.drawable.material_dialog_button_dark_theme;
            }
            positiveButton.setBackgroundResource(backgroundResId);
            positiveButton.setText(text);
            positiveButton.setTextSize(14);
            int textColor;
            try {
                textColor = mContext.getResources().getColor(R.color.dialog_button, null);
            } catch (NoSuchMethodError e) {
                textColor = mContext.getResources().getColor(R.color.dialog_button);
            }
            positiveButton.setTextColor(textColor);
            positiveButton.setGravity(Gravity.CENTER);
            positiveButton.setOnClickListener(listener);
            if (isLollipop()) {
                positiveButton.setBackgroundResource(android.R.color.transparent);
            }
            mButtonLayout.addView(positiveButton);
        }

        /**
         * set negative button
         * @param text text of button
         * @param listener OnClickListener of button
         */
        public void setNegativeButton(CharSequence text, final View.OnClickListener listener) {
            Button negativeButton = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    dip2px(36));
            negativeButton.setLayoutParams(params);
            negativeButton.setPadding(dip2px(8), 0, dip2px(8), 0);
            negativeButton.setMinWidth(dip2px(64));
            int backgroundResId;
            if (mTheme == Theme.LIGHT) {
                backgroundResId = R.drawable.material_dialog_button_light_theme;
            } else {
                backgroundResId = R.drawable.material_dialog_button_dark_theme;
            }
            negativeButton.setBackgroundResource(backgroundResId);
            negativeButton.setLayoutParams(params);
            negativeButton.setText(text);
            negativeButton.setTextSize(14);
            int textColor;
            try {
                textColor = mContext.getResources().getColor(R.color.dialog_button, null);
            } catch (NoSuchMethodError e) {
                textColor = mContext.getResources().getColor(R.color.dialog_button);
            }
            negativeButton.setTextColor(textColor);
            negativeButton.setGravity(Gravity.CENTER);
            negativeButton.setOnClickListener(listener);
            if (isLollipop()) {
                negativeButton.setBackgroundResource(android.R.color.transparent);
            }
            if (mButtonLayout.getChildCount() > 0) {
                params.setMargins(0, 0, dip2px(8), 0);
                negativeButton.setLayoutParams(params);
                negativeButton.setPadding(dip2px(8), 0, dip2px(8), 0);
                negativeButton.setMinWidth(dip2px(64));
                mButtonLayout.addView(negativeButton, 1);
            } else {
                mButtonLayout.addView(negativeButton);
            }
        }

        public void setItems(String[] items, AdapterView.OnItemClickListener listener) {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                    mContext, R.layout.simple_list_item);
            arrayAdapter.addAll(items);

            ListView listView = new ListView(mContext);
            listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            float scale = mContext.getResources().getDisplayMetrics().density;
            int dpAsPixels = (int) (8 * scale + 0.5f);
            //listView.setPadding(0, dpAsPixels, 0, dpAsPixels);
            listView.setDividerHeight(0);
            listView.setBackgroundColor(Color.argb(222, 50, 0, 0));
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(listener);

            setContentView(listView);
        }

        public void setView(View view) {
            LinearLayout l = (LinearLayout) mAlertDialogWindow.findViewById(R.id.dialog_view);
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
            LinearLayout contentViewParent;
            if (contentView instanceof ListView) {
                //autoAdjustContentListView((ListView) contentView, 15);
                contentViewParent = (LinearLayout) mAlertDialogWindow.findViewById(R.id.dialog_main_content_view);
            } else {
                contentViewParent = (LinearLayout) mAlertDialogWindow.findViewById(R.id.dialog_content_view);
            }

            if (contentViewParent != null) {
                contentViewParent.removeAllViews();
                contentViewParent.addView(contentView);

//                ScrollView v =  (ScrollView) mAlertDialogWindow.findViewById(R.id.dialog_scroll_view);
//                boolean isFullScroll = v.fullScroll(ScrollView.FOCUS_DOWN);
//                int scroll = v.getScrollY();
//                int scrollAmount = v.getMaxScrollAmount();
//                boolean isFillViewport = v.isFillViewport();
//                String s = "";

                for (int i = 0; i < contentViewParent.getChildCount(); i++) {
                    if (contentViewParent.getChildAt(i) instanceof AutoCompleteTextView) {
                        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) contentViewParent.getChildAt(i);
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
