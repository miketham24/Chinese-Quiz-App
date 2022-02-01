
package chinese; 

import java.util.ArrayList;


public class Text {
    private int id = -1;
    private String passage = ""; 
    private String title = ""; 
//    int percentEasy = 0;
//    int percentMedium = 0; 
//    int percentHard = 0;
    private int prevScore = 0; 
    private double difficulty; 
    ArrayList<String> easyWords = new ArrayList<String>();
    ArrayList<String> mediumWords = new ArrayList<String>();
    ArrayList<String> hardWords = new ArrayList<String>();

              
    
    public void setDifficulty(double difficulty){ 
        this.difficulty = difficulty; 
    }
    
    public String getDifficulty(){ 
       
        if (difficulty < 1.5){ 
            return "easy";
        } else if (difficulty < 2.5){ 
            return "medium";
        } else { 
            return "hard";
        } 
    }
   
    public void setTitle(String title){ 
        this.title = title;
    }
    
    public String getTitle(){ 
       return title;
    }
    
    public void setPassage(String passage){ 
        this.passage = passage; 
    }
    
    public String getPassage(){ 
        return passage; 
    }
    
    public void setId(int id){ 
        this.id = id;
    }
    
    public int getId(){
        return id; 
    }
    
    public void setPrevScore(int prevScore){ 
        this.prevScore = prevScore;
    }
 
    public int getPrevScore(){ 
       return prevScore;
    }
    


}


