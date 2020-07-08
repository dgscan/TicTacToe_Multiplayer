package com.example.user.tictactoe_multiplayer;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Socket;

public class GameplayActivity extends AppCompatActivity {

    private String turn;
    Button[][] board;
    TextView txt;
    TextView user;
    public Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        Intent myIntent = getIntent();
        turn = myIntent.getStringExtra("turn");


        board = new Button[3][3];

        board[0][0] = findViewById(R.id.button1);
        board[0][1] = findViewById(R.id.button2);
        board[0][2] = findViewById(R.id.button3);
        board[1][0] = findViewById(R.id.button4);
        board[1][1] = findViewById(R.id.button5);
        board[1][2] = findViewById(R.id.button6);
        board[2][0] = findViewById(R.id.button7);
        board[2][1] = findViewById(R.id.button8);
        board[2][2] = findViewById(R.id.button9);

        txt = findViewById(R.id.textView);

        user=findViewById(R.id.textView2);

        user.append(", "+turn);
    }

    public void buttonClickHandler(View v){
        Button b = (Button) v;

        if(b.getText().length()>0) return;
        b.setText(turn);


        System.out.println("0-0: "+board[0][0].getText() +" 0-1: "+board[0][1].getText()+" 0-2: "+board[0][2].getText());

        if(board[0][0].getText() == turn && board[0][1].getText() == turn && board[0][2].getText() == turn){
            txt.setText("Winner is, "+turn);
            txt.setTextSize(40);
        }
        if(board[1][0].getText() == turn && board[1][1].getText() == turn && board[1][2].getText() == turn){
            txt.setText("Winner is, "+turn);
            txt.setTextSize(40);
        }
        if(board[0][0].getText() == turn && board[1][0].getText() == turn && board[2][0].getText() == turn){
            txt.setText("Winner is, "+turn);
            txt.setTextSize(40);
        }
        if(board[0][1].getText() == turn && board[1][1].getText() == turn && board[2][1].getText() == turn){
            txt.setText("Winner is, "+turn);
            txt.setTextSize(40);
        }
        if(board[0][2].getText() == turn && board[1][2].getText() == turn && board[2][2].getText() == turn){
            txt.setText("Winner is, "+turn);
            txt.setTextSize(40);
        }
        if(board[0][0].getText() == turn && board[1][1].getText() == turn && board[2][2].getText() == turn){
            txt.setText("Winner is, "+turn);
            txt.setTextSize(40);
        }
        if(board[0][2].getText() == turn && board[1][1].getText() == turn && board[2][0].getText() == turn){
            txt.setText("Winner is, "+turn);
            txt.setTextSize(40);
        }

        /*
        * create sender thread here
        * */

        new SenderThread(socket,board).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    /*
    public void setBoard(Button[][] board) {
        this.board = board;
    }*/

}
