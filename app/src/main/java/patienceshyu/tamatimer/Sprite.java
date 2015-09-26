package patienceshyu.tamatimer;

import android.widget.ImageView;

/**
 * Created by Patience on 15-09-26.
 */
public class Sprite {

    boolean omelette = false;
    int eggStatus;
    ImageView spriteDisplay;

    int image1 = R.mipmap.ic_start;
    int image2 = R.mipmap.ic_stage2;
    int image3 = R.mipmap.ic_stage3;
    int image4 = R.mipmap.ic_stage4;
    int image5 = R.mipmap.ic_stage5;

    int cookedImage = R.mipmap.ic_cooked;

    public Sprite(ImageView spriteDisplay) {

        eggStatus = 1;
        this.spriteDisplay = spriteDisplay;
        spriteDisplay.setImageResource(image1);
    }

    public void cooked() {
        // Set image to omelette
        omelette = true;
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
