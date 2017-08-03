package org.education;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static org.education.FilesHelper.createFileChannel;
import static org.education.FilesHelper.getNormalizedPath2File;

public class SumOfNumbersFromLargeFile {

    public static void main(String[] args) throws IOException {
        checkIfFilePathPresent(args);
        // 4 supporting pathes in different OSes.
        final String normalizedFilePath = getNormalizedPath2File(args[0]);

        // 4 working with file I used file channel and after that convert it 2 byte buffer, which faster than bufferedinputstream.
        // This one feature helps me 2 read a file with large size.
        final FileChannel fileChannel = createFileChannel(normalizedFilePath);

        System.out.println(CalculationsHelper.calculateAllPartitionsSum(fileChannel));
    }

    private static void checkIfFilePathPresent(final String... args) {
        if (args.length != 1) {
            System.out.println( "Please, use this software with the next way: ./SumOfNumbersFromLargeFile 'nameOfFile'");
            System.exit(1);
        }
    }
}
