package puzzle;

import agent.Action;

public class ActionUp extends Action<PuzzleState>{

    public ActionUp(){
        super(1);
    }

    public void execute(PuzzleState state){
        state.moveUp();
        state.setAction(this);
    }

    public boolean isValid(PuzzleState state){
        return state.canMoveUp();
    }
}