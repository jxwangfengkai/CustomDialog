package com.wangfengkai.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangfengkai on 2015/8/3.
 * Email：wangfengkai@joyworks.com.cn
 */
public class CameraAlbumDialog extends Dialog {
    public CameraAlbumDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private OnClickListener cameraClickListener, albumClickListener,negativeClickListener;

        public Builder(Context context) {
            this.context = context;
        }
        public Builder setCameraClickListener(OnClickListener cameraClickListener){
            this.cameraClickListener = cameraClickListener;
            return this;
        }
        public Builder setAlbumClickListener(OnClickListener albumClickListener){
            this.albumClickListener = albumClickListener;
            return this;
        }
        public Builder setNegativeClickListener(OnClickListener negativeClickListener){
            this.negativeClickListener = negativeClickListener;
            return this;
        }
        public CameraAlbumDialog create() {
            final CameraAlbumDialog dialog = new CameraAlbumDialog(context, R.style.Dialog);
            View layout = LayoutInflater.from(context).inflate(R.layout.camera_album_select_dialog, null);
            ViewHolder viewHolder = new ViewHolder(layout);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (cameraClickListener != null){
                viewHolder.tvSelectCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cameraClickListener.onClick(dialog, Dialog.BUTTON_POSITIVE);
                    }
                });
            }
            if (albumClickListener != null){
                viewHolder.tvSelectAlbum.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        albumClickListener.onClick(dialog, Dialog.BUTTON_POSITIVE);
                    }
                });
            }
            if (negativeClickListener != null){
                viewHolder.tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negativeClickListener.onClick(dialog, Dialog.BUTTON_NEGATIVE);
                    }
                });
            }
            return dialog;
        }

        static class ViewHolder {
            @InjectView(R.id.tv_select_album)
            TextView tvSelectAlbum;
            @InjectView(R.id.tv_select_camera)
            TextView tvSelectCamera;
            @InjectView(R.id.tv_cancel)
            TextView tvCancel;

            ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
        }
    }
}
