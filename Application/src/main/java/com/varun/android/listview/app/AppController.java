package com.varun.android.listview.app;

import android.app.Application;

import com.varun.android.listview.R;
import com.varun.android.listview.data.AppDatabase;
import com.varun.android.listview.util.LruBitmapCache;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by VarunSasidharan_Nair on 25/07/2017.
 */

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;

  /*  public static AppDatabase getDbInstance() {
        return dbInstance;
    }

    public static void setDbInstance(AppDatabase dbInstance) {
        AppController.dbInstance = dbInstance;
    }*/

    private static AppDatabase dbInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //dbInstance = AppDatabase.getDatabase(getApplicationContext());
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }



    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public int getImageId(String name, String type) {
        return getResources().getIdentifier(name, type, getPackageName());
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public boolean SaveLocalFile(String content, String fileName)
    {
        try {
            FileOutputStream fos = getApplicationContext().openFileOutput(fileName,
                    getApplicationContext().MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public StringBuilder getLocalFileContent(String fileName)
    {
        FileInputStream fis = null;
        try {
            fis = getApplicationContext().openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader streamReader = new BufferedReader(isr);

            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            streamReader.close();
            isr.close();
            fis.close();
            return responseStrBuilder;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}

