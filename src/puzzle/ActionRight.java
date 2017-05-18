package puzzle;

import agent.Action;

public class ActionRight extends Action<PuzzleState>{

    public ActionRight(int i){
        super(1,i);
    }

    public void execute(PuzzleState state){
        state.moveRight(getElement());
        state.setAction(this);
    }

    public boolean isValid(PuzzleState state){
        return state.canMoveRight(getElement());
    }
}