package Network;

import java.io.Serializable;
import java.util.ArrayList;

public class BlockChain implements Serializable {

    private Block head;
    private ArrayList<Block> blocks;
    private ArrayList<Integer> size;
    private int current;

    BlockChain() {
        this.head = null;
        blocks = new ArrayList<>();
        size = new ArrayList<>();
        blocks.add(null);
        size.add(0);
        current = 0;
    }

    public void addBlockToChain(Block b){

        System.out.println("BLOOOCKK    " + b);

        blocks.add(b);
        size.add(1);
        updatePointers();
    }

    private void updatePointers(){
        for(int i = 0; i < blocks.size(); i++){
            Block b = blocks.get(i);
            if(b == null)
                continue;
            int index = blocks.indexOf(b.getPreviousBlock());
            if(index == -1){
                size.set(i, 1);
            } else {
                b.setPreviousBlock(blocks.get(index));
                size.set(i, size.get(index)+1);
            }

            if(size.get(i) > current){
                head = b;
                current = size.get(i);
            }
        }
    }


    public boolean checkBlockInBlockChain(Block b){
        return blocks.contains(b);
    }


    public Block getLastBlock(){
        return head;
    }

    public boolean checkTransactionInChain(Transaction t){
        for(Block b : blocks){
            if(b == null)
                continue;
            if(b.getTransactions().contains(t))
                return true;
        }
        return false;
    }


}
