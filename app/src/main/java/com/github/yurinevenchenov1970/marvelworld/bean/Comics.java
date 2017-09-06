package com.github.yurinevenchenov1970.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuri Nevenchenov on 9/4/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comics implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("collectionURI")
    public String mCollectionURI;

    @JsonProperty("items")
    public List<Comic> mComicList;

    public Comics() {
        //Empty constructor needed by Jackson
    }

    protected Comics(Parcel in) {
        mCollectionURI = in.readString();
        mComicList = new ArrayList<>();
        in.readList(mComicList, null);
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mCollectionURI);
        parcel.writeList(mComicList);
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comics comics = (Comics) o;
        return Objects.equal(mCollectionURI, comics.mCollectionURI) &&
                Objects.equal(mComicList, comics.mComicList);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mCollectionURI, mComicList);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mCollectionURI", mCollectionURI)
                .add("mComicList", mComicList)
                .toString();
    }

    private static final class ClassCreator implements Creator<Comics> {
        @Override
        public Comics createFromParcel(Parcel in) {
            return new Comics(in);
        }

        @Override
        public Comics[] newArray(int size) {
            return new Comics[size];
        }
    }
}