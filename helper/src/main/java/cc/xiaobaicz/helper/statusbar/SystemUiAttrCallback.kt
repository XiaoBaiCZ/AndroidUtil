package cc.xiaobaicz.helper.statusbar

import android.annotation.TargetApi
import android.os.Build
import android.view.View
import android.view.WindowInsets

/**
 * 系统UI高度接口
 *
 * @author BoCheng
 * @date 2020/01/14
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
abstract class SystemUiAttrCallback : View.OnApplyWindowInsetsListener {
    override fun onApplyWindowInsets(v: View, insets: WindowInsets): WindowInsets {
        v.setOnApplyWindowInsetsListener(null)
        windowPaddingSize(
            insets.systemWindowInsetLeft,
            insets.systemWindowInsetTop,
            insets.systemWindowInsetRight,
            insets.systemWindowInsetBottom
        )
        return v.onApplyWindowInsets(insets)
    }

    /**
     * 窗口边距大小
     * @param left 左边距
     * @param top 顶边距
     * @param right 右边距
     * @param bottom 底边距
     */
    abstract fun windowPaddingSize(left: Int, top: Int, right: Int, bottom: Int)
}