package cc.xiaobaicz.helper.statusbar

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.view.Window
import java.lang.ref.WeakReference

/**
 * 系统UI助手
 *
 * @author BoCheng
 * @date 2020/01/14
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class SystemUiHelper private constructor(
    activity: Activity,
    private val mSystemUiAction: SystemUiAction
) {

    private val mActivityRef = WeakReference(activity)

    /**
     * 检查活动安全
     *
     * @return true 安全
     */
    private fun checkSafe(): Boolean {
        return activity != null
    }

    private val activity: Activity?
        get() = mActivityRef.get()
    private val window: Window
        get() = activity?.window ?: throw NullPointerException("activity is null")

    /**
     * 还原初始状态
     */
    fun revert(): SystemUiHelper {
        if (!checkSafe()) {
            return this
        }
        mSystemUiAction.revert(window)
        return this
    }

    /**
     * 修改状态栏颜色
     */
    fun setStatusBarColor(color: Int): SystemUiHelper {
        if (!checkSafe()) {
            return this
        }
        mSystemUiAction.setStatusBarColor(window, color)
        return this
    }

    /**
     * 修改导航栏颜色
     */
    fun setNavigationBarColor(color: Int): SystemUiHelper {
        if (!checkSafe()) {
            return this
        }
        mSystemUiAction.setNavigationBarColor(window, color)
        return this
    }

    /**
     * 设置亮色状态栏（ps：黑色前景）
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun setLightStatusBar(): SystemUiHelper {
        if (!checkSafe()) {
            return this
        }
        mSystemUiAction.setLightStatusBar(window)
        return this
    }

    /**
     * 设置亮色导航栏（ps：黑色前景）
     */
    @TargetApi(Build.VERSION_CODES.O)
    fun setLightNavigationBar(): SystemUiHelper {
        if (!checkSafe()) {
            return this
        }
        mSystemUiAction.setLightNavigationBar(window)
        return this
    }

    /**
     * 隐藏状态栏
     */
    fun hideStatusBar(): SystemUiHelper {
        if (!checkSafe()) {
            return this
        }
        mSystemUiAction.hideStatusBar(window)
        return this
    }

    /**
     * 隐藏导航栏
     */
    fun hideNavigationBar(): SystemUiHelper {
        if (!checkSafe()) {
            return this
        }
        mSystemUiAction.hideNavigationBar(window)
        return this
    }

    /**
     * 全屏 ps：隐藏状态栏 & 导航栏
     */
    fun fullScreen(): SystemUiHelper {
        if (!checkSafe()) {
            return this
        }
        mSystemUiAction.fullScreen(window)
        return this
    }

    /**
     * 透明状态栏
     */
    fun transparentStatusBar(): SystemUiHelper {
        if (!checkSafe()) {
            return this
        }
        mSystemUiAction.transparentStatusBar(window)
        return this
    }

    /**
     * 透明导航栏
     */
    fun transparentNavigationBar(): SystemUiHelper {
        if (!checkSafe()) {
            return this
        }
        mSystemUiAction.transparentNavigationBar(window)
        return this
    }

    /**
     * 透明全屏 ps：透明状态栏 & 导航栏
     */
    fun transparentScreen(): SystemUiHelper {
        if (!checkSafe()) {
            return this
        }
        mSystemUiAction.transparentScreen(window)
        return this
    }

    /**
     * 获取状态栏 & 导航栏高度
     */
    fun systemUiHeight(callback: SystemUiAttrCallback?): SystemUiHelper {
        if (!checkSafe()) {
            return this
        }
        mSystemUiAction.systemUiHeight(window, callback)
        return this
    }

    companion object {
        /**
         * 获取助手，自定义系统UI接口实现
         */
        /**
         * 获取助手，提供默认系统UI接口实现
         */
        @JvmOverloads
        fun get(activity: Activity, action: SystemUiAction = SystemUiActionDef()): SystemUiHelper {
            return SystemUiHelper(activity, action)
        }
    }

}