package edu.sdccd.cisc191.template.library;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class LibraryManagerGUI extends Application
{
    // Create an instance of the LibraryManager
    private final LibraryManager libraryManager = LibraryManager.getInstance();

    @Override
    public void start(Stage primaryStage)
    {
        // Create the root layout of the GUI using VBox
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #d3b48a;");

        // Create the title text
        Text titleText = new Text("Library Manager");
        titleText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        titleText.setFill(Color.BROWN);

        // Create buttons for various operations
        Button addBookButton = createStyledButton("Add Book");
        Button searchBookButton = createStyledButton("Search Book by Index");
        Button updateBookButton = createStyledButton("Update Book Information");
        Button deleteBookButton = createStyledButton("Delete Book");
        Button printAllBooksButton = createStyledButton("Print All Books");
        Button expandLibraryButton = createStyledButton("Expand Library");
        Button shrinkLibraryButton = createStyledButton("Shrink Library");
        Button exitButton = createStyledButton("Exit");

        // Set actions for the buttons using lambda expressions
        addBookButton.setOnAction(event -> addBook());
        searchBookButton.setOnAction(event -> searchBookByIndex());
        updateBookButton.setOnAction(event -> updateBookInformation());
        deleteBookButton.setOnAction(event -> deleteBook());
        printAllBooksButton.setOnAction(event -> printAllBooks());
        expandLibraryButton.setOnAction(event -> expandLibrary());
        shrinkLibraryButton.setOnAction(event -> shrinkLibrary());
        exitButton.setOnAction(event -> {
            System.out.println("Exiting...");
            primaryStage.close();
        });

        // Add the UI components to the root layout
        root.getChildren().addAll(
                titleText,
                addBookButton,
                searchBookButton,
                updateBookButton,
                deleteBookButton,
                printAllBooksButton,
                expandLibraryButton,
                shrinkLibraryButton,
                exitButton
        );

        // Create the scene and set it in the stage
        Scene scene = new Scene(root, 300, 400);
        primaryStage.setTitle("Library Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   // Creates brown buttons with the specific font

    private Button createStyledButton(String text)
    {
        Button button = new Button(text);
        button.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #7e442a;");
        return button;
    }

    /**
     * Handle the "Add Book" button action.
     * Prompt the user to enter book details and add the book to the library.
     */
    private void addBook()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Book");
        dialog.setHeaderText("Enter book details:");
        dialog.setContentText("Format: Title, Author");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> {
            String[] parts = input.split(",", 2);
            if (parts.length == 2)
            {
                String title = parts[0].trim();
                String author = parts[1].trim();
                libraryManager.getLibrary().addBook(new Book(title, author));
                showAlert("Book added successfully.");
            } else
            {
                showAlert("Invalid input format.");
            }
        });
    }

    /**
     * Handle the "Search Book by Index" button action.
     * Prompt the user to enter an index and display the corresponding book information.
     */
    private void searchBookByIndex()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search Book by Index");
        dialog.setHeaderText("Enter the index of the book to search:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(indexStr ->
        {
            try
            {
                int index = Integer.parseInt(indexStr);
                Book book = libraryManager.getLibrary().getBookByIndex(index);
                if (book != null) {
                    showAlert("Book found:\n" + book.toString());
                } else {
                    showAlert("Invalid index or book not found.");
                }
            } catch (NumberFormatException e)
            {
                showAlert("Invalid index format.");
            }
        });
    }

    /**
     * Handle the "Update Book Information" button action.
     * Prompt the user to enter an index and updated book information, and update the book.
     */
    private void updateBookInformation() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Update Book Information");
        dialog.setHeaderText("Enter the index of the book to update:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(indexStr -> {
            try
            {
                int index = Integer.parseInt(indexStr);
                Book book = libraryManager.getLibrary().getBookByIndex(index);
                if (book != null)
                {
                    TextInputDialog updateDialog = new TextInputDialog();
                    updateDialog.setTitle("Update Book Information");
                    updateDialog.setHeaderText("Enter the updated book information:");
                    updateDialog.setContentText("Format: Title, Author");
                    Optional<String> updateResult = updateDialog.showAndWait();
                    updateResult.ifPresent(input ->
                    {
                        String[] parts = input.split(",", 2);
                        if (parts.length == 2)
                        {
                            String title = parts[0].trim();
                            String author = parts[1].trim();
                            book.setTitle(title);
                            book.setAuthor(author);
                            showAlert("Book information updated successfully.");
                        } else
                        {
                            showAlert("Invalid input format.");
                        }
                    });
                } else
                {
                    showAlert("Invalid index or book not found.");
                }
            } catch (NumberFormatException e) {
                showAlert("Invalid index format.");
            }
        });
    }

    /**
     * Handle the "Delete Book" button action.
     * Prompt the user to enter an index and delete the corresponding book.
     */
    private void deleteBook()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Book");
        dialog.setHeaderText("Enter book index to delete:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(indexStr -> {
            try
            {
                int index = Integer.parseInt(indexStr);
                boolean deleted = libraryManager.getLibrary().deleteBookByIndex(index);
                if (deleted)
                {
                    showAlert("Book deleted successfully.");
                } else
                {
                    showAlert("Invalid index or book not found.");
                }
            } catch (NumberFormatException e)
            {
                showAlert("Invalid index format.");
            }
        });
    }

    /**
     * Handle the "Print All Books" button action.
     * Displays the information of all books in the library.
     */
    private void printAllBooks()
    {
        String allBooksInfo = libraryManager.getLibrary().getAllBooksInfo();
        showAlert("All Books:\n" + allBooksInfo);
    }

    /**
     * Handle the "Expand Library" button action.
     * Expand the library size.
     */
    private void expandLibrary()
    {
        libraryManager.getLibrary().expandLibrary();
        showAlert("Library expanded.");
    }

    /**
     * Handle the "Shrink Library" button action.
     * Shrink the library size.
     */
    private void shrinkLibrary()
    {
        libraryManager.getLibrary().shrinkLibrary();
        showAlert("Library shrunk.");
    }

    // Show an alert with the specified message.

    private void showAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Library Manager");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}
