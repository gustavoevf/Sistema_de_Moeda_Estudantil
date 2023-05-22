package com.api.moedaestudantil.services;

import com.api.moedaestudantil.models.AlunoModel;
import com.api.moedaestudantil.models.AlunoVantagemModel;
import com.api.moedaestudantil.models.VantagemModel;
import com.api.moedaestudantil.repositories.AlunoRepository;
import com.api.moedaestudantil.repositories.AlunoVantagemRepository;
import com.api.moedaestudantil.repositories.VantagemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoVantagemService {

  /*  @Autowired
    private JavaMailSender mailSender;*/
    final AlunoVantagemRepository alunoVantagemRepository;
    private final AlunoRepository alunoRepository;

    private final VantagemRepository vantagemRepository;

    final EmailService emailService = new EmailService();

    public AlunoVantagemService(AlunoVantagemRepository alunoVantagemRepository, AlunoRepository alunoRepository,
                                VantagemRepository vantagemRepository) {
        this.alunoVantagemRepository = alunoVantagemRepository;
        this.alunoRepository = alunoRepository;
        this.vantagemRepository = vantagemRepository;
    }


    /*public void sendMail()  {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setText("Hello from Spring Boot Application");
        message.setTo("pedro.reis@meta3.com.br");
        message.setFrom("preissouza@gmail.com");

        mailSender.send(message);
    }*/

    @Transactional
    public AlunoVantagemModel save(AlunoVantagemModel alunoVantagemModel) {
        Optional<AlunoModel> alunoOpt = alunoRepository.findById(alunoVantagemModel .getAluno().getId());
        Optional<VantagemModel> vantagemOpt = vantagemRepository.findById(alunoVantagemModel.getVantagem().getId());
        System.out.println(emailService.sendEmail("pedro.reis@meta3.com.br",
                "Teste", "oi"));
        if (alunoOpt.isPresent() && vantagemOpt.isPresent()) {
            AlunoModel aluno = alunoOpt.get();
            VantagemModel vantagem = vantagemOpt.get();

            if (aluno.getValorCarteira() < vantagem.getCusto()) {
                throw new RuntimeException("Saldo insuficiente");
            }

            alunoVantagemModel.setAluno(aluno);
            alunoVantagemModel.setVantagem(vantagem);

            double valor = vantagem.getCusto();
            aluno.setValorCarteira(aluno.getValorCarteira() - valor);

            alunoRepository.save(aluno);

            Optional<AlunoVantagemModel> save = Optional.ofNullable(alunoVantagemRepository.save(alunoVantagemModel));
            return save.orElseGet(AlunoVantagemModel::new);


        } else {
            throw new RuntimeException("Remetente ou destinatário não encontrado");
        }
    }

    public Page<AlunoVantagemModel> findAll(Pageable pageable) {
        return alunoVantagemRepository.findAll(pageable);
    }

    public Optional<AlunoVantagemModel> findById(UUID id) {
        return alunoVantagemRepository.findById(id);
    }

    public List<AlunoVantagemModel> findByAluno(UUID aluno) {
        return alunoVantagemRepository.findByAluno(aluno);
    }

    @Transactional
    public void delete(AlunoVantagemModel alunoVantagemModel) {
        alunoVantagemRepository.delete(alunoVantagemModel);
    }

}