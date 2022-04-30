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

    /**
     * C-tor // given explicit args
     * @param maze: maze grid- 1/0 grid represents a maze.
     * @param start: start pos // starting point of the maze.
     * @param  goal: goal pos // finish point of the maze.
     */
    public Maze(int[][] maze, Position start, Position goal){
        this.maze = maze;
        this.start = start;
        this.goal = goal;
    }

    public void initialize(){
        for (int i=0; i<this.getRows(); ++i){
            for (int j=0; j< this.getCols(); ++j){
                // transition => 0 equality (final).
                maze[i][j] = TRAN;
            }
        }
    }
    public int getRows()
    {
        return maze.length;
    }
    public int getCols()
    {
        return maze[0].length;
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
