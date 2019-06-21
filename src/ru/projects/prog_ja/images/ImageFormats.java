package ru.projects.prog_ja.images;

import java.util.TreeMap;

public class ImageFormats {

    private final TreeMap<Short, String> extensions = new TreeMap<>();

    public ImageFormats(){

        extensions.put((short) 0x8950, "png");
        extensions.put((short) 0xFFD8, "jpg");
        extensions.put((short) 0x4749, "gif");
        extensions.put((short) 0x5745, "webp");
        extensions.put((short) 0x5249, "riff");
        extensions.put((short) 0x4949, "tif");
        extensions.put((short) 0x4D4D, "tif");
        extensions.put((short) 0x0000, "ico");
        extensions.put((short) 0x424D, "bmp");

    }

    public String getByMarker(byte[] marker){

        int v = (marker[0] << 8) | marker[1];

        return extensions.get((short) v);
    }

}
