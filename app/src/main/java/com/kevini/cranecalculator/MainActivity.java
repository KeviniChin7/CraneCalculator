package com.kevini.cranecalculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.icu.math.BigDecimal;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper();
    private EditText E;
    private TextView B;
    private EditText F;
    private EditText C;
    private TextView A;
    private TextView D;
//    private SeekBar seekBar_B;
    private SeekBar seekBar_E;
    private Dialog dialog;
    private EditText dialogTextD;
    private EditText dialogTextA;
    private Button okBtn;
    private Button cancelBtn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        E = (EditText) findViewById(R.id.editText_E);
        B = (TextView) findViewById(R.id.editText_B);
        F = (EditText) findViewById(R.id.editText_F);
        C = (EditText) findViewById(R.id.editText_C);
        A = (TextView) findViewById(R.id.textView_A);
        D = (TextView) findViewById(R.id.textView_D);
        dialog = new Dialog(this);
        dialog.setContentView(View.inflate(this,R.layout.parameter_layout,null));
        dialog.setTitle("吊车参数");
        okBtn = (Button) dialog.findViewById(R.id.ok_btn);
        cancelBtn = (Button) dialog.findViewById(R.id.cancel_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTextD = (EditText) dialog.findViewById(R.id.dialog_text_d);
                dialogTextA = (EditText) dialog.findViewById(R.id.dialog_text_a);
                if ( sharePreferencesHelper.saveA(dialogTextA.getText().toString(),getApplicationContext())&&
                sharePreferencesHelper.saveD(dialogTextD.getText().toString(),getApplicationContext())){
                    Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
                }
                update();
                dialog.cancel();

            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        A.setText(sharePreferencesHelper.getA(this));
        D.setText(sharePreferencesHelper.getD(this));
//        seekBar_B = (SeekBar) findViewById(R.id.seekBar_B);
        seekBar_E = (SeekBar) findViewById(R.id.seekBar_E);
//        seekBar_B.setMax(1000);
//        seekBar_B.setMin(1);
        seekBar_E.setMax(200);
        seekBar_E.setMin(1);
//        seekBar_B.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                B.setText(progress/10.0+"");
//
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
        seekBar_E.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                E.setText(progress/10.0+"");
                if (checkEmpty()){
                    calculateB();
//                    seekBar_B.setProgress((int)calculateB()*10);
                }else {
                    Toast.makeText(getApplicationContext(),"输入错误",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private double calculateB(){
        double a = Double.valueOf(A.getText().toString());
        double d = Double.valueOf(D.getText().toString());
        double e = Double.valueOf(E.getText().toString());
        double c = Double.valueOf(C.getText().toString());
        double f = Double.valueOf(F.getText().toString());
        double x = (a*(e+d))/(f-a);
        double y = a*c/x;
        double result = Math.sqrt((y+f-a)*(y+f-a)+(d+e+c)*(d+e+c));
        BigDecimal   b   =   new BigDecimal(result);
        result   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        B.setText(result+"");
        return result;
    }
//    private double calculateE(){
//        double a = Double.valueOf(A.getText().toString());
//        double d = Double.valueOf(D.getText().toString());
//        double b = Double.valueOf(B.getText().toString());
//        double c = Double.valueOf(C.getText().toString());
//        double f = Double.valueOf(F.getText().toString());
//
//    }
    public void setPara(View view){
        dialog.show();
        dialogTextD = (EditText) dialog.findViewById(R.id.dialog_text_d);
        dialogTextA = (EditText) dialog.findViewById(R.id.dialog_text_a);
        dialogTextA.setText(sharePreferencesHelper.getA(getApplicationContext()));
        dialogTextD.setText(sharePreferencesHelper.getD(getApplicationContext()));
    }
    private void update(){
        A.setText(sharePreferencesHelper.getA(this));
        D.setText(sharePreferencesHelper.getD(this));
    }
    public void clear(View view){
        F.setText("");
        C.setText("");
        B.setText("0");
        E.setText("");
    }
    public void calculate(View view){
        if (checkEmpty()){
            calculateB();
        }
    }
    private boolean checkEmpty(){
        if (A.getText().toString().isEmpty()||
                D.getText().toString().isEmpty()||
                C.getText().toString().isEmpty()||
                F.getText().toString().isEmpty()||
                E.getText().toString().isEmpty()||
                A.getText().toString()=="-1"||
                D.getText().toString()=="-1"){
            return false;
        }
        return true;
    }


}
