package puzzle;

import agent.Heuristic;

public class HeuristicTileDistance extends Heuristic<PuzzleProblem, PuzzleState>{

    public double compute(PuzzleState state){
        //TODO        
        return 0;
    }
    
    @Override
    public String toString(){
        return "Tiles distance to final position";
    }
}