package algorithms.red_black_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static RedBlackTree<Integer> tree = new RedBlackTree<>();

    public static void runDefault() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(6, 11, 10, 2, 9, 7, 5, 13, 22, 27, 36, 12, 31));
        ArrayList<Integer> deleteList = new ArrayList<>(Arrays.asList(5, 27, 36, 12, 11));

        for (Integer element : list) {
            System.out.println("\n\nAfter inserting element " + element + ":");
            tree.insert(element);
            Utility.inorderPrint(tree.getRoot());
            System.out.println("\n\n" + tree);

        }

        for (Integer element : deleteList) {
            System.out.println("\n\nAfter delete element " + element + ":");
            tree.delete(element);
            Utility.inorderPrint(tree.getRoot());
            System.out.println("\n\n" + tree);
        }
    }

    public static void runProgram() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Odaberite opciju:\n 1 - Unos broja\n " +
                    "2 - Brisanje broja\n " +
                    "3 - Preorder ispis\n " +
                    "4 - Inorder ispis\n " +
                    "5 - Postorder ispis\n " +
                    "6 - Graficki prikaz stabla\n " +
                    "0 - Kraj\nVaš odabir: ");
            String input = scanner.nextLine();
            System.out.println();
            try {
                Integer option = Integer.parseInt(input);
                if (option == 0) {
                    System.out.println("Odabrali ste kraj programa.");
                    break;
                } else if (option == 1) {
                    while (true) {
                        System.out.print("Unesite novi cijeli broj: ");
                        input = scanner.nextLine();
                        try {
                            Integer newValue = Integer.parseInt(input);
                            tree.insert(newValue);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Neispravan unos. Pokušajte ponovo...");
                        } catch (Exception e) {
                            System.out.println("Došlo je do greške pri unosu. Greška: " + e.getStackTrace());
                            break;
                        }
                    }
                } else if (option == 2) {
                    while (true) {
                        System.out.print("Unesite cijeli broj za brisanje: ");
                        input = scanner.nextLine();
                        try {
                            Integer newValue = Integer.parseInt(input);
                            tree.delete(newValue);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Neispravan unos. Pokušajte ponovo...");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage() + " Pokušajte ponovo...");
                        } catch (Exception e) {
                            System.out.println("Došlo je do greške pri unosu. Greška: " + e.getStackTrace());
                            break;
                        }

                    }
                } else if (option == 3) {
                    System.out.print("Preorder: ");
                    Utility.preorderPrint(tree.getRoot());
                } else if (option == 4) {
                    System.out.print("Inorder: ");
                    Utility.inorderPrint(tree.getRoot());
                } else if (option == 5) {
                    System.out.print("Postorder: ");
                    Utility.postorderPrint(tree.getRoot());
                } else if (option == 6) {
                    System.out.println(tree);
                } else {
                    System.out.println("Nepostojeca opcija. Pokušajte ponovo...");
                }

                System.out.println("\n\n\n");
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos. Pokušajte ponovo...");
            }
        }
    }

    public static void main(String[] args) {

//        runDefault();
        runProgram();

    }
}
