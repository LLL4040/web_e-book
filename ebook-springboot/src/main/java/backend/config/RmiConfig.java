package backend.config;

import backend.Service.BookRmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class RmiConfig {
    @Autowired
    private BookRmiService bookRmiService;

    @Bean
    public RmiServiceExporter getRmiServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("BookService");
        rmiServiceExporter.setService(bookRmiService);
        rmiServiceExporter.setServiceInterface(BookRmiService.class);
        rmiServiceExporter.setRegistryPort(1099);
        return rmiServiceExporter;
    }
}
