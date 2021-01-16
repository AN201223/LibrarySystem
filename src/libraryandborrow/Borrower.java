
package libraryandborrow;


public class Borrower {
    String name;
    book loanedBook;
    
    public String toBorrower(){
        return (name+","+loanedBook.getName());
    }

    public void setName(String name) {
        this.name = name;
    }

    public Borrower(String name, book loanedBook) {
        this.name = name;
        this.loanedBook = loanedBook;
    }

    public String getName() {
        return name;
    }

    public book getLoanedBook() {
        return loanedBook;
    }

    public void setLoanedBook(book loanedBook) {
        this.loanedBook = loanedBook;
    }
}
