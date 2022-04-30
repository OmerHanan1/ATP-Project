package algorithms.mazeGenerator;

/**
 * Maze class
 */
public class Maze {
    /**
     * Maze attributes
     */
    public static final int WALL =1;
    public static final int TRAN =0;
    private int[][] maze;
    private Position start;
    private Position goal;

    /**
     * C-tor // given row-col
     * @param rows: num of desire rows in maze.
     * @param columns: num of desire cols in maze.
     * @throws IllegalArgumentException in case of given args are illegal- out of bound.
     */
    public Maze(int rows, int columns){
        this.maze = new int[rows][columns];
        this.start = new Position(0,0);
        this.goal = new Position(rows-1, columns-1);
    }

    public int[][] getMaze() {
        return maze;
    }

    public Position getStart() {
        return start;
    }

    public Position getGoal() {
        return goal;
    }
}
