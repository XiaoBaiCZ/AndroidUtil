package cc.xiaobaicz.helper.statusbar

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Size(val left: Int, val top: Int, val right: Int, val bottom: Int)

/**
 * 获取系统UI高度
 */
suspend fun systemUiHeight(helper: SystemUiHelper) = suspendCoroutine<Size> { rsp ->
    helper.systemUiHeight(object : SystemUiAttrCallback() {
        override fun windowPaddingSize(left: Int, top: Int, right: Int, bottom: Int) {
            rsp.resume(Size(left, top, right, bottom))
        }
    })
}