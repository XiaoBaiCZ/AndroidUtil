package cc.xiaobaicz.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.xiaobaicz.demo.databinding.ActivityMainBinding
import cc.xiaobaicz.helper.io.readFrom
import cc.xiaobaicz.helper.io.writeTo
import cc.xiaobaicz.helper.statusbar.SystemUiAttrCallback
import cc.xiaobaicz.helper.statusbar.SystemUiHelper
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        //UI助手
        SystemUiHelper.get(this)
            .setStatusBarColor(0x33000000)
            .setNavigationBarColor(0x33000000)
            .transparentScreen()
            .systemUiHeight(object : SystemUiAttrCallback() {
                override fun windowPaddingSize(left: Int, top: Int, right: Int, bottom: Int) {
                    bind.root.setPadding(0, top, 0, 0)
                }
            })

        //io流
        assets.open("message.txt").use { input ->
            ByteArrayOutputStream().use { out ->
//                input.writeTo(out)  //简化IO读写
                out.readFrom(input)
                bind.message.text = out.toString("UTF-8")
            }
        }

    }

}