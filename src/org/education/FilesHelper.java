package org.education;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

class FilesHelper {

    private static String READ_MODE = "r";

    static String getNormalizedPath2File(final String fileName) {
        return Paths.get(fileName).toAbsolutePath().normalize().toString();
    }

    static FileChannel createFileChannel(final String path2File) {
        try {
            return new RandomAccessFile(path2File, READ_MODE).getChannel();
        } catch (FileNotFoundException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    private FilesHelper() {}
}
