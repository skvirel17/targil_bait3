package view.GUI.session;

import java.util.Objects;

public class Session {

    private int id;
    private static Session activeSession;
    private static String role;

    private Session(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public static void clearSession() {
        activeSession = null;
        role = "admin";
    }

    public static Session getInstance() {
        return activeSession;
    }

    public static Session getInstance(int id) {
        return getInstance(id, "admin");
    }

    public static Session getInstance(int id, String role) {
        if (Objects.isNull(activeSession)) {
            activeSession = new Session(id, role);
        }
        return activeSession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
