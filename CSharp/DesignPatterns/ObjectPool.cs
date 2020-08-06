// Object Pool Demo in C#
using System.Collections.Generic;
using System;

// The base Zombie class:
public class Zombie
{
    private const float START_HEALTH = 100.0f;

    private float health;

    // ... More variables for the zombie:

    public Zombie()
    {
        this.Clean();
    }

    // This seems only slightly ironic:
    public void IsAlive()
    {
        return health > 0.0f;
    }

    public void TakeDamage(float damage)
    {
        health -= damage;
    }

    // This resets the zombie to a "factory new" setting:
    public void Clean()
    {
        this.health = START_HEALTH;
    }

}

public class ZombiePool
{
    protected List<Zombie> activeList;
    protected List<Zombie> reserveList;

    int numberActive;
    int numberReserved;
    int spawnCap;

    public ZombiePool(int reserve = 5, int spawnCap = 20)
    {
        this.activeList = new List<Zombie>();
        this.reserveList = new List<Zombie>();

        this.numberActive = 0;
        this.numberReserved = 0;
        this.spawnCap = spawnCap;

        InitializeReserve(reserve);
    }

    private void InitializeReserve(int reserve)
    {
        for(int i = 0; i < reserve; i++)
        {
            Zombie zomb = new Zombie();
            this.reserveList.Add(zomb);
            this.numberReserved++;
        }
    }

    public bool CanSpawn()
    {
        return this.numberActive < this.spawnCap;
    }

    public Zombie GetZombie()
    {
        /*
            NOTICE:
            In your code, make sure to check the spawn limit.
            you can either:
                1. Throw an exception
                2. Return null
                3. Spawn a new zombie anyways.

                We'll return null.
        */

        if(this.numberActive >= spawnCap)
        {
            return null;
        }

        if(this.numberReserved == 0)
        {
            Zombie tmp = new Zombie();
            this.reserveList.Add(tmp);
            this.numberReserved++;
        }

        Zombie zomb = this.reserveList[0];
        this.reserveList.RemoveAt(0);
        this.numberReserved--;

        this.activeList.Insert(0, zomb);
        this.numberActive++;

        zomb.Clean();

        return zomb;

    }

    public void ReturnZombie(Zombie zomb)
    {
        if(this.activeList.Remove(zomb))
        {
            this.numberActive--;

            this.reserveList.Insert(0, zomb);
            this.numberReserved++;
        }
    }

}