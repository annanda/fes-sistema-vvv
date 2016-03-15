package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.UsuarioController;
import model.Usuario;

@SuppressWarnings("serial")
public class UsuarioEditar extends JDialog {

    private Usuario usuario;
    private JDialog dialog;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtEmail;
    private JTextField txtCodigo;
    private JPasswordField txtSenha;
    private JComboBox<String> cboNivelPermissao;

    /**
     * Create the frame.
     */
    public UsuarioEditar(JFrame owner, int id_usuario) {
        super(owner, true);
        this.dialog = this;
        
        usuario = UsuarioController.getUsuarioById(id_usuario);
        
        setBounds(100, 100, 430, 320);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(52, 12, 40, 15);
        panel.add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setText(usuario.getNome());
        txtNome.setBounds(52, 33, 310, 19);
        panel.add(txtNome);
        
        JLabel lblEndereco = new JLabel("Endereco");
        lblEndereco.setBounds(52, 58, 66, 15);
        panel.add(lblEndereco);
        
        txtEndereco = new JTextField();
        txtEndereco.setText(usuario.getEndereco());
        txtEndereco.setBounds(52, 79, 310, 19);
        panel.add(txtEndereco);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(52, 104, 37, 15);
        panel.add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setText(usuario.getEmail());
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
        cboNivelPermissao.setModel(new DefaultComboBoxModel<String>(new String[] {"0 - Admin", "1 - Usuario"}));
        cboNivelPermissao.setSelectedIndex(usuario.getNivelPermissao());
        panel.add(cboNivelPermissao);
        
        JLabel lblCodigo = new JLabel("Codigo");
        lblCodigo.setBounds(52, 196, 49, 15);
        panel.add(lblCodigo);
        
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        txtCodigo.setText(usuario.getCodigo());
        txtCodigo.setBounds(52, 220, 114, 19);
        txtCodigo.setColumns(10);
        panel.add(txtCodigo);
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(52, 247, 78, 25);
        btnSalvar.addActionListener(new ActionListener() {
            String newpwd = txtSenha.getPassword().toString();
            public void actionPerformed(ActionEvent e) {
                UsuarioController.alterarUsuario(
                        usuario.getId(),
                        txtNome.getText(),
                        txtEndereco.getText(),
                        txtEmail.getText(),
                        newpwd.isEmpty() ? usuario.getSenha() : newpwd,
                        Integer.toString(cboNivelPermissao.getSelectedIndex())
                );
                dialog.dispose();
            }
        });
        panel.add(btnSalvar);
    }
}
