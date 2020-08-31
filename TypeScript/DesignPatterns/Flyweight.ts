// Flyweight in TypeScript

// HTTP request result:
class WebRequestResult
{
    // The time in minutes, converted to milliseconds, to cache the request for
    // This will cache for 1 minute.
    public static readonly CACHE_TIME_MINUTES: number = 1 * 60 * 1000;

    // The data is our flyweight: relatively expensive to compute (wait for request response takes time and bandwidth!)
    public data: string;

    // What time is this data "fresh" until? (Milliseconds)
    private readonly cacheUntilTime: number;

    constructor(data: string)
    {
         this.data = data;
         this.cacheUntilTime = Date.now() + WebRequestResult.CACHE_TIME_MINUTES;
    }

    // Is the result too old / needs to be refreshed?
    public isExpired(): boolean
    {
        return Date.now() > this.cacheUntilTime;
    }
}


// Static flyweight factory:
class WebRequestCache
{
    // Uses ES6 Map for help:
    private static cache: Map<string, WebRequestResult> = new Map<string, WebRequestResult>();

    /*
        We're going to bend the rules a little and make this concurrent
        as opposed to asynchronous which is what a real-world scenario would be.
    */
    public static getRequest(url: string): WebRequestResult
    {
        let result: WebRequestResult = null;
        
        if(this.cache.has(url))
        {
            result = this.cache.get(url);
        }

        // If the result is not cached or is expired, replace:
        if(result === null || result.isExpired())
        {
            result = new WebRequestResult(`Some cool data from ${url}`);
            // Replace the cached request:
            this.cache.set(url, result);
        }

        return result;
    }
}

// Demo class:
class FlyweightSolution
{
    public static execute()
    {
        // Create a request:

        let result = WebRequestCache.getRequest("mycoolapi/link/endpoint");

        console.log(result.data); // Some cool data from mycoolapi/link/endpoint

        console.log(result.isExpired()); // false

        // Wait until the result expires and check again:
        setTimeout(() =>
        {
            console.log(result.isExpired()) // true
        }, WebRequestResult.CACHE_TIME_MINUTES);
    }
}

// Run the demo:
FlyweightSolution.execute();