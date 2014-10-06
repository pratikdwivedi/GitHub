<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SignupActionPage</title>
    </head>
    <body>
        <h1>HI !</h1>
        <form id="form1" name="form1" method="post" action="SignUpAction.jsp" />
<%
            try {
                String username = request.getParameter("username");
                String mob = request.getParameter("mobno");
               // int mo=Integer.parseInt(mob);
                String email = request.getParameter("email");
                  Class.forName("com.mysql.jdbc.Driver");
                Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "mysql");
                Statement st = con1.createStatement();
               st.executeUpdate("create table signup(username varchar(100) not null unique,mobno integer ,email char(30))");
                st.executeUpdate("insert into signup values('"+username+"',"+mob+",'"+email+"')"); 
                 out.println("Data has been submitted.........");
                out.println("your username is ........."+username);
                out.println("& your ID is ........."+email);
               
                           }
            catch (Throwable e) {
                out.print(e);
            }


%>
</form>
    </body>
</html>
