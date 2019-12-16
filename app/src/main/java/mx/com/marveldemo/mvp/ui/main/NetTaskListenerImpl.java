package mx.com.marveldemo.mvp.ui.main;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import mx.com.marveldemo.R;
import mx.com.marveldemo.api.NetHelper;
import mx.com.marveldemo.constants.ApiConstants;
import mx.com.marveldemo.model.GenerialError;
import mx.com.marveldemo.model.Hero;
import mx.com.marveldemo.model.apimodel.ApiGetHeroesResponse;

public class NetTaskListenerImpl implements MainContract.NetTaskListener {



  



    @Override
    public void getHeroesArray(OnResultInterface onResultInterface,Context context) {
        String mUrlString = new StringBuilder(ApiConstants.BASE_URL).append(ApiConstants.MY_HEROES_PATH).toString();


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mUrlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        ApiGetHeroesResponse apiGetHeroesResponse =null;
                        if(response!=null){
                            apiGetHeroesResponse = gson.fromJson(response,ApiGetHeroesResponse.class);
                            if(apiGetHeroesResponse!=null){
                                if(onResultInterface!=null){
                                    onResultInterface.onResultOk(apiGetHeroesResponse);
                                }
                            }else{
                                GenerialError error = new GenerialError();
                                error.setDescription(R.string.error_no_info);
                                if(onResultInterface!=null){
                                    onResultInterface.onError(error);
                                }
                            }
                        }else{
                            GenerialError error = new GenerialError();
                            error.setDescription(R.string.error_no_info);
                            if(onResultInterface!=null){
                                onResultInterface.onError(error);
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        GenerialError generialError = new GenerialError();
                        generialError.setDescription(R.string.error_server);
                        if(onResultInterface!=null){
                            onResultInterface.onError(generialError);
                        }

                    }
                }
        );

        NetHelper.getInstance(context).addToRequestQueue(stringRequest);
    }


}
