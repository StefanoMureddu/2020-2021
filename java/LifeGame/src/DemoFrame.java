
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe Frame c he contiene tutto.
 * @author Stefano
 * @version 19.10.2020
 */
public class DemoFrame extends javax.swing.JFrame implements ActionListener {
    
    /**
     * Il pannello con la griglia.
     */
    private DemoPanel panel;

    /**
     * Il bottone per aggiungere le colonne.
     */
    private JButton colP;
    
    /**
     * Il bottone per rimuovere le colonne.
     */
    private JButton colM;

    /**
     * Il bottone per aggiungere le righe.
     */
    private JButton rigP;
    
    /**
     * Il bottone per rimuovere le righe.
     */
    private JButton rigM;
    
    /**
     * Il bottone per cambiare lo smart painting.
     */
    private JButton smart;
    
     /**
     * Il bottone per passare al prissimo step.
     */
    private JButton step;
    
     /**
     * Il bottone per dare uno stato casuale.
     */
    private JButton rnd;
    /**
     * Il bottone per cambiare il clipping.
     */
    private JButton clipping;
    
    /**
     * Il bottone per resettare.
     */
    private JButton clear;
    
    /**
     * Il bottone per cambiare il double buffered.
     */
    private JButton doubleB;
    
    /**
     * Il pannello sopra.
     */
    private JPanel NPanel;
    
    /**
     * Il pannello sotto.
     */
    private JPanel SPanel;
    
    /**
     * Il pannello a destra.
     */
    private JPanel EPanel;
    
    /**
     * Il pannello a sinistra.
     */
    private JPanel WPanel;
    
    /**
     * Il tempo di paint.
     */
    private JLabel tempo;

    
    /**
     * Creates new form DemoFrame
     */
    public DemoFrame() {
        initComponents();
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        panel = new DemoPanel(10,10);
        NPanel = new JPanel(new GridLayout(0,2));
        WPanel = new JPanel(new GridLayout(2,0));
        EPanel = new JPanel(new GridLayout(3,0));
        SPanel = new JPanel(new GridLayout(0,4));
        colP = new JButton("Aggiungi una colonna");
        colP.addActionListener(this);
        NPanel.add(colP);
        colM = new JButton("Rimuovi una colonna");
        colM.addActionListener(this);
        NPanel.add(colM);
        rigM = new JButton("Rimuovi una riga");
        rigM.addActionListener(this);
        rigP = new JButton("Aggiungi una riga");
        rigP.addActionListener(this);
        WPanel.add(rigM);
        WPanel.add(rigP);
        clipping = new JButton("Attiva/Disattiva il clipping");
        clipping.addActionListener(this);
        EPanel.add(clipping);
        smart = new JButton("Attiva/Disattiva lo smart painting");
        smart.addActionListener(this);
        EPanel.add(smart);
        doubleB = new JButton("Attiva/Disattiva il double buffering");
        doubleB.addActionListener(this);
        EPanel.add(doubleB);
        step = new JButton("step");
        step.addActionListener(this);
        rnd = new JButton("rnd");
        rnd.addActionListener(this);
        clear = new JButton("clear");
        clear.addActionListener(this);
        tempo = new JLabel("0");
        SPanel.add(tempo);
        SPanel.add(step);
        SPanel.add(rnd);
        SPanel.add(clear);
        this.add(SPanel,BorderLayout.SOUTH);
        this.add(NPanel,BorderLayout.NORTH);
        this.add(WPanel,BorderLayout.WEST);
        this.add(EPanel,BorderLayout.EAST);
        this.add(panel,BorderLayout.CENTER);
               
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DemoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DemoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DemoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DemoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DemoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.colM){
            panel.ballsRemoveCol();
        }else if(e.getSource()==this.colP){
            panel.ballsAddCol();
        }else if(e.getSource()==this.rigM){
            panel.ballsRemoveRow();
        }else if(e.getSource()==this.rigP){
            panel.ballsAddRow();
        }else if(e.getSource()==this.doubleB){
            panel.setDoubleB();
        }else if(e.getSource()==this.clipping){
            panel.setClipping();
        }else if(e.getSource()==this.smart){
            panel.setSP();
        }else if(e.getSource()==this.step){
            panel.updateP();
        }else if(e.getSource()==this.rnd){
            panel.random();
        }else if(e.getSource()==this.clear){
            panel.clr();
        }
        tempo.setText(""+panel.getTempo());
        repaint();
    }
}
