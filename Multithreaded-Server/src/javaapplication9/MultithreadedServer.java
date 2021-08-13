package javaapplication9;
 
import java.net.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class MultithreadedServer {
  public static void main(String[] args) throws Exception {
      String mtx="* * * * * * * * * * * * \n *";
       int[][] matrix = new int[10][10];
        for(int i=0; i< 10; i++)
        {
            for(int j=0; j<10;j++)
            {
                matrix[i][j]=(int)Math.floor(Math.random()*(10-1+1)+1); 
                mtx+=String.valueOf(matrix[i][j])+" ";
            }
mtx+="\n";
        }
        mtx+="* * * * * * * * * * * * \n *";
    try{
        JFrame x= new JFrame();  
    JOptionPane.showMessageDialog(x,"The matrix is\n"+mtx);  
      ServerSocket server=new ServerSocket(9999);
      String counter="";
          JOptionPane.showMessageDialog(x,"Server Started ....");  

      while(true){
        Socket serverClient=server.accept();
                  JOptionPane.showMessageDialog(x,"connect");  

                  DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
                                       counter=  inStream.readUTF();
         JOptionPane.showMessageDialog(x," >> " + "The " + counter + "Client started!");
          Thread client = new ClientHandlerThread(serverClient,counter,matrix); 
        client.start();
      }
    }catch(IOException e){
      System.out.println(e);
    }
  }
}