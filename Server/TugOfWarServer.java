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


        ServerBehaviour server = new ServerBehaviour();
        ClientThread[] clientThreads = new ClientThread[server.maxPlayers];

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

            if(!server.isLobbyFull())
            {
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
                    ClientThread newClient = new ClientThread(clientSocket, server);
                    int currnetAmountOfClients = server.GetAmountOfClients();
    
                    clientThreads[currnetAmountOfClients] = new ClientThread(newClient);
                    clientThreads[currnetAmountOfClients].start();
    
                    server.IncreaseAmountOfClientsBy(1);
                }
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