
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;

/**
 *
 * @author Stefano
 */
public class BallsPanel extends Panel implements MouseListener{
  
    static int ROWS = 100;
    
    static int COLS = 100;
    
    static int DIM = 10;

    static Color ON_COLOR = GREEN;
    
    static Color OFF_COLOR = RED;
    
    private boolean[][] pallini = new boolean[ROWS][COLS];
    
    private int[] xy = {0,0};
    
    private boolean primo = true;
    
    public void paint(Graphics g){
        long time = System.nanoTime();
        if(primo){
            for(int i = 0;i<ROWS;i++) {
                for(int j = 0;j<COLS;j++){
                    if(pallini[i][j]){
                        g.setColor(ON_COLOR);
                    }else{
                        g.setColor(OFF_COLOR);
                    }                    
                    g.fillOval(DIM*j, DIM*i, DIM, DIM);
                }
            }
        }else{
            if(pallini[xy[1]][xy[0]]){
                g.setColor(ON_COLOR);
            }else{
                g.setColor(OFF_COLOR);
            }
            g.fillOval(DIM*xy[0], DIM*xy[1], DIM, DIM);
        }
        System.out.println((System.nanoTime()-time));
    }
    
    public void mouseClicked(MouseEvent e) {
        int y = (int) Math.round(e.getY()/DIM);
        int x = (int) Math.round(e.getX()/DIM);
        pallini[y][x]= !pallini[y][x];
        this.xy[0]=x;
        this.xy[1]=y;
        this.primo = false;
        repaint(x*DIM, y*DIM, DIM, DIM);
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
    
    public BallsPanel(){
        this.addMouseListener(this);
    }
}
