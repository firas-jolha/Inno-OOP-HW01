package bank.utils;

public class Utils {
    public static boolean notNull(Object... objects){
        for (Object o:objects) {
            if (o==null) return false;
        }
        return true;
    }
}
