
# Types of trading strategies:
class RiskyTradingStrategy(object):
    def MakeTrades(self):
        print("Making risky trades!")

class ModerateTradingStrategy(object):
    def MakeTrades(self):
        print("Making moderate trades.")

class ConservativeTradingStrategy(object):
    def MakeTrades(self):
        print("Making safe trades.")

# Not Supported below Python 3.4!
from enum import Enum
class TradeConditions(Enum):
    BearMarket = 0,
    BullMarket = 1,
    RecoveringMarket = 2

# The trading firm which changes strategies based on market conditions:
class TradingFirm(object):
    def __init__(self):
        self.riskyStrategy = RiskyTradingStrategy()
        self.moderateStrategy = ModerateTradingStrategy()
        self.safeStrategy = ConservativeTradingStrategy()

        self.currentStrategy = self.moderateStrategy

    def MarketUpdte(self, tradeConditions):
        # Select the best strategy for the market conditions:
        if tradeConditions == TradeConditions.BearMarket:
            self.currentStrategy = self.safeStrategy
        elif tradeConditions == TradeConditions.BullMarket:
            self.currentStrategy = self.riskyStrategy
        elif tradeConditions == TradeConditions.RecoveringMarket:
            self.currentStrategy = self.moderateStrategy

        # Make trades with that strategy:
        self.currentStrategy.MakeTrades()