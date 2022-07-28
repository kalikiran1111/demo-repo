import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "BabyName", urlPatterns = "/BabyNames")
public class BabyName extends HttpServlet {
    Statement stmt;

    /**
     * doGet for the Servlet, which connects to the database, gets information from the form and displays results.
     * @param request the servlet request {@link HttpServletRequest}
     * @param response the servlet response {@link HttpServletResponse}
     * @throws IOException When execution goes wrong
     */
    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Initialize the Database
        Connection connection = null;
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "root1234");
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/babynames?serverTimezone=UTC", connectionProperties);
            System.out.println("Driver loaded");
            stmt = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Get the information from the html form
        String gender = request.getParameter("gender");
        String startsWith = request.getParameter("firstLetter");
        int year = Integer.parseInt(request.getParameter("year"));



        // Prepare the SQL query to be executed
        String queryString;
        List<String> resultList;
        if (gender.equals("M")) {
            // Boy Selection logic
            System.out.println("Boy selected, and the name should start with : " + startsWith);
        } else if (gender.equals("F")) {
            System.out.println("Girl selected, and the name should start with : " + startsWith);
        }
        // SELECT NAME FROM BABYNAME WHERE YEAR IS 'Y' AND GENDER IS "M" AND NAME STATING WITH A GIVEN CHARACTER 'C'
        queryString = "SELECT bname FROM babyname WHERE byear=" + year + " AND gender=" + "'" + gender + "'" + " AND bname LIKE" + "'" + startsWith + "%" + "'";


        // Execute and Display the results
        resultList = makeQuery(queryString);
        int count = 0;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        gender = gender.equals("M") ? "Boys" : "Girls";
        out.println("These details are for the " + gender + " for the given year of " + year + ", whose name is starting with the letter \"" + startsWith + "\" </br>");

        for (String name: resultList) {
            count++;
            out.println(count + ". " + name + "</br>");
        }

        System.out.println("Row count is :" + count); //final count of the total number of names displayed

    }

    /**
     * Executes the given query.
     *
     * @param queryString The SQL query to be executed.
     * @return {@link List} of {@link  String}s which is the list of babynames.
     */
    private List<String> makeQuery(String queryString) {
        try {
            ResultSet resultSet = stmt.executeQuery(queryString);
            List<String> results = new ArrayList<>();
            while (resultSet.next()) {
                String bname = resultSet.getString("bname");
                results.add(bname);
            }
            return results;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
