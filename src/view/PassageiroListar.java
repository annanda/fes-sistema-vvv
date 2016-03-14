package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.PassageiroController;
import model.Passageiro;

@SuppressWarnings("serial")
public class PassageiroListar extends JInternalFrame {

    private JTable table;

    /**
     * Create the frame.
     */
    public PassageiroListar() {
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
                ""
            }
        ));
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);

        Action editAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                PassageiroController.alterarPassageiro(
                        (int) table.getValueAt(row, 0),
                        table.getValueAt(row, 2).toString(),
                        table.getValueAt(row, 3).toString(),
                        table.getValueAt(row, 6).toString(),
                        table.getValueAt(row, 7).toString(),
                        table.getValueAt(row, 8).toString()
                );
            }
        };
        @SuppressWarnings("unused")
        ButtonColumn buttonCol = new ButtonColumn(table, editAction, table.getColumnCount() - 1);

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
                "Salvar",
            };
            data[i] = obj;
        }
        return data;
    }
}
