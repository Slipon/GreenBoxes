package isel.poo.cvaz.boxes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import isel.poo.cvaz.boxes.Model.GreenBoxesModel;
import isel.poo.cvaz.boxes.View.GreenBoxesView;

public class GreenBoxesActivity extends AppCompatActivity {

    private TextView topScore;
    private TextView points;
    private final GreenBoxesModel model = new GreenBoxesModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_boxes);
        topScore = findViewById(R.id.topScore);
        points = findViewById(R.id.points);
        topScore.setText("Top Score: " + " x " + " by " + " y ");
        points.setText("Points: " + " x ");
        LinearLayout boxesLayout = findViewById(R.id.boxesLayout);
        GreenBoxesView.viewHeight = boxesLayout.getHeight();
        GreenBoxesView.viewWidth = boxesLayout.getWidth();
    }

    public final GreenBoxesModel getModel(){
        return this.model;
    }

    public void gameOver(int score) {
        GreenBoxesActivity.this.finishAffinity();
    }
}
