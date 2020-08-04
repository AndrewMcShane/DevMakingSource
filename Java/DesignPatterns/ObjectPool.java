// Object Pool Demo in Java

// Helper Vector3D Class:
public class Vector3D
{
    public float x;
    public float y;
    public float z;

    public void Clean()
    {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
    }

    public void Set(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
}
// Base GameObject Class:
public class GameObject
{
    public Vector3D position;
    public Vector3D rotation;
    public Vector3D scale;

    public GameObject()
    {
        position = new Vector3D();
        rotation = new Vector3D();
        scale = new Vector3D();
    }

    public void Clean()
    {
        position.Clean();
        rotation.Clean();
        scale.Clean();
    }
}

// The GameObject Pool:
import java.util.LinkedList;

public class GameObjectPool
{
    // Active and reserve list for the pool:
    LinkedList<GameObject> activeList;
    LinkedList<GameObject> reserveList;

    // Tracking data:
    int numberActive;
    int numberReserved;

    public GameObjectPool(int reserve = 5)
    {
        this.activeList = new LinkedList<GameObject>();
        this.reserveList = new LinkedList<GameObject>();

        this.numberActive = 0;
        this.numberReserved = 0;

        InitializeReserve(reserve);
    }

    // Helper: Initializes the reserve with X number of elements:
    private void InitializeReserve(int reserve)
    {
        for(int i = 0; i < reserve; i++)
        {
            GameObject gameObject = new GameObject();
            this.reserveList.addFirst(gameObject);
            this.numberReserved++;
        }
    }

    // Get a reserved GameObject:
    public GameObject GetGameObject()
    {
        if(this.numberReserved == 0)
        {
            // We can grow, or deny the request:
            // We'll grow:
            GameObject tmp = new GameObject();
            this.reserveList.addFirst(tmp);
            this.numberReserved++;
        }

        GameObject gameObject = this.reserveList.pop();
        this.numberReserved--;

        this.activeList.addFirst(gameObject);
        this.numberActive++;

        // Clean off the GameObject!
        gameObject.Clean();

        return gameObject;
    }

    // Recycle a GameObject that is no longer needed:
    public void ReturnGameObject(GameObject gameObject)
    {
        if(this.activeList.remove(gameObject))
        {
            this.numberActive--;

            this.reserveList.addFirst(gameObject);
            this.numberReserved++;
        }
    }
}