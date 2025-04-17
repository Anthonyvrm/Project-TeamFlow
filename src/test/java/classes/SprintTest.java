package classes;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SprintTest {

    /**
     * Class being tested: Sprint
     * Method being tested: isCurrent
     * <p>
     * The isCurrent method checks whether the current date and time
     * falls within the start and end dates of the Sprint.
     */

    @Test
    public void testIsCurrentDuringSprint() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = startDate.plusDays(14);
        Sprint sprint = new Sprint(1, startDate, endDate, null);

        assertTrue("Expected the sprint to be current.", sprint.isCurrent());
    }

    @Test
    public void testIsCurrentBeforeSprintStarts() {
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = startDate.plusDays(14);
        Sprint sprint = new Sprint(1, startDate, endDate, null);

        assertFalse("Expected the sprint to not be current before it starts.", sprint.isCurrent());
    }

    @Test
    public void testIsCurrentAfterSprintEnds() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(15);
        LocalDateTime endDate = startDate.plusDays(14);
        Sprint sprint = new Sprint(1, startDate, endDate, null);

        assertFalse("Expected the sprint to not be current after it ends.", sprint.isCurrent());
    }

    @Test
    public void testIsCurrentOnSprintStartDate() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(14);
        Sprint sprint = new Sprint(1, startDate, endDate, null);

        assertTrue("Expected the sprint to be current on its start date.", sprint.isCurrent());
    }

    @Test
    public void testIsCurrentOnSprintEndDate() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(14);
        LocalDateTime endDate = startDate.plusDays(14);
        Sprint sprint = new Sprint(1, startDate, endDate, null);

        assertFalse("Expected the sprint to not be current on its end date.", sprint.isCurrent());
    }
}