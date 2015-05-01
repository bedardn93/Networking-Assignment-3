/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkingassignment3;

/**
 *
 * @author Nick
 */
public class JoshLopezA3server {
    
    
    
    
    
    //create routing table from WT.txt
    
    
    
    
    
    public static void method (int user_input, int numDVR_messages, int[] message){
         
        
        //time to process 
        int T1 = 0, T2 = 0;
                
                
        if (user_input == 1) {
            //reads DVR messages from client 
            
            
            for (int i = 0; i < numDVR_messages; i++) {
            //Updates the routing table 
            
            
            //prints the routing table 
                
                
                
            }
            System.out.println("Elapsed time: " + T1);
            
           
           
        }
        
        else if (user_input == 2) {
            
            //thread 1 
            //reads DVR messages from client 
            
            
            //thread 2
            //Updates the routing table 
         
            //prints the routing table 
                
                
                
              System.out.println("Elapsed time: " + T2);
            
        }
        else {
        
        System.out.println ("invalid input");
        }
        
        
        
        
        
    }
    
    
    
}
