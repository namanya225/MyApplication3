package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class unitlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unitlist);
        setTitle("LIST OF MEMBERS");

        List<String> dataList = new ArrayList<String>();
        dataList.add("Ivan");
        dataList.add("Steven");
        dataList.add("Mac");
        dataList.add("Mose");
        dataList.add("Yawe");

        ListView listView = (ListView) findViewById(R.id.listViewExample);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, dataList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Object clickItemObj = adapterView.getAdapter().getItem(index);
                Toast.makeText(unitlist.this, "You clicked " + clickItemObj.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
