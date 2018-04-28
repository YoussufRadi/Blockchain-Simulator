package Network;

import java.io.*;
import java.security.*;
import java.util.ArrayList;
import java.util.Random;


public class User implements Serializable{

	ArrayList<User> listOfpeers;
	ArrayList<Transaction> transactionCache;
	PublicKey publicKey;
	private PrivateKey privateKey;
	String name;
	BlockChain blockChain;
	
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
		this.transactionCache = new ArrayList<Transaction>();
		this.blockChain = new BlockChain();
		
	}
	public void mineBlock(){
		if(transactionCache.size()>=5){
			int transactionCacheSize = transactionCache.size();
			for (int i = 0; i < transactionCacheSize; i++){
			//	addTransactionToBlock(transactionCache.remove(0));
			}
		}
	}
	
//	public void addTransactionToBlock(Transaction transaction){
//		ArrayList<Transaction> transactions = block.getTransactions();
//		transactions.add(transaction);
//		block.setTransactions(transactions);
//	}
	
	
	public void generateNonce(){
		Random rand = new Random();
		String nonce = "";
		for (int i = 0; i < 10; i++) {
			int digit = rand.nextInt(10);
			nonce+=digit;
		}
		verifyHash(Integer.parseInt(nonce));
	}
	
	public void verifyHash(int nonce){
		//String hash = block.calcHash(nonce, block.getPrevHash(), )
		
	}
	public void addPeer(User user){
		listOfpeers.add(user);
	}
	
	public void announce(Announcement message){
		if(message instanceof Block)
			blockChain.addBlockToChain((Block) message);
		if(transactionCache.contains(message))
			return;
		System.out.println(this.name + " received announcement");
		Random rand = new Random();
		int numberOfRecievers = rand.nextInt(listOfpeers.size())+1;
		transactionCache.add((Transaction) message);
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
	
	public String getName() {
		return name;
	}
	public ArrayList<User> getListOfpeers() {
		return listOfpeers;
	}

	public ArrayList<Transaction> getTransactionCache() {
		return transactionCache;
	}

	public void setTransactionCache(ArrayList<Transaction> transactionCache) {
		this.transactionCache = transactionCache;
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
