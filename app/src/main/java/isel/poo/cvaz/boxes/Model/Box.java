package isel.poo.cvaz.boxes.Model;

import android.graphics.Color;

import isel.poo.cvaz.boxes.View.GreenBoxesView;

/**
 * Created by david on 24/11/2017.
 */

public class Box {
    private static final int BoxColor = Color.GREEN;
    private static final int height = GreenBoxesView.viewHeight/ GreenBoxesModel.height;
    private static final int width = GreenBoxesView.viewWidth/ GreenBoxesModel.width;

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
