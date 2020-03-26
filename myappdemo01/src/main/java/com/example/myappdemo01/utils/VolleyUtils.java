package com.example.myappdemo01.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myappdemo01.base.App;
import com.example.myappdemo01.base.IBaseCall;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Queue;

public class VolleyUtils {

    //设置标记
    private static final String TAG = "VolleyUtils";

    //创建VolleyUtils的单例模式
    private static RequestQueue QUEUE = Volley.newRequestQueue(App.getmContext());

    //判断使用哪种方式进行请求
    public static void request(int method,String url,Map<String,String>map,final IBaseCall iBaseCall){
        if (method == Request.Method.GET){
             requestGet(url,iBaseCall);
        }else {
            requestPost(url,map,iBaseCall);
        }
    }

    //设置get请求方式
    public static void requestGet(String url, final IBaseCall iBaseCall){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //调用外部接口成功的方法
                 iBaseCall.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //调用外部接口失败的方法
                 iBaseCall.onError(error.getMessage());
            }
        }){
            //这个方法解决Volley中文乱码问题
            protected Response<String> parseNetworkResponse(NetworkResponse response){
                try {
                    String string = new String(response.data, "UTF-8");
                    return Response.success(string,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        //添加请求队列
        QUEUE.add(stringRequest);
    }

    //使用post请求方式
    public static void requestPost(String url, final Map<String,String> map, final IBaseCall iBaseCall){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                  iBaseCall.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 iBaseCall.onError(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }

            //这个方法解决Volley中文乱码问题
            protected Response<String> parseNetworkResponse(NetworkResponse response){
                try {
                    String string = new String(response.data, "UTF-8");
                    return Response.success(string,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        QUEUE.add(stringRequest);
    }
}
