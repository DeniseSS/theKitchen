package com.theKitchen.model.entity;

import java.time.LocalDate;

public class Funcionario extends AbstractEntity {

    private String nomeFuncionario;
    private String telefone;
    private String cpf;
    private String cargo;
    private LocalDate dataNascimento;
    private String sobrenome; 

    public Funcionario(String nomeFuncionario, String telefone, String cpf, String cargo, LocalDate dataNascimento, String sobrenome) {
        this.nomeFuncionario = nomeFuncionario;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cargo = cargo;
        this.dataNascimento = dataNascimento;
        this.sobrenome = sobrenome; 
    }

    public Funcionario(int id, String nomeFuncionario, String telefone, String cpf, String cargo, LocalDate dataNascimento, String sobrenome) {
        super.setId(id);
        this.nomeFuncionario = nomeFuncionario;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cargo = cargo;
        this.dataNascimento = dataNascimento;
        this.sobrenome = sobrenome; 
    }



    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
