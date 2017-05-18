package agent;


public abstract class Action <S extends State>{
    private double cost;
    private int el;

    public Action(double cost, int el){
        this.cost = cost;
        this.el = el;
    }

    public abstract void execute(S State);

    public abstract boolean isValid(S State);

    public double getCost(){
        return cost;
    }
    
    public int getElement() {
        return el;
    } 
}