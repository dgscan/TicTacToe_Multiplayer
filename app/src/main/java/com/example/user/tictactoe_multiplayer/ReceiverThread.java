package com.example.user.tictactoe_multiplayer;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiverThread extends AsyncTask<Void,String,Void> {

    MainActivity mainActivity;
    Socket socket;

    public ReceiverThread(Socket s, MainActivity m){
        this.mainActivity=m;
        this.socket=s;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Log.d("preexecute","Socket preexecute");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            /*Reading data from Socket
             * We can use a StreamReader object to read data from a socket
             * */
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (socket.isConnected()){

                String str = reader.readLine();

                if(str==null)
                    break;

                publishProgress(str);
            }



            socket.close();

        }catch (Exception ex){
            Log.d("Receiver Thread Except.",ex.toString());
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {

    }

}
