package patienceshyu.tamatimer;

import android.widget.ImageView;

/**
 * Created by Patience on 15-09-26.
 */
public class Sprite {

    int eggStatus;
    ImageView spriteDisplay;

    int image1 = R.mipmap.ic_egg;
    int image2 = R.mipmap.ic_launcher;
    int image3 = R.mipmap.ic_clock;
    int image4 = R.mipmap.ic_launcher;
    int image5 = R.mipmap.ic_heart;

    int cookedImage = R.mipmap.ic_launcher;

    public Sprite(ImageView spriteDisplay) {
        eggStatus = 1;
        this.spriteDisplay = spriteDisplay;
        spriteDisplay.setImageResource(image1);
    }

    public void cooked() {
        // Set image to omelette
        spriteDisplay.setImageResource(cookedImage);

    }

    public void nextStatus() {
        if (eggStatus == 1) {
            spriteDisplay.setImageResource(image2); // SET TO 2ND IMAGE
            eggStatus++;
        } else if (eggStatus == 2) {
            spriteDisplay.setImageResource(image3); // SET TO THIRD IMAGE
            eggStatus++;
        } else if (eggStatus == 3) {
            spriteDisplay.setImageResource(image4); // SET TO FOURTH IMAGE
            eggStatus++;
        } else  {
            spriteDisplay.setImageResource(image5); // SET TO FIFTH IMAGE
            eggStatus++;
        }
    }


}
