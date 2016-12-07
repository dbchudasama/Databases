package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DataSourceDemo
 */
@WebServlet("/DataSourceDemo")
public class DataSourceDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Declaring data source
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataSourceDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// Getting connection for JNDI Data Source.
		// This is the preferred method to connect to a Database using the JNDI Datasource
		// as it allows us to set timeout, number of simultaneous connections to server etc
		// and returns back objects which are much easier to deal with.
		// Just modify the web.xml file of the project and the context.xml file in the Tomcat Apach
		// local server under 'Servers' Project.
		// FOR REFERENCE refer to points 2. and 3. at this URL:
		// https://tomcat.apache.org/tomcat-7.0-doc/jndi-datasource-examples-howto.html
		try {
			//This initial context can be used to look up the JNDI Datasource
			InitialContext initContext = new InitialContext();
			
			//This will return an object. "java:comp/env" is the standard. Don't know why but it works.
			//Very cryptic but works.
			Context env = (Context)initContext.lookup("java:comp/env");
			
			//Above context can be used to lookup Datasource. Will return an object of type DataSource
			ds = (DataSource)env.lookup("jdbc/nhsfevents");
			
		} catch (NamingException e) {
			throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServletException();
		}
		
		//Use Connection
		PrintWriter out = response.getWriter();
		
		out.println("Connected to database.");
		
		//Close Connection
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
