package com.example.administrator.experiment9_2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper=new MyDatabaseHelper(this,"ContactPerson.db",null,1);
    private SQLiteDatabase db;

    private List<Contacts> contactsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_add=(Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddContactsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initContacts();
        ContactAdaper adaper=new ContactAdaper(MainActivity.this,R.layout.contacts_item,contactsList);
        ListView listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adaper);
    }

    private void initContacts(){
        contactsList.clear();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query("contacts",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String number=cursor.getString(cursor.getColumnIndex("number"));
                String sex=cursor.getString(cursor.getColumnIndex("sex"));

                Contacts contacts=new Contacts(name,number,sex);
                contactsList.add(contacts);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
    }
}
