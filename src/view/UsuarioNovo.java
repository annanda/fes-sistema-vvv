package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.UsuarioController;

@SuppressWarnings("serial")
public class UsuarioNovo extends JInternalFrame {

    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtEmail;
    private JTextField txtCodigo;
    private JPasswordField txtSenha;
    private JComboBox<String> cboNivelPermissao;

    /**
     * Create the frame.
     */
    public UsuarioNovo() {
        setBounds(100, 100, 430, 320);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        
        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(52, 12, 40, 15);
        
        txtNome = new JTextField();
        txtNome.setBounds(52, 33, 310, 19);
        txtNome.setColumns(10);
        
        JLabel lblEndereco = new JLabel("Endereco");
        lblEndereco.setBounds(52, 58, 66, 15);
        
        txtEndereco = new JTextField();
        txtEndereco.setBounds(52, 79, 310, 19);
        txtEndereco.setColumns(10);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(52, 104, 37, 15);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(52, 125, 310, 19);
        txtEmail.setColumns(10);
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(52, 150, 45, 15);
        
        JLabel lblCodigo = new JLabel("Codigo");
        lblCodigo.setBounds(52, 196, 49, 15);
        
        txtCodigo = new JTextField();
        txtCodigo.setBounds(52, 220, 114, 19);
        txtCodigo.setColumns(10);
        
        JLabel lblNivelPermissao = new JLabel("Nivel de Permissao");
        lblNivelPermissao.setBounds(228, 196, 134, 15);
        
        cboNivelPermissao = new JComboBox<String>();
        cboNivelPermissao.setBounds(228, 217, 134, 24);
        cboNivelPermissao.setModel(new DefaultComboBoxModel<String>(new String[] {"0 - Admin", "1 - Usuario"}));
        cboNivelPermissao.setSelectedIndex(1);
        
        txtSenha = new JPasswordField();
        txtSenha.setBounds(52, 171, 310, 19);
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(52, 247, 78, 25);
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
        panel.setLayout(null);
        panel.add(btnSalvar);
        panel.add(lblSenha);
        panel.add(lblEmail);
        panel.add(lblEndereco);
        panel.add(lblNome);
        panel.add(txtSenha);
        panel.add(txtEndereco);
        panel.add(txtEmail);
        panel.add(txtNome);
        panel.add(lblCodigo);
        panel.add(txtCodigo);
        panel.add(cboNivelPermissao);
        panel.add(lblNivelPermissao);
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
