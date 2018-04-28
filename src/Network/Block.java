package Network;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;

public class Block extends Announcement {
    private String hash;
    private String prevHash;
    private ArrayList<Transaction> transactions;

    public Block(String hash, String prevHash, int nonce, ArrayList<Transaction> transactions) throws UnsupportedEncodingException, NoSuchAlgorithmException, WrongHashException {
        this.prevHash = prevHash;
        this.transactions = transactions;
        verifyHash(nonce,hash);
    }

    public String getHash() {
        return hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }


    public static String calcHash(int nonce, String prevHash, ArrayList<Transaction> transactions) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String blockInfo = prevHash + nonce + transactions.toString();
        String possibleHash = "";
        MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
        msdDigest.update(blockInfo.getBytes("UTF-8"));
        possibleHash = DatatypeConverter.printHexBinary(msdDigest.digest());
        return possibleHash;
    }

    public void verifyHash(int nonce, String hash) throws UnsupportedEncodingException, NoSuchAlgorithmException, WrongHashException {
        String verifiedHash = calcHash(nonce, prevHash, transactions);
        String target = "00";
        if (verifiedHash.equals(hash) && verifiedHash.substring(0,2).equals(target))
            this.hash = hash;
        else
            throw new WrongHashException("Failed to create the block, the hash is invalid.");
    }

    @Override
    public String toString() {
        return "Block{" +
                "hash='" + hash + '\'' +
                ", prevHash='" + prevHash + '\'' +
                ", transactions=" + transactions +
                '}';
    }

}
