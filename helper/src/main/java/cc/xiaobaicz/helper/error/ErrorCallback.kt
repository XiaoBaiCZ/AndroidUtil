package cc.xiaobaicz.helper.error

/**
 * @author BC
 * @date 2019/9/20
 */
fun interface ErrorCallback {
    /**
     * 异常通知
     * @param t
     * @param throwable 异常信息
     */
    fun callback(t: Thread, throwable: Throwable)
}