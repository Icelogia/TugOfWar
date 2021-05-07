package Server;

public class ServerBehaviour
{
    public final int maxPlayers = 4;
    private int currnetAmountOfClients = 0;
    private boolean isGameStarted = false;

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

}
