package libraryandborrow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Alex
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private TextArea output;
    @FXML
    private TextField userInput;
    @FXML
    private Button delbook;
    @FXML
    private Button edbook;
    @FXML
    private Button vibook;
    @FXML
    private Button adbook;
    @FXML
    private Button adborrow;
    @FXML
    private Button viborrow;
    @FXML
    private Button edborrow;
    @FXML
    private Button delborrow;
    @FXML
    private Button switchmode;
    @FXML
    private TextField nameof;
    @FXML
    private Button writeto;
    @FXML
    private Button readto;
    @FXML
    private Button confirm;

    private int switchindex = 0;
    private ArrayList<book> booklist = new ArrayList<book>();
    private ArrayList<Borrower> borrowerList = new ArrayList<Borrower>();
    public String folderDirectory = System.getProperty("user.dir") + "\\BookList.txt";
    public String folderDirectoryBorrower = System.getProperty("user.dir") + "\\BorrowerList.txt";
    private boolean pass = false;
    private TextInputDialog dialog = new TextInputDialog("");
    private String name;
    private String ISBN;
    private String author;
    private double price;
    private String genre;
    private String userName;
    private int userChoice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        adborrow.setVisible(false);
        viborrow.setVisible(false);
        edborrow.setVisible(false);
        delborrow.setVisible(false);
        output.setEditable(false);
        nameof.setEditable(false);
    }

    @FXML
    private void ViewBorrower(ActionEvent event) {
        String temp = "";
        if (borrowerList.isEmpty()) {
            output.setText("Sorry there are no borrowers right now");
        } else {
            for (int i = 0; i < borrowerList.size(); i++) {
                temp = temp + borrowerList.get(i).toBorrower() + "\n";

            }
            output.setText(temp);
        }
    }

    @FXML
    private void DeleteBorrow(ActionEvent event) {
        int index = getBorrowerIndex();
        if (index != -1) {
            borrowerList.remove(index);
            output.setText("Succesfuly removed");
        } else {
            output.setText("Sorry I can't find that person");
        }
    }

    @FXML
    private void EditBorrower(ActionEvent event) {
        int index = getBorrowerIndex();
        if (index != -1) {
            dialog.setTitle("Edit borrower");
            dialog.setContentText("Which bit do you want do edit:\n1 - name\n2 - book");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                userChoice = Integer.parseInt(result.get());
            }
            switch (userChoice) {
                case 1:
                    dialog.setContentText("Please type in a name");
                    result = dialog.showAndWait();
                    if (result.isPresent()) {
                        name = result.get();
                    }
                    borrowerList.get(index).setName(name);
                    break;
                case 2:

                    int indextwo = getIndex();
                    if (indextwo != -1) {
                        book newBook = booklist.get(indextwo);
                        borrowerList.get(index).setLoanedBook(newBook);
                    }

            }
        }
    }

    @FXML
    private void NewBorrower(ActionEvent event) {
        Borrower newBorrower = new Borrower(null, null);
        dialog.setTitle("New Loan");
        dialog.setContentText("Please type in your name");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            userName = result.get();
            newBorrower.setName(userName);
        }

        int index = getIndex();
        if (index != -1) {
            book newBook = booklist.get(index);
            newBorrower.setLoanedBook(newBook);
        } else {
            output.setText("Sorry no books right now");
        }
        borrowerList.add(newBorrower);
    }

    @FXML
    private void DeletBook(ActionEvent event) {
        int index = getIndex();
        if (index != -1) {
            booklist.remove(index);
            output.setText("Succesfuly removed");
        } else {
            output.setText("Sorry I can't find that book");
        }

    }

    @FXML
    private void EditBook(ActionEvent event) {
        int index = getIndex();

        if (index != -1) {

            dialog.setContentText("Which bit would you like to edit:\n1 - name\n2 - ISBN\n3 - author\n4 - price\n5 - genre");
            dialog.setTitle("Edit book");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                userChoice = Integer.parseInt(result.get());
            }
            switch (userChoice) {
                case 1:
                    dialog.setContentText("Please type in a name");
                    result = dialog.showAndWait();
                    if (result.isPresent()) {
                        name = result.get();
                    }
                    booklist.get(index).setName(name);
                    break;
                case 2:
                    dialog.setContentText("Please type in an ISBN");
                    result = dialog.showAndWait();
                    if (result.isPresent()) {
                        ISBN = result.get();
                    }
                    booklist.get(index).setISBN(ISBN);
                    break;
                case 3:
                    dialog.setContentText("Please type in an author");
                    result = dialog.showAndWait();
                    if (result.isPresent()) {
                        author = result.get();
                    }
                    booklist.get(index).setAuthor(author);
                    break;
                case 4:
                    dialog.setContentText("Please type in a price");
                    result = dialog.showAndWait();
                    if (result.isPresent()) {
                        price = Double.parseDouble(result.get());
                    }
                    booklist.get(index).setPrice(price);
                    break;
                case 5:
                    dialog.setContentText("Please type in a genre");
                    result = dialog.showAndWait();
                    if (result.isPresent()) {
                        genre = result.get();
                    }
                    booklist.get(index).setGenre(genre);
            }
        }
    }

    @FXML
    private void ViewBook(ActionEvent event) {
        String temp = "";
        if (booklist.isEmpty()) {
            output.setText("Sorry there are no books right now");

        } else {
            for (int i = 0; i < booklist.size(); i++) {
                temp = temp + booklist.get(i).toString() + "\n";

            }
            output.setText(temp);

        }
    }

    @FXML
    private void NewBook(ActionEvent event) {
        dialog.setTitle("New Book");
        dialog.setContentText("Please type in a name");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            name = result.get();
        }
        dialog.setContentText("Please type in an ISBN");
        result = dialog.showAndWait();
        if (result.isPresent()) {
            ISBN = result.get();
        }
        dialog.setContentText("Please type in an author");
        result = dialog.showAndWait();
        if (result.isPresent()) {
            author = result.get();
        }
        dialog.setContentText("Please type in a price");
        result = dialog.showAndWait();
        if (result.isPresent()) {
            price = Double.parseDouble(result.get());
        }
        dialog.setContentText("Please type in a genre");
        result = dialog.showAndWait();
        if (result.isPresent()) {
            genre = result.get();
        }

        book mybook = new book(name, ISBN, author, price, genre);
        booklist.add(mybook);

        System.out.println(mybook.toString());

    }

    @FXML
    private void switchmode(ActionEvent event) {
        switchindex++;
        if (!(switchindex % 2 == 0)) {
            nameof.setText("Library Borrow System");
            adborrow.setVisible(true);
            viborrow.setVisible(true);
            edborrow.setVisible(true);
            delborrow.setVisible(true);
            adbook.setVisible(false);
            vibook.setVisible(false);
            edbook.setVisible(false);
            delbook.setVisible(false);
        } else {
            nameof.setText("Library Book System");
            adborrow.setVisible(false);
            viborrow.setVisible(false);
            edborrow.setVisible(false);
            delborrow.setVisible(false);
            adbook.setVisible(true);
            vibook.setVisible(true);
            edbook.setVisible(true);
            delbook.setVisible(true);
        }
    }

    @FXML
    private void writetofile(ActionEvent event) {
        try {
            FileWriter writeToFile = new FileWriter(folderDirectory, true);
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < booklist.size(); i++) {
                printToFile.println(booklist.get(i).toString());
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        try {
            FileWriter writeToFile = new FileWriter(folderDirectoryBorrower, true);
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < borrowerList.size(); i++) {
                printToFile.println(borrowerList.get(i).toBorrower() + ",");
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    private void readfromfile(ActionEvent event) {
        String lineFromFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(folderDirectory));
            while ((lineFromFile = read.readLine()) != null) {
                String[] bookDetails = lineFromFile.split(",");

                book a = new book(bookDetails[0], bookDetails[1], bookDetails[2], Double.parseDouble(bookDetails[3]), bookDetails[4]);
                booklist.add(a);
            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        try {
            BufferedReader read = new BufferedReader(new FileReader(folderDirectoryBorrower));
            while ((lineFromFile = read.readLine()) != null) {
                String[] borrowerDetails = lineFromFile.split(",");
                int index;
                if (booklist.isEmpty()) {
                    index = -1;
                } else {
                    index = -1;

                    for (int i = 0; i < booklist.size(); i++) {
                        if (borrowerDetails[1].equals(booklist.get(i).getName())) {
                            index = i;
                        } else {
                            
                        }
                    }
                }
                if (index != -1) {
                    Borrower a = new Borrower(borrowerDetails[0], booklist.get(index));
                    borrowerList.add(a);
                }
            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public int getIndex() {
        if (booklist.isEmpty()) {
            return -1;
        } else {
            int index = -1;
            dialog.setTitle("Edit book");
            dialog.setContentText("Please type in an ISBN");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                ISBN = result.get();
            }
            for (int i = 0; i < booklist.size(); i++) {
                if (ISBN.equals(booklist.get(i).getISBN())) {
                    return i;
                }
            }
        }
        return -1;

    }

    public int getBorrowerIndex() {
        if (borrowerList.isEmpty()) {
            return -1;
        } else {
            int index = -1;
            dialog.setTitle("Edit borrower");
            dialog.setContentText("Please type in a name");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                name = result.get();
            }
            for (int i = 0; i < borrowerList.size(); i++) {
                if (name.equals(borrowerList.get(i).getName())) {
                    return i;
                }
            }
        }
        return -1;
    }

    @FXML
    private void pressed(ActionEvent event) {
        pass = true;

    }
}
