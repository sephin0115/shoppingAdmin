package Model;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class dataTableModel extends AbstractTableModel {
	
	protected String[] colName = null;
	protected Object[][] DataTable = null;		
	
	protected int rowCount = 0;
	protected int colCount = 0;
	
	public String[] getColName() {
		return colName;
	}
	public void setColName(String[] colName) {
		this.colName = colName;
	}
	public Object[][] getRowData() {
		return DataTable;
	}
	public void setRowData(Object[][] rowData) {
		this.DataTable = rowData;
	}
	
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	public int getColumnCount()
    {
        return colName.length;
    }

    public int getRowCount()
    {
        return rowCount;
    }

    public String getColumnName(int col)
    {
        return colName[col];
    }
    
    public int getColCount() {
		return colCount;
	}
    
	public void setColCount(int colCount) {
		this.colCount = colCount;
	}	
	
    /// cell 값 가져오기(AbstractTableModel.getValueAt 구현 
	public Object getValueAt(int row,int col)
	{
		return DataTable[row][col];
	}	

}
