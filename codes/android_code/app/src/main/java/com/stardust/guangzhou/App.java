package com.stardust.guangzhou;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.stardust.guangzhou.net.Client;
import com.stardust.guangzhou.net.WebkitCookieManagerProxy;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;

/**
 * Created by Stardust on 2017/4/15.
 */

public class App extends Application {

    private static Client client;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        android.webkit.CookieSyncManager.createInstance(this);
        android.webkit.CookieManager.getInstance().setAcceptCookie(true);
        WebkitCookieManagerProxy coreCookieManager = new WebkitCookieManagerProxy(null, java.net.CookiePolicy.ACCEPT_ALL);
        java.net.CookieHandler.setDefault(coreCookieManager);

        client = new Client(this, "http://192.168.253.1:5000/");
    }

    public static Client getClient() {
        return client;
    }
}
