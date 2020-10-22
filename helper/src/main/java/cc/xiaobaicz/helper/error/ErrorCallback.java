package cc.xiaobaicz.helper.error;

/**
 * @author BC
 * @date 2019/9/20
 */
public interface ErrorCallback {

    /**
     * 异常通知
     * @param t
     * @param throwable 异常信息
     */
    void callback(Thread t, Throwable throwable);

}
