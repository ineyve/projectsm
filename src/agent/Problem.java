package agent;

import java.util.List;

public abstract class Problem <S extends State>{

    private  S initialState;
    protected List<Action> actions;
    private Heuristic heuristic;

    public Problem(S initialState, List<Action> actions) {
        this.initialState = initialState;
        this.actions = actions;
        
    }

    /**
     * @return the initialState
     */
    public S getInitialState() {
        return initialState;
    }

    /**
     * @return the heuristic
     */
    public Heuristic getHeuristic() {
        return heuristic;
    }

    /**
     * @param heuristic the heuristic to set
     */
    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }
    
    public abstract boolean isGoal (S state);
    
    public abstract List<S> executeActions(S state);
    
    public double computePathCost (List<Action> path) {
        double cost = 0;
        
        for(Action a : path){
            cost+= a.getCost();
        }
        return cost;
    }
    
}
