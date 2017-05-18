package puzzle;

import agent.Action;

public class ActionLeft extends Action<PuzzleState>{

    public ActionLeft(int i){
        super(1,i);
    }

    public void execute(PuzzleState state){
        state.moveLeft(getElement());
        state.setAction(this);
    }

    public boolean isValid(PuzzleState state){
        return state.canMoveLeft(getElement());
    }
}
