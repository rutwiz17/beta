package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView timer;
    private CountDownTimer ctimer;
    private boolean timerr;
    private long tleft = 10000;
    int n1,n2,n3,num,sc=0,h=0;
    Button op1,op2,op3,gen;
    Vibrator vibrator;
    TextView score,hs;
    View view;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer=findViewById(R.id.timer);
        input=findViewById(R.id.input);
        op1=findViewById(R.id.op1);
        op2=findViewById(R.id.op2);
        op3=findViewById(R.id.op3);
        gen=findViewById(R.id.gen);
        score=findViewById(R.id.score);
        hs=findViewById(R.id.hs);
        SharedPreferences myScore=this.getSharedPreferences("HIGH SCORE",MODE_PRIVATE);
        h=myScore.getInt("SCORE",0);
        hs.setText("HIGH SCORE : "+h);
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        view=this.getWindow().getDecorView();
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(input.getText().toString().trim().length()>0))
                    Toast.makeText(MainActivity.this,"ENTER A NUMBER",Toast.LENGTH_LONG).show();
                else if(Integer.parseInt(input.getText().toString())<5)
                    Toast.makeText(MainActivity.this,"ENTER A NUMBER GREATER THAN 5",Toast.LENGTH_LONG).show();
                else {
                    num = Integer.parseInt(input.getText().toString());
                    Random rand = new Random();
                    Random sand = new Random();
                    Random tand = new Random();
                    int s = 1;
                    do {
                        n1 = rand.nextInt(num) + 1;
                        n2 = sand.nextInt(num) + 1;
                        n3 = tand.nextInt(num) + 1;
                        if (num % n1 == 0 && num % n2 != 0 && num % n3 != 0 && n2 != n3)
                            s = 0;
                        else if (num % n1 != 0 && num % n2 == 0 && num % n3 != 0 && n1 != n3)
                            s = 0;
                        else if (num % n1 != 0 && num % n2 != 0 && num % n3 == 0 && n1 != n2)
                            s = 0;
                    } while (s == 1);
                    String str1 = String.valueOf(n1);
                    op1.setText(str1);
                    String str2 = String.valueOf(n2);
                    op2.setText(str2);
                    String str3 = String.valueOf(n3);
                    op3.setText(str3);
                    startstop();
                }
            }
        });
        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences myScore = getSharedPreferences("HIGH SCORE", MODE_PRIVATE);
                if (!(input.getText().toString().trim().length() > 0))
                    Toast.makeText(MainActivity.this, "ENTER A NUMBER", Toast.LENGTH_LONG).show();
                else if (Integer.parseInt(input.getText().toString()) < 5)
                    Toast.makeText(MainActivity.this, "ENTER A NUMBER GREATER THAN 5", Toast.LENGTH_LONG).show();
                else {
                    if (num % n1 == 0) {
                        view.setBackgroundResource(R.color.green);
                        Toast.makeText(MainActivity.this, "CORRECT ANSWER!", Toast.LENGTH_LONG).show();
                        sc++;
                        score.setText("SCORE : " + sc);
                    } else {
                        view.setBackgroundResource(R.color.red);
                        vibrator.vibrate(500);
                        if (num % n2 == 0)
                            Toast.makeText(MainActivity.this, "WRONG ANSWER! CORRECT ANSWER IS " + n2, Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "WRONG ANSWER! CORRECT ANSWER IS " + n3, Toast.LENGTH_LONG).show();
                        if (sc > h) {
                            SharedPreferences.Editor editor = myScore.edit();
                            h = sc;
                            editor.putInt("SCORE", h);
                            editor.commit();
                        }
                        sc = 0;
                        score.setText("SCORE : " + sc);
                        hs.setText("HIGH SCORE : " + h);

                    }
                    input.setText("");
                    op1.setText("");
                    op2.setText("");
                    op3.setText("");
                    startstop();
                }
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences myScore = getSharedPreferences("HIGH SCORE", MODE_PRIVATE);
                if (!(input.getText().toString().trim().length() > 0))
                    Toast.makeText(MainActivity.this, "ENTER A NUMBER", Toast.LENGTH_LONG).show();
                else if (Integer.parseInt(input.getText().toString()) < 5)
                    Toast.makeText(MainActivity.this, "ENTER A NUMBER GREATER THAN 5", Toast.LENGTH_LONG).show();
                else {
                    if (num % n2 == 0) {
                        view.setBackgroundResource(R.color.green);
                        Toast.makeText(MainActivity.this, "CORRECT ANSWER!", Toast.LENGTH_LONG).show();
                        sc++;
                        score.setText("SCORE : " + sc);
                    } else {
                        view.setBackgroundResource(R.color.red);
                        vibrator.vibrate(500);
                        if (num % n1 == 0)
                            Toast.makeText(MainActivity.this, "WRONG ANSWER! CORRECT ANSWER IS " + n1, Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "WRONG ANSWER! CORRECT ANSWER IS " + n3, Toast.LENGTH_LONG).show();
                        if (sc > h) {
                            SharedPreferences.Editor editor = myScore.edit();
                            h = sc;
                            editor.putInt("SCORE", h);
                            editor.commit();
                        }
                        sc = 0;
                        score.setText("SCORE : " + sc);
                        hs.setText("HIGH SCORE : " + h);
                    }
                    input.setText("");
                    op1.setText("");
                    op2.setText("");
                    op3.setText("");
                    startstop();
                }
            }

        });
        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences myScore = getSharedPreferences("HIGH SCORE", MODE_PRIVATE);
                if (!(input.getText().toString().trim().length() > 0))
                    Toast.makeText(MainActivity.this, "ENTER A NUMBER", Toast.LENGTH_LONG).show();
                else if (Integer.parseInt(input.getText().toString()) < 5)
                    Toast.makeText(MainActivity.this, "ENTER A NUMBER GREATER THAN 5", Toast.LENGTH_LONG).show();
                else {
                    if (num % n3 == 0) {
                        view.setBackgroundResource(R.color.green);
                        Toast.makeText(MainActivity.this, "CORRECT ANSWER!", Toast.LENGTH_LONG).show();
                        sc++;
                        score.setText("SCORE : " + sc);
                    } else {
                        view.setBackgroundResource(R.color.red);
                        vibrator.vibrate(500);
                        if (num % n2 == 0)
                            Toast.makeText(MainActivity.this, "WRONG ANSWER! CORRECT ANSWER IS " + n2, Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "WRONG ANSWER! CORRECT ANSWER IS " + n1, Toast.LENGTH_LONG).show();
                        if (sc > h) {
                            SharedPreferences.Editor editor = myScore.edit();
                            h = sc;
                            editor.putInt("SCORE", h);
                            editor.commit();
                        }
                        sc = 0;
                        score.setText("SCORE : " + sc);
                        hs.setText("HIGH SCORE : " + h);
                    }
                    input.setText("");
                    op1.setText("");
                    op2.setText("");
                    op3.setText("");
                    startstop();
                }
            }
        });
        updatetimer();
    }
    public void startstop(){
        if(timerr){
        stoptimer();
        }
        else{
            starttimer();
        }
    }
     public void starttimer(){
        ctimer=new CountDownTimer(tleft,1000) {
            @Override
            public void onTick(long l) {
            tleft=l;
            updatetimer();
            }

            @Override
            public void onFinish() {
                SharedPreferences myScore = getSharedPreferences("HIGH SCORE", MODE_PRIVATE);
                view.setBackgroundResource(R.color.red);
                vibrator.vibrate(500);
                    Toast.makeText(MainActivity.this, "BE A LITTLE FAST SLOWPOKE!", Toast.LENGTH_LONG).show();
                if (sc > h) {
                    SharedPreferences.Editor editor = myScore.edit();
                    h = sc;
                    editor.putInt("SCORE", h);
                    editor.commit();
                }
                sc = 0;
                score.setText("SCORE : " + sc);
                hs.setText("HIGH SCORE : " + h);
                input.setText("");
                op1.setText("");
                op2.setText("");
                op3.setText("");
            }
        }.start();
        timerr=true;
     }
     public void stoptimer(){
        tleft=10000;
        ctimer.cancel();
        timerr=false;
     }
     public void updatetimer(){
        int seconds=(int)tleft/1000;
        String timelefttxt;
        timelefttxt=""+seconds;
        timer.setText("TIME LEFT : "+timelefttxt);
     }
}
