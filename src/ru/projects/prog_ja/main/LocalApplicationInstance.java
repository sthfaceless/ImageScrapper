package ru.projects.prog_ja.main;

import ru.projects.prog_ja.files.FileProcessor;

import java.io.File;

public class LocalApplicationInstance {

    private final String dirName;
    private File[] files;

    private FileProcessor fileProcessor;

    public LocalApplicationInstance(String[] args) {

        if (args.length != 2) {
            System.out.println("Use: java <filename> \"<directory>\" <deleteUnknown(1:0)>");
            System.exit(1);
        }

        dirName = args[0];

        try {

            File file = new File(dirName);
            if (!file.isDirectory()) {
                System.out.println("Path is not directory!");
                System.exit(1);
            }

            files = file.listFiles();
            if (files == null || files.length == 0) {
                System.out.println("This directory is empty");
                System.out.println(0);
            }

        } catch (SecurityException e) {
            System.out.println("You haven't rights to this directory!");
            System.exit(1);
        }

        try {

            fileProcessor = new FileProcessor( Integer.parseInt(args[1]) == 1 );

        }catch (NumberFormatException e){
            System.out.println("Invalid second parameter!");
            System.exit(1);
        }

        System.out.println("Parameters are valid");
    }

    public void start(){

        System.out.println("Starting program...");

        for (File file : files){

            fileProcessor.process(file);

        }

        System.out.println("Program have finished");
        System.exit(0);

    }

}
