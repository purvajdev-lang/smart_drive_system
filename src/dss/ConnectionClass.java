package dss;
import java.sql.*;

public class ConnectionClass 
{
    Connection con;
    Statement stm;
    
    
    ConnectionClass()
    {
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/drivesmartsystem","root","qwerty" ); 
         stm=con.createStatement();
        if(con.isClosed())
        
        {
            
          System.out.println("yes");
        
        }
        
        else 
            
        {
        
        
        System.out.println("No");
        
        }
        
        
        }
        
        catch(Exception ex)
        {
            
            ex.printStackTrace();
            
        }
        
    }
    public static void main(String args[])
    {
    
   
        new ConnectionClass();
    }
    
}