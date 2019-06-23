package com.example.connecty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    /*    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.groupList);
        ArrayList<Group> arrayList = new ArrayList<>();
        arrayList.add(new Group("asadsa"));
        arrayList.add(new Group("1"));
        arrayList.add(new Group("2"));
        arrayList.add(new Group("3"));
        arrayList.add(new Group("4"));



        ListOfGroupAdapter listOfGroupAdapter = new ListOfGroupAdapter(this,arrayList);
        recyclerView.setAdapter(listOfGroupAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));*/


    }

    public void languageShow(View view) {

        PopupMenu popup = new PopupMenu(this,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.language_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.English:
                Toast.makeText(this, "Option 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Vietnamese:
                Toast.makeText(this, "Option 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }


    }

}
