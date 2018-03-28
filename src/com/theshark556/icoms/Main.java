package com.theshark556.icoms;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by thesh on 28-Mar-18.
 */
public class Main {
    //ASSESSING VARS
    static ServerSocket SSS = null;
    static Socket socket = null;
    static InputStream in = null;
    static OutputStream out = null;
    static ArrayList<String> ActionHistory = new ArrayList<>();

    public static void main(String[] arg){



        //Checks for args
        if(arg.length>0) {
            System.out.print("Args are: " + System.lineSeparator());
            //GETS ARGS
            for (int i = 0; i < arg.length; i++) {
                //DEBUG: List them out
                System.out.print(i + ": " + arg[i] + System.lineSeparator());


                //Checks for correct arguments and applies
                if (arg[i].toLowerCase().contains("-p")){
                    try {
                        SSS = new ServerSocket(Integer.parseInt(arg[i + 1]));

                    } catch (IOException e) {
                        System.out.println("Can't setup server on this port number.");
                    }
                    ActionHistory.add("SSSDone");

                }
            }
            System.out.print(System.lineSeparator()+ SSS.toString() + System.lineSeparator());
            if (ActionHistory.contains("SSSDone")){
                initSocket();
            }
            if (ActionHistory.contains("SocketDone")){
                initIn();
                initOut();
            }
        }System.exit(1);


    }

     static void initSocket() {
        try {
            socket = SSS.accept();
            ActionHistory.add("SocketDone");
        } catch (IOException e) {
            System.out.println("Can't accept client connection. ");
            ActionHistory.remove("SocketDone");

        }

    }
    static void initIn(){
        try {
            in = socket.getInputStream();
            ActionHistory.add("SocketInDone");
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
            ActionHistory.remove("SocketInDone");
        }
    }
    static void initOut(){
        try {
            out = socket.getOutputStream();
            ActionHistory.add("SocketOutDone");
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
            ActionHistory.remove("SocketOutDone");
        }
    }
}
