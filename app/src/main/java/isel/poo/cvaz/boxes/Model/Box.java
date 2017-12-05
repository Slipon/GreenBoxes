package isel.poo.cvaz.boxes.Model;

import android.graphics.Color;

import isel.poo.cvaz.boxes.View.GreenBoxesView;

/**
 * Created by david on 24/11/2017.
 */

public class Box {
    private static final int BoxColor = Color.BLUE;

    public static int getHeight() {
        return GreenBoxesView.viewHeight/ GreenBoxesModel.height;
    }

    public static int getWidth() {
        return GreenBoxesView.viewWidth/ GreenBoxesModel.width;
    }

    public static int getBoxColor() {
        return BoxColor;
    }
}
