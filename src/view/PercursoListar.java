package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.PercursoController;
import model.Percurso;

@SuppressWarnings("serial")
public class PercursoListar extends JInternalFrame {

    private JTable table;

    /**
     * Create the frame.
     */
    public PercursoListar() {
        setBounds(100, 100, 450, 300);
        
        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            getData(),
            new String[] {
                "Id",
                "Modal",
                "Origem",
                "Destino",
                "Hora da Partida",
                "Duracao",
                "Excluir"
            }
        ));
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);

        Action editAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                PercursoController.deletarPercurso((int) table.getValueAt(row, 0));
            }
        };
        @SuppressWarnings("unused")
        ButtonColumn buttonCol = new ButtonColumn(table, editAction, table.getColumnCount() - 1);

        scrollPane.setViewportView(table);
    }
    
    private Object[][] getData() {
        ArrayList<Percurso> percursos = PercursoController.listarPercursos("", "", "", "");
        Object[][] data = new Object[percursos.size()][];
        for (int i = 0; i < percursos.size(); i++) {
            Percurso p = percursos.get(i);
            Object[] obj = {
                p.getId(),
                p.getModal().getCodigo(),
                p.getPartida().getIdentificador(),
                p.getDestino().getIdentificador(),
                p.getHoraPartida(),
                p.getHorasDuracaoPercurso(),
                "Excluir",
            };
            data[i] = obj;
        }
        return data;
    }
}
