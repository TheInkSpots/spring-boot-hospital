import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

public class test {
    
}

@Controller
public class UserController {

    @Autowired
    private S s ;

    public ResponseEntity<String> controller() {
        
        return service.logic();
    }
}

@Service
public class ServiceA extends S{
    public ResponseEntity<String> logic() {
        return ResponseEntity.ok();
    }
}

public class ServiceB extends S{
    public ResponseEntity<String> logic() {
        return ResponseEntity.ok();
    }
}