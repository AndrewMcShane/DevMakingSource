<?php

interface iTradingStrategy
{
    public function MakeTrades();
}

class RiskyTradingStrategy implements iTradingStrategy
{
    public function MakeTrades()
    {
        echo "Making risky trades!";
    }
}

class ModerateTradingStrategy implements iTradingStrategy
{
    public function MakeTrades()
    {
        echo "Making moderate trades.";
    }
}

class ConservativeTradingStrategy implements iTradingStrategy
{
    public function MakeTrades()
    {
        echo "Making safe trades.";
    }
}

class TradingFirm
{
    // Squirrel away the strategies:
    private $riskyStrategy;
    private $moderateStrategy;
    private $safeStrategy;

    private $currentStrategy;

    function __construct()
    {
        $this->riskyStrategy = new RiskyTradingStrategy();
        $this->moderateStrategy = new ModerateTradingStrategy();
        $this->safeStrategy = new ConservativeTradingStrategy();

        $this->currentStrategy = $this->moderateStrategy;
    }

    public function MarketUpdate($tradeConditions)
    {
        // Select the strategy based on market conditions:
        if($tradeConditions === "BearMarket")
        {
            $this->currentStrategy = $this->safeStrategy;
        }
        else if($tradeConditions  === "BullMarket")
        {
            $this->currentStrategy = $this->riskyStrategy;
        }
        else if($tradeConditions === "RecoveringMarket")
        {
            $this->currentStrategy = $this->moderateStrategy;
        }

        // Make trades with that strategy:
        $this->currentStrategy->MakeTrades();
    }
}
