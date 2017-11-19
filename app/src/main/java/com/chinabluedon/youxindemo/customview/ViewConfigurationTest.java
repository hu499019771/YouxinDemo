package com.chinabluedon.youxindemo.customview;

import android.content.Context;
import android.content.res.Configuration;
import android.view.ViewConfiguration;

/**
 * @author 胡腾
 * @time 2017/8/29  23:14
 * @desc ${TODD}
 */
public class ViewConfigurationTest {

    private void test1(Context context) {
        context.getResources().getConfiguration().orientation = Configuration.ORIENTATION_LANDSCAPE;//获取应用屏幕的方向
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        int touchSlop = viewConfiguration.getScaledTouchSlop();//获取系统认为的最小活动距离
        int scaledMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();//获取滑动速度最小值
        int scaledMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();//获取滑动速度最大值
        boolean b = viewConfiguration.hasPermanentMenuKey();//判断手机是否有物理按键
        int scaledDoubleTapSlop = viewConfiguration.getDoubleTapTimeout();//获取系统认为的双击间隔时间,在这个时间内的两次点击事件算双击
        int longPressTimeout = viewConfiguration.getLongPressTimeout();//获取长按超时时间
        int keyRepeatTimeout = viewConfiguration.getKeyRepeatTimeout();//获取系统认为的按键重复按下时间
    }
}
