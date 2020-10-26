package cc.xiaobaicz.helper.error

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

/**
 * @author BC
 * @date 2019/8/28
 */
internal class ErrorDB @JvmOverloads constructor(context: Context?, name: String? = DB_NAME, factory: CursorFactory? = null, version: Int = DB_VERSION, errorHandler: DatabaseErrorHandler? = null) : SQLiteOpenHelper(context, name, factory, version, errorHandler) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """create table `error_log` (
    `id` integer primary key autoincrement,
    `thread` text default 'main',
    `time` integer default 0,
    `sys` text default null,
    `model` text default null,
    `type` text default null,
    `msg` text default null
)"""
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "db_error_log.db"
    }
}