package backend.Controller;

import backend.Service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/visit")
public class VisitController {
    @Autowired
    VisitService vs;

    @GetMapping("/count")
    public Long getCount(){
        // 获取访问量信息
        String txtFilePath = "D:/myGithub/web e-book/ebook-springboot/src/main/java/count.txt";
        Long count = vs.getVisitCount(txtFilePath);
        System.out.println(count);
        return count;
    }
}
