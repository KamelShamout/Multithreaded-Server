package javaapplication9;
 
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

 
class ClientHandlerThread extends Thread {
  Socket serverClient;
  String clientNo;
  int[][] matrix;
  ClientHandlerThread(Socket inSocket,String counter,int[][]matrix){
    serverClient = inSocket;
    clientNo=counter;
    this.matrix=matrix;
  }
  @Override
  public void run(){
     
    try{
                JFrame x= new JFrame();  
   

          DataInputStream inStream = new DataInputStream(serverClient.getInputStream()) ;
          ObjectOutputStream  outStream = new ObjectOutputStream(serverClient.getOutputStream());
              String clientMessage="";
              while(!clientMessage.equals("bye")){
                  outStream.writeObject(matrix);
                  outStream.flush();                 
                  clientMessage=inStream.readUTF();

                  JOptionPane.showMessageDialog(x,"From Client-" +clientNo+ ":the message is  :"+clientMessage);
              }   
      outStream.close();
      serverClient.close();
    }catch(IOException ex){
      System.out.println(ex);
    }finally{
      System.out.println("Client -" + clientNo + " exit!! ");
    }
  }
}