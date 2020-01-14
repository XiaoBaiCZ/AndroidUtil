package cc.xiaobaicz.utils.statusbar;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

/**
 * 系统UI高度接口
 *
 * @author BoCheng
 * @date 2020/01/14
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public abstract class SystemUiAttrCallback implements View.OnApplyWindowInsetsListener {
    @Override
    public final WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
        v.setOnApplyWindowInsetsListener(null);
        statusBarHeight(insets.getSystemWindowInsetTop());
        navigationBarHeight(insets.getSystemWindowInsetBottom());
        return v.onApplyWindowInsets(insets);
    }

    /**
     * @param height 状态栏高度
     */
    public void statusBarHeight(int height) {
    }

    /**
     * @param height 导航栏高度
     */
    public void navigationBarHeight(int height) {
    }
}
