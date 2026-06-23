package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassConsoleRegistration registration = new ClassConsoleRegistration();
        TxtAssessmentRepository txtRepository = new TxtAssessmentRepository();
        AcademicReportService reportService = new AcademicReportService();
        
        AcademicClass currentClass = null;
        boolean running = true;

        while (running) {
            System.out.println("\n========================================");
            System.out.println("       SISTEMA ACADÊMICO - MENU        ");
            System.out.println("========================================");
            System.out.println("1 - Cadastrar Turma e Avaliações");
            System.out.println("2 - Salvar Avaliações em TXT");
            System.out.println("4 - Gerar Resumo da Turma (US-2375)");
            System.out.println("5 - Gerar Relatório de Pesos (US-2376)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> {
                    currentClass = registration.registerClassFromKeyboard();
                    System.out.println("\nTurma configurada com sucesso!");
                }
                case "2" -> {
                    if (currentClass == null) {
                        System.out.println("\n[ERRO] Cadastre uma turma primeiro (Opção 1).");
                    } else {
                        System.out.print("Nome do arquivo (ex: avaliacoes.txt): ");
                        String filename = scanner.nextLine().trim();
                        txtRepository.saveAssessments(currentClass, filename);
                    }
                }
                case "4" -> reportService.generateSummaryReport(currentClass);
                case "5" -> reportService.generateWeightReport(currentClass);
                case "0" -> {
                    System.out.println("Encerrando o sistema. Até logo!");
                    running = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}

