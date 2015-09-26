package patienceshyu.tamatimer;

import android.widget.ImageView;

/**
 * Created by Patience on 15-09-26.
 */
public class Heart {

    boolean alive;
    ImageView heartDisplay;

    public Heart(ImageView heartDisplay) {
        alive = true;
        this.heartDisplay = heartDisplay;
    }

    public void die() {
        alive = false;
        heartDisplay.setImageResource(R.mipmap.ic_clock);
    }
}
