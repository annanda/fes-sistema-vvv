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
import javax.swing.JTextField;

import controller.ModalController;

@SuppressWarnings("serial")
public class ModalNovo extends JInternalFrame {

    private JComboBox<String> cboTipo;
    private JTextField txtCompanhia;
    private JTextField txtModelo;
    private JTextField txtCapacidade;
    private JTextField txtAnoFabricacao;
    private JTextField txtDataManutencao;
    private JTextField txtCodigo;

    /**
     * Create the frame.
     */
    public ModalNovo() {
        setBounds(100, 100, 430, 320);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(52, 12, 114, 15);
        panel.add(lblTipo);

        cboTipo = new JComboBox<String>();
        cboTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Aviao", "Trem", "Navio"}));
        cboTipo.setBounds(52, 30, 114, 24);
        panel.add(cboTipo);

        JLabel lblCompanhia = new JLabel("Companhia");
        lblCompanhia.setBounds(52, 58, 114, 15);
        panel.add(lblCompanhia);

        txtCompanhia = new JTextField();
        txtCompanhia.setBounds(52, 79, 310, 19);
        panel.add(txtCompanhia);
        
        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(52, 104, 114, 15);
        panel.add(lblModelo);
        
        txtModelo = new JTextField();
        txtModelo.setBounds(52, 125, 114, 19);
        panel.add(txtModelo);

        JLabel lblCapacidade = new JLabel("Capacidade");
        lblCapacidade.setBounds(248, 104, 114, 15);
        panel.add(lblCapacidade);

        txtCapacidade = new JTextField();
        txtCapacidade.setBounds(248, 125, 114, 19);
        panel.add(txtCapacidade);

        JLabel lblAnoFabricacao = new JLabel("Ano de Fabricacao");
        lblAnoFabricacao.setBounds(52, 150, 140, 15);
        panel.add(lblAnoFabricacao);
        
        txtAnoFabricacao = new JTextField();
        txtAnoFabricacao.setBounds(52, 171, 114, 19);
        panel.add(txtAnoFabricacao);
        
        JLabel lblDataManutencao = new JLabel("Data de Manutencao");
        lblDataManutencao.setBounds(214, 150, 148, 15);
        panel.add(lblDataManutencao);
        
        txtDataManutencao = new JTextField();
        txtDataManutencao.setBounds(248, 171, 114, 19);
        panel.add(txtDataManutencao);
        
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
                ModalController.cadastrarModal(
                        cboTipo.getSelectedItem().toString(),
                        txtCodigo.getText(),
                        txtCompanhia.getText(),
                        txtCapacidade.getText(),
                        txtModelo.getText(),
                        txtAnoFabricacao.getText(),
                        "0",
                        "0",
                        txtDataManutencao.getText()
                );
                clear();
            }
        });
        panel.add(btnSalvar);
    }
    
    public void clear() {
        cboTipo.setSelectedIndex(0);
        txtCompanhia.setText("");
        txtModelo.setText("");
        txtCapacidade.setText("");
        txtAnoFabricacao.setText("");
        txtDataManutencao.setText("");
        txtCodigo.setText("");
    }
}
