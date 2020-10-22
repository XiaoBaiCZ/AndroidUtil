package cc.xiaobaicz.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;

/**
 * 进程工具
 * @author BC
 * @date 2019/8/30
 */
public final class ProcessUtil {

    /**
     * 判断是否主进程
     * @param context 上下文
     * @return true是主进程
     */
    public boolean isMainProcess(Context context) throws PackageManager.NameNotFoundException {
        return isPidOfProcessName(context, getPid(), getMainProcessName(context));
    }

    /**
     * 判断该进程ID是否属于该进程名
     *
     * @param context
     * @param pid 进程ID
     * @param p_name 进程名
     * @return true属于该进程名
     */
    public boolean isPidOfProcessName(Context context, int pid, String p_name) {
        if (p_name == null)
            return false;
        boolean isMain = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : am.getRunningAppProcesses()) {
            if (process.pid == pid) {
                if (process.processName.equals(p_name)) {
                    isMain = true;
                }
                break;
            }
        }
        return isMain;
    }

    /**
     * 获取主进程名
     * @param context 上下文
     * @return 主进程名
     */
    public String getMainProcessName(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).processName;
    }

    /**
     * 获取当前进程ID
     * @return 进程ID
     */
    public int getPid() {
        return Process.myPid();
    }

}
