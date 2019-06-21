package ru.projects.prog_ja.images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

public class ImageConsumer {

    private final String dirName;

    public ImageConsumer(String dirName) {
        this.dirName = dirName;
    }

    public void consume(ReadableByteChannel channel, byte[] marker, String name) throws IOException {

        try (FileOutputStream os = new FileOutputStream(new File(dirName + name))) {

            os.write(marker);
            os.getChannel().transferFrom(channel, 2, Long.MAX_VALUE);
            os.flush();

        }

    }

}
