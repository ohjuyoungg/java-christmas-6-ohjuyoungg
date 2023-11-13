package christmas.domain.date;

public class Date {
    private final int date;

    public Date(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.valueOf(date);
    }
}