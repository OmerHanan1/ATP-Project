package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class BestFirstSearchTest {

    BestFirstSearch to_check = new BestFirstSearch();
    //Checks that getName returns the correct name
    @Test
    void getName(){
        Assertions.assertEquals("Best First Search", to_check.getName());
    }

    @Test
    void Test_Null(){
        try{
            Solution solution = to_check.solve(null);
        }
        catch (Exception e){
            Assertions.assertTrue(true);
            return;
        }
        Assertions.fail();
    }

    //Checks the cost of path for a known small maze
    @Test
    void Test_cost() throws Exception{
        Maze maze = new Maze(
                new int[][]{{1,0,0},{1,1,0},{0,0,0}},
                new Position(0,1),
                new Position(2,0)
        );
        //Solves the maze using BestFirstSearch then checks the cost
        SearchableMaze Maze = new SearchableMaze(maze);
        Solution solution = to_check.solve(Maze);
        ArrayList<AState> sol = solution.getSolutionPath();
        Assertions.assertEquals(40, sol.get(sol.size()-1).getCost());
    }

    @Test
    void Test_time() throws Exception{
        Maze maze = new MyMazeGenerator().generate(1000,1000);
        SearchableMaze Maze = new SearchableMaze(maze);
        long start_time = System.currentTimeMillis();
        to_check.solve(Maze);
        long end_time = System.currentTimeMillis();
        long tot_time = end_time-start_time;
        Assertions.assertTrue(tot_time<=60000);
    }
}