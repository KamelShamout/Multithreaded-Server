package javaapplication9;

import java.net.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class ThirdClient {
  public static void main(String[] args) throws Exception {
  try{
        JFrame x= new JFrame();  
    Socket socket = new Socket("localhost",9999);
                                             
          DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
 outStream.writeUTF("Third");
                            outStream.flush();

              ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
          String clientMessage="";int max=-999999;
          int [][]matrix;
          while(!clientMessage.equals("bye")){
              
              matrix=(int[][]) inStream.readObject();
              for(int i=0; i< 10; i++)
              {
                  for(int j=0; j<10;j++)
                  {
                      if(matrix[i][j]>max)max=matrix[i][j];
                  }
              }
              
              JOptionPane.showMessageDialog(x,"the maxmuim number in the matrix is= "+max);
              
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