package com.example.myapplication.model;
import android.widget.Toast;

import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.widget.Toast;
public class Model{

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private ExecutorService pool;
    public Model(){
        pool = Executors.newSingleThreadExecutor();
    }
    public class RunnableStartConnection implements Runnable{
        String ip;
        int port;
        Socket clientSocket;
        public RunnableStartConnection(String ip, int port ,Socket clientSocket){
            this.ip = ip;
            this.port = port;
            this.clientSocket = clientSocket;
        }
        public void run(){
            try {

                this.clientSocket = new Socket(this.ip, this.port);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (UnknownHostException e) {


            } catch(IOException i){

            }
        }
    }
    public class RunnableSendData implements Runnable{
        String data;
        public RunnableSendData(String data){
           this.data = data;
        }
        public void run(){
            try {
                out.print(data);
                out.flush();
            }catch(Exception i){}
        }
    }

    public void startConnection(String ip, int port) {
        int a = 2;
        pool.execute(new RunnableStartConnection(ip , port, this.clientSocket));
    }


    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        }
        catch(IOException i){}
    }

    public void changeAileron(String v){
        pool.execute(new RunnableSendData("set /controls/flight/aileron "+v+"\r\n"));
    }

    public void changeElevator(String v){

            pool.execute(new RunnableSendData("set /controls/flight/elevator " + v + "\r\n"));

    }
    public void changeRudder(String v){
        pool.execute(new RunnableSendData("set /controls/flight/rudder "+v+"\r\n"));
    }
    public void changeThrottle(String v){
        pool.execute(new RunnableSendData("set /controls/engines/current-engine/throttle "+v+"\r\n"));
    }
    public void printanyway(String v){
        pool.execute(new RunnableSendData(v+"\r\n"));
    }
}
