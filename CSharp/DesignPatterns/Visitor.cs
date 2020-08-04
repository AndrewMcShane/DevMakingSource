// Visitor pattern example using a battle system:

// In this example, we'll be doing things a little differently:
// Our base class with be both the visitor and the visitee!
// Additionally, we'll be implementing it as an abstract class
// although, you should take great care when using an abstract class for the visitor pattern!

using System;

public abstract class ElementalType
{
    protected const float EFFECTIVE = 2.0f;
    protected const float NOT_EFFECTIVE = 0.25f;

    // Our base accept visitation method:
    public abstract float Attack(ElementalType defenceType);

    // ===================
    // Elemental types:
    // ===================
    // The methods are virtual, which makes them optional.
    // This way, implementing classes only need to have methods for special values:
    public virtual float GetAttackMultiplier(WaterType attackType) { return 1.0f; }
    public virtual float GetAttackMultiplier(FireType attackType) { return 1.0f; }
    public virtual float GetAttackMultiplier(NormalType attackType) { return 1.0f; }

}

// Our water class:
public class WaterType: ElementalType
{
    // Base accept method:
    public override float Attack(ElementalType defenceType)
    {
        return defenceType.GetAttackMultiplier(this);
    }

    // We want to override Fire, Water, and Normal types:
    public override float GetAttackMultiplier(FireType attackType)
    {
        // A Fire move against water is not effective:
        return this.NOT_EFFECTIVE;
    }

    public override float GetAttackMultiplier(WaterType attackType)
    {
        // A water move against water is not effective:
        return this.NOT_EFFECTIVE;
    }

    public override float GetAttackMultiplier(NormalType attackType)
    {
        // A normal move against water is effective:
        return this.EFFECTIVE;
    }
}

public class FireType: ElementalType
{
    // Base accept method:
    public override float Attack(ElementalType defenceType)
    {
        return defenceType.GetAttackMultiplier(this);
    }

    // We want to override Fire and Water types:
    public override float GetAttackMultiplier(FireType attackType)
    {
        // A Fire move against fire is not effective:
        return this.NOT_EFFECTIVE;
    }

    public override float GetAttackMultiplier(WaterType attackType)
    {
        // A water move against fire is effective:
        return this.EFFECTIVE;
    }
}

public class NormalType: ElementalType
{
    // Base accept method:
    public override float Attack(ElementalType defenceType)
    {
        return defenceType.GetAttackMultiplier(this);
    }

    // We want to override Fire and Water types:
    public override float GetAttackMultiplier(FireType attackType)
    {
        // A Fire move against normal is effective:
        return this.EFFECTIVE;
    }

    public override float GetAttackMultiplier(WaterType attackType)
    {
        // A water move against normal is not effective:
        return this.NOT_EFFECTIVE;
    }
}


// Using our classes in client code:
public class Solution
{
    public static void main(string[] args)
    {
        // Create our combatants:
        ElementalType cerberus = new FireType();
        ElementalType hydra = new WaterType();
        ElementalType heracles = new NormalType();

        // persephone attacks poseidon:
        float multiplier = cerberus.Attack(hydra);
        Console.WriteLine("Persephone attacks Poseidon and deals " + multiplier + " x damage!");

        // poseidon attacks back:
        multiplier = hydra.Attack(cerberus);
        Console.WriteLine("Poseidon attacks back and deals " + multiplier + " x damage!");

        // Heracles attacks Poseidon:
        multiplier = heracles.Attack(hydra);
        Console.WriteLine("Heracles attacks Poseidon and deals " + multiplier + " x damage!");

        // Persephone attacks Heracles:
        multiplier = cerberus.Attack(heracles);
        Console.WriteLine("Persephone attacks Heracles and deals " + multiplier + " x damage!");

        Console.WriteLine("The battle has ended!");
    }
}