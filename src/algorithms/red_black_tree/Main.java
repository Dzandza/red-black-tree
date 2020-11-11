package algorithms.red_black_tree;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        RedBlackTree<Integer> tree = new RedBlackTree<>();
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 7, 10, 12, 14, 15, 16, 17, 19, 20, 21, 23, 26, 28, 30, 35, 38, 39, 41, 47));

        for (Integer element : list) {
            System.out.println("\n\nAfter inserting element " + element + ":");
            tree.insert(element);
            System.out.println(tree);

        }



    }
}
