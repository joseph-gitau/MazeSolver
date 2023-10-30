package ass2;

import java.util.LinkedList;
import java.util.Queue;

public class MazeSolverQueue extends MazeSolver {
    private Queue<Square> queue;

    MazeSolverQueue(Maze maze) {
        super(maze, QUEUE); // Pass QUEUE as the solverType
        this.queue = new LinkedList<>();
        Square start = this.maze.getStart();
        start.mark();
        this.add(start);
    }

    void makeEmpty() {
        this.queue.clear();
    }

    boolean isEmpty() {
        return this.queue.isEmpty();
    }

    void add(Square sq) {
        this.queue.offer(sq);
//        sq.setOnPath(); // Mark the square as on the path
    }

    Square next() {
        return this.queue.poll();
    }
}
