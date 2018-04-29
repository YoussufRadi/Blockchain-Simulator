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

        blocks.add(b);
        size.add(1);
        updatePointers();

        System.out.println("TOTAL SIZE : " + blocks.size());
        System.out.println("BLOCK " + b.getPrevHash() + " SIZE :  " + current);
        System.out.println("Size is " + size);
        System.out.println("Hoppa all blockchain blocks : ");
        for (int i = 0; i < blocks.size(); i++) {
        	if(blocks.get(i)!=null)
        		System.out.println(blocks.get(i).getTransactions());
		}
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
