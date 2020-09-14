module ChainOfResponsibility
{
    // An enum that we'll attach to every game object to specify type.
    export enum ItemType
    {
        Sword,
        Armor,
        Potion,
        Ring,
        QuestItem,
        
        Undefined
    }

    // For brevity, we'll just use a base class.
    // In a real-world scenario, you'd want to subclass for more abilities.
    export class EquipmentItem
    {
        public name: string;
        public type: ItemType;

        constructor(name: string, type: ItemType)
        {
            this.name = name;
            this.type = type;
        }
    }

    export class ItemChest
    {
        public items: Array<EquipmentItem>;
        protected chestName: string;

        constructor(name: string)
        {
            this.chestName = name;
            this.items = new Array<EquipmentItem>();
        }

        public putAway(item: EquipmentItem)
        {
            this.items.push(item);
        }

        public printItems()
        {
            if(this.items.length > 0)
            {
                console.log("Items in " + this.chestName + ": ");
                for(let item of this.items)
                {
                    console.log("\t" + item.name);
                }
            }
            else
            {
                console.log(this.chestName + " is empty!");
            }
        }

        // We've only implemented the methods we need for the demo, feel free to add more!
    }

    // Chain of responsibility:
    export class ChestSorter
    {
        protected sortType: ItemType;
        protected next: ChestSorter;
        protected chest: ItemChest;

        constructor(chest: ItemChest, sortType: ItemType)
        {
            this.chest = chest;
            this.sortType = sortType;
        }

        public setNext(sorter: ChestSorter)
        {
            this.next = sorter;
        }

        // Handle an item being placed into the sorter:
        public handle(item: EquipmentItem)
        {
            if(item.type === this.sortType)
            {
                this.chest.putAway(item);
            }
            else if(this.next != null)
            {
                this.next.handle(item);
            }
        }

        // Helper for displaying chest contents:
        public printChain()
        {
            this.chest.printItems();
            if(this.next != null)
            {
                this.next.printChain();
            }
        }
    }

    // The Null sorter gracefully handles a scenario where no item has a fit:
    export class NullSorter extends ChestSorter
    {
        constructor(chest: ItemChest)
        {
            super(chest, ItemType.Undefined);
        }

        // This will put away ANY item that is placed into it:
        public handle(item: EquipmentItem)
        {
            this.chest.putAway(item);
        }
    }

    // Demo of the chain of responsibility:
    export class Solution
    {
        public static execute(): void
        {
            // Create some chests:
            const swordChest = new ItemChest("Sword Chest");
            const armorChest = new ItemChest("Armor Chest");
            const potionChest = new ItemChest("Potions");
            const otherItems = new ItemChest("Misc.");

            // Create the chain of responsibility:
            const swords = new ChestSorter(swordChest, ItemType.Sword);
            const armor = new ChestSorter(armorChest, ItemType.Armor);
            const potions = new ChestSorter(potionChest, ItemType.Potion);
            // Null sorter for item's that don't have an explicit chest:
            const other = new NullSorter(otherItems);

            // Link the chains:
            swords.setNext(armor);
            armor.setNext(potions);
            potions.setNext(other);

            // Pointer to the head of the list:
                // Implementation note: You can create another class to maintain all the sorting items!
            const sortingMachine = swords;

            // Insert a few items into the sorting machine:
            sortingMachine.handle(new EquipmentItem("Mighty Sword", ItemType.Sword));
            sortingMachine.handle(new EquipmentItem("Potion of life", ItemType.Potion));
            sortingMachine.handle(new EquipmentItem("Blade of infinity", ItemType.Sword));
            sortingMachine.handle(new EquipmentItem("Hockey Pads", ItemType.Armor));
            sortingMachine.handle(new EquipmentItem("Scroll of a thousand truths", ItemType.QuestItem));
            sortingMachine.handle(new EquipmentItem("Isildur's Bane", ItemType.Ring));

            // Display all the chests' contents:
            sortingMachine.printChain();

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
    }
}

// Automatically call to the demo:
ChainOfResponsibility.Solution.execute();