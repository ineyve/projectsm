package puzzle;

import agent.Action;

public class ActionUp extends Action<PuzzleState>{

    public ActionUp(int i){
        super(1,i);
    }

    public void execute(PuzzleState state){
        state.moveUp(getElement());
        state.setAction(this);
    }

    public boolean isValid(PuzzleState state){
        return state.canMoveUp(getElement());
    }
}