package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.PassageiroController;
import model.Passageiro;

@SuppressWarnings("serial")
public class PassageiroListar extends JInternalFrame {

    @SuppressWarnings("unused")
    private JFrame frame;
    private JTable table;

    /**
     * Create the frame.
     */
    public PassageiroListar(JFrame frame) {
        this.frame = frame;
        
        setBounds(100, 100, 450, 300);
        
        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            getData(),
            new String[] {
                "Id",
                "Codigo",
                "Nome",
                "Endereco",
                "CPF",
                "Data de Nascimento",
                "Telefone",
                "Profissao",
                "Responsavel",
                "Editar",
                "Excluir",
            }
        ));
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);

        Action editAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                int id = (int) table.getValueAt(row, 0);
                PassageiroEditar editView = new PassageiroEditar(frame, id);
                editView.setVisible(true);
            }
        };
        @SuppressWarnings("unused")
        ButtonColumn editBtnCol = new ButtonColumn(table, editAction, table.getColumnCount() - 2);

        Action deleteAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                PassageiroController.deletarPassageiro((int) table.getValueAt(row, 0));
            }
        };
        @SuppressWarnings("unused")
        ButtonColumn deleteBtnCol = new ButtonColumn(table, deleteAction, table.getColumnCount() - 1);

        scrollPane.setViewportView(table);
    }
    
    private Object[][] getData() {
        ArrayList<Passageiro> passageiros = PassageiroController.listarPassageiros("", "", "", "");
        Collections.sort(passageiros, new Comparator<Passageiro>() {
            public int compare(Passageiro a, Passageiro b) {
                return a.getCodigo().compareTo(b.getCodigo());
            }
        });

        Object[][] data = new Object[passageiros.size()][];
        for (int i = 0; i < passageiros.size(); i++) {
            Passageiro p = passageiros.get(i);
            Passageiro r = p.getResponsavel();
            Object[] obj = {
                p.getId(),
                p.getCodigo(),
                p.getNome(),
                p.getEndereco(),
                p.getCpf(),
                p.getDataDeNascimento(),
                p.getTelefone(),
                p.getProfissao(),
                r != null ? r.getCpf() : "",
                "Editar",
                "Excluir",
            };
            data[i] = obj;
        }
        return data;
    }
}
