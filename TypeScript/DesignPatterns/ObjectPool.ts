// The Vector3D and GameObject classes that make up the object pool entities:
class Vector3D
{
    public x: number;
    public y: number;
    public z: number;

    constructor()
    {
        this.clear();
    }

    public clear()
    {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }
}

class GameObject
{
    public position: Vector3D;
    public rotation: Vector3D;
    public scale: Vector3D;

    constructor()
    {
        this.position = new Vector3D();
        this.rotation = new Vector3D();
        this.scale = new Vector3D();
    }

    public clear()
    {
        this.position.clear();
        this.rotation.clear();
        this.scale.clear();
    }
}

// The object pool:
// Note:
/*
    Try implementing your own datastructure
    for the reserve and active lists!
    This way, you can guarantee the runtime complexity
    and have full control over your pool's efficiency.
*/
class GameObjectPool
{
    private activeList: Array<GameObject>;
    private reserveList: Array<GameObject>;

    private numberActive: number;
    private numberReserved: number;

    constructor(reserve: number = 5)
    {
        this.activeList = new Array<GameObject>();
        this.reserveList = new Array<GameObject>();

        this.numberActive = 0;
        this.numberReserved = 0;

        this.initializeReserve(reserve);
    }

    private initializeReserve(reserve: number)
    {
        for(let i = 0; i < reserve; i++)
        {
            const gameObject = new GameObject();
            this.reserveList.push(gameObject);
        }
    }

    public getGameObject(): GameObject
    {
        if(this.numberReserved == 0)
        {
            this.reserveList.push(new GameObject());
            this.numberReserved++;
        }

        const gameObject = this.reserveList.pop();
        this.numberReserved--;

        this.activeList.push(gameObject);
        this.numberActive++;

        gameObject.clear();

        return gameObject;
    }

    public returnGameObject(gameObject: GameObject)
    {
        // Get the index of the gameObject in the active list:
        const index = this.activeList.indexOf(gameObject);
        if(index >= 0)
        {
            // Splice the list around the element to remove.
            // Splice can be an expensive operation, which is why
            // I would use a custom collection in a real scenario:
            this.activeList.splice(index, 1);
            this.numberActive--;

            // Add it to the reserve:
            this.reserveList.push(gameObject);
            this.numberReserved++;
        }
    }
}