package isel.poo.cvaz.boxes.Model;

import android.graphics.Point;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import isel.poo.cvaz.boxes.View.OnBoxTouch;
import isel.poo.cvaz.boxes.View.OnGameOver;

/**
 * Created by david on 24/11/2017.
 */

public class GreenBoxesModel{
    public static int width = 2;
    public static int height = 3;
    private boolean[][] boardArray;
    private int score;
    private int blankBoxes;
    private OnGameOver onGameOver;
    private final Point lastPoint = new Point(-1, -1);
    private OnBoxTouch onBoxListener;

    public GreenBoxesModel(){
        init();
    }

    public final int getScore(){
        return this.score;
    }


    public final void setEndGameListener(OnGameOver listener){
        this.onGameOver = listener;
    }

    public final void setOnBoxListener(OnBoxTouch listener){
        this.onBoxListener = listener;
    }

    public final OnBoxTouch getOnBoxTouchListener() {
        return onBoxListener;
    }

    public final void save(OutputStream out) throws IOException{
        //TODO - DataOutputStream
    }
    public final void load(InputStream in) throws IOException{
        //TODO - DataInputStream
    }

    private void init(){
        lastPoint.set(-1, -1);
        this.boardArray = new boolean[height][width];
        this.blankBoxes = height * width;
        paintBox();
    }

    public void resetGame(){
        this.score=0;
        height = 3;
        width = 2;
        init();
    }

    private void levelUp(){
        int sumTo;
        sumTo= (height-width <= 1) ? height++ : width++;
        init();
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
        if (onGameOver != null) {
            onGameOver.gameOver(this.score);
            onGameOver.checkNewHighScore(this.score);

        }
        return false;
    }

    private void paintBox() {
        int randomBox = (int) (Math.random() * ((double) blankBoxes));
        int firstPosLn = 0;
        int lastPosLn = height-1;
        if (firstPosLn <= lastPosLn) {
            int firstPosCl;
            loop_label:
            while (true) {
                firstPosCl = 0;
                int lastPosCl = width-1;
                if (firstPosCl <= lastPosCl) {
                    while (firstPosCl <= lastPosCl) {
                        if (!boardArray[firstPosLn][firstPosCl]) {
                            if (randomBox == 0) {
                                break loop_label;
                            }
                            randomBox -= 1;
                        }
                        firstPosCl++;
                    }
                }
                if (firstPosLn != lastPosLn) {
                    firstPosLn++;
                } else {
                    return;
                }
            }
            boardArray[firstPosLn][firstPosCl] = true;
            lastPoint.set(firstPosCl, firstPosLn);
            blankBoxes--;
        }
    }

    public boolean checkBoxPainted(int h, int w){
        return boardArray[h][w];
    }

}
