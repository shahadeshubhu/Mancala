import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoardC {
    public ArrayList<PitContainer> pitContainer;
    private Gamer gamer;
    boolean rowEmpty = true;
    private int[] prevS; // previousState
    private boolean undo; // redo
    private boolean turnAgain; // extraTurn
    private int undoIndex; // countUndo
    private ArrayList<ChangeListener> listOfListeners; // listeners

    public BoardC(BoardTheme boardC){
        pitContainer = new ArrayList<PitContainer>();
        prevS = new int[14];
        turnAgain = false;
        undo = false;
        undoIndex = 0;
        gamer = Gamer.FIRST; // Player.ONE
        for(int i = 0; i < 6; i++){
            pitContainer.add(new PitContainer(Gamer.FIRST, 0, i, boardC));
        }
        pitContainer.add(new MancalaContainer(Gamer.FIRST, 0, 6, boardC));
        for(int i = 7; i < 13; i++){
            pitContainer.add(new PitContainer(Gamer.SECOND, 0, i, boardC));
        }
        pitContainer.add(new MancalaContainer(Gamer.SECOND, 0, 13, boardC));

        listOfListeners = new ArrayList<ChangeListener>();
    }

    //getPlayer
    public Gamer getGamer(){
        return gamer;
    }

    //gameOver
    public boolean finalScore() {
        for (int i = 0; i < 6; i++) {
            if (pitContainer.get(i).getStones() != 0) {
                rowEmpty = false;
            }
        }
        for (int i = 7; i < 13; i++) {
            if (pitContainer.get(i).getStones() != 0) {
                rowEmpty = false;
            }
        }
        return rowEmpty;
    }

    // getMancala
    public MancalaContainer getScore(Gamer gamer) {
        for (PitContainer pit : pitContainer) {
            if (pit.getGamer() == gamer && pit instanceof MancalaContainer) {
                return (MancalaContainer) pit;
            }
        }
        return null;
    }

    // old method fillBoard()
    // populates the mancala board with specified amount of stones
    public void populateBoard(int amtStones){
        int index = 0;
        for(PitContainer pitC: pitContainer){
            if(!(pitC instanceof MancalaContainer)){
                pitC.setStones(amtStones);
                prevS[index] = amtStones;
            }
            else{
                prevS[index] = 0;
                index++;
            }
        }
        ChangeEvent ce = new ChangeEvent(this);
        for(ChangeListener l: listOfListeners){
            l.stateChanged(ce);
        }
    }
    // old method addChangeListener()
    public void appendChangeListener(ChangeListener l){
        listOfListeners.add(l);
    }

    // old method distributeMarbles()
    public void updatePitContainer(int indexOfPit, int amtStone){
        int availableStones = amtStone;
        int index = indexOfPit;
        while(availableStones > 0){
            if(index == 5 && gamer == Gamer.SECOND){
                index = index + 2;
            }
            else if(index == 12 && gamer == Gamer.FIRST){
                index = 0;
            }
            else{
                index++;
            }
            if(index == 14){
                index = 0;
            }
            pitContainer.get(index).setStones(pitContainer.get(index).getStones() + 1);
            availableStones--;
        }
    }
    // old method choosePit()
    public void selectPitContainer(PitContainer pitContainer){
        if(!undo){
            undoIndex = 0;
        }
        if(pitContainer.getGamer() != gamer){
            return;
        }
        if(pitContainer.getStones() == 0){
            return;
        }
        for(PitContainer pit: this.pitContainer){
            prevS[pit.getPitContainerPosition()] = pit.getStones();
        }

        turnAgain = isTurnAgain(pitContainer);
        int lastStone = lastStone(pitContainer);
        int availableNumStones = pitContainer.getStones();
        pitContainer.setStones(0);

        updatePitContainer(pitContainer.getPitContainerPosition(), availableNumStones); // distributeMarbles
        takeStones(lastStone); // wonOpponenetMarbles

        if(finalScore()){ // gameOver
            resetBoard();
        }
        if(!turnAgain){
            swapGamer(); // switchPlayer()
        }
        undo = false;
        ChangeEvent ce = new ChangeEvent(this);
        for(ChangeListener l: listOfListeners){
            l.stateChanged(ce);
        }
    }
    public boolean undoPossible() {
        if(finalScore() || firstTurn() || undoIndex == 3){
            return false;
        }
        else
            return true;
    }

    // old method getLastMarble()
    public int lastStone(PitContainer pitContainer){
        int availableNumStones = pitContainer.getStones();
        int index = pitContainer.getPitContainerPosition();
        while(availableNumStones > 0){
            if(index == 5 && gamer == Gamer.SECOND){
                index = index + 2;
            }
            else if(index == 12 && gamer == Gamer.FIRST){
                index = index + 2;
            }
            else{
                index++;
            }
            if(index == 14){
                index = 0;
            }
            availableNumStones--;
        }
        return index;
    }

    private boolean firstTurn() {
        boolean fTurn = false;
        for(PitContainer pit: pitContainer)
            if(pit.getStones()!=prevS[pit.getPitContainerPosition()])
                fTurn = false;
            else
                fTurn = true;
        return fTurn;
    }

    // clearBoard()
    private void resetBoard(){
        for(int i = 0; i < 6; i++){
            pitContainer.get(6).setStones(pitContainer.get(6).getStones() + pitContainer.get(i).getStones());
            pitContainer.get(i).setStones(0);
        }
        for(int i = 7; i < 13; i++){
            pitContainer.get(13).setStones(pitContainer.get(13).getStones() + pitContainer.get(i).getStones());
            pitContainer.get(i).setStones(0);
        }

      /*for(int i = 0; i < 14; i++){
        pitContainer.get(i).setStones(0);
      }*/
    }

    // switchPlayer()
    private void swapGamer(){
        gamer = gamer == Gamer.FIRST? Gamer.SECOND: Gamer.FIRST;
        turnAgain = false;
    }

    // old method wonOpponenetMarbles;
    private void takeStones(int indexLastStonePlaced){
        int posMancala = 6; // default to player 1
        if(indexLastStonePlaced == 6 || indexLastStonePlaced == 13){
            return; // stone placed in a player's mancala. Unable to take stones
        }
        else if(pitContainer.get(indexLastStonePlaced).getStones() == 1 && pitContainer.get(indexLastStonePlaced).getGamer() == gamer){
            if(pitContainer.get(12-indexLastStonePlaced).getStones() == 0){
                return;
            }
            posMancala = gamer == Gamer.FIRST? 6: 13;

            pitContainer.get(posMancala).setStones(pitContainer.get(posMancala).getStones() + pitContainer.get(12 - indexLastStonePlaced).getStones());

            pitContainer.get(posMancala).setStones(pitContainer.get(posMancala).getStones() + pitContainer.get(indexLastStonePlaced).getStones());

            pitContainer.get(12 - indexLastStonePlaced).setStones(0);
            pitContainer.get(indexLastStonePlaced).setStones(0);
        }
    }

    // old method getExtraTurn
    private boolean isTurnAgain(PitContainer pitContainer){
        int stoneAmt = pitContainer.getPitContainerPosition() + pitContainer.getStones();
        if((stoneAmt - 6) % 13 == 0 && gamer == Gamer.FIRST){
            return true;
        }
        if((stoneAmt - 6) % 13 == 0 && gamer == Gamer.SECOND){
            return true;
        }
        return false;
    }

    //Undo
    public void isUndo(){
        if(!undoPossible()){
            System.out.println("Can not process Undo command");
        }
        else {
            undo = true;
            undoIndex = undoIndex + 1;
            for (PitContainer pat : pitContainer )
                pat.setStones(prevS[pat.getPitContainerPosition()]);
            ChangeEvent event = new ChangeEvent(this);
            for ( ChangeListener listener : listOfListeners ) {
                listener.stateChanged(event);
            }
        }

    }

    //getData
    public ArrayList<PitContainer> data() {
        return pitContainer;
    }

}