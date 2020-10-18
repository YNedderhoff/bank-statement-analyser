package xyz.nedderhoff.bankstatementanalyser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.nedderhoff.bankstatementanalyser.config.FileStorageConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class FileParsingTask {
    private static final Logger logger = LoggerFactory.getLogger(FileParsingTask.class);

    private final Path fileStorageLocation;
    private final ParsedFilesCache cache;
    private final FileParsingService parser;

    @Autowired
    public FileParsingTask(
            FileStorageConfig fileStorageConfig,
            ParsedFilesCache cache,
            FileParsingService parser
    ) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        this.cache = cache;
        this.parser = parser;
    }

    @Scheduled(fixedDelay = 1000)
    public void parseNewlyUploadedFiles() throws IOException {
        try (Stream<Path> paths = Files.walk(fileStorageLocation)) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(file -> !cache.contains(file))
                    .map(cache::addFilenameIfAbsent)
                    .map(Path::toFile)
                    .forEach(parser::process);
        }
    }
}
