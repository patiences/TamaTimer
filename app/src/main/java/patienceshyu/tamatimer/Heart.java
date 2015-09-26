package patienceshyu.tamatimer;

import android.widget.ImageView;

/**
 * Created by Patience on 15-09-26.
 */
public class Heart {

    boolean alive;
    ImageView heartDisplay;
    int redHeart = R.mipmap.ic_heart;
    int deadHeart = R.mipmap.ic_deadheart;

    public Heart(ImageView heartDisplay) {
        alive = true;
        this.heartDisplay = heartDisplay;
        heartDisplay.setImageResource(redHeart);
    }

    public void die() {
        alive = false;
        heartDisplay.setImageResource(deadHeart);
    }
}
