package focusflow.Model;

import javafx.beans.property.SimpleStringProperty;

public class Tugas {
    private String namaTugas;
    private int timer;
    private SimpleStringProperty timerString;

    public Tugas(String namaTugas, int timer, String timerString2) {
        this.namaTugas = namaTugas;
        this.timer = timer;
        this.timerString = new SimpleStringProperty(formatTimer(timer));
    }

    public String getNamaTugas() {
        return namaTugas;
    }

    public int getTimer() {
        return timer;
    }

    public String getTimerString() {
        return timerString.get();
    }

    public void decrementTimer() {
        timer--;
        timerString.set(formatTimer(timer));
    }

    private String formatTimer(int timer) {
        int hours = timer / 3600;
        int minutes = (timer % 3600) / 60;
        int seconds = timer % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public boolean isNotificationShown() {
        return false;
    }

    public void setNotificationShown(boolean b) {
    }
}