package DBController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.UserBean;

public class UserJoin {
	
	
	UserBean user;
	Connection conn = null;
	Statement stmt = null;	


		public void insert(String UserId , String UserPassword ,String UserName , 
				String UserAddress , String UserSchoolName , String UserGender, String UserAge,
				String UserPhone , String UserFmphone) {
		    StringBuilder sb = new StringBuilder();
		    
		    String sql = sb.append("insert into " + user + " values(")
		            .append(user.getUserId() + ",")
		            .append(user.getUserPassword() + "','")
		            .append(user.getUserName() + "','")
		            .append(user.getUserAddress() + "','")
		            .append(user.getUserSchoolName() + ",")
		            .append(user.getUserGender()+",")
		            .append(user.getUserAge() + ",")
		            .append(user.getUserPhone() + ",")
		            .append(user.getUserFmphone() + ",")
		            .append("');")
		            .toString();
		    try {
		        stmt.executeUpdate(sql);
		        System.out.println(sql);
		    } catch (SQLException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
    	}
	}
}
