package AdminDBOP;

import cgc_admin_panel.DBDriver;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class AdminDBOp {

    public boolean isAdminTableCreated() {
        boolean flag = false;
        try {
            DBDriver dbd = new DBDriver();
            Statement st = dbd.getStatement();
            String createQuery = "CREATE TABLE IF NOT EXISTS admin_info (" + "username VARCHAR(10) PRIMARY KEY, " + "password VARCHAR(10))";
            st.executeUpdate(createQuery);
            
            System.out.println("Admin table checked/created successfully.");
       
            if (isInserted()) 
            {
                flag = true;
                System.out.println("Admin credentials created successfully.");
            } 

        } catch (Exception e) {
            System.out.println("Error in creation of admin table. Please try again.");
            flag = false;
        }

        return flag;
    }

    private boolean isInserted() {
        boolean flag = false;
        try {
            DBDriver dbd = new DBDriver();
            Statement st = dbd.getStatement();

            
//            String checkQuery = "SELECT * FROM admin_info WHERE username='admin'";
//            ResultSet rs = st.executeQuery(checkQuery);
//
//            if (!rs.next()) 
//            {
                
                String insertQuery = "INSERT INTO admin_info(username, password) VALUES('admin', 'admin')";
                int rows = st.executeUpdate(insertQuery);
              if(rows > 0)
                  flag=true;
//            } else {
//                inserted = true;
//            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean isAdminExisted(String username, String password) {
        boolean exists = false;
        try {
            DBDriver dbd = new DBDriver();
            Statement st = dbd.getStatement();

            String query = "SELECT * FROM admin_info WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                exists = true;
            }

        } catch (Exception e) {
            System.out.println("Error checking admin credentials.");
        }
        return exists;
    }

 public ArrayList<String> getAdminCredentials() {
    ArrayList<String> admindata = new ArrayList<String>();
    try {
        String adminname = "admin";
        DBDriver dbd = new DBDriver();
        Statement st = dbd.getStatement();
        String query = "SELECT * FROM admin_info WHERE username = '" + adminname + "'";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            admindata.add(rs.getString("username"));  
            admindata.add(rs.getString("password"));  
        }
    } catch (Exception ex) {
        System.out.println("Exception at class AdminOP in method getAdminCredentials(): " + ex);
    }
    return admindata;
}

   public boolean isEdited(String username,String password)
{
boolean flag=false;
    try
    {
        DBDriver dbd=new DBDriver();
        Statement st=dbd.getStatement();
        //username,pass
        String query="Update admin_info set password='"+password+"' where username='"+username+"'";
         int x=st.executeUpdate(query);
            if(x>0)
            flag=true;
            st.close();
            dbd.conn.close();
            dbd.st.close();
 
    }
    catch(Exception ex)
    {
        System.out.println("Exception at class AdminOP and at method isEdited() is "+ex);
    }
return flag;
}

public boolean isChatInfoTableCreated() {
    boolean flag = false;
    try {
        DBDriver dbd = new DBDriver();
        Statement st = dbd.getStatement();
        
        String createQuery = "CREATE TABLE IF NOT EXISTS chat_info (" + "sr_no VARCHAR(10) PRIMARY KEY, " + "question VARCHAR(500), " + "answer VARCHAR(255))";
                             
        st.executeUpdate(createQuery);
        flag = true;
        System.out.println("Chat info table checked/created successfully.");

        st.close();
        dbd.conn.close();
    } catch (Exception e) {
        System.out.println("Error in creation of chat_info table. Please try again. " + e);
        flag = false;
    }

    return flag;
}


   

}



//inside admindbop class
//when clicking on admininfo button
//Function isAdminTableCreated()---create table if not exited and return boolean usand ps should be hardcoded.. use dbdriver
//if fuction return false then message error in creation of admin table,please try again
//inside this funtion if it is true then 
//if isInserted() call dbdriver (usernamae and passowrd=admin) if true then message admin credential are created sucesfully if false them message error while creating admin credential
//fucntion wihtout argument and with returning value  
//if both tables are true successfully admin credential are created


//when click on submit button it should call class admindbop inside that below fuction should be called 
//isAdminExisted(username,password)boolea and call class dbdriver if true open frame adminoperation frame
//When managing adminprofile
//funtion String getAdminData call db driver
//isAdminEdited(username,password) call dbdriver
//when clicking on chatinfo
//isChatInfoCreated() call dbdriver