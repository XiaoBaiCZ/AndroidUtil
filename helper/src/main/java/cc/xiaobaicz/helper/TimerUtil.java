package cc.xiaobaicz.helper;

import android.os.SystemClock;

/**
 * @author BC
 * @date 2019/06/26
 */
public final class TimerUtil {

    public interface Action {
        void action();
    }

    public static long timer(Action action) {
        final long start = SystemClock.elapsedRealtime();
        action.action();
        return SystemClock.elapsedRealtime() - start;
    }

}
