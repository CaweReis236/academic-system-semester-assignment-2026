package org.example;

public class AcademicReportService {

    // US-2375: Generate class assessment summary report
    public void generateSummaryReport(AcademicClass academicClass) {
        if (academicClass == null) {
            System.out.println("Nenhuma turma selecionada para o relatório.");
            return;
        }
        System.out.println("\n=== RELATÓRIO: RESUMO DA TURMA (US-2375) ===");
        System.out.println("Turma: " + academicClass.getName());
        System.out.println("Total de Avaliações: " + academicClass.getAssessments().size());
        System.out.println("----------------------------------------");
    }

    // US-2376: Generate assessment weight report
    public void generateWeightReport(AcademicClass academicClass) {
        if (academicClass == null) {
            System.out.println("Nenhuma turma selecionada para o relatório.");
            return;
        }
        System.out.println("\n=== RELATÓRIO: PESOS DAS AVALIAÇÕES (US-2376) ===");
        double totalWeight = 0.0;
        for (Assessment a : academicClass.getAssessments()) {
            System.out.printf("- %s (%s): Peso %.2f\n", a.getName(), a.getClass().getSimpleName(), a.getWeight());
            totalWeight += a.getWeight();
        }
        System.out.println("----------------------------------------");
        System.out.printf("SOMA TOTAL DOS PESOS: %.2f\n", totalWeight);
    }
}
