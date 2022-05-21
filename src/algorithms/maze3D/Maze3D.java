package algorithms.maze3D;

public class Maze3D {
    /**
     * Maze attributes
     */
    public static final int WALL = 1;
    public static final int TRAN = 0;
    private int[][][] maze;
    private Position3D start;
    private Position3D goal;

    public Maze3D(int[][][] maze, Position3D s, Position3D g){
        this.maze = maze;
        this.start = s;
        this.goal = g;
    }

    public Maze3D(int depth, int row, int col) throws IllegalArgumentException{
        if (depth < 2 || row < 2 || col <2){throw new IllegalArgumentException();}
        this.maze = new int[depth][row][col];
        this.start = new Position3D(0,0,0);
        this.goal = new Position3D(depth-1, row-1, col-1);
    }

    public int[][][] getMap(){
        return this.maze;
    }
    public Position3D getStartPosition(){
        return this.start;
    }
    public Position3D getGoalPosition(){
        return this.goal;
    }
}
