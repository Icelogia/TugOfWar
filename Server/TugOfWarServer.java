package Server;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;

public class TugOfWarServer
{
    public static void main(String[] args)
    {
        boolean isServerOn = true;
        int portNumber = 4444;
        ServerSocket serverSocket = null;

        ServerBehaviour server = new ServerBehaviour();
        LinkedList <ClientThread> clientThreads = new  LinkedList <ClientThread>();

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
                    System.out.println("Client connected!");
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
    
                    clientThreads.add(new ClientThread(newClient)) ;
                    server.IncreaseAmountOfClientsBy(1, newClient.GetTeam());

                    clientThreads.get(currnetAmountOfClients).SetCommunicationWithClient();
                    clientThreads.get(currnetAmountOfClients).start();

                    for(int i = 0; i <= currnetAmountOfClients; i++)
                    {
                        System.out.println("Amount of clients " + currnetAmountOfClients);
                        clientThreads.get(i).ClientUpdateTeamsNumber();
                    }
                }

                for(int i = 0; i <= server.GetAmountOfClients(); i++)
                {
                    if(!clientThreads.get(i).isAlive())
                    {
                        clientThreads.get(i).stop();
                        clientThreads.remove(i);
                    }
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