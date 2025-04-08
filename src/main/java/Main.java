import dao.UserDAO;
import database.DatabaseSetup;
import classes.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the database
        DatabaseSetup.createTables();

        UserDAO.insertTestUser();

        //Genereer het menu
        CLI.main();
    }
}
