package Server;

import java.io.IOException;
import java.net.*;

public class TugOfWarServer
{
    public static void main(String[] args)
    {
        boolean isServerOn = true;
        int portNumber = 4444;
        ServerSocket serverSocket = null;

        try
        {
            serverSocket = new ServerSocket(portNumber);
        }
        catch(IOException ioe)
        {
            System.out.println("Couldn't create server socekt!");
            return;
        }

        while(isServerOn)
        {
            Socket clientSocket = null;
            boolean isConnected = true;
            try
            {
                clientSocket = serverSocket.accept();
            }
            catch(IOException ioe)
            {
                System.out.println("Couldn't create client!");
                isConnected = false;
            }

            if(isConnected)
            {
                //Create Client Thread
            }
        }


        try 
        {
            serverSocket.close();
        } 
        catch (IOException ioe) 
        {
            System.out.println("Problem with closing server!");
            return;
        }

        return;
    }
}