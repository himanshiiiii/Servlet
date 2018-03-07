import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BlogPageServlet extends HttpServlet {


    public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
        HttpSession httpSession=req.getSession(false);

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        String blogId = req.getParameter("blogid");
        String blogContent = req.getParameter("blogcontent");
        if(httpSession!=null && httpSession.getAttribute("name")!=null) {

                if (blogId != null && blogContent != null) {


                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "mysql");
                        PreparedStatement ps = c.prepareStatement("insert into blogpage values(?,?)");
                        ps.setString(1, blogId);
                        ps.setString(2, blogContent);
                        ps.executeUpdate();

                        out.println("Data save successfuly...");

                    } catch (Exception e) {
                        out.println("Field Empty.UnSuccessful Submission");
                    }
                }
                else out.println("Blog Id And Blog Content has to be filled...");

        }
        else
            out.println("you have to login");




        out.println("</body></html>");
    }
}
