package dataProvider;

import Payload.RafflePayload;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.List;

public class RaffleDataProvider {

    @DataProvider(name = "raffleData")
    public static Object[][] provideRaffleData() {
        List<Integer> institutionIds = Arrays.asList();
        List<String> degrees = Arrays.asList();
        List<Integer> levels = Arrays.asList();
        List<String> programmes = Arrays.asList(); // Empty List

        RafflePayload rafflePayload = new RafflePayload(
                "Automation Raffle",
                null,
                null,
                8,
                3,
                1,
                institutionIds,
                degrees,
                levels,
                programmes,
                false,
                "active",
                "Join our Automation raffle"
        );

        return new Object[][]{{rafflePayload}};
    }
}
