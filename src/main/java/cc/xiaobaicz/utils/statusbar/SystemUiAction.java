package cc.xiaobaicz.utils.statusbar;

import android.view.Window;

/**
 * 系统UI操作接口
 *
 * @author BoCheng
 * @date 2020/01/14
 */
public interface SystemUiAction {
    /**
     * 修改状态栏颜色
     */
    void setStatusBarColor(Window window, int color);

    /**
     * 修改导航栏颜色
     */
    void setNavigationBarColor(Window window, int color);

    /**
     * 隐藏状态栏
     */
    void hideStatusBar(Window window);

    /**
     * 隐藏导航栏
     */
    void hideNavigationBar(Window window);

    /**
     * 全屏 ps：隐藏状态栏 & 导航栏
     */
    void fullScreen(Window window);

    /**
     * 透明状态栏
     */
    void transparentStatusBar(Window window);

    /**
     * 透明导航栏
     */
    void transparentNavigationBar(Window window);

    /**
     * 透明全屏 ps：透明状态栏 & 导航栏
     */
    void transparentScreen(Window window);

    /**
     * 获取状态栏 & 导航栏高度
     */
    void systemUiHeight(Window window, SystemUiAttrCallback callback);
}
