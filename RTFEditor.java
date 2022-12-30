import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Container;  

import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;  
import javax.swing.JPanel; 
import javax.swing.JButton;
import javax.swing.JScrollPane;  
import javax.swing.JTextPane;  
import javax.swing.text.BadLocationException;  
import javax.swing.text.Document;  
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;  
import javax.swing.text.StyleConstants;  
import javax.swing.text.rtf.RTFEditorKit;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class RTFEditor {  

    private RTFEditorKit kit;
    private FileOutputStream fos;
    private FileInputStream fis;
    private File file;
    private StyledDocument doc;
    private JFrame frame;  
    private JPanel buttonPanel;
    private JButton bold;
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    private Container cp;  
    private JTextPane pane;
    private JScrollPane scrollPane;
    private FileWriter fileOut;
    private ByteArrayOutputStream outputStream;

    public RTFEditor(String fileName){
        this.kit = new RTFEditorKit();
        this.file = new File(fileName);
        this.fos = null;
        this.fis = null;
        //StyledDocument doc;

        this.frame = new JFrame("JTextPane Example");  
        this.buttonPanel = new JPanel();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.cp = frame.getContentPane();  
        this.pane = new JTextPane();
        this.scrollPane = new JScrollPane(pane);  
        this.cp.add(buttonPanel, BorderLayout.WEST);
        this.cp.add(scrollPane, BorderLayout.CENTER); 

        //this.buttonPanel.setSize(100, 300);  
        this.buttonPanel.setVisible(true); 
  
        this.frame.setSize(400, 300);  
        this.frame.setVisible(true);

        //SimpleAttributeSet attributeSet = new SimpleAttributeSet();  
        //StyleConstants.setBold(attributeSet, true);
        readFromFile();
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // call terminate
                writeToFile();
            }
        });
    }

    public void setBold(){
        
    }

    public void setFile(String fileName) {
        this.file = new File(fileName);
    }

    public void readFromFile(){
        try {
             
            this.fis = new FileInputStream(this.file);
             
            // Writes bytes from the specified byte array to this file output stream 
            //fos.write(s.getBytes());
            //kit.read(fis, doc, 0);
            this.kit.read(this.fis, this.pane.getStyledDocument(), 0);

 
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
        }
        catch (BadLocationException ble) {
            System.out.println("Exception while writing file " + ble);
        }
    }

    public void writeToFile() {
        this.doc = this.pane.getStyledDocument();
        try {
            fileOut = new FileWriter(this.file);
            outputStream = new ByteArrayOutputStream();
            //this.fos = new FileOutputStream(this.file, true);
             
            // Writes bytes from the specified byte array to this file output stream 
            //fos.write(s.getBytes());
            //this.kit.write(this.fos, this.doc, 0, this.doc.getLength());
            this.kit.write(outputStream, this.doc, 0, this.doc.getLength());
            fileOut.write(new String(outputStream.toByteArray()));
 
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
        catch (BadLocationException ble) {
            System.out.println("Exception while writing file " + ble);
        }
        finally {
            // close the streams using close method
            try {
                //if (this.fos != null) {
                //    this.fos.close();
                //}
                if (fileOut != null) {
                    fileOut.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing stream/fileWriter: " + ioe);
            }
 
        }
    }

    public static void main(String args[]) throws BadLocationException {  
        RTFEditor inst = new RTFEditor("test_doc.rtf");
        
      }  
}  