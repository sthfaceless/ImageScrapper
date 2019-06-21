package ru.projects.prog_ja.files;

import ru.projects.prog_ja.images.ImageFormats;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileProcessor {

    private final ImageFormats imageFormats = new ImageFormats();

    private final boolean delOnUnknown;

    public FileProcessor(boolean delOnUnknown){
        this.delOnUnknown = delOnUnknown;
    }

    public void process(File file){

        byte[] marker = new byte[2];

        String fileName = file.getAbsolutePath();

        int extIndex = fileName.lastIndexOf(".") + 1;
        String oldExt = fileName.substring(extIndex, fileName.length());

        try (FileInputStream fis = new FileInputStream(file)) {

            if(fis.read(marker, 0 , marker.length) == 0)
                return;

            String newExt = imageFormats.getByMarker(marker);
            if(newExt == null){
                if(delOnUnknown)
                    file.delete();
                return;
            }

            if(!oldExt.equals(newExt)){

                file.renameTo(new File(fileName.substring(0, extIndex) + newExt));

            }

        } catch (IOException e){ }

    }

}
