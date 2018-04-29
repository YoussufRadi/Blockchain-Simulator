package Network;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Block extends Announcement implements Serializable {
    private String hash;
    private ArrayList<Transaction> transactions;
    private Block previousBlock;

    Block(Block previousBlock, int nonce, ArrayList<Transaction> transactions) throws UnsupportedEncodingException, NoSuchAlgorithmException, WrongHashException {
        this.previousBlock = previousBlock;
        this.transactions = transactions;
        verifyHash(nonce);
    }

    private String getHash() {
        return hash;
    }

    protected String getPrevHash() {
        if(previousBlock == null)
            return "0000000000000000000000000000000000000000";
        return previousBlock.getHash();
    }

    public Block getPreviousBlock() {
        return previousBlock;
    }

    public void setPreviousBlock(Block previousBlock) {
        this.previousBlock = previousBlock;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }


    public static String calcHash(int nonce, String prevHash, ArrayList<Transaction> transactions) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String blockInfo = prevHash + nonce + transactions.toString();
        String possibleHash = "";
        MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
        msdDigest.update(blockInfo.getBytes("UTF-8"));
        possibleHash = DatatypeConverter.printHexBinary(msdDigest.digest());
        return possibleHash;
    }

    public void verifyHash(int nonce) throws UnsupportedEncodingException, NoSuchAlgorithmException, WrongHashException {
        String verifiedHash = calcHash(nonce, getPrevHash(), transactions);
        String target = "00";
        if (verifiedHash.substring(0,2).equals(target))
            this.hash = verifiedHash;
        else
            throw new WrongHashException("Failed to create the block, the hash is invalid.");
    }

    @Override
    public String toString() {
        return "Block{" +
                "hash='" + getHash() + '\'' +
                ", prevHash='" + getPrevHash() + '\'' +
                ", transactions=" + getTransactions() +
                '}';
        
    }

}
