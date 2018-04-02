package Network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;
import java.security.Signature;


public class User {

	ArrayList<User> listOfpeers;
	ArrayList<Announcement> announcements;
	PublicKey id;
	private PrivateKey privateKey;
	
	public User( PublicKey id, PrivateKey privateKey) {
		super();
		this.listOfpeers = new ArrayList<User>();
		announcements = new ArrayList<Announcement>();
		this.id = id;
		this.privateKey = privateKey;
	}
	
	public void addPeer(User user){
		listOfpeers.add(user);
	}
	
	public void announce(Announcement message){
		Random rand = new Random();
		int numberOfRecievers = rand.nextInt(listOfpeers.size())+1;
		announcements.add(message);
		for (int i = 0; i < numberOfRecievers; i++) {
			
		}
		
		
	}
	public void createTransaction(int amount,User reciever) throws InvalidKeyException, Exception{
		Transaction t = new Transaction(amount, this, reciever);
		//t.getId();
		byte[] signature = sign(t);
		Announcement message = new Announcement(t,signature);
		announce(message);
		
		
	}
	
	public byte[] sign(Transaction transaction) throws InvalidKeyException, Exception{
		Signature rsa = Signature.getInstance("SHA1withRSA"); 
		rsa.initSign(this.privateKey);
		rsa.update(serialize(transaction));
		return rsa.sign();
	}
	public static byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	
	public ArrayList<User> getListOfpeers() {
		return listOfpeers;
	}
	
	
	public void setListOfpeers(ArrayList<User> listOfpeers) {
		this.listOfpeers = listOfpeers;
	}
	public PublicKey getId() {
		return id;
	}
	public void setId(PublicKey id) {
		this.id = id;
	}
	
}
