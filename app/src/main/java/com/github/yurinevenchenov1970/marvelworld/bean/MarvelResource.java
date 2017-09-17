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
 * @author Yuri Nevenchenov on 9/7/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelResource implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("title")
    private String mTitle;

    @JsonProperty("description")
    private String mDescription;

    @JsonProperty("thumbnail")
    private Thumbnail mThumbnail;

    @JsonProperty("images")
    private List<Thumbnail> mImages;

    @JsonProperty("resourceURI")
    private String mResourceUri;

    @JsonProperty("urls")
    private List<MarvelUrl> mUrls;

    public MarvelResource() {
        //Empty constructor needed by Jackson
    }

    protected MarvelResource(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mThumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        mImages = in.createTypedArrayList(Thumbnail.CREATOR);
        mResourceUri = in.readString();
        mUrls = new ArrayList<>();
        in.readTypedList(mUrls, MarvelUrl.CREATOR);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public Thumbnail getThumbnail() {
        return mThumbnail;
    }

    public List<Thumbnail> getImages() {
        return mImages;
    }

    public String getResourceUri() {
        return mResourceUri;
    }

    public List<MarvelUrl> getUrls() {
        return mUrls;
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mDescription);
        parcel.writeParcelable(mThumbnail, i);
        parcel.writeTypedList(mImages);
        parcel.writeString(mResourceUri);
        parcel.writeTypedList(mUrls);
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarvelResource that = (MarvelResource) o;
        return Objects.equal(mTitle, that.mTitle) &&
                Objects.equal(mDescription, that.mDescription) &&
                Objects.equal(mThumbnail, that.mThumbnail) &&
                Objects.equal(mImages, that.mImages) &&
                Objects.equal(mResourceUri, that.mResourceUri) &&
                Objects.equal(mUrls, that.mUrls);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mTitle,
                mDescription,
                mThumbnail,
                mImages,
                mResourceUri,
                mUrls);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mTitle", mTitle)
                .add("mDescription", mDescription)
                .add("mThumbnail", mThumbnail)
                .add("mImages", mImages)
                .add("mResourceUri", mResourceUri)
                .add("mUrls", mUrls)
                .toString();
    }

    private static final class ClassCreator implements Creator<MarvelResource> {
        @Override
        public MarvelResource createFromParcel(Parcel in) {
            return new MarvelResource(in);
        }

        @Override
        public MarvelResource[] newArray(int size) {
            return new MarvelResource[size];
        }
    }
}