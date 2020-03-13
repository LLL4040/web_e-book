package backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class RmiClient {

    @Bean(name = "rmiService")
    public RmiProxyFactoryBean getUserService() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:1099/BookService");
        rmiProxyFactoryBean.setServiceInterface(BookRmiService.class);
        return rmiProxyFactoryBean;
    }
}
