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
import java.io.FileOutputStream;
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
                LS();
            }
            else if("get".equals(command[0]))
            {
                get(command[1], clientSocket);
            }
            else if("put".equals(command[0]))
            {
                put(command[1], clientSocket);
            }
       
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    //The list commands method
    public static void LS()
    {
        boolean success = false;
        System.out.println("LS!!!");
    }

    //The get command method
    public static void get(String Message, Socket client) throws FileNotFoundException, IOException
    {
        int count = 0;
        
        File myFile = new File(Message);
        long length = myFile.length();
        byte data[] = new byte[16*1024];
        
        InputStream in = new FileInputStream(myFile);
        OutputStream out = client.getOutputStream();
   
        if(myFile.exists())
        {
            while((count = in.read(data)) > 0)
            {
                out.write(data, 0, count);
            }
            System.out.println("SUCCESS");
        }
        else
        {
            System.out.println("FAILURE");
        }
            
        out.close();
        in.close();
        
        System.out.println("get!!!");
        
    }

    //the put command method
    public static void put(String Message, Socket client) throws IOException
    {
        boolean success = false;
        int count;
        InputStream in =client.getInputStream();
       
        OutputStream out = new FileOutputStream(Message);
       
        byte data[] = new byte[16*1024];
       
        while((count = in.read(data)) > 0)
        {
            out.write(data, 0, count);
        }
        System.out.println("put!!!");
    }



}


