package Client.controllers;

public interface OnGameUpdateListiner 
{
    public void FinishGame(String winLoseInfo);
    public void UpdateAmountOfLeftPlayers(int value);
    public void UpdateAmountOfRightPlayers(int value);
}
