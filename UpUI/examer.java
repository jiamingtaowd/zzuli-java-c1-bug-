package UpUI;

public class examer {
    private String id;
    private String password;

    public examer() {
    }

    public examer(String id, String password) {
        this.id = id;
        this.password = password;
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "UpUI.examer{id = " + id + ", password = " + password + "}";
    }
}
