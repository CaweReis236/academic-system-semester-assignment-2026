package org.example;

public class PersistenceManager {
    private String currentStorageType = "TXT"; // Padrão inicial

    // US-2372: Configure persistence type as administrator
    public void setStorageType(String type) {
        String upper = type.toUpperCase().trim();
        if (upper.equals("TXT") || upper.equals("XML") || upper.equals("JSON")) {
            this.currentStorageType = upper;
            System.out.println("Formato de persistência alterado para: " + upper);
        } else {
            System.out.println("Formato inválido! Mantendo o atual.");
        }
    }

    // US-2377: Generate persistence configuration report
    public void generatePersistenceReport() {
        System.out.println("\n=== RELATÓRIO DE CONFIGURAÇÃO DE PERSISTÊNCIA (US-2377) ===");
        System.out.println("Tipo de Armazenamento Ativo: " + this.currentStorageType);
        System.out.println("---------------------------------------------------------");
    }

    public void save(AcademicClass academicClass, String baseFilename) {
        switch (this.currentStorageType) {
            case "TXT" -> new TxtAssessmentRepository().saveAssessments(academicClass, baseFilename + ".txt");
            case "XML" -> new XmlAssessmentRepository().saveAssessments(academicClass, baseFilename + ".xml");
            case "JSON" -> new JsonAssessmentRepository().saveAssessments(academicClass, baseFilename + ".json");
        }
    }
}

