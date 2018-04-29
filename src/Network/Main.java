package Network;

import java.security.InvalidKeyException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) throws InvalidKeyException, Exception {
		Network n = new Network();
		User yara = new User("yara");
		User safa = new User("safa");
		User narihan = new User("narihan");
		User youssef = new User("youssef");
		
		n.addNewUser(yara);
		n.addNewUser(safa);
		n.addNewUser(narihan);
		n.addNewUser(youssef);
		
		System.out.println("USERS AND THEIR PEERS");
		ArrayList<User> safaPeers = safa.getListOfpeers();
		ArrayList<User> yaraPeers = yara.getListOfpeers();
		ArrayList<User> narihanPeers = narihan.getListOfpeers();
		ArrayList<User> youssefPeers = youssef.getListOfpeers();
		
		
		System.out.println();
		System.out.println("Safa's peers " + safaPeers.toString());
		System.out.println("Yara's peers " + yaraPeers.toString());
		System.out.println("Narihan's peers " + narihanPeers.toString());
		System.out.println("Youssef's peers " + youssefPeers.toString());
		
		System.out.println();
		System.out.println("ANNOUNCEMENTS");
		System.out.println();

		safa.createTransaction(1, safaPeers.get(0));
		safa.createTransaction(10, safaPeers.get(0));
		safa.createTransaction(11, safaPeers.get(0));
		safa.createTransaction(12, safaPeers.get(0));
		safa.createTransaction(15, safaPeers.get(0));
		safa.createTransaction(1, safaPeers.get(0));
		yara.createTransaction(50, yaraPeers.get(0));
		yara.createTransaction(12, yaraPeers.get(0));
		yara.createTransaction(33, yaraPeers.get(0));
		yara.createTransaction(45, yaraPeers.get(0));
		yara.createTransaction(44, yaraPeers.get(0));
		youssef.createTransaction(41, yaraPeers.get(0));
		youssef.createTransaction(412, yaraPeers.get(0));
		youssef.createTransaction(412, yaraPeers.get(0));
		youssef.createTransaction(1412, yaraPeers.get(0));
		youssef.createTransaction(4412, yaraPeers.get(0));
		youssef.createTransaction(5412, yaraPeers.get(0));
		narihan.createTransaction(0, yaraPeers.get(0));
		narihan.createTransaction(-1, yaraPeers.get(0));
		narihan.createTransaction(0, yaraPeers.get(0));
		narihan.createTransaction(3, yaraPeers.get(0));
		narihan.createTransaction(2, yaraPeers.get(0));
		narihan.createTransaction(0, yaraPeers.get(0));

		System.out.println();
		System.out.println("USER'S RECEIVED Announcements");
		
		System.out.println();
//		System.out.println("Yara's Announcements " + yara.getAnnouncements().toString());
//		System.out.println("Safa's Announcements " + safa.getAnnouncements().toString());
//		System.out.println("Narihan's Announcements " + narihan.getAnnouncements().toString());
//		System.out.println("Youssef's Announcements " + youssef.getAnnouncements().toString());
//		
	}
}
