package cc.xiaobaicz.utils.statusbar;

import android.app.Activity;
import android.view.Window;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 系统UI助手
 *
 * @author BoCheng
 * @date 2020/01/14
 */
public final class SystemUiHelper {

    private Reference<Activity> mActivityRef;

    private SystemUiAction mSystemUiAction;

    private SystemUiHelper(Activity activity, SystemUiAction action) {
        if (activity == null || action == null) {
            throw new NullPointerException();
        }
        mActivityRef = new WeakReference<>(activity);
        mSystemUiAction = action;
    }

    /**
     * 获取助手，提供默认系统UI接口实现
     */
    public static SystemUiHelper get(Activity activity) {
        return get(activity, new SystemUiActionDef());
    }

    /**
     * 获取助手，自定义系统UI接口实现
     */
    public static SystemUiHelper get(Activity activity, SystemUiAction action) {
        return new SystemUiHelper(activity, action);
    }

    /**
     * 检查活动安全
     *
     * @return true 安全
     */
    private boolean checkSafe() {
        return getActivity() != null;
    }

    private Activity getActivity() {
        return mActivityRef.get();
    }

    private Window getWindow() {
        return getActivity().getWindow();
    }

    /**
     * 修改状态栏颜色
     */
    public SystemUiHelper setStatusBarColor(int color) {
        if (!checkSafe()) {
            return this;
        }
        mSystemUiAction.setStatusBarColor(getWindow(), color);
        return this;
    }

    /**
     * 修改导航栏颜色
     */
    public SystemUiHelper setNavigationBarColor(int color) {
        if (!checkSafe()) {
            return this;
        }
        mSystemUiAction.setNavigationBarColor(getWindow(), color);
        return this;
    }

    /**
     * 隐藏状态栏
     */
    public SystemUiHelper hideStatusBar() {
        if (!checkSafe()) {
            return this;
        }
        mSystemUiAction.hideStatusBar(getWindow());
        return this;
    }

    /**
     * 隐藏导航栏
     */
    public SystemUiHelper hideNavigationBar() {
        if (!checkSafe()) {
            return this;
        }
        mSystemUiAction.hideNavigationBar(getWindow());
        return this;
    }

    /**
     * 全屏 ps：隐藏状态栏 & 导航栏
     */
    public SystemUiHelper fullScreen() {
        if (!checkSafe()) {
            return this;
        }
        mSystemUiAction.fullScreen(getWindow());
        return this;
    }

    /**
     * 透明状态栏
     */
    public SystemUiHelper transparentStatusBar() {
        if (!checkSafe()) {
            return this;
        }
        mSystemUiAction.transparentStatusBar(getWindow());
        return this;
    }

    /**
     * 透明导航栏
     */
    public SystemUiHelper transparentNavigationBar() {
        if (!checkSafe()) {
            return this;
        }
        mSystemUiAction.transparentNavigationBar(getWindow());
        return this;
    }

    /**
     * 透明全屏 ps：透明状态栏 & 导航栏
     */
    public SystemUiHelper transparentScreen() {
        if (!checkSafe()) {
            return this;
        }
        mSystemUiAction.transparentScreen(getWindow());
        return this;
    }

    /**
     * 获取状态栏 & 导航栏高度
     */
    public SystemUiHelper systemUiHeight(SystemUiAttrCallback callback) {
        if (!checkSafe()) {
            return this;
        }
        mSystemUiAction.systemUiHeight(getWindow(), callback);
        return this;
    }

}
