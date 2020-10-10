package com.jald.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class Model {
	//Database Columns
 private String name;
 private String custid;
 private int accno;
 private String pwd;
 private int bal;
 private String email;
 private int raccno;
 
public int getRaccno() {
	return raccno;
}
public void setRaccno(int raccno) {
	this.raccno = raccno;
}
private Connection con;
private PreparedStatement pstmt;
private int x;
private ResultSet res;
private PreparedStatement prepareStatement;

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
public boolean checkBalance() {
	String s="select balance from jaldbank where accno=?";
	 try {
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1,accno);
	        res = pstmt.executeQuery();
	      //if res having value of data then it enter in loop
			while(res.next()==true) {
				bal=res.getInt("balance");
				System.out.println(bal);
				return true;
			}   
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	return false;
}
public boolean changePassword() {
	
	String s="update jaldbank set password=? where accno=?";
	 try {
		pstmt = con.prepareStatement(s);
		pstmt.setString(1,pwd);
		pstmt.setInt(2,accno);
	        int x = pstmt.executeUpdate();
	      if(x>0) return true;
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
	 
	return false;
}
public boolean transfer() throws SQLException {
	
    String s1="select * from jaldbank where accno=?";
           pstmt= con.prepareStatement(s1);
           pstmt.setInt(1,raccno);
           ResultSet res1 = pstmt.executeQuery();
         while(res1.next()==true) {
    	String s2="update jaldbank set balance=balance-? where accno=?";
    	 prepareStatement = con.prepareStatement(s2);
    	 prepareStatement.setInt(1,bal);
    	 prepareStatement.setInt(2,accno);
    	 int x = prepareStatement.executeUpdate();
    	 System.out.println("balance deducted from"+accno);
   try {
    	 if(x>0) {
    		 int bal1 = res1.getInt("balance");
    		 System.out.println(bal1);
    		 if(bal1>0&&bal1>bal) {
    			 String s3="update jaldbank set balance=balance+? where accno=?";
    	    	 prepareStatement = con.prepareStatement(s3);
    	    	 prepareStatement.setInt(1,bal);
    	    	 prepareStatement.setInt(2,raccno);
    	    	 int x1 = prepareStatement.executeUpdate();
    	    	 System.out.println("balance sucessfully inserted to reciver account"+raccno);
   try {
    	  	 if(x1>0) {
    	    		 String s4="insert into getstatement values(?,?,?)";
    	    		 prepareStatement = con.prepareStatement(s4);
    	    		 prepareStatement.setInt(1,accno);
    	    		 prepareStatement.setInt(2,raccno);
    	    		 prepareStatement.setInt(3,bal);
    	    		 int executeUpdate = prepareStatement.executeUpdate();
    	    		 if(executeUpdate>0) {
    	    			 System.out.println("Balance transfer sucessfully");
    	    			 return true;
    	    		 }else {
    	    			 //Get statement coudn't be update 
    	    			 System.out.println("ststment coudn't be updated");
    	    			 }
  
    	    	 }
                else {
    	    		//balance not update to receiver account
    	    		 System.out.println("balance not update to reciver account");
    	    		 String s5="update jaldbank set balance=balance+? where accno=?";
        	    	 prepareStatement = con.prepareStatement(s5);
        	    	 prepareStatement.setInt(1,bal);
        	    	 prepareStatement.setInt(2,accno);
        	    	 int x2 = prepareStatement.executeUpdate();
        	    	 if(x2>0)System.out.println("balance return to account");
        			 return false; 
    	    	 }}catch(Exception e) {
    	    		 return true;
    	    	 }
    		 }
    		 else {
    			 //balance either lessthan 0 or less than sending amount
	    		 System.out.println("balance either lessthan 0 or less than sending amount");
    			 String s5="update jaldbank set balance=balance+? where accno=?";
    	    	 prepareStatement = con.prepareStatement(s5);
    	    	 prepareStatement.setInt(1,bal);
    	    	 prepareStatement.setInt(2,accno);
    	    	 int x2 = prepareStatement.executeUpdate();
    	    	 if(x2>0)System.out.println("balance return to account");
    			 return false; 
    		 }
    	 }
   }catch(Exception e){
		//some problem occur after execution of s2 SQL query
		 System.out.println("some problem occur after execution of s2 SQL query");
		 String s5="update jaldbank set balance=balance+? where accno=?";
  	 prepareStatement = con.prepareStatement(s5);
  	 prepareStatement.setInt(1,bal);
  	 prepareStatement.setInt(2,accno);
  	 int x2 = prepareStatement.executeUpdate();
  	 if(x2>0)System.out.println("balance return to account");
		 return false; 
	}
    }
	return false;
}
@SuppressWarnings("rawtypes")
public Map Statementdata=new TreeMap<>();
@SuppressWarnings({ "rawtypes", "unchecked" })
public Map getStatement() {
   String s1="select * from getstatement where accno=?";
   String s2="select * from getstatement where raccno=?";
   
   try {
	   //For Amount sending transaction
	pstmt = con.prepareStatement(s1);
	pstmt.setInt(1,accno);
	  res= pstmt.executeQuery();
	  int x=1;
	while(res.next()==true) {
		Statementdata.put("debit:"+x++,res.getInt("amount"));
	}
	//For Amount Receiving transaction
	pstmt = con.prepareStatement(s2);
	pstmt.setInt(1,accno);
	  res= pstmt.executeQuery();
	  x=1;
	while(res.next()==true) {
		Statementdata.put("credit:"+x++,res.getInt("amount"));
	}
	
} catch (SQLException e) {
	e.printStackTrace();
}
	return Statementdata;
}
public boolean applyLoan() throws SQLException {
	 String s="select * from jaldbank where accno=?";
	 pstmt = con.prepareStatement(s);
		pstmt.setInt(1,accno);
		  res= pstmt.executeQuery();
		  while(res.next()==true) {
			name=  res.getString("name");
			email=res.getString("email");
			return true;
		  }
	return false;
}
public void sendLoanData(String loantype) throws SQLException {
	String s="select * from jaldbank where accno=?";
	 pstmt = con.prepareStatement(s);
		pstmt.setInt(1,accno);
		  res= pstmt.executeQuery();
		  while(res.next()==true) {
			name=  res.getString("name");
			email=res.getString("email");
		  }
	String s1="insert into loandata values(?,?,?,?)";
	 pstmt = con.prepareStatement(s1);
		pstmt.setInt(1,accno);
		pstmt.setString(2,loantype);
		pstmt.setString(3,name);
		pstmt.setString(4,email);
		int x = pstmt.executeUpdate();
		if(x>0) System.out.println("Loan Data inserted");

}
 
}
