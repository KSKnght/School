import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainApp {
    private static BST binaryTree = new BST();
    private static Software soft;
    private static Scanner in = new Scanner(System.in);

    public static void scanText() {
        try {
            File file = new File("software.txt");
            if (file.createNewFile()){}
            else {}
        } catch (IOException e){}

        try {
            File file = new File("software.txt");
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                String name = in.nextLine();
                String ver = in.nextLine();
                int qty = Integer.parseInt(in.nextLine());
                int price = Integer.parseInt(in.nextLine());
                
                soft = new Software(name, ver, qty, price);
                binaryTree.insert(soft);
            }
            in.close();    
        } catch (IOException e) {}
    }

    public static void addSoftware() {
        System.out.println("enter the name of the software:");
        String name = in.nextLine();

        if (binaryTree.search(name)){
                System.out.println("application exists! Which version?");
                String ver = in.nextLine();
                if (binaryTree.searchVer(name, ver)) {
                    binaryTree.addExistingSoftware(name, ver, 1);
                    System.out.println("Successfully added!");
                } else if (!binaryTree.searchVer(name, ver)) {
                    System.out.println("application does not exists in the entry! please enter the needed details:");
                    System.out.println("price:");
                    int price = Integer.parseInt(in.nextLine());
                    binaryTree.insert(new Software(name, ver, 1, price));
                }
        } else if (!binaryTree.search(name)) {
            System.out.println("application does not exists in the entry! please enter the needed details:");
            System.out.println("version:");
            String ver = in.nextLine();
            System.out.println("price:");
            int price = Integer.parseInt(in.nextLine());
            binaryTree.insert(new Software(name, ver, 1, price));
        }
        System.out.println("done");
    }

    public static void showSoftwares() {
        System.out.printf("%-30s%-10s%-10s%-10s\n", "Software name", "Version", "Quantity", "Price");
        binaryTree.inorder();
        System.out.println("Press Enter to continue...");
        in.nextLine();
    }

    public static void buySoft() {
        System.out.println("Enter the Software you want to buy: ");
        String name = in.nextLine();

        if (binaryTree.search(name)){
                System.out.println("application exists! Which version?");
                String ver = in.nextLine();

                if (binaryTree.searchVer(name, ver)) {
                    System.out.println("enter quantity:");
                    int qty = Integer.parseInt(in.nextLine());
                    System.out.printf("%-30s%-10s%-10s%-10s\n", "Software name", "Version", "Price", "Quantity");
                    binaryTree.selected(name, ver, qty);
                    System.out.println("Confirm? y or n");
                    String confirm = in.nextLine();
                    if (confirm.equalsIgnoreCase("y")) {
                        binaryTree.buyExistingSoftware(name, ver, qty);
                        System.out.println("successfully purchased!");
                    }
                }
                else {
                    System.out.println("Version not found!");
                }
        } else if (!binaryTree.search(name)) {
            System.out.println("sorry the software is already sold out!");
        }
    }

    public static void sellSoft() {
        System.out.println("enter the name of the software:");
        String name = in.nextLine();

        if (binaryTree.search(name)){
            System.out.println("application exists! Which version?");
            String ver = in.nextLine();

            if (binaryTree.searchVer(name, ver)) {
                System.out.println("How much?");
                int q = Integer.parseInt(in.nextLine());
                binaryTree.addExistingSoftware(name, ver, q);
                System.out.println("Successfully added!");
            }
            else
                System.out.println("Version not found! please enter the needed details:");
                System.out.println("price:");
                int price = Integer.parseInt(in.nextLine());
                System.out.println("quantity: ");
                int q = Integer.parseInt(in.nextLine());
                binaryTree.insert(new Software(name, ver, q, price));
        } else if (!binaryTree.search(name)) {
            System.out.println("application does not exists in the entry! please enter the needed details:");
            System.out.println("version:");
            String ver = in.nextLine();
            System.out.println("price:");
            int price = Integer.parseInt(in.nextLine());
            System.out.println("quantity: ");
            int q = Integer.parseInt(in.nextLine());
            binaryTree.insert(new Software(name, ver, q, price));
        }
        System.out.println("done");
    }

    public static void main(String[] args) throws IOException {
        scanText();

        while (true) {
            System.out.println("-----------------------");
            System.out.println("  S - show softwares   ");
            System.out.println("  A - add a software   ");
            System.out.println("  B - buy a software   ");
            System.out.println("  D - sell a software  ");
            System.out.println("  E - exit             ");
            System.out.println("-----------------------");

            String input = in.nextLine();
            if (input.equalsIgnoreCase("E")) 
                break;
            else if (input.equalsIgnoreCase("S"))
                showSoftwares();
            else if (input.equalsIgnoreCase("A"))
                addSoftware();
            else if (input.equalsIgnoreCase("B"))
                buySoft();
            else if (input.equalsIgnoreCase("D"))
                sellSoft();
            else
                System.out.println("invalid input");
        }

        File file = new File("software.txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        binaryTree.inorderPrint(pw);
        pw.close();
        
        in.close();
    }
}
