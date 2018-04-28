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

//        if(head == null){
//            b.setPreviousBlock(head);
//            head = b;
//            return;
//        }

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
        if(size.get(size.size()-1) > current)
            head = b;


    }

//    public void addBlockToChain(Block b){
//
//        if(chain.isEmpty()){
//            chain.add(b);
//            return;
//        }
//
//        Block lastBlock = chain.get(chain.size()-1);
//        if(lastBlock.getHash().equals(b.getPrevHash())){
//            chain.add(b);
//            return;
//        }
//
//        for(BlockChain bc : cache){
//            if(bc.getHashOfLastBlock().equals(b.getPrevHash())){
//                bc.addBlockToChain(b);
//                Block temp = null;
//                for(Block a : chain){
//                    if(a.getHash().equals(bc.chain.get(0).getPrevHash())){
//                        temp = a;
//                        break;
//                    }
//                }
//                break;
//            }
//        }
//
//
//    }

    public boolean checkBlockInBlockChain(Block b){
        if(blocks.contains(b))
            return true;
        return false;
    }


    public Block getHashOfLastBlock(){
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
