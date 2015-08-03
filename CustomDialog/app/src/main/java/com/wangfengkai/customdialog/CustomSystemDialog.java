package com.wangfengkai.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangfengkai on 2015/7/14.
 * Email：wangfengkai@joyworks.com.cn
 */
public class CustomSystemDialog extends Dialog {

    public CustomSystemDialog(Context context) {
        super(context);
    }


    public CustomSystemDialog(Context context, int theme) {
        super(context, theme);
    }


    public static class Builder {
        private String title;
        private Context context;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;

        private DialogInterface.OnClickListener positiveButtonClickListener,negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CustomSystemDialog create() {
            final CustomSystemDialog dialog = new CustomSystemDialog(context, R.style.Dialog);
            View layout = LayoutInflater.from(context).inflate(R.layout.custom_system_dialog, null);

            ViewHolder viewHolder = new ViewHolder(layout);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (!TextUtils.isEmpty(title)) {
                viewHolder.tvTitle.setText(title);
                viewHolder.llytDialogTopTitle.setVisibility(View.VISIBLE);
                viewHolder.llytDialogTopTitle.setBackgroundResource(R.drawable.bg_custom_system_dialog_top);
                viewHolder.tvMessage.setBackgroundResource(R.color.bg_dialog);

            }else {
                viewHolder.llytDialogTopTitle.setVisibility(View.GONE);
                viewHolder.tvMessage.setBackgroundResource(R.drawable.bg_custom_system_dialog_top);
            }
            if (!TextUtils.isEmpty(message)) {
                viewHolder.tvMessage.setText(message);
            }
            if (!TextUtils.isEmpty(positiveButtonText)) {
                viewHolder.tvPositive.setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    viewHolder.tvPositive.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            }
            if (!TextUtils.isEmpty(negativeButtonText)) {
                viewHolder.tvNegative.setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    viewHolder.tvNegative.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            }
            controlLayout(viewHolder);
            return dialog;
        }

        private void controlLayout(ViewHolder viewHolder) {
            if (!TextUtils.isEmpty(positiveButtonText) && !TextUtils.isEmpty(negativeButtonText)){
                viewHolder.tvNegative.setVisibility(View.VISIBLE);
                viewHolder.tvPositive.setVisibility(View.VISIBLE);
                viewHolder.line.setVisibility(View.VISIBLE);
                viewHolder.tvNegative.setBackgroundResource(R.drawable.bg_custom_system_dialog_left_selector);
                viewHolder.tvPositive.setBackgroundResource(R.drawable.bg_custom_system_dialog_right_selector);

            }else if (!TextUtils.isEmpty(positiveButtonText) && TextUtils.isEmpty(negativeButtonText)){
                viewHolder.tvNegative.setVisibility(View.GONE);
                viewHolder.tvPositive.setVisibility(View.VISIBLE);
                viewHolder.line.setVisibility(View.GONE);
                viewHolder.tvPositive.setBackgroundResource(R.drawable.bg_custom_system_dialog_foot_selector);
            }else if (TextUtils.isEmpty(positiveButtonText) && !TextUtils.isEmpty(negativeButtonText)){
                viewHolder.tvNegative.setVisibility(View.VISIBLE);
                viewHolder.tvPositive.setVisibility(View.GONE);
                viewHolder.line.setVisibility(View.GONE);
                viewHolder.tvNegative.setBackgroundResource(R.drawable.bg_custom_system_dialog_foot_selector);
            }else {
                viewHolder.tvNegative.setVisibility(View.VISIBLE);
                viewHolder.tvPositive.setVisibility(View.VISIBLE);
                viewHolder.line.setVisibility(View.VISIBLE);
                viewHolder.tvNegative.setBackgroundResource(R.drawable.bg_custom_system_dialog_left_selector);
                viewHolder.tvPositive.setBackgroundResource(R.drawable.bg_custom_system_dialog_right_selector);
            }
        }

        /**
         * This class contains all butterknife-injected Views & Layouts from layout file 'custom_system_dialog.xml'
         * for easy to all layout elements.
         *
         * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
         */
        static class ViewHolder {
            @InjectView(R.id.tv_title)
            TextView tvTitle;
            @InjectView(R.id.llyt_dialog_top_title)
            LinearLayout llytDialogTopTitle;
            @InjectView(R.id.tv_message)
            TextView tvMessage;
            @InjectView(R.id.tv_negative)
            TextView tvNegative;
            @InjectView(R.id.line)
            View line;
            @InjectView(R.id.tv_positive)
            TextView tvPositive;

            ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
        }
    }

}
