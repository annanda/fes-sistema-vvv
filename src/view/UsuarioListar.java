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

import controller.UsuarioController;
import model.Usuario;

@SuppressWarnings("serial")
public class UsuarioListar extends JInternalFrame {

    private JTable table;

    /**
     * Create the frame.
     */
    public UsuarioListar() {
        setBounds(100, 100, 450, 300);
        
        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            getData(),
            new String[] {
                "Id", "Codigo", "Nome", "Endereco", "Email", "Nivel de Permissao", "Excluir"
            }
        ));
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);

        Action editAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                UsuarioController.deletarUsuario((int) table.getValueAt(row, 0));
            }
        };
        @SuppressWarnings("unused")
        ButtonColumn buttonCol = new ButtonColumn(table, editAction, table.getColumnCount() - 1);

        scrollPane.setViewportView(table);
    }
    
    private Object[][] getData() {
        ArrayList<Usuario> usuarios = UsuarioController.listarUsuarios("", "", "", "");
        Collections.sort(usuarios, new Comparator<Usuario>() {
            public int compare(Usuario a, Usuario b) {
                return a.getCodigo().compareTo(b.getCodigo());
            }
        });

        Object[][] data = new Object[usuarios.size()][];
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usu = usuarios.get(i);
            Object[] obj = {
                usu.getId(),
                usu.getCodigo(),
                usu.getNome(),
                usu.getEndereco(),
                usu.getEmail(),
                usu.getNivelPermissao(),
                "Excluir",
            };
            data[i] = obj;
        }
        return data;
    }
}
