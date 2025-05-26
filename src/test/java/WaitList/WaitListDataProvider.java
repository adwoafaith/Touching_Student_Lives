package WaitList;

import org.testng.annotations.DataProvider;

import java.util.List;

import static WaitList.WaitListTest.waitListId;

public class WaitListDataProvider {

    @DataProvider(name = "userIdsProvider")
    public Object[][] provideUserIds() {
        if (waitListId == null) {
            throw new RuntimeException("waitListId is null. Ensure getWaitListTest() ran and found a valid ID.");
        }

        return new Object[][]{
                {List.of(waitListId)}
        };

    }
}
