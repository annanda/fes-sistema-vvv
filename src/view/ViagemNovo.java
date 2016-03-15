package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PercursoController;
import controller.ViagemController;
import model.Percurso;

@SuppressWarnings("serial")
public class ViagemNovo extends JInternalFrame {

    private JTextField txtNomePacote;
    private JList<String> lstPercursos;

    /**
     * Create the frame.
     */
    public ViagemNovo() {
        setBounds(100, 100, 430, 320);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel lblNomePacote = new JLabel("Nome do Pacote");
        lblNomePacote.setBounds(52, 12, 150, 15);
        panel.add(lblNomePacote);

        txtNomePacote = new JTextField();
        txtNomePacote.setBounds(52, 33, 310, 19);
        panel.add(txtNomePacote);

        ArrayList<Percurso> percursos = PercursoController.listarPercursos("", "", "", "");
        String[] data = new String[percursos.size()];
        for (int i = 0; i < percursos.size(); i++) {
            Percurso p = percursos.get(i);
            data[i] = String.join(" - ", Integer.toString(p.getId()),
                    p.getPartida().getIdentificador(), p.getDestino().getIdentificador(),
                    p.getHoraPartida().toString(), Integer.toString(p.getHorasDuracaoPercurso()));
        }

        JLabel lblPercursos = new JLabel("Percursos");
        lblPercursos.setBounds(52, 58, 114, 15);
        panel.add(lblPercursos);

        lstPercursos = new JList<String>(data);
        lstPercursos.setBounds(52, 80, 310, 155);
        panel.add(lstPercursos);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(52, 247, 78, 25);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<String> percursos = lstPercursos.getSelectedValuesList();
                String[] ids_percursos = new String[percursos.size()];
                for (int i = 0; i < percursos.size(); i++) {
                    ids_percursos[i] = percursos.get(i).split(" - ")[0];
                }
                ViagemController.cadastrarViagem(txtNomePacote.getText(), ids_percursos);
                clear();
            }
        });
        panel.add(btnSalvar);
    }

    public void clear() {
        txtNomePacote.setText("");
        lstPercursos.clearSelection();
    }
}
