package com.huangstudio.liteui_android;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.huangstudio.liteui.LiteAlertDialog;

public class LiteDialogActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_dialog);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button://普通dialog
                new LiteAlertDialog(this, LiteAlertDialog.NORMAL_TYPE)
                        .setTitleText("hello")
                        .setContentText("my name is david!")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new LiteAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(LiteAlertDialog sDialog) {
                                sDialog
                                        .setTitleText("Deleted!")
                                        .setContentText("Your imaginary file has been deleted!")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(LiteAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
                break;
            case R.id.button2://警告dialog
                new LiteAlertDialog(this, LiteAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new LiteAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(LiteAlertDialog sDialog) {
                                sDialog
                                        .setTitleText("Deleted!")
                                        .setContentText("Your imaginary file has been deleted!")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(LiteAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
                break;
            case R.id.button3://禁止dialog
                new LiteAlertDialog(this, LiteAlertDialog.ERROR_TYPE)
                        .setTitleText("ERROR OCCUR?")
                        .setContentText("Your operation is wrong!")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new LiteAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(LiteAlertDialog sDialog) {
                                sDialog
                                        .setTitleText("Deleted!")
                                        .setContentText("Your imaginary file has been deleted!")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(LiteAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
                break;
            case R.id.button4://等待dialog
                final LiteAlertDialog pDialog = new LiteAlertDialog(this, LiteAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(true);
                pDialog.show();
                break;
            case R.id.button5://自定义dialog
                final EditText newtext = new EditText(this);
                new LiteAlertDialog(this, LiteAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("Selfview")
                        .setConfirmText("Yes,delete it!")
                        .setCustomView(newtext)
                        .setConfirmClickListener(new LiteAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(LiteAlertDialog sDialog) {
                                String context = "edittext "+newtext.getText().toString()+" has been deleted!";
                                sDialog
                                        .setTitleText("Deleted!")
                                        .setContentText(context)
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(LiteAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
                break;
        }
    }
}
