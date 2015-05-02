/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkingassignment3;

import java.util.PriorityQueue;
import java.util.*; 
import java.io.*;
import java.util.Scanner;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    

      public static void main(String Args[]) throws Exception {
        
        Scanner input = new Scanner(System.in);
        
        int user_input;
   
	// creates a connection to server?
	Socket sock = null;
	DataOutputStream localOut = null;
	BufferedInputStream bis = null;
	do{
	    System.out.println ("Enter an options 1 or 2");
	    user_input = input.nextInt();


	    try{
		//create new socket
		sock = new Socket("localhost", 33445);
		//Loads DVR file
		URL url = JoshLopezA3server.class.getResource("DVR.txt");
		Scanner reader = new Scanner(new File(url.toURI()));
		String dvr = Integer.toString(user_input) + "\n";
		
		//Read DVR into string format
		while(reader.hasNextLine())
		    dvr = dvr.concat(reader.nextLine()+"\n");
		System.out.println(dvr);
		localOut = new DataOutputStream(sock.getOutputStream());		

		//send user input and DVR.txt to server
		localOut.writeBytes(dvr);



		//sent DVR to the server
		//localOut.writeBytes(file);
	    }finally{
		if(sock!=null)sock.close();
	    }
	}while(user_input!=-1);
    }
}
