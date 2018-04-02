package Network;

import java.util.ArrayList;
import java.util.Random;

/*
 * This class keeps track of all the users present in the network
 */
public class Network {
	ArrayList<User> listOfUsers;
	
	public Network() {
		listOfUsers = new ArrayList<User>();
	}
/*
 * Add a new user to the network
 * Make the new user a peer to a random number of users
 * Make these users also peers to the new user 
 * Add the user to the listOfUsers
 */
	public void addNewUser(User u){
		if(listOfUsers.size() == 0) {
			listOfUsers.add(u);
			return;
		}
		Random rand = new Random();
		int  numberOfPeers = rand.nextInt(listOfUsers.size()) + 1;
		ArrayList<Integer> peersIndex = new ArrayList<Integer>();
		for(int i = 0; i<numberOfPeers; i++){
			int indexOfPeer = rand.nextInt(listOfUsers.size());
			if(peersIndex.contains(indexOfPeer)){
				i--;
			}
			else{
				peersIndex.add(indexOfPeer);
				User peer = listOfUsers.get(indexOfPeer);
				u.addPeer(peer);
				peer.addPeer(u);	
			}
		}
		listOfUsers.add(u);
	}
}
