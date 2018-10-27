package csc312;

public class Cell {
    private Character value;
	private int row;
	private int column;

    public Cell(Character value, int row, int column, boolean isTraversed) {
        this.value = value;
        this.row = row;
        this.column = column;
        this.isTraversed = isTraversed;
    }

    public Character getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isTraversed() {
        return isTraversed;
    }

    public void setTraversed(boolean traversed) {
        isTraversed = traversed;
    }

    private boolean isTraversed;
}
