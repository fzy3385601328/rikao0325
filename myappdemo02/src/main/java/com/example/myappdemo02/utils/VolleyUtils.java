package com.example.myappdemo02.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myappdemo02.base.App;
import com.example.myappdemo02.base.IBaseCall;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 这个是Volley工具类的二次封装
 */
public class VolleyUtils {
    //设置一个标示
    private static final String TAG = "VolleyUtils";
    //创建请求队列，调用App的get方法
    private static RequestQueue eQueue = Volley.newRequestQueue(App.getmContext());

    //选择请求方式
    public static void request(int method,String url,Map<String,String>map,
                               IBaseCall iBaseCall){
        if (method==Request.Method.GET){
            //选择的是get请求方式
            doGet(url,iBaseCall);
        }else {
            //选择的是post请求方式
            doPost(url,map,iBaseCall);
        }
    }

    //建立get的请求
    public static void doGet(String url, final IBaseCall iBaseCall){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //调用成功的方法
                iBaseCall.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //调用失败的方法
                iBaseCall.onError(error.getMessage());

            }
        }){
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String a;
                try {
                    a=new String(response.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    a=new String(response.data);
                }
                return Response.success(a, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        //添加数据到请求队列中
        eQueue.add(stringRequest);
    }

    public static void doPost(String url, final Map<String, String> map, final IBaseCall iBaseCall){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //post请求成功
                iBaseCall.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //post请求失败
                iBaseCall.onError(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String a;
                try {
                    a=new String(response.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    a=new String(response.data);
                }
                return Response.success(a, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        eQueue.add(stringRequest);
    }


}
