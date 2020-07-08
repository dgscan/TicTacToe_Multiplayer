package com.example.user.tictactoe_multiplayer;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;


import java.io.PrintWriter;
import java.net.Socket;

public class SenderThread extends AsyncTask<Void,Void,Void> {

    Socket socket;
    Button board[][];

    public SenderThread(Socket socket, Button board[][]){

        this.socket=socket;
        this.board=board;
    }


    @Override
    protected Void doInBackground(Void... voids) {

        try {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.print(board);
            out.flush();

        }catch (Exception ex){
            Log.d("sender error: ",ex.toString());
        }


        return null;
    }
}
