package sellingBook;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import sellingBook.interfaceRMI.ILibraries;


@SuppressWarnings("deprecation")
public class MainServerLibraries {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		System.out.println("Lancement du server de librairies");
	    System.out.println("Working Directory = " + System.getProperty("user.dir"));
		System.setProperty("java.security.policy", "sec.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		LocateRegistry.createRegistry(1099);
		ILibraries lib = new Libraries();
		Naming.rebind("rmi://localhost/Libraries", lib);
	}

}
