import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String pswd;
    private String phoneNumber;

    public User(int id, String pswd, String phoneNumber) {
        this.id = id;
        this.pswd = pswd;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", pswd='" + pswd + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
