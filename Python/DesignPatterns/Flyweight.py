# Flyweight pattern in Python

import time

# HTTP Request result:
class WebRequestResult(object):
    # The time (in munutes, converted to seconds) to cache a request.
    # This is a 1-minute cahce.
    CACHE_TIME_MINUTES = 1 * 60 

    def __init__(self, data):
        # The Flyweight data:
        self.data = data
        self.cacheUntilTime = time.time() + WebRequestResult.CACHE_TIME_MINUTES

    def isExpired(self):
        return time.time() > self.cacheUntilTime
    

# The Flyweight class:
class WebRequestCache(object):
    def __init__(self):
        self.cache = {}

    # For simplicity, we'll be concurrent with our requests:
    def getRequest(self, url):
        result = None

        if url in self.cache:
            result = self.cache[url]

        if result is None or result.isExpired():
            result = WebRequestResult("Some cool data from " + str(url))

            self.cache[url] = result
        
        return result


# Demo of the Flyweight Web Request

webCache = WebRequestCache()

request = webCache.getRequest("mycoolapi/endpoint")

print(request.data) # output: Some cool data from mycoolapi/endpoint

print(request.isExpired()) # output: False

# Will wait for 1 minute!
time.sleep(WebRequestResult.CACHE_TIME_MINUTES)

print(request.isExpired()) # output: True

