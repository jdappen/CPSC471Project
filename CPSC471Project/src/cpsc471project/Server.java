package cpsc471project;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;

public class Server {
    
    public static void main(String[] args)
    {
        int portNum = 987;
        String input;
        String output;
        String command[] = new String[2];
        boolean success;
        
                
        
        try {
            ServerSocket server = new ServerSocket(portNum);
            
            Socket clientSocket = server.accept();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 
            

            
            //Sends connection message to the Client
            out.println("connected to server");
            out.println("ftp>");
            
            //reads in command form the client
            input = in.readLine();
            
            //splits the command into its parts
            command = input.split(" ");
            
            
            System.out.println(input);
            
            
//            //IF block that calls the right method based on what command is inputed
            if("ls".equals(command[0]))
            {
                success = LS();
            }
            else if("get".equals(command[0]))
            {
                success = get(command[1], clientSocket);
            }
            else if("put".equals(command[0]))
            {
                success = put(command[1]);
            }
       
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    //The list commands method
    public static boolean LS()
    {
        boolean success = false;
        System.out.println("LS!!!");
        return success;
    }

    //The get command method
    public static boolean get(String Message, Socket client) throws FileNotFoundException, IOException
    {
        boolean success = false;
        int count = 0;
        
        File myFile = new File(Message);
        long length = myFile.length();
        byte data[] = new byte[16*1024];
        
        InputStream in = new FileInputStream(myFile);
        OutputStream out = client.getOutputStream();
   
        while((count = in.read(data)) > 0)
        {
         out.write(data, 0, count);
        }
        out.close();
        in.close();
                
        
        
        
//        if(myFile.exists())
//        {
//            System.out.println("found file");
//            byte[] byteArray = new byte[(int)myFile.length()];
//            FileInputStream fileStream = new FileInputStream(myFile);
//            BufferedInputStream bufStream = new BufferedInputStream(fileStream);
//            bufStream.read(byteArray, 0,byteArray.length);
//            OutputStream outStream = client.getOutputStream();
//            outStream.write(byteArray, 0, byteArray.length);
//            outStream.flush();
//            success = true;
//        }
        
        System.out.println("get!!!");
        System.out.println(success);
        
        return success;
    }

    //the put command method
    public static boolean put(String Message)
    {
        boolean success = false;
    
        System.out.println("put!!!");
        return success;
    }



}


