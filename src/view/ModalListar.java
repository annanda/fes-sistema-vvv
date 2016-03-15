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

import controller.ModalController;
import model.Modal;

@SuppressWarnings("serial")
public class ModalListar extends JInternalFrame {

    @SuppressWarnings("unused")
    private JFrame frame;
    private JTable table;

    /**
     * Create the frame.
     */
    public ModalListar(JFrame frame) {
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
                "Tipo",
                "Companhia",
                "Modelo",
                "Ano de Fabricacao",
                "Capacidade",
                "Em Manutencao",
                "Em Uso",
                "Data de Manutencao",
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
                ModalEditar editView = new ModalEditar(frame, id);
                editView.setVisible(true);
            }
        };
        @SuppressWarnings("unused")
        ButtonColumn editBtnCol = new ButtonColumn(table, editAction, table.getColumnCount() - 2);

        Action deleteAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                ModalController.deletarModal((int) table.getValueAt(row, 0));
            }
        };
        @SuppressWarnings("unused")
        ButtonColumn deleteBtnCol = new ButtonColumn(table, deleteAction, table.getColumnCount() - 1);

        scrollPane.setViewportView(table);
    }
    
    private Object[][] getData() {
        ArrayList<Modal> modais = ModalController.listarModais("", "", "", "", "", "");
        Collections.sort(modais, new Comparator<Modal>() {
            public int compare(Modal a, Modal b) {
                return a.getCodigo().compareTo(b.getCodigo());
            }
        });

        Object[][] data = new Object[modais.size()][];
        for (int i = 0; i < modais.size(); i++) {
            Modal p = modais.get(i);
            Object[] obj = {
                p.getId(),
                p.getCodigo(),
                p.getTipo(),
                p.getCompanhia(),
                p.getModelo(),
                p.getAnoFabricacao(),
                p.getCapacidade(),
                p.getEmManutencao(),
                p.getEmUso(),
                p.getDataManutencao(),
                "Editar",
                "Excluir",
            };
            data[i] = obj;
        }
        return data;
    }
}
