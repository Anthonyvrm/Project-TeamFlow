package classes;

import org.junit.Test;

public class ChatTest {

    @Test
    public void testViewHighlightedMessages_NoMessages() {
        Chat chat = new Chat("Test Chat");

        // Method call
        chat.viewHighlightedMessages();

        // No assertions as this method primarily prints to the console
        // Test would pass if no exceptions occur
    }

    @Test
    public void testViewChatMessages_NoMessages() {
        Chat chat = new Chat("Test Chat");

        // Method call
        chat.viewChatMessages();

        // No assertions as this method primarily prints to the console
        // Test would pass if no exceptions occur
    }

    @Test
    public void testViewChatMessages_SingleMessage() {
        Chat chat = new Chat("Test Chat");
        User user = new User("john_doe", false);
        chat.addMessage(new Message(user, "This is a normal message", false, chat));

        // Method call
        chat.viewChatMessages();

        // No assertions as this method primarily prints to the console
        // Test would pass if no exceptions occur
    }

    @Test
    public void testViewChatMessages_SingleHighlightedMessage() {
        Chat chat = new Chat("Test Chat");
        User user = new User("john_doe", true);
        chat.addMessage(new Message(user, "This is a highlighted message", true, chat));

        // Method call
        chat.viewChatMessages();

        // No assertions as this method primarily prints to the console
        // Test would pass if no exceptions occur
    }

    @Test
    public void testViewChatMessages_MultipleMessages() {
        Chat chat = new Chat("Test Chat");
        User user1 = new User("john_doe", false);
        User user2 = new User("jane_doe", true);
        chat.addMessage(new Message(user1, "Regular message 1", false, chat));
        chat.addMessage(new Message(user2, "Highlighted message", true, chat));
        chat.addMessage(new Message(user1, "Regular message 2", false, chat));

        // Method call
        chat.viewChatMessages();

        // No assertions as this method primarily prints to the console
        // Test would pass if no exceptions occur
    }

    @Test
    public void testViewHighlightedMessages_NoHighlightedMessages() {
        Chat chat = new Chat("Test Chat");
        User user = new User("john_doe", false);
        chat.addMessage(new Message(user, "This is a normal message", false, chat));

        // Method call
        chat.viewHighlightedMessages();

        // No assertions as this method primarily prints to the console
        // Test would pass if no exceptions occur
    }

    @Test
    public void testViewHighlightedMessages_WithHighlightedMessages() {
        Chat chat = new Chat("Test Chat");
        User user = new User("john_doe", true);
        chat.addMessage(new Message(user, "This is an important message", true, chat));
        chat.addMessage(new Message(user, "This is another normal message", false, chat));

        // Method call
        chat.viewHighlightedMessages();

        // No assertions as this method primarily prints to the console
        // Test would pass if no exceptions occur
    }

    @Test
    public void testViewHighlightedMessages_MultipleHighlightedMessages() {
        Chat chat = new Chat("Test Chat");
        User user = new User("jane_doe", true);
        chat.addMessage(new Message(user, "Important message 1", true, chat));
        chat.addMessage(new Message(user, "Important message 2", true, chat));
        chat.addMessage(new Message(user, "Regular message", false, chat));

        // Method call
        chat.viewHighlightedMessages();

        // No assertions as this method primarily prints to the console
        // Test would pass if no exceptions occur
    }
}