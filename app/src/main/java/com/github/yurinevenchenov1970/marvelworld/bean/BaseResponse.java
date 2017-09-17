package com.github.yurinevenchenov1970.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * @author Yuri Nevenchenov on 9/4/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("code")
    private int mCode;

    @JsonProperty("data")
    private ResponseData<T> mResponseData;

    public BaseResponse() {
        //Empty constructor needed by Jackson
    }

    protected BaseResponse(Parcel in) {
        mCode = in.readInt();
        mResponseData = in.readParcelable(ResponseData.class.getClassLoader());
    }

    public int getCode() {
        return mCode;
    }

    public ResponseData<T> getResponseData() {
        return mResponseData;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseResponse that = (BaseResponse) o;
        return mCode == that.mCode &&
                Objects.equal(mResponseData, that.mResponseData);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mCode, mResponseData);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mCode", mCode)
                .add("mResponseData", mResponseData)
                .toString();
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mCode);
        parcel.writeParcelable(mResponseData, i);
    }

    private static final class ClassCreator implements Creator<BaseResponse> {
        @Override
        public BaseResponse createFromParcel(Parcel in) {
            return new BaseResponse(in);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    }
}