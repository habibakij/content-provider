package com.notificationshare.contentpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private static final Uri INSERT_URI= Uri.parse(MyContentProvider.CONTENT_URI+"/"+SQLHelper.DATABASE_TABLE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= findViewById(R.id.showcontent);
        ContentValues contentValues= new ContentValues();
        contentValues.put(SQLHelper.COLOUM_NAME,"this is trial message");
        Uri inserturi= getContentResolver().insert(INSERT_URI,contentValues);
        textView.setText(inserturi.toString());
    }
}
