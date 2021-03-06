package com.stardust.tool;

import android.content.Intent;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stardust on 2017/3/5.
 */

public interface OnActivityResultDelegate {


    void onActivityResult(int requestCode, int resultCode, Intent data);

    interface DelegateHost {
        Intermediary getDelegateManger();
    }

    class Intermediary implements OnActivityResultDelegate {

        private SparseArray<OnActivityResultDelegate> mSpecialDelegate = new SparseArray<>();
        private List<OnActivityResultDelegate> mDelegates = new ArrayList<>();

        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            OnActivityResultDelegate delegate = mSpecialDelegate.get(requestCode);
            if (delegate != null) {
                delegate.onActivityResult(requestCode, resultCode, data);
            }
            for (OnActivityResultDelegate d : mDelegates) {
                d.onActivityResult(requestCode, resultCode, data);
            }
        }

        public void addDelegate(OnActivityResultDelegate delegate) {
            mDelegates.add(delegate);
        }

        public void addDelegate(int requestCode, OnActivityResultDelegate delegate) {
            mSpecialDelegate.put(requestCode, delegate);
        }

        public void removeDelegate(OnActivityResultDelegate delegate) {
            if (mDelegates.remove(delegate)) {
                // TODO: 2017/3/16 优化
                mSpecialDelegate.removeAt(mSpecialDelegate.indexOfValue(delegate));
            }
        }
    }

}
