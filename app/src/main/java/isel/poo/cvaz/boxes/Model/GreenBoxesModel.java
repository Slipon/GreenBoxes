package isel.poo.cvaz.boxes.Model;

import android.graphics.Point;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import isel.poo.cvaz.boxes.View.OnBoxTouch;

/**
 * Created by david on 24/11/2017.
 */

public class GreenBoxesModel {
    public static int width;
    public static int height;
    private boolean[][] boardArray;
    private int score;
    private int blankBoxes;
    private OnBoxTouch listener;
    private final Point lastPoint = new Point(-1, -1);


    private final void setHeight(int height){
        this.height = height;
    }

    public final int getHeight(){
        return this.height;
    }

    private final void setWidth(int width){
        this.width = width;
    }

    public final int getWidth(){
        return this.width;
    }

    private final void setScore(int score){
        this.score=score;
    }

    public final int getScore(){
        return this.score;
    }

    public final void setListener(OnBoxTouch listener){
        this.listener=listener;
    }

    public final OnBoxTouch getListener() {
        return this.listener;
    }

    public final void save(OutputStream out) throws IOException{
        //TODO - DataOutputStream
    }
    public final void load(InputStream in) throws IOException{
        //TODO - DataInputStream
    }

    public final void init(int height, int width){
        this.height=height;
        this.width=width;
        //TODO
    }

    private final void resetGame(){
        this.score=0;
        init(2,2);
    }

    private final void levelUp(){
        //TODO
    }

    public final boolean playedMove(int h, int w){
        if (h == lastPoint.y && w == lastPoint.x) {
            score++;
            if (blankBoxes != 0) {
                paintBox();
            } else {
                levelUp();
            }
            return true;
        }
        if (listener != null) {
            listener.gameOver(this.score);
        }
        resetGame();
        return false;
    }

    private void paintBox() {
        int pos = (int) (Math.random() * ((double) this.blankBoxes));
        int l = 0;
        int last = height;
        if (l <= last) {
            int c;
            int pos2;
            loop0:
            while (true) {
                c = 0;
                int last2 = width;
                if (c <= last2) {
                    while (true) {
                        if (!boardArray[l][c]) {
                            pos2 = pos - 1;
                            if (pos == 0) {
                                break loop0;
                            }
                            pos = pos2;
                        }
                        if (c == last2) {
                            break;
                        }
                        c++;
                    }
                }
                if (l != last) {
                    l++;
                } else {
                    return;
                }
            }
            boardArray[l][c] = true;
            this.lastPoint.set(c, l);
            this.blankBoxes--;
        }
    }

}
