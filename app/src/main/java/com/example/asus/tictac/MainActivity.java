package com.example.asus.tictac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int allPlayer=0;
    boolean active=true;
    int win[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int position[] ={2,2,2,2,2,2,2,2,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void dropIn(View view){
        ImageView count=(ImageView) view;
        int gameTapp=Integer.parseInt(count.getTag().toString());
        if(position[gameTapp]==2&& active) {
            position[gameTapp] = allPlayer;
            count.setTranslationY(-1000f);
            if (allPlayer == 0) {
                count.setImageResource(R.drawable.yellow);
                allPlayer = 1;
            } else {
                count.setImageResource(R.drawable.red);
                allPlayer = 0;
            }
            count.animate().translationYBy(1000f).rotation(360).setDuration(700);

            for(int win[]:win){
                if(position[win[0]]==position[win[1]]&&
                        position[win[1]]==position[win[2]]&&
                        position[win[0]]!=2){

                    active=false;
                    String winner="Red";

                    if(position[win[0]]==0){
                        winner="Yellow";
                    }

                    System.out.println(position[win[0]]);
                    TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner+" has won!");
                    LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);


                }else{
                    boolean gameisOver=true;
                    for(int counterState:position){
                        if(counterState==2)gameisOver=false;
                    }
                    if(gameisOver){
                        TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a Draw");
                        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }
    }
    public void playAgain(View view){
        active=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        allPlayer=0;

        for(int i=0;i<position.length;i++){
            position[i]=2;
        }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


}
