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

@SuppressWarnings("serial")
public class Main extends JFrame {

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
        
        JMenuItem mntmNovoUsuario = new JMenuItem("Novo");
        mntmNovoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showInternalFrame(new UsuarioNovo());
            }
        });
        mnUsuario.add(mntmNovoUsuario);
        
        JMenuItem mntmListarUsuario = new JMenuItem("Listar");
        mntmListarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showInternalFrame(new UsuarioListar());
            }
        });
        mnUsuario.add(mntmListarUsuario);
        
        JMenu mnPassageiro = new JMenu("Passageiro");
        menuBar.add(mnPassageiro);
        
        JMenuItem mntmNovoPassageiro = new JMenuItem("Novo");
        mntmNovoPassageiro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showInternalFrame(new PassageiroNovo());
            }
        });
        mnPassageiro.add(mntmNovoPassageiro);
        
        JMenuItem mntmListarPassageiro = new JMenuItem("Listar");
        mntmListarPassageiro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showInternalFrame(new PassageiroListar());
            }
        });
        mnPassageiro.add(mntmListarPassageiro);
        
        JMenu mnModal = new JMenu("Modal");
        menuBar.add(mnModal);
        
        JMenuItem mntmNovoModal = new JMenuItem("Novo");
        mntmNovoModal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showInternalFrame(new ModalNovo());
            }
        });
        mnModal.add(mntmNovoModal);
        
        JMenuItem mntmListarModal = new JMenuItem("Listar");
        mntmListarModal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInternalFrame(new ModalListar());
            }
        });
        mnModal.add(mntmListarModal);
        contentPane = new JDesktopPane();
        setContentPane(contentPane);
    }

    private void showInternalFrame(JInternalFrame iFrame) {
        contentPane.removeAll();
        contentPane.add(iFrame);
        iFrame.setVisible(true);
        iFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        iFrame.setClosable(true);
        iFrame.setMaximizable(false);
        try {
            iFrame.setMaximum(true);
        } catch (PropertyVetoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
