package puzzle;

import agent.Action;
import agent.Problem;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PuzzleProblem extends Problem<PuzzleState> {
    
    private PuzzleState goalState;
    
    public PuzzleProblem(PuzzleState initialState) {
        super(initialState,new ArrayList<Action>() );
        actions.add(new ActionUp());
        actions.add(new ActionRight());
        actions.add(new ActionDown());
        actions.add(new ActionLeft());
    }

    @Override
    public boolean isGoal(PuzzleState state) {
       return state.getColumnCar() == state.getNumColumns() -1;
    }

    @Override
    public List<PuzzleState> executeActions(PuzzleState state) {
      List<PuzzleState> successors = new LinkedList<PuzzleState>();
      
      for(Action a : actions){
          if(a.isValid(state)){
              PuzzleState successor = (PuzzleState)state.clone();
              a.execute(successor);
              successors.add(successor);
          }
      }
      return successors;
    }

    @Override
    public double computePathCost(List<Action> path) {
        return path.size();
    }
    
    

        
}
