package com.test2.tstuff.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.test2.tstuff.DBHelper.DBHelper;
import com.test2.tstuff.MainActivity;
import com.test2.tstuff.R;
import com.test2.tstuff.toolbar.Tmenu;

import java.util.ArrayList;
import java.util.List;

public class Viewlist extends Tmenu {
    private List<Tlist> tlists;

    Tlist li;
    String t;

    ListView listView;
    DBHelper sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_viewlist, null, false);
        drawerLayout.addView(contentView, 0);
        //setContentView(R.layout.activity_viewlist);


            listView = findViewById(R.id.listview);
        sql = new DBHelper(this);

        addListView();

        ListAdapter b = new ListAdapter(this,R.layout.my_custom_list, tlists);
        listView.setAdapter(b);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                li = tlists.get(i);
                Integer itemID =  li.getID();

                prompt(li.getTlist(),li.getQuantity(),itemID.toString());

                //sql.update(itemID.toString());



               //Intent nextActivity = new Intent(Viewlist.this,DelList.class);
                //nextActivity.putExtra("id",itemID.toString());
               // startActivity(nextActivity);

            }
        });



    }


    private void prompt(String item, int Quantity, String id){



        LayoutInflater factory = LayoutInflater.from(this);

        final String ID = id;

        final View text = factory.inflate(R.layout.edit,null);
        final Spinner spinner = text.findViewById(R.id.spin);

        final EditText editText = text.findViewById(R.id.edtext);


        final TextView display = text.findViewById(R.id.display);
        final TextView display2 = text.findViewById(R.id.display2);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Viewlist.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t = (String) parent.getSelectedItem(); // hol

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editText.setText("", TextView.BufferType.EDITABLE);


        display.setText("Name: "+item);
        display2.setText("Quantity: "+Quantity);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
       // builder.setTitle("Update List Item");
        builder.setView(text);
        builder.setCancelable(true);

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "List updated", Toast.LENGTH_SHORT);

                final int quan = Integer.valueOf(t);
                final String name = editText.getText().toString();

                sql.update(name,ID,quan);
                finish();
                startActivity(getIntent());


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //builder.show();
        AlertDialog dialog = builder.create();
        dialog.show();

        Button pos = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button neg = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);

       neg.setTextColor(getResources().getColor(R.color.menColor));
       pos.setTextColor(getResources().getColor(R.color.menColor));



    }
    private void addListView(){
        Cursor data = sql.getData();

        tlists = new ArrayList<>();
        while(data.moveToNext()){

            tlists.add((new Tlist(data.getString(1), data.getInt(2),data.getInt(0))));

        }



    }


}
