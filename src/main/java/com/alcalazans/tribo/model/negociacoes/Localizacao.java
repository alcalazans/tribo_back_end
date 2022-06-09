package com.alcalazans.tribo.model.negociacoes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Localizacao {

    private String latitude;
    private String longitude;

    private Logradouro logradouro;
}
