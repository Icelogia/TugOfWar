package Server;

public class ServerBehaviour
{
    public final int maxPlayers = 4;
    private int currnetAmountOfClients = 0;
    private boolean isGameStarted = true;

    final private int amountOfPixelsToMove = 5;
    private int ropePosition = 0;
    private int ropeWinLength = 100;

    public boolean IsGameStarted()
    {
        return this.isGameStarted;
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
            isGameStarted = true;
        }
    }

    public boolean isLobbyFull()
    {
        return this.currnetAmountOfClients == this.maxPlayers;
    }

    synchronized public void MoveRope(int direction)
    {
        System.out.println("Rope position: " + ropePosition);
        ropePosition += direction * amountOfPixelsToMove;
        System.out.println("Rope position 2: " + ropePosition);
    }

    public int GetRopePositionTowardsZero()
    {
        return ropePosition;
    }
}
