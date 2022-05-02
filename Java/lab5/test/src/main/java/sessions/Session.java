package sessions;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Session {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
