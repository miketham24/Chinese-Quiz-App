package chinese;

import java.sql.*; 
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michaeltham
 */
public class DataModel {
     private Connection connection = null;  
     private ResultSet resultSet = null;  
     private Statement statement = null;
     private String url = "jdbc:sqlite:database.db";
    
    
   public static void main(String[]args) throws SQLException{ 
      
       DataModel d = new DataModel();
       d.addConnection();
       d.createTables();
      
     
       
//       Text p = new Text();
//       p.setPassage("我安静三爱");
//       d.addText(p);

//       Text t = new Text();
//       t.setId(2);
//       ArrayList<Vocab> vocabs = d.getQuizWords(t);
//       System.out.println(vocabs);
//       System.out.println(vocabs.get(0).id);
//       System.out.println(vocabs.get(0).word);
//       System.out.println(vocabs.get(0).difficulty);
       
       String sql = "SELECT * FROM vocab;";
       try { 
           ResultSet rs =  d.statement.executeQuery(sql);
           int count = 0;
           while (rs.next()) { 
                String replace = rs.getString("word");
                System.out.println(replace);
                System.out.println(rs.getInt("id"));
                String newWord = ""; 
                // creates new word String
                for( int i=0; i < replace.length();i++){
                    if (!replace.substring(i,i+1).equals(" ")){
                        newWord += replace.substring(i,i+1);
                    }
                }
                sql = "UPDATE vocab SET word = '" + newWord + "' WHERE id = " + rs.getInt("id") + ";";
                Statement statement2 = d.connection.createStatement();            
                statement2.execute(sql);
                count++;
                
                
            }
       } 
       catch (Exception e) { 
            e.printStackTrace();
       }
       
      
       
      
   }
       
    
    public void addConnection(){ 
     try {   
         connection = DriverManager.getConnection(url);
         statement = connection.createStatement(); 
     } 
     catch (Exception e) 
     {  
         e.printStackTrace();  }   
 }  
       
      public void closeConnection(){ 
          try{ 
              connection.close(); 
          }
         catch(Exception e){ 
             e.printStackTrace();
         }
    }
    
    public void createTables(){ 
        try {   
         String sql=  "CREATE TABLE IF NOT EXISTS vocab (id INTEGER PRIMARY KEY ,word STRING, difficulty STRING)";
         statement.execute(sql); 
         sql = "CREATE TABLE IF NOT EXISTS texts(id INTEGER PRIMARY KEY,title STRING , passage STRING , prev_score INTEGER) "; 
         statement.execute(sql); 
         sql = "CREATE TABLE IF NOT EXISTS quizzes (textid INTEGER, vocabid INTEGER)";
         statement.execute(sql);
         } catch (SQLException ex) {
             Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public void dropTables(){ 
       try{ 
           String sql = ("DROP TABLE IF EXISTS vocab; ");
           statement.execute(sql);
           sql = ("DROP TABLE IF EXISTS texts; ");
           statement.execute(sql);
           sql = ("DROP TABLE IF EXISTS quizzes; ");
           statement.execute(sql);
       } 
       catch(SQLException e){ 
        e.printStackTrace();
       }
    }


    public void addWord(String word, String difficulty){
     String sql = "INSERT INTO vocab (word, difficulty) " 
            + "VALUES( '"+word + "','" + difficulty + "'); ";
         try {
             statement.execute(sql);
         } catch (SQLException ex) {
             Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }   

    
    public void deleteWord(String word, Text text){ 
        String sql = "DELETE FROM vocab (word, text) "
               + "WHERE( '"+word + "','" + text + "'); ";
        try{ 
            statement.execute(sql); 
        }
           catch (SQLException ex){ 
             Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
           }
}
    public void addText(Text text){
        try { 
            // inserts text into texts database table
            String sql = "INSERT INTO texts (title,passage,prev_score) "
                    + " VALUES ( " 
                    + "'"+ text.getTitle() + "',"
                    + "'"+ text.getPassage() + "',"
                    + text.getPrevScore() + ");" ;
     
            statement.execute(sql);
             
            //gets text id 
            sql = "SELECT id FROM texts "
                 + "ORDER BY id DESC LIMIT 1;";
            ResultSet results = statement.executeQuery(sql); 
            while(results.next()){
                text.setId(results.getInt("id"));
            }

             //gets all words that are in the passage  
            sql = "SELECT vocab.id, texts.id FROM vocab "
                       + "INNER JOIN texts "
                       + "ON passage LIKE '%' || word || '%' "
                       + "WHERE texts.id = " + text.getId() + ";";
            results = statement.executeQuery(sql);
            
            Statement statement2 = connection.createStatement();
          //adds vocabid and textid into quizzes table
            while (results.next()) {
                
                String vocabid = results.getString(1);
                String textid = results.getString(2);
               
                sql = "INSERT INTO quizzes (vocabid, textid) "
                       + "VALUES (" + vocabid + ',' + textid + ");";
                statement2.execute(sql);        
            }    
        } catch (SQLException ex) { 
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateScore(Text text){ 
        String sql = "UPDATE texts"
                +" SET prev_score = "
                + text.getPrevScore()
                + " WHERE id =" + text.getId()+ ";";
         try {
             statement.execute(sql);
         } catch (SQLException ex) {
             Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
         }    
    }
    
    public double getAvgDifficulty(Text text){  
       
       double total = 0; 
       int count = 0; 
       
       String sql = "SELECT difficulty, COUNT(*) FROM vocab "
                  + "INNER JOIN quizzes "
                  + "ON vocabid = id "  
                  + " WHERE textid = " + text.getId()
                  + " GROUP BY difficulty;";
         try { 
             ResultSet results = statement.executeQuery(sql);
             while (results.next()) { 
                 if (results.getString("difficulty").equals("easy")) { 
                   total += results.getInt("COUNT(*)"); 
                 }
                 else if (results.getString("difficulty").equals("medium")) { 
                   total += results.getInt("COUNT(*)") *2;
                }
                 else if (results.getString("difficulty").equals("hard")) { 
                   total += results.getInt("COUNT(*)")*3;
                }
                count++;
             }
          
         } catch (SQLException ex) {
             Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
         }
        
         return total/count;
    }
  
  
    public ArrayList<Text> getTexts(String title, String difficulty, int score, String ho){ 
        String sql = "";
        if (ho.equals("Higher")){  
        sql = "SELECT * FROM texts"
                + " WHERE title LIKE '%" + title + "%' "
                + " AND prev_score >= " + score + ";";
       } else if (ho.equals("Lower")){ 
         sql = "SELECT * FROM texts"
                + " WHERE title LIKE '%" + title + "%' "
                + " AND prev_score <= " + score + ";";
       }
        System.out.println(sql);
        
        //creates text arraylist
        ArrayList<Text> texts = new ArrayList<Text>();
         try { 
             ResultSet results = statement.executeQuery(sql);
             // adds text
             while (results.next()){ 
               Text text = new Text();
               text.setId(results.getInt("id"));
               text.setTitle(results.getString("title"));
               text.setPassage(results.getString("passage")); 
               text.setPrevScore(results.getInt("prev_score"));
               texts.add(text);   
             }
             for (int i=texts.size()-1; i >= 0 ;i--){ 
               double diff = getAvgDifficulty(texts.get(i));
               texts.get(i).setDifficulty(diff);
               
               if (!texts.get(i).getDifficulty().equals(difficulty)){
                   texts.remove(i);
               }
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
         }
         System.out.println(texts);
         return texts; 
          
}
   
    public ArrayList<Vocab> getQuizWords(Text text){ 
        ArrayList<Vocab> vocab = new ArrayList();
       
        try {
             String sql = "SELECT id, word, difficulty FROM vocab"
                     + " LEFT JOIN quizzes ON id = vocabid"
                     + " WHERE textid =" + text.getId() + ";";
             ResultSet quiz = statement.executeQuery(sql);
             
             while(quiz.next()){
                 Vocab qword = new Vocab();
                 qword.difficulty = quiz.getString("difficulty");
                 qword.id = quiz.getInt("id");
                 qword.word = quiz.getString("word");
                 vocab.add(qword);
             }   
         } catch (SQLException ex) {
             Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return vocab;
    }
    
    
   
} 
