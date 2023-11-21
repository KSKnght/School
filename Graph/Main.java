import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("graph.txt");

        if(file.createNewFile()) {}
        else {}
        Scanner checkNodes = new Scanner(file);
        Scanner scanNodes = new Scanner(file);
        int index = 0, edgeIndex = 0;

        while (scanNodes.hasNextLine()) { //scan indexes for array
            String temp = scanNodes.nextLine();
            if (temp.length() == 1)
                index++;
            else if (temp.length() != 1)
                edgeIndex++;
        }

        char graphNodes[] = new char[index];
        String graphEdge[] = new String[edgeIndex];

        index = 0; edgeIndex = 0;

        while(checkNodes.hasNextLine()) { //scan the letters
            String temp = checkNodes.nextLine();
            if (temp.length() == 1)
            graphNodes[index++] = temp.charAt(0);
            //graphNodes.add(temp.charAt(0));
            else if (temp.length() != 1)
            graphEdge[edgeIndex++] = "" + temp.charAt(0) + temp.charAt(2);
            //graphEdge.add("" + temp.charAt(0) + temp.charAt(2));
        }

        int adjacentMatrix[][] = new int[index][index]; //col,row
        int indcidenceMatrix[][] = new int[index][edgeIndex];


        for (int i = 0; i < graphNodes.length; i++) { //evaluating the letters for adjacentMatrix
            for (int j = 0; j < graphEdge.length; j++) {
                String temp = graphEdge[j];
                if (temp.contains("" + graphNodes[i])){
                    temp = temp.replace("" + graphNodes[i], "");
                    for (int k = 0; k < graphNodes.length; k++) 
                        if (graphNodes[k] == temp.charAt(0))
                            adjacentMatrix[i][k] = 1;
                        else if (adjacentMatrix[i][k] == 1)
                            adjacentMatrix[i][k] = 1;
                        else
                            adjacentMatrix[i][k] = 0;
                }
            }
        }

        for (int i = 0; i < graphNodes.length; i++) { //evaluating the incidence
            for (int j = 0; j < graphEdge.length; j++) {
                String temp = graphEdge[j];
                if (temp.contains("" + graphNodes[i]))
                    indcidenceMatrix[i][j] = 1;
                else
                    indcidenceMatrix[i][j] = 0;
            }
        }

        System.out.println("Adjacency Matrix");
        System.out.println("-----------------------------");


        System.out.print("   "); //display adjacent
        for (int i = 0; i < graphNodes.length; i++) {
            System.out.print(graphNodes[i] + "  ");
        }
        System.out.println("");
        for (int i = 0; i < graphNodes.length; i++) {
            System.out.print(graphNodes[i] + "  ");
            for (int j = 0; j < graphNodes.length; j++) {
                System.out.print(adjacentMatrix[i][j] + "  ");
            }
            System.out.println("");
        }

        System.out.println("\n\nIncidence Matrix");
        System.out.println("---------------------------");

        System.out.print("   "); //display indcidence
        for (int i = 0; i < graphEdge.length; i++) {
            System.out.print(graphEdge[i] + " ");
        }
        System.out.println("");
        for (int i = 0; i < graphNodes.length; i++) {
            System.out.print(graphNodes[i] + "  ");
            for (int j = 0; j < graphEdge.length; j++) {
                System.out.print(indcidenceMatrix[i][j] + "  ");
            }
            System.out.println("");
        }

        checkNodes.close();
        scanNodes.close();
    }
}
