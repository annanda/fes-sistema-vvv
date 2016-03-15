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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CidadeController;
import controller.ModalController;
import controller.PercursoController;
import model.Cidade;
import model.Constants;
import model.Modal;
import model.Percurso;

@SuppressWarnings("serial")
public class PercursoEditar extends JDialog {

    private Percurso percurso;
    private JDialog dialog;
    private JComboBox<String> cboModal;
    private JComboBox<String> cboOrigem;
    private JComboBox<String> cboDestino;
    private JTextField txtHoraPartida;
    private JTextField txtDuracao;

    /**
     * Create the frame.
     */
    public PercursoEditar(JFrame owner, int id_percurso) {
        super(owner, true);
        this.dialog = this;

        percurso = PercursoController.getPercursoById(id_percurso);

        setBounds(100, 100, 430, 320);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);

        ArrayList<Modal> modais = ModalController.listarModais("", "", "", "", "", "");
        String[] strModais = new String[modais.size()];
        for (int i = 0; i < modais.size(); i++) {
            Modal modal = modais.get(i);
            strModais[i] =
                    String.join(" - ", modal.getCodigo(), modal.getCompanhia(), modal.getModelo());
        }

        JLabel lblModal = new JLabel("Modal");
        lblModal.setBounds(52, 12, 114, 15);
        panel.add(lblModal);

        Modal selectedModal = percurso.getModal();
        String strSelectedModal = String.join(" - ", selectedModal.getCodigo(),
                selectedModal.getCompanhia(), selectedModal.getModelo());

        cboModal = new JComboBox<String>(strModais);
        cboModal.setSelectedItem(strSelectedModal);
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
            strCidades[i] = String.join("-", cidade.getCodigo(), cidade.getIdentificador(),
                    cidade.getNome());
        }

        JLabel lblOrigem = new JLabel("Origem");
        lblOrigem.setBounds(52, 58, 114, 15);
        panel.add(lblOrigem);
        
        Cidade cidadeOrigem = percurso.getPartida();
        String strCidadeOrigem = String.join("-", cidadeOrigem.getCodigo(),
                cidadeOrigem.getIdentificador(), cidadeOrigem.getNome());

        cboOrigem = new JComboBox<String>(strCidades);
        cboOrigem.setEditable(false);
        cboOrigem.setSelectedItem(strCidadeOrigem);
        cboOrigem.setBounds(52, 78, 114, 24);
        panel.add(cboOrigem);

        JLabel lblDestino = new JLabel("Destino");
        lblDestino.setBounds(248, 58, 114, 15);
        panel.add(lblDestino);

        Cidade cidadeDestino = percurso.getPartida();
        String strCidadeDestino = String.join("-", cidadeDestino.getCodigo(),
                cidadeDestino.getIdentificador(), cidadeDestino.getNome());

        cboDestino = new JComboBox<String>(strCidades);
        cboDestino.setEditable(false);
        cboDestino.setSelectedItem(strCidadeDestino);
        cboDestino.setBounds(248, 78, 114, 24);
        panel.add(cboDestino);

        JLabel lblHoraPartida = new JLabel("Hora da Partida");
        lblHoraPartida.setBounds(52, 104, 114, 15);
        panel.add(lblHoraPartida);

        txtHoraPartida = new JTextField();
        txtHoraPartida.setEditable(false);
        txtHoraPartida.setText(Constants.DATETIME_FORMAT.format(percurso.getHoraPartida()));
        txtHoraPartida.setBounds(52, 125, 114, 19);
        panel.add(txtHoraPartida);

        JLabel lblDuracao = new JLabel("Duracao");
        lblDuracao.setBounds(52, 150, 114, 15);
        panel.add(lblDuracao);

        txtDuracao = new JTextField();
        txtDuracao.setText(Integer.toString(percurso.getHorasDuracaoPercurso()));
        txtDuracao.setBounds(52, 171, 114, 19);
        panel.add(txtDuracao);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(52, 247, 78, 25);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PercursoController.alterarPercurso(
                        percurso.getId(),
                        cboModal.getSelectedItem().toString().split("-")[0],
                        txtDuracao.getText(),
                        null// codigo_aeroporto_modificado
                );
                dialog.dispose();
            }
        });
        panel.add(btnSalvar);
    }
}
