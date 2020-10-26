package cc.xiaobaicz.helper.statusbar

import android.annotation.TargetApi
import android.os.Build
import android.view.Window

/**
 * 系统UI操作接口
 *
 * @author BoCheng
 * @date 2020/01/14
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
interface SystemUiAction {
    /**
     * 还原初始状态
     */
    fun revert(window: Window)

    /**
     * 修改状态栏颜色
     */
    fun setStatusBarColor(window: Window, color: Int)

    /**
     * 修改导航栏颜色
     */
    fun setNavigationBarColor(window: Window, color: Int)

    /**
     * 设置亮色状态栏（ps：黑色前景）
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun setLightStatusBar(window: Window)

    /**
     * 设置亮色导航栏（ps：黑色前景）
     */
    @TargetApi(Build.VERSION_CODES.O)
    fun setLightNavigationBar(window: Window)

    /**
     * 隐藏状态栏
     */
    fun hideStatusBar(window: Window)

    /**
     * 隐藏导航栏
     */
    fun hideNavigationBar(window: Window)

    /**
     * 全屏 ps：隐藏状态栏 & 导航栏
     */
    fun fullScreen(window: Window)

    /**
     * 透明状态栏
     */
    fun transparentStatusBar(window: Window)

    /**
     * 透明导航栏
     */
    fun transparentNavigationBar(window: Window)

    /**
     * 透明全屏 ps：透明状态栏 & 导航栏
     */
    fun transparentScreen(window: Window)

    /**
     * 获取状态栏 & 导航栏高度
     */
    fun systemUiHeight(window: Window, callback: SystemUiAttrCallback?)
}