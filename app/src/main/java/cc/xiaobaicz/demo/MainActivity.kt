package cc.xiaobaicz.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.xiaobaicz.helper.statusbar.SystemUiHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemUiHelper.get(this)
            .setStatusBarColor(0x33000000)
            .setNavigationBarColor(0x33000000)
            .transparentScreen()

    }

}