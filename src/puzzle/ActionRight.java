package puzzle;

import agent.Action;

public class ActionRight extends Action<PuzzleState>{

    public ActionRight(){
        super(1);
    }

    public void execute(PuzzleState state){
        state.moveRight();
        state.setAction(this);
    }

    public boolean isValid(PuzzleState state){
        return state.canMoveRight();
    }
}