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

    public void TranInitialize() {
        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[0].length; j++) {
                for (int k = 0; k < this.maze[0][0].length; k++) {
                    this.maze[i][j][k] = TRAN; // transition = 0;
                }
            }
        }
    }

    public void WallInitialize() {
        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[0].length; j++) {
                for (int k = 0; k < this.maze[0][0].length; k++) {
                    this.maze[i][j][k] = WALL; // wall = 1;
                }
            }
        }
    }

    public void SetTransition(Position3D position) {
        if(isValidPosition(position)){
            this.maze[position.getDepthIndex()][position.getRowIndex()][position.getColumnIndex()] = TRAN;
        }
    }

    public ArrayList<Position3D> GetWallNeighbour(Position3D currentPosition) {
        ArrayList<Position3D> wallsList = new ArrayList<>();
        if (currentPosition != null) {
            Position3D up = currentPosition.getUpPosition();
            if (this.isValidPosition(up) && IsWall(up)) //UP
                wallsList.add(up);
            Position3D right = currentPosition.getRightPosition();
            if (this.isValidPosition(right) && IsWall(right)) //RIGHT
                wallsList.add(right);
            Position3D down = currentPosition.getDownPosition();
            if (this.isValidPosition(down) && IsWall(down)) //DOWN
                wallsList.add(down);
            Position3D left = currentPosition.getLeftPosition();
            if (this.isValidPosition(left) && IsWall(left)) //LEFT
                wallsList.add(left);
            Position3D high = currentPosition.getHigherPosition();
            if (this.isValidPosition(high) && IsWall(down)) //DOWN
                wallsList.add(down);
            Position3D low = currentPosition.getLowerPosition();
            if (this.isValidPosition(low) && IsWall(left)) //LEFT
                wallsList.add(left);
        }
        return wallsList;
    }

    public ArrayList<Position3D> GetTransitionNeighbour(Position3D currentPosition) {
        ArrayList<Position3D> wallsList = new ArrayList<>();
        if (currentPosition != null) {
            Position3D up = currentPosition.getUpPosition();
            if (this.isValidPosition(up) && !IsWall(up)) //UP
                wallsList.add(up);
            Position3D right = currentPosition.getRightPosition();
            if (this.isValidPosition(right) && !IsWall(right)) //RIGHT
                wallsList.add(right);
            Position3D down = currentPosition.getDownPosition();
            if (this.isValidPosition(down) && !IsWall(down)) //DOWN
                wallsList.add(down);
            Position3D left = currentPosition.getLeftPosition();
            if (this.isValidPosition(left) && !IsWall(left)) //LEFT
                wallsList.add(left);
            Position3D high = currentPosition.getHigherPosition();
            if (this.isValidPosition(high) && !IsWall(down)) //DOWN
                wallsList.add(down);
            Position3D low = currentPosition.getLowerPosition();
            if (this.isValidPosition(low) && !IsWall(left)) //LEFT
                wallsList.add(left);
        }
        return wallsList;
    }

    public ArrayList<Position3D> wallsTwoStepsAway(Position3D currentPosition) {
        ArrayList<Position3D> wallsList = new ArrayList<>();
        if (currentPosition != null) {
            Position3D up = currentPosition.getUpPosition().getUpPosition();
            if (this.isValidPosition(up) && IsWall(up))
                wallsList.add(up);
            Position3D right = currentPosition.getRightPosition().getRightPosition();
            if (this.isValidPosition(right) && IsWall(right))
                wallsList.add(right);
            Position3D down = currentPosition.getDownPosition().getDownPosition();
            if (this.isValidPosition(down) && IsWall(down))
                wallsList.add(down);
            Position3D left = currentPosition.getLeftPosition().getLeftPosition();
            if (this.isValidPosition(left) && IsWall(left))
                wallsList.add(left);
            Position3D high = currentPosition.getHigherPosition().getHigherPosition();
            if (this.isValidPosition(high) && IsWall(high))
                wallsList.add(high);
            Position3D low = currentPosition.getLowerPosition().getLowerPosition();
            if (this.isValidPosition(low) && IsWall(low))
                wallsList.add(low);
        }
        return wallsList;
    }

    public boolean IsWall(Position3D position) {
        return (isValidPosition(position) && this.maze[position.getDepthIndex()][position.getRowIndex()][position.getColumnIndex()] == WALL);
    }

    public void connectNeighbours(Position3D currentPosition, Position3D neighbour) throws IllegalArgumentException {

    }


    private boolean isValidPosition(Position3D position3D) {
        return position3D != null && 0 <= position3D.getDepthIndex() && this.maze.length < position3D.getDepthIndex() &&
                0 <= position3D.getRowIndex() && this.maze[0].length < position3D.getRowIndex() &&
                0 <= position3D.getColumnIndex() && this.maze[0][0].length < position3D.getColumnIndex();
    }

}
