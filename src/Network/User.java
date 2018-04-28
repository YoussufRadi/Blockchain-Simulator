package Network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.*;
import java.util.ArrayList;
import java.util.Random;


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
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG",
				"SUN");
        keyGen.initialize(1024, random);
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
		Announcement message = new Transaction(amount, this, receiver);
		byte[] signature = sign(message);
		message.setSignature(signature);
		System.out.println(this.name + " creates announcement ");
		announce(message);
		
	}
	
	public ArrayList<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(ArrayList<Announcement> announcements) {
		this.announcements = announcements;
	}

	public byte[] sign(Announcement transaction) throws InvalidKeyException, Exception{
		Signature rsa = Signature.getInstance("DSA");
		rsa.initSign(this.privateKey);
		rsa.update(serialize(transaction));
		return rsa.sign();
	}
	public static byte[] serialize(Announcement transaction) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(transaction);
	    os.flush();
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
