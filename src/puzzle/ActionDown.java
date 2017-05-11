package puzzle;

import agent.Action;

public class ActionDown extends Action<PuzzleState>{

    public ActionDown(){
        super(1);
    }

    public void execute(PuzzleState state){
        state.moveDown();
        state.setAction(this);
    }

    public boolean isValid(PuzzleState state){
        return state.canMoveDown();
    }
}