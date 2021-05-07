package Server;

import java.net.Socket;

public class ClientThread extends Thread
{
    private Socket client = null;
    private ServerBehaviour server = null;
    private Team team;

    public ClientThread(Socket currentClient, ServerBehaviour server)
    {
        this.client = currentClient;
        this.server = server;

        if(server.GetAmountOfClients() % 2 == 0)
        {
            team = Team.Left;
        }
        else
        {
            team = Team.Right;
        }
    }

    public ClientThread(ClientThread clientThread)
    {
        this.client = clientThread.client;
        this.server = clientThread.server;
    }

    @Override
    public void run()
    {
        while(!server.IsGameStarted()) 
        {
            //Wait until game starts
        }

        while(server.IsGameStarted())
        {
            //get input
            if(team == Team.Left)
            {

            }
            else if(team == Team.Right)
            {

            }
            //send and synchronized on server thread
            //give output to client
        }
        
    }
}
