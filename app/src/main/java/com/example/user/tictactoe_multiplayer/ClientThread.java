package com.example.user.tictactoe_multiplayer;

import android.os.AsyncTask;
import android.util.Log;

import java.net.Socket;

public class ClientThread extends AsyncTask<Void,Socket,Void> {

    MainActivity mainActivity;
    GameplayActivity gameplayActivity;

    public ClientThread(MainActivity m){
        this.mainActivity=m;
    }


    @Override
    protected Void doInBackground(Void... voids) {

        try {
            /*Client Socket
             * Using a Client Socket we can connect to server on a specific  port :
             * */
            Socket socket = new Socket("10.0.2.2",7000);
            publishProgress(socket);

        }catch (Exception e){
            Log.d("Client Exception",e.toString());
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Socket... values) {
        mainActivity.roomStatus.append("Connected to: "+values[0].getRemoteSocketAddress().toString());
        mainActivity.socket=values[0];

        new ReceiverThread(values[0],mainActivity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //new ReceiverTurnThread creation??


        mainActivity.StartGame("O");

    }
}
