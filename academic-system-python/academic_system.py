import os

# --- MODELO DE DOMÍNIO ---
class Assessment:
    def __init__(self, name: str, weight: float):
        self.name = name
        self.weight = weight

class Exam(Assessment):
    pass

class PracticalAssignment(Assessment):
    pass

class Seminar(Assessment):
    pass

class AcademicClass:
    def __init__(self, class_code: str, class_name: str):
        self.class_code = class_code
        self.class_name = class_name
        self.assessments = []

    def add_assessment(self, assessment: Assessment):
        self.assessments.append(assessment)

# --- PERSISTÊNCIA ---
class TxtAssessmentRepository:
    def save(self, academic_class: AcademicClass, filename: str):
        with open(filename, 'w', encoding='utf-8') as f:
            for a in academic_class.assessments:
                f.write(f"{type(a).__name__};{a.name};{a.weight}\n")
        print(f"Salvo em TXT com sucesso: {filename}")

class XmlAssessmentRepository:
    def save(self, academic_class: AcademicClass, filename: str):
        with open(filename, 'w', encoding='utf-8') as f:
            f.write("<academicClass>\n")
            f.write(f"  <name>{academic_class.class_name}</name>\n")
            f.write("  <assessments>\n")
            for a in academic_class.assessments:
                f.write("    <assessment>\n")
                f.write(f"      <type>{type(a).__name__}</type>\n")
                f.write(f"      <name>{a.name}</name>\n")
                f.write(f"      <weight>{a.weight}</weight>\n")
                f.write("    </assessment>\n")
            f.write("  </assessments>\n")
            f.write("</academicClass>")
        print(f"Salvo em XML com sucesso: {filename}")

class JsonAssessmentRepository:
    def save(self, academic_class: AcademicClass, filename: str):
        with open(filename, 'w', encoding='utf-8') as f:
            f.write("{\n")
            f.write(f"  \"className\": \"{academic_class.class_name}\",\n")
            f.write("  \"assessments\": [\n")
            for i, a in enumerate(academic_class.assessments):
                f.write("    {\n")
                f.write(f"      \"type\": \"{type(a).__name__}\",\n")
                f.write(f"      \"name\": \"{a.name}\",\n")
                f.write(f"      \"weight\": {a.weight}\n")
                if i == len(academic_class.assessments) - 1:
                    f.write("    }\n")
                else:
                    f.write("    },\n")
            f.write("  ]\n}")
        print(f"Salvo em JSON com sucesso: {filename}")

class PersistenceManager:
    def __init__(self):
        self.current_storage_type = "TXT"

    def set_storage_type(self, storage_type: str):
        upper = storage_type.upper().strip()
        if upper in ["TXT", "XML", "JSON"]:
            self.current_storage_type = upper
            print(f"Formato alterado para: {upper}")
        else:
            print("Formato inválido!")

    def save(self, academic_class: AcademicClass, base_filename: str):
        if self.current_storage_type == "TXT":
            TxtAssessmentRepository().save(academic_class, base_filename + ".txt")
        elif self.current_storage_type == "XML":
            XmlAssessmentRepository().save(academic_class, base_filename + ".xml")
        elif self.current_storage_type == "JSON":
            JsonAssessmentRepository().save(academic_class, base_filename + ".json")

# --- RELATÓRIOS ---
class AcademicReportService:
    def generate_summary_report(self, academic_class: AcademicClass):
        if not academic_class:
            print("Nenhuma turma cadastrada.")
            return
        print("\n=== RELATÓRIO: RESUMO DA TURMA ===")
        print(f"Turma: {academic_class.class_name} ({academic_class.class_code})")
        print(f"Total de Avaliações: {len(academic_class.assessments)}")

    def generate_weight_report(self, academic_class: AcademicClass):
        if not academic_class:
            print("Nenhuma turma cadastrada.")
            return
        print("\n=== RELATÓRIO: PESOS DAS AVALIAÇÕES ===")
        total = 0.0
        for a in academic_class.assessments:
            print(f"- {a.name} ({type(a).__name__}): Peso {a.weight}")
            total += a.weight
        print(f"SOMA TOTAL DOS PESOS: {total}")

# --- MENU PRINCIPAL ---
def main():
    manager = PersistenceManager()
    report_service = AcademicReportService()
    current_class = None

    while True:
        print("\n========================================")
        print("   SISTEMA ACADÊMICO - MENU PYTHON (MIGRADO) ")
        print("========================================")
        print("1 - Cadastrar Turma e Avaliações")
        print("2 - Escolher Formato de Salvamento (TXT/XML/JSON)")
        print("3 - Salvar Dados da Turma Atual")
        print("4 - Gerar Resumo da Turma")
        print("5 - Gerar Relatório de Pesos")
        print("0 - Sair")
        option = input("Escolha uma opção: ").strip()

        if option == "1":
            code = input("Digite o código da turma: ")
            name = input("Digite o nome da turma: ")
            current_class = AcademicClass(code, name)
            
            while True:
                resp = input("\nDeseja adicionar uma avaliação? (S/N): ").upper().strip()
                if resp == "N":
                    break
                if resp != "S":
                    print("Opção inválida!")
                    continue
                print("1-Exam, 2-PracticalAssignment, 3-Seminar")
                t = input("Tipo: ")
                a_name = input("Nome da avaliação: ")
                try:
                    weight = float(input("Peso: "))
                except ValueError:
                    print("Peso inválido! Usando 0.0")
                    weight = 0.0
                
                if t == "1": a = Exam(a_name, weight)
                elif t == "2": a = PracticalAssignment(a_name, weight)
                else: a = Seminar(a_name, weight)
                current_class.add_assessment(a)
        
        elif option == "2":
            fmt = input("Digite o formato (TXT, XML, JSON): ")
            manager.set_storage_type(fmt)
        elif option == "3":
            if not current_class:
                print("Cadastre uma turma primeiro.")
            else:
                base = input("Nome base do arquivo (sem extensão): ")
                manager.save(current_class, base)
        elif option == "4":
            report_service.generate_summary_report(current_class)
        elif option == "5":
            report_service.generate_weight_report(current_class)
        elif option == "0":
            print("Saindo do sistema migrado.")
            break
        else:
            print("Opção inválida.")

if __name__ == "__main__":
    main()
