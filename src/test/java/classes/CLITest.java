package classes;

import org.junit.Before;
import org.junit.Test;

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
    public void testMainMenu_SendMessageOption() {
        String input = "2\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        cli.mainMenu();

        System.setIn(systemIn);
    }

    @Test
    public void testMainMenu_ViewChatsOption() {
        String input = "3\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        cli.mainMenu();

        System.setIn(systemIn);
    }

    @Test
    public void testMainMenu_ExitOption() {
        String input = "5\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        // Assuming exit generates output or interaction to verify behavior
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