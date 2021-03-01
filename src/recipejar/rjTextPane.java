/*
 * rjTextPane.java
 *
 * Created on September 20, 2008, 8:30 PM
 */
package recipejar;

import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author  James McConnel
 */
public class rjTextPane extends javax.swing.JPanel implements HyperlinkListener {

   private JPopupMenu popup;

   /** Creates new form ViewerPanel */
   public rjTextPane() {
      initComponents();
      jTextPane1.setContentType("text/html");

      jTextPane1.setEditable(false);
      setPreferredSize(new java.awt.Dimension(500, 200));
      popup = null;
   }

    /**
     * Intended as a listener for the IndexPane.
     */
   public void hyperlinkUpdate(HyperlinkEvent e){
       if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
           try {
               System.out.print(e.getDescription());
               setPage(new recipejar.filetypes.RecipeFile(recipejar.filetypes.IndexFile.getDatabaseLocation()+"/"+e.getDescription()));
           }catch(IOException ioe){}
       }
   }
   public void setPopup(JPopupMenu popup) {
      this.popup = popup;
   }

   public JTextPane getTextPane() {
      return jTextPane1;
   }

   public void addHyperLinkListener(HyperlinkListener listener) {
      jTextPane1.addHyperlinkListener(listener);
   }

   public void setPage(File f) {
      try {
         jTextPane1.setPage("file://" + f.getAbsolutePath()); //.setText(f.toString());
      } catch (IOException ex) {
         Logger.getLogger(rjTextPane.class.getName()).log(Level.SEVERE, null, ex);
      }
      jTextPane1.setCaretPosition(0);
   }

   public void setPage(String s) {
      jTextPane1.setText(s);
      jTextPane1.setCaretPosition(0);
   }

   public void setStyle(File f) {
      StyleSheet css = new StyleSheet();
      try {
         css.loadRules(new FileReader(f), null);
      } catch (IOException ex) {
         JOptionPane.showMessageDialog(null, "Fatal Error.  See \"errorLog.txt\" for more information.");
         System.exit(1);
      }
      HTMLDocument doc = (HTMLDocument) jTextPane1.getEditorKit().createDefaultDocument();
      doc.getStyleSheet().addStyleSheet(css);
      jTextPane1.setDocument(doc);
   }

   @Override
   public void addMouseListener(MouseListener l) {
      super.addMouseListener(l);
      jTextPane1.addMouseListener(l);
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setMaximumSize(new java.awt.Dimension(600, 600));
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showPopup(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                showPopup(evt);
            }
        });
        jScrollPane1.setViewportView(jTextPane1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void showPopup(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPopup
   if (evt.isPopupTrigger() && popup != null) {
      popup.show(evt.getComponent(), evt.getX(), evt.getY());
   }
}//GEN-LAST:event_showPopup
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}