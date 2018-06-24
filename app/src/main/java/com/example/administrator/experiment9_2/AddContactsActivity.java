package com.example.administrator.experiment9_2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddContactsActivity extends AppCompatActivity {

    private  EditText edit_name;
    private  EditText edit_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        edit_name=(EditText) findViewById(R.id.edit_name);
        edit_number=(EditText) findViewById(R.id.edit_number);


        Button btn_commit=(Button) findViewById(R.id.btn_commit);
        btn_commit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{

                    Spinner sp=(Spinner) findViewById(R.id.spinner_sex);

                    MyDatabaseHelper dbHelper=new MyDatabaseHelper(AddContactsActivity.this,"ContactPerson.db",null,1);
                    SQLiteDatabase db=dbHelper.getWritableDatabase();
                    ContentValues values=new ContentValues();
                    values.put("name",edit_name.getText().toString());
                    values.put("number",edit_number.getText().toString());
                    values.put("sex",sp.getSelectedItem().toString());

                    db.insert("contacts",null,values);
                    Toast.makeText(AddContactsActivity.this,"添加信息成功",Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(AddContactsActivity.this,"添加信息失败",Toast.LENGTH_SHORT).show();
                }
                finally{
                    //添加完数据之后关闭当前窗口
                    finish();
                }

            }
        });

    }
}
