
abstract class Media {
    protected String title;
    protected int overdueDays;

    Media(String title, int overdueDays) {
        this.title = title;
        this.overdueDays = Math.max(0, overdueDays);
    }

    abstract double lateFee();
}

class Book extends Media {
    Book(String title, int overdueDays) {
        super(title, overdueDays);
    }

    @Override
    double lateFee() {
        return overdueDays * 5.0;
    }
}

class DVD extends Media {
    DVD(String title, int overdueDays) {
        super(title, overdueDays);
    }

    @Override
    double lateFee() {
        return overdueDays * 10.0;
    }
}

class Magazine extends Media {
    Magazine(String title, int overdueDays) {
        super(title, overdueDays);
    }

    @Override
    double lateFee() {
        return overdueDays * 2.0;
    }
}
