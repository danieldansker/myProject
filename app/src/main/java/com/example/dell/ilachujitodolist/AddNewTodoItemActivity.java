package com.example.dell.ilachujitodolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by DELL on 4/12/2015.
 */
public class AddNewTodoItemActivity extends Activity {

    protected void onCreate(Bundle unused) {
        super.onCreate(unused);
        setContentView(R.layout.new_todo_activity);
//        Resources res = getResources();
        final Context context = this;
        Button cancelBtn = (Button)this.findViewById(R.id.btnCancel);
        Button okBtn = (Button)this.findViewById(R.id.btnOk);
//        final Intent result = new Intent();
        final EditText edit = (EditText)this.findViewById(R.id.edtNewItem);
        final DatePicker date = (DatePicker)this.findViewById(R.id.datePicker);

//        String itemStr = edit.getText().toString();
//        Log.v("----------",itemStr);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(0);
                finish();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {

//            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View v = inflater.inflate(R.layout.new_todo_activity, null);
//            DatePicker date = (DatePicker)v.findViewById(R.id.datePicker);
//            String stringDate = date.toString();
//            TextView tV = (TextView)v.findViewById(R.id.edtNewItem);
//            String itemStr = tV.toString();
            @Override
            public void onClick(View arg0) {
//                LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View v = inflater.inflate(R.layout.new_todo_activity, null);
                Intent result = new Intent();

//                DatePicker date = (DatePicker)v.findViewById(R.id.datePicker);
                int month = date.getMonth()+1;
                String stringDate = date.getDayOfMonth()+"/"+month+"/"+date.getYear();
//                EditText edit = (EditText)this.findViewById(R.id.edtNewItem);
                String itemStr = edit.getText().toString();
                Log.i(itemStr , "-------------");
                Log.i(stringDate , "-------------");
                result.putExtra("item",itemStr);
                result.putExtra("date",stringDate);
                setResult(1,result);
                finish();
            }
        });


    }
}
