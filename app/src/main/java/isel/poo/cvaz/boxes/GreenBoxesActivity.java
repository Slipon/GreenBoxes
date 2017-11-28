package isel.poo.cvaz.boxes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import isel.poo.cvaz.boxes.Model.GreenBoxesModel;
import isel.poo.cvaz.boxes.View.GreenBoxesView;
import isel.poo.cvaz.boxes.View.OnGameOver;

public class GreenBoxesActivity extends AppCompatActivity implements OnGameOver {

    private TextView topScore;
    private TextView points;
    private int topPoints;
    private final GreenBoxesModel model = new GreenBoxesModel();
    private GreenBoxesView view;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_boxes);
        LinearLayout root = findViewById(R.id.root);
        topScore = findViewById(R.id.topScore);
        points = findViewById(R.id.points);
        topScore.setText("Top Score: " + " x " + " by " + " y ");
        points.setText("Points: " + " x ");
        view = findViewById(R.id.boxesLayout);
       // GreenBoxesView.viewHeight = boxesLayout.getHeight();
       // GreenBoxesView.viewWidth = boxesLayout.getWidth();
        model.setEndGameListener(this);
       // view = new GreenBoxesView(this,model);
        //root.addView(view);
        view.init(model);
        view.hide();
        update();
    }

    private void update() {
        TextView textView = this.points;
        textView.setText("Points: " + this.model.getScore());
        view.invalidate();
    }


    public final GreenBoxesModel getModel(){
        return this.model;
    }

    public void gameOver(int score) {
        GreenBoxesActivity.this.finishAffinity();
    }

    public final void setPoints(TextView points){
        this.points = points;
    }

    public final TextView getPoints(){
        TextView textView = this.points;
        return textView;
    }

    public final void setTopScore(TextView topScore){
        this.topScore=topScore;
    }

    public final TextView getTopScore(){
        TextView textView = this.topScore;
        return textView;
    }

    public final void setTopPoints(int topPoints){
        this.topPoints=topPoints;
    }

    public final int getTopPoints(){
        return this.topPoints;
    }

    public final void setPlayerName(String name) {
        this.playerName = name;
    }

    public final String getPlayerName() {
        return this.playerName;
    }


}
