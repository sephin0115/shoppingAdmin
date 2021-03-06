package bizConnect.connect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectDb {
	Connection con = null;
	Statement stmt = null;
	CallableStatement ctmt = null;
	ResultSet rs = null;
	settingDb newDB = new settingDb();	
	//DB 연결
	public void connectDB() throws SQLException
	{					
		newDB.setStrServerIP("61.251.180.143");
		newDB.setStrServerPort("");
		newDB.setStrID("NEOE");
		newDB.setStrPW("NEOE");
		newDB.setStrDBName("NEOE");
		newDB.SettingServer();		
		System.out.println(newDB.getConnectionUrl());		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver Okay");
			con = DriverManager.getConnection(newDB.getConnectionUrl(),newDB.getStrID() , newDB.getStrPW());
			System.out.println("Connection Made");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.close();
		}
		finally {
			
		}
	}
	
	//프로시져 호출 
	//conn.prepareCall("{call db명.프로시저명(?,?,?)}");
	public ResultSet excute(String strProc , Object[] obj) throws SQLException
	{
		try {
			String str = "";
			str = "{call " +newDB.getStrDBName() +"."+strProc+"(";
			
			// 동적으로 매개변수 선언! obj배열의 수만큼!
			for (int i = 0 ; i < obj.length ; i++)
			{
				str = str + "?,";
			}
			str = str.substring(0, str.length() - 1);
			str = str + ")}";
			
			ctmt = con.prepareCall(str,ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_READ_ONLY );
			// 매개변수 매핑
			for (int i = 0 ; i < obj.length ; i++)
			{
				ctmt.setObject(i + 1, obj[i]);
			}
			System.out.println(str);
			
			
			ctmt.execute();
			rs = ctmt.getResultSet();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 			
		
		return rs;	
			
	}
	
	public ResultSet excuteQuery(String _sql) throws SQLException
	{
		try {
			String sql = _sql;
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;		
	}
	
	public boolean excuteSave(String strProc , Object[][] obj)
	{
		String str = "";
		boolean b_save = false;
		str = "{call " +newDB.getStrDBName() +"."+strProc+"(";
		try {
			// 동적으로 매개변수 선언! obj배열의 수만큼!
			Object[] row = null;
			
			// 첫행만 보고 str을 만들어 주면된다.
			row = obj[0];
			for (int colIdx = 0 ; colIdx < row.length ; colIdx++)
			{					
				str = str + "?,";
			}		
			
			str = str.substring(0, str.length() - 1);
			str = str + ")}";
			//ctmt = con.prepareCall(str,ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_READ_ONLY);
			ctmt = con.prepareCall(str);
			
			
			// 매개변수 매핑
			// 2차원 배열이라 좀 힘든데 이걸 어찌해야되나...
			
			
			for(int rowIdx = 0 ; rowIdx < obj.length ; rowIdx++)
			{
				row = obj[rowIdx];
				for(int colIdx = 0; colIdx < row.length ; colIdx ++)
				{
					System.out.println(obj[rowIdx][colIdx]);
					System.out.println(row[colIdx]);
					System.out.println(rowIdx + 1);
					ctmt.setObject(colIdx + 1, row[colIdx]);					
				}
				System.out.println(str);
				b_save = ctmt.execute();
				
				System.out.println("db result : "+b_save);

			}
			
			
			System.out.println(str);
			//b_save = ctmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			b_save = false;
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean excuteNonquery(String strQuery)
	{
		boolean bSuccess = false;
		
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_READ_ONLY);
			bSuccess = stmt.execute(strQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return bSuccess;
	}
	
	public void close() throws SQLException
	{
		this.con.close();
		this.ctmt.close();
		this.rs.close();	
	}
}
