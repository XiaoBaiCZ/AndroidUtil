package cc.xiaobaicz.utils.error;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 崩溃日志
 * @author BC
 * @date 2019/8/28
 */
public final class ErrorUtil implements Thread.UncaughtExceptionHandler {

    private static volatile Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    private ErrorDB mErrorDB;

    private ErrorUtil(final Context context) {
        if (context == null)
            throw new NullPointerException();
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        mErrorDB = new ErrorDB(context.getApplicationContext());
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        onError(t, e);
        uncaughtExceptionHandler.uncaughtException(t, e);
    }

    /**
     * 初始化
     * @param context context
     */
    public static void init(Context context) {
        if (context == null)
            throw new NullPointerException();
        if (!(uncaughtExceptionHandler instanceof ErrorUtil))
            Thread.setDefaultUncaughtExceptionHandler(new ErrorUtil(context));
    }

    /**
     * 写入错误日志
     * @param t 异常线程
     * @param e 异常信息
     */
    private void onError(Thread t, Throwable e) {
        final String tName = t.getName();
        final long time = System.currentTimeMillis();
        final String model = Build.MODEL;
        final String sys = String.format("Android %s", Build.VERSION.RELEASE);

        final String msg;
        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        try (PrintStream ps = new PrintStream(os)) {
            e.printStackTrace(ps);
            msg = os.toString();
        }

        try (SQLiteDatabase db = mErrorDB.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("thread", tName);
            values.put("time", time);
            values.put("sys", sys);
            values.put("model", model);
            values.put("msg", msg);
            db.beginTransaction();
            try {
                db.insert("error_log", null, values);
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }
    }

    /**
     * 获取所有日志
     * @return 日志集
     */
    public static List<Error> getLogs(Context context) {
        if (context == null)
            throw new NullPointerException();
        List<Error> errors = new ArrayList<>();
        try (
                SQLiteDatabase db = new ErrorDB(context.getApplicationContext()).getReadableDatabase();
                Cursor cursor = db.query("error_log", new String[]{"thread", "time", "sys", "model", "msg"}, null, null, null, null, "time desc")
        ) {
            Error error;
            while (cursor.moveToNext()) {
                error = new Error();
                error.thread = cursor.getString(0);
                error.time = cursor.getLong(1);
                error.sys = cursor.getString(2);
                error.model = cursor.getString(3);
                error.msg = cursor.getString(4);
                errors.add(error);
            }
        }
        return errors;
    }

    /**
     * 获取最新日志
     * @return 最新日志
     */
    public static Error getNewLog(Context context) {
        if (context == null)
            throw new NullPointerException();
        try (
                SQLiteDatabase db = new ErrorDB(context.getApplicationContext()).getReadableDatabase();
                Cursor cursor = db.query("error_log", new String[]{"thread", "time", "sys", "model", "msg"}, null, null, null, null, "time desc", "1")
        ) {
            Error error;
            while (cursor.moveToNext()) {
                error = new Error();
                error.thread = cursor.getString(0);
                error.time = cursor.getLong(1);
                error.sys = cursor.getString(2);
                error.model = cursor.getString(3);
                error.msg = cursor.getString(4);
                return error;
            }
        }
        return null;
    }

    /**
     * 清空所有日志
     * @return 是否成功，false清空失败，true清空成功
     */
    public static void clear(Context context) {
        if (context == null)
            throw new NullPointerException();
        try (SQLiteDatabase db = new ErrorDB(context.getApplicationContext()).getReadableDatabase()) {
            db.delete("error_log", null, null);
        }
    }

}
