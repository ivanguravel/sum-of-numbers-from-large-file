package org.education;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CalculationsHelper {

    private static int DEFAULT_BUFFER_4_READ_SIZE = 16;
    private static int DIGITS_SPACE = 8;

    static long calculateAllPartitionsSum(final FileChannel fileChannel) {
        long generalSum = 0;
        try {
            // we must 2 have count partitions 4 reading. DEFAULT_BUFFER_4_READ_SIZE is empirical size.
            final long partitions = fileChannel.size() / DEFAULT_BUFFER_4_READ_SIZE;

            int indent;
            for (int i = 0; i < partitions; i++) {
                // 4 caclulating indent of every number.
                indent = i * DEFAULT_BUFFER_4_READ_SIZE;
                calculatePartitionOfFileSum(fileChannel, DEFAULT_BUFFER_4_READ_SIZE, indent);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Problems with file channel.");
        }
        return generalSum;
    }

    private static long calculatePartitionOfFileSum(final FileChannel inChannel, final int bufferSize, final int bufferOffset) {
        long partitionSum = 0;
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        try {
            inChannel.read(buffer, bufferOffset);
            for(int i = 0; i < bufferSize; i += DIGITS_SPACE) {
                partitionSum = Long.sum(partitionSum, buffer.getInt(i));
            }
        } catch (IOException ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
        return partitionSum;
    }

    private CalculationsHelper() {
    }
}
