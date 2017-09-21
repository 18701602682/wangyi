package com.wangyi;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by lenovo on 2017/9/18.
 */

public class BaseActivity extends SwipeBackActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        SwipeBackLayout layout=getSwipeBackLayout();
        layout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }
}
