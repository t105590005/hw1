package com.example.user.hw7;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private ImageView ImgRollingDice;
    private Button BtnDice;
    private Button BtnResult;
    private int CountSet = 0,
            CountPlayerWin = 0,
            CountComWin = 0,
            CountDraw = 0;
    public static Context sContext;

    private static class StaticHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public StaticHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity == null) return;
            int iRand = (int)(Math.random()*6 + 1);
            if(iRand==1) {
                Toast.makeText(activity.sContext, "player win", Toast.LENGTH_LONG).show();
                activity.CountPlayerWin++;
                activity.CountSet++;
                activity.ImgRollingDice.setImageResource(R.drawable.dice01);
            }
            else if(iRand==2) {
                Toast.makeText(activity.sContext, "player win", Toast.LENGTH_LONG).show();
                activity.CountPlayerWin++;
                activity.CountSet++;
                activity.ImgRollingDice.setImageResource(R.drawable.dice02);
            }
            else if(iRand==3) {
                Toast.makeText(activity.sContext, "draw", Toast.LENGTH_LONG).show();
                activity.CountDraw++;
                activity.CountSet++;
                activity.ImgRollingDice.setImageResource(R.drawable.dice03);
            }
            else if(iRand==4) {
                Toast.makeText(activity.sContext, "draw", Toast.LENGTH_LONG).show();
                activity.CountDraw++;
                activity.CountSet++;
                activity.ImgRollingDice.setImageResource(R.drawable.dice04);
            }
            else if(iRand==5) {
                Toast.makeText(activity.sContext, "computer win", Toast.LENGTH_LONG).show();
                activity.CountComWin++;
                activity.CountSet++;
                activity.ImgRollingDice.setImageResource(R.drawable.dice05);
            }
            else if(iRand==6) {
                Toast.makeText(activity.sContext, "computer win", Toast.LENGTH_LONG).show();
                activity.CountComWin++;
                activity.CountSet++;
                activity.ImgRollingDice.setImageResource(R.drawable.dice06);
            }
        }
    }

    public final MainActivity.StaticHandler mHandler = new MainActivity.StaticHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle =this.getIntent().getExtras();
        if(bundle!=null)
        {
            CountSet = bundle.getInt("CountSet");
            CountPlayerWin = bundle.getInt("CountPlayerWin");
            CountComWin = bundle.getInt("CountComWin");
            CountDraw = bundle.getInt("CountDraw");
        }

        ImgRollingDice = (ImageView)findViewById(R.id.imgRollingDice);
        BtnDice = (Button)findViewById(R.id.btnDice);
        BtnResult = (Button)findViewById(R.id.btnResult);
        sContext = MainActivity.this;

        BtnDice.setOnClickListener(btnDiceOnClick);
        BtnResult.setOnClickListener(btnResultOnClick);
    }

    private View.OnClickListener btnDiceOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // Decide computer play.
            Resources res = getResources();
            final AnimationDrawable animDraw =
                    (AnimationDrawable) res.getDrawable(R.drawable.anim_roll_dice);
            ImgRollingDice.setImageDrawable(animDraw);
            animDraw.start();

            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    animDraw.stop();
                    mHandler.sendMessage(mHandler.obtainMessage());
                }
            }).start();
        }
    };

    private View.OnClickListener btnResultOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent();
            it.setClass(MainActivity.this, ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("CountSet", CountSet);
            bundle.putInt("CountPlayerWin", CountPlayerWin);
            bundle.putInt("CountComWin", CountComWin);
            bundle.putInt("CountDraw", CountDraw);
            it.putExtras(bundle);
            startActivity(it);
            MainActivity.this.finish();
        }
    };
}
