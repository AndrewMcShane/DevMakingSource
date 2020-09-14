//Chain-of-responsibility pattern in Java
import java.util.*;

// An enum that we'll attach to every game object to specify type.
public enum ItemType 
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
public class EquipmentItem 
{
 public String name;
 public ItemType type;

 public EquipmentItem(String name, ItemType type)
 {
     this.name = name;
     this.type = type;
 }
}

// A storage container class:
public class ItemChest
{
 public LinkedList<EquipmentItem> items;
 protected String chestName;

 public ItemChest(String name)
 {
     this.chestName = name;
     this.items = new LinkedList<EquipmentItem>();
 }

 public void PutAway(EquipmentItem item)
 {
     this.items.addFirst(item);
 }

 public void PrintItems()
 {
     if(this.items.size() > 0)
     {
         System.out.println("Items in " + this.chestName + ": ");
         for(EquipmentItem item : this.items)
         {
             System.out.println("\t" + item.name);
         }
     }
     else
     {
         System.out.println(this.chestName + " is empty!");
     }
 }

 // Feel free to add the rest of the methods in your implementations!

}

// Chain of responsibility:
public class ChestSorter
{
 protected ItemType sortType;
 protected ChestSorter next;
 protected ItemChest chest;
 
 public ChestSorter(ItemChest chest, ItemType sortType)
 {
     this.chest = chest;
     this.sortType = sortType;
 }

 public void SetNext(ChestSorter sorter)
 {
     this.next = sorter;
 }

 // Handle an item being placed into the sorter:
 public void Handle(EquipmentItem item)
 {
     if(item.type == this.sortType)
     {
         this.chest.PutAway(item);
     }
     else if(this.next != null)
     {
         this.next.Handle(item);
     }
 }

 // Helper method for displaying the contents of every chest in the chain:
 public void PrintChain()
 {
     this.chest.PrintItems();
     if(this.next != null)
     {
         this.next.PrintChain();
     }
 }
}

// The Null sorter gracefully handles a scenario where no item has a fit:
public class NullSorter extends ChestSorter
{
 public NullSorter(ItemChest chest)
 {
     super(chest, ItemType.Undefined);
 }
 
 // This will put away ANY item that is placed into it:
 public void Handle(EquipmentItem item)
 {
     this.chest.PutAway(item);
 }
}


// Demo of the Chain of responsibility:
public class Solution
{
 public static void main(String[] args)
 {
     // Create some chests:
     ItemChest swordChest = new ItemChest("Sword Chest");
     ItemChest armorChest = new ItemChest("Armor Chest");
     ItemChest potionChest = new ItemChest("Potions");
     ItemChest otherItems = new ItemChest("Misc.");

     // Create the chain of responsibility:
     ChestSorter swords = new ChestSorter(swordChest, ItemType.Sword);
     ChestSorter armor = new ChestSorter(armorChest, ItemType.Armor);
     ChestSorter potions = new ChestSorter(potionChest, ItemType.Potion);
     // Null sorter for item's that don't have an explicit chest:
     ChestSorter other = new NullSorter(otherItems);

     // Link the chains:
     swords.SetNext(armor);
     armor.SetNext(potions);
     potions.SetNext(other);

     // Pointer to the head of the list:
         // Implementation note: You can create another class to maintain all the sorting items!
     ChestSorter sortingMachine = swords;

     // Insert a few items into the sorting machine:
     sortingMachine.Handle(new EquipmentItem("Mighty Sword", ItemType.Sword));
     sortingMachine.Handle(new EquipmentItem("Potion of life", ItemType.Potion));
     sortingMachine.Handle(new EquipmentItem("Blade of infinity", ItemType.Sword));
     sortingMachine.Handle(new EquipmentItem("Hockey Pads", ItemType.Armor));
     sortingMachine.Handle(new EquipmentItem("Scroll of a thousand truths", ItemType.QuestItem));
     sortingMachine.Handle(new EquipmentItem("Isildur's Bane", ItemType.Ring));

     // Display all the chests' contents:
     sortingMachine.PrintChain();

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