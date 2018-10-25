package csc312;

public class cell {
	String value;
	String row;
	String column;
	Boolean isTraversed;
	
	public cell(String value, String row, String column, Boolean isTraversed) {
		this.value = value;
		this.row = row;
		this.column = column;
		this.isTraversed = isTraversed;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public Boolean getIsTraversed() {
		return isTraversed;
	}
	public void setIsTraversed(Boolean isTraversed) {
		this.isTraversed = isTraversed;
	}
}
