package com.example.dell.ilachujitodolist;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class TodoListManagerActivity extends ActionBarActivity {
    myAdapter itemsAdapter;
    ListView listView;
    ArrayList<Item> items = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_todo_list_manager);
//        Resources res = getResources();
        final Context context = this;
        itemsAdapter = new myAdapter(this, items);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final int toRemove = position;
                final Dialog inviteBuilder = new Dialog(context);
                LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View bodyView = inflater.inflate(R.layout.mydialog, null);
                ((TextView) bodyView.findViewById(R.id.textViewTitle)).setText(items.get(position).mTitle);
                if(!items.get(position).mTitle.toString().startsWith("Call")) {
                    bodyView.findViewById(R.id.menuItemCall).setVisibility(View.INVISIBLE);
//                    ((TextView) bodyView.findViewById(R.id.menuItemCall)).setText(items.get(position).mTitle);

                }
                ((TextView) bodyView.findViewById(R.id.textViewTitle)).setTextColor(Color.BLACK);
                ((TextView) bodyView.findViewById(R.id.menuItemCall)).setText(items.get(position).mTitle);
                Button dltButton = (Button) bodyView.findViewById(R.id.menuItemDelete);
                Button callButton = (Button) bodyView.findViewById(R.id.menuItemCall);
                inviteBuilder.setContentView(bodyView);

                dltButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        items.remove(toRemove);
                        itemsAdapter.notifyDataSetChanged();
                        inviteBuilder.dismiss();
                    }
                });
                if(items.get(position).mTitle.toString().startsWith("Call")) {
                    callButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            Intent dial = new Intent(Intent.ACTION_DIAL,
                                    Uri.parse("tel:" + items.get(position).mTitle.replace("Call", "")));
                            startActivity(dial);
                            inviteBuilder.dismiss();
                        }
                    });
                }
                inviteBuilder.show();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_list_manager, menu);

        return true;
    }

    protected void onActivityResult(int req){

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Resources res = getResources();
        final Context context = this;

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.add){
            Intent intent = new Intent(this, AddNewTodoItemActivity.class);
            startActivityForResult(intent, 1);



//            EditText editText = (EditText) findViewById(R.id.add_item);
//            Item stringItem = editText.getText().toString();
//            items.add(stringItem);
//            itemsAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data){
        if (reqCode == 1){
            if (resCode == 0){
                return;
            }
            else if(resCode == 1){
                Item itm = new Item(data.getStringExtra("item"), data.getStringExtra("date"));
                items.add(itm);
                itemsAdapter.notifyDataSetChanged();
                return;
            }
        }
    }
}