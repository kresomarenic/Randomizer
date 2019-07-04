package hr.vvg.mobilne.randomizer.model;

import com.google.gson.annotations.SerializedName;

public class Dog {

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
