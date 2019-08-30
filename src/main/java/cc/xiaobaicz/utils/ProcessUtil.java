package cc.xiaobaicz.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

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
    public boolean isMainProcess(Context context) {
        return isMainProcess(context.getPackageName());
    }

    /**
     * 判断是否主进程
     * @param p_name 主进程名
     * @return true是主进程
     */
    public boolean isMainProcess(String p_name) {
        boolean isMain = false;
        try {
            final Process exec = Runtime.getRuntime().exec(String.format("pidof %s", p_name));
            try (InputStream input = exec.getInputStream()) {
                final byte[] src = new byte[1024];
                final int len = input.read(src);
                final String PID = new String(src, 0, len).trim();
                if (PID.equals(String.valueOf(android.os.Process.myPid()))) {
                    isMain = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isMain;
    }

}
