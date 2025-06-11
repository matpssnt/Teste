package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    public Conexao() {
        carregarConfiguracoes();
    }

    public void carregarConfiguracoes() {
        Properties props = new Properties();
        try (InputStream inputPropsConfig = getClass().getClassLoader().getResourceAsStream
                ("config.properties")) {
            props.load(inputPropsConfig);
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");

        }
        catch (IOException erro) {
            System.err.println("Erro ao carregar configurações: " + erro.getMessage());
        }

    }

    public Connection conectar() {

        Connection condb = null;

        try {
            //Especifíca a rota do driver a ser carregado (JDBC para SGBD MySql)
            Class.forName(driver);

            //Inicializar o driver já carregado por meio do metodo getConnection(IP, porta, nome do banco, usuario, senha)

            condb = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado com sucesso!");
            return condb;





        } catch (SQLException | ClassNotFoundException erro) {
            System.err.println("Erro ao conectar com o Banco de Dados" + erro);
            erro.printStackTrace();
            return null;
        }

    }
}