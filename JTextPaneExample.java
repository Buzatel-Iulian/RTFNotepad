import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Container;  
import javax.swing.JFrame;  
import javax.swing.JScrollPane;  
import javax.swing.JTextPane;  
import javax.swing.text.BadLocationException;  
import javax.swing.text.Document;  
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;  
import javax.swing.text.StyleConstants;  
import javax.swing.text.rtf.RTFEditorKit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class JTextPaneExample {  
    public static void main(String args[]) throws BadLocationException {  
        RTFEditorKit kit = new RTFEditorKit();
        File file = new File("test_doc.rtf");
        FileOutputStream fos = null;
        FileInputStream fis = null;
        //StyledDocument doc;

        JFrame frame = new JFrame("JTextPane Example");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        Container cp = frame.getContentPane();  
        JTextPane pane = new JTextPane();  
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();  
        StyleConstants.setBold(attributeSet, true);  
        //pane.setContentType("text/html");
        //pane.setText("<html><li>Welcome<li><i>italic<li><font size=+2>large</font><li><font color=red>red</font><li><img src='beeple-10-30-19.gif' alt='Alt Text' title='Optional Title'/></html>"); 
  
        try {
             
            fis = new FileInputStream(file);
             
            // Writes bytes from the specified byte array to this file output stream 
            //fos.write(s.getBytes());
            //kit.read(fis, doc, 0);
            kit.read(fis, pane.getStyledDocument(), 0);

 
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
        }
        /*finally {
            // close the streams using close method
            try {
                if (fis != null) {
                    fis.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
 
        }*/
        //pane.setStyledDocument(doc);
        //kit.read(fis, pane.getStyledDocument(), 0);
        StyledDocument doc = pane.getStyledDocument();
        // Set the attributes before adding text  
        /*pane.setCharacterAttributes(attributeSet, true);  
        pane.setText("Welcome");  
  
        attributeSet = new SimpleAttributeSet();  
        StyleConstants.setItalic(attributeSet, true);  
        StyleConstants.setForeground(attributeSet, Color.red);  
        StyleConstants.setBackground(attributeSet, Color.blue);  
  
        StyledDocument doc = pane.getStyledDocument();  
        doc.insertString(doc.getLength(), "To Java ", attributeSet);  
  
        attributeSet = new SimpleAttributeSet();  
        doc.insertString(doc.getLength(), "World", attributeSet);  */
  
        JScrollPane scrollPane = new JScrollPane(pane);  
        cp.add(scrollPane, BorderLayout.CENTER);  
  
        frame.setSize(400, 300);  
        frame.setVisible(true);
        //kit.write();  
        try {
             
            fos = new FileOutputStream(file, true);
             
            // Writes bytes from the specified byte array to this file output stream 
            //fos.write(s.getBytes());
            kit.write(fos, doc, 0, doc.getLength()); 
 
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
        finally {
            // close the streams using close method
            try {
                if (fos != null) {
                    fos.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
 
        }
      }  
}  