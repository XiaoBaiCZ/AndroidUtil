package cc.xiaobaicz.helper.statusbar;

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
        windowPaddingSize(
                insets.getSystemWindowInsetLeft(),
                insets.getSystemWindowInsetTop(),
                insets.getSystemWindowInsetRight(),
                insets.getSystemWindowInsetBottom()
        );
        return v.onApplyWindowInsets(insets);
    }

    /**
     * 窗口边距大小
     * @param left 左边距
     * @param top 顶边距
     * @param right 右边距
     * @param bottom 底边距
     */
    public abstract void windowPaddingSize(int left, int top, int right, int bottom);

}
