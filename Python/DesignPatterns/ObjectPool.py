# Object pool demo in Python

# The base vector class:
class Vector3D(object):
    def __init__(self):
        self.x = 0.0
        self.y = 0.0
        self.z = 0.0

    def Set(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z
    
    def Clean(self):
        self.x = 0.0
        self.y = 0.0
        self.z = 0.0


# The Laser Beam class:
class LaserBeam(object):
    def __init__(self):
        self.position = Vector3D()
    
    def Clean(self):
        self.position.Clean()


# The Laser Beam Object Pool:

class LaserBeamPool(object):
    def __init__(self, reserve = 5, maxCapacity = 100):
        self.activeList = []
        self.reserveList = []

        self.maxCapacity = maxCapacity
        self.numberActive = 0
        self.numberReserved = 0
        self._InitializeReserve(reserve)
        
    def _InitializeReserve(self, reserve):
        for i in range(reserve + 1):
            tmp = LaserBeam()
            self.reserveList.append(tmp)
            self.numberReserved += 1
    
    def CanSpawn(self):
        return self.numberActive < self.maxCapacity

    def GetLaserBeam(self):
        '''
            NOTICE:
            In your code, make sure to check the spawn limit.
            you can either:
                1. Throw an exception
                2. Return None
                3. Spawn a new zombie anyways.

                We'll return None.
        '''
        if self.numberActive >= self.maxCapacity:
            return None
        
        if self.numberReserved == 0:
            tmp = LaserBeam()
            self.reserveList.append(tmp)
            self.numberReserved += 1
        
        laser = self.reserveList.pop()
        self.numberReserved -= 1

        self.activeList.append(laster)
        self.numberActive += 1

        laser.Clean()

        return laser

    def ReturnLaserBeam(self, laser):
        if laser in self.activeList:
            self.activeList.remove(laser)
            self.numberActive -= 1
            
            self.reserveList.append(laser)
            self.numberReserved += 1