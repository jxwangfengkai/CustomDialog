package com.wangfengkai.customdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @InjectView(R.id.tv_one)
    TextView tvOne;
    @InjectView(R.id.tv_two)
    TextView tvTwo;
    @InjectView(R.id.tv_three)
    TextView tvThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initEvent();
    }

    private void initEvent() {
        tvOne.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
        tvThree.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_one:
                CustomDialogUtils.showCameraAlbumDialog(this,false,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //select camera
                    }
                },new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //select album
                    }
                });
                break;
            case R.id.tv_two:
                CustomDialogUtils.showCustomDialog(this,"","确定删除","取消","确定",true,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                break;
            case R.id.tv_three:
                CustomDialogUtils.showCustomDialog(this,"","1.8版本更新啦","","更新",false,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                break;
        }
    }
}
