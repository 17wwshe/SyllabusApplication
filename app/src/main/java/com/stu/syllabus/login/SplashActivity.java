package com.stu.syllabus.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.MainActivity;
import com.stu.syllabus.login.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DataBaseHelper(this, "user", null, 1);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        queryUser(sqLiteDatabase);
    }
    private void queryUser(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.query("user", new String[] {"account", "password"}, null, null, null, null, null);
        String account = null;
        String password = null;
        while (cursor.moveToNext()) {
            account = cursor.getString(cursor.getColumnIndex("account"));
            password = cursor.getString(cursor.getColumnIndex("password"));
            Log.d(TAG, "queryUser: " + account + " " + password);
        }
        if (account == null && password == null) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        } else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }
        sqLiteDatabase.close();
    }
}
