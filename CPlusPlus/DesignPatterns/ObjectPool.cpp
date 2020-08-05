// Object Pool in C++

// We'll use stl List to keep it brief,
// although I encourage you to create your own
// data structure to get more speed, and have full control!
#include <algorithm>
#include <iostream>
#include <list>

class Vector3D
{
    public:
        Vector3D()
            : x(0.0f), y(0.0f), z(0.0f)
        {};


        void Set(float x, float y, float z)
        {
            this->x = x;
            this->y = y;
            this->z = z;
        }

        void Clean()
        {
            this->x = 0.0f;
            this->y = 0.0f;
            this->z = 0.0f; 
        }

    public:
        float x;
        float y;
        float z;
};

class GoldCoin
{
    public:
        GoldCoin()
            :position()
            {};

        void Clean()
        {
            this->position.Clean();
        }

        
    public:
        Vector3D position;
    
};


class CoinPool
{
    public:
        CoinPool() = delete;

        CoinPool(int reserve, int spawnCap = 1000);
        ~CoinPool();

        bool CanSpawn();
        GoldCoin* GetCoin();
        void ReturnCoin(GoldCoin& coin);

    protected:
        void InitializeReserve(int reserve)
        {
            for(int i = 0; i < reserve; i++)
            {
                GoldCoin* pCoin = new GoldCoin();
                this->reserveList.push_front(pCoin);
                this->reserveSize++;
            }
        };

        // Data:
        std::list<GoldCoin*> activeList;
        std::list<GoldCoin*> reserveList;

        int reserveSize;
        int activeSize;
        int spawnCap;
};

// Implementation:

CoinPool::CoinPool(int reserve, int spawnCap)
{
    this->reserveSize = 0;
    this->activeSize = 0;
    this->spawnCap = spawnCap;

    this->InitializeReserve(reserve);

}

bool CoinPool::CanSpawn()
{
    return this->activeSize < this->spawnCap;
}

GoldCoin* CoinPool::GetCoin()
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
    if(this->activeSize >= this->spawnCap)
    {
        return nullptr;
    }

    if(this->reserveSize == 0)
    {
        GoldCoin* pTmp = new GoldCoin();
        this->reserveList.push_front(pTmp);
        this->reserveSize++;
    }

    // Get the first coin from the reserve:
    GoldCoin* pCoin = this->reserveList.front();
    this->reserveList.pop_front();
    this->reserveSize--;

    // Add it to the active list:
    this->activeList.push_front(pCoin);
    this->activeSize++;

    pCoin->Clean();

    return pCoin;
}

void CoinPool::ReturnCoin(GoldCoin& coin)
{
    this->activeList.remove(&coin);
    this->activeSize--;

    this->reserveList.push_front(&coin);
    this->reserveSize++;
}

// Destructor: Clean up our mess!
CoinPool::~CoinPool()
{
    // Iterate both lists, delete all elements.
    std::list<GoldCoin*>::const_iterator it = this->activeList.begin();
    for(; it != this->activeList.end(); ++it)
    {
        delete *it;
    }
    this->activeList.clear();


    it = this->reserveList.begin();
    for(; it != this->reserveList.end(); ++it)
    {
        delete *it;
    }
    this->reserveList.clear();
}