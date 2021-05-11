package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientThread extends Thread
{
    private Socket client = null;
    private ServerBehaviour server = null;
    private Team team;

    private int clientDirection = 0;

    private PrintWriter pr;
    private InputStreamReader in;
    private BufferedReader bf;

    public ClientThread(Socket currentClient, ServerBehaviour server)
    {
        this.client = currentClient;
        this.server = server;

        if(server.GetAmountOfClients() % 2 == 0)
        {
            System.out.println("Client direction -1");
            team = Team.Left;
            this.clientDirection = -1;
            System.out.println(clientDirection);
        }
        else
        {
            System.out.println("Client direction 1");
            team = Team.Right;
            this.clientDirection = 1;
        }
    }

    public ClientThread(ClientThread clientThread)
    {
        this.client = clientThread.client;
        this.server = clientThread.server;
        this.team = clientThread.team;
        this.clientDirection = clientThread.clientDirection;
    }

    @Override
    public void run()
    {
        SetCommunicationWithClient();

        while(!server.IsGameStarted()) 
        {
            //Wait until game starts
        }

        while(server.IsGameStarted())
        {
            //get input
            ClientMoveRope();
            //give output to client
            ClientSendRopePosition();
        }
        
    }

    private void ClientSendRopePosition() 
    {
        pr.println(server.GetRopePositionTowardsZero());
        pr.flush();
    }

    private void ClientMoveRope()
    {
        try 
        {
            if( bf.readLine().equals("Pull"))
            {
                server.MoveRope(clientDirection);
            }
                
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    private void SetCommunicationWithClient()
    {
        try 
        {
            pr = new PrintWriter(client.getOutputStream());
            in = new InputStreamReader(client.getInputStream());
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        bf = new BufferedReader(in);
    }
}
