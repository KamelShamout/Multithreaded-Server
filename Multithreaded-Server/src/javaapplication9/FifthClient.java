package javaapplication9;

import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class FifthClient {
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
          @SuppressWarnings("UseOfObsoleteCollectionType")
          Hashtable<Integer,Integer>count= new Hashtable<>();
          int [][]matrix;
          while(!clientMessage.equals("bye")){
              
              matrix=(int[][]) inStream.readObject();
              for(int i=0; i< 10; i++)
              {
                  for(int j=0; j<10;j++)
                  {
                      if(count.containsKey(matrix[i][j])){
                          int temp= count.get(matrix[i][j]);
                          temp++;
                          count.replace(matrix[i][j], temp);
                      }
                      else count.put(matrix[i][j], 1);
                      
                  }
                  
              }
              Enumeration<Integer>keys=count.keys();
              while(keys.hasMoreElements()){
                 mtx+="the number : "+keys.nextElement()+" repeated :"+count.get(keys.nextElement())+"\n";
              }
              JOptionPane.showMessageDialog(x,mtx);
              clientMessage=JOptionPane.showInputDialog(x,"Enter bye to exit"); 
              outStream.writeUTF(clientMessage);
              outStream.flush();
          
      }
    outStream.close();
          inStream.close();
          socket.close(); }catch(IOException | ClassNotFoundException e){
    System.out.println(e);
  }
  }
}