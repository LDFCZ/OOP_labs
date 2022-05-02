import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sessions.Session;

@Component
public class User2 {
    @Autowired
    private Session session;

    public void test() {
        System.out.println(session.getId());
    }
}
