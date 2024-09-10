package GUI.session;

import java.util.Objects;

public class Session {

    private int id;
    private static Session activeSession;

    private Session(int id) {
        this.id = id;
    }

    public static void clearSession() {
        activeSession = null;
    }

    public static Session getInstance() {
        return activeSession;
    }

    public static Session getInstance(int id) {
        if (Objects.isNull(activeSession)) {
            activeSession = new Session(id);
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
