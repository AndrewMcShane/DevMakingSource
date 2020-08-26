# Composite Pattern in Python

def tab(indent = 0):
    return " " * (indent * 4)

class ListItem(object):
    def __init__(self, description):
        self.taskDescription = description
    
    def read(self, indent = 0):
        print(tab(indent) + str(self.taskDescription))

class TaskList(object):
    def __init__(self, name):
        self.taskListName = name
        self.todoList = []
    
    def read(self, indent = 0):
        print(tab(indent) + str(self.taskListName))

        for i in self.todoList:
            i.read(indent + 1)
    
    def add(self, task):
        self.todoList.append(task)
    
    def remove(self, task):
        self.todoList.remove(task)


# Demo of Composite pattern:


todo = TaskList("To-Do for today:")

# List for groceries:
groceries = TaskList("Go to the store:")
groceries.add(ListItem("Apples"))
groceries.add(ListItem("Bread"))
groceries.add(ListItem("Yogurt"))
groceries.add(ListItem("Popcorn"))

# Add list to the top level:
todo.add(groceries)

# Homework list:
homework = TaskList("Do my homework:")
homework.add(ListItem("Math paper"))
homework.add(ListItem("Physics quiz"))
# CS sub-list:
csHomework = TaskList("CSC:")
csHomework.add(ListItem("Lab"))
csHomework.add(ListItem("Assignment"))
csHomework.add(ListItem("Make flashcards"))
# Add CS list to homework list:
homework.add(csHomework)
# Add homework list to top level:
todo.add(homework)

#Read off the list:
todo.read()


# Output:
'''
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
'''