package algorithms.mazeGenerator;

public class Position {
    // row property
    private int row;
    // col property
    private int col;

    public Position(int row, int col){
        if (row < 0 || col < 0){
            // Prevent Illegal Argument Exceptions.
            this.row = 0;
            this.col = 0;
        }
        this.row = row;
        this.col = col;
    }
    // Getters & Setters
    // equals
    // hash code

}
