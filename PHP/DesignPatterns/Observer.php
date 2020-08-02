<?php

// Stock ticker observer:

class TickerObserver
{
    // The symbol that this observer will monitor for:
    private $symbol;
    // The last price observed:
    private $lastPrice;

    function __construct($symbol)
    {
        $this->symbol = $symbol;
        $this->lastPrice = 0.0;
    }

    // Recieves an update from the subject:
    public function Update($symbol, $price)
    {
        if($this->symbol === $symbol)
        {
            $this->lastPrice = $price;
        }
    }

    // Used to get the most recent update information:
    public function GetTicker()
    {
        return "{$this->symbol} : {$this->lastPrice}";
    }
}

class StockTicker
{
    // The subscribed observers:
    private $observers = array();

    function __construct()
    {   }

    // Adds an observer to the list:
    public function AttachObserver($observer)
    {
        $this->observers[] = $observer;
    }

    // Notifies all observers:
    public function Notify($symbol, $price)
    {
        foreach($this->observers as $observer)
        {
            $observer->Update($symbol, $price);
        }
    }

}