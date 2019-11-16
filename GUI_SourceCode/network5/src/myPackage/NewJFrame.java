/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JOptionPane;

/**
 *
 * @author USCS
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    Image img;
    Graphics2D gfx;
    int nodeSize = 50;
    int xc = 20, yc = 50;
    int id = 0;
    int select = -1;
    int current;
    int arr[][];
    int z[][];
    int sid, did;
    HashMap<Integer, Point> location;
    HashMap<Integer, HashSet> nodes;

    public NewJFrame() {
        initComponents();
        location = new HashMap<>();
        nodes = new HashMap<>();
        img = panel.createImage(panel.getWidth(), panel.getHeight());
        gfx = (Graphics2D) img.getGraphics();
    }

    public void draw() {
        for (int i = 0; i < nodes.size(); i++) { //draw edge
            Point ps = (Point) location.values().toArray()[i];  //source

            for (Integer dest : (HashSet<Integer>) nodes.values().toArray()[i]) {
                Point pd = location.get(dest); //destination
                gfx.setColor(Color.red);
                gfx.setStroke(new BasicStroke(3));
                gfx.drawLine(ps.x, ps.y, pd.x, pd.y);

            }

        }
        for (int i = 0; i < location.size(); i++) { //drew nodes
            if (i == current) {
                gfx.setColor(Color.yellow);
            } else {
                gfx.setColor(Color.blue);
            }
            Point p = (Point) location.values().toArray()[i];
            gfx.fillOval(p.x - (nodeSize / 2), p.y - (nodeSize / 2), nodeSize, nodeSize);
            gfx.setColor(Color.black);
            gfx.drawString("N" + i, p.x + (nodeSize / 2), p.y + (nodeSize / 2));

        }

        panel.getGraphics().drawImage(img, 0, 0, this);
        System.out.println(location);
        System.out.println(nodes);
    }
    public void readValue(){
        String temp = JOptionPane.showInputDialog(this, "what's Source Node number?", "Enter Source", JOptionPane.QUESTION_MESSAGE);
        sid = Integer.parseInt(temp);
        temp = JOptionPane.showInputDialog(this, "what's Destination Node number?", "Enter Destination", JOptionPane.QUESTION_MESSAGE);
        did = Integer.parseInt(temp);
    }

    public void calculate() {
        
        int rmin = sid;
        int min = arr[rmin][rmin];
        z[sid][0] = sid;
        z[sid][1] = 0;
        z[sid][2] = 1;

        do {
            for (int r = 0; r < nodes.size(); r++) {
                if ((z[r][2] == 0)
                        && (z[r][1] > (arr[rmin][r] + min))) {
                    z[r][1] = arr[rmin][r] + min;
                    z[r][0] = rmin;
                }
            }

            min = 10000;
            rmin = -1;
            for (int r = 0; r < nodes.size(); r++) {
                if ((z[r][2] == 0) && (z[r][1] < min)) {
                    min = z[r][1];
                    rmin = r;
                }
            }

            for (int r = 0; r < nodes.size(); r++) {
                System.out.print("\t" + r + "\t" + z[r][0] + "\t" + z[r][1] + "\t" + z[r][2] + "\n");
            }
            System.out.print("rmin= " + rmin + "\t min = " + min + "\n\n");

            z[rmin][2] = 1;
                
        } while (rmin != did);
        System.out.println("  Final Result ");
        int r = did;
        String s = Integer.toString(r);
        System.out.print("\t" + r);
        while (r != sid) {
            r = z[r][0];
            System.out.print("\t" + r);
            s =s+" <- "+r;
            
        }
        System.out.println("");
        JOptionPane.showMessageDialog(this, "The minimum cost ="+min, "MinimumCost", JOptionPane.DEFAULT_OPTION);
        JOptionPane.showMessageDialog(this, "The shortest path is: \n"+s, "ShortestPath", JOptionPane.DEFAULT_OPTION);

    }
    

    private int selectedNode(int xcoo, int ycoo) {
        for (int i = 0; i < location.size(); i++) {
            Point thepoint = (Point) location.values().toArray()[i];
            int deltaX = xcoo - (thepoint.x - nodeSize / 2);
            int deltaY = ycoo - (thepoint.y - nodeSize / 2);

            if (Math.sqrt(deltaX * deltaX + deltaY * deltaY) <= nodeSize + 6) {
                return (int) location.keySet().toArray()[i];
            }
        }
        return -1;
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs); //To change body of generated methods, choose Tools | Templates.
        draw();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        btnCost = new javax.swing.JButton();
        cbtnStart = new javax.swing.JCheckBox();
        btnPath = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelMouseDragged(evt);
            }
        });
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        btnCost.setText("show table");
        btnCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCostActionPerformed(evt);
            }
        });

        cbtnStart.setText("Start");
        cbtnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtnStartActionPerformed(evt);
            }
        });

        btnPath.setText("shortestPath");
        btnPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPathActionPerformed(evt);
            }
        });

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(cbtnStart))
                .addContainerGap(412, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(cbtnStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCost)
                    .addComponent(btnPath)
                    .addComponent(jButton1)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            current = select;
            draw();
        } else if (evt.getClickCount() == 2) {
            for (Integer dest : (HashSet<Integer>) nodes.values().toArray()[select]) {
                String temp = JOptionPane.showInputDialog(this, "Node" + select + "->" + "Node" + dest, "Enter Coast", JOptionPane.QUESTION_MESSAGE);
                int cost = Integer.parseInt(temp);
                arr[select][dest] = cost;
            }

        }
    }//GEN-LAST:event_panelMouseClicked

    private void panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMousePressed
        // TODO add your handling code here:
        select = selectedNode(evt.getX(), evt.getY());
        if (select == -1) {
            location.put(id, new Point(evt.getX(), evt.getY()));
            nodes.put(id, new HashSet());
            id++;
            draw();
        }
    }//GEN-LAST:event_panelMousePressed

    private void panelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseReleased
        // TODO add your handling code here:
        int destination = selectedNode(evt.getX(), evt.getY());
        if (destination != select && destination > -1 && select > -1) {
            nodes.get(destination).add(select);
            nodes.get(select).add(destination);
        }
        draw();
    }//GEN-LAST:event_panelMouseReleased

    private void panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseDragged
        // TODO add your handling code here:
//         
    }//GEN-LAST:event_panelMouseDragged

    private void btnCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCostActionPerformed
        // TODO add your handling code here:
        String t = "";
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
                if(arr[i][j]==100000){
                    t=t+"inf"+"   ";
                }
                else{
                    t = t + Integer.toString(arr[i][j]) + "   ";
                }
                

            }
            System.out.println("");
            t = t + "\n";
        }
        JOptionPane.showMessageDialog(this, t, "Cost Table", JOptionPane.DEFAULT_OPTION);
    }//GEN-LAST:event_btnCostActionPerformed

    private void cbtnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbtnStartActionPerformed
        // TODO add your handling code here:
        z = new int[nodes.size()][3];
        for (int r = 0; r < nodes.size(); r++) {
            z[r][0] = -1;
            z[r][1] = 100000;
            z[r][2] = 0;
        }

        arr = new int[nodes.size()][nodes.size()];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                arr[i][j] = 100000;
                if (i == j) {
                    arr[i][j] = 0;
                }
            }

        }
    }//GEN-LAST:event_cbtnStartActionPerformed

    private void btnPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPathActionPerformed
        // TODO add your handling code here:
        
        readValue();

        calculate();

    }//GEN-LAST:event_btnPathActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        z = new int[nodes.size()][3];
        for (int r = 0; r < nodes.size(); r++) {
            z[r][0] = -1;
            z[r][1] = 100000;
            z[r][2] = 0;
        }
        readValue();
        calculate();
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCost;
    private javax.swing.JButton btnPath;
    private javax.swing.JCheckBox cbtnStart;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
