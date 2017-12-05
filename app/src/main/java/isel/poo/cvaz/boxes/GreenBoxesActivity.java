package isel.poo.cvaz.boxes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import isel.poo.cvaz.boxes.Model.GreenBoxesModel;
import isel.poo.cvaz.boxes.View.GreenBoxesView;
import isel.poo.cvaz.boxes.View.OnBoxTouch;
import isel.poo.cvaz.boxes.View.OnGameOver;

public class GreenBoxesActivity extends AppCompatActivity implements OnGameOver,OnBoxTouch {

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
        topScore = findViewById(R.id.topScore);
        points = findViewById(R.id.points);
        loadTopScore();
        topScore.setText("Top Score: " + topPoints + " by " + playerName);
        points.setText("Points: ");
        view = findViewById(R.id.boxesLayout);
        model.setEndGameListener(this);
        model.setOnBoxListener(this);
        view.init(model);
        view.hide();
        update();
    }

    @Override
    public void checkNewHighScore(int points) {
        if(points>topPoints) saveNewTop(points);

    }

    private void saveNewTop(final int points){
        final EditText name = new EditText(this);
        name.setHint("player name");
        name.setSingleLine(true);
        new AlertDialog.Builder(this)
                .setTitle("New  top score")
                .setView(name)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        playerName = String.valueOf(name.getText());
                        topScore.setText("Top Score: " + points + " by " + playerName);
                        GreenBoxesActivity.this.topPoints = model.getScore();
                        saveTopScore();
                    }
                }).show();
    }

    private void saveTopScore(){
        try(PrintWriter printWriter = new PrintWriter(openFileOutput("TopScore.txt",MODE_PRIVATE))){
            printWriter.println("Name:"+playerName+","+"Score:"+topPoints);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadTopScore(){
        try(Scanner reader = new Scanner(openFileInput("TopScore.txt"))) {
            while(reader.hasNextLine()){
                String line =reader.nextLine();
                String[] newLine = line.split(",");
                //Name:playerName->[0]
                //Score:topPoints->[1]
                int idxName = newLine[0].indexOf(":");
                playerName = newLine[0].substring(idxName+1,newLine[0].length());
                int idxPoints = newLine[1].indexOf(":");
                topPoints = Integer.parseInt(newLine[1].substring(idxPoints+1,newLine[1].length()));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void update() {
        TextView textView = this.points;
        textView.setText("Points: " + this.model.getScore());
        view.invalidate();
    }

    @Override
    public void gameOver(int score) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Game Over. Try Again?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(GreenBoxesActivity.this,"New Game",Toast.LENGTH_LONG).show();
                            model.resetGame();
                            update();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            GreenBoxesActivity.this.finish();
                        }
                    }).create().show();
    }


    @Override
    public void playedMove(int h, int w) {
        if(model.playedMove(h,w)){
            view.hide();
            update();
        }
    }
}
