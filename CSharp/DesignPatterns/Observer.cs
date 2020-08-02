// Part of the observer demo

public class TickerObserver
{
    // The symbol that this observer will monitor:
    string symbol;
    // The price last observed by the subject:
    float lastPrice;

    public TickerObserver(string symbol)
    {
        this.symbol = symbol;
        lastPrice = 0.0f;
    }

    // In this example, we only update the price if it's for our symbol.
    // In a more advanced design, you could designate an observer per ticker symbol to be more efficient.
    public void Update(string symbol, float price)
    {
        if(symbol.Equals(this.symbol))
        {
            this.lastPrice = price;
        }
    }

    // When the client wants to be updated on the current ticker status, this can be called:
    public string GetTicker()
    {
        return symbol + " : " + lastPrice.ToString();
    }

}