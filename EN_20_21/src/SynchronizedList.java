import java.util.ArrayList;

public class SynchronizedList {
    private ArrayList<User> userList = new ArrayList<>();

    public synchronized void addUser (User newUser) {
        userList.add(newUser);
    }

    public synchronized ArrayList<User> getAllUsers() {
        return userList;
    }
}
