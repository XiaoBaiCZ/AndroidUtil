package cc.xiaobaicz.helper.process

import android.app.ActivityManager
import android.content.Context
import android.os.Process

/**
 * 获取主进程名
 * @return 主进程名
 */
fun Context.getMainProcessName(): String = packageManager.getApplicationInfo(packageName, 0).processName

/**
 * 判断该进程ID是否属于该进程名
 * @param pid 进程ID
 * @param p_name 进程名
 * @return true属于该进程名
 */
fun Context.isPidOfProcessName(pid: Int, p_name: String): Boolean {
    for (process in (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).runningAppProcesses) {
        if (process.pid == pid) {                   //对比目标pid
            if (process.processName == p_name) {    //对比目标进程名
                return true                         //一致则该 pid 属于该 进程名
            }
        }
    }
    return false
}

/**
 * 判断是否主进程
 * @return true是主进程
 */
fun Context.isMainProcess() = isPidOfProcessName(Process.myPid(), getMainProcessName())