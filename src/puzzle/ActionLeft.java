package puzzle;

import agent.Action;

public class ActionLeft extends Action<PuzzleState>{

    public ActionLeft(){
        super(1);
    }

    public void execute(PuzzleState state){
        state.moveLeft();
        state.setAction(this);
    }

    public boolean isValid(PuzzleState state){
        return state.canMoveLeft();
    }
}
