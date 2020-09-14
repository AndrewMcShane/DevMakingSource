# Chain of Responsibility pattern in Python

import enum

# An enum we'll attach to every game object to specify type:
# Requires Python 3.4 +
class ItemType(enum.Enum):
    Sword = 0,
    Armor = 1,
    Potion = 2,
    Ring = 3,
    QuestItem = 4,

    Undefined = 5

# For brevity, we'll just use a base class.
# In a real-world scenario, you'd want to subclass for more abilities.
class EquipmentItem(object):
    def __init__(self, name, itemType):
        self.name = name
        self.type = itemType


# A sortage container class:
class ItemChest(object):
    def __init__(self, name):
        self.chestName = name
        self.items = []
    
    def putAway(self, item):
        self.items.append(item)

    def printItems(self):
        if self.items.count > 0:
            print("Items in " + str(self.chestName) + ": ")
            for item in self.items:
                print("\t" + str(item.name))
        else:
            print(str(self.chestName) + " is empty!")
    
class ChestSorter(object):
    def __init__(self, chest, sortType):
        self.chest = chest
        self.sortType = sortType
        self.next = None
    
    def setNext(self, sorter):
        self.next = sorter
    
    def handle(self, item):
        if item.type == self.sortType:
            self.chest.putAway(item)
        elif self.next is not None:
            self.next.handle(item)

    def printChain(self):
        self.chest.printItems()
        if self.next is not None:
            self.next.printChain()


# The Null sorter gracefully handles a scenario where no item has a fit:
class NullSorter(ChestSorter):
    def __init__(self, chest):
        super(NullSorter, self).__init__(chest, ItemType.Undefined)
    
    def handle(self, item):
        self.chest.putAway(item)


# Demo:
# Create some chests:
swordChest = ItemChest("Sword Chest")
armorChest = ItemChest("Armor Chest")
potionChest = ItemChest("Potions")
otherItems = ItemChest("Misc.")

# Create the chain of responsibility:
swords = ChestSorter(swordChest, ItemType.Sword)
armor = ChestSorter(armorChest, ItemType.Armor)
potions = ChestSorter(potionChest, ItemType.Potion)
# Null sorter for item's that don't have an explicit chest:
other = NullSorter(otherItems)

# Link the chains:
swords.setNext(armor)
armor.setNext(potions)
potions.setNext(other)

# Pointer to the head of the list:
    # Implementation note: You can create another class to maintain all the sorting items!
sortingMachine = swords

# Insert a few items into the sorting machine:
sortingMachine.handle(EquipmentItem("Mighty Sword", ItemType.Sword))
sortingMachine.handle(EquipmentItem("Potion of life", ItemType.Potion))
sortingMachine.handle(EquipmentItem("Blade of infinity", ItemType.Sword))
sortingMachine.handle(EquipmentItem("Hockey Pads", ItemType.Armor))
sortingMachine.handle(EquipmentItem("Scroll of a thousand truths", ItemType.QuestItem))
sortingMachine.handle(EquipmentItem("Isildur's Bane", ItemType.Ring))

# Display all the chests' contents:
sortingMachine.printChain()

'''
    Output:
        Items in Sword Chest: 
            Mighty Sword
            Blade of infinity
        Items in Armor Chest: 
            Hockey Pads
        Items in Potions: 
            Potion of life
        Items in Misc.: 
            Scroll of a thousand truths
            Isildur's Bane
'''