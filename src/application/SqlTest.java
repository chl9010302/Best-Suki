package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class SqlTest {
    Connection conn = null;
    Statement stmt = null;
    String table;
 
    public SqlTest(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
        try {
            this.stmt = conn.createStatement();
            insert(23,"gohome", "Y");
            select(23);
            update(23,"do not go home","N");
            select(23);
            delete(23);
            selectAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    // 삽입
    public void insert(int id, String title, String flag) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("insert into " + table + " values(")
                .append(id + ",")
                .append("'" + title + "','")
                .append(flag)
                .append("');")
                .toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 

    // 삭제
    public void delete(int id) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("delete from " + table + " where id = ")
                .append(id)
                .append(";")
                .toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    // 수정
    public void update(int id, String title, String flag) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("update " + table + " set")
                .append(" title = ")
                .append("'" + title + "',")
                .append(" flag = '")
                .append(flag)
                .append("' where id = ")
                .append(id)
                .append(";").toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    // 모든 검색
    public void selectAll() {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from " + table)
                .append(";").toString(); 
        try {
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.print("id");
            System.out.print("\t");
            System.out.print("title");
            System.out.print("\t");
            System.out.print("flag");
            System.out.print("\n");
            System.out.println("────────────────────────");
            
              while(rs.next()){
                     System.out.print(rs.getInt("id"));
                     System.out.print("\t");
                     System.out.print(rs.getString("title"));
                     System.out.print("\t");
                     System.out.print(rs.getString("flag"));
                     System.out.print("\n");
                }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // 검색
    public void select(int id){
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from " + table + " where")
                .append(" id = ")
                .append(id)
                .append(";").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.print("id");
            System.out.print("\t");
            System.out.print("title");
            System.out.print("\t");
            System.out.print("flag");
            System.out.print("\n");
            System.out.println("────────────────────────");
            
              while(rs.next()){
                     System.out.print(rs.getInt("id"));
                     System.out.print("\t");
                     System.out.print(rs.getString("title"));
                     System.out.print("\t");
                     System.out.print(rs.getString("flag"));
                     System.out.print("\n");
                }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}