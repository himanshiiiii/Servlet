import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BlogPageServlet extends HttpServlet {


    public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        String blogId = req.getParameter("blogid");
        String blogContent = req.getParameter("blogcontent");


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





        out.println("</body></html>");
    }
}
