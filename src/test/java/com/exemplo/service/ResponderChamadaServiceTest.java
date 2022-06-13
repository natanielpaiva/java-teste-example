package com.exemplo.service;

import com.exemplo.TestBase;
import com.exemplo.domain.Aluno;
import com.exemplo.domain.Chamada;
import com.exemplo.domain.HistoricoChamadaAluno;
import com.exemplo.domain.Mensagens;
import com.exemplo.exceptions.ValidacaoException;
import com.exemplo.repository.ChamadaRepository;
import com.exemplo.repository.HistoricoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.time.LocalDate;

class ResponderChamadaServiceTest extends TestBase {

    Chamada chamada;

    Aluno aluno;

    @Spy
    HistoricoChamadaAluno historicoChamadaAluno;

    @Mock
    ChamadaRepository chamadaRepository;

    @Mock
    HistoricoRepository historicoRepository;

    @InjectMocks
    ResponderChamadaService responderChamadaService;

    @BeforeEach
    public void setUp(){
        aluno = new Aluno();
        aluno.setNome("Nataniel Paiva");
        aluno.setStatusMatricula(false);
        chamada = new Chamada();
        chamada.setAluno(aluno);
        chamada.setData(LocalDate.now());
        historicoChamadaAluno.setChamada(chamada);
        historicoChamadaAluno.setAluno(aluno);
    }

    @Test()
    void verificaAlunoNaoMatriculado(){
        var validacao = Assertions.assertThrows(
                ValidacaoException.class,
                ()-> responderChamadaService.apply(chamada)
        );

        Assertions.assertEquals(Mensagens.ALUNO_NAO_MATRICULADO.getMensagem(), validacao.getMessage());
    }

    @Test
    void verificaDataChamadaInvalida(){
        aluno.setStatusMatricula(true);
        chamada.setAluno(aluno);
        chamada.setData(LocalDate.now().plusDays(1));
        var validacao = Assertions.assertThrows(
                ValidacaoException.class,
                ()-> responderChamadaService.apply(chamada)
        );

        Assertions.assertEquals(Mensagens.REGISTRO_EM_DATA_FUTURA.getMensagem(), validacao.getMessage());

    }

    @Test
    void alunoSalvoComSucesso() throws ValidacaoException {
        aluno.setStatusMatricula(true);
        chamada.setAluno(aluno);
        Mockito.when(chamadaRepository.salvar(chamada)).thenReturn(chamada);
        var result = responderChamadaService.apply(chamada);

        Mockito.verify(historicoRepository).salvar(chamada);

        Assertions.assertEquals(chamada.getData(), result.getData());
    }



}
