package cc.xiaobaicz.utils.error;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author BC
 * @date 2019/8/28
 */
public final class Error implements Parcelable {

    public String thread;
    public long time;
    public String sys;
    public String model;
    public String type;
    public String msg;

    public Error() {
    }

    protected Error(Parcel in) {
        thread = in.readString();
        time = in.readLong();
        sys = in.readString();
        model = in.readString();
        type = in.readString();
        msg = in.readString();
    }

    public static final Creator<Error> CREATOR = new Creator<Error>() {
        @Override
        public Error createFromParcel(Parcel in) {
            return new Error(in);
        }

        @Override
        public Error[] newArray(int size) {
            return new Error[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(thread);
        dest.writeLong(time);
        dest.writeString(sys);
        dest.writeString(model);
        dest.writeString(type);
        dest.writeString(msg);
    }

    @Override
    public String toString() {
        return "Error{" +
                "thread='" + thread + '\'' +
                ", time=" + String.format("%tc", time) +
                ", sys='" + sys + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

}
