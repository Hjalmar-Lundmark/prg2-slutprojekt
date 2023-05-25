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
    private int userId;
    private String error = "";

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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
            error += "Username is required \n";
        }
        if (pwd.length() < 2) {
            error += "Password is required \n";
        }

        if (error.length() < 5) { // stops the login if error is found
            try {
                stmt = conn.createStatement();
                SQLQuery = "SELECT * FROM hl21users WHERE name='" + user + "'";
                result = stmt.executeQuery(SQLQuery);
                result.next();

                //checks if user exist in the db
                if (Objects.equals(user, result.getString("name"))) {
                    //checks if wrote password and pwd in db is the same
                    if (BCrypt.checkpw(pwd, result.getString("password"))) {
                        loggedIn = true;
                        username = user;
                        userId = result.getInt("id");
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

    public void register(String u, String pwd, String pwdConf) {
        error = "";
        if (user.length() < 2) {
            error += "Username is required \n";
        }
        if (pwd.length() < 8) {
            error += "Password needs atleast 8 characters \n";
        }
        if (!pwd.equals(pwdConf)) {
            error += "Passwords does not match \n";
        }

        if (error.length() < 5) { // stops the login if error is found
            try {
                // check if username already exists
                stmt = conn.createStatement();
                SQLQuery = "SELECT * FROM hl21users WHERE name='" + u + "' ";
                result = stmt.executeQuery(SQLQuery);

                if (result.toString() != "") { // if username is not taken, create acc
                    String hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
                    SQLQuery = "INSERT INTO hl21users (name, password) VALUES ('" + u + "','" + hashedPwd + "')";
                    stmt.executeUpdate(SQLQuery);

                    loggedIn = true;
                    username = u;
                    // another stmt for getting uId???

                } else {
                    error += "Username is already taken \n";
                }

                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Insert failed?");
            }
        } else {
            System.out.println(error);
        }
    }

    public boolean createNewPost(String title, String content) {
        error = "";
        boolean success = false;
        if (title.length() < 2) {
            error += "Title is required \n";
        }
        if (content.length() < 2) {
            error += "Content is required \n";
        }

        if (error.length() < 5) {
            try {
                // sanitize data?
                //stuff


                stmt = conn.createStatement();
                SQLQuery = "INSERT INTO hl21forum (title, content, authorId) VALUES ('" + title + "', '" + content + "', '" + userId + "')";
                stmt.executeUpdate(SQLQuery);
                System.out.println("Create post worked?");
                success = true;

                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Insert failed");
            }

        } else {
            System.out.println(error);
        }
        return success;
    }

    // testing
    public static void main(String[] args) {
        Model m = new Model();
        m.connect();
        m.getPosts();
    }
}
