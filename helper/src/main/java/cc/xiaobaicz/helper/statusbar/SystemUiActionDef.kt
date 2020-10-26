package cc.xiaobaicz.helper.statusbar

import android.annotation.TargetApi
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * 系统UI操作接口 默认实现
 *
 * @author BoCheng
 * @date 2020/01/14
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
internal class SystemUiActionDef : SystemUiAction {
    /**
     * 适配状态栏安卓9
     */
    @TargetApi(Build.VERSION_CODES.P)
    private fun configStatusBar9_0(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val attr = window.attributes
            attr.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = attr
        }
    }

    /**
     * 获取状态栏，导航栏亮度模式
     * @since 0.3.2
     */
    @TargetApi(Build.VERSION_CODES.M)
    private fun getLightModeFlag(window: Window): Int {
        var flag = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flag = flag or (window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            flag = flag or (window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
        }
        return flag
    }

    override fun revert(window: Window) {
        window.decorView.systemUiVisibility = 0
    }

    override fun setStatusBarColor(window: Window, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = color
        }
    }

    override fun setNavigationBarColor(window: Window, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = color
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun setLightStatusBar(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    override fun setLightNavigationBar(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }

    override fun hideStatusBar(window: Window) {
        val decorView = window.decorView
        configStatusBar9_0(window)
        decorView.systemUiVisibility = getLightModeFlag(window) or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    override fun hideNavigationBar(window: Window) {
        val decorView = window.decorView
        decorView.systemUiVisibility = getLightModeFlag(window) or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    override fun fullScreen(window: Window) {
        val decorView = window.decorView
        configStatusBar9_0(window)
        decorView.systemUiVisibility = getLightModeFlag(window) or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    override fun transparentStatusBar(window: Window) {
        val decorView = window.decorView
        decorView.systemUiVisibility = getLightModeFlag(window) or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    override fun transparentNavigationBar(window: Window) {
        val decorView = window.decorView
        decorView.systemUiVisibility = getLightModeFlag(window) or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    override fun transparentScreen(window: Window) {
        val decorView = window.decorView
        decorView.systemUiVisibility = getLightModeFlag(window) or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    override fun systemUiHeight(window: Window, callback: SystemUiAttrCallback?) {
        val decorView = window.decorView
        decorView.setOnApplyWindowInsetsListener(callback)
    }
}