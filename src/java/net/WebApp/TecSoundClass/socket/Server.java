/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.WebApp.TecSoundClass.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Server implements Runnable{
    List<Socket> sockets=new ArrayList();
    private static final String ServerIp = "101.132.71.111";
    private static final int ServerPort = 8088;
    @Override
    public void run() {
       try{
            System.out.println("服务端启动...");
            ServerSocket server = new ServerSocket(ServerPort);
            while(true){
                Socket client = server.accept();
                sockets.add(client);
                receiveThread re = new receiveThread(client);
                re.start();
            }
        }catch(Exception e){
            System.out.println("------S:Error 1-------");
            e.printStackTrace();
        }
 
    }
    //接受msg线程
    public class receiveThread extends Thread{
        Socket socket;
        private BufferedReader br;
        private PrintWriter pw;
        public String msg;
        public receiveThread(Socket s){
            socket = s;
        }
        public void run(){
            try{
                br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));//不转码在Android端会乱码
                msg = "sys:##:"+"欢迎"+socket.getInetAddress()+"进入聊天室，当前人数为"+sockets.size();
                sendMsg(InetAddress.getByName("1"));
                while((msg = br.readLine())!=null){
                    if(msg.equals("EndEndClosethesocket")){
                        close(socket.getInetAddress());
                    }else{
                        msg = socket.getInetAddress()+":##:"+msg;
                        sendMsg(socket.getInetAddress());
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        public void sendMsg(InetAddress ip){
            try{
                System.out.println(msg);
                for(int i = 0;i < sockets.size();i++){
                    if(!ip.equals(sockets.get(i).getInetAddress())){
                    pw = new PrintWriter(new OutputStreamWriter(sockets.get(i).getOutputStream(),"UTF-8"),true);
                    pw.println(msg);
                    pw.flush();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
 
    public void close(InetAddress ip){
        for(int i = 0;i < sockets.size();i++){
            if(sockets.get(i).getInetAddress()==ip){
                sockets.remove(i);
                msg ="sys:##:"+ip+"已经离开了聊天室";
                try{
                    sendMsg(InetAddress.getByName("1"));
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    }
    public static void main(String args[]){
        Thread thread = new Thread(new Server());
        thread.start();
    }
 
}