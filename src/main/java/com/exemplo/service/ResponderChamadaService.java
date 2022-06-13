package com.exemplo.service;

import com.exemplo.domain.Chamada;
import com.exemplo.exceptions.ValidacaoException;
import com.exemplo.repository.ChamadaRepository;
import com.exemplo.repository.HistoricoRepository;

public class ResponderChamadaService {

    private ChamadaRepository chamadaRepository;

    private HistoricoRepository historicoRepository;

    public ResponderChamadaService(ChamadaRepository chamadaRepository, HistoricoRepository historicoRepository) {
        this.chamadaRepository = chamadaRepository;
        this.historicoRepository = historicoRepository;
    }

    public Chamada apply(Chamada chamada) throws ValidacaoException {
        chamada.getAluno().verificaAlunoMatriculado();
        chamada.validaDataFutura();

        var chamadaSalva = chamadaRepository.salvar(chamada);
        historicoRepository.salvar(chamada);

        return chamadaSalva;
    }

}
