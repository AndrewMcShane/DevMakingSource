// Coaching  strategy demo

#include <iostream>

// Base class for all coaching strategies:
class CoachingStrategy
{
    public:
        CoachingStrategy() { };
        virtual ~CoachingStrategy() { };

        // Pure abstract method:
        // When a coach needs to tell the team what needs to happen, this method is used:
        virtual void BroadcastStrategy() = 0;

};

// Coaching strategy implementations:

// When the team needs to be on the attack, use this:
class OffensiveStrategy : public CoachingStrategy
{
    public:
        OffensiveStrategy() : CoachingStrategy() { };
        void BroadcastStrategy() override
        {
            std::cout << "Crash the net!" << std::endl;
        }
};

// When the team needs to defend the lead:
class DefendLeadStrategy : public CoachingStrategy
{
    public:
        DefendLeadStrategy() : CoachingStrategy() { };
        void BroadcastStrategy() override
        {
            std::cout << "Don't be sloppy, maintain this lead!" << std::endl;
        }
};


// The team bench where the coach gives recommendations based on the current game stats.
class TeamBench
{
    public:
        // Constructor:
        TeamBench()
        : teamScore(0), opposingScore(0)
        {
            // Default strategy:
            this->pOffensiveStrategy = new OffensiveStrategy();
            this->pDefensiveStrategy = new DefendLeadStrategy();
            this->SetStrategy(this->pOffensiveStrategy);
        };

        // Destructor:
        ~TeamBench()
        {
            // Clean up:
            this->pCurrentStrategy = nullptr;
            delete this->pOffensiveStrategy;
            delete this->pDefensiveStrategy;
        }

        // When our team scores, call this method:
        void TeamScored()
        {
            teamScore++;

            if(teamScore > opposingScore)
            {
                this->SetStrategy(this->pDefensiveStrategy);
            }
        }

        // When the opposing team scores, call this method:
        void OpposingTeamScored()
        {
            opposingScore++;

            if(opposingScore >= teamScore)
            {
                this->SetStrategy(this->pOffensiveStrategy);
            }
        }

        // Get the coaches reccomended strategy:
        void BroadcastCoachStrategy()
        {
            this->pCurrentStrategy->BroadcastStrategy();
        }


    protected:

        // Protected helper to set the current strategy.
        void SetStrategy(CoachingStrategy* pStrategy)
        {
            this->pCurrentStrategy = pStrategy;
        }

        // Create the various strategies from the get-go to avoid allocations:
        CoachingStrategy* pOffensiveStrategy;
        CoachingStrategy* pDefensiveStrategy;

        // The current strategy:
        CoachingStrategy* pCurrentStrategy;

        // Game stats:
        int teamScore;
        int opposingScore;
};