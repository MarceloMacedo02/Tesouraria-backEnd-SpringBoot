package br.com.areadigital.aplicativo.entities.enums;

public enum CargoDirtetoria {
    PRESIDENTE("Presidente", 1),
    VICEPRESIDENTE("Vice-Presidente", 2),
    SECRETARIO("Secretario", 3),
    SEGUNDOSECRETARIOS("Segundo Secretario", 4),
    TESOUREIRO("Tesoureiro", 5),
    SEGUNDOTESOUREIRO("Segundo Tesoureiro", 6),
    ORADOR("Orador", 7),
    PRESIDENTECONSELHOFISCAL("Presidente Conselheiro Fiscal", 8),
    SEGUNDOMENBROCONSELHOFISCAL("Segundo Membro Conselheiro Fiscal", 9),
    TERCEROMENBROCONSELHOFISCAL("Terceiro Membros Conselheiro Fiscal", 10),
    PRIMEIROSUPLENTECONSELHOFISCAL("Primeiro Suplente Conselheiro Fiscal", 11),
    SEGUNDOSUPLENTECONSELHOFISCAL("Segundo Suplente Conselheiro Fiscal", 12),
    TERCEROSUPLENTECONSELHOFISCAL("Terceiro Suplente Conselheiro Fiscal", 13);

    private final String name;
    private final int id;

    private CargoDirtetoria(String name, int id) {
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
        for (CargoDirtetoria e : CargoDirtetoria.values()) {
            if (e.getId() == id) {
                return e.getName();
            }
        }
        return null;
    }

    public static int returnId(String name) {
        for (CargoDirtetoria e : CargoDirtetoria.values()) {
            if (e.getName().equals(name)) {
                return e.getId();
            }
        }
        return 0;
    }
}