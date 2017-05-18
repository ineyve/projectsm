package puzzle;

import agent.Action;

public class ActionDown extends Action<PuzzleState>{

    public ActionDown(int i){
        super(1,i);
    }

    public void execute(PuzzleState state){
        state.moveDown(getElement());
        state.setAction(this);
    }

    public boolean isValid(PuzzleState state){
        return state.canMoveDown(getElement());
    }
}