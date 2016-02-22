package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 3403133465852562090L;
    private JDesktopPane contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 430, 380);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnUsuario = new JMenu("Usuario");
        menuBar.add(mnUsuario);
        
        JMenuItem mntmNovo = new JMenuItem("Novo");
        mntmNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInternalFrame(new UsuarioNovo());
            }
        });
        mnUsuario.add(mntmNovo);
        contentPane = new JDesktopPane();
        setContentPane(contentPane);
    }

    private void showInternalFrame(JInternalFrame iFrame) {
        contentPane.removeAll();
        contentPane.add(iFrame);
        try {
            iFrame.setMaximum(true);
        } catch (PropertyVetoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
