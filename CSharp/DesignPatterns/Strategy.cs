using System;

// The Base strategy interface:
public interface CoachingStrategy
{
    void BroadcastStrategy();
}

// When the team is tied or losing the game:
public class OffensiveStrategy: CoachingStrategy
{
    public void BroadcastStrategy()
    {
        Console.WriteLine("Crash the net!");
    }
}

// When the team is ahead of the other team:
public class DefendLeadStrategy: CoachingStrategy
{
    public void BroadcastStrategy()
    {
        Console.WriteLine("Don't be sloppy, maintain this lead!");
    }
}


// Class that utilizes the strategies:
// Maintains strategies itself to avoid GC:
public class TeamBench
{
    protected CoachingStrategy offensiveStrategy;
    protected CoachingStrategy defensiveStrategy;

    protected CoachingStrategy currentStrategy;

    protected int teamScore;
    protected int opposingTeamScore;

    public TeamBench()
    {
        offensiveStrategy = new OffensiveStrategy();
        defensiveStrategy = new DefendLeadStrategy();
        this.SetStrategy(this.offensiveStrategy);

        teamScore = 0;
        opposingTeamScore = 0;
    }

    // Helper method to set the current strategy:
    protected void SetStrategy(CoachingStrategy strategy)
    {
        this.currentStrategy = strategy;
    }

    // When our team scores, compare the scores and set the strategy.
    public void TeamScored()
    {
        this.teamScore++;

        if(teamScore > opposingTeamScore)
        {
            this.SetStrategy(this.defensiveStrategy);
        }
    }

    // When the opposing team scores, check if a strategy change is needed.
    public void OpposingTeamScored()
    {
        this.opposingTeamScore++;

        if(opposingTeamScore >= teamScore)
        {
            this.SetStrategy(this.offensiveStrategy);
        }
    }

    // Get the recommended strategy from the coach:
    public void BroadcastCoachStrategy()
    {
        this.currentStrategy.BroadcastStrategy();
    }
}