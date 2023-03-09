package e2;

import java.util.function.BiPredicate;

public class Utils {

    public static int countChecked(final ActionGrid grid) {
        int count = 0;
        for (int i = 0; i < grid.rows(); i++) {
            for (int j = 0; j < grid.columns(); j++) {
                if(grid.check(i,j)){
                    count ++;
                }
            }
        }
        return count;
    }

    public static int countMines(final BiPredicate<Integer,Integer> predicate, final int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(predicate.test(i,j)){
                    count ++;
                }
            }
        }
        return count;
    }
}
