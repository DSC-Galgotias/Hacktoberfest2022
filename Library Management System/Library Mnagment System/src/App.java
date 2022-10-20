import java.sql.*;  
import java.util.Scanner;


public class App {

       // JDBC driver name and database URL 
       static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";   
       static final String DB_URL = "jdbc:mysql://localhost:3306/library";  
       
       //  Database credentials 
       static final String USER = "root"; 
       static final String PASS = "1234"; 
       static Connection con = null;
       static Statement stmt = null;
       static Statement stmt2 = null;
       static Statement stmt3 = null;
       static Statement stmt4 = null;

       static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        try{  
            Class.forName(JDBC_DRIVER);  
            con = DriverManager.getConnection(DB_URL,USER,PASS);  
            stmt = con.createStatement();  
            // String sql =  "CREATE TABLE USER " + 
            //     "(id INTEGER not NULL, " + 
            //     " name VARCHAR(255), " +  
            //     " password VARCHAR(255), " +  
            //     " PRIMARY KEY ( id ));";  
            // int rs = stmt.executeUpdate(sql);  
            // while(rs.next())  
            // System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
            // con.close();  
            }
            catch(Exception e)
            { System.out.println(e);}  

            homePage();
            scanner.close();
}

private static void homePage() {
    System.out.println("\t\t\t+------------------------------------+");
    System.out.println("\t\t\t|\tLibrary Management System    |");
    System.out.println("\t\t\t|\t\t1) Student Login     |");
    System.out.println("\t\t\t|\t\t2) Librarian Login   |");
    System.out.println("\t\t\t|\t\t3) Exit              |");
    System.out.println("\t\t\t+------------------------------------+");

    int choice = 0;

         choice = scanner.nextInt();
         scanner.nextLine();
    
    switch (choice) {
        case 1:
            studentLogin();
            homePage();
            break;
        case 2:
            librarianLogin();
            homePage();
            break;
        case 3:
            return;   
    
        default:
            System.out.println("Invalid choice");
            break;
        }
   
    }

        private static void librarianLogin() {
            String librarianId,password;
            System.out.println("---------------------------------------------------------------------");
            System.out.print("Enter Librarian Id : ");
            librarianId = scanner.nextLine();
            System.out.print("Enter Password : ");
            password = scanner.nextLine();
        
        
        if(librarianVerify(librarianId,password)){
            System.out.println("Login Successfully");
            librarianPage();
        }
        else{
            System.out.println("Login failed !!! \nLibrarian Id /Password is incorrect");
        }

        }

        private static void librarianPage() {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("\t\t\t\t\t Menu");
            System.out.println("\t 1) Issue Book");
            System.out.println("\t 2) Return Book");
            System.out.println("\t 3) Search Book");
            System.out.println("\t 4) Add Book");
            System.out.println("\t 5) Delete Book");
            System.out.println("\t 6) Register User");
            System.out.println("\t 7) Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    issueBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                // addBook();
                    break;
                case 5:
                    break; 
                case 6:
                    register();
                    break;
                case 7:
                    return;
                default:
                    break;
            }
        }

        // private static void addBook() {
        //     String userName = null,password = null;
        //     int userId = 0;
        //     System.out.println("---------------------------------------------------------------------");
        //     System.out.print("Enter Title");
        //     title = scanner.nextLine();
        //     System.out.print("Enter Author ");
        //     author = scanner.nextInt();
        //     System.out.print("Enter ISBN ");
        //     isbn = scanner.nextLine();
        //     System.out.print("Enter Publication ");
        //     publication = scanner.nextInt();
        //     scanner.nextLine();
        //     System.out.print("Enter Quantity ");
        //     password = scanner.nextLine();
        //     try{  
        //         Class.forName(JDBC_DRIVER);  
        //         con = DriverManager.getConnection(DB_URL,USER,PASS);  
        //         stmt = con.createStatement();  
        //     String query = "insert into user (userid,name,password) values ("+ userId +",'"+ userName +"','"+ password+"')";         
        //     int rs = stmt.executeUpdate(query);
        //     if(rs == 1){
        //         System.out.println("User Registered Successfully !!!");
        //     }
        //     else{
        //         System.out.println("Failed !!!  User is not registered.");
        //     }
        //     }
        //     catch(Exception e)
        //     { System.out.println(e);} 
    
        // }
        private static void searchBook() {
            System.out.println("---------------------------------------------------------------------");
            System.out.print("Enter Book ISBN : ");
            String isbn = scanner.nextLine();
            String query = "select * from books where isbn="+isbn; 
            try{  
                Class.forName(JDBC_DRIVER);  
                con = DriverManager.getConnection(DB_URL,USER,PASS);  
                stmt = con.createStatement(); 
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    System.out.println("---------------------------------------------------------------------");
                    System.out.print("Title : ");
                    System.out.println(rs.getString("Title").toString());
                    System.out.print("Author : ");
                    System.out.println(rs.getString("Author").toString());
                    System.out.print("ISBN : ");
                    System.out.println(rs.getString("ISBN").toString());
                    System.out.print("Publication : ");
                    System.out.println(rs.getString("Publication").toString());
                    System.out.print("Quantity : ");
                    System.out.println(rs.getString("Quantity").toString());
                }

            } catch(Exception e)
            { System.out.println(e);} 


        }

        private static void issueBook() {
            System.out.println("---------------------------------------------------------------------");
            System.out.print("Enter UserId : ");
            int userId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Book ISBN : ");
            String isbn = scanner.nextLine();

            try{  
                Class.forName(JDBC_DRIVER);  
                con = DriverManager.getConnection(DB_URL,USER,PASS);  
                stmt = con.createStatement();  
                stmt2 = con.createStatement();
                stmt3 = con.createStatement();
                stmt4 = con.createStatement();
            String query = "select * from books where ISBN="+isbn; 
            String query2 = "select * from user where userid="+String.valueOf(userId);        
            ResultSet rs = stmt.executeQuery(query);
            ResultSet rs2 = stmt2.executeQuery(query2);
            while (rs.next() && rs2.next()) {

                if(rs.getString("ISBN").equalsIgnoreCase(isbn)){

                    if(rs2.getInt("userid")==userId){
                        if(rs.getInt("quantity")>0){                            
                            String updateBook = "update books set quantity="+(rs.getInt("quantity")-1)+" where isbn="+isbn;
                            String update = "update user set no_borrowed_books="+String.valueOf(rs2.getInt("No_borrowed_books")+1)+" where userid="+String.valueOf(userId);
                            int q1 = stmt3.executeUpdate(update);
                            int q2 = stmt4.executeUpdate(updateBook);
                            if(q1==1&&q2==1){
                                System.out.println("Book issued successfully !!!");
                            }
                            else{
                                System.out.println("Book is not issued successfully !!!");
                            }
                        }
                        else{
                            System.out.println("Book is not available");
                        }
                    }
                    else{
                        System.out.println("User Id is incorrect !!!");
                    }
                }
                else{
                    System.out.println("ISBN is incorrect !!!");
                }      
            }
        }
            catch(Exception e)
            { System.out.println(e);} 

        }


        private static void returnBook() {
            System.out.println("---------------------------------------------------------------------");
            System.out.print("Enter UserId : ");
            int userId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Book ISBN : ");
            String isbn = scanner.nextLine();

            try{  
                Class.forName(JDBC_DRIVER);  
                con = DriverManager.getConnection(DB_URL,USER,PASS);  
                stmt = con.createStatement();  
                stmt2 = con.createStatement();
                stmt3 = con.createStatement();
                stmt4 = con.createStatement();
            String query = "select * from books where ISBN="+isbn; 
            String query2 = "select * from user where userid="+String.valueOf(userId);        
            ResultSet rs = stmt.executeQuery(query);
            ResultSet rs2 = stmt2.executeQuery(query2);
            while (rs.next() && rs2.next()) {

                if(rs.getString("ISBN").equalsIgnoreCase(isbn)){

                    if(rs2.getInt("userid")==userId){
                        if(rs2.getInt("No_borrowed_books")>0){                            
                            String updateBook = "update books set quantity="+(rs.getInt("quantity")+1)+" where isbn="+isbn;
                            String update = "update user set no_borrowed_books="+String.valueOf(rs2.getInt("No_borrowed_books")-1)+" where userid="+String.valueOf(userId);
                            int q1 = stmt3.executeUpdate(update);
                            int q2 = stmt4.executeUpdate(updateBook);
                            if(q1==1&&q2==1){
                                System.out.println("Book issued successfully !!!");
                            }
                            else{
                                System.out.println("Book is not issued successfully !!!");
                            }
                        }
                        else{
                            System.out.println("Book is not available");
                        }
                    }
                    else{
                        System.out.println("User Id is incorrect !!!");
                    }
                }
                else{
                    System.out.println("ISBN is incorrect !!!");
                }      
            }
        }
            catch(Exception e)
            { System.out.println(e);} 

        }


        private static boolean librarianVerify(String librarianId, String password) {
            try{  
                Class.forName(JDBC_DRIVER);  
                con = DriverManager.getConnection(DB_URL,USER,PASS);  
                stmt = con.createStatement();  
            String query = "select * from librarian where librarianId="+librarianId;         
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                    if(password.equals(rs.getString(3))){
                        return true;
                    }
            }
            }
            catch(Exception e)
            { System.out.println(e);} 
            return false;
        }


        private static void register() {
        String userName = null,password = null;
        int userId = 0;
        System.out.println("---------------------------------------------------------------------");
        System.out.print("Enter Username ");
        userName = scanner.nextLine();
        System.out.print("Enter userid ");
        userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Password ");
        password = scanner.nextLine();
    

        try{  
            Class.forName(JDBC_DRIVER);  
            con = DriverManager.getConnection(DB_URL,USER,PASS);  
            stmt = con.createStatement();  
        String query = "insert into user (userid,name,password) values ("+ userId +",'"+ userName +"','"+ password+"')";         
        int rs = stmt.executeUpdate(query);
        if(rs == 1){
            System.out.println("User Registered Successfully !!!");
        }
        else{
            System.out.println("Failed !!!  User is not registered.");
        }
        }
        catch(Exception e)
        { System.out.println(e);} 

    }


    private static void studentLogin() {
    String userId,password;
    System.out.println("---------------------------------------------------------------------");
        System.out.print("Enter UserId : ");
        userId = scanner.nextLine();
        System.out.print("Enter Password : ");
        password = scanner.nextLine();
    
    
    if(verify(userId,password)){
        System.out.println("Login Successfully");
    }
    else{
        System.out.println("Login failed !!! \nUserid/Password is incorrect");
    }

}

private static boolean verify(String userId, String password) {
    try{  
        Class.forName(JDBC_DRIVER);  
        con = DriverManager.getConnection(DB_URL,USER,PASS);  
        stmt = con.createStatement();  
    String query = "select * from user where userid="+userId;         
    ResultSet rs = stmt.executeQuery(query);
    while (rs.next()) {
            if(password.equals(rs.getString(3))){
                return true;
            }
    }
    }
    catch(Exception e)
    { System.out.println(e);} 

    
    return false;
}

}
