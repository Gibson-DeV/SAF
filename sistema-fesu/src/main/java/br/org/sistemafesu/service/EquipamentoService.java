package br.org.sistemafesu.service;

import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Equipamento;
import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.repository.EquipamentoRepository;
import br.org.sistemafesu.repository.LocacaoRepository;
import jakarta.transaction.Transactional;

@Service
public class EquipamentoService extends AbstractService<Equipamento, EquipamentoRepository> {
    private final LocacaoRepository locacaoRepository;

    public EquipamentoService(Equipamento equipamento, EquipamentoRepository equipamentoRepository, LocacaoRepository locacaoRepository) {
        super(equipamento, equipamentoRepository);

        this.locacaoRepository = locacaoRepository;
    }

    @Override
    public Equipamento update(Long id, Equipamento model) {
        if (model.getId() == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Sala não encontrada!");
        }

        return super.save(model);
    }

    @Transactional
    public void deleteLocacao(Long id) {
        Locacao locacao = locacaoRepository.findById(id).orElse(null);

        if (locacao != null) {
            for (Equipamento equipamento : locacao.getEquipamentos()) {
                equipamento.setLocacao(null);
                repository.save(equipamento); // Atualize o equipamento para refletir a remoção da referência
                                                         // à locação
            }

            super.deleteById(id);
        }
    }
}
