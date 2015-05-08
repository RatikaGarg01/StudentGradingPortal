

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpS
 */
@WebServlet("/SignUpS")
public class SignUpS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	    String l =request.getParameter("firstname");
	    String m =request.getParameter("lastname");      
	    String n =request.getParameter("email_id");  
	    String o =request.getParameter("password");  
	    String p =request.getParameter("branch"); 
	    String firstname =request.getParameter("firstname");
	    String lastname =request.getParameter("lastname");      
	    String email_id =request.getParameter("email_id");  
	    String password =request.getParameter("password");  
	    String branch =request.getParameter("branch");
	    
	    System.out.println(n);
	    System.out.println(p);
	    
	    if(l!="NULL" && m!="NULL" && n!="NULL"&& o!="NULL"&& p!="NULL")
	    {
	    try{  
	    	System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
	    Class.forName("com.mysql.jdbc.Driver");  
	    Connection con=DriverManager.getConnection(  "jdbc:mysql://127.0.0.1/automatedgradingsystem","root","root"
	    );  
	    System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");   
	    PreparedStatement ps=con.prepareStatement("insert into student_details(firstname, password, lastname,email_id,branch) "
	    	    + "values (?,?,?,?,?)");
	    	ps.setString(1, firstname);
	    	ps.setString(2, password);
	    	ps.setString(3, lastname);
	    	ps.setString(4, email_id);
	    	ps.setString(5, branch);
	    	
	    	
	    	ps.executeUpdate();
	    	int i = ps.executeUpdate(); 
	    	if(i!=0)
	    	{ 
	    	System.out.println("Record has been inserted"); 
	    	} 
	    	else
	    	{ 
	    		
	    	System.out.println("failed to insert the data"); 
	    	} 
	    }
	    catch(Exception e){System.out.println(e);
	    }
	    }
	    
	    else
	    {
	    	
	    	RequestDispatcher rd=request.getRequestDispatcher("student_home.jsp");  
	        rd.forward(request,response); 
	    	System.out.println("please enter all fields");
	    }
	    
	    
	    try{
		    if(  LoginDao.validate(n, o)){  
		        RequestDispatcher rd=request.getRequestDispatcher("student_home.jsp");  
		        rd.forward(request,response);  
		    }
		    
		    else{  
		        out.print("Sorry username or password error");  
		        RequestDispatcher rd=request.getRequestDispatcher("index.html");  
		        rd.include(request,response);  
		    }  
		          
		    out.close();
		    }
		    catch(Exception e){System.out.println(e);}
	    
	  
	}

}
