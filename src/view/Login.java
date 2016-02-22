package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.UsuarioController;
import model.Usuario;

public class Login {

    private JFrame frame;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JLabel lblErro;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Login() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        JLabel lblEmail = new JLabel("Email");

        txtEmail = new JTextField();
        txtEmail.setColumns(10);

        JLabel lblSenha = new JLabel("Senha");

        txtSenha = new JPasswordField();

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String erro = "Usuario ou senha invalido(s)";
                String senha = new String(txtSenha.getPassword());
                Usuario usuario = UsuarioController.getUsuarioByEmail(txtEmail.getText());
                if (usuario != null && usuario.getSenha().equals(senha)) {
                    frame.dispose();
                    (new Main()).setVisible(true);
                }
                else {
                    lblErro.setText(erro);
                    lblErro.setVisible(true);
                }
            }
        });

        lblErro = new JLabel("Erro");
        lblErro.setVisible(false);

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(119)
                    .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addComponent(btnLogin)
                        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                            .addComponent(lblSenha)
                            .addComponent(lblEmail)
                            .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(txtSenha)
                            .addComponent(lblErro)))
                    .addGap(107))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(59)
                    .addComponent(lblEmail)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(lblSenha)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnLogin)
                    .addGap(18)
                    .addComponent(lblErro)
                    .addContainerGap(59, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
    }
}
