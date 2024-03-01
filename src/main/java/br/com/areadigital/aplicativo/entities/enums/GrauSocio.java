package br.com.areadigital.aplicativo.entities.enums;

public enum GrauSocio {
    MESTREREPRESENTANTE("Mestre Representante", 1),
    MESTRE("Mestre", 2),
    CONSELHEIRO("Conselheiro", 3),
    DISCIPULO("Discipulo", 4);

    private final String name;
    private final int id;

    private GrauSocio(String name, int id) {
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
        for (GrauSocio e : GrauSocio.values()) {
            if (e.getId() == id) {
                return e.getName();
            }
        }
        return null;

    }

    public static int returnId(String name) {
        for (GrauSocio e : GrauSocio.values()) {
            if (e.getName().equals(name)) {
                return e.getId();
            }
        }
        return 0;
    }
}