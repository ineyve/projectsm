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
		
		int id=1;
        int largePiece=0;        
        for(int l=0; l < initialState.getNumLines(); l++)
        {
            for(int c=0; c < initialState.getNumColumns(); c++)
            {
                int val = initialState.getTileValue(l, c);
                if(largePiece<=0)
                {
                    switch(val){
                        case 1: case 2: case 4: case 6: case 8:
                            actions.add(new ActionRight(id));
                            actions.add(new ActionLeft(id));
                            id++;
                        break;
                    }
                    switch(val){
                        //horizontal
                        case 4:
                            largePiece=1;
                        break;
                        case 6: 
                            largePiece=2;
                        break;
                        case 8:
                            largePiece=3;
                        break;
                    }
                }else{
                    largePiece--;
                }
            }
        }
        
        for(int c=0; c < initialState.getNumColumns(); c++)
        {
            for(int l=0; l < initialState.getNumLines(); l++)
            {
                int val = initialState.getTileValue(l, c);
                if(largePiece<=0)
                {
                    
                    switch(val){
                        case 3: case 5: case 7: case 9:
                            actions.add(new ActionUp(id));
                            actions.add(new ActionDown(id));
                            id++;
                        break;
                    }
                    switch(val){
                        //vertical
                        case 5: 
                            largePiece=1;
                        break;
                        case 7: 
                            largePiece=2;
                        break;
                        case 9:
                            largePiece=3;
                        break;
                    }
                }else{
                    largePiece--;
                }
                
            }
        }
        System.out.print("start");
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
