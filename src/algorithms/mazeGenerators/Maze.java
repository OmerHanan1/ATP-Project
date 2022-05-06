package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * Maze class
 */
public class Maze {
    /**
     * Maze attributes
     */
    public static final int WALL = 1;
    public static final int TRAN = 0;
    private int[][] maze;
    private Position start;
    private Position goal;

    /**
     * C-tor // given row-col
     *
     * @param rows:    num of desire rows in maze.
     * @param columns: num of desire cols in maze.
     * @throws IllegalArgumentException in case of given args are illegal- out of bound.
     */
    public Maze(int rows, int columns) {
        this.maze = new int[rows][columns];
        this.start = new Position(0, 0);
        this.goal = new Position(rows - 1, columns - 1);
    }

    /**
     * C-tor // given explicit args
     *
     * @param maze:  maze grid- 1/0 grid represents a maze.
     * @param start: start pos // starting point of the maze.
     * @param goal:  goal pos // finish point of the maze.
     */
    public Maze(int[][] maze, Position start, Position goal) {
        this.maze = maze;
        this.start = start;
        this.goal = goal;
    }

    // Init maze with transitions "0"
    public void TranInitialize() {
        for (int i = 0; i < this.getRows(); ++i) {
            for (int j = 0; j < this.getCols(); ++j) {
                // transition => 0 equality (final).
                maze[i][j] = TRAN;
            }
        }
    }

    // Check position inside array bounds and position is legal.
    public boolean validMazePosition(Position position) {
        return (position != null &&
                0 <= position.getRowIndex() && position.getRowIndex() < this.getRows() &&
                0 <= position.getColumnIndex() && position.getColumnIndex() < this.getCols());
    }

    // Init maze with walls "1"
    public void WallInitialize() {
        for (int i = 0; i < this.getRows(); ++i) {
            for (int j = 0; j < this.getCols(); ++j) {
                // Wall => 1 equality (final).
                maze[i][j] = WALL;
            }
        }
    }

    // Set "0" = break the wall in current position
    public void SetTransition(Position position) {
        if (position.getRowIndex() >= 0 && position.getColumnIndex() >= 0)
            this.maze[position.getRowIndex()][position.getColumnIndex()] = TRAN;
    }

    public static final String RED = "\033[0;31m";      // RED
    public static final String RESET = "\033[0m";       // Text Reset

    // Print the maze in needed format
    public void print() {
        for (int i = 0; i < this.getRows(); i++) {
            System.out.print("{");
            for (int j = 0; j < this.getCols(); j++) {
                if (this.start.equals(new Position(i, j)))
                    System.out.print(RED + " S" + RESET);
                else if (this.goal.equals(new Position(i, j)))
                    System.out.print(RED + " F" + RESET);
                else
                    System.out.print(" " + this.maze[i][j]);
            }
            System.out.println(" }");
        }
    }

    // WALL neighbours
    public ArrayList<Position> GetWallNeighbour(Position currentPosition) {
        ArrayList<Position> wallsList = new ArrayList<>();
        if (currentPosition != null) {
            Position up = currentPosition.Up();
            if (IsWall(up)) //UP
                wallsList.add(up);
            Position right = currentPosition.Right();
            if (IsWall(right)) //RIGHT
                wallsList.add(right);
            Position down = currentPosition.Down();
            if (IsWall(down)) //DOWN
                wallsList.add(down);
            Position left = currentPosition.Left();
            if (IsWall(left)) //LEFT
                wallsList.add(left);
        }
        return wallsList;
    }

    // TRAN neighbours
    public ArrayList<Position> GetTransitionNeighbour(Position currentPosition) {
        ArrayList<Position> wallsList = new ArrayList<>();
        if (currentPosition != null) {
            Position up = currentPosition.Up();
            if (!IsWall(up)) //UP
                wallsList.add(up);
            Position right = currentPosition.Right();
            if (!IsWall(right)) //RIGHT
                wallsList.add(right);
            Position down = currentPosition.Down();
            if (!IsWall(down)) //DOWN
                wallsList.add(down);
            Position left = currentPosition.Left();
            if (!IsWall(left)) //LEFT
                wallsList.add(left);
        }
        return wallsList;
    }

    // Using as sub-function for the DFS algorithm
    public ArrayList<Position> wallsTwoStepsAway(Position currentPosition) {
        ArrayList<Position> wallsList = new ArrayList<>();
        if (currentPosition != null) {
            Position up = currentPosition.Up().Up();
            if (IsWall(up))
                wallsList.add(up);
            Position right = currentPosition.Right().Right();
            if (IsWall(right))
                wallsList.add(right);
            Position down = currentPosition.Down().Down();
            if (IsWall(down))
                wallsList.add(down);
            Position left = currentPosition.Left().Left();
            if (IsWall(left))
                wallsList.add(left);
        }
        return wallsList;
    }

    // True - if the given position is "1"
    // False - if the given position is "0"
    public boolean IsWall(Position position) {
        if (validMazePosition(position)) {
            return maze[position.getRowIndex()][position.getColumnIndex()] == 1;
        }
        return false;
    }

    // Connect two positions
    public void connectNeighbours(Position currentPosition, Position neighbour) throws IllegalArgumentException {
        if (!this.validMazePosition(currentPosition)) {
            throw new IllegalArgumentException("one of the given positions is not a valid position in the maze");
        }
        if (currentPosition.getColumnIndex() == neighbour.getColumnIndex()) {
            this.SetTransition(new Position(Math.min(neighbour.getRowIndex(), currentPosition.getRowIndex()) + 1, currentPosition.getColumnIndex()));
        } else if (currentPosition.getRowIndex() == neighbour.getRowIndex()) {
            this.SetTransition(new Position(currentPosition.getRowIndex(), Math.min(neighbour.getColumnIndex(), currentPosition.getColumnIndex()) + 1));
        }
    }

    public int getRows() {
        return maze.length;
    }

    public int getCols() {
        return maze[0].length;
    }

    public int[][] getMaze() {
        return maze;
    }

    public Position getStartPosition() {
        return start;
    }

    public Position getGoalPosition() {
        return goal;
    }
}