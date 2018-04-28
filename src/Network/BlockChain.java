package Network;

import java.util.ArrayList;

public class BlockChain {

    private ArrayList<Block> chain;
    private ArrayList<Block> cache;

    public void addBlockToChain(Block b){

        if(chain.isEmpty()){
            chain.add(b);
            return;
        }

        Block lastBlock = chain.get(chain.size()-1);
        if(lastBlock.getHash().equals(b.getPrevHash())){
            chain.add(b);
            return;

        }



    }

    public boolean checkBlockInBlockChain(Block b){
        if(chain.contains(b))
            return true;
        return false;
    }


    public String getHashOfLastBlock(){
        return chain.get(chain.size()-1).getHash();
    }

    public boolean checkTransactionInChain(Transaction t){
        for(Block b : chain){
            if(b.getTransactions().contains(t))
                return true;
        }
        return false;
    }


}
