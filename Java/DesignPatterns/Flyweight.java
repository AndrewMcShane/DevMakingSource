/// Flyweight pattern in Java

import java.util.*;

// HTTP request result:
public class WebRequestResult
{
    // The time (in minutes, converted to miliseconds) to cahce a request:
    // We're going to cache for 10 minutes:
    public static final long CACHE_TIME_MINUTES = 10 * 60 * 1000;


    public String data;
    private final long cacheUntilMili

    public WebRequestResult(String data)
    {
        this.data = data;
        this.cacheUntilMili = System.currentTimeMillis() + CACHE_TIME_MINUTES;
    }

    // Is the result still considered "fresh"?
    // We want it to "expire" after 10 minutes:
    public boolean IsExpired()
    {
        return System.currentTimeMillis() > this.cacheUntilMili;
    }
}

// The Flyweight class:
public class WebRequestCache
{
    // The cache for the request system:
    static HashMap<String, WebRequestResult> cache = new HashMap<String, WebRequestResult>();

    // We're going to make this simple and be concurrent, even though the world of 
    // web communication would be asynchronous!

    public static WebRequestResult GetRequest(String url)
    {
        WebRequestResult result = null;
        if(cache.containsKey(url))
        {
            result = cache.get(url);
        }

        // If the result is not cached, or is old, replace it:
        if(result == null || result.IsExpired())
        {
            result = new WebRequestResult("Some cool data from " + url);
            // Replace in the cache:
            cache.put(url, result);
        }
        
        // Give back the result:
        return result;
    }
}


public class Solution
{
    public static void main(String[] args)
    {
        WebRequestResult result = null;

        result = WebRequestCache.GetRequest("myapiservice/link/endpoint");

        System.out.println(result.data); // Some cool data from myapiservice/link/endpoint

        result = WebRequestCache.GetRequest("myotherservice/endpoint");

        System.out.println(result.data); // Some cool data from myotherservice/endpoint

        System.out.println(result.IsExpired()); // false

        // Sleep for the cache time (WARNING: 10 minutes!!)
        Thread.sleep(result.CACHE_TIME_MINUTES);

        System.out.println(result.IsExpired()); // true
    }
}
