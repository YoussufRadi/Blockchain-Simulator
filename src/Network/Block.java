package Network;


import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;

public class Block {
    String hash;
    String prevHash;
    int nonce;
    ArrayList<Transaction> transactions;

    public Block(String prevHash, int nonce, ArrayList<Transaction> transactions) {
        this.prevHash = prevHash;
        this.nonce = nonce;
        this.transactions = transactions;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }


    public String calcHash() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String blockInfo = prevHash + nonce + transactions.toString();
        String possibleHash = "";
        MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
        msdDigest.update(blockInfo.getBytes("UTF-8"));
        possibleHash = DatatypeConverter.printHexBinary(msdDigest.digest());
        return possibleHash;
    }

    @Override
    public String toString() {
        return "Block{" +
                "hash='" + hash + '\'' +
                ", prevHash='" + prevHash + '\'' +
                ", nonce=" + nonce +
                ", transactions=" + transactions +
                '}';
    }
}
