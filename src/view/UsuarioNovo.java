package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.UsuarioController;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsuarioNovo extends JInternalFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -303966158760444015L;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtEmail;
    private JTextField txtCodigo;
    private JLabel lblNivelPermissao;
    private JPasswordField txtSenha;
    private JComboBox<String> cboNivelPermissao;

    /**
     * Create the frame.
     */
    public UsuarioNovo() {
        setVisible(true);
        setBounds(100, 100, 430, 320);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setClosable(true);
        setMaximizable(false);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        
        JLabel lblNome = new JLabel("Nome");
        
        txtNome = new JTextField();
        txtNome.setColumns(10);
        
        JLabel lblEndereco = new JLabel("Endereco");
        
        txtEndereco = new JTextField();
        txtEndereco.setColumns(10);
        
        JLabel lblEmail = new JLabel("Email");
        
        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        
        JLabel lblSenha = new JLabel("Senha");
        
        JLabel lblCodigo = new JLabel("Codigo");
        
        txtCodigo = new JTextField();
        txtCodigo.setColumns(10);
        
        lblNivelPermissao = new JLabel("Nivel de Permissao");
        
        cboNivelPermissao = new JComboBox<String>();
        cboNivelPermissao.setModel(new DefaultComboBoxModel<String>(new String[] {"0 - Admin", "1 - Usuario"}));
        cboNivelPermissao.setSelectedIndex(1);
        
        txtSenha = new JPasswordField();
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UsuarioController.cadastrarUsuario(
                        txtNome.getText(),
                        txtEndereco.getText(),
                        txtCodigo.getText(),
                        txtEmail.getText(),
                        txtSenha.getPassword().toString(),
                        Integer.toString(cboNivelPermissao.getSelectedIndex())
                );
                clear();
            }
        });
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(52)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(btnSalvar)
                            .addContainerGap())
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblSenha)
                                .addComponent(lblEmail)
                                .addComponent(lblEndereco)
                                .addComponent(lblNome)
                                .addComponent(txtSenha, 310, 310, 310)
                                .addComponent(txtEndereco, 310, 310, 310)
                                .addComponent(txtEmail, 310, 310, 310)
                                .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
                                .addGroup(gl_panel.createSequentialGroup()
                                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblCodigo)
                                        .addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(cboNivelPermissao, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblNivelPermissao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addContainerGap(58, Short.MAX_VALUE))))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNome)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(lblEndereco)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(lblEmail)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(lblSenha)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblCodigo)
                        .addComponent(lblNivelPermissao))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboNivelPermissao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnSalvar)
                    .addContainerGap(16, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
    }
    
    public void clear() {
        txtNome.setText("");
        txtEndereco.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtCodigo.setText("");
        cboNivelPermissao.setSelectedIndex(1);
    }
}
