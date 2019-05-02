package Connect;

public class settingDb {
	private String strServerIP;
	private String strServerPort;
	
	private String strSqlConnect;
	private String connectionUrl;
	private String strDBName;
	private String strID;
	private String strPW;
	
	public void SettingServer()
	{
		this.connectionUrl = "jdbc:sqlserver://" + strServerIP + strServerPort + ";" +"databaseName="+strDBName+";";
	}
	
	public String getStrServerIP() {
		return strServerIP;
	}

	public void setStrServerIP(String strServerIP) {
		this.strServerIP = strServerIP;
	}

	public String getStrServerPort() {
		return strServerPort;
	}

	public void setStrServerPort(String strServerPort) {
		this.strServerPort = strServerPort;
	}

	public String getStrSqlConnect() {
		return strSqlConnect;
	}

	public void setStrSqlConnect(String strSqlConnect) {
		this.strSqlConnect = strSqlConnect;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public String getStrDBName() {
		return strDBName;
	}

	public void setStrDBName(String strDBName) {
		this.strDBName = strDBName;
	}

	public String getStrID() {
		return strID;
	}

	public void setStrID(String strID) {
		this.strID = strID;
	}

	public String getStrPW() {
		return strPW;
	}

	public void setStrPW(String strPW) {
		this.strPW = strPW;
	}
	
	
	
}
