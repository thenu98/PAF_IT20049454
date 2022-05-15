package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Pay {
	private Connection connect()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//provide user-name and password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/test","root","");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("can not connect");
		}
		return con;	}
	
	public String insertPayment(String fullName, String NIC, String amount, String date, String bankName, String debitCard, String otpNumber)
	{
		String output ="";
		try {
			Connection con = connect();
			if(con == null) {
				return "can not connect to the database";}
				
				//create a prepared statement
				String query = "insert into payment(`payID`,`fullName`,`NIC`,`amount`,`date`,`bankName`,`debitCard`,`otpNumber`)"
				+" values (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(query);
				
				//binding values
				ps.setInt(1, 0);
				ps.setString(2, fullName);
				ps.setString(3, NIC);
				ps.setString(4, amount);
				ps.setString(5, date);
				ps.setString(6, bankName);
				ps.setString(7, debitCard);
				ps.setString(8, otpNumber);
				
				//execute the statement
				ps.execute();
				con.close();
				
				output="Data inserted successfully";
			
		}catch(Exception e) {
			e.printStackTrace();
			//output ="Insert operation failed. check again.";
		}
		return output;
	}

	
	public String readPayment() {
		String output="";
		try {
			Connection con = connect();
			if(con == null) {
				return "can not read";
			}
			output ="<table border='1'>"+
					"<tr><th>PayID</th><th>Full Name</th>"+
					"<th>NIC</th><th>Amount</th>"+
					"<th>Date</th><th>Bank Name</th>"+
					"<th>Debit Card</th><th>OTP</th></tr>";
			String query ="select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String payID = Integer.toString(rs.getInt("payID"));
				String fullName = rs.getString("fullName");
				String NIC = rs.getString("NIC");
				String amount = rs.getString("amount");
				String date = rs.getString("date");
				String bankName = rs.getString("bankName");
				String debitCard = rs.getString("debitCard");
				String otpNumber = rs.getString("otpNumber");
			
				//add values to html table
				output +="<tr><td><input id=\'hidProjectIDUpdate\' name=\'hidProjectIDUpdate\' type=\'hidden\' value=\'"+payID+"'></td>";
				output +="<td>"+fullName+"</td>";
				output +="<td>"+NIC+"</td>";
				output +="<td>"+amount+"</td>";
				output +="<td>"+date+"</td>";
				output +="<td>"+bankName+"</td>";
				output +="<td>"+debitCard+"</td>";
				output +="<td>"+otpNumber+"</td>";
				
				//buttons
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'</td>"
						+ "<td><form method='post' action='payment.jsp'>"
						+"<input name='btnRemove' type='button' value='Remove' class='btn btn-danger'>"
						+ "<input name='payID' type='hidden' value='"+payID 
						+"'>" + "</form></td></tr>";
			}
			con.close();
			//close html table
			output +="</table>";
		}
		catch(Exception e) {
			e.printStackTrace();
			output ="can not read please check your error.";
		}
		return output;
	}
	
	public String updatePayment(String payID, String fullName, String NIC, String amount, String date, String bankName, String debitCard, String otpNumber) {
		String output ="";
		try {
			Connection con = connect();
			if(con == null) {
				return "can not connect to the database";
			}
			String query ="update payment set fullName=?,NIC=?,amount=?,date=?,bankName=?,debitCard=?,otpNumber=? where payID=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, fullName);
			ps.setString(2, NIC);
			ps.setString(3, amount);
			ps.setString(4, date);
			ps.setString(5, bankName);
			ps.setString(6, debitCard);
			ps.setString(7, otpNumber);
			ps.setInt(8, Integer.parseInt(payID));
			
			ps.execute();
			con.close();
			
			output ="Updated successfully";
		}
		catch(Exception e) {
			output="update fail";
			e.printStackTrace();
		}
		return output;
	}
	public String deletePayment(String payID) {
		String output="";
		try {
			Connection con = connect();
			if(con == null) {
				return "can not connect to the database";
			}
			String query ="delete from payment where payID=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, Integer.parseInt(payID));
			ps.execute();
			con.close();
			output ="Deleted successfully";
		}
		catch(Exception e) {
			output ="can not delete, check error.";
			e.printStackTrace();
		}
		return output;
	}
}
