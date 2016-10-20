package com.vccorp.monngon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PC0353 on 10/20/2016.
 */

public class Mon implements Parcelable {
    private int id;
    private String title;
    private String description;
    private String material;
    private String image;
    private String making;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMaking() {
        return making;
    }

    public void setMaking(String making) {
        this.making = making;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(material);
        dest.writeString(image);
        dest.writeString(making);

    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Mon> CREATOR = new Parcelable.Creator<Mon>() {
        public Mon createFromParcel(Parcel in) {
            return new Mon(in);
        }

        public Mon[] newArray(int size) {
            return new Mon[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Mon(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        material = in.readString();
        image = in.readString();
        making = in.readString();

    }
}
