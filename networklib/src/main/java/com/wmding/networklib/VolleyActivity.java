package com.wmding.networklib;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wmding.commonlib.utils.MyLog;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        initData();
    }

    private void initData() {

    }

    public void btnGet(View view) {
        getString();
    }

    public void btnPost(View view) {
        jsonRequest();
    }

    /**
     * string get
     */
    public void getString() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = "https://www.baidu.com";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyLog.info(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                MyLog.error(error.getMessage());
            }
        });

        requestQueue.add(stringRequest);
    }

    /**
     * json post
     */
    public void jsonRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("username", "username");
            jsonRequest.put("password", "password");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "https://wanandroid.com/user/login";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                // json字符串
                String jsonStr = response.toString();
                // 转换成对应的实体类，todo
                MyLog.info(jsonStr);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyLog.error(error.getMessage());

            }
        });

        requestQueue.add(jsonObjectRequest);
    }


    public void imageRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        ImageRequest imageRequest = new ImageRequest("", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

            }
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(imageRequest);
    }


}