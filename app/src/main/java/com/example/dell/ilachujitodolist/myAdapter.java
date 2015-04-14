package com.example.dell.ilachujitodolist;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by DELL on 3/16/2015.
 */
public class myAdapter extends ArrayAdapter<Item>{

    public myAdapter(Context context, ArrayList<Item> items){
            super(context,0,items);
    }


    @Override
    public View getView(int position, View v, ViewGroup vG){
        Item item = getItem(position);
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.task_view, vG, false);
        }
        TextView todoTitle = (TextView) v.findViewById(R.id.txtTodoTitle);
        TextView todoDate = (TextView) v.findViewById(R.id.txtTodoDueDate);
        todoTitle.setText(item.mTitle);
        todoDate.setText(item.mDate);
        Calendar c = Calendar.getInstance();
        boolean before = false;
//        Date d = new Date();
//        Log.v("--------------",d.toString());
        int cYear = c.get(Calendar.YEAR);
        int cMonth = c.get(Calendar.MONTH);
        int cDay = c.get(Calendar.DAY_OF_MONTH);
//        Log.v("----------------",c.toString());
        String[]date = item.mDate.split("/");
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1])-1;
        int day = Integer.parseInt(date[0]);
        if(cYear > year){
            before = true;
        }
        if(cYear == year && cMonth > month){
            before =true;
        }
        if(cYear == year && cMonth == month && cDay > day){
            before = true;
        }
//        Date myD = new Date(year-1900, month, day);

//        Log.v("-----------------",""+day);
//        Log.v("-----------------",""+cDay);
//        Log.v("-----------------",""+year);
//        Log.v("-----------------",""+cYear);
//        Log.v("-----------------",""+cMonth);
//        Log.v("-----------------",""+month);
        if(before){
            todoTitle.setTextColor(Color.RED);
            todoDate.setTextColor(Color.RED);
        }
        else{
            todoTitle.setTextColor(Color.BLACK);
            todoDate.setTextColor(Color.BLACK);
        }
        return v;
    }



}
