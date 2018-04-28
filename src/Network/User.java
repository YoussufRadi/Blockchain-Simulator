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
	public void mineBlock() throws InvalidKeyException, Exception{
		if(transactionCache.size()>=5){
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			for (int i = 0; i < 5; i++){
				transactions.add(transactionCache.remove(0));
			}
			Block block = addTransactionsToBlock(transactions);
			byte[] signature = sign(block);
			
		}
	}
	public int generateNonce(){
		Random rand = new Random();
		String nonce = "";
		for (int i = 0; i < 10; i++) {
			int digit = rand.nextInt(10);
			nonce+=digit;
		}
		return Integer.parseInt(nonce);
	}

	public Block addTransactionsToBlock(ArrayList<Transaction> transactions){
		Block block = null;
		boolean flag = true;
		while(flag){
			try {
				block = new Block(blockChain.getHashOfLastBlock(), generateNonce(), transactions);
				flag = false;
			} catch (UnsupportedEncodingException | NoSuchAlgorithmException
					| WrongHashException e) {
			}
		}
		return block;
	}

	public void addPeer(User user){
		listOfpeers.add(user);
	}
	
	public void announce(Announcement message){
		if(message instanceof Block)
			if(blockChain.checkBlockInBlockChain((Block) message))
				return;
			else
				blockChain.addBlockToChain((Block) message);
		else
			if(transactionCache.contains((Transaction) message) || blockChain.checkTransactionInChain((Transaction) message))
				return;
			else
				transactionCache.add((Transaction) message);
		System.out.println(this.name + " received announcement");
		Random rand = new Random();
		int numberOfRecievers = rand.nextInt(listOfpeers.size())+1;
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
