package isel.poo.cvaz.boxes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import isel.poo.cvaz.boxes.Model.GreenBoxesModel;
import isel.poo.cvaz.boxes.View.GreenBoxesView;
import isel.poo.cvaz.boxes.View.OnBoxTouch;
import isel.poo.cvaz.boxes.View.OnGameOver;

public class GreenBoxesActivity extends AppCompatActivity implements OnGameOver,OnBoxTouch {

    private TextView topScore;
    private TextView points;
    private int topPoints; //TODO
    private final GreenBoxesModel model = new GreenBoxesModel();
    private GreenBoxesView view;
    private String playerName; //TODO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_boxes);
        topScore = findViewById(R.id.topScore);
        points = findViewById(R.id.points);
        topScore.setText("Top Score: " + " x " + " by " + " y ");
        points.setText("Points: " + " x ");
        view = findViewById(R.id.boxesLayout);
        model.setEndGameListener(this);
        model.setOnBoxListener(this);
        view.init(model);
        view.hide();
        update();
    }

    private void update() {
        TextView textView = this.points;
        textView.setText("Points: " + this.model.getScore());
        view.invalidate();
    }


    public void gameOver(int score) {
        //TODO
        //GreenBoxesActivity.this.finishAffinity();
    }


    @Override
    public void playedMove(int h, int w) {
        if(model.playedMove(h,w)){
            view.hide();
        }
        update();
    }
}
