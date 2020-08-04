// Builder Demo in C#

using System.Collections.Generic;
using System;

public class SuperHero
{
    string name;
    string alterEgo;
    string baseOfOperations;
    string nemesis;

    List<string> strengths;
    List<string> weaknesses;

    bool wearsCape;

    public SuperHero(string name, string alterEgo, string baseOfOperations, string nemesis, 
        List<string> strengths, List<string> weaknesses, bool wearsCape)
    {
        this.name = name;
        this.alterEgo = alterEgo;
        this.baseOfOperations = baseOfOperations;
        this.nemesis = nemesis;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.wearsCape = wearsCape;
    }

    public void PrintProfile()
    {
        Console.WriteLine("SuperHero Spotlight: " + this.name);
        Console.WriteLine("Alter Ego: " + this.alterEgo);
        Console.WriteLine("Base Of Operations: " + this.baseOfOperations);
        Console.WriteLine("Strengths: ");
        foreach(string strength in this.strengths)
        {
            Console.WriteLine("\t-"+ strength);
        }
        Console.WriteLine("Weaknesses: ");
        foreach(string weakness in this.weaknesses)
        {
            Console.WriteLine("\t-" + weakness);
        }

        Console.WriteLine("Nemesis:" + this.nemesis);
        Console.WriteLine("Wears a cape:" + this.wearsCape);

    }

}

// Hero builder:
public class HeroBuilder
{
    // Copy the properties to modify easily:
    string name;
    string alterEgo;
    string baseOfOperations;
    string nemesis;

    List<string> strengths;
    List<string> weaknesses;

    bool wearsCape;

    // Constructor:
    public HeroBuilder()
    {
        this.strengths = new List<string>();
        this.weaknesses = new List<string>();
    }

    // Set methods for setting the properties of the class:
    public HeroBuilder SetName(string heroName)
    {
        this.name = heroName;
        return this;
    }

    public HeroBuilder SetAlterEgo(string alterEgo)
    {
        this.alterEgo = alterEgo;
        return this;
    }

    public HeroBuilder SetBaseOfOperations(string location)
    {
        this.baseOfOperations = location;
        return this;
    }

    public HeroBuilder SetNemesis(string nemesis)
    {
        this.nemesis = nemesis;
        return this;
    }

    public HeroBuilder AddStrength(string strength)
    {
        this.strengths.Add(strength);
        return this;
    }

    public HeroBuilder AddWeakness(string weakness)
    {
        this.weaknesses.Add(weakness);
        return this;
    }

    public HeroBuilder WearsCape(bool wearsCape)
    {
        this.wearsCape = wearsCape;
        return this;
    }

    // Builds out our new hero:
    public SuperHero BuildHero()
    {
        return new SuperHero(this.name, this.alterEgo, this.baseOfOperations,
        this.nemesis, this.strengths, this.weaknesses, this,wearsCape);
    }
}


public class Solution
{
    public static void main(string[] args)
    {
        HeroBuilder builder = new HeroBuilder();

        builder.SetName("Batman")
        .SetAlterEgo("Bruce Wayne")
        .SetBaseOfOperations("Gotham City")
        .SetNemesis("The Joker, Ra's-al-Ghul, The Court of Owls")
        .WearsCape(true);

        // Breaking it up for readability:
        builder.AddStrength("Genius-level intellect")
        .AddStrength("Expert in forensics")
        .AddStrength("Trained to physical perfection")
        .AddStrength("The World's Greatest Detective")
        .AddStrength("Lives by a code of honor to never kill");

        builder.AddWeakness("Can be prey to his own anger")
        .AddWeakness("Often refuses help from others");

        // Create the character:
        SuperHero theDarkKnight = builder.BuildHero();

        // Get to know the character!
        theDarkKnight.PrintProfile();
    }
}