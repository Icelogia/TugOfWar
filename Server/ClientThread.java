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

    final String winMsg = "Win";
    final String loseMsg = "Lose";

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

        while(!server.IsGameInProgress()) 
        {
            //Wait until game starts
            System.out.println(clientDirection);
        }

        while(server.IsGameInProgress())
        {
            System.out.println(clientDirection + "1");
            //get input
            ClientInput();
            //give output to client
            ClientSendRopePosition();
        }

        //Send info about winner team
        ClientSendWinTeam();
        
    }

    private void ClientSendRopePosition() 
    {
        System.out.println(clientDirection + "4");
        pr.println(server.GetRopePositionTowardsZero());
        pr.flush();
    }

    private void ClientSendWinTeam()
    {
        Team winTeam = server.GetWinTeam();
        if(winTeam == team)
        {
            pr.println(winMsg);
        }
        else
        {
            pr.println(loseMsg);
        }
        
        pr.flush();
    }

    private void ClientInput()
    {
        try 
        {
            String clientMsg = bf.readLine();
            System.out.println(clientDirection + "2");
            if(clientMsg.equals("Pull"))
            {
                System.out.println(clientDirection + "4");
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
