package algorithms.search;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    private final Stack<AState> stack;
    public DepthFirstSearch() {
        super();
        this.stack=new Stack<>();
    }

    /**
     *
     * @return solution
     * create a stack for backtracking
     * set current cell to 0
     * set visited cells to 0
     *
     * while current cell not goal
     *     get unvisited neighbors using cell_neighbors
     *     if at least one neighbor
     *         choose random neighbor to be new cell
     *         visit new cell using visit_cell
     *         push current cell to stack
     *         set current cell to new cell
     *         add 1 to visited cells
     *     else
     *         backtrack current cell using backtrack method
     *         pop from stack to current cell
     *     call refresh_maze_view to update visualization
     * set state to 'idle'
     */
    @Override
    public Solution solve(ISearchable s) throws Exception {
        if (s==null){
            throw new Exception("the is no solution to empty problem");
        }
        Stack<AState> sol=new Stack<>();

        AState start = s.getStartState();
        AState goal=s.getGoalState();
        ArrayList<AState> temp=new ArrayList<>();
        AState curr=start;
        this.stack.push(start);

        ArrayList<AState> legal_possitions;
        HashSet<AState> have_been_visited=new HashSet<>();

        while (!curr.equals(goal)||!stack.empty()){
            curr=stack.pop();
            have_been_visited.add(curr);

            legal_possitions=s.getAllPossibleStates(curr);
            for (int i=0;i< legal_possitions.size();i++){
                if(!(this.stack.contains(legal_possitions.get(i)))||!(have_been_visited.contains(legal_possitions.get(i)))){
                    this.stack.push(legal_possitions.get(i));
                    this.NumberOfNodesEvaluated++;
                }
            }
        }
        return getSolution(sol, start, goal, temp, curr);
    }

    static Solution getSolution(Stack<AState> sol, AState start, AState goal, ArrayList<AState> temp, AState curr) {
        if (curr.equals(goal)){
            while (curr.getFrom()!=null){
                sol.push(curr);
                curr=curr.getFrom();
            }
        }
        if (sol.empty()){
            return new Solution(new ArrayList<>());
        }
        sol.push(start);

        while (!sol.empty()){
            temp.add(sol.pop());
        }
        return new Solution(temp);
    }
}