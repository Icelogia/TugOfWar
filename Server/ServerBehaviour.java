package Server;

public class ServerBehaviour
{
    public final int maxPlayers = 1;
    private int currnetAmountOfClients = 0;
    private boolean isGameInProgress = false;

    final private int amountOfPixelsToMove = 5;
    private int ropePosition = 0;
    private int ropeWinLength = 100;

    private Team winTeam;

    synchronized public boolean IsGameInProgress()
    {
        return this.isGameInProgress;
    }

    public int GetAmountOfClients()
    {
        return currnetAmountOfClients;
    }

    public void IncreaseAmountOfClientsBy(int value)
    {
        this.currnetAmountOfClients += value;

        if(this.currnetAmountOfClients == this.maxPlayers)
        {
            winTeam = null;
            ropePosition = 0;

            isGameInProgress = true;
        }
    }

    public boolean isLobbyFull()
    {
        return this.currnetAmountOfClients == this.maxPlayers;
    }

    synchronized public void MoveRope(int direction)
    {
        
        ropePosition += direction * amountOfPixelsToMove;
        System.out.println("Rope position: " + ropePosition);
        
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

    public int GetRopePositionTowardsZero()
    {
        return ropePosition;
    }

    public Team GetWinTeam()
    {
        return winTeam;
    }
}
