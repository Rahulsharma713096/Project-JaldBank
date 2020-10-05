package com.jald.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Model {
 private String name;
 private String custid;
 private int accno;
 private String pwd;
 private int bal;
 private String email;
private Connection con;
private PreparedStatement pstmt;
private int x;
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
@SuppressWarnings("finally")
public boolean register() {
  String s="insert into jaldbank values(?,?,?,?,?,?)";
	try {
		 pstmt = con.prepareStatement(s);
		 pstmt.setString(1,name);
		 pstmt.setString(2,custid);
		 pstmt.setInt(3,accno);
		 pstmt.setString(4,pwd);
		 pstmt.setInt(5,bal);
		 pstmt.setString(6,email);
		x = pstmt.executeUpdate();
		System.out.println("CustId:"+custid+" is Sucessfully Inserted");
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		if(x>0) return true;
		return false;
	}
	
	
}
 
}
