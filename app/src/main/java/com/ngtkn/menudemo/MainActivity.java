package com.ngtkn.menudemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList = new ArrayList<>();
    Button btnPop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnPop = findViewById(R.id.btn_popup);

        btnPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnPop);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menui, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Toast.makeText(getBaseContext(), "You select: " + item.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        listView = findViewById(R.id.list_view);
        arrayList.add("Clubs");
        arrayList.add("Hearts");
        arrayList.add("Spades");
        arrayList.add("Diamonds");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(adapter);

        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.settings:
                Toast.makeText(getBaseContext(), "Settings", Toast.LENGTH_LONG).show();
                break;
            case R.id.search:
                Toast.makeText(getBaseContext(), "Search", Toast.LENGTH_LONG).show();
                break;
            case R.id.exit:
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Select the action");
        menu.add(0, v.getId(), 0, "Chat");
        menu.add(0, v.getId(), 0, "Voice call");
        menu.add(0, v.getId(), 0, "Video Call");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        String action = item.getTitle().toString();

        if(action == "Chat"){
            Toast.makeText(getBaseContext(), "Chat", Toast.LENGTH_LONG).show();
        } else if(action == "Voice call"){
            Toast.makeText(getBaseContext(), "voice call", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getBaseContext(), "video call", Toast.LENGTH_LONG).show();
        }


        return true;
    }
}
