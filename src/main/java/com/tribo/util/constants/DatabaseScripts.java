package com.tribo.util.constants;

public final class DatabaseScripts {

    private DatabaseScripts() {
    }

    public static final String CREATE_DDL = "classpath:db/scripts/estrutura.sql";

    public static final String INSERT_BASICOS = "classpath:db/scripts/inserts.sql";

    public static final String DROP_DDL = "classpath:db/scripts/drops.sql";

}
