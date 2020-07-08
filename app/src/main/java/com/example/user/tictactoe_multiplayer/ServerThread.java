package com.example.user.tictactoe_multiplayer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;



public class ServerThread extends AsyncTask<Void,Socket,Void> {

    private MainActivity mainActivity;
    private GameplayActivity gameplayActivity;




    public ServerThread (MainActivity m){
        this.mainActivity=m;
    }


    @Override
    protected Void doInBackground(Void... voids) {

        try {
            /*Server Socket
             *Using a Server Socket we can open a port and wait for incoming connections:
             */
            ServerSocket listener = new ServerSocket();

            listener.bind(new InetSocketAddress(InetAddress.getLocalHost(),9090));

            Socket socket = listener.accept();
            publishProgress(socket);

        }catch (Exception ex){
            Log.d("Server Exception", ex.toString());
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Socket... values) {

        /*
        * MainActivity'deki room status'ü güncelle.
        * MainActivity'de tanımlanan sockete buradaki values değerini ata
        * */
        mainActivity.roomStatus.append("\n"+values[0].getRemoteSocketAddress().toString()+" : Connected.");
        mainActivity.socket=values[0];

        new ReceiverThread(values[0],mainActivity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }




    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);
        mainActivity.StartGame("X");




    }
}
