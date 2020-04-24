package com.epam.cdp.maksim.katuranau.module7.task3;

import com.epam.cdp.maksim.katuranau.module7.task3.service.Mover;
import com.epam.cdp.maksim.katuranau.module7.task3.service.impl.BufferedFileStreamsMover;
import com.epam.cdp.maksim.katuranau.module7.task3.service.impl.FileChannelMover;
import com.epam.cdp.maksim.katuranau.module7.task3.service.impl.FileStreamsMover;
import com.epam.cdp.maksim.katuranau.module7.task3.service.impl.NioFileMover;
import com.epam.cdp.maksim.katuranau.module7.task3.util.FileSizeGenerator;
import com.epam.cdp.maksim.katuranau.module7.task3.util.PerformanceCalculator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        List<Mover> movers = new ArrayList<>();
        movers.add(new NioFileMover());
        movers.add(new FileChannelMover());
        movers.add(new BufferedFileStreamsMover());
        movers.add(new FileStreamsMover());
        String sourcePath = "module7/src/main/java/com/epam/cdp/maksim/katuranau/module7/task3/source/";
        String destinationPath = "module7/src/main/java/com/epam/cdp/maksim/katuranau/module7/task3/destination/";
        String fileName = "file.test";

        FileSizeGenerator fileSizeGenerator = new FileSizeGenerator();
        fileSizeGenerator.createDirectory(sourcePath);
        fileSizeGenerator.createDirectory(destinationPath);

        PerformanceCalculator performanceCalculator =
                new PerformanceCalculator(fileSizeGenerator, sourcePath, destinationPath, fileName);

        movers.forEach(performanceCalculator::logTimings);

        fileSizeGenerator.deleteDirectory(sourcePath);
        fileSizeGenerator.deleteDirectory(destinationPath);
        System.out.println("\n\nresult time :"
                + (System.nanoTime() - startTime) / Double.parseDouble("60000000000") + " minute");
    }
}
