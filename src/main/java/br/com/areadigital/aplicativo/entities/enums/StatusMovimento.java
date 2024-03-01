package br.com.areadigital.aplicativo.entities.enums;

public enum StatusMovimento {

    ABERTO("Aberto", 1),
    QUITADO("Quitado", 2);

    private final String name;
    private final int id;

    private StatusMovimento(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static String returnName(int id) {
        for (StatusMovimento e : StatusMovimento.values()) {
            if (e.getId() == id) {
                return e.getName();
            }
        }
        return null;
    }

    public static int returnId(String name) {
        for (StatusMovimento e : StatusMovimento.values()) {
            if (e.getName().equals(name)) {
                return e.getId();
            }
        }
        return 0;
    }
}