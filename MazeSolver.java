package ass2;

import java.util.*;

abstract class MazeSolver {
    public static final int STACK = 1;
    public static final int QUEUE = 2;

    protected Maze maze;
    protected boolean finished = false;
    protected boolean pathFound = false;
    private LinkedList<Square> path;
    private int solverType;

    MazeSolver(Maze maze, int solverType) {
        this.maze = maze;
        this.solverType = solverType;
        this.path = new LinkedList<>();
    }

    abstract void makeEmpty();

    abstract boolean isEmpty();

    abstract void add(Square sq);

    abstract Square next();

    public boolean isFinished() {
        return finished;
    }

    public boolean pathFound() {
        return pathFound;
    }

    private void buildPath(Square exit) {
        this.path.clear();
        if (solverType == STACK) {
            buildPathForStack(exit);
        } else if (solverType == QUEUE) {
            buildPathForQueue(exit);
        }
    }

    private void buildPathForStack(Square exit) {
        // Perform a Breadth-First Search
        Queue<Square> queue = new LinkedList<>();
        Map<Square, Square> parentMap = new HashMap<>();

        // Initialize with the exit square
        queue.offer(exit);
        parentMap.put(exit, null);

        while (!queue.isEmpty()) {
            Square currentSquare = queue.poll();

            if (currentSquare.getType() == Square.START) {
                // Found the start square, build the path
                LinkedList<Square> shortestPath = new LinkedList<>();
                Square square = currentSquare;
                while (square != null) {
                    shortestPath.add(square);
                    square = parentMap.get(square); 
                }
                this.path = shortestPath;
                return;
            }

            for (Square neighbor : this.maze.getNeighbors(currentSquare)) {
                if (!parentMap.containsKey(neighbor) && neighbor.getType() != Square.WALL) {
                    queue.offer(neighbor);
                    parentMap.put(neighbor, currentSquare);
                }
            }
        }
    }


    private void buildPathForQueue(Square exit) {
        Queue<Square> queue = new LinkedList<>();
        while (exit != null) {
            queue.add(exit);
            exit = exit.getPrevious();
        }

        Stack<Square> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }

        while (!stack.isEmpty()) {
            this.path.add(stack.pop());
        }
    }

    public LinkedList<Square> getPath() {
        return this.path;
    }

    public void step() {
        if (this.isEmpty()) {
            this.finished = true;
            return;
        }
        Square nextSquare = this.next();

        // Mark the square as explored
        nextSquare.mark();

        if (nextSquare.getType() == Square.EXIT) {
            // Exit found; build the path
            this.buildPath(nextSquare);
            this.finished = true;
            this.pathFound = true;

            // Mark squares on the solution path as "on path"
            for (Square squareOnPath : this.path) {
                squareOnPath.onpath();
            }
        } else {
            // Add neighboring unexplored squares to the worklist
            for (Square neighbor : this.maze.getNeighbors(nextSquare)) {
                if (!neighbor.isMarked() && neighbor.getType() != Square.WALL) {
                    neighbor.setPrevious(nextSquare);
                    this.add(neighbor);
                }
            }
        }
    }
}
