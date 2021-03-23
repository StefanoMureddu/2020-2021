
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * Classe con il pannello contenente la griglia.
 * @author Stefano
 * @version 19.10.2020
 */
public class DemoPanel extends JPanel implements MouseListener{
     /**
     * Il margine minimo.
     */
    static int MINMARGIN = 60;
    
    /**
     * Il clipping attivo o inattivo.
     */
    private boolean clipping = false;
    
    /**
     * La griglia.
     */
    private BallsPanelSwing ballsPanel;
    
    /**
     * Il tempo.
     */
    private long tI = 0;
        
    /**
     * Il metodo costruttore
     * @param rows le righe della griglia
     * @param cols le colonne della griglia
     */
    public DemoPanel(int rows, int cols){ 
        this.ballsPanel = new BallsPanelSwing(1000,1000,MINMARGIN ,rows,cols);
        this.addMouseListener(this);
    }
    
    /**
     * Metodo per rimuovere le colonne.
     */
    public void ballsRemoveCol(){
        ballsPanel.removeCol();
    }
    
    /**
     * Metodo per aggiungere le colonne.
     */
    public void ballsAddCol(){
        ballsPanel.addCol();
    }
    
    /**
     * Metodo per aggiungere le righe.
     */
    public void ballsAddRow(){
        ballsPanel.addRow();
    }
      
    /**
     * Metodo per rimuovere le righe.
     */
    public void ballsRemoveRow(){
        ballsPanel.removeRow();
    }
        
    /**
     * Metodo paint.
     * @param g 
     */
    public void paintComponent(Graphics g){
        long time = System.nanoTime();
        super.paintComponent(g);
        ballsPanel.center(this.getWidth(), this.getHeight(), 10);
        ballsPanel.paintComponent(g);
        this.tI = System.nanoTime()-time;
    }
    
    /**
     * Getter per il tempo di paint.
     * @return il tempo di paint
     */
    public long getTempo(){
        return this.tI;
    }

    /**
     * Esegue lo step.
     */
    public void updateP(){
        ballsPanel.update();
    }
    
    /**
     * Esegue il random.
     */
    public void random(){
        ballsPanel.rnd();
        repaint();
    }
    
    /**
     * Esegue il clear.
     */
    public void clr(){
        ballsPanel.clear();
        repaint();
    }
    
    /**
     * Metodo per settare il double buffered.
     */
    public void setDoubleB(){
        this.setDoubleBuffered(!this.isDoubleBuffered());
    }

    /**
     * Metodo per settare il clipping.
     */
    public void setClipping(){
        this.clipping = !this.clipping;
    }
    
    /**
     * Metodo per settare lo smart painting.
     */
    public void setSP(){
        ballsPanel.setSmart();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int y = ballsPanel.getColAt(e.getX());
        int x = ballsPanel.getRowAt(e.getY());
        ballsPanel.toggleValueAt(x, y);
        if(clipping){
        ballsPanel.rp(x,y);
        }else{
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}