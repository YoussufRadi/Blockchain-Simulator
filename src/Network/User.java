package Network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;
import java.security.Signature;

import javax.crypto.KeyGenerator;


public class User implements Serializable{

	ArrayList<User> listOfpeers;
	ArrayList<Announcement> announcements;
	PublicKey publicKey;
	private PrivateKey privateKey;
	String name;
	
	public User(String name) throws NoSuchAlgorithmException, NoSuchProviderException {
		super();
		this.name = name;
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
		KeyPair pair = keyGen.generateKeyPair();
		this.publicKey = pair.getPublic();
		this.privateKey = pair.getPrivate();
		this.listOfpeers = new ArrayList<User>();
		announcements = new ArrayList<Announcement>();
		
	}
	
	public String getName() {
		return name;
	}

	public void addPeer(User user){
		listOfpeers.add(user);
	}
	
	public void announce(Announcement message){
		if(announcements.contains(message))
			return;
		System.out.println(this.name + " received announcement");
		Random rand = new Random();
		int numberOfRecievers = rand.nextInt(listOfpeers.size())+1;
		announcements.add(message);
		ArrayList<Integer> receiversIndex = new ArrayList<Integer>();
		for(int i = 0; i<numberOfRecievers; i++){
			int indexOfReceiver = rand.nextInt(listOfpeers.size());
			if(receiversIndex.contains(indexOfReceiver)){
				i--;
			}
			else{
				receiversIndex.add(indexOfReceiver);
				User receiver = listOfpeers.get(indexOfReceiver);
				System.out.println(this.name + " sends" + " to " + receiver.name);
				receiver.announce(message);

				
			}
		}
		
		
	}
	public void createTransaction(int amount,User receiver) throws InvalidKeyException, Exception{
		Transaction t = new Transaction(amount, this, receiver);
		byte[] signature = sign(t);
		Announcement message = new Announcement(t,signature);
		System.out.println(this.name + " creates announcement ");
		announce(message);
		
	}
	
	public ArrayList<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(ArrayList<Announcement> announcements) {
		this.announcements = announcements;
	}

	public byte[] sign(Transaction transaction) throws InvalidKeyException, Exception{
		Signature rsa = Signature.getInstance("DSA"); 
		rsa.initSign(this.privateKey);
		rsa.update(serialize(transaction));
		return rsa.sign();
	}
	public static byte[] serialize(Transaction transaction) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(transaction);
	    return out.toByteArray();
	}
	
	public ArrayList<User> getListOfpeers() {
		return listOfpeers;
	}
	
	
	public void setListOfpeers(ArrayList<User> listOfpeers) {
		this.listOfpeers = listOfpeers;
	}
	public PublicKey getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
	
	
}
