package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonAssessmentRepository {
    public void saveAssessments(AcademicClass academicClass, String filename) {
        if (academicClass == null) return;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("{\n");
            writer.write("  \"className\": \"" + academicClass.getName() + "\",\n");
            writer.write("  \"assessments\": [\n");
            List<Assessment> list = academicClass.getAssessments();
            for (int i = 0; i < list.size(); i++) {
                Assessment a = list.get(i);
                writer.write("    {\n");
                writer.write("      \"type\": \"" + a.getClass().getSimpleName() + "\",\n");
                writer.write("      \"name\": \"" + a.getName() + "\",\n");
                writer.write("      \"weight\": " + a.getWeight() + "\n");
                writer.write(i == list.size() - 1 ? "    }\n" : "    },\n");
            }
            writer.write("  ]\n");
            writer.write("}");
            System.out.println("Salvo em JSON com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar JSON: " + e.getMessage());
        }
    }
}

