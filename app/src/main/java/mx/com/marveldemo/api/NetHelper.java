package mx.com.marveldemo.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class NetHelper {


    private static NetHelper mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private NetHelper(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized NetHelper getInstance(Context context){
        if(mInstance == null){
            mInstance = new NetHelper(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }

    public<T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}