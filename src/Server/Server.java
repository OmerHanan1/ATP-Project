package Server;

import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import static jdk.internal.vm.PostVMInitHook.run;

public class Server {
    private final int port;
    private final int listeningIntervalMS;
    private final IServerStrategy strategy;
    private volatile boolean stop;

    private final ExecutorService threadPool;


    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;

        int num_threads = Configurations.getInstance().getNumOfThreads();

        this.threadPool = Executors.newFixedThreadPool(num_threads);

    }

    public void start() {
        Thread thread = new Thread(this::run);
        thread.start();

    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            serverSocket.setSoTimeout(this.listeningIntervalMS);


            while (!this.stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    threadPool.submit(()->{handleClient(clientSocket);});

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            serverSocket.close();
            threadPool.shutdown();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void handleClient(Socket clientSocket) {
        try {
            this.strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());

            clientSocket.close();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                InstantiationException | IllegalAccessException ioException) {
            ioException.printStackTrace();

        }

    }

    public void stop() {

        this.stop = true;
    }
}