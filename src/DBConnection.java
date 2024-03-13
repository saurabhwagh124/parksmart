import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.util.*;
public class DBConnection {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/smartpark";
    static String finalprf = "";
    static int lgsuc = 0;
    static final String USER = "root";
    static final String PASS = "Saurabh@124";
    Connection conn;
    PreparedStatement pst = null;
    ResultSet rs = null;
    static final DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");



    public static void registerUser(String a, String b, String c, String d, String e, String f){
     Connection conn = null;
    PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO register_user (username, Name, phone, email, password, prfimg) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, a);
            preparedStatement.setString(2, b);
            preparedStatement.setString(3, c);
            preparedStatement.setString(4, d);
            preparedStatement.setString(5, e);
            preparedStatement.setString(6, f);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            System.out.println("Inserted records into the table...");
        } catch (Exception k) {
            k.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception g) {
                g.printStackTrace();
            }
        }
    }


    public static void registerAdmin(String a, String b, String c, String d, String e){
     Connection conn = null;
     PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO admin_reg (adname, adpass, adphone, ademail, adadminname) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, a);
            preparedStatement.setString(2, b);
            preparedStatement.setString(3, c);
            preparedStatement.setString(4, d);
            preparedStatement.setString(5, e);
            preparedStatement.executeUpdate();
            System.out.println("Inserted records into the table...");
        } catch (Exception f) {
            f.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception g) {
                g.printStackTrace();
            }
        }
    }

    public static void checklogin(String a, String b){
    Connection conn = null;
    PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM register_user WHERE username=? AND password=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, a);
            preparedStatement.setString(2, b);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Alert access = new Alert(AlertType.INFORMATION,"Access granted");
                access.show();
                System.out.println("Access Granted");
                finalprf = b;
                lgsuc=1;

            } else {
                Alert accdenied = new Alert(AlertType.INFORMATION,"Access Denied");
                accdenied.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception g) {
                g.printStackTrace();
            }
        }

    }
    /*public static void checklogin(String username, String password) throws ClassNotFoundException {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM register_user WHERE username=? AND password=?")) {

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                Alert access = new Alert(AlertType.INFORMATION, "Access granted");
                access.show();
                System.out.println("Access Granted");
                finalprf = password;
                login.rootstage.setScene(profilepg.retprofile());
            } else {
                Alert accdenied = new Alert(AlertType.INFORMATION, "Access Denied");
                accdenied.show();
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }*/


    public static void getprofiledetails(){
        Connection conn = null;
        PreparedStatement pStatement = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT username, Name, phone, email, prfimg FROM register_user WHERE password=?";
            pStatement = conn.prepareStatement(sql);
            pStatement.setString(1, finalprf);
            try (ResultSet resultSet = pStatement.executeQuery()){
                if (resultSet.next()) {

                    profilepg.ae = resultSet.getString("username");
                    login.una = profilepg.ae;
                    profilepg.be = resultSet.getString("Name");
                    profilepg.ce = resultSet.getString("phone");
                    profilepg.de = resultSet.getString("email");
                    profilepg.prfurl = resultSet.getString("prfimg");
                    System.out.println("Details fetched successfully");

                } else {
                    System.out.println("User not found");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }catch(Exception t){
            System.out.println("exception dude!!");
            t.printStackTrace();
        }
        finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception g) {
                g.printStackTrace();
            }
        }
    }
    public static void profdetails(String profileImage) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "UPDATE register_user SET prfimg=? WHERE password=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, profileImage);
            preparedStatement.setString(2, finalprf);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Profile image inserted successfully.");
            } else {
                System.out.println("No rows affected. Profile image insertion failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception g) {
                g.printStackTrace();
            }
        }
    }
    public static void fetchsearch(String zx){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            System.out.println("Entering database");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="SELECT areaname FROM parking_space WHERE areaname LIKE '%"+zx+"%'";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet rset = preparedStatement.executeQuery();
            int cx = 1;
            int o = 2;
            int j = 1;
            int i = 0;
            int p = 0;
            int k = 0;
            while((rset.next())){
                System.out.println("Printing start");
                String ax = rset.getString("areaname");

                String ab = Integer.toString(cx);
                Label srnolb = new Label(ab);
                srnolb.setFont(new Font("ARIAL", 30));

                Label areanm = new Label(ax);
                areanm.setFont(new Font("ARIAL",30));
                areanm.setOnMouseClicked(e->{
                    login.rootStage.setScene(parklayout.retlayout());
                    parklayout.bg = ax;
                });

                searchparkspc.searchtb.add(srnolb, p, k);
                searchparkspc.searchtb.add(areanm, j, k);
                i++;
                k++;
                cx++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void bookingdts(String vm, String vn, String ag, String bg, String un){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        LocalDateTime nowtime = LocalDateTime.now();
        String dt = nowtime.format(DateFormat);
        String tm = nowtime.format(timeFormat);
        System.out.println(dt);
        System.out.println(tm);
        try  {
            System.out.println("Creating ticket");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO bookings (username, vehicle_model, vehicle_no, booking_date, booking_time, parking_no, parking_name) VALUES(?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,un);
            preparedStatement.setString(2, vm);
            preparedStatement.setString(3, vn);
            preparedStatement.setString(4, dt);
            preparedStatement.setString(5, tm);
            preparedStatement.setString(6, bg);
            preparedStatement.setString(7, ag);
            System.out.println(preparedStatement);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static Stack<String> getTickets() throws SQLException {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Stack<String> st = new Stack<String>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "Select * from bookings where username = ?";
            preparedStatement = conn.prepareStatement(sql);
            int i =0;
            preparedStatement.setString(1, "saurabh888");
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while(resultset.next()){
                    st.push(resultset.getString("booking_no"));
                    st.push(resultset.getString("username"));
                    st.push(resultset.getString("vehicle_model"));
                    st.push(resultset.getString("vehicle_no"));
                    st.push(resultset.getString("booking_date"));
                    st.push(resultset.getString("booking_time"));
                    st.push(resultset.getString("parking_no"));
                    st.push(resultset.getString("parking_name"));
                    i = i+8;
                }
            } catch (Exception f) {
                f.printStackTrace();
            }

            }catch (Exception g){
            g.printStackTrace();
        }
        finally {
            try{

                if(preparedStatement!=null){
                    preparedStatement.close();
                }
                if (conn!=null){
                    conn.close();
                }
            }catch(Exception w){
                w.printStackTrace();
            }
        }

        return st;
    }


    }
