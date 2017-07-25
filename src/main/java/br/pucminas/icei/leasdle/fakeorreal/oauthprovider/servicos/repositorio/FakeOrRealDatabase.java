package br.pucminas.icei.leasdle.fakeorreal.oauthprovider.servicos.repositorio;

import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.excecoes.UsuarioOuSenhaInvalidaException;
import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.modelos.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gustavo on 7/21/17.
 */
public class FakeOrRealDatabase {

    private static FakeOrRealDatabase instance;

    private enum QueryBy {
        CPF("WHERE p.cpf LIKE "),
        ID("WHERE p.id LIKE ");

        private static final String SELECT = "SELECT p.id,p.name,p.cpf,e.email FROM person p "
                + "JOIN person_has_email phe ON (p.id=phe.person_id) "
                + "JOIN email e ON (phe.email_id=e.id) ";
        private final String query;

        private QueryBy(String query) {
            this.query = SELECT + query;
        }
    }

    private enum DatabaseConnectionParams {
        LOCAL_GUSTAVO("jdbc:mysql://localhost:3306/fake_or_realDB", "root", "root", "com.mysql.jdbc.Driver"),
        LOCAL_ICEI("jdbc:mysql://localhost:3306/fake_or_realDB", "root", "pucmg!2016", "com.mysql.jdbc.Driver");

        private final String user, url, pass, driver;

        private DatabaseConnectionParams(String url, String user, String pass, String driver) {
            this.user = user;
            this.url = url;
            this.pass = pass;
            this.driver = driver;
        }
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        DatabaseConnectionParams dc = DatabaseConnectionParams.LOCAL_ICEI;

        Class.forName(dc.driver);
        return DriverManager.getConnection(
                dc.url,
                dc.user,
                dc.pass);
    }

    public static FakeOrRealDatabase getInstance() {
        if (instance == null) {
            instance = new FakeOrRealDatabase();
        }
        return instance;
    }

    public Usuario getById(int... id) {
        String whereStatement;
        if (id.length > 0) {
            String delimiter = " OR ";
            whereStatement = String.valueOf(id[0]);
            for (int i = 1; i < id.length; i++) {
                whereStatement = whereStatement + delimiter + String.valueOf(id[i]);
            }
        } else {
            whereStatement = "";
        }
        return null;
    }

    public Usuario getByCpf(String cpf) {
        try {        	
        	ResultSet resultSet = execute(QueryBy.CPF.query + cpf);
        	if(resultSet.next()) {
        		return getUsuario(resultSet);
        	}else {
        		throw new UsuarioOuSenhaInvalidaException("Usuário não encontrado.");
        	}
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FakeOrRealDatabase.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsuarioOuSenhaInvalidaException("Erro ao buscar usuário.");
        }
    }

    public ArrayList<Usuario> getAll() {
        ArrayList<Usuario> us = new ArrayList<>();
        try {
            ResultSet rs = execute(QueryBy.SELECT);
            while (rs.next()) {
                us.add(getUsuario(rs));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FakeOrRealDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }

    private ResultSet execute(String query) throws SQLException, ClassNotFoundException {
        return getConnection()
                .createStatement()
                .executeQuery(query);
    }

    private Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId((long) rs.getInt("id"));
        u.setNome(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setLogin(rs.getString("cpf"));
        u.setSenha("");
        return u;
    }

}
