package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controller.PassageiroController;
import controller.ReservaController;
import controller.ViagemController;
import model.Passageiro;
import model.Viagem;

@SuppressWarnings("serial")
public class ReservaNovo extends JInternalFrame {

    @SuppressWarnings("unused")
    private int id_usuario;
    private JList<String> lstViagem;
    private JList<String> lstPassageiros;
    private JTextField txtValor;
    private JRadioButton rdbtnAVista;
    private JRadioButton rdbtnParcelado;
    private JComboBox<String> cboParcelas;
    private JTextField txtCodigo;

    /**
     * Create the frame.
     */
    public ReservaNovo(int id_usuario) {
        this.id_usuario = id_usuario;

        setBounds(100, 100, 430, 320);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel lblViagem = new JLabel("Viagem");
        lblViagem.setBounds(52, 12, 114, 15);
        panel.add(lblViagem);

        ArrayList<Viagem> viagens = ViagemController.listarViagens("", "", "");
        Collections.sort(viagens, new Comparator<Viagem>() {
            public int compare(Viagem a, Viagem b) {
                return a.getCodigo().compareTo(b.getCodigo());
            }
        });
        String[] strViagens = new String[viagens.size()];
        for (int i = 0; i < viagens.size(); i++) {
            Viagem v = viagens.get(i);
            strViagens[i] = String.join(" - ", Integer.toString(v.getId()), v.getCodigo(), v.getNomeDoPacote());
        }

        lstViagem = new JList<String>(strViagens);
        lstViagem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstViagem.setBounds(52, 33, 310, 48);
        panel.add(lstViagem);

        JLabel lblPassageiros = new JLabel("Passageiros");
        lblPassageiros.setBounds(52, 90, 114, 15);
        panel.add(lblPassageiros);

        ArrayList<Passageiro> passageiros = PassageiroController.listarPassageiros("", "", "", "");
        Collections.sort(passageiros, new Comparator<Passageiro>() {
            public int compare(Passageiro a, Passageiro b) {
                return a.getNome().toLowerCase().compareTo(b.getNome().toLowerCase());
            }
        });
        String[] strPassageiros = new String[passageiros.size()];
        for (int i = 0; i < passageiros.size(); i++) {
            Passageiro p = passageiros.get(i);
            strPassageiros[i] = String.join(" - ", Integer.toString(p.getId()), p.getNome());
        }

        lstPassageiros = new JList<String>(strPassageiros);
        lstPassageiros.setBounds(52, 110, 310, 48);
        panel.add(lstPassageiros);

        JLabel lblValor = new JLabel("Valor");
        lblValor.setBounds(52, 165, 49, 15);
        panel.add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(119, 165, 61, 19);
        panel.add(txtValor);

        rdbtnAVista = new JRadioButton("A vista");
        rdbtnAVista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cboParcelas.setEnabled(false);
            }
        });
        rdbtnAVista.setSelected(true);
        rdbtnAVista.setBounds(188, 161, 74, 23);
        panel.add(rdbtnAVista);

        rdbtnParcelado = new JRadioButton("Parcelado");
        rdbtnParcelado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cboParcelas.setEnabled(true);
            }
        });
        rdbtnParcelado.setBounds(266, 161, 96, 23);
        panel.add(rdbtnParcelado);

        ButtonGroup rdGroup = new ButtonGroup();
        rdGroup.add(rdbtnAVista);
        rdGroup.add(rdbtnParcelado);

        JLabel lblParcelas = new JLabel("Parcelas");
        lblParcelas.setBounds(52, 197, 70, 15);
        panel.add(lblParcelas);

        cboParcelas = new JComboBox<String>();
        cboParcelas.setEnabled(false);
        cboParcelas.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6"}));
        cboParcelas.setBounds(119, 192, 60, 24);
        panel.add(cboParcelas);

        JLabel lblCodigo = new JLabel("Codigo");
        lblCodigo.setBounds(52, 226, 49, 15);
        panel.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(119, 224, 61, 19);
        txtCodigo.setColumns(10);
        panel.add(txtCodigo);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(52, 251, 78, 25);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<String> passageiros = lstPassageiros.getSelectedValuesList();
                String[] ids_passageiros = new String[passageiros.size()];
                for (int i = 0; i < passageiros.size(); i++) {
                    ids_passageiros[i] = passageiros.get(i).split(" - ")[0];
                }

                ReservaController.cadastrarReserva(
                        txtCodigo.getText(),
                        Integer.parseInt(cboParcelas.getSelectedItem().toString()),
                        rdbtnAVista.isSelected() ? "0" : "1",
                        Double.parseDouble(txtValor.getText()),
                        false,
                        ids_passageiros,
                        id_usuario,
                        Integer.parseInt(lstViagem.getSelectedValue().split(" - ")[0])
                );
                clear();
            }
        });
        panel.add(btnSalvar);
    }
    
    public void clear() {
        lstViagem.clearSelection();
        lstPassageiros.clearSelection();
        txtValor.setText("");
        rdbtnAVista.setSelected(true);
        cboParcelas.setSelectedIndex(0);
        cboParcelas.setEnabled(false);
        txtCodigo.setText("");
    }
}
