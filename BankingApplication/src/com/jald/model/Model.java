package com.jald.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
	//Database Columns
 private String name;
 private String custid;
 private int accno;
 private String pwd;
 private int bal;
 private String email;
private Connection con;
private PreparedStatement pstmt;
private int x;
private ResultSet res;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCustid() {
	return custid;
}
public void setCustid(String custid) {
	this.custid = custid;
}
public int getAccno() {
	return accno;
}
public void setAccno(int accno) {
	this.accno = accno;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public int getBal() {
	return bal;
}
public void setBal(int bal) {
	this.bal = bal;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
//zero parametrize constructor with JDBE connection establish statements  
public Model() {
		try { System.out.println("Try To Connect DB");
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapplication","root","12345678");
		System.out.println("Driver loaded and connection is established");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
}
//This method used to register data in db
@SuppressWarnings("finally")
public boolean register() {
	//simple sql incomplete query
  String s="insert into jaldbank values(?,?,?,?,?,?)";
	try {
		//prepare statement used because query is incomplete
		 pstmt = con.prepareStatement(s);
		 pstmt.setString(1,name);
		 pstmt.setString(2,custid);
		 pstmt.setInt(3,accno);
		 pstmt.setString(4,pwd);
		 pstmt.setInt(5,bal);
		 pstmt.setString(6,email);
		//if the data successfully inserted the x value updated 1
		 x = pstmt.executeUpdate();
		System.out.println("CustId:"+custid+" is Sucessfully Inserted");
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		
		if(x>0) return true;
		return false;
	}
	
	
}
//check the email and password present in db if it is then update accno value 
public boolean logIn() {
	//simple sql incomplete query
	String s="select * from jaldbank where custid=? and password=?";
	try {
		//prepare statement used because query is incomplete
		 pstmt = con.prepareStatement(s);
		 pstmt.setString(1,custid);
		 pstmt.setString(2,pwd);
	        res = pstmt.executeQuery();
		System.out.println("CustId:"+custid+" is Sucessfully Checked inside Database");
		//if res having value of data then it enter in loop
		while(res.next()==true) {
			accno=res.getInt("accno");
			return true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}
 
}
