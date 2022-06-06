package Server;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
//import IO.SimpleCompressorOutputStream;
import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BestFirstSearch;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void ServerStrategy(InputStream InputFromClient, OutputStream OutputToClient) throws IOException {
        ObjectInputStream fromClient = new ObjectInputStream(InputFromClient);
        ObjectOutputStream toClient=new ObjectOutputStream(OutputToClient);
        int[] ClientArray;
        //if the array is out of bounds
        try{
            ClientArray= (int[]) fromClient.readObject();
            if (ClientArray.length!=2){
                throw new Exception("array size needs to be 2");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //new maze from the user
        IMazeGenerator mazeGenerator;
        try {
            mazeGenerator= (IMazeGenerator) Class.forName("algorithms.mazeGenerators"+Configurations.getInstance().getGenerator()).getConstructor().newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Maze maze ;
        ByteArrayOutputStream ByteArrayTo=new ByteArrayOutputStream();
        MyCompressorOutputStream Compressed=new MyCompressorOutputStream(ByteArrayTo);

        try {
            maze = mazeGenerator.generate(ClientArray[0], ClientArray[1]);
            Compressed.write(maze.toByteArray());
            Compressed.flush();
            toClient.writeObject(ByteArrayTo.toByteArray());
            toClient.flush();
            fromClient.close();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
