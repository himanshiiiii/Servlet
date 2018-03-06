import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginSessionServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
    {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        out.println("<html><body>");
        String name=req.getParameter("name");
        String pass=req.getParameter("pass");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","mysql");
            Statement s=c.createStatement();
            String s1="select * from login where name='"+name+"'and pass='"+pass+"'";
            ResultSet rs=s.executeQuery(s1);
            if(rs.next()){
//                HttpSession se=req.getSession();
//                se.setAttribute("name",name);
//                se.setAttribute("pass",pass);
//
                res.sendRedirect("BlogPage.html");

            }
            else
                out.println("user is invalid");
        }
        catch(Exception e)
        {
            out.println(e);
        }
        out.println("</body></html>");
    }

}
