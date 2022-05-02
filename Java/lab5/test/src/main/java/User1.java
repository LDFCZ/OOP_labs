import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sessions.Session;

@Component
public class User1 {

    @Autowired
    private Session session;

    public void test() {
        session.setId(1);
        System.out.println(session.getId());
    }
}
