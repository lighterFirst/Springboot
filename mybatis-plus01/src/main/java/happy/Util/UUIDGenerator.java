package happy.Util;

import java.util.UUID;

public class UUIDGenerator {
    public UUIDGenerator(){

    }
    public static String generete(){
        return UUID.randomUUID().toString();
    }
}
