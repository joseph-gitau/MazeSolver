# MazeSolver
Simply A-Maze-ing! Stack and Queue
Project 2
__________________________________________________________________________
This assignment has many different pieces that all need to work together. Most pieces are small, but it may take some additional patience on your part. Just chug along and the end will show itself in due time!
Please start working early!

Note: You may choose to work with a partner on this assignment.
Introduction
In this assignment you will use the power of a stack and a queue to explore and find your way through a maze. 

After completing this project you should be able to:
●implement stack and queue ADT using Linked Lists
●use your stack and queue to solve a maze
●explore the differences between the two solution strategies 

We provide you with the following Java files that you do NOT need (and are not allowed) to modify:
●Square.java
●Maze.java
●MazeApp.java
●StackInterface.java
●QueueInterface.java

We also provide all the JUnit tests for testing your solutions:
●MiniListTest.java
●MyQueueTest.java
●MyStackTest.java
●MazeSolverTest.java

You will need to modify the following starter file and include it with your submission:
●MazeSolver.java

You will need to create and submit the following files:
●MiniList.java (reuse your implementation from Recitation 4)
●MyStack.java
●MyQueue.java
●MazeSolverStack.java
●MazeSolverQueue.java

All the starter code, including some sample mazes can be found in this archive. Download the archive, unzip it and later import the java files into your Eclipse project. Make sure to place all the provided java files into the ass2 package.
Part I. Mini-List
Take the file MiniList.java that you created during Recitation 4, and add it to the package ass2. We are going to base our implementation of the Stack and Queue on this MiniList.

Test your List
Before going forward with this, add a JUnit Test Case file to your project, as described in Recitation 5. For this select the MiniList.java in the project explorer, open it in the project editor, and select ‘New JUnit Test Case’ in the context menu. This will prepopulate all the information for your JUnit test. Press Finish, and also add JUnit library to your project build path if prompted. 

Now you have a file MiniListTest.java in the ass2 package. Go ahead and copy this code into it. When you run MiniListTest, you should see all the tests passed. If not, then you need to debug the methods that failed and fix the bugs. Do not continue before your MiniList works properly.
Part II. Implementing Stack and Queue ADT
We've been talking about stacks and queues in class, and now it is your time to put that theory to good use. Write two classes `MyStack<T>` and `MyQueue<T>` that implement the supplied interfaces `StackInterface` and `QueueInterface`, respectively.

Both `MyStack` and `MyQueue` can make good use of a simple Linked List data structure implemented in our MiniList class. Double-check that your `MiniList` implements three methods: `addFirst()`, `removeFirst()` and `addLast()`. These three methods should be implemented in the most efficient way.

Now add an instance variable of type MiniList inside both `MyStack` and `MyQueue`.

For example, the definition of MyStack class could look like:
public class MyStack<T> implements StackInterface<T> {
  private MiniList<T> list = new MiniList<T>();
}


MyStack
`MyStack` implements `StackInterface`. In Eclipse, you can add all the unimplemented method stubs at once by pressing on the red cross and selecting "Add unimplemented methods".

Implement the provided `StackInterface`. Use the private variable of type `MiniList` to implement all the required methods. Think which end of the list should be used for the most efficient pop/push operations.
MyQueue
`MyQueue` implements the provided `QueueADT` interface. Use the same `MiniList` as the underlying data structure. 

Test your Stack and Queue  
After implementing, you should run JUnit tests for `MyStack` and `MyQueue` that perform testing on your data structures. These are provided in  MyStackTest.java and MyQueueTest.java respectively. Add these JUnit Test Case classes to your project. 

Pass all the tests before moving to the next step.
Part III. Solving Mazes
3.1. Representing a Maze
Let's talk about how we can implement a basic maze. It has walls and pathways, as well as a
starting point and an ending point.  Furthermore, one wall is just like another, and any open space (not including start and finish) is also identical. So, we can think of a maze as a 2D array being made up of individual squares, each square either empty, a wall, the start, or the exit. 

We encoded several sample mazes in files located in the mazes folder. We represent each maze with a text file of the following format:

The first line of the file contains two integers. The first indicates the number of rows (R), the second, the number of columns (C). 

The rest of the file will be R rows with C integers in each row. 

The value of the integers can be one of:
●0 -- an empty space
●1 -- a wall
●2 -- the start 
●3 -- the exit 


Here is a content of file maze-2:
7 13
0 0 0 0 0 0 1 0 0 0 0 0 0
1 1 0 1 1 1 1 1 1 1 0 1 0
0 1 0 0 0 0 0 0 0 0 0 1 0
0 1 0 1 1 0 0 1 1 1 0 1 0
0 0 0 1 0 0 0 0 0 1 0 1 0
0 1 1 1 1 1 0 1 0 1 0 1 0
0 0 0 0 2 0 0 1 0 0 1 3 0

And here is a graphical representation of the maze encoded in this file: 


The green box represents the start, the red box the exit, and the black squares – the walls.

In terms of coordinates, we consider the upper left corner to be position (0, 0) and the lower right to be (R - 1, C - 1). The maze above starts at coordinate (6, 4) and exits at (6, 11)).
3.2. The Square Class
You are also given a code for class `Square` that represents a single square in the maze. Squares hold their coordinates and an `int` representing their type (empty: 0, wall: 1, start: 2 or exit: 3).  Below are descriptions of the `Square` methods you will need to use – for other methods, read the code.  

public Square(int type, int row, int col)
constructor to create a new Square object

public int getRow()
public int getColumn()
public int getType()
Getters for the various instance variables. 

public static final int SPACE = 0, WALL = 1, START = 2, EXIT = 3;
These are possible Square types. You can use them for finding out the type of a given square sq – for example: if (sq.getType() == Square.SPACE) 

public void mark()
public boolean isMarked()
Once you traversed the square you would use the mark() method to set its marked instance variable to true. After this operation the isMarked() for this square will return true.

public boolean isOnPath()
public void setOnPath()
Once you reached the exit, you would recreate the path by traversing the list in the opposite direction. Each of the nodes will be marked as belonging to the path using the two methods above.

public void reset()
The reset() method sets all of the markers to `false`.

public void setPrevious (Square s)
public Square getPrevious ()
Sets and gets the previous square you accessed: from which you reached the current square. You will use this to build the path in `MazeSolver`.

3.3. The Maze Class
You are also provided with a `Maze` class that stores the logical layout of a maze in the form of a two-dimensional array of Square objects. 

You will be interested in the following methods (for more, see the code): 

ArrayList<Square> getNeighbors(Square sq)
Returns an ArrayList of the Square neighbors of the parameter Square sq. At each step we can move one square horizontally or vertically.

Square getStart()
Returns the starting square of the maze.

Note that there is no method getExit() – because there could be more than one possible exits in a given maze. 

There is also method loadMaze() that loads the data from a maze file, and method reset() that resets all the squares of the maze to their original unmarked state. 
3.2. Maze solving algorithm
Now that you have working stack and queue data types, you can use them to solve mazes! 

You'll next be implementing the application portion of this assignment, writing up `MazeSolver` classes which will determine if a given maze has a valid solution. That is, whether you can get from the start to the finish without jumping over any walls.

Our maze solving algorithm goes something like this: begin at the start location, and trace along all possible paths to (eventually) visit every reachable square. If at some point you visit the finish `Square`, then this maze is solvable. If you ran out of squares to check, and did not reach the finish, then the maze is unsolvable. 

We add all the squares that we reach during the exploration into a worklist which works either as a Stack or as a Queue.

The logic of a maze-solving algorithm can be expressed as following:

======================================================================
Initialization:
●Create an (empty) worklist (stack/queue) of locations to explore.
●Add the start location to it.

Repeat until the worklist is empty:
1.Grab the "next" Square to explore from the worklist.
2.Is this an exit square? 
a.If yes: the finish was reachable; terminate the algorithm and output the path you found.
b.If no: add every neighboring square that is not a wall to the worklist for later     exploration provided they have not previously been marked as explored

If the working list is empty and we did not reach the finish Square, then this maze is unsolvable.
=======================================================================
Note that this pseudocode does not depend on what kind of worklist you use (namely, a stack or a queue). You'll need to pick one when you create the worklist, but subsequently everything should work abstractly in terms of the worklist operations.
3.3. The MazeSolver abstract superclass
Thus, you will work in the abstract class `MazeSolver` to  implement the above algorithm, using a general worklist. Its abstract methods `add()` and `next()` will be implemented differently depending on whether the worklist is a stack or a queue. The `MazeSolver` class already has a non-public instance variable of type `Maze` to store the maze, and the non-public instance variable `path` of type `LinkedList` to store the path from start to finish. 

The following methods are partly implemented in class MazeSolver:

MazeSolver (Maze maze)
A (non-abstract) constructor that takes a `Maze` as a parameter and  stores it in a variable that all the subclasses can access.  The  `MazeSolver` constructor takes as a parameter the `Maze` to be solved. Recall that the constructor of a superclass is not inherited by a subclass. In your subclasses you would perform the two initialization steps of creating an empty worklist (using the `makeEmpty()` abstract method) and adding the maze's start location to the worklist (using the `add()` abstract method).

abstract void makeEmpty()
This method creates an empty worklist. This will be implemented by the subclasses of `MazeSolver`, depending which data type is selected for the worklist (Stack or Queue).

abstract boolean isEmpty()
Returns true if the worklist is empty.

abstract void add (Square sq)
Adds the given Square sq to the worklist.

abstract Square next()
Returns the next item from the worklist.

boolean isFinished()
A non-abstract method that the application program uses to see if this algorithm has solved this maze. That is, has it determined the path to the exit or if there is no path.  This method will return `true` if either
●a path from the start to the exit has been found; or
●you determine there is no such path (worklist is now empty, there is nothing more to explore).

boolean pathFound()
Returns `true` if there exists a path from the start to the exit, and `false` otherwise.

void step()
Performs one iteration of the algorithm described in 3.2. Note that this is not an abstract method, that is, you should implement this method in the `MazeSolver` class by using the abstract methods listed above.

In order to keep track of which squares have been already previously explored, after adding any new square to the worklist, you should "mark" it as explored. Then, before you add a new square to the worklist, you should first check that it is not marked (and if it is, refrain from adding it).  Use the `mark()` and `isMarked()` functions in `Square` to do this.

In addition, have each `Square` keep track of which `Square` added it to the worklist (i.e., "Which `Square` was being currently explored when this `Square` was added to the
worklist?", or “From which `Square` did we get to this neighbor?”). 
Use the `setPrevious()` method in `Square` to record this.  Note that  this means that each `Square` is a node in a singly-linked list of `Square`s, with `previous` pointing to the next node in this list.

The `MazeApp` will repeatedly call `step()` until `isFinished()` returns `true`, so make sure you correctly set the `MazeSolver`'s `finished` and `pathFound` `boolean`s in your `step()` function; otherwise, the `MazeApp` will not work correctly.

LinkedList<Square> getPath()
Returns a `LinkedList` consisting of each `Square` on the path from start to exit.
In order to create the path for `getPath()`, you will need to keep track of the path that was followed by your algorithm. This seems to be a difficult proposition; however, you've already done most of the work when you marked your worklist nodes using the previous. 

Let me explain this one more time: 
In order to keep from wandering in a circle, you should avoid exploring the same location twice. You only ever explore locations that are placed on your worklist, so you can guarantee that each location is explored at most once by making sure that each location goes on the worklist at most once. You've already accomplished this by "marking" each square that you place in the worklist and only adding unmarked squares to the list.

You are keeping track of the solution path that includes a given square by putting an arrow (the `previous` variable) in it that points back to the square from which you added it to the worklist. Now, when you are at the exit, you can just follow the arrows back to the start.  Of course, following the arrows gives you the path in reverse order. To get the sequence in the correct order, you probably want to add each previous square in front of the `LinkedList`.

So once the exit square has been reached, you can compute the path in the private method `buildPath(Square exit)`, to have your path ready to be returned when asked by the `MazeApp`.

If the exit is unreachable, `getPath()` returns the instance variable `path` which by default is initialized to null.
3.4. The MazeSolverStack and MazeSolverQueue subclasses
Now we just need to actually create two different concrete implementations of the `MazeSolver` class. Create two new classes `MazeSolverStack` and `MazeSolverQueue` that extend the `MazeSolver` class.

Each class should contain as an instance variable a worklist of the appropriate type (so, `MazeSolverStack` should have an instance member of type `MyStack<Square>` and MazeSolverQueue` should have one of type `MyQueue<Square>`).

These are not the abstract classes, and so you must implement the `MazeSolver`'s abstract methods. All you have to do to implement the abstract methods is perform the appropriate operations on the stack or queue instance variable. 

For example, the `MazeSolverStack`'s `add()` method may look like this.
public void add(Square sq) {
    stack.push(sq);
}

Recall that the constructors are not inherited by subclasses. You need to have a separate constructor for each subclass. Don't forget to include a call to `super(maze)` as the first line of your constructors.

For example the constructor for `MazeSolverStack` may look something like:
public MazeSolverStack(Maze maze) {
        super(maze);
        this.makeEmpty();
        Square start = this.maze.getStart();
        start.mark();
        this.add(start);
 } 

Test Your Maze Solvers
Add another JUnit Test Case to your project: MazeSolverTest.java. Run all the tests and fix any bugs that you find.
IV. Maze experiments
If everything is working in your `Maze` and `MazeSolver` classes, you should be able to run the `MazeApp` program and get a GUI interface that will allow you to visualize the process of finding the solution of the maze. You do not need to modify anything in `MazeApp.java`: you just need to uncomment the code in the `makeNewSolver()` method in `MazeApp.java` after you have implemented the required concrete Maze solvers.

The `load` and `quit` buttons operate as you might expect. You can load different files from the mazes folder and run different solvers on them. The reset button will call the `Maze`'s `reset()` method and then create a new `MazeSolver`. If you click on the stack button it will toggle between using a `Stack` or `Queue` to solve the maze. The `makeNewSolver()` method creates a new instance of either `MazeSolverStack` or `MazeSolverQueue`, depending on what the user has chosen in the GUI.)  

The `step` button performs a single step of the `MazeSolver` and `start` will animate things taking one step per timer delay interval.  As the animation proceeds, marked squares are painted gray. If the maze is solved, the squares on the solution path are painted yellow.  The `getPath()` method that you wrote in `MazeSolver.java` is used to get the path and display it in the bottom panel.
