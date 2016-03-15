package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PassageiroController;
import model.Passageiro;

@SuppressWarnings("serial")
public class PassageiroNovo extends JInternalFrame {

    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtDataNascimento;
    private JTextField txtCpf;
    private JTextField txtTelefone;
    private JTextField txtProfissao;
    private JComboBox<String> cboResponsavel;
    private JTextField txtCodigo;

    /**
     * Create the frame.
     */
    public PassageiroNovo() {
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
        
        JLabel lblDataNascimento = new JLabel("Data de Nascimento");
        lblDataNascimento.setBounds(52, 104, 150, 15);
        panel.add(lblDataNascimento);
        
        txtDataNascimento = new JTextField();
        txtDataNascimento.setBounds(52, 125, 114, 19);
        panel.add(txtDataNascimento);

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(248, 104, 37, 15);
        panel.add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setBounds(248, 125, 114, 19);
        panel.add(txtCpf);

        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(52, 150, 66, 15);
        panel.add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(52, 171, 114, 19);
        panel.add(txtTelefone);

        JLabel lblProfissao = new JLabel("Profissao");
        lblProfissao.setBounds(248, 150, 72, 15);
        panel.add(lblProfissao);
        
        txtProfissao = new JTextField();
        txtProfissao.setBounds(248, 171, 114, 19);
        panel.add(txtProfissao);

        JLabel lblResponsavel = new JLabel("Responsavel");
        lblResponsavel.setBounds(228, 196, 134, 15);
        panel.add(lblResponsavel);

        cboResponsavel = new JComboBox<String>();
        cboResponsavel.setBounds(228, 217, 134, 24);
        cboResponsavel.addItem("");
        ArrayList<Passageiro> passageiros = PassageiroController.listarPassageiros("", "", "", "");
        Collections.sort(passageiros, new Comparator<Passageiro>() {
            public int compare(Passageiro a, Passageiro b) {
                return a.getCpf().compareTo(b.getCpf());
            }
        });
        for (Passageiro p : passageiros)
            cboResponsavel.addItem(p.getCpf());
        cboResponsavel.setSelectedIndex(0);
        panel.add(cboResponsavel);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(52, 247, 78, 25);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PassageiroController.cadastrarPassageiro(
                        txtNome.getText(),
                        txtEndereco.getText(),
                        txtCodigo.getText(),
                        txtCpf.getText(),
                        txtTelefone.getText(),
                        txtProfissao.getText(),
                        txtDataNascimento.getText(),
                        cboResponsavel.getSelectedItem().toString()
                );
                clear();
            }
        });
        
        JLabel lblCodigo = new JLabel("Codigo");
        lblCodigo.setBounds(52, 196, 49, 15);
        panel.add(lblCodigo);
        
        txtCodigo = new JTextField();
        txtCodigo.setBounds(52, 220, 114, 19);
        txtCodigo.setColumns(10);
        panel.add(txtCodigo);
        panel.add(btnSalvar);
    }
    
    public void clear() {
        txtNome.setText("");
        txtEndereco.setText("");
        txtDataNascimento.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtProfissao.setText("");
        cboResponsavel.setSelectedIndex(0);
        txtCodigo.setText("");
    }
}
