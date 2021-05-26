package Server;

import java.util.LinkedList;

public class ServerBehaviour
{
    private int amountOfLeftTeam;
    private int amountOfRightTeam;
    public final int maxPlayers = 1;
    private int currnetAmountOfClients = 0;
    private boolean isGameInProgress = false;

    final private int amountOfPixelsToMove = 5;
    private int ropePosition = 0;
    private int ropeWinLength = 100;
    LinkedList <ClientThread> clientThreads;

    private Team winTeam;

    synchronized public boolean IsGameInProgress()
    {
        return this.isGameInProgress;
    }

    public int GetAmountOfClients()
    {
        return currnetAmountOfClients;
    }

    public int GetAmountOfLeftClients()
    {
        return amountOfLeftTeam;
    }

    public int GetAmountOfRightClients()
    {
        return amountOfRightTeam;
    }

    public void IncreaseAmountOfClientsBy(int value, Team team)
    {
        this.currnetAmountOfClients += value;

        if(this.currnetAmountOfClients == this.maxPlayers)
        {
            winTeam = null;
            ropePosition = 0;

            isGameInProgress = true;
        }

        IncreaseAmountInTeams(value, team);
    }

    private void IncreaseAmountInTeams(int value, Team team)
    {
        if(team == Team.Left)
        {
            this.amountOfLeftTeam += value;
        }
        else if(team == Team.Right)
        {
            this.amountOfRightTeam += value;
        }
    }

    public boolean isLobbyFull()
    {
        return this.currnetAmountOfClients == this.maxPlayers;
    }

    public void SetClientsThread(LinkedList<ClientThread> clients)  
    {
        clientThreads = clients;
    }

    synchronized public void MoveRope(int direction)
    {
        
        ropePosition += direction * amountOfPixelsToMove;
        System.out.println("Rope position: " + ropePosition);
        
        for (ClientThread client : clientThreads) 
        {
            client.ClientSendRopePosition(ropePosition);
        }
 
        
        if(Math.abs(ropePosition) >= ropeWinLength)
        {
            GameOver();
        }
        
    }

    private void GameOver() 
    {
        isGameInProgress = false;

        if(ropePosition < 0)
        {
            winTeam = Team.Left;
            System.out.println("Team left win");
        }
        else if(ropePosition > 0)
        {
            winTeam = Team.Right;
            System.out.println("Team right win");
        }
    }

    public Team GetWinTeam()
    {
        return winTeam;
    }
}
