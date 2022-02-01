package chinese;
import java.util.Calendar;
import java.util.GregorianCalendar; 
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author michaeltham
 */
public class Home extends javax.swing.JFrame {
        
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        CurrentDate();
        
    }
    
    
    public void CurrentDate(){ 
        
        Thread clock = new Thread(){
            public void run(){ 
                try {
                    for(;;){
                    Calendar cal = new GregorianCalendar(); 
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR); 
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    date_txt. setText("Date:"+day+"/"+(month+1)+"/"+year);  

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE); 
                    int hour = cal.get(Calendar.HOUR);
                    time_txt. setText("Time:"+hour+":"+(minute)+":"+second); 
                    sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
       
        };
        
        clock.start();
    }
                
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jCheckBox1 = new javax.swing.JCheckBox();
        Search = new javax.swing.JButton();
        UploadText = new javax.swing.JButton();
        Welcome = new javax.swing.JLabel();
        date_txt = new javax.swing.JLabel();
        time_txt = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 380));
        getContentPane().setLayout(null);

        Search.setText("Search for Text/Quiz");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        getContentPane().add(Search);
        Search.setBounds(360, 140, 140, 104);

        UploadText.setText("Add New Text");
        UploadText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadTextActionPerformed(evt);
            }
        });
        getContentPane().add(UploadText);
        UploadText.setBounds(120, 140, 130, 104);

        Welcome.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        Welcome.setText("你好 , Qiong Wu !");
        getContentPane().add(Welcome);
        Welcome.setBounds(6, 6, 295, 43);

        date_txt.setText("Date:");
        getContentPane().add(date_txt);
        date_txt.setBounds(449, 6, 150, 16);

        time_txt.setText("Time: ");
        getContentPane().add(time_txt);
        time_txt.setBounds(450, 40, 150, 16);

        jLabel1.setIcon(new javax.swing.ImageIcon("/Users/michaeltham/Desktop/Layered_rainbow_gradient_from_photoshop_default_settings.jpg")); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 730, 420);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
        this.dispose(); 
        SearchFor obj = new SearchFor();
        obj.setVisible(true);
    }//GEN-LAST:event_SearchActionPerformed

    private void UploadTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadTextActionPerformed
        // TODO add your handling code here:
        this.dispose(); 
        FileUpload obj = new FileUpload(); 
        obj.setVisible(true); 
    }//GEN-LAST:event_UploadTextActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Search;
    private javax.swing.JButton UploadText;
    private javax.swing.JLabel Welcome;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel date_txt;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel time_txt;
    // End of variables declaration//GEN-END:variables

   
}