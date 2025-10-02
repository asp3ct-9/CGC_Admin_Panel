/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc_admin_panel;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author HP
 */
public class CGC_Admin_Panel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       AdminLoginFrame af= new AdminLoginFrame();
       Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
       af.setVisible(true);
       af.setSize(d);       
     
    }
    
}
