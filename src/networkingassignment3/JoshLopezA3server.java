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
import java.text.DecimalFormat;




public class JoshLopezA3server {
    
    public static void main(String argv[]) throws Exception{
        String wt;
        String option;
        String httpReturn;
        ServerSocket welcomeSocket = new ServerSocket(33445);
        double startTime;
        
                
        //read wt file 
            
            
        //create wt file 
            
        createRouting(wt);
            
        //print routing
        printRouting();
                
        
        
        
        //creates the server?
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
         

            
            
            
            
            
            
            
            //Get the option the user wants
            option = inFromClient.readLine();
            
            //SEND get request (NEEDS TO BE PROPERLY FORMATTED) 
            
            // the user send option 1
             if (option.equals("1") ){ 
                 //get current time
                 startTime = (System.currentTimeMillis()/1000.0) + 2208988800.0;

              
           
                 //get data from client 
                 int numDVR_messages =inFromClient.readLine();
                 String dvrMessage = inFromClient.readLine();
                 
                 
                 //for each dvr message 
                 for (int i = 0; i < numDVR_messages; i++) {
                    
                     
                    updateRouting(dvrMessage); 
                    
                    printRouting(); 
                    
                 }
                 //Elapsed time 
                 double endTime = (System.currentTimeMillis()/1000.0) + 2208988800.0;
                 double timeBetween = startTime - endTime;
                 System.out.println("Elapsed time (T1): " + new DecimalFormat("0.00").format(timeBetween*1000) + " ms");  
            
            
             }
         //if option 2 is selected    
         else if (option.equals("2") ){
           startTime = (System.currentTimeMillis()/1000.0) + 2208988800.0;
            

            //thread 1 
            //reads DVR messages from client 
            int numDVR_messages =inFromClient.readLine();
            String dvrMessage = inFromClient.readLine();
          
            
            
            
            
            //thread 2
            //Updates the routing table
            updateRouting(dvrMessage); 
            
            
            //prints the routing table 
             printRouting();
             
             
             
            //Elapsed time 
                 double endTime = (System.currentTimeMillis()/1000.0) + 2208988800.0;
                 double timeBetween = startTime - endTime;
                 System.out.println("Elapsed time (T1): " + new DecimalFormat("0.00").format(timeBetween*1000) + " ms");  
              
             
             
           
        } else {
            //invalid choice as in not (1 or 2)
            System.out.println("User choice is invalid");
        }//end of if block
             
             
             
             
             
        }//end of while loop
        
        
    }//end of main


    private static void updateRouting(String dvrMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void printRouting() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void createRouting(String wt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private static void dvr(String wt) {
        
        
        
    }
    
    
    
    
  
    
    
    
    
  
    
    
    
    
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
    

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("C:\\Users\\Nick\\Documents\\GitHub\\Networking-Assignment-3\\src\\networkingassignment3\\WT.txt"));
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        int count = 0;
        while(input.hasNextInt()){
            vertices.add(new Vertex(input.next()));
            vertices.get(count).adj = 
                    new Edge[]{new Edge(vertices.get(count),input.nextInt())};
            count++;
        }
        for(int i = 0; i < vertices.size(); i++){
            System.out.println("( "+vertices.get(i).getName()+", "+vertices.get(i).adj[0].getWeight()+" )");
        }
        /*
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
                */
    }
    
}
