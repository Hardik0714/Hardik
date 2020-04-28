import  java.util.Scanner;
import java.sql.*;

class FirstExample{

    private static final  String url = "jdbc:mysql://localhost:3306/";

	  public static void main(String args[]) {

		Connection conn = null;
		Statement stmt = null;
    PreparedStatement pstmt = null;
    CallableStatement cstmt = null;
    Scanner in = new Scanner(System.in);

		try {
                        Class.forName("com.mysql.jdbc.Driver");
			System.out.println("\nConnecting to Database...");
			conn = DriverManager.getConnection(url,"yogesh","yogesh123");

			String sql;

            // 1) Create Database
            System.out.println("Creating Statement ....");
            stmt = conn.createStatement();
			      sql = "CREATE DATABASE CHILD";
            stmt.executeUpdate(sql);
            System.out.println("Database Created Sucessfully.\nEnter 1&press entr....!");
            int n=in.nextInt();

            // 2) Create Table
            sql = "CREATE TABLE ENROLLED_CHILD(ID INT, FIRSTNAME VARCHAR(25), LASTNAME VARCHAR(25), AADHAR BIGINT, STATUS BOOLEAN);";
            stmt.executeUpdate("USE CHILD");
            stmt.executeUpdate(sql);
            System.out.println("Table Created Successfully");

            //3) Insert Record Into Table Using Statement
            sql = "INSERT INTO ENROLLED_CHILD VALUES(1,'MUKUND','KULKARNI',123412341234,0);";
            stmt.executeUpdate(sql);
            System.out.println("\nInserted Sucessfully Using Statement.");

            //4) Insert Record Using Prepeared Statement
            System.out.println("Creating Prepared Statement ....");
            sql = "INSERT INTO ENROLLED_CHILD VALUES(?,?,?,?,?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,2);
            pstmt.setString(2,"AMAN");
            pstmt.setString(3,"JHURANI");
            pstmt.setInt(4,43214321);
            pstmt.setInt(5,0);
            pstmt.executeUpdate();
            System.out.println("Inserted Sucessfully Using Prepared Statement.");


            //5) Insert Record Using Callable Statement
            System.out.println("Creating Callable Statement ....");
            sql = "{CALL `Add`(?,?,?,?,?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1,3);
            cstmt.setString(2,"Pallav");
            cstmt.setString(3,"Patel");
            cstmt.setInt(4,43214321);
            cstmt.setInt(5,0);
            cstmt.executeUpdate();
            System.out.println("Inserted Sucessfully Using Callable Statement.");


            //6) Update Record Using Statement
            sql = "UPDATE `enrolled_child` SET `STATUS`= 1 WHERE `id` = 1";
            stmt.executeUpdate(sql);
            System.out.println("\nUpdated Sucessfully Using Statement.");

            //7) Update Record Using Prepeared Statement
            sql = "UPDATE `enrolled_child` SET `STATUS`= ? WHERE `id` = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,1);
            pstmt.setInt(2,2);
            pstmt.executeUpdate();
            System.out.println("Updated Sucessfully Using Prepared Statement.");

            //8) Update Record Using Callable Statement
            sql = "{CALL `Change`(?,?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1,3);
            cstmt.setInt(2,1);
            cstmt.executeUpdate();
            System.out.println("Updated Sucessfully Using Callable Statement.");


            //9) Delete  Record Using Statement
            sql = "DELETE FROM `enrolled_child` WHERE `id` = 1;";
            stmt.executeUpdate(sql);
            System.out.println("\nDeleted Sucessfully Using Statement.");

            //10) Delete Record Using Prepeared Statement
            sql = "DELETE FROM `enrolled_child` WHERE `id` = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,2);
            pstmt.executeUpdate();
            System.out.println("Deleted Sucessfully Using Prepared Statement.");

            //11) Delete Record Using Callable Statement
            sql = "{CALL `Remove`(?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1,3);
            cstmt.executeUpdate();
            System.out.println("Deleted Sucessfully Using Callable Statement.");


            //12) Delete Table
            sql = "DROP TABLE ENROLLED_CHILD;";
            stmt.executeUpdate(sql);
            System.out.println("\nTable Droped Sucessfully Using Statement.");

            //13) Delete Database
            sql = "DROP DATABASE CHILD";
            stmt.executeUpdate(sql);
            System.out.println("\nDatabase Deleted Sucessfully.");

			stmt.close();
      pstmt.close();
      cstmt.close();
      in.close();
			conn.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
