package ass2;

import java.util.Stack;

public class MazeSolverStack extends MazeSolver {
    private Stack<Square> stack;

    MazeSolverStack(Maze maze) {
        super(maze, STACK); // Pass STACK as the solverType
        this.stack = new Stack<>();
        Square start = this.maze.getStart();
        start.mark();
        this.add(start);
    }

    void makeEmpty() {
        this.stack.clear();
    }

    boolean isEmpty() {
        return this.stack.isEmpty();
    }

    void add(Square sq) {
        this.stack.push(sq);
//        sq.setOnPath(); // Mark the square as on the path
    }

    Square next() {
        return this.stack.pop();
    }
}
