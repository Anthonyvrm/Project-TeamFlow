package classes;

import org.junit.Test;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CLITest {

    private CLI cli;
    private final InputStream systemIn = System.in;

    @Before
    public void setUp() {
        cli = new CLI();
    }

    @Test
    public void testMainMenu_UserManagementOption() {
        String input = "1\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        cli.mainMenu();

        System.setIn(systemIn);
    }

    @Test
    public void testMainMenu_InvalidOption() {
        String input = "99";
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        cli.mainMenu();

        System.setIn(systemIn);
    }

    @Test
    public void testMainMenu_ScrumManagementOption() {
        String input = "4\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        cli.mainMenu();

        System.setIn(systemIn);
    }
}