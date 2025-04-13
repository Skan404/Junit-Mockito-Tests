package org.example;

public class Main {
    public static void main(String[] args) {
        MageRepository repository = new MageRepository();
        MageController controller = new MageController(repository);

        System.out.println(controller.save("Gandalf", 10)); //"done"
        System.out.println(controller.save("Saruman", 8)); // "done"

        System.out.println(controller.save("Gandalf", 15)); // "bad request"

        System.out.println(controller.find("Gandalf")); // "Found: Gandalf, level: 100"
        System.out.println(controller.find("Radagast")); // "not found"

        // Usuwanie maga
        System.out.println(controller.delete("Saruman")); //  "done"

        // Próba usunięcia nieistniejącego maga
        System.out.println(controller.delete("Saruman")); //  "not found"
    }
}
