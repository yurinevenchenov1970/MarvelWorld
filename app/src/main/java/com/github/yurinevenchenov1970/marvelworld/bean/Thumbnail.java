package com.github.yurinevenchenov1970.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * @author Yuri Nevenchenov on 9/4/2017.
 */

public class Thumbnail implements Parcelable {

    @StringDef({STANDARD_MEDIUM,
            LANDSCAPE_AMAZING,
            LANDSCAPE_INCREDIBLE,
            PORTRAIT_FANTASTIC
    })

    public @interface ThumbnailSize {
    }

    public static final String STANDARD_MEDIUM = "standard_medium";
    public static final String LANDSCAPE_AMAZING = "landscape_amazing";
    public static final String LANDSCAPE_INCREDIBLE = "landscape_incredible";
    public static final String PORTRAIT_FANTASTIC = "portrait_fantastic";
    public static final String PORTRAIT_UNCANNY = "portrait_uncanny";

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("path")
    public String mPath;

    @JsonProperty("extension")
    public String mExtension;

    @JsonProperty("resourceURI")
    public String mResourceURI;

    @JsonProperty("comix")
    public Comics mComics;

    public Thumbnail() {
        //Empty constructor needed by Jackson
    }

    protected Thumbnail(Parcel in) {
        mPath = in.readString();
        mExtension = in.readString();
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mPath);
        parcel.writeString(mExtension);
    }

    /**
     * +     * @param size one of the supported sizes
     * +     * @return full path to the image
     * +
     */
    @JsonIgnore
    public String getFullPath(@ThumbnailSize String size) {
        return mPath + "/" + size + "." + mExtension;
    }

    @JsonIgnore
    public String getFullSize() {
        return mPath + "." + mExtension;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thumbnail thumbnail = (Thumbnail) o;
        return Objects.equal(mPath, thumbnail.mPath) &&
                Objects.equal(mExtension, thumbnail.mExtension);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mPath, mExtension);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mPath", mPath)
                .add("mExtension", mExtension)
                .toString();
    }

    private static final class ClassCreator implements Creator<Thumbnail> {
        @Override
        public Thumbnail createFromParcel(Parcel in) {
            return new Thumbnail(in);
        }

        @Override
        public Thumbnail[] newArray(int size) {
            return new Thumbnail[size];
        }
    }
}