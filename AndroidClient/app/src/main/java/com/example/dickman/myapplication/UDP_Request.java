package com.example.dickman.myapplication;

import java.io.InputStream; //輸入串流
import java.io.OutputStream; //輸出串流
import java.io.IOException; //例外功能
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.MulticastSocket;
import java.net.Socket; //Socket網路功能
import java.net.UnknownHostException;

public class UDP_Request {
    private String Host;
    private int SentPort;
    private int RecievePort;
    private MulticastSocket socket;
    private DatagramPacket rceivePacket;

    public UDP_Request(String Host, int SentPort, int RecievePort, int timeout, int bufferSize) throws IOException {
        byte receiveBuffer[] = new byte[bufferSize];
        this.Host = Host;
        this.SentPort = SentPort;
        this.RecievePort = RecievePort;
        socket = new MulticastSocket(SentPort);
        socket.setBroadcast(true); //open Broadcast
        socket.setSoTimeout(timeout);
        rceivePacket = new DatagramPacket(receiveBuffer, bufferSize, InetAddress.getByName(Host), RecievePort); //https://developer.android.com/reference/java/net/DatagramPacket.html
    }

    public void send (final String input) throws UnknownHostException {

        byte data[] = input.getBytes();
        final DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(Host), this.SentPort);
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    error(e);
                }
            }
        }).start();
    }

    public void error(IOException e) {
        // TODO
    }

    public String receive() throws IOException {
        socket.receive(rceivePacket);
        return new String(rceivePacket.getData(), 0, rceivePacket.getLength());
    }
}

