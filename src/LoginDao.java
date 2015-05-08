import java.sql.*;

  
public class LoginDao {  
public static boolean validate(String name,String pass){  
boolean status=false;  
System.out.println("^^^^^^^^^^^^^^"+name+"^^^^^^^^^^^^^^^^"); 
try{  
	System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  "jdbc:mysql://127.0.0.1/automatedgradingsystem","root","root"
);  
System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");   
PreparedStatement ps=con.prepareStatement(  
"select * from student_details where email_id=? and password=?");  
ps.setString(1,name);  
ps.setString(2,pass);

System.out.println("^^^^^^^^^^^^^^"+name); 

ResultSet rs=ps.executeQuery();  
status=rs.next();  
System.out.println(status);        
}
catch(Exception e){System.out.println(e);
}  
return status;   
}
       
}  