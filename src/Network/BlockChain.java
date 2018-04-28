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


    public String getHashOfLastBlock(){
        return chain.get(chain.size()-1).getHash();
    }


}
