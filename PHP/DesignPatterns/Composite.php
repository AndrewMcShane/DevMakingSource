<?php

// Composite Pattern in PHP

abstract class TodoList
{
    public abstract function Read(int $indent = 0);

    protected function Tab(int $indent)
    {
        return str_repeat(" ", $indent * 4);
    }
}

class ListItem extends TodoList
{
    private $taskDescription;

    function __construct(string $description)
    {
        $this->taskDescription = $description;
    }

    public function Read(int $indent = 0)
    {
        echo $this->Tab($indent) . $this->taskDescription . "\n";
    }
}

class TaskList extends TodoList
{
    private $taskListName;
    private $todoList;

    function __construct(string $name)
    {
        $this->taskListName = $name;
        $this->todoList = array();
    }

    public function Read(int $indent = 0)
    {
        echo $this->Tab($indent) . $this->taskListName . "\n";
        foreach($this->todoList as $item)
        {
            $item->Read($indent + 1);
        }
    }

    public function Add(TodoList $task)
    {
        $this->todoList[] = $task;
    }

    public function Remove(TodoList $task)
    {
        if (($key = array_search($task, $this->todoList)) !== false) {
            unset($this->todoList[$key]);
        }
    }
}

// Demo of Composite Pattern in PHP

// Top level todo list:
$todo = new TaskList("To-Do for today:");

// List for groceries:
$groceries = new TaskList("Go to the store:");
$groceries->Add(new ListItem("Apples"));
$groceries->Add(new ListItem("Bread"));
$groceries->Add(new ListItem("Yogurt"));
$groceries->Add(new ListItem("Popcorn"));

// Add list to the top level:
$todo->Add($groceries);

// Homework list:
$homework = new TaskList("Do my homework:");
$homework->Add(new ListItem("Math paper"));
$homework->Add(new ListItem("Physics quiz"));
// CS sub-list:
$csHomework = new TaskList("CSC:");
$csHomework->Add(new ListItem("Lab"));
$csHomework->Add(new ListItem("Assignment"));
$csHomework->Add(new ListItem("Make flashcards"));
// Add CS list to homework list:
$homework->Add($csHomework);
// Add homework list to top level:
$todo->Add($homework);

// Read off the list:
$todo->Read();

// Output:
/*
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
*/