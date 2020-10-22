package cc.xiaobaicz.helper.io

import java.io.InputStream
import java.io.OutputStream
import java.io.Reader
import java.io.Writer

/**
 * 从当前输入流写到目标输出流
 */
fun InputStream.writeTo(outputStream: OutputStream) {
    val buff = ByteArray(1024)
    var len = this.read(buff)
    while (len != -1) {
        outputStream.write(buff, 0, len)
        len = this.read(buff)
    }
}

/**
 * 从当前输出流写到目标输入流
 */
fun OutputStream.readFrom(inputStream: InputStream) {
    val buff = ByteArray(1024)
    var len = inputStream.read(buff)
    while (len != -1) {
        this.write(buff, 0, len)
        len = inputStream.read(buff)
    }
}

/**
 * 从当前输入流写到目标输出流
 */
fun Reader.writeTo(writer: Writer) {
    val buff = CharArray(1024)
    var len = this.read(buff)
    while (len != -1) {
        writer.write(buff, 0, len)
        len = this.read(buff)
    }
}

/**
 * 从当前输出流写到目标输入流
 */
fun Writer.readFrom(reader: Reader) {
    val buff = CharArray(1024)
    var len = reader.read(buff)
    while (len != -1) {
        this.write(buff, 0, len)
        len = reader.read(buff)
    }
}