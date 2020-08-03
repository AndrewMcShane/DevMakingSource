//Strategy demo

public interface TradingStrategy
{
 void MakeTrades();
}

public class RiskyTradingStrategy implements TradingStrategy
{
 public void MakeTrades()
 {
     System.out.println("Making risky trades!");
 }
}

public class ModerateTradingStrategy implements TradingStrategy
{
 public void MakeTrades()
 {
     System.out.println("Making moderate trades.");
 }
}

public class ConservativeTradingStrategy implements TradingStrategy
{
 public void MakeTrades()
 {
     System.out.println("Making safe trades.");
 }
}

//Important for getting the current state of the market:
public enum TradeConditions 
{
 BearMarket,
 BullMarket,
 RecoveringMarket
}

public class TradingFirm
{
 // Squirrel away the strategies to conserve GC:
 private TradingStrategy riskyStrategy;
 private TradingStrategy moderateStrategy;
 private TradingStrategy safeStrategy;

 // The currently used strategy:
 private TradingStrategy currentStrategy;

 public TradingFirm()
 {
     riskyStrategy = new RiskyTradingStrategy();
     moderateStrategy = new ModerateTradingStrategy();
     safeStrategy = new ConservativeTradingStrategy();

     currentStrategy = moderateStrategy;
 }

 public void MarketUpdate(TradeConditions marketCondition)
 {
     // Select the best strategy based on the market conditions:
     switch (marketCondition) {
         case BearMarket:
             currentStrategy = safeStrategy;
             break;
         case BullMarket:
             currentStrategy = riskyStrategy;
             break;
         case RecoveringMarket:
             currentStrategy = moderateStrategy;
             break;
         default:
             break;
     }

     // Make trades with that strategy:
     currentStrategy.MakeTrades();
 }

}