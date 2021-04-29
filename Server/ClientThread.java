package Server;

import java.net.Socket;

public class ClientThread extends Thread
{
    private Socket client = null;
    private ServerThread server = null;

    public ClientThread(Socket currentClient, ServerThread server)
    {
        this.client = currentClient;
        this.server = server;
    }

    @Override
    public void run()
    {
        //get input
        //send and synchronized on server thread
        //give output to client
    }
}
