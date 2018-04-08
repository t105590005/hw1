package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    private TextView mTxtComPlay, mTxtResult;
    private Button mBtnScissors, mBtnStone, mBtnPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtComPlay = (TextView) findViewById(R.id.txtComPlay);
        mTxtResult = (TextView) findViewById(R.id.txtResult);
        mBtnScissors = (Button) findViewById(R.id.btnScissors);
        mBtnStone = (Button) findViewById(R.id.btnStone);
        mBtnPaper = (Button) findViewById(R.id.btnPaper);

        mBtnScissors.setOnClickListener(btnScissorsOnClick);
        mBtnStone.setOnClickListener(btnStoneOnClick);
        mBtnPaper.setOnClickListener(btnPaperOnClick);
    }

    private int SetCom() {
        int iComPlay = (int) (Math.random() * 3 + 1);
        switch (iComPlay) {
            case 1:
                mTxtComPlay.setText(R.string.play_scissors);
                break;
            case 2:
                mTxtComPlay.setText(R.string.play_stone);
                break;
            case 3:
                mTxtComPlay.setText(R.string.play_paper);
                break;
        }
        return iComPlay;
    }

    private View.OnClickListener btnScissorsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            WinOrLose result = new WinOrLose();
            String who = result.who(1, SetCom());
            if (who == "win") {
                mTxtComPlay.setText(R.string.play_paper);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_win));
            } else if (who == "lose") {
                mTxtComPlay.setText(R.string.play_stone);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_lose));
            } else {
                mTxtComPlay.setText(R.string.play_scissors);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_draw));
            }
        }
    };

    private View.OnClickListener btnStoneOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            WinOrLose result = new WinOrLose();
            String who = result.who(2, SetCom());
            if (who == "win") {
                mTxtComPlay.setText(R.string.play_scissors);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_win));
            } else if (who == "lose") {
                mTxtComPlay.setText(R.string.play_paper);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_lose));
            } else {
                mTxtComPlay.setText(R.string.play_stone);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_draw));
            }
        }
    };

    private View.OnClickListener btnPaperOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            WinOrLose result = new WinOrLose();
            String who = result.who(3, SetCom());
            if (who == "win") {
                mTxtComPlay.setText(R.string.play_stone);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_win));
            } else if (who == "lose") {
                mTxtComPlay.setText(R.string.play_scissors);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_lose));
            } else {
                mTxtComPlay.setText(R.string.play_paper);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_draw));
            }
        }
    };
}