package classes;

import org.junit.Test;
import queries.QueryMessages;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class MessageTest {

    // Helper class to simulate controlled data retrieval for tests
    static class TestQueryMessages extends QueryMessages {
        private static List<Message> testMessages;

        public TestQueryMessages(List<Message> testMessages) {
            TestQueryMessages.testMessages = testMessages;
        }

        public static ArrayList<Message> getAllMessages() {
            return new ArrayList<>(testMessages); // Return pre-defined test messages as ArrayList
        }
    }

    // Helper to redirect system output for validation
    private ByteArrayOutputStream redirectOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        return outputStream;
    }

    // Helper to reset system output to its original state
    private void resetOutput() {
        System.setOut(System.out);
    }

    @Test
    public void testViewHighlighted_NoHighlightedMessages() {
        // Setup a condition with no highlighted messages
        List<Message> messages = new ArrayList<>(); // Empty list of messages
        QueryMessages queryMessages = new TestQueryMessages(messages);

        // Redirect output
        ByteArrayOutputStream outputStream = redirectOutput();

        // Test: Call the static method to display highlighted messages
        Message.viewHighlighted();

        // Validation: Validate output via the redirected stream if necessary
        // For example: Assert that the outputStream's content is empty
        resetOutput();
    }

    @Test
    public void testViewHighlighted_WithHighlightedMessages() {
        // Setup: Some messages with one highlighted
        List<Message> messages = List.of(
                new Message(new User("user1", true), "This is a highlighted message", true, new Chat("Chat1")),
                new Message(new User("user2", false), "This is a normal message", false, new Chat("Chat2"))
        );
        QueryMessages queryMessages = new TestQueryMessages(messages);

        // Redirect output
        ByteArrayOutputStream outputStream = redirectOutput();

        // Test: Call the static method to display highlighted messages
        Message.viewHighlighted();

        // Validation: Validate output via the redirected stream if necessary
        resetOutput();
    }
}