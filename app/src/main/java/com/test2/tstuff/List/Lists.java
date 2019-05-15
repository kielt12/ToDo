package com.test2.tstuff.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.test2.tstuff.DBHelper.DBHelper;
import com.test2.tstuff.R;
import com.test2.tstuff.toolbar.Tmenu;

public class Lists extends Tmenu {

    private static final String TAG = "Lists"; // tag for exception
    DBHelper sqlLiteDataBaseHelper;
    Button add;
    Button done;
    EditText editText;
    TextView textView;
    Spinner spinner;

    String t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_lists, null, false);
        drawerLayout.addView(contentView, 0);
        //setContentView(R.layout.activity_lists);
            sqlLiteDataBaseHelper = new DBHelper(this);
            add = findViewById(R.id.button);
            done = findViewById(R.id.add);
            editText = findViewById(R.id.editText);
            spinner = findViewById(R.id._dynamic);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Lists.this,
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
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newEntry = editText.getText().toString();

                   AddData(newEntry,Integer.parseInt(t));



                    editText.setText("");

                }
            });

            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   Intent i = new Intent(Lists.this, Viewlist.class);
                    startActivity(i);
                }
            });
    }





    public void AddData(String newEntry, int item) {
        boolean insertData = sqlLiteDataBaseHelper.addData(newEntry, item);

        if (insertData) {
           Toast toast = Toast.makeText(Lists.this, "Added",Toast.LENGTH_LONG);
            toast.show();
        } else {
            Toast.makeText(this, "Fail",Toast.LENGTH_SHORT);
        }
    }
}





