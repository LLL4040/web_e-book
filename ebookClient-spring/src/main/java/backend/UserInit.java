package backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserInit implements CommandLineRunner {

    @Autowired
    private BookRmiService rmiService;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(rmiService.findAllByBookname("活着"));
    }
}
