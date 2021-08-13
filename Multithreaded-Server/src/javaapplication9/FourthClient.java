package javaapplication9;

import java.net.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class FourthClient {
  public static void main(String[] args) throws Exception {
  try{
String mtx="";
        JFrame x= new JFrame();  
    Socket socket = new Socket("localhost",9999);
                                             
          DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
 outStream.writeUTF("Fourth");
                            outStream.flush();

              ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
          String clientMessage="";
          int [][]transpose=new int[10][10];
          int [][]matrix;
          while(!clientMessage.equals("bye")){
              
              matrix=(int[][]) inStream.readObject();
              for(int i=0; i< 10; i++)
              {
                  for(int j=0; j<10;j++)
                  {
                      transpose[j][i]=matrix[i][j];
                  }
              }
              
              mtx+="the matrix after transpose\n";
             mtx+="************************************************\n";
              for(int i=0; i< 10; i++)
              {
                  for(int j=0; j<10;j++)
                  {
                      mtx+=String.valueOf( transpose[i][j])+"    ";
                      
                  }
mtx+="\n";              }
               JOptionPane.showMessageDialog(x,mtx);
             clientMessage=JOptionPane.showInputDialog(x,"Enter bye to exit"); 
              outStream.writeUTF(clientMessage);
              outStream.flush();
          
      }
     outStream.close();
          inStream.close();
          socket.close();}catch(IOException | ClassNotFoundException e){
    System.out.println(e);
  }
  }
}