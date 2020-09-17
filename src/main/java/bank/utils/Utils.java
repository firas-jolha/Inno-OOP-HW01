package bank.utils;

public class Utils {
    /**
     * Checks if all objects are not null
     *
     * @param objects dynamic array of objects
     * @return status of objects
     */
    public static boolean notNull(Object... objects){
        for (Object o:objects) {
            if (o==null) return false;
        }
        return true;
    }
}
