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
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(52, 12, 40, 15);
        panel.add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setBounds(52, 33, 310, 19);
        panel.add(txtNome);
        
        JLabel lblEndereco = new JLabel("Endereco");
        lblEndereco.setBounds(52, 58, 66, 15);
        panel.add(lblEndereco);
        
        txtEndereco = new JTextField();
        txtEndereco.setBounds(52, 79, 310, 19);
        panel.add(txtEndereco);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(52, 104, 37, 15);
        panel.add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(52, 125, 310, 19);
        panel.add(txtEmail);
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(52, 150, 45, 15);
        panel.add(lblSenha);
        
        txtSenha = new JPasswordField();
        txtSenha.setBounds(52, 171, 310, 19);
        panel.add(txtSenha);
        
        JLabel lblNivelPermissao = new JLabel("Nivel de Permissao");
        lblNivelPermissao.setBounds(228, 196, 134, 15);
        panel.add(lblNivelPermissao);
        
        cboNivelPermissao = new JComboBox<String>();
        cboNivelPermissao.setBounds(228, 217, 134, 24);
        cboNivelPermissao.setModel(new DefaultComboBoxModel<String>(new String[] {"0 - Usuario", "1 - Admin"}));
        cboNivelPermissao.setSelectedIndex(0);
        panel.add(cboNivelPermissao);
        
        JLabel lblCodigo = new JLabel("Codigo");
        lblCodigo.setBounds(52, 196, 49, 15);
        panel.add(lblCodigo);
        
        txtCodigo = new JTextField();
        txtCodigo.setBounds(52, 220, 114, 19);
        txtCodigo.setColumns(10);
        panel.add(txtCodigo);
        
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
        panel.add(btnSalvar);
    }
    
    public void clear() {
        txtNome.setText("");
        txtEndereco.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtCodigo.setText("");
        cboNivelPermissao.setSelectedIndex(0);
    }
}
