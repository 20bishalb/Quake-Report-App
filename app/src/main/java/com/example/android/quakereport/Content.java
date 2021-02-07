package com.example.android.quakereport;

public class Content {

    private Double mRichterScale;
    private String city;
    private long mTimeInMillisecond;
    private String mUrl;

    public Content (Double richterScale, String city, long timeInMillisecond, String url) {
        mRichterScale = richterScale;
        this.city = city;
        mTimeInMillisecond = timeInMillisecond;
        mUrl = url;
    }

    public double getRichterScale() {
        return mRichterScale;
    }

    public String getCity() {
        return city;
    }

    public long getTimeInMillisecond() {
        return mTimeInMillisecond;
    }

    public String getUrl() {
        return mUrl;
    }

}
