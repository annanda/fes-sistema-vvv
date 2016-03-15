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
import javax.swing.JTextField;

import controller.ModalController;
import model.Constants;
import model.Modal;

@SuppressWarnings("serial")
public class ModalEditar extends JDialog {

    private Modal modal;
    private JDialog dialog;
    private JComboBox<String> cboTipo;
    private JTextField txtCompanhia;
    private JTextField txtModelo;
    private JTextField txtCapacidade;
    private JTextField txtAnoFabricacao;
    private JTextField txtDataManutencao;
    private JComboBox<String> cboEmUso;
    private JComboBox<String> cboEmManutencao;
    private JTextField txtCodigo;

    /**
     * Create the frame.
     */
    public ModalEditar(JFrame owner, int id_modal) {
        super(owner, true);
        this.dialog = this;
        
        modal = ModalController.getModalById(id_modal);
        
        setBounds(100, 100, 430, 320);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(52, 12, 114, 15);
        panel.add(lblTipo);

        cboTipo = new JComboBox<String>();
        cboTipo.setEditable(false);
        cboTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Aviao", "Trem", "Navio"}));
        cboTipo.setSelectedItem(modal.getTipo());
        cboTipo.setBounds(52, 30, 114, 24);
        panel.add(cboTipo);

        JLabel lblCompanhia = new JLabel("Companhia");
        lblCompanhia.setBounds(52, 58, 114, 15);
        panel.add(lblCompanhia);

        txtCompanhia = new JTextField();
        txtCompanhia.setText(modal.getCompanhia());
        txtCompanhia.setBounds(52, 79, 310, 19);
        panel.add(txtCompanhia);
        
        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(52, 104, 114, 15);
        panel.add(lblModelo);
        
        txtModelo = new JTextField();
        txtModelo.setEditable(false);
        txtModelo.setText(modal.getModelo());
        txtModelo.setBounds(52, 125, 114, 19);
        panel.add(txtModelo);

        JLabel lblCapacidade = new JLabel("Capacidade");
        lblCapacidade.setBounds(248, 104, 114, 15);
        panel.add(lblCapacidade);

        txtCapacidade = new JTextField();
        txtCapacidade.setText(Integer.toString(modal.getCapacidade()));
        txtCapacidade.setBounds(248, 125, 114, 19);
        panel.add(txtCapacidade);

        JLabel lblAnoFabricacao = new JLabel("Ano de Fabricacao");
        lblAnoFabricacao.setBounds(52, 150, 140, 15);
        panel.add(lblAnoFabricacao);
        
        txtAnoFabricacao = new JTextField();
        txtAnoFabricacao.setEditable(false);
        txtAnoFabricacao.setText(Integer.toString(modal.getAnoFabricacao()));
        txtAnoFabricacao.setBounds(52, 171, 114, 19);
        panel.add(txtAnoFabricacao);
        
        JLabel lblDataManutencao = new JLabel("Data de Manutencao");
        lblDataManutencao.setBounds(214, 150, 148, 15);
        panel.add(lblDataManutencao);
        
        txtDataManutencao = new JTextField();
        txtDataManutencao.setText(Constants.DATE_FORMAT.format(modal.getDataManutencao()));
        txtDataManutencao.setBounds(248, 171, 114, 19);
        panel.add(txtDataManutencao);
        
        JLabel lblEmUso = new JLabel("Em Uso");
        lblEmUso.setBounds(178, 196, 56, 15);
        panel.add(lblEmUso);
        
        cboEmUso = new JComboBox<String>();
        cboEmUso.setModel(new DefaultComboBoxModel<String>(new String[] {"Nao", "Sim"}));
        cboEmUso.setSelectedIndex(modal.getEmUso());
        cboEmUso.setBounds(178, 217, 64, 24);
        panel.add(cboEmUso);
        
        JLabel lblEmManutencao = new JLabel("Em Manutencao");
        lblEmManutencao.setBounds(248, 196, 114, 15);
        panel.add(lblEmManutencao);
        
        cboEmManutencao = new JComboBox<String>();
        cboEmManutencao.setModel(new DefaultComboBoxModel<String>(new String[] {"Nao", "Sim"}));
        cboEmManutencao.setSelectedIndex(modal.getEmManutencao());
        cboEmManutencao.setBounds(298, 217, 64, 24);
        panel.add(cboEmManutencao);
        
        JLabel lblCodigo = new JLabel("Codigo");
        lblCodigo.setBounds(52, 196, 49, 15);
        panel.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        txtCodigo.setText(modal.getCodigo());
        txtCodigo.setBounds(52, 220, 114, 19);
        txtCodigo.setColumns(10);
        panel.add(txtCodigo);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(52, 247, 78, 25);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModalController.alterarModal(
                        modal.getId(),
                        txtCompanhia.getText(),
                        txtCapacidade.getText(),
                        Integer.toString(cboEmManutencao.getSelectedIndex()),
                        Integer.toString(cboEmUso.getSelectedIndex()),
                        txtDataManutencao.getText()
                );
                dialog.dispose();
            }
        });
        panel.add(btnSalvar);
    }
}
