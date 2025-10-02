
package cgc_admin_panel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBDriver {
      public  Statement st=null;
     public   Connection conn=null;
   public Statement getStatement()
   {
     
        try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cgc_chatbot","root","root");
                st= conn.createStatement() ;    
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class DBDriver and function getStaement()");
        }
        return st;
    }
}
