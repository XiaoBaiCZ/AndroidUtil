package cc.xiaobaicz.helper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 主线程Handler
 * @date 2019-4-11
 * @author BC
 */
public final class MainHandler extends Handler {

    /**
     * 消息接收接口
     */
    public interface IHandlerMessage {
        /**
         * 消息接收回调
         * @param obj 接收对象实例
         * @param msg 消息
         */
        void handleMessage(IHandlerMessage obj, Message msg);
    }

    /**
     * 当前消息接收者引用（弱引用）
     */
    private final Reference<IHandlerMessage> mReference;

    public MainHandler() {
        this(null);
    }

    public MainHandler(IHandlerMessage obj) {
        super(Looper.getMainLooper());
        mReference = new WeakReference<>(obj);
    }

    @Override
    public void handleMessage(Message msg) {
        IHandlerMessage obj = mReference.get();
        //消息接收者销毁后不执行操作
        if (obj == null) {
            return;
        }
        obj.handleMessage(obj, msg);
    }

    /**
     * 回收销毁
     */
    public void recycle() {
        removeCallbacksAndMessages(null);
        mReference.clear();
    }

}
