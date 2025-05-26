package WaitList;

import java.util.List;

public class WaitListPayload {

    private List<String> userIds;

    public WaitListPayload() {
    }

    public WaitListPayload(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
