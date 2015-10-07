package com.silion.jsonsample;

/**
 * Created by silion on 2015/8/26.
 */
public class Information {
    private long mId;
    private String mText;
    private double[] mGeo;
    private User mUser;

    public Information() {

    }

    public Information(long mId, String mText, double[] mGeo, User mUser) {
        this.mId = mId;
        this.mText = mText;
        this.mGeo = mGeo;
        this.mUser = mUser;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public void setGeo(double[] geo) {
        this.mGeo = geo;
    }

    public void setUser(User user) {
        this.mUser = user;
    }

    public long getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    public double[] getGeo() {
        return mGeo;
    }

    public User getUser() {
        return mUser;
    }

    static public class User {
        private String name;
        private int cf;

        public User() {

        }

        public User(String name, int cf) {
            this.name = name;
            this.cf = cf;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCf(int cf) {
            this.cf = cf;
        }

        public String getName() {
            return name;
        }

        public int getCf() {
            return cf;
        }
    }

    @Override
    public String toString() {
        return new String("id = " + getId() + ", text = " + getText() +
                ", geo = " + getGeo() + ", user = " + getUser());
    }
}
