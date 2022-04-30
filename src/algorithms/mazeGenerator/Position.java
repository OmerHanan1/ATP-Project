package algorithms.mazeGenerator;

public class Position {
    // row property
    private int row;
    // col property
    private int col;

    /**
     * C-tor // given row-col.
     * @param row: position's row. (represents by "i" in the grid).
     * @param col: position's col. (represents by "j" in the grid).
     */
    public Position(int row, int col){
        if (row < 0 || col < 0){
            // Prevent Illegal Argument Exceptions.
            this.row = 0;
            this.col = 0;
        }
        this.row = row;
        this.col = col;
    }

    /**
     * C-tor // copy constructor.
     * @param position: the "other" to copy.
     */
    public Position(Position position){
        if (position != null){
            this.row=position.row;
            this.col=position.col;
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) throws IllegalArgumentException{
        if (row < 0){
            throw new IllegalArgumentException("Row index must be greater or equal to 0");
        }
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        if (col<0){
            throw new IllegalArgumentException("Col index must be greater to equal to 0.");
        }
        this.col = col;
    }

    // equals
    // hash code

}
