package xyz.nedderhoff.bankstatementanalyser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import xyz.nedderhoff.bankstatementanalyser.exceptions.FileAlreadyParsedException;

import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
public class ParsedFilesCache {
    private static final Logger logger = LoggerFactory.getLogger(ParsedFilesCache.class);
    private final Set<String> cache = new ConcurrentSkipListSet<>();

    public boolean contains(Path file) {
        return cache.contains(getName(file));
    }

    public Path addFilename(Path file) throws FileAlreadyParsedException {
        if (this.contains(file)) {
            throw new FileAlreadyParsedException(file + "was already parsed");
        }
        String fileName = getName(file);
        cache.add(fileName);
        return file;
    }

    public Path addFilenameIfAbsent(Path file) {
        Path storedFile = file;
        try {
            storedFile = this.addFilename(file);
        } catch (FileAlreadyParsedException e) {
            logger.debug("File {} already in cache, skipping", getName(file));
        }
        return storedFile;
    }


    private String getName(Path file) {
        return file.getFileName().toString();
    }
}
