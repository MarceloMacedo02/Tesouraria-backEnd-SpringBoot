package br.com.areadigital.aplicativo.entities.enums;

public enum TipoEntradaFicha {
    MENSALIDADE("Mensalidade", 1),
    DOACAO("Doacao", 2),
    OUTRASENTRADAS("Outras Entradas", 3);

    private final String name;
    private final int id;

    private TipoEntradaFicha(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;

    }

    public int getId() {
        return id;
    }

    public static TipoEntradaFicha getById(int id) {
        for (TipoEntradaFicha e : TipoEntradaFicha.values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public static String returnName(int id) {
        for (TipoEntradaFicha e : TipoEntradaFicha.values()) {
            if (e.getId() == id) {
                return e.getName();
            }

        }
        return null;
    }

    public static int returnId(String name) {

        for (TipoEntradaFicha e : TipoEntradaFicha.values()) {
            if (e.getName().equals(name)) {
                return e.getId();
            }
        }
        return 0;
    }
}
