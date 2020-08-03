# Observer demo

# The Observer class that recieves updates from the ticker on a specific symbol:
class TickerObserver(object):

    def __init__(self, tickerSymbol):
        self.tickerSymbol = tickerSymbol
        self.lastPrice = 0.0

    def Update(self, symbol, price):
        if symbol == self.tickerSymbol:
            self.lastPrice = price

    def GetTicker(self):
        return self.tickerSymbol + " : " + str(self.lastPrice)
    


# The ticker subject that sends out updates to all observers:
class StockTicker(object):

    def __init__(self):
        self.observers = []

    def AttachObserver(self, tickerObserver):
        self.observers.append(tickerObserver)
    
    def RemoveObserver(self, tickerObserver):
        self.observers.remove(tickerObserver)

    def Notify(self, symbol, price):
        for observer in self.observers:
            observer.Update(symbol, price)