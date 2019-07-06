//TODO
using System;
using System.Collections;

public class SimpleHashing
{
    private static readonly int BUCKETS_DEFAULT = 16;

    private HashNode[] buckets;
    private int numBuckets;
    public int Size { get; private set; }

    public class HashNode
    {
        public string key;
        public string value;
        public HashNode next;

        public HashNode(string key, string value)
        {
            this.key = key;
            this.value = value;
            this.next = null;
        }
        public HashNode(string key, string value, HashNode next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public SimpleHashing()
    {
        this.numBuckets = BUCKETS_DEFAULT;
        buckets = new HashNode[numBuckets];
    }

    public  SimpleHashing(int numBuckets)
    {
        this.numBuckets = numBuckets;
        buckets = new HashNode[numBuckets];
    }

    public bool IsEmpty()
    {
        return Size <= 0;
    }

    private int GetHash(string key)
    {
        return (key.GetHashCode() & 0x7fffffff) % numBuckets;
    }

    public void Put(string key, string value)
    {
        int bucket = GetHash(key);
        HashNode newNode = new HashNode(key, value);
        if(buckets[bucket] == null)
        {
            buckets[bucket] = newNode;
            Size++;
        }
        else
        {
            HashNode tmp = buckets[bucket];
            while(tmp.next != null)
            {
                if (tmp.key.Equals(key))
                {
                    tmp.value = value;
                    return;
                }
                tmp = tmp.next;
            }
            tmp.next = newNode;
            Size++;
        }
        return;
    }

    public string Get(string key)
    {
        int bucket = GetHash(key);
        HashNode tmp = buckets[bucket];
        while(tmp != null)
        {
            if (tmp.key.Equals(key))
            {
                return tmp.value;
            }
        }
        return null;
    }

    public void Remove(string key)
    {
        int bucket = GetHash(key);
        if(buckets[bucket] == null)
        {
            return;
        }
        HashNode tmp = buckets[bucket];
        if (tmp.key.Equals(key))
        {
            buckets[bucket] = buckets[bucket].next;
            Size--;
            return;
        }
        while(tmp.next != null)
        {
            if (tmp.next.key.Equals(key))
            {
                tmp.next = tmp.next.next;
                Size--;
                return;
            }
            tmp = tmp.next;
        }
    }

    public void Clear()
    {
        buckets = new HashNode[numBuckets];
    }

    override
        public string ToString()
    {
        string res = "{";
        int count = 0;
        for(int i = 0; i < numBuckets; i++)
        {
            if(buckets[i] != null)
            {
                HashNode tmp = buckets[i];
                while(tmp != null)
                {
                    res += tmp.key + ": " + tmp.value;
                    count++;
                    if(count < Size)
                    {
                        res += ", ";
                    }
                    tmp = tmp.next;
                }
            }
        }
        res += "}";
        return res;
    }
}