package br.com.cm.workshop.apicrud.models;

import lombok.Data;

@Data
public class Status {
    private String status;

    public Status(){
        this.status="PENDENTE";
    }

    public Status(String status) {
        this.status = status;
    }

}
