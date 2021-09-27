import java.sql.*;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
   
public class mulesoft {

   public  static void  insertintonewmovies(int id,String movie, String actress,String actor, String director,int year,Connection conn) {  
      String sql = "INSERT INTO Movies (id, MovieName, actor, actress, director, year_of_release ) VALUES(?,?,?,?,?,?)";  
 
      try{  
         //  Connection conn = this.connect();  
          PreparedStatement pstmt = conn.prepareStatement(sql);  
          pstmt.setInt(1, id);
          pstmt.setString(2, movie);  
          pstmt.setString(3, actor);  
          pstmt.setString(4, actress); 
          pstmt.setString(5, director); 
          pstmt.setInt(6, year);
          pstmt.executeUpdate();  
      } catch (SQLException e) {  
          System.out.println(e.getMessage());  
      }  
      finally{
         System.out.println("Inserted");

      }
  }  
   
   public static void createnewtablemovie(Connection conn){
      String sql = "CREATE TABLE IF NOT EXISTS Movies (\n"  
                + " id integer PRIMARY KEY,\n"  
                + " MovieName text NOT NULL,\n"  
                + " actor text,\n"  
                + " actress text,\n"  
                + " director text,\n"
                + " year_of_release integer\n"
                
                + ");";  

          
        try{  
            // Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
   
      }

      public static void selectallmovies(Connection conn){  
         String sql = "SELECT * FROM Movies";  
           
         try {  
            //  Connection conn = this.connect();  
             Statement stmt  = conn.createStatement();  
             ResultSet rs    = stmt.executeQuery(sql);  
               
             // loop through the result set  
             while (rs.next()) {  
                 System.out.println(rs.getInt("id") +  "\t" +   
                                    rs.getString("MovieName") + "\t" +  
                                    rs.getString("actor") + "\t" +  
                                    rs.getString("actress") + "\t" +  
                                    rs.getDouble("director") + "\t" +
                                    rs.getInt("year_of_release"));
             }  
         } catch (SQLException e) {  
             System.out.println(e.getMessage());  
         }  
     }  

     public  static void selectallmovieactors(Connection conn,String actor){  
      String sql = "SELECT * FROM Movies WHERE actor = '"+actor+ "';";  
        
      try {  
         //  Connection conn = this.connect();  
          Statement stmt  = conn.createStatement();  
          ResultSet rs    = stmt.executeQuery(sql);  
            
          // loop through the result set  
          while (rs.next()) {  
              System.out.println(rs.getInt("id") +  "\t" +   
                                 rs.getString("MovieName") + "\t" +  
                                 rs.getString("actor") + "\t" +  
                                 rs.getString("actress") + "\t" +  
                                 rs.getDouble("director") + "\t" +
                                 rs.getInt("year_of_release"));
          }  
      } catch (SQLException e) {  
          System.out.println(e.getMessage());  
      }  
  }

  public static void main( String args[] ) {
      Connection c = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:testDB.db");
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Opened database successfully");
   
   createnewtablemovie(c);
   insertintonewmovies(1,"The Dark Knight","Emma Thomas","Jonathan Nolan","Christopher Nolan",2018,c);
   insertintonewmovies(2,"Avengers: Endgame","Scarlett Johansson","Robert Downey Jr","Christopher ",2019,c);
   insertintonewmovies(3,"Coco","Alanna Ubach","Anthony Gonzalez","Jphn",2017,c);
   insertintonewmovies(4,"Avatar","Zoe Saldana","Worthington","Alvin",2009,c);

   System.out.println("Inserted to the database");
   System.out.println("Gonna retrieve all from db........");
  selectallmovies(c);
   System.out.println("Gonna retrieve movies of Anthony Gonzalez from db........");
   selectallmovieactors(c,"Worthington");
  }

}