// Part of the observer demo
using System.Collections.Generic;

public class StockTicker
{
    // For brevity, we'll be using a built-in List structure:
    List<TickerObserver> observers;

    public StockTicker()
    {
        observers = new List<TickerObserver>();
    }

    public void AddObserver(TickerObserver observer)
    {
        observers.Add(observer);
    }

    public void RemoveObserver(TickerObserver observer)
    {
        observers.Remove(observer);
    }

    // In our design, we're using one observer for all stock symbols.
    // This design can be improved on further by designating one subject per ticker symbol!
    public void Notify(string tickerSymbol, float price)
    {
        // Notify all the observers of the price update.
        foreach(TickerObserver observer in observers)
        {
            observer.Update(tickerSymbol, price);
        }
    }
}