import java.sql.*;
import java.util.ArrayList;

public class Model {
    private Connection conn = null;
    private String user = dbData.user;
    private String db = dbData.URL;
    private String host = dbData.host;
    private String pwd = dbData.pwd;
    private Statement stmt;
    private String SQLQuery;
    private ResultSet result;

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

    // testing
    public static void main(String[] args) {
        Model m = new Model();
        m.connect();
        m.getPosts();
    }
}
