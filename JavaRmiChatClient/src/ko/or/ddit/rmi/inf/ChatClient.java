package ko.or.ddit.rmi.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote{
	
	public void printMessage(String msg) throws RemoteException;
	
	
}
