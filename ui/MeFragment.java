package com.stardust.guangzhou.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.GenericDraweeView;
import com.stardust.guangzhou.App;
import com.stardust.guangzhou.R;
import com.stardust.guangzhou.model.User;
import com.stardust.guangzhou.net.Client;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Stardust on 2018/4/15.
 */

public class MeFragment extends Fragment {

    private TextView mUserName;
    private GenericDraweeView mAvatar;


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onMessage(Integer message) {
        if (message.equals(Client.MESSAGE_USER_REFRESH)) {
            refreshUser();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getClient().loginIfNeeded();
            }
        });
        view.findViewById(R.id.logOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getClient().logOut();
            }
        });
        view.findViewById(R.id.my_products).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!App.getClient().loginIfNeeded()) {
                    return;
                }
                getContext().startActivity(new Intent(getContext(),MyProductActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        mUserName = (TextView) view.findViewById(R.id.username);
        mAvatar = (GenericDraweeView) view.findViewById(R.id.portrait);
        refreshUser();
    }
    
    // seems something need to add here
    private void refreshUser() {
        User user = App.getClient().getUser();
        Log.d("0", "refreshUser: "+user.name);
        mUserName.setText(user.name);
        if (user.avatar == null) {
            mAvatar.setImageResource(R.drawable.defaul_avatar);
        } else
            mAvatar.setImageURI(Uri.parse(App.getClient().getBaseUrl() + user.avatar));
    }
}
