package javaapplication9;

import java.net.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class FirstClient {
  public static void main(String[] args) throws Exception {
  try{
        JFrame x= new JFrame();  
    Socket socket = new Socket("localhost",9999);
                                             
          DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
 outStream.writeUTF("First");
                            outStream.flush();

              ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
          String clientMessage="";int summation=0;
          int [][]matrix;                                             

          while(!clientMessage.equals("bye")){

              matrix=(int[][]) inStream.readObject();
              for(int i=0; i< 10; i++)
              {
                  for(int j=0; j<10;j++)
                  {
                      summation+=matrix[i][j];
                  }
              }

               JOptionPane.showMessageDialog(x,"the summation of the matrix is= "+summation);

              clientMessage=JOptionPane.showInputDialog(x,"Enter bye to exit"); 
              outStream.writeUTF(clientMessage);
              outStream.flush();
          }
          outStream.close();
          inStream.close();
          socket.close();
      
  }catch(Exception e){
    System.out.println(e);
  }
  }}
