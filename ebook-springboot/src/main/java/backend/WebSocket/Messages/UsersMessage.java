package backend.WebSocket.Messages;

import java.util.List;

public class UsersMessage extends Message {
    private List<String> userList;

    public UsersMessage(List<String> userList) {
        this.userList = userList;
    }

    public List<String> getUserList() {
        return userList;
    }

    /* For logging purposes */
    @Override
    public String toString() {
        return "[UsersMessage] " + userList.toString();
    }
}
