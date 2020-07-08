package com.example.user.tictactoe_multiplayer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    public TextView roomStatus;
    public Socket socket;
    private String turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomStatus = (TextView) findViewById(R.id.textView3);
    }


    public void CreateRoom(View v){

        /*Getting Network information*/

        /*ConnectivityManager
         * Answers queries about the state of network connectivity
         * Notifies applications when network connectivity changes
         * */

        ConnectivityManager connMngr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        /*
         *NetworkInfo
         * Describes status of a network interface of a given type
         * Mobile or Wi-Fi
         * */
        NetworkInfo networkInfo = connMngr.getActiveNetworkInfo();

        if (networkInfo.isConnected() && networkInfo!=null){

            roomStatus.setText("Phone is connected using "+networkInfo.getTypeName()+".");

            /*AsyncTask - very short task, or no result returned to UI
             * Only a UI thread can create and start an AsyncTask
             * */
            new ServerThread(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }else{
            roomStatus.setText("No network available.");
        }

    }


    public void JoinRoom(View v){

        new ClientThread(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void StartGame(String t){

        turn=t;

        Toast.makeText(this,"starting game",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this,GameplayActivity.class);

        intent.putExtra("turn",turn);


        startActivity(intent);

    }

}
