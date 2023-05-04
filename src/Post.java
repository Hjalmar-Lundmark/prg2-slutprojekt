import java.sql.Timestamp;

public class Post {
    private String Title;
    private String Content;
    private String Author;
    private int id;
    private Timestamp createdAt;

    public Post(String title, String content, String author, Timestamp c, int id) {
        Title = title;
        Content = content;
        Author = author;
        createdAt = c;
        this.id = id;
    }

    @Override
    public String toString() {
        return getTitle() + "\n" +
                "Made by: " + getAuthor() + "\n" +
                getContent() + "\n" +
                "Created at " + getCreatedAt() + "\n";
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
