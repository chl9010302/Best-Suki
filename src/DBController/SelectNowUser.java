package DBController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import DBModel.UserBean;
import usingstaticfunction.DBConnectionKeeping;

public class SelectNowUser {

	public UserBean getSelectUser(String login_Id) {
		DBConnectionKeeping dbConnectionKeeping;
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		StringBuilder sb = new StringBuilder();
		String sql = sb.append("SELECT * FROM  USER WHERE").append(" UserId = ").append(login_Id).append(";")
				.toString();
		Connection con = usingstaticfunction.DBConnectionKeeping.con;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.print("UserId");
			System.out.print("\t");
			System.out.print("UserPassword");
			System.out.print("\t");
			System.out.print("UserName");
			System.out.print("\t");
			System.out.print("UserAddress");
			System.out.print("\t");
			System.out.print("UserSchoolName");
			System.out.print("\t");
			System.out.print("UserAge");
			System.out.print("\t");
			System.out.print("UserGender");
			System.out.print("\t");
			System.out.print("UserPhone");
			System.out.print("\t");
			System.out.print("UserFmphone");
			System.out.print("\t");
			System.out.print("UserLoginsession");
			System.out.print("\t");
			System.out.print("Isteacher");
			System.out.print("\t");
			System.out.print("Classnumber");
			System.out.print("\t");
			System.out.print("Classnumber_2");
			System.out.print("\t");
			System.out.print("Classnumber_4");
			System.out.print("\n");
			System.out.println("────────────────────────");
			UserBean userbean = new UserBean();
			while (rs.next()) {
				System.out.print(rs.getString("UserId"));
				System.out.print("\t");
				userbean.setUserId(rs.getString("UserId"));
				System.out.print(rs.getString("UserPassword"));
				System.out.print("\t");
				userbean.setUserPassword(rs.getString("UserPassword"));
				System.out.print(rs.getString("UserAddress"));
				System.out.print("\t");
				userbean.setUserName(rs.getString("UserName"));
				System.out.print(rs.getString("UserName"));
				System.out.print("\t");
				userbean.setUserAddress(rs.getString("UserAddress"));
				System.out.print(rs.getString("UserSchoolName"));
				System.out.print("\t");
				userbean.setUserSchoolName(rs.getString("UserSchoolName"));
				System.out.print(rs.getString("UserAge"));
				System.out.print("\t");
				userbean.setUserAge(rs.getString("UserAge"));
				System.out.print(rs.getString("UserGender"));
				System.out.print("\t");
				userbean.setUserGender(rs.getString("UserGender"));
				System.out.print(rs.getString("UserStudentnumber"));
				System.out.print("\t");
				userbean.setUserStudentnumber(rs.getString("UserStudentnumber"));
				System.out.print(rs.getString("UserPhone"));
				System.out.print("\t");
				userbean.setUserPhone(rs.getString("UserPhone"));
				System.out.print(rs.getString("UserFmphone"));
				System.out.print("\t");
				userbean.setUserFmphone(rs.getString("UserFmphone"));
				System.out.print(rs.getString("UserLoginsession"));
				System.out.print("\t");
				userbean.setUserLoginsession(rs.getString("UserLoginsession"));
				System.out.print(rs.getString("Isteacher"));
				System.out.print("\t");
				userbean.setIsteacher(rs.getString("Isteacher"));
				System.out.print(rs.getString("Classnumber"));
				System.out.print("\t");
				userbean.setClassnumber(rs.getString("Classnumber"));
				System.out.print(rs.getString("Classnumber_2"));
				System.out.print("\t");
				userbean.setClassnumber_2(rs.getString("Classnumber_2"));
				System.out.print(rs.getString("Classnumber_3"));
				System.out.print("\t");
				userbean.setClassnumber_3(rs.getString("Classnumber_3"));
				System.out.print(rs.getString("Classnumber_4"));
				System.out.print("\n");
				userbean.setClassnumber_2(rs.getString("Classnumber_4"));
			}
				return userbean;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}

		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
