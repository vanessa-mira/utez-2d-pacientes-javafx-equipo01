package com.example.consultorio.repositories;

    import java.io.IOException;
    import java.nio.charset.StandardCharsets;
    import java.nio.file.*;
    import java.util.List;

    public class PacienteRepository {
        private final Path filePath = Paths.get("data", "pacientes.csv");

        private void ensureFileExist() throws IOException {
            if (Files.notExists(filePath.getParent())) Files.createDirectories(filePath.getParent());
            if (Files.notExists(filePath)) Files.createFile(filePath);
        }

        public List<String> readAllLines() throws IOException {
            ensureFileExist();
            return Files.readAllLines(filePath, StandardCharsets.UTF_8);
        }

        public void saveAllLines(List<String> lines) throws IOException {
            ensureFileExist();
            Files.write(filePath, lines, StandardCharsets.UTF_8,
                    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        }
    }

