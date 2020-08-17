// Strategy demo in TypeScript

interface TradingStrategy
{
    makeTrades(): void;
}

class RiskyTradingStrategy implements TradingStrategy
{
    public makeTrades()
    {
        console.log("Making risky trades!");
    }
}

class ModerateTradingStrategy implements TradingStrategy
{
    public makeTrades()
    {
        console.log("Making moderate trades.");
    }
}

class ConservativeTradingStrategy implements TradingStrategy
{
    public makeTrades()
    {
        console.log("Making safe trades.");
    }
}

enum TradeConditions
{
    BearMarket,
    BullMarket, 
    RecoveringMarket
}

class TradingFirm
{
    private riskyStrategy: TradingStrategy;
    private moderateStrategy: TradingStrategy;
    private safeStrategy: TradingStrategy;

    private currentStrategy: TradingStrategy;

    constructor()
    {
        this.riskyStrategy = new RiskyTradingStrategy();
        this.moderateStrategy = new ModerateTradingStrategy();
        this.safeStrategy = new ConservativeTradingStrategy();

        this.currentStrategy = this.moderateStrategy;
    }

    public marketUpdate(marketCondition: TradeConditions)
    {
        switch(marketCondition)
        {
            case TradeConditions.BearMarket:
                this.currentStrategy = this.safeStrategy;
                break;
            case TradeConditions.BullMarket:
                this.currentStrategy = this.riskyStrategy;
                break;
            case TradeConditions.RecoveringMarket:
                this.currentStrategy = this.moderateStrategy;
                break;
        }

        this.currentStrategy.makeTrades();
    }
    
}

class StrategyDemo
{
    public execute()
    {
        let firm = new TradingFirm();

        firm.marketUpdate(TradeConditions.BullMarket); // "Making risky trades!"
        firm.marketUpdate(TradeConditions.BearMarket); // "Making safe trades."
        firm.marketUpdate(TradeConditions.RecoveringMarket); // "Making moderate trades."
    }
}