package isel.poo.cvaz.boxes;

import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import isel.poo.cvaz.boxes.Model.Box;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Button blueButton = findViewById(R.id.blueButton);
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Box.BoxColor= ResourcesCompat.getColor(getResources(), R.color.blue, null);
            }
        });

        Button orangeButton = findViewById(R.id.orangeButton);
        orangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Box.BoxColor= ResourcesCompat.getColor(getResources(), R.color.orange, null);
            }
        });

        Button redButton = findViewById(R.id.redButton);
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Box.BoxColor= ResourcesCompat.getColor(getResources(), R.color.red, null);
            }
        });

        Button greenButton = findViewById(R.id.greenButton);
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Box.BoxColor= ResourcesCompat.getColor(getResources(), R.color.green, null);
            }
        });
    }
}
