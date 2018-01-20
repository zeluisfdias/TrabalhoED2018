/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import javax.swing.GroupLayout;
import recursos.interfaces.IFormigueiro;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import recursos.interfaces.IComida;
import recursos.interfaces.IFormiga;
import recursos.interfaces.IFormigueiro;
import recursos.interfaces.IPair;
import recursos.interfaces.IProcessamento;
import recursos.interfaces.ISala;
import recursos.interfaces.ISilo;
import recursos.interfaces.ITunel;
import recursos.interfaces.collections.UnorderedListADT;

/**
 *
 * @author Fábio Rêgo
 */
public class FormigueiroViewer extends recursos.utils.FormigueiroViewer{

 
 
/* public static void main(String[] args) {


 // private IFormigueiro f;
  //private FormigueiroGUI gui;
  
  public FormigueiroViewer(IFormigueiro f)
  {
    this.f = f;
    
    this.gui = new FormigueiroGUI(f, f.getMaxX() + 100, f.getMaxY() + 100, null);
    JFrame frame = new JFrame("FormigueiroViewer");
    frame.setDefaultCloseOperation(3);
    frame.add(this.gui);
    frame.setSize(f.getMaxX() + 100, f.getMaxY() + 100);
    frame.setVisible(true);
  }
  
  public void reset()
  {
    this.gui.setFormigueiro(this.f);
  }
  
  public void atualizaFormigueiro(IFormigueiro f)
  {
    this.f = f;
  }
  
  public void pintaCaminho(Iterator<ISala> caminho)
  {
    this.gui.paintPath(caminho);
  }
  
  public void limpaCaminho()
  {
    this.gui.clearPath();
  }
  
  private class FormigueiroGUI
    extends JPanel
  {
    private IFormigueiro formigueiro;
    private final int ALTURA_SALA = 20;
    private final Color COR_CAMINHO = Color.GRAY;
    private final Color COR_LETRAS = Color.BLACK;
    private final Color COR_PROCESSAMENTO = Color.green;
    private final Color COR_SILO = Color.BLACK;
    private int largura;
    private int altura;
    private ArrayList<ISala> redPath;
    
    private FormigueiroGUI(IFormigueiro f, int largura, int altura)
    {
      initComponents();
      this.formigueiro = f;
      this.largura = largura;
      this.altura = altura;
      this.redPath = new ArrayList();
    }

        private FormigueiroGUI(IFormigueiro f, int i, int i0, Object object) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
    private void setFormigueiro(IFormigueiro f)
    {
      this.formigueiro = f;
      
      repaint();
    }
    
    private void paintPath(Iterator<ISala> caminho)
    {
      this.redPath = new ArrayList();
      while (caminho.hasNext()) {
        this.redPath.add(caminho.next());
      }
      repaint();
    }
    
    private void clearPath()
    {
      this.redPath = new ArrayList();
      repaint();
    }
    
    public void paintComponent(Graphics g)
    {
      g.setColor(Color.white);
      g.fillRect(0, 0, this.largura, this.altura);
      Iterator<ISala> it = this.formigueiro.iterator();
      while (it.hasNext())
      {
        ISala next = (ISala)it.next();
        
        Iterator<IPair<ISala, ITunel>> itt = this.formigueiro.ligacoesDe(next);
        while (itt.hasNext())
        {
          g.setColor(this.COR_CAMINHO);
          IPair<ISala, ITunel> pair = (IPair)itt.next();
          
          Color cor = this.COR_CAMINHO;
          if (this.redPath.contains(next))
          {
            int index = this.redPath.indexOf(next);
            if (((index > 0) && (((ISala)this.redPath.get(index - 1)).equals(pair.getFirst()))) || (
              (index < this.redPath.size() - 1) && (((ISala)this.redPath.get(index + 1)).equals(pair.getFirst()))))
            {
              cor = Color.red;
              g.setColor(Color.red);
            }
          }
          if (((ITunel)pair.getSecond()).getRadious() <= 1) {
            g.drawLine(next.getX() + 10, next.getY() + 10, ((ISala)pair.getFirst()).getX() + 10, ((ISala)pair.getFirst()).getY() + 10);
          } else {
            drawThickLine(g, next.getX() + 10, next.getY() + 10, ((ISala)pair.getFirst()).getX() + 10, ((ISala)pair.getFirst()).getY() + 10, ((ITunel)pair.getSecond()).getRadious(), cor);
          }
          g.setColor(this.COR_LETRAS);
          if (next.getX() < ((ISala)pair.getFirst()).getX())
          {
            int posX = next.getX() + Math.abs(next.getX() - ((ISala)pair.getFirst()).getX()) / 2;
            int posY = 0;
            if (next.getY() > ((ISala)pair.getFirst()).getY()) {
              posY = next.getY() - Math.abs(next.getY() - ((ISala)pair.getFirst()).getY()) / 2;
            } else {
              posY = next.getY() + Math.abs(next.getY() - ((ISala)pair.getFirst()).getY()) / 2;
            }
            String texto = ((ITunel)pair.getSecond()).getId() + ":" + ((ITunel)pair.getSecond()).getDistance() + "";
            g.drawString(texto + "(" + ((ITunel)pair.getSecond()).getRadious() + ")", posX, posY);
          }
        }
        int posy = next.getY();
        Iterator<IFormiga> formigas = next.listaFormigas().iterator();
        while (formigas.hasNext())
        {
          IFormiga f = (IFormiga)formigas.next();
          g.setColor(Color.RED);
          g.drawString("F" + f.getId() + "(" + f.getCarga() + "/" + f.getCapacidadeCarga() + ")", next.getX(), posy);
          posy -= 20;
        }
        g.setColor(this.COR_LETRAS);
        if ((next instanceof ISilo))
        {
          Iterator<IComida> comidas = ((ISilo)next).iteratorComida();
          while (comidas.hasNext())
          {
            IComida c = (IComida)comidas.next();
            g.drawString("C" + c.getId() + "(" + c.getTamanho() + ")", next.getX(), posy);
            posy -= 20;
          }
        }
        else if ((next instanceof IProcessamento))
        {
          Iterator<IComida> comidas = ((IProcessamento)next).iteratorComida();
          while (comidas.hasNext())
          {
            IComida c = (IComida)comidas.next();
            g.drawString("C" + c.getId() + "(" + c.getTamanho() + ")", next.getX(), posy);
            posy -= 20;
          }
        }
        g.setColor(this.COR_LETRAS);
        g.drawString(next.getId() + "", next.getX() - 10, next.getY() + 10);
      }
      it = this.formigueiro.iterator();
      while (it.hasNext())
      {
        ISala next = (ISala)it.next();
        if ((next instanceof IProcessamento)) {
          g.setColor(this.COR_PROCESSAMENTO);
        } else if ((next instanceof ISilo)) {
          g.setColor(this.COR_SILO);
        } else {
          g.setColor(this.COR_CAMINHO);
        }
        g.fillOval(next.getX(), next.getY(), 20, 20);
        
        Iterator<IPair<ISala, ITunel>> itt = this.formigueiro.ligacoesDe(next);
        while (itt.hasNext())
        {
          IPair<ISala, ITunel> pair = (IPair)itt.next();
          g.setColor(this.COR_LETRAS);
          if (next.getX() < ((ISala)pair.getFirst()).getX())
          {
            int posX = next.getX() + Math.abs(next.getX() - ((ISala)pair.getFirst()).getX()) / 2;
            int posY = 0;
            if (next.getY() > ((ISala)pair.getFirst()).getY()) {
              posY = next.getY() - Math.abs(next.getY() - ((ISala)pair.getFirst()).getY()) / 2;
            } else {
              posY = next.getY() + Math.abs(next.getY() - ((ISala)pair.getFirst()).getY()) / 2;
            }
            String texto = ((ITunel)pair.getSecond()).getId() + ":" + ((ITunel)pair.getSecond()).getDistance() + "";
            g.drawString(texto + "(" + ((ITunel)pair.getSecond()).getRadious() + ")", posX, posY);
          }
        }
      }
    }
    
    private void drawThickLine(Graphics g, int x1, int y1, int x2, int y2, int thickness, Color c)
    {
      g.setColor(c);
      int dX = x2 - x1;
      int dY = y2 - y1;
      
      double lineLength = Math.sqrt(dX * dX + dY * dY);
      
      double scale = thickness / (2.0D * lineLength);
      
      double ddx = -scale * dY;
      double ddy = scale * dX;
      ddx += (ddx > 0.0D ? 0.5D : -0.5D);
      ddy += (ddy > 0.0D ? 0.5D : -0.5D);
      int dx = (int)ddx;
      int dy = (int)ddy;
      
      int[] xPoints = new int[4];
      int[] yPoints = new int[4];
      
      xPoints[0] = (x1 + dx);
      yPoints[0] = (y1 + dy);
      xPoints[1] = (x1 - dx);
      yPoints[1] = (y1 - dy);
      xPoints[2] = (x2 - dx);
      yPoints[2] = (y2 - dy);
      xPoints[3] = (x2 + dx);
      yPoints[3] = (y2 + dy);
      
      g.fillPolygon(xPoints, yPoints, 4);
    }
    
    private void initComponents()
    {
      GroupLayout layout = new GroupLayout(this);
      setLayout(layout);
      layout.setHorizontalGroup(layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 400, 32767));
      
      layout.setVerticalGroup(layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 300, 32767));
    }
  }

*/

    public FormigueiroViewer(IFormigueiro f) {
        super(f);
    }

 
 
/* public static void main(String[] args) {


 // private IFormigueiro f;
  //private FormigueiroGUI gui;
  
  public FormigueiroViewer(IFormigueiro f)
  {
    this.f = f;
    
    this.gui = new FormigueiroGUI(f, f.getMaxX() + 100, f.getMaxY() + 100, null);
    JFrame frame = new JFrame("FormigueiroViewer");
    frame.setDefaultCloseOperation(3);
    frame.add(this.gui);
    frame.setSize(f.getMaxX() + 100, f.getMaxY() + 100);
    frame.setVisible(true);
  }
  
  public void reset()
  {
    this.gui.setFormigueiro(this.f);
  }
  
  public void atualizaFormigueiro(IFormigueiro f)
  {
    this.f = f;
  }
  
  public void pintaCaminho(Iterator<ISala> caminho)
  {
    this.gui.paintPath(caminho);
  }
  
  public void limpaCaminho()
  {
    this.gui.clearPath();
  }
  
  private class FormigueiroGUI
    extends JPanel
  {
    private IFormigueiro formigueiro;
    private final int ALTURA_SALA = 20;
    private final Color COR_CAMINHO = Color.GRAY;
    private final Color COR_LETRAS = Color.BLACK;
    private final Color COR_PROCESSAMENTO = Color.green;
    private final Color COR_SILO = Color.BLACK;
    private int largura;
    private int altura;
    private ArrayList<ISala> redPath;
    
    private FormigueiroGUI(IFormigueiro f, int largura, int altura)
    {
      initComponents();
      this.formigueiro = f;
      this.largura = largura;
      this.altura = altura;
      this.redPath = new ArrayList();
    }

        private FormigueiroGUI(IFormigueiro f, int i, int i0, Object object) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
    private void setFormigueiro(IFormigueiro f)
    {
      this.formigueiro = f;
      
      repaint();
    }
    
    private void paintPath(Iterator<ISala> caminho)
    {
      this.redPath = new ArrayList();
      while (caminho.hasNext()) {
        this.redPath.add(caminho.next());
      }
      repaint();
    }
    
    private void clearPath()
    {
      this.redPath = new ArrayList();
      repaint();
    }
    
    public void paintComponent(Graphics g)
    {
      g.setColor(Color.white);
      g.fillRect(0, 0, this.largura, this.altura);
      Iterator<ISala> it = this.formigueiro.iterator();
      while (it.hasNext())
      {
        ISala next = (ISala)it.next();
        
        Iterator<IPair<ISala, ITunel>> itt = this.formigueiro.ligacoesDe(next);
        while (itt.hasNext())
        {
          g.setColor(this.COR_CAMINHO);
          IPair<ISala, ITunel> pair = (IPair)itt.next();
          
          Color cor = this.COR_CAMINHO;
          if (this.redPath.contains(next))
          {
            int index = this.redPath.indexOf(next);
            if (((index > 0) && (((ISala)this.redPath.get(index - 1)).equals(pair.getFirst()))) || (
              (index < this.redPath.size() - 1) && (((ISala)this.redPath.get(index + 1)).equals(pair.getFirst()))))
            {
              cor = Color.red;
              g.setColor(Color.red);
            }
          }
          if (((ITunel)pair.getSecond()).getRadious() <= 1) {
            g.drawLine(next.getX() + 10, next.getY() + 10, ((ISala)pair.getFirst()).getX() + 10, ((ISala)pair.getFirst()).getY() + 10);
          } else {
            drawThickLine(g, next.getX() + 10, next.getY() + 10, ((ISala)pair.getFirst()).getX() + 10, ((ISala)pair.getFirst()).getY() + 10, ((ITunel)pair.getSecond()).getRadious(), cor);
          }
          g.setColor(this.COR_LETRAS);
          if (next.getX() < ((ISala)pair.getFirst()).getX())
          {
            int posX = next.getX() + Math.abs(next.getX() - ((ISala)pair.getFirst()).getX()) / 2;
            int posY = 0;
            if (next.getY() > ((ISala)pair.getFirst()).getY()) {
              posY = next.getY() - Math.abs(next.getY() - ((ISala)pair.getFirst()).getY()) / 2;
            } else {
              posY = next.getY() + Math.abs(next.getY() - ((ISala)pair.getFirst()).getY()) / 2;
            }
            String texto = ((ITunel)pair.getSecond()).getId() + ":" + ((ITunel)pair.getSecond()).getDistance() + "";
            g.drawString(texto + "(" + ((ITunel)pair.getSecond()).getRadious() + ")", posX, posY);
          }
        }
        int posy = next.getY();
        Iterator<IFormiga> formigas = next.listaFormigas().iterator();
        while (formigas.hasNext())
        {
          IFormiga f = (IFormiga)formigas.next();
          g.setColor(Color.RED);
          g.drawString("F" + f.getId() + "(" + f.getCarga() + "/" + f.getCapacidadeCarga() + ")", next.getX(), posy);
          posy -= 20;
        }
        g.setColor(this.COR_LETRAS);
        if ((next instanceof ISilo))
        {
          Iterator<IComida> comidas = ((ISilo)next).iteratorComida();
          while (comidas.hasNext())
          {
            IComida c = (IComida)comidas.next();
            g.drawString("C" + c.getId() + "(" + c.getTamanho() + ")", next.getX(), posy);
            posy -= 20;
          }
        }
        else if ((next instanceof IProcessamento))
        {
          Iterator<IComida> comidas = ((IProcessamento)next).iteratorComida();
          while (comidas.hasNext())
          {
            IComida c = (IComida)comidas.next();
            g.drawString("C" + c.getId() + "(" + c.getTamanho() + ")", next.getX(), posy);
            posy -= 20;
          }
        }
        g.setColor(this.COR_LETRAS);
        g.drawString(next.getId() + "", next.getX() - 10, next.getY() + 10);
      }
      it = this.formigueiro.iterator();
      while (it.hasNext())
      {
        ISala next = (ISala)it.next();
        if ((next instanceof IProcessamento)) {
          g.setColor(this.COR_PROCESSAMENTO);
        } else if ((next instanceof ISilo)) {
          g.setColor(this.COR_SILO);
        } else {
          g.setColor(this.COR_CAMINHO);
        }
        g.fillOval(next.getX(), next.getY(), 20, 20);
        
        Iterator<IPair<ISala, ITunel>> itt = this.formigueiro.ligacoesDe(next);
        while (itt.hasNext())
        {
          IPair<ISala, ITunel> pair = (IPair)itt.next();
          g.setColor(this.COR_LETRAS);
          if (next.getX() < ((ISala)pair.getFirst()).getX())
          {
            int posX = next.getX() + Math.abs(next.getX() - ((ISala)pair.getFirst()).getX()) / 2;
            int posY = 0;
            if (next.getY() > ((ISala)pair.getFirst()).getY()) {
              posY = next.getY() - Math.abs(next.getY() - ((ISala)pair.getFirst()).getY()) / 2;
            } else {
              posY = next.getY() + Math.abs(next.getY() - ((ISala)pair.getFirst()).getY()) / 2;
            }
            String texto = ((ITunel)pair.getSecond()).getId() + ":" + ((ITunel)pair.getSecond()).getDistance() + "";
            g.drawString(texto + "(" + ((ITunel)pair.getSecond()).getRadious() + ")", posX, posY);
          }
        }
      }
    }
    
    private void drawThickLine(Graphics g, int x1, int y1, int x2, int y2, int thickness, Color c)
    {
      g.setColor(c);
      int dX = x2 - x1;
      int dY = y2 - y1;
      
      double lineLength = Math.sqrt(dX * dX + dY * dY);
      
      double scale = thickness / (2.0D * lineLength);
      
      double ddx = -scale * dY;
      double ddy = scale * dX;
      ddx += (ddx > 0.0D ? 0.5D : -0.5D);
      ddy += (ddy > 0.0D ? 0.5D : -0.5D);
      int dx = (int)ddx;
      int dy = (int)ddy;
      
      int[] xPoints = new int[4];
      int[] yPoints = new int[4];
      
      xPoints[0] = (x1 + dx);
      yPoints[0] = (y1 + dy);
      xPoints[1] = (x1 - dx);
      yPoints[1] = (y1 - dy);
      xPoints[2] = (x2 - dx);
      yPoints[2] = (y2 - dy);
      xPoints[3] = (x2 + dx);
      yPoints[3] = (y2 + dy);
      
      g.fillPolygon(xPoints, yPoints, 4);
    }
    
    private void initComponents()
    {
      GroupLayout layout = new GroupLayout(this);
      setLayout(layout);
      layout.setHorizontalGroup(layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 400, 32767));
      
      layout.setVerticalGroup(layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 300, 32767));
    }
  }

*/
    

}
   
    

