package libraryandborrow;

public class book {

    private String name;
    private String ISBN;
    private String author;
    private double price;
    private String genre;

    
    public book(String name, String ISBN, String author, double price, String genre) {
        this.name = name;
        this.ISBN = ISBN;
        this.author = author;
        this.price = price;
        this.genre = genre;
    }
    
    public String toString(){
        return name+","+ISBN+","+author+","+price+","+genre;
    }

    public String getName() {
        return name;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
