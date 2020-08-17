// Observer demo in TypeScript

// The Subject class:
class StockTicker
{
    private observersRoot: TickerObserver;

    constructor()
    {
        this.observersRoot = null;
    }

    // Head-first insertion into the list:
    public addObserver(observer: TickerObserver)
    {
        observer.next = this.observersRoot;
        this.observersRoot = observer;
    }

    // In our design, we're using one observer for all stock symbols.
    // This design can be improved on further by designating one subject per ticker symbol!
    public notify(tickerSymbol: string, price: number)
    {
        let tmp = this.observersRoot;
        while(tmp != null)
        {
            tmp.update(tickerSymbol, price);
            tmp = tmp.next;
        }
    }
}


// The Observer class:
class TickerObserver
{
    public next: TickerObserver;

    private symbol: string;
    private lastPrice: number;

    constructor(symbol: string)
    {
        this.next = null;
        this.symbol = symbol;
        this.lastPrice = 0.0;

    }

    // In this example, we only update the price if it's for our symbol.
    // In a more advanced design, you could designate an observer per ticker symbol to be more efficient.
    public update(symbol:string, price: number)
    {
        if(this.symbol == symbol)
        {
            this.lastPrice = price;
        }
    }

    public getTicker(): string
    {
        return this.symbol + " : " + this.lastPrice;
    }
}