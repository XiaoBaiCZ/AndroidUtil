package cc.xiaobaicz.helper.statusbar;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 系统UI操作接口 默认实现
 *
 * @author BoCheng
 * @date 2020/01/14
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
final class SystemUiActionDef implements SystemUiAction {
    /**
     * 适配状态栏安卓9
     */
    @TargetApi(Build.VERSION_CODES.P)
    private void configStatusBar9_0(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            final WindowManager.LayoutParams attr = window.getAttributes();
            attr.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(attr);
        }
    }

    /**
     * 获取状态栏，导航栏亮度模式
     * @since 0.3.2
     */
    @TargetApi(Build.VERSION_CODES.M)
    private int getLightModeFlag(Window window) {
        int flag = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flag |= window.getDecorView().getSystemUiVisibility() & View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            flag |= window.getDecorView().getSystemUiVisibility() & View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
        }
        return flag;
    }

    @Override
    public void revert(Window window) {
        window.getDecorView().setSystemUiVisibility(0);
    }

    @Override
    public void setStatusBarColor(Window window, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color);
        }
    }

    @Override
    public void setNavigationBarColor(Window window, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setNavigationBarColor(color);
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void setLightStatusBar(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.O)
    public void setLightNavigationBar(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }

    @Override
    public void hideStatusBar(Window window) {
        final View decorView = window.getDecorView();
        configStatusBar9_0(window);
        decorView.setSystemUiVisibility(getLightModeFlag(window) | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void hideNavigationBar(Window window) {
        final View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(getLightModeFlag(window) | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void fullScreen(Window window) {
        final View decorView = window.getDecorView();
        configStatusBar9_0(window);
        decorView.setSystemUiVisibility(getLightModeFlag(window) | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void transparentStatusBar(Window window) {
        final View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(getLightModeFlag(window) | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    @Override
    public void transparentNavigationBar(Window window) {
        final View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(getLightModeFlag(window) | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    @Override
    public void transparentScreen(Window window) {
        final View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(getLightModeFlag(window) | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    @Override
    public void systemUiHeight(Window window, final SystemUiAttrCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            final View decorView = window.getDecorView();
            decorView.setOnApplyWindowInsetsListener(callback);
        }
    }
}
