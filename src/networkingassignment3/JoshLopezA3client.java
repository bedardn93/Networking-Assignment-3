/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkingassignment3;

import java.util.PriorityQueue;
import java.util.*; 
/**
 *
 * @author Nick
 */
public class JoshLopezA3client {

    
    public static void Dijkstra(Vertex source){
        source.minDis = 0;
        PriorityQueue<Vertex> vertex = new PriorityQueue<>();
      	vertex.add(source);

	while (!vertex.isEmpty()) {
	    Vertex u = vertex.poll();

            for (Edge edge : u.adj)
            {
                Vertex vert = edge.node;
                double weight = edge.weight;
                double distance = u.minDis + weight;
		if (distance < vert.minDis) {
		    vertex.remove(vert);
		    vert.minDis = distance;
		    vert.prev = u;
		    vertex.add(vert);
		}
            }
        }
    }
    
    private static class Vertex implements Comparable<Vertex>{
	String name;
	Edge[] adj;
	double minDis = Double.POSITIVE_INFINITY;
	Vertex prev;

	public Vertex(String name){
	    this.name = name;    
	}

	public String getName(){
	    return name;
	}

	public int compareTo(Vertex node){
	    return Double.compare(minDis, node.minDis);
	}
    }
    private static class Edge {
    
	Vertex node;
	double weight;

	public Edge(){}

	public Edge(Vertex node, double weight){
	    this.node = node;
	    this.weight = weight;
	}

	public Vertex getNode(){
	    return node;
	}

	public double getWeight(){
	    return weight;
	}
    }
    

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        int user_input;
        
        //read in the WT.txt
        
        
        
        //sent WT to the server
        
        
        System.out.println ("Enter an options 1 or 2");
        user_input = input.nextInt();
        
        
        //read in the DVR.txt
        
        
        
        //sent DVR to the server
        
        
        //send user input to the server 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        Vertex v0 = new Vertex("v0");
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");
        Vertex v8 = new Vertex("v8");
        Vertex v9 = new Vertex("v9");
        v0.adj = new Edge[]{ new Edge(v1,1),
                                new Edge(v4,1)};
        v1.adj = new Edge[]{ new Edge(v4,1),
                                new Edge(v5,1),
                                new Edge(v2,1) };
        v2.adj = new Edge[]{ new Edge(v5,1),
                                new Edge(v6,1)};
        v3.adj = new Edge[]{ new Edge(v0,1),
                                new Edge(v7,1)};
        v4.adj = new Edge[]{ new Edge(v3,1),
                                new Edge(v7,1),
                                new Edge(v8,1),
                                new Edge(v5,1)};
        v5.adj = new Edge[]{ new Edge(v8,1),
                                new Edge(v9,1),
                                new Edge(v6,1)};
        v6.adj = new Edge[]{ new Edge(v6,0)};
        v7.adj = new Edge[]{ new Edge(v7,0)};
        v8.adj = new Edge[]{ new Edge(v7,1),
                                new Edge(v9,1)};
        v9.adj = new Edge[]{ new Edge(v6,1)};
        Vertex[] vert = { v0, v1, v2, v3, v4, v5, v6, v7, v8, v9};

        Dijkstra(v0);
        System.out.println("From\tTo\tShortest Path");
        for (Vertex vertex : vert)
            System.out.println(v0.name +"\t" + vertex.name + "\t" + (int) vertex.minDis);
        
        try{
            Thread.sleep(3000);
        }catch(Exception e){}
        
        System.out.println("\nV3->V0, V9->V6, and V2->V5 edge weights changed to -1.");
        
        Vertex v10 = new Vertex("v0");
        Vertex v11 = new Vertex("v1");
        Vertex v12 = new Vertex("v2");
        Vertex v13 = new Vertex("v3");
        Vertex v14 = new Vertex("v4");
        Vertex v15 = new Vertex("v5");
        Vertex v16 = new Vertex("v6");
        Vertex v17 = new Vertex("v7");
        Vertex v18 = new Vertex("v8");
        Vertex v19 = new Vertex("v9");
        v10.adj = new Edge[]{ new Edge(v11,1),
                                new Edge(v14,1)};
        v11.adj = new Edge[]{ new Edge(v14,1),
                                new Edge(v15,1),
                                new Edge(v12,1) };
        v12.adj = new Edge[]{ new Edge(v15,-1),
                                new Edge(v16,1)};
        v13.adj = new Edge[]{ new Edge(v10,-1),
                                new Edge(v17,1)};
        v14.adj = new Edge[]{ new Edge(v13,1),
                                new Edge(v17,1),
                                new Edge(v18,1),
                                new Edge(v15,1)};
        v15.adj = new Edge[]{ new Edge(v18,1),
                                new Edge(v19,1),
                                new Edge(v16,1)};
        v16.adj = new Edge[]{ new Edge(v16,0)};
        v17.adj = new Edge[]{ new Edge(v17,0)};
        v18.adj = new Edge[]{ new Edge(v17,1),
                                new Edge(v19,1)};
        v19.adj = new Edge[]{ new Edge(v16,-1)};
        Vertex[] vert1 = { v10, v11, v12, v13, v14, v15, v16, v17, v18, v19};

        Dijkstra(v10);
        System.out.println("From\tTo\tShortest Path");
        for (Vertex vertex : vert1)
            System.out.println(v10.name +"\t" + vertex.name + "\t" + (int) vertex.minDis);
    }
    
}
