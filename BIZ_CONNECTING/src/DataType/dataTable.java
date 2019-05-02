package DataType;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import Model.*;

@SuppressWarnings("serial")
public class dataTable extends dataTableModel {
	public int rowCount = 0;
	public int colCount = 0;
	
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getColCount() {
		return colCount;
	}
	public void setColCount(int colCount) {
		this.colCount = colCount;
	}
	
	public Object[] Row = new Object[rowCount];
	public Object[][] DataTable = new Object[rowCount][colCount];	
	

	public void SetCol(String ColName , int ColCount)
	{
		colName = new String[ColCount];
		for(int i = 1 ; i <ColCount ; i++)
		{			
			colName[i] = ColName;
		}
		System.out.println(colName);
	}
	
	public Object[][] SetDataTable(ResultSet _rs)
	{
		try {
			rs = _rs;
			rsmd = _rs.getMetaData();
			rs.last(); //커서의 위치를 제일 뒤로 이동
			rowCount = rs.getRow(); //현재 커서의 Row Index 값을 저장
			colCount = rsmd.getColumnCount();
			colName = new String[colCount];
			DataTable = new Object[rowCount][colCount];
			
			rs.beforeFirst();
			//rs.first(); //커서 위치를 첫행으로 변경
			int iRow =0;
			System.out.println("배열생성중");
			while(rs.next())
			{
				iRow = rs.getRow();
				for(int iCol = 0 ; iCol < colCount  ; iCol ++)
				{					
					DataTable[iRow-1][iCol] = rs.getObject(iCol+1);
				}
			}
			
			System.out.println(DataTable);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return DataTable;
	}
	
}
