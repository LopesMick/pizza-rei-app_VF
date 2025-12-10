package br.senac.rj.backend;

import java.util.EnumSet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import br.senac.rj.backend.config.CorsFilter;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.DispatcherType;

/**
 * 
 * @author reinaldo.jose
 * Classe que configura e inicia o servidor.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        // 1) Força inicialização do Hibernate/JPA
        // Isso garante que o hbm2ddl.auto=update rode e as tabelas sejam criadas/atualizadas
        // conforme o persistence.xml
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("backendPU");

        // 2) Configura o servidor HTTP Jetty para escutar na porta 8080
        int port = 8080;
        Server server = new Server(port);

        // 3) Configura um contexto com suporte a sessões
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        // Define que o contexto irá atuar na raiz (/) do servidor
        context.setContextPath("/");

        // 4) Adiciona o servlet Jersey ao contexto
        // Configura o Servlet Jersey para operar na rota /api a partir da raiz
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/api/*");

        // Aponta para a classe Application que configura o Servlet Jersey
        jerseyServlet.setInitParameter(
                "jakarta.ws.rs.Application",
                "br.senac.rj.backend.config.JaxRsApplication"
        );

        // 5) Configuração do filtro de CORS
        context.addFilter(CorsFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        // 6) Define o handler do servidor
        server.setHandler(context);

        // 7) Fecha o EntityManagerFactory ao encerrar o servidor
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                if (emf.isOpen()) {
                    emf.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        // 8) Inicia o servidor
        try {
            server.start();
            System.out.println("Servidor iniciado em http://localhost:" + port + "/api");
            server.join();
        } finally {
            try {
                if (emf.isOpen()) {
                    emf.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
