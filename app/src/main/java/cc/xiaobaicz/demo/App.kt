package cc.xiaobaicz.demo

import android.app.Application
import cc.xiaobaicz.helper.process.isMainProcess

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (isMainProcess()) {
            //主进程
            println("这是主进程，可以做一些特定操作")
        } else {
            println("这是其他进程")
        }

    }

}