package ru.projects.prog_ja.main;

import ru.projects.prog_ja.images.ImageConsumer;
import ru.projects.prog_ja.images.ImageFormats;
import ru.projects.prog_ja.net.ImageProducer;
import ru.projects.prog_ja.utils.ImageNameProducer;

import java.nio.file.Paths;

public class RemoteApplicationInstance {

    private int numOfThreads;
    private String dirName;

    public RemoteApplicationInstance(String[] args){

        if(args.length != 2) {
            System.out.println("Usage: java -jar ImageScrapper.jar \"<directoryName>\" <numOfThreads>");
            System.exit(1);
        }

        dirName = args[0];
        if(!Paths.get(dirName).toFile().isDirectory()){
            System.out.println("First parameter isn't a directory");
            System.exit(1);
        }

        try {

             numOfThreads = Integer.parseInt(args[1]);
             if(numOfThreads > 30){
                 System.out.println("Max num of threads: 30");
                 System.exit(1);
             }else if(numOfThreads < 0){
                 System.out.println("You didn't start any threads");
                 System.exit(1);
             }

        }catch (NumberFormatException e){
            System.out.println("Second parameter isn't a number");
            System.exit(1);
        }

    }

    public void start(){

        for(int i = 0; i < numOfThreads; i++) {
            System.out.printf("Starting thread: #%d\n", i);

            new Thread(new ImageProducer(
                    new ImageConsumer(dirName + "/"), new ImageFormats(), new ImageNameProducer())).start();
        }
    }

}
