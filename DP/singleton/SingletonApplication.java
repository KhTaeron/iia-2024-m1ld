package singleton;

import java.lang.reflect.Constructor;

public class SingletonApplication {
    public static void main(String[] args) throws Exception {
        // Singleton s1 = new Singleton();
        // Singleton s2 = new Singleton();
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        
        System.out.println(s1 == s2);

        Constructor<Singleton> ctor = Singleton.class.getDeclaredConstructor();

        ctor.setAccessible(true);

        Singleton s3 = ctor.newInstance();
        Singleton s4 = ctor.newInstance();

        System.out.println(s3 == s4);
    }
}
