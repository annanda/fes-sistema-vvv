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

import controller.ReservaController;
import model.Reserva;

@SuppressWarnings("serial")
public class ReservaListar extends JInternalFrame {

    private JTable table;

    /**
     * Create the frame.
     */
    public ReservaListar() {
        setBounds(100, 100, 450, 300);
        
        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            getData(),
            new String[] {
                "Id",
                "Codigo",
                "Viagem",
                "Valor",
                "Data da Reserva",
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
                ReservaController.deletarReserva((int) table.getValueAt(row, 0));
            }
        };
        @SuppressWarnings("unused")
        ButtonColumn buttonCol = new ButtonColumn(table, editAction, table.getColumnCount() - 1);

        scrollPane.setViewportView(table);
    }
    
    private Object[][] getData() {
        ArrayList<Reserva> reservas = ReservaController.listarReservas("", "", "", "", "");
        Object[][] data = new Object[reservas.size()][];
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            Object[] obj = new Object[] {
                r.getId(),
                r.getCodigo(),
                r.getViagem().getNomeDoPacote(),
                r.getValor(),
                r.getDataDaReserva().toString(),
                "Excluir",
            };
            data[i] = obj;
        }
        return data;
    }
}
