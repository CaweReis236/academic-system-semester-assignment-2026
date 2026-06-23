package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtAssessmentRepository {

    public void saveAssessments(AcademicClass academicClass, String filename) {
        if (academicClass == null || academicClass.getAssessments() == null) {
            System.err.println("Turma ou lista de avaliações inválida.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Assessment assessment : academicClass.getAssessments()) {
                String type = assessment.getClass().getSimpleName();
                String name = assessment.getName();
                double weight = assessment.getWeight();
                
                writer.write(type + ";" + name + ";" + weight);
                writer.newLine();
            }
            System.out.println("Avaliações salvas com sucesso em: " + filename);
        } catch (IOException e) {
            System.err.println("Erro ao salvar as avaliações no arquivo TXT: " + e.getMessage());
        }
    }
}
