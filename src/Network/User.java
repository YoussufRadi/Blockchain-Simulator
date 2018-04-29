package Network;

import java.io.*;
import java.security.*;
import java.util.ArrayList;
import java.util.Random;


public class User implements Serializable{

	private ArrayList<User> listOfpeers;
    private ArrayList<Transaction> transactionCache;
    private PublicKey publicKey;
	private PrivateKey privateKey;
    private String name;
    private BlockChain blockChain;
	
    User(String name) throws NoSuchAlgorithmException, NoSuchProviderException {
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

	private void mineBlock() throws InvalidKeyException, Exception{
		if(transactionCache.size()>=5){
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			for (int i = 0; i < 5; i++){
				transactions.add(transactionCache.remove(0));
			}
			Announcement block = addTransactionsToBlock(transactions);
			byte[] signature = sign(block);
			block.setSignature(signature);
			announce(block);
			
		}
	}

	private int generateNonce(){
		Random rand = new Random();
		String nonce = "";
		for (int i = 0; i < 9; i++) {
			int digit = rand.nextInt(10);
			nonce+=digit;
		}
		return Integer.parseInt(nonce);
	}

	private Block addTransactionsToBlock(ArrayList<Transaction> transactions){
		Block block = null;
		boolean flag = true;
		while(flag){
			try {
				block = new Block(blockChain.getLastBlock(), generateNonce(), transactions);
				flag = false;

			} catch (WrongHashException e){
			    
            }
            catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			    e.printStackTrace();
			}
		}
		return block;
	}

	public void addPeer(User user){
		listOfpeers.add(user);
	}
	
	private void announce(Announcement message) throws Exception{
		if(message instanceof Block)
			if(blockChain.checkBlockInBlockChain((Block) message))
				return;
			else{
                blockChain.addBlockToChain((Block) message);
                transactionCache.removeAll(((Block) message).getTransactions());
            }
		else
			if(transactionCache.contains((Transaction) message) || blockChain.checkTransactionInChain((Transaction) message))
				return;
			else {
				transactionCache.add((Transaction) message);
				mineBlock();
			}
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
		Announcement transaction = new Transaction(amount, this, receiver);
		byte[] signature = sign(transaction);
		transaction.setSignature(signature);
		System.out.println(this.name + " creates announcement ");
		announce(transaction);
		
	}
	

	private byte[] sign(Announcement message) throws InvalidKeyException, Exception{
		Signature rsa = Signature.getInstance("DSA");
		rsa.initSign(this.privateKey);
		rsa.update(serialize(message));
		return rsa.sign();
	}

	private static byte[] serialize(Announcement message) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(message);
	    os.flush();
	    return out.toByteArray();
	}
	
	public String getName() {
		return name;
	}

	public ArrayList<User> getListOfpeers() {
		return listOfpeers;
	}
	public PublicKey getPublicKey() {
		return publicKey;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}	
	
}
