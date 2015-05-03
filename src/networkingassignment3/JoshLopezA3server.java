/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkingassignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;




public class JoshLopezA3server extends Thread {
    
    static ArrayList<Vertex> vertices = new ArrayList<>();
    static ArrayList<DVR> dvr = new ArrayList<>();
    private Thread t;
    private String threadName;
    
    public JoshLopezA3server(){}
    
    public JoshLopezA3server (String name){
	threadName = name;
	System.out.println("Creating " + threadName);
    }
    
    public JoshLopezA3server (String name, ArrayList<Vertex> list){
	threadName = name;
	vertices = list;
	System.out.println("Creating " + threadName);
    }
    public static void main(String argv[]) throws Exception{
        String wt;
        String option;
        String httpReturn;
        ServerSocket welcomeSocket = new ServerSocket(33445);
        
                
        //read wt file 
            
            
        //create wt file
        createRouting();
            
        //print routing
        printVertexEdge();
	printRouting();
                
        
        
        
        //creates the server?
	
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            
            //Get the option the user wants
            option = inFromClient.readLine();
	    System.out.println(option);
            
            // the user send option 1
	    if (option.equals("1") ){ 
		//get current time
		long startTime = (System.nanoTime());

                 //get data from client
		int numDVR_messages = Integer.parseInt(inFromClient.readLine());
		
		//for each dvr message
		for (int i = 0; i < numDVR_messages; i++) 
		    updateRouting(inFromClient.readLine());
		//Elapsed time
		long endTime = (System.nanoTime()-startTime);
		System.out.println("Elapsed time (T1): " + (endTime/1000000000.0) + " seconds.");
            
	    
	    }
	    //if option 2 is selected    
	    else if (option.equals("2") ){
		long startTime = (System.nanoTime());

		//thread 1 
		//reads DVR messages from client 
		JoshLopezA3server newThread = new JoshLopezA3server("Thread-1");
		newThread.start();
		int numDVR_messages = Integer.parseInt(inFromClient.readLine());
		String dvrMessage = inFromClient.readLine();

		//thread 2
		//Updates the routing table
		updateRouting(dvrMessage); 

		//prints the routing table 
		printVertexEdge();

		//Elapsed time 
		long endTime = (System.nanoTime()-startTime);
		System.out.println("Elapsed time (T2): " + (endTime/1000000000.0) + " seconds.");




	    } else {
		//invalid choice as in not (1 or 2)
		System.out.println("User choice is invalid");
	    }//end of if block
	    //print routing
	    printVertexEdge();
        }//end of while loop
        
        
    }//end of main

    public void run(){
	System.out.println("New thread running ");
	try{
	    Thread.sleep(50);
	}catch (InterruptedException e){
	    System.out.println("Thread interrupted.");
	}
    }
    private static void updateRouting(String dvrMessage) {
        
    }

    private static void printVertexEdge() {
	for (Vertex vertice : vertices) {
	    System.out.print("( " + vertice.getName() + ", ");
	    for(Edge edge : vertice.adj)
		System.out.print(edge.getWeight() + " ");
	    System.out.print(")\n");
	}
    }
    
    private static void printRouting(){
	System.out.println("Destination\tNext Hop\tDistance");
	for(DVR d : dvr){
	    System.out.println(d.getDestNode()+"\t\t"+d.getNextHop()+"\t\t"+d.getDistance());
	}
    }

    private static void createRouting() {
        URL url = JoshLopezA3server.class.getResource("WT.txt");
	Scanner reader = null;
	try {
	    reader = new Scanner(new File(url.toURI()));
	} catch (URISyntaxException | FileNotFoundException ex) {
	    Logger.getLogger(JoshLopezA3server.class.getName()).log(Level.SEVERE, null, ex);
	}
	int count = 0;
        while(reader.hasNextInt()){
            vertices.add(new Vertex(reader.next()));
            //vertices.get(0).adj = 
                    //new Edge[]{new Edge(vertices.get(0),reader.nextInt())};
	    int weight = reader.nextInt();
	    System.out.println("one "+weight +" "+ weight);
	    if(count!=0)
		vertices.get(count).adj.add(new Edge(vertices.get(count),weight));
	    vertices.get(0).adj.add(new Edge(vertices.get(0),weight));
	    vertices.get(count).printEdges();
	    count++;
        }
	dvr.add(new DVR(vertices.get(0),new Vertex("0"),new Vertex("0")));
    }
    
    public static void Dijkstra(Vertex source) {
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
    
    private static class DVR {
	Vertex destination;
	Vertex nexthop;
	Vertex distance;
	
	public DVR(){}
	
	public DVR(Vertex dest, Vertex hop, Vertex dist){
	    destination = dest;
	    nexthop = hop;
	    distance = dist;
	}
	
	public String getDestNode(){
	    return destination.getName();
	}
	
	public String getNextHop(){
	    return nexthop.getName();
	}
	
	public String getDistance(){
	    return distance.getName();
	}
    }
    
    private static class Vertex implements Comparable<Vertex>{
	String name;
	ArrayList<Edge> adj;
	double minDis = Double.POSITIVE_INFINITY;
	Vertex prev;

	public Vertex(String name){
	    this.name = name;    
	    adj = new ArrayList<>();
	}

	public String getName(){
	    return name;
	}
	
	public void printEdges(){
	    for(Edge e: adj)
		System.out.print(e.getWeight()+ " ");
	    
	    System.out.println();
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
}