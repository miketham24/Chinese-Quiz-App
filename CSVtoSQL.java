/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinese;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author michaeltham
 */
public class CSVtoSQL {
    

    public static void main(String[]args){
        
        DataModel d = new DataModel();
        d.addConnection();
     
       

        try( Scanner scanner = new Scanner(new File("/Users/michaeltham/NetBeansProjects/Chinese/src/chinese/easy copy.csv"));){
            while(scanner.hasNextLine()){ 
                String word = scanner.nextLine(); 
                d.addWord(word,"easy"); 
            } 
        }
        catch(Exception e){ 
            System.err.println(e.getMessage());
        }


        try( Scanner scanner = new Scanner(new File("/Users/michaeltham/NetBeansProjects/Chinese/src/chinese/medium copy.csv"));){
            while(scanner.hasNextLine()){ 

                String word = scanner.nextLine(); 
                d.addWord(word,"medium"); 
            } 
        }
        catch(Exception e){ 
            System.err.println(e.getMessage());
        }

         try( Scanner scanner = new Scanner(new File("/Users/michaeltham/NetBeansProjects/Chinese/src/chinese/hard copy.csv"));){
            while(scanner.hasNextLine()){

                String word = scanner.nextLine(); 
                d.addWord(word,"Hard"); 
            } 
        }
        catch(Exception e){ 
            System.err.println(e.getMessage()); 
        }

    d.closeConnection();
    }

}
