package com.wangfengkai.customdialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by wangfengkai on 2015/7/14.
 * Email：wangfengkai@joyworks.com.cn
 */
public class CustomDialogUtils {

    /**
     *
     * @param context
     * @param title If the pop-up box does not have title,"" or null
     * @param message
     * @param negative
     * @param positive
     * @param cancelOutside
     * @param ok
     * @return
     */
    public static CustomSystemDialog showCustomDialog(final Context context,String title, String message,
                                                      final String negative,final String positive, final boolean cancelOutside,
                                                      final DialogInterface.OnClickListener ok) {
        CustomSystemDialog customSystemDialog;
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }
        CustomSystemDialog.Builder builder = new CustomSystemDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setNegativeButton(negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ok != null) {
                            ok.onClick(dialog, which);
                        }
                        dialog.dismiss();
                    }
                });
        customSystemDialog = builder.create();
        customSystemDialog.setCanceledOnTouchOutside(cancelOutside);
        customSystemDialog.show();
        return customSystemDialog;
    }

    public static CameraAlbumDialog showCameraAlbumDialog(final Context context,final boolean cancelOutside,
                                                          final DialogInterface.OnClickListener cameraListener,final DialogInterface.OnClickListener albumListener
                                                          ){
        CameraAlbumDialog cameraAlbumDialog;
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }
        CameraAlbumDialog.Builder builder = new CameraAlbumDialog.Builder(context);
        builder.setCameraClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (cameraListener != null){
                    cameraListener.onClick(dialog,which);
                }
                dialog.dismiss();
            }
        });
        builder.setAlbumClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (albumListener != null){
                    albumListener.onClick(dialog,which);
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        cameraAlbumDialog = builder.create();
        cameraAlbumDialog.setCanceledOnTouchOutside(cancelOutside);
        cameraAlbumDialog.show();
        return cameraAlbumDialog;
    }
}
