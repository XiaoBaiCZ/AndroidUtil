package cc.xiaobaicz.utils.error;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author BC
 * @date 2019/8/28
 */
final class ErrorDB extends SQLiteOpenHelper {

    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "db_error_log.db";

    public ErrorDB(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    public ErrorDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        this(context, name, factory, version, null);
    }

    public ErrorDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table `error_log` (\n" +
                "    `id` integer primary key autoincrement,\n" +
                "    `thread` text default 'main',\n" +
                "    `time` integer default 0,\n" +
                "    `sys` text default null,\n" +
                "    `model` text default null,\n" +
                "    `type` text default null,\n" +
                "    `msg` text default null\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
