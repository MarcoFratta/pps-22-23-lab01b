package e2;

import java.util.function.BiConsumer;
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

    public static int countOnAllGrid(final BiPredicate<Integer,Integer> predicate, final int size) {
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

    public static Pair<Integer,Integer> find(final BiPredicate<Integer, Integer> predicate, final int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(predicate.test(i,j)){
                    return new Pair<>(i, j);
                }
            }
        }
        return null;
    }

    public static void foreachCellDo(final BiConsumer<Integer, Integer> action, final int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                action.accept(i,j);

            }
        }
    }
}
