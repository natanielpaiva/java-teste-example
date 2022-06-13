package com.exemplo.repository;

import com.exemplo.domain.Chamada;
import com.exemplo.domain.HistoricoChamadaAluno;

import java.util.ArrayList;
import java.util.List;

public class HistoricoRepository {

    private List<HistoricoChamadaAluno> historicoChamadaAlunos = new ArrayList<>();

    public void salvar(Chamada chamada){
        var historicoChamadaAluno = new HistoricoChamadaAluno(chamada.getAluno(), chamada);
        historicoChamadaAlunos.add(historicoChamadaAluno);
    }

}
