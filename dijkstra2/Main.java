import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException { 
        ArrayList<Edge>[] graph;
        File file = new File("graph.txt");
        Scanner in = new Scanner(System.in);
        Scanner sizeCheck = new Scanner(file);
        Scanner edgeCheck = new Scanner(file);
        int size = 0;

        while(sizeCheck.hasNextLine()){
            String temp = sizeCheck.nextLine();
            if (temp.length() == 1)
                size++; 
        }

        graph = new ArrayList[size];
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        while(edgeCheck.hasNextLine()){
            String temp = edgeCheck.nextLine();
            if (temp.length() != 1){
                String length = "" + temp.charAt(4);
                if (temp.length() == 6)
                    length += temp.charAt(5);
                int weight = Integer.parseInt(length);
                graph[temp.charAt(0)-65].add(new Edge(temp.charAt(0), temp.charAt(2), weight));
            }
        }

        System.out.print("Starting Vertex: ");
	    char src = in.next().charAt(0);
	    Queue<VertexWeight> Traverse = new LinkedList<>();
	    Traverse.add(new VertexWeight(src, 0));
        ArrayList<VertexWeight> shortestPath = new ArrayList<>();

	    while (Traverse.size() > 0) {
	        VertexWeight topEle = Traverse.remove();
            
            if(shortestPath.isEmpty())
                shortestPath.add(topEle);

            else if (!shortestPath.isEmpty()){
                boolean vertexAlreadyListed = false;
                VertexWeight tmp;
                int index = 0;
                for(VertexWeight e : shortestPath){
                    if (e.vertex == topEle.vertex){
                        vertexAlreadyListed = true;
                        tmp = e;
                        if (topEle.wsf < tmp.wsf)
                        shortestPath.set(index, topEle);
                        break;
                    }
                    index++;
                }
                if (vertexAlreadyListed == false)
                shortestPath.add(topEle);
            }

            for (Edge edge : graph[topEle.vertex-65]) {
                Traverse.add(new VertexWeight(edge.nbr, topEle.wsf + edge.weight ));
            }
	    }

        for (VertexWeight r : shortestPath)
            if(r.wsf != 0)
                System.out.println(r.vertex + " " + r.wsf);
        sizeCheck.close();
        edgeCheck.close();
        in.close();
	}
}