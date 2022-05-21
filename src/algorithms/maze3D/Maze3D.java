package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class Maze3D {
    /**
     * Maze attributes
     */
    public static final int WALL = 1;
    public static final int TRAN = 0;
    private int[][][] maze;
    private Position3D start;
    private Position3D goal;

    public Maze3D(int[][][] maze, Position3D s, Position3D g) {
        this.maze = maze;
        this.start = s;
        this.goal = g;
    }

    public Maze3D(int depth, int row, int col) throws IllegalArgumentException {
        if (depth < 2 || row < 2 || col < 2) {
            throw new IllegalArgumentException();
        }
        this.maze = new int[depth][row][col];
        this.start = new Position3D(0, 0, 0);
        this.goal = new Position3D(depth - 1, row - 1, col - 1);
    }

    public int[][][] getMap() {
        return this.maze;
    }

    public Position3D getStartPosition() {
        return this.start;
    }

    public Position3D getGoalPosition() {
        return this.goal;
    }

    // The implementation of the maze 3d is the same as the maze 2d imp.
    // because we are using the same algorithms in order to generate the maze, (with some adaptions)
    // the following method will be the same.
    // we cannot re-use the code from project's first part (maze 2d) because it is not capable.
    public void TranInitialize() {}
    public void WallInitialize() {}
    public void SetTransition(Position position) {}
    public ArrayList<Position> GetWallNeighbour(Position currentPosition) {}
    public ArrayList<Position> GetTransitionNeighbour(Position currentPosition) {}
    public ArrayList<Position> wallsTwoStepsAway(Position currentPosition) {}
    public boolean IsWall(Position position) {}
    public void connectNeighbours(Position currentPosition, Position neighbour) throws IllegalArgumentException {}


        private boolean isValidPosition(Position3D position3D) {
        if (
                position3D != null && 0 <= position3D.getDepthIndex() && this.maze.length < position3D.getDepthIndex() &&
                        0 <= position3D.getRowIndex() && this.maze[0].length < position3D.getRowIndex() &&
                        0 <= position3D.getColumnIndex() && this.maze[0][0].length < position3D.getColumnIndex()
        ) {
            return true;
        }
        return false;
    }

}
