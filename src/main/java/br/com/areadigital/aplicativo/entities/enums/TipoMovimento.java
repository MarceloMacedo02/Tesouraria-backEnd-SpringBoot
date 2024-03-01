package br.com.areadigital.aplicativo.entities.enums;

public enum TipoMovimento {

    ENTRADA("Entrada", 1),
    SAIDA("Saida", 2);

    private final String name;
    private final int id;

    private TipoMovimento(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static String toName(Integer id) {
        if (id == null) {
            return null;
        }
        for (TipoMovimento x : TipoMovimento.values()) {
            if (id.equals(x.getId())) {
                return x.getName();
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

    public static Integer toId(String name) {
        if (name == null) {
            return null;
        }
        for (TipoMovimento x : TipoMovimento.values()) {
            if (name.equals(x.getName())) {
                return x.getId();
            }
        }
        throw new IllegalArgumentException("Nome inválido: " + name);
    }


}