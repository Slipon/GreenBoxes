package isel.poo.cvaz.boxes.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import isel.poo.cvaz.boxes.Model.Box;
import isel.poo.cvaz.boxes.Model.GreenBoxesModel;

/**
 * Created by david on 21/11/2017.
 */

public class GreenBoxesView extends View{

    public static int viewHeight;
    public static int viewWidth;
    private GreenBoxesModel model;
    private final Paint paint = new Paint();
    private long hidenTimer;


    public GreenBoxesView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs,defStyle);
    }

    public GreenBoxesView(Context context, AttributeSet attrs){
        super(context, attrs,0);
    }

    public GreenBoxesView(Context context){
        super(context,null,0);
    }

    public void init(GreenBoxesModel model) {
        this.model = model;
        this.paint.setColor(Box.getBoxColor());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.hidenTimer > ((long) 0)) {
            if (System.currentTimeMillis() - this.hidenTimer < ((long) 1000)) {
                postInvalidateDelayed(100);
                return;
            }
            this.hidenTimer = 0;
        }
        initDims();
        int firstPosLn = 0;
        int lastPosLn = model.getHeight()-1;
        if (firstPosLn <= lastPosLn) {
            while (true) {
                int firstPosCl = 0;
                int lastPosCl = model.getWidth()-1;
                if (firstPosCl <= lastPosCl) {
                    while (firstPosCl <= lastPosCl) {
                        if (this.model.checkBoxPainted(firstPosLn, firstPosCl)) {
                            int x = firstPosCl * Box.getWidth();
                            int y = firstPosLn * Box.getHeight();
                            canvas.drawRect((float) x, (float) y, (float) (Box.getWidth() + x), (float) (Box.getHeight() + y), this.paint);
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
        }
    }

    private void initDims() {
        viewWidth = getWidth();
        viewHeight = getHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int heightLine = ((int) event.getY()) / Box.getHeight();
            int widthCol = ((int) event.getX()) / Box.getWidth();
            if (model.checkBoxPainted(heightLine, widthCol)) {
                OnBoxTouch lst = this.model.getListener();
                lst.playedMove(heightLine, widthCol);
                invalidate();
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    public final void hide() {
        this.hidenTimer = System.currentTimeMillis();
        invalidate();
    }
}
