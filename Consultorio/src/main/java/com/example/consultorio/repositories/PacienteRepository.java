package com.example.consultorio.repositories;


import com.example.consultorio.models.Paciente;
import java.io.*;
import java.util.*;

public class PacienteRepository {
    private final String archivo = "pacientes.txt";

    public List<Paciente> leer() {
        List<Paciente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                if (d.length == 6) {
                    lista.add(new Paciente(d[0], d[1], Integer.parseInt(d[2]), d[3], d[4], d[5]));
                }
            }
        } catch (Exception e) {
            System.out.println("Archivo nuevo o vacio");
        }
        return lista;
    }

}