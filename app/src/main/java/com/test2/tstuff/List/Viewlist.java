package com.test2.tstuff.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.test2.tstuff.DBHelper.DBHelper;
import com.test2.tstuff.List.AdaptersList.ListAdapter;
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

               final boolean bool = delete_or_update(li.getTlist(),li.getQuantity(),itemID.toString());
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
        editText.setText(item, TextView.BufferType.EDITABLE);


        display.setText("Name: "+item);
        display2.setText("Quantity: "+Quantity);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
       // builder.setTitle("Update List Item");
        builder.setView(text);
        builder.setCancelable(true);

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast toast =  Toast.makeText(getApplicationContext(), "List updated", Toast.LENGTH_SHORT);
                toast.show();

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

        builder.show();



    }


    private boolean delete_or_update(final String item, final int Quantity, final String id){
        LayoutInflater UDPrompt = LayoutInflater.from(this);
        final View text = UDPrompt.inflate(R.layout.up_or_del,null);

        final TextView display = text.findViewById(R.id.up_or_deltextView1);
        final TextView display2 = text.findViewById(R.id.up_or_deltextView2);
        display.setText("Name: "+item);
        display2.setText("Quantity: "+Quantity);
        AlertDialog.Builder UOrD = new AlertDialog.Builder(this);

        UOrD.setView(text);

        UOrD.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sql.delete(id);
                sql.deladdData(item,Integer.valueOf(Quantity));
                Toast toast =  Toast.makeText(getApplicationContext(), "item deleted", Toast.LENGTH_SHORT);
                toast.show();
                finish();
                startActivity(getIntent());

            }
        });

        UOrD.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prompt(item,Quantity,id);


            }
        });


        UOrD.show();
        return true;



    }
    private void addListView(){
        Cursor data = sql.getData();

        tlists = new ArrayList<>();
        while(data.moveToNext()){

            tlists.add((new Tlist(data.getString(1), data.getInt(2),data.getInt(0))));

        }



    }


}
