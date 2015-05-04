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
    static ArrayList<Edge> edges = new ArrayList<>();
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
        createEdgesFromFile("WT.txt");
            
        //print routing
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
		for (int i = 0; i < numDVR_messages; i++) {
		    updateRoutingTable(inFromClient.readLine());
		    printRouting();
		}
		//Elapsed time
		long endTime = (System.nanoTime()-startTime);
		System.out.println("Elapsed time (T1): " + (endTime/1000000000.0) + " seconds.");
            
	    
	    }
	    //if option 2 is selected    
	    else if (option.equals("2") ){
		long startTime = (System.nanoTime());

		//thread 1 
		//reads DVR messages from client 
		//JoshLopezA3server newThread = new JoshLopezA3server("Thread-1");
		//newThread.start();
		int numDVR_messages = Integer.parseInt(inFromClient.readLine());

		//thread 2
		//Updates the routing table
		for (int i = 0; i < numDVR_messages; i++) {
		    updateRoutingTable(inFromClient.readLine());
		    printRouting();
		}

		//Elapsed time 
		long endTime = (System.nanoTime()-startTime);
		System.out.println("Elapsed time (T2): " + (endTime/1000000000.0) + " seconds.");




	    } else {
		//invalid choice as in not (1 or 2)
		System.out.println("User choice is invalid");
	    }//end of if block
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
    
    private static void printVertexEdge() {
	    for(Edge edge : edges)
		System.out.println(edge.toString());
    }
    
    private static void printRouting(){
	System.out.println("Routing table for i=3");
	System.out.println("Destination\tNext Hop\tDistance");
	for(DVR d : dvr){
	    System.out.println(d.getDestNode()+"\t\t"+d.getNextHop()+"\t\t"+d.getDistance());
	}
    }

    private static void createEdgesFromFile(String file) {
        URL url = JoshLopezA3server.class.getResource(file);
	Scanner reader = null;
	try {
	    reader = new Scanner(new File(url.toURI()));
	} catch (URISyntaxException | FileNotFoundException ex) {
	    Logger.getLogger(JoshLopezA3server.class.getName()).log(Level.SEVERE, null, ex);
	}
	//int count = 0;
	String source = reader.next();
	double weight = reader.nextInt();
        while(reader.hasNextInt()){
            //vertices.add(new Vertex(reader.next()));
            //vertices.get(0).adj = 
                    //new Edge[]{new Edge(vertices.get(0),reader.nextInt())};
	    String next = reader.next();
	    weight = reader.nextDouble();
	    edges.add(new Edge(new Vertex(source),new Vertex(next),weight));
	    //vertices.get(0).adj.add(new Edge(vertices.get(0),weight));
	    //vertices.get(count).printEdges();
	    //count++;
        }
	dvr.add(new DVR(new Vertex(source),new Vertex("0"),0.0));
    }
    
    private static void updateRoutingTable(String line){
	Scanner reader = new Scanner(line);
	//int count = 0;
	boolean updated = false;
	Vertex source = new Vertex(reader.next());
	double weight = reader.nextInt();
        while(reader.hasNextInt()){
            //vertices.add(new Vertex(reader.next()));
            //vertices.get(0).adj = 
                    //new Edge[]{new Edge(vertices.get(0),reader.nextInt())};
	    String next = reader.next();
	    weight = reader.nextDouble();
	    edges.add(new Edge(source,new Vertex(next),weight));
	    if(!updated){
		dvr.add(new DVR(source, source, weight));
		updated = true;
	    }
	    //vertices.get(0).adj.add(new Edge(vertices.get(0),weight));
	    //vertices.get(count).printEdges();
	    //count++;
        }
    }
    
    public static void Dijkstra(Vertex source) {
        source.minDis = 0;
        PriorityQueue<Vertex> vertex = new PriorityQueue<>();
      	vertex.add(source);

	while (!vertex.isEmpty()) {
	    Vertex u = vertex.poll();

            for (Edge edge : u.adj)
            {
                Vertex vert = edge.node1;
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
	double distance;
	
	public DVR(){}
	
	public DVR(Vertex dest, Vertex hop, double dist){
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
	
	public double getDistance(){
	    return distance;
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
    
	Vertex node1,node2;
	double weight;

	public Edge(Vertex node1, Vertex node2){
	    this.node1 = node1;
	    this.node2 = node2;
	    this.weight = Double.POSITIVE_INFINITY;
	}

	public Edge(Vertex node1, Vertex node2, double weight){
	    this.node1 = node1;
	    this.node2 = node2;
	    this.weight = weight;
	}

	public Vertex getNode1(){
	    return node1;
	}
	
	public Vertex getNode2(){
	    return node2;
	}

	public double getWeight(){
	    return weight;
	}
	
	public String toString(){
	    return node1.getName() + "->" + node2.getName() + " distance: " + weight;
	}
    }
}