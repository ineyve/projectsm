package puzzle;

import agent.Action;
import agent.State;
import java.util.ArrayList;
import java.util.Arrays;

public class PuzzleState extends State implements Cloneable {
    static final int[][] goalMatrix =  {{0,0,0,0,0,0},
                                        {0,0,0,0,0,0},
                                        {0,0,0,0,3,0},
                                        {0,0,0,0,0,0},
                                        {0,0,0,0,0,1},
                                        {0,0,0,0,0,0}
    };
   // static final int[] linesfinalMatrix = {0, 0, 0, 1, 1, 1, 2, 2, 2};
   // static final int[] colsfinalMatrix = {0, 1, 2, 0, 1, 2, 0, 1, 2};
    public static final int SIZE = 6;
    private int[][] matrix;
    private int lineBlank;
    private int columnBlank;

    public PuzzleState(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 1) {
                    lineBlank = i;
                    columnBlank = j;
                }
            }
        }
    }

    public void executeAction(Action action) {
        action.execute(this);
        firePuzzleChanged(null);
    }

    public boolean canMoveUp() {
        if(lineBlank!=0)
            if(this.matrix[lineBlank - 1][columnBlank]==0)
                return true;
        return false;
    }

    public boolean canMoveRight() {
        if(columnBlank<matrix.length-1)
            if(this.matrix[lineBlank][columnBlank + 1]==0)
                return true;
        return false;
    }

    public boolean canMoveDown() {
        if(lineBlank<matrix.length-1)
            if(this.matrix[lineBlank + 1][columnBlank]==0)
                return true;
        return false;
    }

    public boolean canMoveLeft() {
        if(columnBlank!=0)
            if(this.matrix[lineBlank][columnBlank -1]==0)
                return true;
        return false;
    }

    /*
     * In the next four methods we don't verify if the actions are valid.
     * This is done in method executeActions in class EightPuzzleProblem.
     * Doing the verification in these methods would imply that a clone of the
     * state was created whether the operation could be executed or not.
     */
    public void moveUp() {
        int aux = matrix[lineBlank][columnBlank];
        matrix[lineBlank][columnBlank] = matrix[--lineBlank][columnBlank];
        matrix[lineBlank][columnBlank] = aux;
        System.out.println("UP:"+lineBlank+"|||||"+columnBlank);
    }

    public void moveRight() {
        int aux = matrix[lineBlank][columnBlank];
        matrix[lineBlank][columnBlank] = matrix[lineBlank][++columnBlank];
        matrix[lineBlank][columnBlank] = aux;
        System.out.println("RIGHT:"+lineBlank+"|||||"+columnBlank);
    }

    public void moveDown() {
        int aux = matrix[lineBlank][columnBlank];
        matrix[lineBlank][columnBlank] = matrix[++lineBlank][columnBlank];
        matrix[lineBlank][columnBlank] = aux;
        System.out.println("DOWN:"+lineBlank+"|||||"+columnBlank);
    }

    public void moveLeft() {
        int aux = matrix[lineBlank][columnBlank];
        matrix[lineBlank][columnBlank] = matrix[lineBlank][--columnBlank];
        matrix[lineBlank][columnBlank] = aux;
        System.out.println("LEFT:"+lineBlank+"|||||"+columnBlank);
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
        return matrix[line][column];
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
            if(matrix[2][i]==1)
                return i;
        }
        return 0;
    }
}
