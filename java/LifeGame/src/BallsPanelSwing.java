
import java.awt.Color;
import javax.swing.*;
import java.awt.Graphics;

/**
 * Classe contenente la griglia.
 * @author Stefano
 * @version 19.10.2020
 */
public class BallsPanelSwing extends JPanel{
     /**
     * Le righe della griglia.
     */
    public int rows = 12;
    
    /**
     * Le colonne della griglia.
     */
    public int cols = 8;

    /**
     * Lo stato delle celle della griglia.
     */
    private int[][] griglia;
    
    /**
     * Il margine minimo.
     */
    private int minMargin;
    
    /**
     * La larghezza della finestra.
     */
    private int windowWidth;
    
    /**
     * L'altezza della finestra.
     */
    private int windowHeight;
    
    /**
     * Lo smart painting attivo o inattivo.
     */
    private boolean sp = false;
    
    /**
     * Metodo costruttore di MyGrid.
     * @param windowWidth La larghezza della finestra
     * @param windowHeight L'altezza della finestra
     * @param minMargin Il margine minimo
     * @param rows le righe della griglia
     * @param cols le colonne della griglia
     */
    public BallsPanelSwing(int windowWidth, int windowHeight, int minMargin,
            int rows, int cols){
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.minMargin = minMargin;
        this.cols = cols;
        this.rows = rows;
        this.griglia = new int[rows][cols];
        for(int i = 0; i<rows;i++){
                for(int j = 0; j<cols;j++){
                    this.griglia[i][j] = 0; 
                }
            }
    }
    
    /**
     * Metodo per ricentrare la griglia.
     * @param windowWidth la larghezza della finestra
     * @param windowHeight l'altezza della finestra
     * @param minMargin il margine minimo
     */
    public void center(int windowWidth, int windowHeight, int minMargin){
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.minMargin = minMargin;
    }
    
    /**
     * Metodo per calcolare la larghezza di una cella.
     * @return la larghezza di una cella
     */
    public int getCellSize(){
        int pSX=(windowWidth - minMargin * 2) / this.cols;
        int pSY=(windowHeight - minMargin * 2) / this.rows;
        int l = pSX;
        if(pSX>pSY){
            l = pSY;
        }
        return l;
    }
    
   
    
    /**
     * Metodo per rimuovere le colonne.
     */
    public void removeCol(){
        if(this.cols>1){
            this.cols--;
            int[][] newGriglia = new int[rows][cols];
            for(int i = 0; i<rows;i++){
                for(int j = 0; j<cols;j++){
                    newGriglia[i][j] = this.griglia[i][j]; 
                }
            }
            this.griglia = newGriglia;
        }
    }
    
    /**
     * Metodo per aggiungere le colonne.
     */
    public void addCol(){
        this.cols++;
        int[][] newGriglia = new int[rows][cols];
        for(int i = 0; i<rows;i++){
            for(int j = 0; j<cols-1;j++){
                newGriglia[i][j] = this.griglia[i][j]; 
            }
        }
        this.griglia = newGriglia;
    }
    
    /**
     * Metodo per rimuovere le righe.
     */
    public void removeRow(){
        if(this.rows>1){
            this.rows--;
            int[][] newGriglia = new int[rows][cols];
            for(int i = 0; i<rows;i++){
                for(int j = 0; j<cols;j++){
                    newGriglia[i][j] = this.griglia[i][j]; 
                }
            }
            this.griglia = newGriglia;
        }
    }
    
    /**
     * Metodo per aggiungere le righe.
     */
    public void addRow(){
        this.rows++;
        int[][] newGriglia = new int[rows][cols];
        for(int i = 0; i<rows-1;i++){
            for(int j = 0; j<cols;j++){
                newGriglia[i][j] = this.griglia[i][j]; 
            }
        }
        this.griglia = newGriglia;
    }
    
    /**
     * Metodo per settare lo smart painting.
     */
    public void setSmart(){
        this.sp = !this.sp;
    }
    
    /**
     * Metodo che ritorna il margine.
     * @return il margine
     */
    public int getMargin(){
        return this.minMargin;
    }
    
    /**
     * Ritorna la cordinata x 0 della griglia.
     * @return la cordinata x 0 della griglia
     */
    public int getX(){
        return (windowWidth- getWidth())/2;
    }
    
    /**
     * Ritorna la cordinata y 0 della griglia.
     * @return la cordinata y 0 della griglia
     */
    public int getY(){
        return (windowHeight- getHeight())/2;
    }
    
    /**
     * Ritorna la larghezza della griglia in px.
     * @return la larghezza della griglia in px
     */
    public int getWidth() {
        return getCellSize() * this.cols;
    }
    
    /**
     * Ritorna l'altezza della griglia in px.
     * @return l'altezza della griglia in px
     */
    public int getHeight(){
        return getCellSize() * this.rows;
    }
    
    /**
     * Ritorna la colonna della griglia alla coordinata x.
     * @param x la coordinata x
     * @return la colonna della griglia alla coordinata x
     */
    public int getColAt(int x){
        if(x >= getX()&& x<getX()+getWidth()){
            return ((x-getX())/getCellSize());
        }else{
            return -1;
        }
    }
    
    /**
     * Ritorna la riga della griglia alla coordinata y.
     * @param y la coordinata y
     * @return la riga della griglia alla coordinata y
     */
    public int getRowAt(int y){
        if(y >= getY() && y<getY()+getHeight()){
            return ((y-getY())/getCellSize());
        }else{
            return -1;
        }
    }
    
    /**
     * Ritorna lo stato della cella alla coordinata x e y.
     * @param x la coordinata x
     * @param y la coordinata y
     * @return lo stato della cella alla coordinata x e y
     */
    public int getValueAt(int x, int y){
        if(y<this.cols&&x<this.rows&&y>=0&&x>=0){
            return griglia[x][y];
        }else{
            return 0;
        }
    }
   
     /**
     * Inverte lo stato della cella alla coordinata x e y.
     * @param x la coordinata x
     * @param y la coordinata y
     */
    public void toggleValueAt(int x, int y){
        if(this.griglia[x][y]==0||this.griglia[x][y]==2){
            this.griglia[x][y]=1;
        }else{
            this.griglia[x][y]=0;
        }
        updateS(x,y);
        updateA(x,y);
    }
    
    /**
     * Metodo che aggiorna le cellule attorno.
     * @param x la riga
     * @param y la colonna
     */
    public void updateA(int x, int y){
        if(x!=0){
            if(y!=0){
                updateS(x-1,y-1);
                updateS(x,y-1);
                if(x!=this.rows-1){
                    updateS(x+1,y-1);
                }
            }
            if(y!=this.cols-1){
                updateS(x-1,y+1);
                updateS(x,y+1);
                if(x!=this.rows-1){
                    updateS(x+1,y+1);
                }
            }
            if(x!=this.rows-1){
                updateS(x+1,y);
            }
            updateS(x-1,y);
        }else{
            if(y!=0){
                updateS(x,y-1);
                if(x!=this.rows-1){
                    updateS(x+1,y-1);
                }
            }
            if(y!=this.cols-1){
                updateS(x,y+1);
                if(x!=this.rows-1){
                    updateS(x+1,y+1);
                }
            }
            if(x!=this.rows-1){
                updateS(x+1,y);
            }
        }
    }
    
    /**
     * Metodo che conta quante cellule accese ci sono accanto.
     * @param riga la riga della cellula
     * @param colonna la colonna della cellula
     * @return il numero di cellule accese
     */
     public int countV(int riga, int colonna){
        int c = 0;
        if(riga != 0){
            if(colonna>0){
                if(this.griglia[riga-1][colonna-1] == 1 ||
                        this.griglia[riga-1][colonna-1] == 3){
                    c++;
                }
            }
            if(this.griglia[riga-1][colonna] == 1 ||
                    this.griglia[riga-1][colonna] == 3){
                c++;
            }
            if(colonna<cols-1){
                if(this.griglia[riga-1][colonna+1] == 1 ||
                        this.griglia[riga-1][colonna+1] == 3){
                    c++;
                }
            }
        }
        if(colonna>0){
            if(this.griglia[riga][colonna-1] == 1 ||
                    this.griglia[riga][colonna-1] == 3){
                c++;
            }
        }
        if(colonna<cols-1){
            if(this.griglia[riga][colonna+1] == 1 ||
                this.griglia[riga][colonna+1] == 3){
            c++; 
            }
        }
        if(riga < rows-1){
            if(colonna>0){
                if(this.griglia[riga+1][colonna-1] == 1 ||
                        this.griglia[riga+1][colonna-1] == 3){
                    c++;
                }
            }
            if(this.griglia[riga+1][colonna] == 1 ||
                    this.griglia[riga+1][colonna] == 3){
                c++;
            }
            if(colonna<cols-1){
                if(this.griglia[riga+1][colonna+1] == 1 ||
                        this.griglia[riga+1][colonna+1] == 3){
                    c++;
                }
            }
        }
        
        return c; 
    }
    
     /**
      * Fa l'upgrade di quando si cambia uno stato.
      * @param x la riga della cellula
      * @param y la colonna della cellula
      */
    public void updateS(int x, int y){
        if(countV(x,y)==3){
            if(this.griglia[x][y]==3){
                this.griglia[x][y] = 1;
            }else if(this.griglia[x][y]==0){
                this.griglia[x][y] = 2;
            }
        }else{
            if(this.griglia[x][y]==1||this.griglia[x][y]==3){
                if(countV(x,y)==2){
                    this.griglia[x][y] = 1;
                }else{
                    this.griglia[x][y] = 3;
                }
            }else if(this.griglia[x][y]==2){
                this.griglia[x][y] = 0;
            }
        }
    }
    
    /**
     * Aggiorna lo step.
     */
    public void update(){
        for(int i = 0; i<rows;i++){
            for(int j = 0; j<cols;j++){
                updateA(i,j);
            }
        }
        for(int i = 0; i<rows;i++){
            for(int j = 0; j<cols;j++){
                if(this.griglia[i][j]==2){
                    this.griglia[i][j]=1;
                }else if(this.griglia[i][j]==3){
                    this.griglia[i][j]=0;
                }
            }
        } 
        for(int i = 0; i<rows;i++){
            for(int j = 0; j<cols;j++){
                updateA(i,j);
            }
        }
    }
   
    /**
     * Esegue il random
     */
    public void rnd(){
        for(int i = 0; i<rows;i++){
            for(int j = 0; j<cols;j++){
                this.griglia[i][j]= (int)Math.round(Math.random());
                System.out.println(this.griglia[i][j]);
            }
        }
        for(int i = 0; i<rows;i++){
            for(int j = 0; j<cols;j++){
                updateA(i,j);
            }
        }
        
    }
    
    /**
     * Esegue il clear
     */
    public void clear(){
        for(int i = 0; i<rows;i++){
            for(int j = 0; j<cols;j++){
                this.griglia[i][j]= 0;
            }
        }
    }
    
    public void rp(int x, int y){
        int colI = (this.getX()+x*this.getCellSize());
        int rowI = (this.getY()+y*this.getCellSize());
        repaint(rowI,colI,this.getCellSize(),this.getCellSize());
    }
    
    /**
     * Metodo paint di MyGrid.
     * @param g 
     */
    public void paintComponent(Graphics g){
        if(sp){
            
        }else{
            g.setColor(Color.BLACK);
            g.fillRect(0,0, this.getWidth()+(getX()*2),
                    this.getHeight()+getY()*2);
            int l = getCellSize();
            for(int i = 0;i<this.rows;i++) {
                for(int j = 0;j<this.cols;j++){  
                    if(griglia[i][j]==1||griglia[i][j]==3){
                        g.setColor(Color.GREEN);
                        g.fillOval((getX()+l*j)+1,(getY()+l*i)+1, l-2, l-2);
                        if(griglia[i][j]==3){
                           g.setColor(Color.RED);
                           g.fillOval((getX()+l*j)+l*10/100,
                                   (getY()+l*i)+l*10/100, (l-2)*85/100,
                                   (l-2)*85/100); 
                        }
                    }else{
                        g.setColor(Color.RED);
                        g.fillOval((getX()+l*j)+1,(getY()+l*i)+1, l-2, l-2);
                        if(griglia[i][j]==2){
                           g.setColor(Color.GREEN);
                           g.fillOval((getX()+l*j)+l*10/100,
                                   (getY()+l*i)+l*10/100, (l-2)*85/100,
                                   (l-2)*85/100); 
                        } 
                    } 
                    g.setColor(Color.BLACK);
                    String num = "";
                    g.drawString(num+countV(i,j), (getX()+l*j)+l/2,
                            (getY()+l*i)+l/2);
                }
            }
        }
    }
}