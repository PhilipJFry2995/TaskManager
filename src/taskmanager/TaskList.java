package taskmanager;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.ListModel;
import javax.swing.ToolTipManager;

/**
 *
 * @author Dennis
 */
public class TaskList extends javax.swing.JFrame {

    private DefaultListModel model = new DefaultListModel();
    private Point position;
    private File file = new File("tasks.txt");
    private File settingsFile = new File("settings.ini");
    private static Point defaultLocation;
    private static boolean small;
    
    public TaskList() throws FileNotFoundException, IOException {

        initComponents();
        this.setLayout(null);

        if (file.exists()){
            String buffer;
            BufferedReader in = new BufferedReader(new FileReader(file));
            while((buffer = in.readLine()) != null){
                model.addElement(buffer);
            }
            jList1.setModel(model);
            in.close();
        }
        else {
            file.createNewFile();
        }
        
        if(settingsFile.exists()){
            String buffer;
            BufferedReader in = new BufferedReader(new FileReader(settingsFile));
            buffer = in.readLine();
            StringTokenizer st = new StringTokenizer(buffer, " ");
            defaultLocation = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            /*buffer = in.readLine();
            if(buffer.equals("true")){
                small = true;
                setSmallSize();
            }
            else {
                small = false;
                setBigSize();
            }*/
            in.close();
        }
        else {
            settingsFile.createNewFile();
        }
        
        jTextField1.requestFocusInWindow();
        TaskList taskList = this;
        /*System.out.println(defaultLocation);
        if(defaultLocation != null)
            taskList.setLocation(defaultLocation);
        else taskList.setLocationRelativeTo(null);
        */
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {                            // Позиция мыши
                position = e.getPoint();
                getComponentAt(position);
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {                       // Перетаскивание по экрану
            @Override
            public void mouseDragged(MouseEvent e) {
 
                int thisX = taskList.getLocation().x;
                int thisY = taskList.getLocation().y;
 
                int xMoved = (thisX + e.getX()) - (thisX + position.x);
                int yMoved = (thisY + e.getY()) - (thisY + position.y);
 
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                taskList.setLocation(X, Y);
            }
        });
        
        jButton5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                taskList.setState(JFrame.ICONIFIED);
                jTextField1.requestFocusInWindow();
            }
        });
        
        jList1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if("Delete".equals(e.getKeyText(e.getKeyCode()))){
                    if(jList1.getSelectedIndex() != -1){
                        int[] toDelete = jList1.getSelectedIndices();
                        for (int i = toDelete.length-1; i >= 0; i--){
                            model.removeElementAt(toDelete[i]);
                        }
                        jList1.setModel(model);
                        saveToFile();
                        jTextField1.requestFocusInWindow();
                    }
                }
            }
        });
        
        ToolTipManager.sharedInstance().registerComponent(jButton6);
        ToolTipManager.sharedInstance().registerComponent(jButton1);
        ToolTipManager.sharedInstance().registerComponent(jButton2);
        ToolTipManager.sharedInstance().registerComponent(jButton4);
        ToolTipManager.sharedInstance().setInitialDelay(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(228, 228, 228));
        setMinimumSize(new java.awt.Dimension(220, 290));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(241, 241, 241));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMinimumSize(new java.awt.Dimension(220, 290));
        jPanel1.setPreferredSize(new java.awt.Dimension(220, 290));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setToolTipText("Enter Task");
        jTextField1.setPreferredSize(new java.awt.Dimension(150, 30));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/add.jpg"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

        jScrollPane2.setViewportView(jList1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 220));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/remove.jpg"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Список задач");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 2, 140, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/exit.jpg"))); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(15, 15));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 0, -1, -1));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/clear.jpg"))); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 173, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/tray.jpg"))); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(15, 15));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 0, -1, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/help.jpg"))); // NOI18N
        jButton6.setToolTipText("<html>\n<p>Список задач v1.0\n<p>\n<p>Для добления записей нажмите зеленый плюсик или Enter\n<p>Для удаления выделенных записей нажмите минусик или Delete\n<p>Для удаления всех записей нажмите крестик\n<p>Окно откроется в том месте, где было закрыто\n<p>\n<p>Раработчик DFilyagin den@filyagin.com\n</html>");
        jButton6.setPreferredSize(new java.awt.Dimension(15, 15));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 0, -1, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/UpArrow.png"))); // NOI18N
        jButton7.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton7MouseExited(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 99, -1, -1));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/DownArrow.png"))); // NOI18N
        jButton8.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton8MouseExited(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 136, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTextField1.getText().length() != 0){
            model.addElement(jTextField1.getText());
            jList1.setModel(model);
            jTextField1.setText(null);
            jTextField1.requestFocusInWindow();
            saveToFile();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jList1.getSelectedIndex() != -1){
            int[] toDelete = jList1.getSelectedIndices();
            for (int i = toDelete.length-1; i >= 0; i--){
                model.removeElementAt(toDelete[i]);
            }
            jList1.setModel(model);
        }
        jTextField1.requestFocusInWindow();
        saveToFile();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        saveToFile();
        int x = this.getLocation().x;
        int y = this.getLocation().y;
        saveSettings(new Point (x, y));
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        model = new DefaultListModel();
        jList1.setModel(model);
        jTextField1.requestFocusInWindow();
        saveToFile();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        if (evt.getKeyChar() == '\n'){
            if(jTextField1.getText().length() != 0){
                    model.addElement(jTextField1.getText());
                    jList1.setModel(model);
                    jTextField1.setText(null);
                    jTextField1.requestFocusInWindow();
                    saveToFile();
            }
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/addActive.jpg")));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/add.jpg")));
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/removeActive.jpg")));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/remove.jpg")));
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/clearActive.jpg")));
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/clear.jpg")));
    }//GEN-LAST:event_jButton4MouseExited

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/trayActive.jpg")));
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/tray.jpg")));
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
       jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/helpActive.jpg")));
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/help.jpg")));
    }//GEN-LAST:event_jButton6MouseExited

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/exitActive.jpg")));
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/exit.jpg")));
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jTextField1.requestFocusInWindow();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/UpArrowActive.png")));
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/UpArrow.png")));
    }//GEN-LAST:event_jButton7MouseExited

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/DownArrowActive.png")));
    }//GEN-LAST:event_jButton8MouseEntered

    private void jButton8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseExited
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taskmanager/DownArrow.png")));
    }//GEN-LAST:event_jButton8MouseExited

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(jList1.getSelectedIndex() != -1){
            int selected = jList1.getSelectedIndex();
            if(selected != 0){
                Object moveUp = model.getElementAt(selected);
                model.removeElementAt(selected);
                model.add(selected - 1, moveUp);
            }
            jList1.setModel(model);
            jList1.setSelectedIndex(selected - 1);
        }
        jList1.requestFocusInWindow();
        //jTextField1.requestFocusInWindow();
        saveToFile();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(jList1.getSelectedIndex() != -1){
            int selected = jList1.getSelectedIndex();
            if(selected != model.getSize() - 1){
                Object moveDown = model.getElementAt(selected);
                model.removeElementAt(selected);
                model.add(selected + 1, moveDown);
            }
            jList1.setModel(model);
            jList1.setSelectedIndex(selected + 1);
        }
        jList1.requestFocusInWindow();

        //jTextField1.requestFocusInWindow();
        saveToFile();
    }//GEN-LAST:event_jButton8ActionPerformed
    /***************************
    КНОПКА УВЕЛИЧЕНИЯ/УМЕНЬШЕНИЯ
            if(small){
            small = false;
            setBigSize();
        }
        else{
            small = true;
            setSmallSize();
        }
    ***************************/
    
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
            java.util.logging.Logger.getLogger(TaskList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaskList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaskList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaskList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TaskList list;
                try {
                    list = new TaskList();
                    list.setVisible(true);
                    if(defaultLocation != null)
                        list.setLocation(defaultLocation);
                    else list.setLocationRelativeTo(null);
                } catch (IOException ex) {
                    Logger.getLogger(TaskList.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }
    
    private void saveToFile(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            ListModel model = jList1.getModel();
            for (int i = 0; i < model.getSize(); i++){
                out.write(model.getElementAt(i).toString() + "\n");
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(TaskList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void saveSettings(Point location){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(settingsFile));
            out.write(location.x + " " + location.y + "\n");
            out.write(small + "\n");
            out.close();
        }catch (IOException ex){
            Logger.getLogger(TaskList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    private void setBigSize(){
        this.setSize(440, 580);
        jPanel1.setBounds(0, 0, 440, 580);
        jButton5.setBounds(173 + 220, 0, jButton5.getWidth(), jButton5.getHeight());
        jButton6.setBounds(188 + 220, 0, jButton6.getWidth(), jButton6.getHeight());
        jButton3.setBounds(203 + 220, 0, jButton3.getWidth(), jButton3.getHeight());
        //jButton7.setBounds(173 + 220, 20, 45, 20);
        //jButton7.setText("<<");
        jButton4.setBounds(jButton4.getX() + 220, jButton4.getY() + 290, jButton4.getWidth(), jButton4.getHeight());
        jButton2.setBounds(jButton2.getX() + 220, jButton2.getY() + 290, jButton2.getWidth(), jButton2.getHeight());
        jButton1.setBounds(jButton1.getX() + 220, jButton1.getY() + 290, jButton1.getWidth(), jButton1.getHeight());
        jList1.setBounds(jList1.getX(), jList1.getY(), jList1.getWidth()*2, WIDTH);
        this.add(jButton5);
        this.add(jButton6);
        this.add(jButton3);
        //this.add(jButton7);
        this.add(jButton4);
        this.add(jButton2);
        this.add(jButton1);
        
        this.add(jPanel1);
    }
    
    private void setSmallSize(){
        this.setSize(220, 290);
        jPanel1.setBounds(0, 0, 220, 290);
        jButton5.setBounds(173, 0, jButton5.getWidth(), jButton5.getHeight());
        jButton6.setBounds(188, 0, jButton6.getWidth(), jButton6.getHeight());
        jButton3.setBounds(203, 0, jButton3.getWidth(), jButton3.getHeight());
        //jButton7.setBounds(173, 20, 45, 20);
        //jButton7.setText(">>");
        jButton4.setBounds(jButton4.getX() - 220, jButton4.getY() - 290, jButton4.getWidth(), jButton4.getHeight());
        jButton2.setBounds(jButton2.getX() - 220, jButton2.getY() - 290, jButton2.getWidth(), jButton2.getHeight());
        jButton1.setBounds(jButton1.getX() - 220, jButton1.getY() - 290, jButton1.getWidth(), jButton1.getHeight());
        
        this.add(jButton5);
        this.add(jButton6);
        this.add(jButton3);
        //this.add(jButton7);
        this.add(jButton4);
        this.add(jButton2);
        this.add(jButton1);
        
        this.add(jPanel1);
    }
    */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
