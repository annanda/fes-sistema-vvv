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
public class ReservaConfirmar extends JInternalFrame {

    private JTable table;

    /**
     * Create the frame.
     */
    public ReservaConfirmar() {
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
                "Confirmar",
            }
        ));
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);

        Action editAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                int id_reserva = (int) table.getValueAt(row, 0);
                Reserva reserva = ReservaController.getReservaById(id_reserva);
                ReservaController.alterarReserva(id_reserva, true, reserva.getTipoPagamento(), reserva.getQtdParcelas());
            }
        };
        @SuppressWarnings("unused")
        ButtonColumn buttonCol = new ButtonColumn(table, editAction, table.getColumnCount() - 1);

        scrollPane.setViewportView(table);
    }
    
    private Object[][] getData() {
        ArrayList<Reserva> reservas = ReservaController.listarReservas("", "", "", "", "");
        ArrayList<Reserva> reservas_nao_confirmadas = new ArrayList<Reserva>();
        for (Reserva r : reservas) {
            if (r.getStatus() == false) {
                reservas_nao_confirmadas.add(r);
            }
        }
        Object[][] data = new Object[reservas_nao_confirmadas.size()][];
        for (int i = 0; i < reservas_nao_confirmadas.size(); i++) {
            Reserva r = reservas_nao_confirmadas.get(i);
            Object[] obj = new Object[] {
                r.getId(),
                r.getCodigo(),
                r.getViagem().getNomeDoPacote(),
                r.getValor(),
                r.getDataDaReserva().toString(),
                "Confirmar",
            };
            data[i] = obj;
        }
        return data;
    }
}
