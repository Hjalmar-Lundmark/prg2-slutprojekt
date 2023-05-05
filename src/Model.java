import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class Model {
    private Connection conn = null;
    private String user = dbData.user;
    private String db = dbData.URL;
    private String host = dbData.host;
    private String pwd = dbData.pwd;
    private Statement stmt;
    private String SQLQuery;
    private ResultSet result;

    // info for login and loggedIn
    private boolean loggedIn = false;
    private String username = "";
    private String error = "";

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + db + ":3306/" + host + "? "+
                    "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",user,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Post> getPosts() { // returns ArrayList of posts, in the format of the class Post
        ArrayList<Post> out = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            SQLQuery = "SELECT hl21forum.*, hl21users.name FROM hl21forum JOIN hl21users WHERE hl21forum.authorId = hl21users.id ORDER BY hl21forum.id DESC";
            result = stmt.executeQuery(SQLQuery);

            while (result.next()) {
                out.add(new Post(result.getString("title"),
                        result.getString("content"),
                        result.getString("name"),
                        result.getTimestamp("createdAt"),
                        result.getInt("id")));
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public void login(String user, String pwd) {
        error = "";
        if (user.length() < 2) {
            error += "Username is Required \n";
        }
        if (pwd.length() < 2) {
            error += "Password is Required \n";
        }

        if (error.length() < 5) { // stops the login if error is found
            try {
                stmt = conn.createStatement();
                SQLQuery = "SELECT * FROM hl21users WHERE name='" + user + "'";
                result = stmt.executeQuery(SQLQuery);

                //checks if user exist in the db
                if (Objects.equals(user, result.getString("name"))) {
                    //checks if wrote password and pwd in db is the same
                    if (BCrypt.checkpw(pwd, result.getString("password"))) {
                        loggedIn = true;
                        username = user;
                        System.out.println("Login worked");
                    } else {
                        System.out.println("Login failed - password");
                    }
                } else {
                    System.out.println("Login failed - username");
                }

                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(error);
        }
    }

    // testing
    public static void main(String[] args) {
        Model m = new Model();
        m.connect();
        m.getPosts();
    }
}
