package org.example;

import java.util.Scanner;

public class ClassConsoleRegistration {

    public AcademicClass registerClassFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== CADASTRO DE NOVA TURMA ===");
        System.out.print("Digite o código da turma (ex: COM112): ");
        String classCode = scanner.nextLine();
        
        System.out.print("Digite o nome da turma (ex: POO): ");
        String className = scanner.nextLine();
        
        // Instancia passando os dois parâmetros exigidos pelo seu construtor
        AcademicClass academicClass = new AcademicClass(classCode, className);
        
        boolean addingAssessments = true;
        while (addingAssessments) {
            System.out.print("\nDeseja adicionar uma avaliação a esta turma? (S/N): ");
            String response = scanner.nextLine().trim().toUpperCase();
            
            if (response.equals("N")) {
                addingAssessments = false;
                break;
            } else if (!response.equals("S")) {
                System.out.println("Opção inválida! Digite S para Sim ou N para Não.");
                continue;
            }
            
            System.out.println("\nSelecione o tipo de avaliação:");
            System.out.println("1 - Exame (Exam)");
            System.out.println("2 - Trabalho Prático (PracticalAssignment)");
            System.out.println("3 - Seminário (Seminar)");
            System.out.print("Opção: ");
            String typeOption = scanner.nextLine().trim();
            
            System.out.print("Digite o nome/descrição da avaliação: ");
            String name = scanner.nextLine();
            
            double weight = 0.0;
            boolean validWeight = false;
            while (!validWeight) {
                System.out.print("Digite o peso da avaliação (ex: 4.0): ");
                try {
                    weight = Double.parseDouble(scanner.nextLine().trim());
                    validWeight = true;
                } catch (NumberFormatException e) {
                    System.out.println("Peso inválido! Use números e ponto para decimais.");
                }
            }
            
            Assessment assessment;
            switch (typeOption) {
                case "1" -> assessment = new Exam(name, weight);
                case "2" -> assessment = new PracticalAssignment(name, weight);
                case "3" -> assessment = new Seminar(name, weight);
                default -> {
                    System.out.println("Tipo inválido! Criando como Exame por padrão.");
                    assessment = new Exam(name, weight);
                }
            }
            
            academicClass.addAssessment(assessment);
            System.out.println("Avaliação adicionada com sucesso!");
        }
        
        return academicClass;
    }
}
