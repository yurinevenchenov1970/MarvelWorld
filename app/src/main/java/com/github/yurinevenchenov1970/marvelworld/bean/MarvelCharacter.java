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
public class MarvelCharacter implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("name")
    public String mName;

    @JsonProperty("description")
    public String mDescription;

    @JsonProperty("thumbnail")
    public Thumbnail mThumbnail;

    @JsonProperty("resourceURI")
    public String mResourceUri;

    @JsonProperty("comics")
    public Comics mComics;

    public MarvelCharacter() {
        //Empty constructor needed by Jackson
    }

    protected MarvelCharacter(Parcel in) {
        mName = in.readString();
        mDescription = in.readString();
        mThumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        mResourceUri = in.readString();
        mComics = in.readParcelable(Comics.class.getClassLoader());
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarvelCharacter that = (MarvelCharacter) o;
        return Objects.equal(mName, that.mName) &&
                Objects.equal(mDescription, that.mDescription) &&
                Objects.equal(mThumbnail, that.mThumbnail) &&
                Objects.equal(mResourceUri, that.mResourceUri) &&
                Objects.equal(mComics, that.mComics);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mName, mDescription, mThumbnail, mResourceUri, mComics);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mName", mName)
                .add("mDescription", mDescription)
                .add("mThumbnail", mThumbnail)
                .add("mResourceUri", mResourceUri)
                .add("mComics", mComics)
                .toString();
    }

    private static final class ClassCreator implements Creator<MarvelCharacter> {
        @Override
        public MarvelCharacter createFromParcel(Parcel in) {
            return new MarvelCharacter(in);
        }

        @Override
        public MarvelCharacter[] newArray(int size) {
            return new MarvelCharacter[size];
        }
    }
}