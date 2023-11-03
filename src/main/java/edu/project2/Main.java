package edu.project2;

public class Main {
    public static void main(String[] args) {
        Generator generator = new RecursiveBacktrackingGenerator();
        Maze maze = generator.generate(10, 15);

        Renderer renderer = new DefaultRenderer();
        System.out.println(renderer.render(maze));

        Solver solver = new DfsSolver();

        SolvedMaze solvedMaze = solver.solve(maze);


        System.out.println(renderer.render(solvedMaze));
    }
}
