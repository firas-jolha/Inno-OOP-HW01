package bank.utils;

public class Utils {
    public static boolean notNull(Object... objects){
        for (int i = 0; i < objects.length; i++) {
            if (objects[i]==null) return false;
        }
        return true;
    }
}
