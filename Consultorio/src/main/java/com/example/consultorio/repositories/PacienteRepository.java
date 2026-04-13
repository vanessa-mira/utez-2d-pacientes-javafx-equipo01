package com.example.consultorio.repositories;

import com.example.consultorio.models.Paciente;
import java.io.*;
import java.util.*;

public class PacienteRepository {
    private final String archivo = "data/pacientes.csv"; // nombre y ruta del archivo donde se guarda todo

    public List<Paciente> leer() {
        List<Paciente> lista = new ArrayList<>(); // crea una lista vacia para guardar lo que lea
        File file = new File(archivo); // crea el objeto del archivo
        if (!file.exists()) return lista; // si no existe el archivo todavia regresa la lista vacia

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) { // lee el archivo linea por linea
                String[] d = linea.split(","); // separa los datos cuando encuentra una coma
                if (d.length == 6) { // si la linea tiene los 6 datos completos los procesa
                    lista.add(new Paciente(d[0], d[1], Integer.parseInt(d[2]), d[3], d[4], d[5])); // agrega el paciente
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo"); // avisa en consola si hubo un problema
        }
        return lista; // entrega la lista con los pacientes cargados
    }

    public void guardar(List<Paciente> lista) {
        File directorio = new File("data"); // referencia a la carpeta data
        if (!directorio.exists()) directorio.mkdir(); // crea la carpeta si no existe para que no de error

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Paciente p : lista) { // recorre cada paciente de la lista
                // escribe los datos del paciente en una linea separados por comas
                bw.write(p.getCurp() + "," + p.getNombre() + "," + p.getEdad() + "," +
                        p.getTelefono() + "," + p.getAlergias() + "," + p.getEstatus());
                bw.newLine(); // agrega un salto de linea para el siguiente paciente
            }
        } catch (IOException e) {
            e.printStackTrace(); // muestra el error detallado si falla el guardado
        }
    }
}