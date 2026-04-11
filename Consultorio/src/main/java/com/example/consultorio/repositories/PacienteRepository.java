package com.example.consultorio.repositories;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class PacienteRepository {
    private final Path filePath = Paths.get("data", "pacientes.csv");

    private void ensureFileExist() throws IOException {
        Path dataDir = Paths.get("data");
        if (Files.notExists(dataDir)) Files.createDirectories(dataDir);
        if (Files.notExists(filePath)) Files.createFile(filePath);
    }

    public List<String> readAllLines() throws IOException {
        ensureFileExist();
        return Files.readAllLines(filePath, StandardCharsets.UTF_8);
    }

    public void appendNewLine(String line) throws IOException {
        ensureFileExist();
        Files.writeString(filePath, line + System.lineSeparator(),
                StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    public void writeAllLines(List<String> lines) throws IOException {
        ensureFileExist();
        Files.write(filePath, lines, StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }
}
