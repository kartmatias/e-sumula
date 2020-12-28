package br.com.sumulaesportiva;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import br.com.sumulaesportiva.entities.Calendario;
import br.com.sumulaesportiva.entities.Equipe;
import br.com.sumulaesportiva.entities.Liga;
import br.com.sumulaesportiva.entities.Modalidade;
import br.com.sumulaesportiva.entities.Partida;
import br.com.sumulaesportiva.entities.Pessoa;
import br.com.sumulaesportiva.entities.Ponto;
import br.com.sumulaesportiva.entities.Punicao;
import br.com.sumulaesportiva.entities.Sumula;
import br.com.sumulaesportiva.entities.Tempo;
import br.com.sumulaesportiva.entities.Test;
import br.com.sumulaesportiva.repositories.CalendarioRepository;
import br.com.sumulaesportiva.repositories.EquipeRespository;
import br.com.sumulaesportiva.repositories.LigaRepository;
import br.com.sumulaesportiva.repositories.ModalidadeRepository;
import br.com.sumulaesportiva.repositories.PartidaRepository;
import br.com.sumulaesportiva.repositories.PessoaRepository;
import br.com.sumulaesportiva.repositories.SumulaRepository;
import br.com.sumulaesportiva.repositories.TestRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private TestRepository testRepository;
	@Autowired
	private SumulaRepository sumulaRepository;
	@Autowired
	private PartidaRepository partidaRepository;
	@Autowired
	private EquipeRespository equipeRepository;
	@Autowired
	private ModalidadeRepository modalidadeRepository;
	@Autowired
	private CalendarioRepository calendarioRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private LigaRepository ligaRepository;

	@Override
	public void run(ApplicationArguments arg) throws Exception {
		Test test = new Test();
		test.setName("Eduardo");
		testRepository.save(test);

		addSumulaDefaultData();
		Pessoa pessoa = addPessoaDefaultData();
		Modalidade modalidade = new Modalidade();
		Equipe equipe = addEquipeDefaultData(pessoa, modalidade);
		addCalendarioDefaultData(getFirstPartida());
		addLiga(equipe, modalidade);

	}

	private void addLiga(Equipe equipe, Modalidade modalidade) {

		Liga liga = new Liga();
		liga.setNome("Brasileiro");
		liga.setData(new Date());
		liga.setModalidade(modalidade);
		liga.setCabecaDeChave(equipe);
		ligaRepository.save(liga);

	}

	private Pessoa addPessoaDefaultData() {
		Pessoa pessoa = new Pessoa();

		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(Calendar.DAY_OF_MONTH, 18);
		dataNascimento.set(Calendar.MONTH, 9);
		dataNascimento.set(Calendar.YEAR, 1994);
		pessoa.setDataNascimento(dataNascimento.getTime());
		pessoa.setEmail("email@email.com");
		pessoa.setEndereco("Rua de testes, bairro testes cidade...");
		pessoa.setNome("Eduardo");
		pessoa.setRG("87151980");
		pessoa.setSexo('M');
		pessoa.setTelefone("99958978");

		pessoa = pessoaRepository.save(pessoa);
		return pessoa;
	}

	private void addCalendarioDefaultData(Partida anyPartida) {
		if (anyPartida == null) {
			throw new RuntimeException("Erro ao buscar a primeira partida.");
		}

		Calendario calendario = new Calendario();
		calendario.setDataInicio(new Date());
		calendario.setDataFim(new Date());
		calendario.setPartidas(Lists.newArrayList(anyPartida));

		calendarioRepository.save(calendario);
	}

	private Partida getFirstPartida() {
		Iterable<Partida> partidas = partidaRepository.findAll();
		Iterator<Partida> iteratorPartidas = partidas.iterator();
		Partida partida = null;
		if (iteratorPartidas.hasNext())
			partida = iteratorPartidas.next();
		return partida;
	}

	private Equipe addEquipeDefaultData(Pessoa pessoa, Modalidade modalidade) {
		Equipe equipe = new Equipe();
		equipe.setNome("Brasil");

		equipe.setModalidade(modalidade);
		modalidade.setDescricao("Futebol");

		Tempo tempo = new Tempo();
		tempo.setDuracaoExtra(15);
		tempo.setDuracaoPeriodos(45);
		tempo.setDuracaoTimeout(0);
		tempo.setQuantidadePeriodos(2);
		tempo.setQuantidadeTemposExtras(2);
		tempo.setQuantidadeTimeout(0);

		modalidade.setTempo(tempo);
		tempo.setModalidade(modalidade);

		Ponto ponto = new Ponto();
		ponto.setDescricao("Gol");
		ponto.setValor(1);

		modalidade.setTiposPonto(Lists.newArrayList(ponto));

		Punicao cartaoAmarelo = new Punicao();
		cartaoAmarelo.setTipo("Cartão amarelo");
		cartaoAmarelo.setDescricao("Punição aplicada a um jogador que comete uma falta razoavelmente grave. Duas punições dessa obrigam o árbitro a aplicar a punição do cartão vermelho.");

		Punicao cartaoVermelho = new Punicao();
		cartaoVermelho.setTipo("Cartão vermelho");
		cartaoVermelho.setDescricao("Punição aplicada a um jogador que comete uma falta grave. Acarreta na expulsão imediata do jogador.");

		modalidade.setTiposPunicao(Lists.newArrayList(cartaoAmarelo, cartaoVermelho));

		modalidadeRepository.save(modalidade);

		pessoa.setEquipe(equipe);
		equipe.getIntegrantes().add(pessoa);
		equipeRepository.save(equipe);
		pessoaRepository.save(pessoa);

		return equipe;

	}

	private void addSumulaDefaultData() {
		Sumula sumula = new Sumula();

		Partida partida = new Partida();
		partida.setData(new Date());
		partida.setLocal("Maracanã");

		sumula.setPartida(partida);

		partidaRepository.save(partida);
		sumulaRepository.save(sumula);
	}

}
