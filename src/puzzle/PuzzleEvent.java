package puzzle;

import java.util.EventObject;

public class PuzzleEvent extends EventObject {

    public PuzzleEvent(PuzzleState source) {
        super(source);
    }
}
