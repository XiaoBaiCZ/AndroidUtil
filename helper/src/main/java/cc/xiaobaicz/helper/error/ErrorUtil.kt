package cc.xiaobaicz.helper.error

import android.content.ContentValues
import android.content.Context
import android.os.Build
import java.io.*
import java.nio.charset.Charset
import java.util.*

/**
 * 崩溃日志
 * @author BC
 * @date 2019/8/28
 */
class ErrorUtil private constructor(context: Context?) : Thread.UncaughtExceptionHandler {
    private val CALLBACKS: MutableSet<ErrorCallback> = HashSet()
    private val mErrorDB: ErrorDB

    init {
        if (context == null) throw NullPointerException()
        mErrorDB = ErrorDB(context.applicationContext)
    }

    /**
     * 默认错误接收器
     */
    private val ERROR_CALLBACK = ErrorCallback { t, e ->
        val tName = t.name
        val time = System.currentTimeMillis()
        val model = Build.MODEL
        val sys = String.format("Android %s", Build.VERSION.RELEASE)
        val type: String
        type = if (e.cause != null) {
            e.cause!!.javaClass.name
        } else {
            e.javaClass.name
        }
        var msg: String
        val os = ByteArrayOutputStream(1024)
        PrintStream(os).use { ps ->
            e.printStackTrace(ps)
            msg = os.toString()
        }
        mErrorDB.writableDatabase.use { db ->
            val values = ContentValues()
            values.put("thread", tName)
            values.put("time", time)
            values.put("sys", sys)
            values.put("model", model)
            values.put("type", type)
            values.put("msg", msg)
            db.beginTransaction()
            try {
                db.insert("error_log", null, values)
                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }
        }
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        for (callback in CALLBACKS) {
            callback.callback(t, e)
        }
        uncaughtExceptionHandler.uncaughtException(t, e)
    }

    companion object {
        @Volatile
        private var uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        private var sErrorUtil: ErrorUtil? = null

        /**
         * 初始化
         * @param context context
         */
        fun init(context: Context?) {
            if (context == null) throw NullPointerException()
            if (uncaughtExceptionHandler !is ErrorUtil) {
                if (sErrorUtil != null) {
                    sErrorUtil!!.mErrorDB.close()
                    sErrorUtil!!.CALLBACKS.clear()
                }
                Thread.setDefaultUncaughtExceptionHandler(ErrorUtil(context).also { sErrorUtil = it })
                register(sErrorUtil!!.ERROR_CALLBACK)
            }
        }

        /**
         * 注册监听
         * @param callback 监听
         */
        fun register(callback: ErrorCallback) {
            if (sErrorUtil == null) throw NullPointerException()
            sErrorUtil!!.CALLBACKS.add(callback)
        }

        /**
         * 获取指定类型日志
         * @return 日志集
         */
        fun getLogs(context: Context?, type: String, index: Int, count: Int): List<Error> {
            if (context == null) throw NullPointerException()
            check(!(index < 0 || count < 0))
            val errors: MutableList<Error> = ArrayList()
            ErrorDB(context.applicationContext).readableDatabase.use { db ->
                db.query("error_log", arrayOf("thread", "time", "sys", "model", "type", "msg"), "type = ?", arrayOf(type), null, null, "time desc", String.format("%s, %s", index, count)).use { cursor ->
                    var error: Error
                    while (cursor.moveToNext()) {
                        error = Error()
                        error.thread = cursor.getString(0)
                        error.time = cursor.getLong(1)
                        error.sys = cursor.getString(2)
                        error.model = cursor.getString(3)
                        error.type = cursor.getString(4)
                        error.msg = cursor.getString(5)
                        errors.add(error)
                    }
                }
            }
            return errors
        }

        /**
         * 获取日志
         * @return 日志集
         */
        fun getLogs(context: Context?, index: Int, count: Int): List<Error> {
            if (context == null) throw NullPointerException()
            check(!(index < 0 || count < 0))
            val errors: MutableList<Error> = ArrayList()
            ErrorDB(context.applicationContext).readableDatabase.use { db ->
                db.query("error_log", arrayOf("thread", "time", "sys", "model", "type", "msg"), null, null, null, null, "time desc", String.format("%s, %s", index, count)).use { cursor ->
                    var error: Error
                    while (cursor.moveToNext()) {
                        error = Error()
                        error.thread = cursor.getString(0)
                        error.time = cursor.getLong(1)
                        error.sys = cursor.getString(2)
                        error.model = cursor.getString(3)
                        error.type = cursor.getString(4)
                        error.msg = cursor.getString(5)
                        errors.add(error)
                    }
                }
            }
            return errors
        }

        /**
         * 获取所有日志
         * @return 日志集
         */
        fun getLogs(context: Context?): List<Error> {
            return getLogs(context, 0, Int.MAX_VALUE)
        }

        /**
         * 获取最新日志
         * @return 最新日志
         */
        fun getNewLog(context: Context?): Error? {
            if (context == null) throw NullPointerException()
            ErrorDB(context.applicationContext).readableDatabase.use { db ->
                db.query("error_log", arrayOf("thread", "time", "sys", "model", "type", "msg"), null, null, null, null, "time desc", "1").use { cursor ->
                    val error: Error
                    while (cursor.moveToNext()) {
                        error = Error()
                        error.thread = cursor.getString(0)
                        error.time = cursor.getLong(1)
                        error.sys = cursor.getString(2)
                        error.model = cursor.getString(3)
                        error.type = cursor.getString(4)
                        error.msg = cursor.getString(5)
                        return error
                    }
                }
            }
            return null
        }

        /**
         * 清空所有日志
         * @return 是否成功，false清空失败，true清空成功
         */
        fun clear(context: Context?) {
            if (context == null) throw NullPointerException()
            ErrorDB(context.applicationContext).readableDatabase.use { db -> db.delete("error_log", null, null) }
        }

        /**
         * 导出全部崩溃记录
         */
        @Throws(FileNotFoundException::class)
        fun export(context: Context?, path: File?) {
            val logs = getLogs(context)
            export(path, logs)
        }

        /**
         * 导出指定崩溃记录
         */
        @Throws(FileNotFoundException::class)
        fun export(path: File?, errors: List<Error>) {
            if (path == null) throw NullPointerException()
            if (!path.exists()) {
                if (!path.mkdirs()) {
                    throw FileNotFoundException()
                }
            }
            val sb = StringBuilder()
            sb.append(String.format("%s,%s,%s,%s,%s,%s\n", "thread", "time", "sys", "model", "type", "msg"))
            for (log in errors) {
                sb.append(String.format(Locale.CHINA, "%s,%tF %tT,%s,%s,%s,%s\n", log.thread, log.time, log.time, log.sys, log.model, log.type, log.msg!!.replace("\n", " ~ ")))
            }
            val now = System.currentTimeMillis()
            val file = File(path, String.format(Locale.CHINA, "%tF-%tT.csv", now, now))
            try {
                RandomAccessFile(file, "rw").use { out -> out.write(sb.toString().toByteArray(Charset.forName("GB2312"))) }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}