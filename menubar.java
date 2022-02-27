package com.example.task1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class menubar extends AppCompatActivity {
    private Context context=this;
    Databasehelper db;
    EditText e1,e2,e3;
    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubar);
        e1=findViewById(R.id.ed1);
        e2=findViewById(R.id.ed2);
        e3=findViewById(R.id.ed3);
        b1=findViewById(R.id.ins);
        b2=findViewById(R.id.view);
        Button B1=findViewById(R.id.button);
        Button b3=findViewById(R.id.update);
        Button b4=findViewById(R.id.del);
        db=new Databasehelper(this);
        updateData();
        deleteData();
        AddData();
        viewData();




        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(i2);
                AlertDialog.Builder alert=new AlertDialog.Builder(context);
                alert.setTitle("Alert");
                alert.setMessage("Are you sure to go back").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        menubar.this.finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


                AlertDialog ob=alert.create();
                ob.show();

            }
        });

    }

    private void deleteData() {

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void updateData() {
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void viewData() {

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res=db.getAllData();
                if(res.getCount()==0)
                {
                    showmessage("error","nothing found");
                    return;
                }
                StringBuffer stringBuffer=new StringBuffer();
                while (res.moveToNext())
                {
                    stringBuffer.append("ID :"+res.getString(0)+"\n");
                    stringBuffer.append("NAME :"+res.getString(1)+"\n");
                    stringBuffer.append("AGE :"+res.getString(2)+"\n");
                    stringBuffer.append("GENDER :"+res.getString(3)+"\n");
                }
                showmessage("data",stringBuffer.toString());

            }


        });
    }

    public void showmessage(String title, String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    private void AddData() {

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isinserted = db.insertData(e1.getText().toString(),
                        e2.getText().toString(),
                        e3.getText().toString());
                if(isinserted)
                {
                    Toast.makeText(menubar.this, "Data inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(menubar.this, "data not inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menusample,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){

        Toast.makeText(this, "Selected Item:"+item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()){
                case R.id.search:
                    Intent i=new Intent(getApplicationContext(),mediaplayer.class);
                    startActivity(i);
                      return true;

                case R.id.up:
                    Intent i2=new Intent(getApplicationContext(),video.class);
                    startActivity(i2);
                      return true;

                      default:
                          return super.onOptionsItemSelected(item);
        }
    }

}
