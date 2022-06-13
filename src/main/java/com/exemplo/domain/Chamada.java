package com.exemplo.domain;

import com.exemplo.exceptions.ValidacaoException;

import java.time.LocalDate;

public class Chamada {

    private Aluno aluno;

    private boolean presente;

    private LocalDate data;

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void validaDataFutura() throws ValidacaoException {
        if(this.data.isAfter(LocalDate.now())){
            throw new ValidacaoException(Mensagens.REGISTRO_EM_DATA_FUTURA.getMensagem());
        }
    }
}
