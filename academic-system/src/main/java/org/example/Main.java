package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassConsoleRegistration registration = new ClassConsoleRegistration();
        PersistenceManager persistenceManager = new PersistenceManager();
        AcademicReportService reportService = new AcademicReportService();
        
        AcademicClass currentClass = null;
        boolean running = true;

        while (running) {
            System.out.println("\n========================================");
            System.out.println("       SISTEMA ACADÊMICO - MENU FINAL   ");
            System.out.println("========================================");
            System.out.println("1 - Cadastrar Turma e Avaliações (Teclado)");
            System.out.println("2 - Escolher Formato de Salvamento (TXT/XML/JSON) (US-2372)");
            System.out.println("3 - Salvar Dados da Turma Atual");
            System.out.println("4 - Gerar Resumo da Turma (US-2375)");
            System.out.println("5 - Gerar Relatório de Pesos (US-2376)");
            System.out.println("6 - Gerar Relatório de Persistência (US-2377)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> {
                    currentClass = registration.registerClassFromKeyboard();
                    System.out.println("\nTurma configurada com sucesso!");
                }
                case "2" -> {
                    System.out.print("Digite o formato desejado (TXT, XML ou JSON): ");
                    String format = scanner.nextLine();
                    persistenceManager.setStorageType(format);
                }
                case "3" -> {
                    if (currentClass == null) {
                        System.out.println("\n[ERRO] Nenhuma turma cadastrada. Use a opção 1.");
                    } else {
                        System.out.print("Digite o nome base do arquivo (sem extensão): ");
                        String name = scanner.nextLine().trim();
                        persistenceManager.save(currentClass, name);
                    }
                }
                case "4" -> reportService.generateSummaryReport(currentClass);
                case "5" -> reportService.generateWeightReport(currentClass);
                case "6" -> persistenceManager.generatePersistenceReport();
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

