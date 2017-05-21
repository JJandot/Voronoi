import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Voronoi {

    static class TestComp implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }


    public static void main(String[] args) {
        SortedSet<Integer> test = new TreeSet<>(new TestComp());
        for(int i = 0; i < 10; i+=2){
            test.add(i);
        }
        System.out.println(test);
        test.add(3);
        test.add(1);
        test.add(8);
        test.add(16);
        System.out.println(test);
    }
}
