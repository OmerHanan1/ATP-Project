package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    public MazeState(AState prevState, Position stateName, int cost) {
        super(prevState, stateName, cost);
    }
}