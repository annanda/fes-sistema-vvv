package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Constants;
import model.Percurso;
import model.Viagem;

public class ViagemDAO extends DAO {
    private String tabela = "viagens";
    private String tabela_relacionamento = "viagens_percursos";

    public HashMap<String, Integer> cadastrarViagem(Viagem nova_viagem) {
        // setting labels for reusing
        final String id_viagem_label = "id_viagem";
        final String id_ordem_label = "id_ordem";
        final String id_viagem_percurso_label = "id_viagem_percurso";
        // setting variables and constants
        int id = 0;
        HashMap<String, Integer> ids_map = new HashMap<String, Integer>();
        ArrayList<Percurso> plano_de_viagem = nova_viagem.getPlanoDeViagem();
        final String codigo = nova_viagem.getCodigo();
        // query to insert Viagem object in viagens table
        String sql_query =
                insertFactory(
                        tabela,
                        new String[] { nova_viagem.getNomeDoPacote(),
                                "" + nova_viagem.getLotacao(),
                                Constants.DATE_FORMAT.format(nova_viagem.getDataPartida()),
                                Constants.DATE_FORMAT.format(nova_viagem.getDataChegada()), codigo });

        connect();
        try {
            // inserting Viagem object in viagens table
            statement.executeUpdate(sql_query);

            // query to retrieve the new id inserted in viagens table
            sql_query =
                    selectFactory(tabela, new String[] { id_viagem_label }, "codigo = " + codigo);
            // retrieving the new id inserted in viagens table
            result_set = statement.executeQuery(sql_query);
            if (result_set.first()) { // testing if the Viagem object was successfully inserted
                id = result_set.getInt(id_viagem_label);
                // TODO: Tentar externalizar essa parte em um (ou dois) metodos
                ids_map.put(id_viagem_label, id);
                int ordem = 0; // variable to order the viagem-percurso relationships
                for (Percurso percurso : plano_de_viagem) {
                    // query to insert the percursos related to this viagem
                    sql_query =
                            insertFactory(tabela_relacionamento, new String[] { "" + id,
                                    "" + percurso.getId(), "" + ordem });
                    // inserting percursos related to this viagem
                    statement.executeUpdate(sql_query);
                }

                // query to retrieve all percursos related to the viagem inserted above
                sql_query =
                        selectFactory(tabela_relacionamento, new String[] {
                                id_viagem_percurso_label, id_ordem_label }, "id_viagem = " + id);
                // retrieving all percursos related to the viagem inserted above
                result_set = statement.executeQuery(sql_query);
                while (result_set.next()) {
                    // setting the ids_map with the percursos related with the viagem inserted. Each
                    // percurso is set with "percurso <ordem column>" key and int id_viagem_percurso
                    // column value
                    ids_map.put("percurso " + result_set.getInt(id_ordem_label),
                            result_set.getInt(id_viagem_percurso_label));
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        disconnect();

        return ids_map;
    }

    public ArrayList<Viagem> listarViagens(HashMap<String, String> conditions) {
        // TODO Auto-generated method stub
        return null;
    }

    public Viagem getViagemByCodigo(String codigo) {
        // TODO Auto-generated method stub
        return null;
    }

    public Viagem getViagemById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    public void alterarViagem(Viagem viagem_modificada) {
        // TODO Auto-generated method stub

    }
}
