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
import java.util.logging.Level;
import java.util.logging.Logger;




public class JoshLopezA3server {
    
    /*
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
                 int numDVR_messages = Integer.parseInt(inFromClient.readLine());
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
*/

    private static void updateRouting(String dvrMessage) {
        
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
    

    public static void main(String[] args) throws FileNotFoundException, IOException {
	URL url = JoshLopezA3server.class.getResource("WT.txt");
	File file = null;
	try {
	    file = new File(url.toURI());
	} catch (URISyntaxException ex) {
	    Logger.getLogger(JoshLopezA3server.class.getName()).log(Level.SEVERE, null, ex);
	}
	Scanner reader = new Scanner(file);
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        int count = 0;
        while(reader.hasNextInt()){
            vertices.add(new Vertex(reader.next()));
            vertices.get(count).adj = 
                    new Edge[]{new Edge(vertices.get(count),reader.nextInt())};
            count++;
        }
        for(int i = 0; i < vertices.size(); i++){
            System.out.println("( "+vertices.get(i).getName()+", "+vertices.get(i).adj[0].getWeight()+" )");
        }
    
    }
}