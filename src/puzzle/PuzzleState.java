package puzzle;

import agent.Action;
import agent.State;
import java.util.ArrayList;
import java.util.Arrays;

public class PuzzleState extends State implements Cloneable {

    private Boolean DEBUG_ = false;
    
    public static final int SIZE = 6;
    private int[][][] matrix;
    private int[] linesPieces;
    private int[] columnsPieces;

    //foreach peças todas -> mostrar no ecrã > criar sucessores -> executeActions
    public PuzzleState(int[][][] matrix) {
        linesPieces = new int[SIZE*SIZE];
        columnsPieces = new int[SIZE*SIZE];
        ArrayList<Integer> searchIds = new ArrayList<Integer>();
        //System.out.println(ArrayIds.matrixToString(matrix));
        
        this.matrix = new int[matrix.length][matrix.length][3];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j][0] = matrix[i][j][0]; //Get the piece ID               
                this.matrix[i][j][1] = matrix[i][j][1]; //Get the piece Type           
                this.matrix[i][j][2] = matrix[i][j][2]; //Get the piece Image
                
                int id = this.matrix[i][j][0];
                Boolean repeating = searchIds.contains(id);
                
                if (this.matrix[i][j][1] != 0 && repeating == false) { //Put coordinates on array, each ID is a cell
                    linesPieces[id] = i; 
                    columnsPieces[id] = j;
                    searchIds.add(id);
                }
                
                //Code to edit image
            }
        }
    }

    public void executeAction(Action action) {
        action.execute(this);
        firePuzzleChanged(null);
    }

    public boolean canMoveUp(int id) {
        if (linesPieces[id] > 0) {
            if (this.matrix[linesPieces[id] - 1][columnsPieces[id]][1] == 0) { //Works, even for multiple pieces
                return true;
            }
        }
        return false;
    }

    public boolean canMoveRight(int id) {
        int type = this.matrix[linesPieces[id]][columnsPieces[id]][1];
        if(type == 4 && columnsPieces[id] + 1 < matrix.length-1) //multiple pieces
        {
            if (this.matrix[linesPieces[id]][columnsPieces[id] + 2][1] == 0) {
                return true;
            }
        }
        else if(type == 6 && columnsPieces[id] + 2 < matrix.length-1) //multiple pieces
        {   
            if (this.matrix[linesPieces[id]][columnsPieces[id] + 3][1] == 0) {
                return true;
            }
        }
        else if(type == 8 && columnsPieces[id] + 3 < matrix.length-1) //multiple pieces
        {
            if (this.matrix[linesPieces[id]][columnsPieces[id] + 4][1] == 0) {
                return true;
            }
        }
        else if(type == 3 || type == 1) //single cell
        {
            if (columnsPieces[id] < matrix.length-1) {
                if (this.matrix[linesPieces[id]][columnsPieces[id] + 1][1] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canMoveDown(int id) {
        
        //CODE SHOULD BE OPTIMIZED!!!
        
       
        
        int type = this.matrix[linesPieces[id]][columnsPieces[id]][1];
        if(type == 5 && linesPieces[id] + 1 < matrix.length-1) //multiple pieces
        { 
            if (this.matrix[linesPieces[id] + 2][columnsPieces[id]][1] == 0) {
                return true;
            }
        }
        else if(type == 7 && linesPieces[id] + 2 < matrix.length-1) //multiple pieces
        {
            if (this.matrix[linesPieces[id] + 3][columnsPieces[id]][1] == 0) {
                return true;
            }
        }
        else if(type == 9 && linesPieces[id] + 3 < matrix.length-1) //multiple pieces
        {
            if (this.matrix[linesPieces[id] + 4][columnsPieces[id]][1] == 0) {
                return true;
            }
        }
        
        else if(type == 3) //single cell
        {
            
        }
            if (linesPieces[id] < matrix.length - 1) {
                if (this.matrix[linesPieces[id] + 1][columnsPieces[id]][1] == 0) {
                    return true;
                }
            }
        
        
        return false;
    }

    public boolean canMoveLeft(int id) {
        if (columnsPieces[id] > 0) {
            if (this.matrix[linesPieces[id]][columnsPieces[id] - 1][1] == 0) { //Works, even for multiple pieces
                return true;
            }
        }
        return false;
    }

    /* 
     * Auto-generated comment?
     * In the next four methods we don't verify if the actions are valid.
     * This is done in method executeActions in class EightPuzzleProblem.
     * Doing the verification in these methods would imply that a clone of the
     * state was created whether the operation could be executed or not.
     */
    
    public void moveUp(int id) {
        if(DEBUG_)System.out.print("\nU"+ArrayIds.matrixToString(matrix)+"\n");
        int type = this.matrix[linesPieces[id]][columnsPieces[id]][1];
        
        //Get piece size knowing it's type
        int pieces = 1;
        switch (type) {
            case 5:
                pieces = 2;
                break;
            case 7:
                pieces = 3;
                break;
            case 9:
                pieces = 4;
                break;
            default:
                break;
        }
        int aux;
        aux = matrix[linesPieces[id]+(pieces-1)][columnsPieces[id]][0];
        matrix[linesPieces[id]+(pieces-1)][columnsPieces[id]][0] = matrix[linesPieces[id]-1][columnsPieces[id]][0];
        matrix[linesPieces[id]-1][columnsPieces[id]][0] = aux;

        aux = matrix[linesPieces[id]+(pieces-1)][columnsPieces[id]][1];
        matrix[linesPieces[id]+(pieces-1)][columnsPieces[id]][1] = matrix[linesPieces[id]-1][columnsPieces[id]][1];
        matrix[linesPieces[id]-1][columnsPieces[id]][1] = aux;
        linesPieces[id]--;
    }

    public void moveRight(int id) {
        if(DEBUG_)System.out.print("\nR"+ArrayIds.matrixToString(matrix)+"\n");
        int type = this.matrix[linesPieces[id]][columnsPieces[id]][1];
        int pieces = 1;
        switch (type) {
            case 4:
                pieces = 2;
                break;
            case 6:
                pieces = 3;
                break;
            case 8:
                pieces = 4;
                break;
            default:
                break;
        }
        int aux;
        aux = matrix[linesPieces[id]][columnsPieces[id]][0];
        matrix[linesPieces[id]][columnsPieces[id]][0] = matrix[linesPieces[id]][columnsPieces[id]+pieces][0];
        matrix[linesPieces[id]][columnsPieces[id]+pieces][0] = aux;

        aux = matrix[linesPieces[id]][columnsPieces[id]][1];
        matrix[linesPieces[id]][columnsPieces[id]][1] = matrix[linesPieces[id]][columnsPieces[id]+pieces][1];
        matrix[linesPieces[id]][columnsPieces[id]+pieces][1] = aux;
        columnsPieces[id]++;
    }

    public void moveDown(int id) {
        if(DEBUG_)System.out.print("\nD"+ArrayIds.matrixToString(matrix)+"\n");
        int type = this.matrix[linesPieces[id]][columnsPieces[id]][1];
        
        int pieces = 1;
        switch (type) {
            case 5:
                pieces = 2;
                break;
            case 7:
                pieces = 3;
                break;
            case 9:
                pieces = 4;
                break;
            default:
                break;
        }
        int aux;
        aux = matrix[linesPieces[id]][columnsPieces[id]][0];
        matrix[linesPieces[id]][columnsPieces[id]][0] = matrix[linesPieces[id]+pieces][columnsPieces[id]][0];
        matrix[linesPieces[id]+pieces][columnsPieces[id]][0] = aux;

        aux = matrix[linesPieces[id]][columnsPieces[id]][1];
        matrix[linesPieces[id]][columnsPieces[id]][1] = matrix[linesPieces[id]+pieces][columnsPieces[id]][1];
        matrix[linesPieces[id]+pieces][columnsPieces[id]][1] = aux;
        linesPieces[id]++;
    }

    public void moveLeft(int id) {
        if(DEBUG_)System.out.print("\nL"+ArrayIds.matrixToString(matrix)+"\n");
        
        int type = this.matrix[linesPieces[id]][columnsPieces[id]][1];
        int pieces = 1;
        switch (type) {
            case 4:
                pieces = 2;
                break;
            case 6:
                pieces = 3;
                break;
            case 8:
                pieces = 4;
                break;
            default:
                break;
        }
        int aux;     
        aux = matrix[linesPieces[id]][columnsPieces[id]+(pieces-1)][0];
        matrix[linesPieces[id]][columnsPieces[id]+(pieces-1)][0] = matrix[linesPieces[id]][columnsPieces[id]-1][0];
        matrix[linesPieces[id]][columnsPieces[id]-1][0] = aux;

        aux = matrix[linesPieces[id]][columnsPieces[id]+(pieces-1)][1];
        matrix[linesPieces[id]][columnsPieces[id]+(pieces-1)][1] = matrix[linesPieces[id]][columnsPieces[id]-1][1];
        matrix[linesPieces[id]][columnsPieces[id]-1][1] = aux;
        columnsPieces[id]--;
    }

    public int getNumLines() {
        return matrix.length;
    }

    public int getNumColumns() {
        return matrix[0].length;
    }

    public int getTileValue(int line, int column) {

        //verificar se naquela linha já está código do mesmo tipo
        
        if (!isValidPosition(line, column)) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        return matrix[line][column][2]; //
    }
    
    public int getTileType(int line, int column) {
        if (!isValidPosition(line, column)) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        return matrix[line][column][1]; 
    }

    public boolean isValidPosition(int line, int column) {
        return line >= 0 && line < matrix.length && column >= 0 && column < matrix[0].length;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PuzzleState)) {
            return false;
        }

        PuzzleState o = (PuzzleState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public Object clone() {
        return new PuzzleState(matrix);
    }
    //Listeners
    private transient ArrayList<PuzzleListener> listeners = new ArrayList<PuzzleListener>(3);

    public synchronized void removeListener(PuzzleListener l) {
        if (listeners != null && listeners.contains(l)) {
            listeners.remove(l);
        }
    }

    public synchronized void addListener(PuzzleListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void firePuzzleChanged(PuzzleEvent pe) {
        for (PuzzleListener listener : listeners) {
            listener.puzzleChanged(null);
        }
    }

    int getColumnCar() {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[2][i][1] == 1) {
                return i;
            }
        }
        return 0;
    }
}
