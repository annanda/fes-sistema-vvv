package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PassageiroController;
import model.Constants;
import model.Passageiro;

@SuppressWarnings("serial")
public class PassageiroEditar extends JDialog {

    private Passageiro passageiro;
    private JDialog dialog;
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
    public PassageiroEditar(JFrame owner, int id) {
        super(owner, true);
        this.dialog = this;
        
        passageiro = PassageiroController.getPassageiroById(id);
        
        setBounds(100, 100, 430, 320);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(52, 12, 40, 15);
        panel.add(lblNome);

        txtNome = new JTextField();
        txtNome.setText(passageiro.getNome());
        txtNome.setBounds(52, 33, 310, 19);
        panel.add(txtNome);

        JLabel lblEndereco = new JLabel("Endereco");
        lblEndereco.setBounds(52, 58, 66, 15);
        panel.add(lblEndereco);

        txtEndereco = new JTextField();
        txtEndereco.setText(passageiro.getEndereco());
        txtEndereco.setBounds(52, 79, 310, 19);
        panel.add(txtEndereco);
        
        JLabel lblDataNascimento = new JLabel("Data de Nascimento");
        lblDataNascimento.setBounds(52, 104, 150, 15);
        panel.add(lblDataNascimento);
        
        txtDataNascimento = new JTextField();
        txtDataNascimento.setEnabled(false);
        txtDataNascimento.setText(Constants.DATE_FORMAT.format(passageiro.getDataDeNascimento()));
        txtDataNascimento.setBounds(52, 125, 114, 19);
        panel.add(txtDataNascimento);

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(248, 104, 37, 15);
        panel.add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setEnabled(false);
        txtCpf.setText(passageiro.getCpf());
        txtCpf.setBounds(248, 125, 114, 19);
        panel.add(txtCpf);

        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(52, 150, 66, 15);
        panel.add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setText(passageiro.getTelefone());
        txtTelefone.setBounds(52, 171, 114, 19);
        panel.add(txtTelefone);

        JLabel lblProfissao = new JLabel("Profissao");
        lblProfissao.setBounds(248, 150, 72, 15);
        panel.add(lblProfissao);
        
        txtProfissao = new JTextField();
        txtProfissao.setText(passageiro.getProfissao());
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
        int selectedIndex = 0;
        boolean found = passageiro.getResponsavel() == null;
        for (Passageiro p : passageiros) {
            if (!p.getCpf().equals(passageiro.getCpf())) {
                if (!found) {
                    selectedIndex++;
                    if (passageiro.getResponsavel().getCpf().equals(p.getCpf()))
                        found = true;
                }
                cboResponsavel.addItem(p.getCpf());
            }
        }
        cboResponsavel.setSelectedIndex(found ? selectedIndex : 0);
        panel.add(cboResponsavel);

        JLabel lblCodigo = new JLabel("Codigo");
        lblCodigo.setBounds(52, 196, 49, 15);
        panel.add(lblCodigo);
        
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        txtCodigo.setText(passageiro.getCodigo());
        txtCodigo.setBounds(52, 220, 114, 19);
        txtCodigo.setColumns(10);
        panel.add(txtCodigo);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(52, 247, 78, 25);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PassageiroController.alterarPassageiro(
                        passageiro.getId(),
                        txtNome.getText(),
                        txtEndereco.getText(),
                        txtTelefone.getText(),
                        txtProfissao.getText(),
                        cboResponsavel.getSelectedItem().toString()
                );
                dialog.dispose();
            }
        });
        panel.add(btnSalvar);
    }
}
