package algorithms.mazeGenerators;

import java.util.Objects;

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

    public int getRowIndex() {
        return row;
    }

    public void setRow(int row) throws IllegalArgumentException{
        if (row < 0){
            throw new IllegalArgumentException("Row index must be greater or equal to 0");
        }
        this.row = row;
    }

    public int getColumnIndex() {
        return col;
    }

    public void setCol(int col) {
        if (col<0){
            throw new IllegalArgumentException("Col index must be greater to equal to 0.");
        }
        this.col = col;
    }

    // Get the relevant position neighbors: Up/Down/Right/Left.
    public Position Up(){
        // i-1
        return new Position(this.row-1, this.col);
    }
    public Position Down(){
        // i+1
        return new Position(this.row+1, this.col);
    }
    public Position Right(){
        // j+1
        return new Position(this.row, this.col+1);
    }
    public Position Left(){
        // j-1
        return new Position(this.row, this.col-1);
    }

    @Override
    public String toString() {
        return "{" + this.row + "," + this.col + '}';
    }

    // Equals & HashCode templates (overrides the Object class functions).
    // Provides position equality options.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row &&
                col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
