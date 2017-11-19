package com.chinabluedon.youxindemo.process;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chinabluedon.youxindemo.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author 胡腾
 * @time 2017/11/9  23:34
 * @desc ${TODD}
 */
public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        com.chinabluedon.youxindemo.process.UserManager.userId = 2;
        Log.i(TAG, "MainActivity--userId--" + com.chinabluedon.youxindemo.process.UserManager.userId);
        serializableTest();
        Class<MainActivity> aClass = MainActivity.class;
        ClassLoader classLoader = aClass.getClassLoader();
        Log.i(TAG,"类加载器的名字--"+classLoader.toString());

    }

    private void serializableTest() {
        com.chinabluedon.youxindemo.process.User teng = new com.chinabluedon.youxindemo.process.User("teng", 18, true);
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cache.txt"));
            outputStream.writeObject(teng);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("cache.txt"));
            try {
                com.chinabluedon.youxindemo.process.User user = (com.chinabluedon.youxindemo.process.User) inputStream.readObject();
                Log.i(TAG,user.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void click(View view) {
        startActivity(new Intent(this, com.chinabluedon.youxindemo.process.TwoActivity.class));
    }
}
