package com.stardust.guangzhou.net;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stardust.guangzhou.model.Post;
import com.stardust.guangzhou.model.ProductItem;
import com.stardust.guangzhou.model.User;
import com.stardust.guangzhou.ui.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Stardust on 2017/4/15.
 */

public class Client {


    public static final Integer MESSAGE_USER_REFRESH = 11000;

    public void setLogined(boolean logined) {
        mLogined = logined;
    }

    public User getUser() {
        return mUser;
    }

    public interface Callback<T> {
        void accept(T t);
    }

    private static final String TAG = "Client";

    private String mBaseUrl;
    private RequestQueue mRequestQueue;
    private Gson mGson = new Gson();
    private Context mContext;
    private boolean mLogined = false;
    private User mUser = new User();
    private static final Type PRODUCT_LIST_TYPE = new TypeToken<List<ProductItem>>() {
    }.getType();

    public Client(Context context, String url) {
        mRequestQueue = Volley.newRequestQueue(context);
        mBaseUrl = url;
        mContext = context;
    }

    public void list(String url, final Callback<List<ProductItem>> c) {
        StringRequest req = new StringRequest(mBaseUrl + url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "list: " + response);
                List<ProductItem> list = mGson.fromJson(response, PRODUCT_LIST_TYPE);
                c.accept(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        req.setShouldCache(false);
        mRequestQueue.add(req);
    }

    public void sendPost(Post post, final Callback<Exception> callback) {
        final JSONObject object = new JSONObject();
        try {
            object.put("title", post.title);
            object.put("location", post.location);
            object.put("image", post.image);
            object.put("post", post.post);
            object.put("type", post.type);
            object.put("price",post.price);
            object.put("liquidateDamages",post.liquidateDamages);
            Log.e("0", "违约金: "+post.liquidateDamages );
            object.put("startAt",post.startAt);
            object.put("endAt",post.endAt);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JsonRequest req = new JsonRequest(Request.Method.POST, mBaseUrl + "diary", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "json res: " + response);
                try {
                    if (response.has("succeed") && response.getInt("succeed") == 1)
                        callback.accept(null);
                    else
                        callback.accept(new RuntimeException(response.getString("error")));
                } catch (JSONException e) {
                    callback.accept(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.accept(error);
            }
        });
        req.setShouldCache(false);
        mRequestQueue.add(req);
    }

    public void logOut() {
        mLogined = false;
        android.webkit.CookieManager.getInstance().removeAllCookies(null);
        mUser.reset();
        EventBus.getDefault().post(MESSAGE_USER_REFRESH);
    }

    public void refreshUserInfo() {
        StringRequest req = new StringRequest(mBaseUrl + "cur_user", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "cur_user: " + response);
                mUser = mGson.fromJson(response, User.TYPE);
                EventBus.getDefault().post(MESSAGE_USER_REFRESH);
                Log.d(TAG, "onResponse: "+mUser.name
                );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        req.setShouldCache(false);
        mRequestQueue.add(req);
    }


    public boolean loginIfNeeded() {
        if (mLogined) {
            return true;
        }
        mContext.startActivity(new Intent(mContext, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        return false;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }
}
