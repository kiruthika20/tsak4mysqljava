package assignment.task4;

import java.io.*;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;

import java.sql.Timestamp;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Date;

public class MysqlJava {

	public static Connection getConnection() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqljava","root","mmmm");
    	return con;
	
}

	public static void createTable() throws Exception{
		try {
			Connection con=getConnection();
			String create =("CREATE TABLE IF NOT EXISTS myfile(id int PRIMARY KEY auto_increment, fileName varchar(200) not null,timestamp TIMESTAMP not null,vowelValue varchar(10) not null,word varchar(70) not null)");
			Statement st=con.createStatement();
			  st.execute(create);
			
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			System.out.println("Table created successfully");
		}
	}
	

public void readFile(String infName, String opfName) { 
	char[] vowels = {'a', 'e', 'i', 'o', 'u'};
	
try {
	
	Date date = new Date();
	Timestamp timestamp=new Timestamp(date.getTime());
	File myObj= new File(infName);
	FileWriter opobj= new FileWriter (opfName);
	Scanner myReader = new Scanner (myObj);
	
	Path path = Paths.get(infName);   // create object of Path
    
	Path fileName = path.getFileName(); // call getFileName() and get FileName path object
	String FILENAME = fileName.toString();  // storing  FileName to strinng
	   
	while (myReader. hasNextLine()) {
	
		String data = myReader.nextLine();
		String[] inputArray=data.split(" ");
		 for(String s:inputArray)
			 if(s.length()>1) 
			 {
				 
				 char t= s.charAt(1);
				 
				
		for(int i = 0;i<vowels.length; i++) 
			       
		{
			if(t==vowels[i]) {
				opobj.write(s +" "); 
				 Connection con=getConnection();
				  String query = " insert into myfile (fileName,timestamp,vowelValue,word)"
					        + " values ('"+FILENAME+"','"+timestamp+"','"+t+"','"+s+"')";
				  Statement st=con.createStatement();
				  st.execute(query);

				}
		}
	      }
	    }
	
	
		myReader.close(); 
		opobj.close();
	
	} catch (Exception e) { 
		System.out.println("An error occurred.");

	}
}




public static void main(String[] args) throws Exception { 
	createTable();
	MysqlJava v= new MysqlJava();
	v.readFile("C:\\Users\\krithika\\Desktop\\sampleInput.txt", "C:\\Users\\krithika\\Desktop\\sampleOutput.txt");

}
}