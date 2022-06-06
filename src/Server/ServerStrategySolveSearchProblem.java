package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    private HashMap<Integer,String> map;


    public ServerStrategySolveSearchProblem() {
        map=new HashMap<>();
    }

    @Override
    public void ServerStrategy(InputStream InputFromClient, OutputStream OutputToClient) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            ObjectOutputStream toClient=new ObjectOutputStream(OutputToClient);
            ObjectInputStream fromClient=new ObjectInputStream(InputFromClient);
            toClient.flush();
            Maze maze= (Maze) fromClient.readObject();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            int code=maze.hashCode();
            String path=tempDirectoryPath+"/"+code;


            File file=new File(path);
            if (file.exists()||map.containsKey(code)){
                FileInputStream fileInputStream=new FileInputStream(path);
                ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
                Solution sol=(Solution) objectInputStream.readObject();
                toClient.writeObject(sol);
                toClient.flush();
                fromClient.close();
                toClient.close();


            }
            else {

                ISearchingAlgorithm algorithm=Configurations.getSearching();
                SearchableMaze maze1=new SearchableMaze(maze);
                Solution sol1 =algorithm.solve(maze1);
                FileOutputStream fileOut=new FileOutputStream(path);
                ObjectOutputStream objectOut=new ObjectOutputStream(fileOut);
                objectOut.writeObject(sol1);
                objectOut.flush();
                map.put(code,path);
                fileOut.close();
                objectOut.close();

                toClient.writeObject(sol1);
                toClient.flush();




            }




        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
