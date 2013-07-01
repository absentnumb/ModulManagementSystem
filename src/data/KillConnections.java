package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KillConnections {

	//schließt Verbindung zur Datenbank
	public void closeConnections(ResultSet rs, PreparedStatement pstmt) {

		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
	}
}
