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

import controller.CidadeController;
import controller.ModalController;
import controller.PercursoController;
import model.Cidade;
import model.Modal;

@SuppressWarnings("serial")
public class PercursoNovo extends JInternalFrame {

    private JComboBox<String> cboModal;
    private JComboBox<String> cboOrigem;
    private JComboBox<String> cboDestino;
    private JTextField txtHoraPartida;
    private JTextField txtDuracao;

    /**
     * Create the frame.
     */
    public PercursoNovo() {
        setBounds(100, 100, 430, 320);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);

        ArrayList<Modal> modais = ModalController.listarModais("", "", "", "", "", "");
        String[] strModais = new String[modais.size()];
        for (int i = 0; i < modais.size(); i++) {
            Modal modal = modais.get(i);
            strModais[i] = String.join("-", modal.getCodigo(), modal.getCompanhia(), modal.getModelo());
        }

        JLabel lblModal = new JLabel("Modal");
        lblModal.setBounds(52, 12, 114, 15);
        panel.add(lblModal);

        cboModal = new JComboBox<String>(strModais);
        cboModal.setBounds(52, 30, 114, 24);
        panel.add(cboModal);

        ArrayList<Cidade> cidades = CidadeController.listarCidades("", "", "");
        Collections.sort(cidades, new Comparator<Cidade>() {
            public int compare(Cidade a, Cidade b) {
                return a.getNome().compareTo(b.getNome());
            }
        });
        String[] strCidades = new String[cidades.size()];
        for (int i = 0; i < cidades.size(); i++) {
            Cidade cidade = cidades.get(i);
            strCidades[i] = String.join("-", cidade.getCodigo(), cidade.getIdentificador(), cidade.getNome());
        }

        JLabel lblOrigem = new JLabel("Origem");
        lblOrigem.setBounds(52, 58, 114, 15);
        panel.add(lblOrigem);

        cboOrigem = new JComboBox<String>(strCidades);
        cboOrigem.setBounds(52, 78, 114, 24);
        panel.add(cboOrigem);

        JLabel lblDestino = new JLabel("Destino");
        lblDestino.setBounds(248, 58, 114, 15);
        panel.add(lblDestino);

        cboDestino = new JComboBox<String>(strCidades);
        cboDestino.setBounds(248, 78, 114, 24);
        panel.add(cboDestino);

        JLabel lblHoraPartida = new JLabel("Hora da Partida");
        lblHoraPartida.setBounds(52, 104, 114, 15);
        panel.add(lblHoraPartida);

        txtHoraPartida = new JTextField();
        txtHoraPartida.setBounds(52, 125, 114, 19);
        panel.add(txtHoraPartida);

        JLabel lblDuracao = new JLabel("Duracao");
        lblDuracao.setBounds(52, 150, 114, 15);
        panel.add(lblDuracao);

        txtDuracao = new JTextField();
        txtDuracao.setBounds(52, 171, 114, 19);
        panel.add(txtDuracao);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(52, 247, 78, 25);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PercursoController.cadastrarPercurso(
                        cboModal.getSelectedItem().toString().split("-")[0],
                        cboOrigem.getSelectedItem().toString().split("-")[0],
                        cboDestino.getSelectedItem().toString().split("-")[0],
                        txtHoraPartida.getText(),
                        txtDuracao.getText(),
                        null
                  );
                clear();
            }
        });
        panel.add(btnSalvar);
    }
    
    public void clear() {
        cboModal.setSelectedIndex(0);
        cboOrigem.setSelectedIndex(0);
        cboDestino.setSelectedIndex(0);
        txtHoraPartida.setText("");
        txtDuracao.setText("");
    }
}
