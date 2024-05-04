package part1;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    void registerInvestor(Investor investor);
    void unregisterInvestor(Investor investor);
    void updatePrice(float price);
}

class Stock implements Subject {
    private final String symbol;
    private float price;
    private List<Investor> investors;

    public Stock(String symbol) {
        this.symbol = symbol;
        this.price = 0.0f;
        this.investors = new ArrayList<>();
    }

    public void registerInvestor(Investor investor) {
        investors.add(investor);
    }

    public void unregisterInvestor(Investor investor) {
        investors.remove(investor);
    }

    public void updatePrice(float price) {
        this.price = price;
        notifyInvestors();
    }

    private void notifyInvestors() {
        for (Investor investor : investors) {
            investor.update(this, price);
        }
    }

    @Override
    public String toString() {
        return symbol;
    }
}


interface Observer {
    void update(Stock stock, float price);
}

class Investor implements Observer {
    private final String name;
    private List<Stock> stocks;

    public Investor(String name) {
        this.name = name;
        this.stocks = new ArrayList<>();
    }

    public void update(Stock stock, float price) {
        System.out.println("Investor " + name + " received update for stock " + stock + ": Price is now " + price);
    }
}

public class Main {
    public static void main(String[] args) {

        Stock facebook = new Stock("FB");
        Stock google = new Stock("GOOGLE");


        Investor investor1 = new Investor("John");
        Investor investor2 = new Investor("Alice");


        facebook.registerInvestor(investor1);
        google.registerInvestor(investor1);
        google.registerInvestor(investor2);


        facebook.updatePrice(150.0f);
        google.updatePrice(2500.0f);
    }
}
