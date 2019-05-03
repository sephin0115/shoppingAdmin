package main;

import java.sql.ResultSet;
import java.sql.SQLException;


import bizConnect.connect.connectDb;
import bizConnect.dataType.dataTable;

public class dbTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		connectDb dbConn = new connectDb();
		try {
			dbConn.connectDB();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block	
			dbConn.close();
			e1.printStackTrace();
			
		}
		
		Object[] obj = new Object[1];
		obj[0] = "1000";
		ResultSet rs = null;
		ResultSet Qrs = null;
		boolean b_save = false;
		
		dataTable dt = new dataTable();
		dataTable dt2 = new dataTable();
		
		
		try {
			//select 프로시져 호출 예제
			rs = dbConn.excute("UP_ITB_JAVA_TEST_S", obj);
			while(rs.next()) {
				System.out.println(rs.getString("CD_COMPANY") + "|" + rs.getString("ID_USER") +"|" + rs.getString("PW"));				
			}
			
			dt.SetDataTable(rs);		
			
			for(int iRow = 0 ; iRow < dt.rowCount ; iRow++)
			{				
				for(int iCol = 0 ; iCol < dt.colCount ; iCol++)
				{
					System.out.println(dt.DataTable[iRow][iCol]);
				}
			}
			
			//select 쿼리 예제
			Qrs = dbConn.excuteQuery("SELECT * FROM ITB_JAVA_TEST");
			
			dt2.SetDataTable(Qrs);
			
			for(int iRow = 0 ; iRow < dt2.rowCount ; iRow++)
			{				
				for(int iCol = 0 ; iCol < dt2.colCount ; iCol++)
				{
					System.out.println(dt2.DataTable[iRow][iCol]);
				}
			}
			StringBuilder str = new StringBuilder();
			/*
			 * str.append("CREATE PROC P_CU_USER_INSERT_I\n"); str.
			 * append("( @P_CD_COMPANY NVARCHAR(10) , @P_ID_USER NVARCHAR(30) , @P_PW NVARCHAR(20)) AS \n"
			 * ); str.
			 * append("INSERT INTO ITB_JAVA_TEST (CD_COMPANY , ID_USER , PW) VALUES (@P_CD_COMPANY , @P_ID_USER , @P_PW) \n"
			 * );
			 */
			//P_CU_USER_INSERT_I
			Object[][] insertParam = new Object[2][3];
			for(int i = 0; i <2 ; i ++)
			{
				insertParam[i][0] = "1000";
				insertParam[i][1] = "20190502" + i;
				insertParam[i][2] = "1";				
			}
			
			//dbConn.excuteNonquery("exec P_CU_USER_INSERT_I '1000' , '2019505050' , '1'");
			System.out.println(str);
			//boolean b = dbConn.excuteNonquery(str.toString());
			boolean b = dbConn.excuteSave("P_CU_USER_INSERT_I", insertParam);
			System.out.println(b);
			
			//b_save = dbConn.excuteSave("", obj);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
	        if(rs != null){ try{ rs.close(); }catch(SQLException e){} }	  
	        dbConn.close();
	        //if(dbConn != null){ try{ dbConn.; }catch(SQLException e){} }
	    }	    	
	}

}
