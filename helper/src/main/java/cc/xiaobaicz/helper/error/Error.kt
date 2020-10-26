package cc.xiaobaicz.helper.error

import android.os.Parcel
import android.os.Parcelable

/**
 * @author BC
 * @date 2019/8/28
 */
class Error : Parcelable {
    var thread: String? = null
    var time: Long = 0
    var sys: String? = null
    var model: String? = null
    var type: String? = null
    var msg: String? = null

    constructor() {}
    protected constructor(`in`: Parcel) {
        thread = `in`.readString()
        time = `in`.readLong()
        sys = `in`.readString()
        model = `in`.readString()
        type = `in`.readString()
        msg = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(thread)
        dest.writeLong(time)
        dest.writeString(sys)
        dest.writeString(model)
        dest.writeString(type)
        dest.writeString(msg)
    }

    override fun toString(): String {
        return "Error{" +
                "thread='" + thread + '\'' +
                ", time=" + String.format("%tc", time) +
                ", sys='" + sys + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", msg='" + msg + '\'' +
                '}'
    }

    companion object {
        val CREATOR: Parcelable.Creator<Error> = object : Parcelable.Creator<Error> {
            override fun createFromParcel(`in`: Parcel): Error? {
                return Error(`in`)
            }

            override fun newArray(size: Int): Array<Error?> {
                return arrayOfNulls(size)
            }
        }
    }
}