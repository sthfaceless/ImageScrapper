package ru.projects.prog_ja.net;

import ru.projects.prog_ja.images.ImageConsumer;
import ru.projects.prog_ja.images.ImageFormats;
import ru.projects.prog_ja.utils.ImageNameProducer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.TreeSet;

public class ImageProducer implements Runnable {

    private final ImageConsumer imageConsumer;
    private final ImageFormats imageFormats;
    private final ImageNameProducer imageNameProducer;

    private final static String IMAGE_SERVER_URL = "http://i.imgur.com/";

    private final TreeSet<Integer> fails = new TreeSet<>();

    public ImageProducer(ImageConsumer imageConsumer,
                         ImageFormats imageFormats,
                         ImageNameProducer imageNameProducer) {
        this.imageConsumer = imageConsumer;
        this.imageFormats = imageFormats;
        this.imageNameProducer = imageNameProducer;

        fails.addAll(Arrays.asList(0, 503, 4939, 4940, 4941, 12003, 5556));
    }


    @Override
    public void run() {

        byte[] marker = new byte[2];
        String random, format;
        InputStream is;
        HttpURLConnection connection;

        while (true){

            random = imageNameProducer.produce();

            try {

                connection = (HttpURLConnection) new URL(IMAGE_SERVER_URL + random + ".jpg").openConnection();

                if(fails.contains(connection.getContentLength()))
                    continue;

                is = connection.getInputStream();

                int read = is.read(marker);
                format = read == 2 ? imageFormats.getByMarker(marker) : null;
                if(format == null)
                    continue;

                ReadableByteChannel channel = Channels.newChannel(is);

                imageConsumer.consume(channel, marker, random + "." + format);

            } catch (IOException e) { }

        }

    }
}
