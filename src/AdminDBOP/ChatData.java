/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminDBOP;

import cgc_admin_panel.DBDriver;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class ChatData {
     public boolean isDataStored(String filepath) {
        boolean flag = false;
        try {
            DBDriver dbd = new DBDriver();
            Statement st = dbd.getStatement();

            
            File file = new File(filepath);
            String filename = file.getName();

            
            String query = "CREATE TABLE IF NOT EXISTS stored_files (" + "filename VARCHAR(200), " + "filepath TEXT)";
            st.executeUpdate(query);

            
            String insert = "INSERT INTO stored_files (filename, filepath) " +  "VALUES ('" + filename + "', '" + filepath + "')";
            int rows = st.executeUpdate(insert);

            if (rows > 0)
                flag = true;

            st.close();
            dbd.conn.close();
        } catch (Exception e) {
            System.out.println("Error in isDataStored: " + e);
        }

        return flag;
    }
     
    public boolean isChatEdited(String sr_no, String question, String answer) {
        boolean flag = true;
        try {
            DBDriver dbd = new DBDriver();
            Statement st = dbd.getStatement();
            
                String query = "UPDATE chat_info SET question = '" + question + "', answer = '" + answer + "' WHERE sr_no = '" + sr_no + "'";
                System.out.println("Query : "+query);
                st.executeUpdate(query);
                st.close();
                
        } catch (Exception e) {
            flag=false;
            System.out.println("Exception at class ChatData in method isChatEdited() : " + e);
        }

        return flag;
    }

public String[][] getChatData() {
    String tablename="chat_info";
    int columncount=3;
    String table_data[][] = null;
    try {
        DBDriver dbd = new DBDriver();
        Statement st1 = dbd.getStatement();
        Statement st2 = dbd.getStatement();
        String query = "SELECT * FROM "+tablename;

        ResultSet rs1 = st1.executeQuery(query);
        ResultSet rs2 = st2.executeQuery(query);

        int rowcount = 0;
        while (rs1.next()) 
            rowcount++;
        

        table_data= new String [rowcount][columncount];
        int i = 0;
        while (rs2.next()) {
            for (int j = 0; j < columncount; j++) {
                table_data[i][j]=rs2.getString(j+1);
            }
            i++;
        
        }
    }
    catch (Exception e) {
        System.out.println("Exception at class ChatData in method getChatData()" +e);
    }
    return table_data;
}


public void loadTableData(JTable table) {
        String[][] data = getChatData();
        String[] columnNames = {"sr_no", "question", "answer"};
        table.setModel(new DefaultTableModel(data, columnNames));

    //table.setModel(new DefaultTableModel(getChatData(), new String[]{"sr_no", "question", "answer"}));   
}
}