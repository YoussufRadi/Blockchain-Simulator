package Network;

import java.util.ArrayList;

public class BlockChain {

    private Block head;
    private ArrayList<Block> blocks;
    private ArrayList<Integer> size;
    private int current;

    public BlockChain() {
        this.head = null;
        blocks = new ArrayList<>();
        size = new ArrayList<>();
        current = 0;
    }

    public void addBlockToChain(Block b){

        if(head.equals(b.getPreviousBlock())){
            b.setPreviousBlock(head);
            blocks.add(b);
            if(size.isEmpty())
                size.add(1);
            else size.get(blocks.indexOf(head)+1);
            current = size.get(size.size()-1);
            head = b;
            return;
        }

        int index = blocks.indexOf(b.getPreviousBlock());
        b.setPreviousBlock(blocks.get(index));
        blocks.add(b);
        size.add(index+1);
        if(size.get(size.size()-1) > current){
            head = b;
            current = size.get(size.size()-1);
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
            if(b.getTransactions().contains(t))
                return true;
        }
        return false;
    }


}
