package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class XmlAssessmentRepository {
    public void saveAssessments(AcademicClass academicClass, String filename) {
        if (academicClass == null) return;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("<academicClass>\n");
            writer.write("  <name>" + academicClass.getName() + "</name>\n");
            writer.write("  <assessments>\n");
            for (Assessment a : academicClass.getAssessments()) {
                writer.write("    <assessment>\n");
                writer.write("      <type>" + a.getClass().getSimpleName() + "</type>\n");
                writer.write("      <name>" + a.getName() + "</name>\n");
                writer.write("      <weight>" + a.getWeight() + "</weight>\n");
                writer.write("    </assessment>\n");
            }
            writer.write("  </assessments>\n");
            writer.write("</academicClass>");
            System.out.println("Salvo em XML com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar XML: " + e.getMessage());
        }
    }
}
