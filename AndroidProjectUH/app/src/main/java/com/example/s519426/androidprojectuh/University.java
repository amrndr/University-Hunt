package com.example.s519426.androidprojectuh;

/**
 * Created by Amerender Singh, Giridharan Srinivasaraj, Mounika Thangella.
 */

import android.graphics.Bitmap;

/**
 * Created by S519426 on 11/28/2014.
 */
public class University {


    private String name;
    private Bitmap pic;

    public University(String name, Bitmap pic) {
        this.name = name;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public Bitmap getPic() {
        return pic;
    }


    @Override
    public String toString() {
        return name;
    }
}
