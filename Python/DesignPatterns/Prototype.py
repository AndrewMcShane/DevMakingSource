# Prototype Pattern in Python

# Desktop computer class:
class Desktop(object):
    def __init__(self):
        self.cpu = "C1"
        self.gpu = "Inegrated Graphics"
        self.gigsOfRam = 2

    def clone(self):
        copy = Desktop()
        copy.cpu = self.cpu
        copy.gpu = self.gpu
        copy.gigsOfRam = self.gigsOfRam

        return copy


# The Desktop Catalogue:
class DesktopCatalogue(object):
    def __init__(self):
        self.catalogue = {}
    
    def add(self, key, prototype):
        self.catalogue[key] = prototype.clone()
    
    def getComputer(self, key):
        if key in self.catalogue:
            return self.catalogue[key].clone()
        else:
            raise ValueError(key)
    
# Demo of the Prototype Pattern:
catalogue = DesktopCatalogue()

# Create a basic setup:
basicWorkstation = Desktop()
basicWorkstation.cpu = "j5"
basicWorkstation.gpu = "cornea Graphics 9001"
basicWorkstation.gigsOfRam = 4

catalogue.add("Everyday Computing", basicWorkstation)

# Now a more advanced setup:
gamingSetup = Desktop()
gamingSetup.cpu = "X1 Hyper Thread"
gamingSetup.gpu = "Hive 7series"
gamingSetup.gigsOfRam = 1024

catalogue.add("Gaming", gamingSetup)

# Using the Prototypes:
myBasicComputer = catalogue.getComputer("Everyday Computing")
# Add some more ram to get more use out of it:
myBasicComputer.gigsOfRam = 8

# Let's make a big super computer next:
mySuperComputer = catalogue.getComputer("Gaming")

mySuperComputer.gigsOfRam = 4096
mySuperComputer.cpu = "Octa-Streamed X1 Giga Thread"
mySuperComputer.gpu = "Quad Hive 9series"
