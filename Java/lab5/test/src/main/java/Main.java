import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SessionConf.class);
        User1 u1 = applicationContext.getBean(User1.class);
        User2 u2 = applicationContext.getBean(User2.class);

        u1.test();
        u2.test();
    }
}
