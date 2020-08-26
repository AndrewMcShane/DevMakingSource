// Composite pattern in C#
using System;
using System.Collections.Generic;

public abstract class TodoList
{
    public abstract void Read(int indent = 0);

    protected virtual string Tab(int indent)
    {
        return new String(' ', indent * 2);
    }
}

class ListItem: TodoList
{
    string taskDescription;

    public ListItem(string description)
    {
        this.taskDescription = description;
    }

    public override void Read(int indent = 0)
    {
        Console.WriteLine(this.Tab(indent) + this.taskDescription);
    }
}

class TaskList: TodoList
{
    string taskListName;
    List<TodoList> todoList;

    public TaskList(string name)
    {
        this.taskListName = name;
        this.todoList = new List<TodoList>();
    }

    public override void Read(int indent = 0)
    {
        Console.WriteLine(this.Tab(indent) + this.taskListName);
        foreach (TodoList item in this.todoList)
        {
            item.Read(indent + 1);
        }
    }

    public void AddToList(TodoList task)
    {
        todoList.Add(task);
    }

    public  void RemoveFromList(TodoList task)
    {
        todoList.Remove(task);
    }
}

public class Solution
{
    public static void Main(string[] args)
    {
        // Top level todo list:
        TaskList todo = new TaskList("To-Do for today:");

        // List for groceries:
        TaskList groceries = new TaskList("Go to the store:");
        groceries.AddToList(new ListItem("Apples"));
        groceries.AddToList(new ListItem("Bread"));
        groceries.AddToList(new ListItem("Yogurt"));
        groceries.AddToList(new ListItem("Popcorn"));

        // Add list to the top level:
        todo.AddToList(groceries);

        // Homework list:
        TaskList homework = new TaskList("Do my homework:");
        homework.AddToList(new ListItem("Math paper"));
        homework.AddToList(new ListItem("Physics quiz"));
        // CS sub-list:
        TaskList csHomework = new TaskList("CSC:");
        csHomework.AddToList(new ListItem("Lab"));
        csHomework.AddToList(new ListItem("Assignment"));
        csHomework.AddToList(new ListItem("Make flashcards"));
        // Add CS list to homework list:
        homework.AddToList(csHomework);
        // Add homework list to top level:
        todo.AddToList(homework);

        // Read off the list:
        todo.Read();

        Console.WriteLine("Done");

        /*
            Output:
            
            To-Do for today:
                Go to the store:
                    Apples
                    Bread
                    Yogurt
                    Popcorn
                Do my homework:
                    Math paper
                    Physics quiz
                    CSC:
                    Lab
                    Assignment
                    Make flashcards
            Done
        */
    }
}