package com.example.khilanmakwana.scamesdice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity{

    TextView yourscore,computerscore,turn,tempScore;
    Button roll_btn,hold_btn,reset_btn;
    ImageView img;

    int your_temp, your_global, com_temp, com_global;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yourscore = (TextView) findViewById(R.id.yourscore);
        computerscore = (TextView) findViewById(R.id.cscore);
        turn = (TextView) findViewById(R.id.turn);
        tempScore = (TextView) findViewById(R.id.temp_score);

        turn.setText("Your: ");

        roll_btn = (Button) findViewById(R.id.roll);
        hold_btn = (Button) findViewById(R.id.hold);
        reset_btn = (Button) findViewById(R.id.reset);
        img = (ImageView) findViewById(R.id.dice_img);

        resetgame();

        roll_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int x = rand.nextInt(6)+1;
                rolldice(x);

                if (x == 1){
                    Toast.makeText(getApplicationContext(), "Computer Turn",Toast.LENGTH_SHORT).show();
                    your_temp = 0;
                    tempScore.setText(String.valueOf(your_temp));
                    computerturn();
                }
                else{
                    your_temp += x;
                    tempScore.setText(String.valueOf(your_temp));
                }

            }
        });
        hold_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                your_global += your_temp;
                your_temp = 0;
                yourscore.setText((String.valueOf(your_global)));
                if(your_global >= 100){
                    Toast.makeText(getApplicationContext(),"You won!!!",Toast.LENGTH_SHORT).show();
                    resetgame();
                }else {
                    Toast.makeText(getApplicationContext(), "Computer Turn",Toast.LENGTH_SHORT).show();
                    turn.setText("Computer: ");
                    tempScore.setText(String.valueOf(your_temp));
                    computerturn();

                }




            }
        });
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetgame();

            }
        });

    }

    private void computerturn() {
        Random rand = new Random();
        int x = rand.nextInt(6)+1;


        rolldice(x);

        if(x == 1){
            com_temp = 0;
            turn.setText("Your: ");
            tempScore.setText(String.valueOf(com_temp));
            Toast.makeText(getApplicationContext(),"Your Turn",Toast.LENGTH_SHORT).show();
        } else {
            com_temp += x;
            tempScore.setText(String.valueOf(com_temp));

            if((com_temp+com_global) >= 100){
                Toast.makeText(getApplicationContext(),"Computer won!!!",Toast.LENGTH_SHORT).show();
                resetgame();
            }else {
                if(com_temp >= 13){
                    computerHold();
                }else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            computerturn();
                        }
                    },5000);

                }

            }
        }


    }

    private void computerHold() {
        com_global += com_temp;
        com_temp = 0;

        turn.setText("Your: ");
        tempScore.setText(String.valueOf(com_temp));
        computerscore.setText(String.valueOf(com_global));

        Toast.makeText(getApplicationContext(),"Your Turn", Toast.LENGTH_SHORT).show();
    }

    private void rolldice(int x) {
        switch (x){
            case 1:
                img.setImageResource(R.drawable.dice1);
                break;

            case 2:
                img.setImageResource(R.drawable.dice2);
                break;

            case 3:
                img.setImageResource(R.drawable.dice3);
                break;

            case 4:
                img.setImageResource(R.drawable.dice4);
                break;

            case 5:
                img.setImageResource(R.drawable.dice5);
                break;

            case 6:
                img.setImageResource(R.drawable.dice6);
                break;
        }

    }

    private void resetgame() {

        your_global = 0;
        your_temp = 0;
        com_global = 0;
        com_temp = 0;

        yourscore.setText(String.valueOf(your_global));
        computerscore.setText(String.valueOf(com_global));
    }

}
