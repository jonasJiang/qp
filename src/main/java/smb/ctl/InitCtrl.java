package smb.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitCtrl {
    
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!!";
    }
    
    @RequestMapping("/initdata")
    @ResponseBody
    JsonObj initdata() {
        JsonObj json = new JsonObj();
        return json;
    }
    

}
