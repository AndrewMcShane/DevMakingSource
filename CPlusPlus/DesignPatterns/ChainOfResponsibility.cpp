// Chain of responsibility in C++

#include <iostream>
#include <string>
#include <vector>

// Enum class for each item type in the game:
enum ItemType
{
    Sword,
    Armor,
    Potion,
    Ring,
    QuestItem,

    Undefined
};

// For brevity, we'll just use a base class.
// In a real-world scenario, you'd want to subclass for more abilities.
class EquipmentItem
{
    public:
        EquipmentItem(std::string name, ItemType type)
        : name(name), type(type)
        {};

        std::string name;
        ItemType type;
};

// A storage container class:
class ItemChest
{
    public:
        ItemChest(std::string name)
        : chestName(name)
        {};

        // We'll implement just the needed methods for this demo:
        void PutAway(EquipmentItem item);
        void PrintItems();

        std::string chestName;
        std::vector<EquipmentItem> items;
};

// Implementation note: You might want to implement this as EquipmentItem*
// non-pointer types here can get messy when retrieving.
void ItemChest::PutAway(EquipmentItem item)
{
    this->items.push_back(item);
}

void ItemChest::PrintItems()
{
    if(this->items.size() > 0)
    {
        std::cout << "Items in " << this->chestName << ": " << std::endl;

        std::vector<EquipmentItem>::const_iterator it = this->items.begin();
        for(; it != this->items.end(); ++it)
        {
            std::cout << "\t" << it->name << std::endl;
        }
    }
    else
    {
        std::cout << this->chestName << " is empty!" << std::endl;
    }
}


// Chain of Responsibility:
class ChestSorter
{
    public:
        ChestSorter(ItemChest* chest, ItemType sortType)
        : chest(chest), sortType(sortType), next(nullptr)
        {};

        void SetNext(ChestSorter* sorter) { this->next = sorter; };
        virtual void Handle(EquipmentItem item);

        void PrintChain();

    protected:
        ItemType sortType;
        ChestSorter* next;
        ItemChest* chest;
};

void ChestSorter::Handle(EquipmentItem item)
{
    if(item.type == this->sortType)
    {
        this->chest->PutAway(item);
    }
    else if(this->next != nullptr)
    {
        this->next->Handle(item);
    }
}

void ChestSorter::PrintChain()
{
    this->chest->PrintItems();
    if(this->next != nullptr)
    {
        this->next->PrintChain();
    }
}

// The Null sorter gracefully handles a scenario where no item has a fit:
class NullSorter: public ChestSorter
{
    public:
        NullSorter(ItemChest* chest)
        : ChestSorter(chest, ItemType::Undefined)
        {};

        // This will put away ANY item that is placed into it:
        void Handle(EquipmentItem item) override 
        { 
            this->chest->PutAway(item);
        };
};

// Demo:
int main()
{
    ItemChest swordChest = ItemChest("Sword Chest");
    ItemChest armorChest = ItemChest("Armor Chest");
    ItemChest potionChest = ItemChest("Potions");
    ItemChest otherItems = ItemChest("Misc.");

    // Create the chain of responsibility:
    ChestSorter swords = ChestSorter(&swordChest, ItemType::Sword);
    ChestSorter armor = ChestSorter(&armorChest, ItemType::Armor);
    ChestSorter potions = ChestSorter(&potionChest, ItemType::Potion);
    // Null sorter for item's that don't have an explicit chest:
    NullSorter other = NullSorter(&otherItems);

    // Link the chains:
    swords.SetNext(&armor);
    armor.SetNext(&potions);
    potions.SetNext(&other);

    // Pointer to the head of the list:
            // Implementation note: You can create another class to maintain all the sorting items!
    ChestSorter* sortingMachine = &swords;

    // Insert a few items into the sorting machine:
    sortingMachine->Handle(EquipmentItem("Mighty Sword", ItemType::Sword));
    sortingMachine->Handle(EquipmentItem("Potion of life", ItemType::Potion));
    sortingMachine->Handle(EquipmentItem("Blade of infinity", ItemType::Sword));
    sortingMachine->Handle(EquipmentItem("Hockey Pads", ItemType::Armor));
    sortingMachine->Handle(EquipmentItem("Scroll of a thousand truths", ItemType::QuestItem));
    sortingMachine->Handle(EquipmentItem("Isildur's Bane", ItemType::Ring));

    // Display all the chests' contents:
    sortingMachine->PrintChain();

    /*
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
    */
}