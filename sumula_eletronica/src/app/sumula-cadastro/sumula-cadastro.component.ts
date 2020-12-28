import { Component, OnInit, ɵConsole } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm, NgModel } from '@angular/forms';
import { ClubeService } from '../services/clube.service';
import { ArbitroService } from '../services/arbitro.service';
import { AtletaService } from '../services/atleta.service';
import { SumulaService } from '../services/sumula.service';
import { Clube } from '../models/clube';
import { Arbitro } from '../models/arbitro';
import { Atleta } from '../models/atleta';
import { Escalacao } from '../models/escalacao';
import { Relacao } from '../models/relacao';
import { HeaderComponent } from '../header/header.component';
import { LoginService } from '../services/login.service';
import { Substituicao } from '../models/substituicao';
import { Comissao } from '../models/comissao';
import { CreateSumula } from '../models/createSumula';
import { Cronologia } from '../models/cronologia';
import { CreateEscalacao} from '../models/createEscalacao';
import { CreateRelacao } from '../models/createRelacao';
import { Cep } from '../models/cep';
import { CepService } from '../services/cep.service';


@Component({
  selector: 'app-sumula-cadastro',
  templateUrl: './sumula-cadastro.component.html',
  styleUrls: ['./sumula-cadastro.component.css']
})
export class SumulaCadastroComponent implements OnInit {
  sumula: CreateSumula = new CreateSumula();
  clubes: Clube[] = [];
  arbitros: Arbitro[] = [];
  atletasMandante: Atleta[] = [];
  atletasVisitante: Atleta[] = [];
  placarMandante: Number = 0;
  placarVisitante: Number = 0;
  endereco: Cep = new Cep();

  escalacaoMandante: Escalacao = new Escalacao();
  escalacaoVisitante: Escalacao = new Escalacao();
  relacoesMandante: Relacao[] = [];
  relacoesVisitante: Relacao[] = [];
  inserirRelacaoMandante: Relacao = new Relacao();
  inserirRelacaoVisitante: Relacao = new Relacao();

  substituicoes: Substituicao[] = [];
  substituicoesMandante: Substituicao[] = [];
  substituicoesVisitante: Substituicao[] = [];

  inserirSubstituicaoMandante: Substituicao = new Substituicao();
  inserirSubstituicaoVisitante: Substituicao = new Substituicao();

  comissaoMandante: Comissao = new Comissao();
  comissaoVisitante: Comissao = new Comissao();

  cronologia: Cronologia = new Cronologia();
  arbitro: Arbitro = new Arbitro();

  createEscalacao: CreateEscalacao = new CreateEscalacao();


  constructor(private router: Router, private clubeService: ClubeService,
    private arbitroService: ArbitroService, private atletaService: AtletaService,
    private sumulaService: SumulaService, private header: HeaderComponent,
    private loginService: LoginService, private cepService: CepService) { }

/*==============================================================================
NG ON INIT
==============================================================================*/
  ngOnInit() 
  {
    this.inicializaSumula(); //Inicializa variáveis da súmula
    this.getAssistentes(); //Busca opções de assistentes
    this.getClubes(); //Busca opções de clubes
    this.inicializaSumula(); //Inicializa variáveis da súmula
  }
/*==============================================================================
ON SUBIMIT
==============================================================================*/
  onSubmit(formulario: NgForm)
  {
    if(formulario.valid)
    {
      if(!this.validaSumula())
      {
        alert("Há campos com erros");
        return;
      }

      //Objeto para criar escalação no banco de dados
      this.createEscalacao = new CreateEscalacao();
      this.createEscalacao.id = 0;

      //Monta a escalação completa com todas as relações
      this.escalacaoMandante.relacoes = this.relacoesMandante; 
      this.escalacaoVisitante.relacoes = this.relacoesVisitante;

      //Salva a sumula e todos os seus componentes
      this.cadastraSumula();

    }
  }
/*==============================================================================
INFORMA ERRO DE CADASTRO DE SÚMULA
==============================================================================*/
informaErroCadastro(dados: any)
{
  alert("Erro ao cadastrar súmula.") 
}
/*==============================================================================
CRIA A CLASSE CREATE RELACAO
==============================================================================*/
toCreateRelacao(relacoes: Relacao[], idEscalacao: String): CreateRelacao[]
{
  const createRelacoes: CreateRelacao[] = [];

  relacoes.forEach(relacao => {
    const createRelacao = new CreateRelacao();
  createRelacao.id = 0;
  createRelacao.atleta = relacao.idAtleta;
  createRelacao.numero = relacao.numero;
  createRelacao.gol = relacao.gol;
  createRelacao.titular = relacao.titular;
  createRelacao.cartoes = relacao.cartoes;
  createRelacao.escalacao = idEscalacao;

  createRelacoes.push(createRelacao);
  })

  return createRelacoes;
}
/*==============================================================================
TROCA O CLUBE MANDANTE
==============================================================================*/
  changeClubeMandante(idMandante: NgModel)
  {
    console.log("Trocando Local da Partida");
    console.log(idMandante.value);

    //Busca nome do estádio
    if(this.sumula.clubeMandante != "null")
    {
      let clube: Clube;
      clube = this.clubes.find(clube => clube.id == idMandante.value);
      this.sumula.local = clube.estadio; 
      this.buscaCep(clube);
    }
    else 
    {
      this.sumula.local = "";
    }

    this.getAtletasByClubes(); //Busca os atletas da equipe mandante
    this.inicializaInserirRelacaoMandante(); //Reinicia todos os atletas mandantes
    this.relacoesMandante = []; //Apaga Lista de Atletas
  }
/*==============================================================================
TROCA O CLUBE VISITANTE
==============================================================================*/
  changeClubeVisitante(idVisitante: NgModel)
  {
    console.log("Trocando Local da Partida");
    console.log(idVisitante.value);

    this.getAtletasByClubes(); //Busca os atletas da equipe visitante
    this.inicializaInserirRelacaoVisitante(); //Reinicia todos os atletas visitantes
    this.relacoesVisitante = []; //Apaga Lista de Atletas
  }
/*==============================================================================
ON CLICK - INSERIR ATLETA CLUBE MANDANTE
==============================================================================*/
  onClickInserirRelacaoMandante()
  {
    //Cria um objeto Relação para verificar se já existe um atleta com esse número
    const obj: Relacao = this.relacoesMandante.find
    (relacao => relacao.numero == this.inserirRelacaoMandante.numero);

    console.log("Id do atleta");
    console.log(this.inserirRelacaoMandante.idAtleta);

    //Prossegue com a inserção se: 
    //O Atleta for válido
    //O Número da camisa dele for maior que 0
    //Se a quantidade de gols que ele fez por maior ou igual a 0
    //E se nenhuma tleta com o mesmo número dele está inserido nessa lista
    if(this.inserirRelacaoMandante.idAtleta != "null"
    && this.inserirRelacaoMandante.numero > 0
    && this.inserirRelacaoMandante.gol >= 0
    && !obj)
    {
      //Busca o nome pelo seu id
      this.inserirRelacaoMandante.nome = this.atletasMandante.find(
        atleta => atleta.id == this.inserirRelacaoMandante.idAtleta).nome;
      
      console.log("Nome encontrado");
      console.log(this.inserirRelacaoMandante.nome);
   
      this.relacoesMandante.push(this.inserirRelacaoMandante); //Coloca na relação
      this.inserirCartoesMandante(); //Insere os cartões
      this.atualizaPlacarFinal(); //Atualiza o placar
      this.inicializaInserirRelacaoMandante(); //Reinicia os valores para inserção de atleta
      return;
    }
    console.log("Nenhum Selecionado")
    alert("Verifique os campos de inserção e tente novamente"); //Informa o erro
  }
/*==============================================================================
ON CLICK - EXCLUIR ATLETA DA EQUIPE MANDANTE
==============================================================================*/
  onClickExcluirRelacaoMandante(apagar: any)
  {
    console.log("Registro para remoção recebido");
    console.log(apagar);

    //Busca o atleta que será apagado da relação com base no número da camisa
    const obj: Relacao = this.relacoesMandante.find(relacao => relacao.numero == apagar);
    //Busca a posição na array
    const index: number = this.relacoesMandante.indexOf(obj);

    //Foi encontrado
    if (index !== -1) 
    {
      this.relacoesMandante.splice(index, 1); //Apaga
      this.atualizaPlacarFinal(); //Atualiza o placar final
    } 
  }
/*==============================================================================
ON CLICK - INSERIR ATLETA EQUIPE VISITANTE
==============================================================================*/
  onClickInserirRelacaoVisitante()
  {
    //Cria um objeto Relação para verificar se já existe um atleta com esse número
    const obj: Relacao = this.relacoesVisitante.find
    (relacao => relacao.numero == this.inserirRelacaoVisitante.numero );

    //Prossegue com a inserção se: 
    //O Atleta for válido
    //O Número da camisa dele for maior que 0
    //Se a quantidade de gols que ele fez por maior ou igual a 0
    //E se nenhuma tleta com o mesmo número dele está inserido nessa lista
    if(this.inserirRelacaoVisitante.idAtleta != "null"
    && this.inserirRelacaoVisitante.numero > 0
    && this.inserirRelacaoVisitante.gol >= 0
    && !obj)
    {
      console.log("Atleta Válido");

      //Busca o nome pelo seu id
      this.inserirRelacaoVisitante.nome = this.atletasVisitante.find(
        atleta => atleta.id == this.inserirRelacaoVisitante.idAtleta).nome;

      this.relacoesVisitante.push(this.inserirRelacaoVisitante); //Remove da lista
      this.inserirCartoesVisitante();  //Insere os cartões
      this.atualizaPlacarFinal(); //Atualiza placar final
      this.inicializaInserirRelacaoVisitante(); //Reinicia as variáveis de inserção
      return;
    }
    console.log("Nenhum Selecionado")
    alert("Verifique os campos de inserção e tente novamente");
  }
/*==============================================================================
ON CLICK - EXCLUIR ATLETA EQUIPE VISITANTE
==============================================================================*/
  onClickExcluirRelacaoVisitante(apagar: any)
  {
    console.log("Registro para remoção recebido");
    console.log(apagar);

    //Busca o atleta pelo número da camisa
    const obj: Relacao = this.relacoesVisitante.find(relacao => relacao.numero == apagar);
    //Busca a sua posição na array
    const index: number = this.relacoesVisitante.indexOf(obj);

    //Posição válida
    if (index !== -1) 
    {
      this.relacoesVisitante.splice(index, 1); //Remove
      this.atualizaPlacarFinal(); //Atualiza placar final
    } 
  }
/*==============================================================================
ON CLICK - INSERIR SUBSTITUIÇÃO MANDANTE
==============================================================================*/
  onClickInserirSubstituicaoMandante()
  {
    //Verifica se já não foi inserido em entrou ou em saiu
    const verificaEntrou: Substituicao = this.substituicoesMandante.find
    (sub => sub.entra == this.inserirSubstituicaoMandante.entra);
    const verificaSaiu: Substituicao = this.substituicoesMandante.find
    (sub => sub.sai == this.inserirSubstituicaoMandante.sai);

    console.log("Substituicao recebida");
    console.log(this.inserirSubstituicaoMandante);

    //Verifica se quem entrou, saiu e o tempo são maior ou igual a 1
    //Verifica se periodo não é nulo
    //Verifica se entrou e saiu são diferentes e se não se repetiram
    if(this.inserirSubstituicaoMandante.entra >= 1 &&
      this.inserirSubstituicaoMandante.sai >= 1 &&
      this.inserirSubstituicaoMandante.tempo >= 1 &&
      this.inserirSubstituicaoMandante.periodo != null &&
      this.inserirSubstituicaoMandante.entra != this.inserirSubstituicaoMandante.sai &&
      !verificaEntrou && !verificaSaiu)
    {
      this.inserirSubstituicaoMandante.equipeMandante = true; //Equipe mandante
      this.substituicoesMandante.push(this.inserirSubstituicaoMandante); //Insere
      
      console.log("Subs Mandante Atualizado");
      console.log(this.substituicoesMandante);

      this.inicializaInserirSubstituicaoMandante(); //Reinicia a inserção para um novo registro
      return;
    }
    console.log("Erro nas substituição")
    alert("Verifique os campos de inserção e tente novamente"); //Informa o erro
  }
/*==============================================================================
ON CLICK - EXCLUIR ATLETA DA EQUIPE MANDANTE
==============================================================================*/
  onClickExcluirSubstituicaoMandante(numero: any)
  {
    //Busca quem será excluído pelo número de quem entra
    const excluirSubstituicao: Substituicao = 
      this.substituicoesMandante.find(subs => subs.entra == numero);
    //Busca posição no array
    const index: number = this.substituicoesMandante.indexOf(excluirSubstituicao);

    if(index >= 0) //Encontrado
    {
      this.substituicoesMandante.splice(index, 1); 
    }
  }
/*==============================================================================
ON CLICK - INSERIR SUBSTITUIÇÃO VISITANTE
==============================================================================*/
  onClickInserirSubstituicaoVisitante()
  {
    //Verifica se já não foi cadastrado quem entrou ou saiu
    const verificaEntrou: Substituicao = this.substituicoesVisitante.find
    (sub => sub.entra == this.inserirSubstituicaoVisitante.entra);
    const verificaSaiu: Substituicao = this.substituicoesVisitante.find
    (sub => sub.sai == this.inserirSubstituicaoVisitante.sai);

    console.log("Substituicao recebida");
    console.log(this.inserirSubstituicaoVisitante);

    //Verifica se quem entrou, saiu e o tempo são maior ou igual a 1
    //Verifica se periodo não é nulo
    //Verifica se entrou e saiu são diferentes e se não se repetiram

    if(this.inserirSubstituicaoVisitante.entra >= 1 &&
      this.inserirSubstituicaoVisitante.sai >= 1 &&
      this.inserirSubstituicaoVisitante.tempo >= 1 &&
      this.inserirSubstituicaoVisitante.periodo != null &&
      this.inserirSubstituicaoVisitante.entra != this.inserirSubstituicaoVisitante.sai &&
      !verificaEntrou && !verificaSaiu)
    {
      this.inserirSubstituicaoVisitante.equipeMandante = false; //Equipe visitante
      this.substituicoesVisitante.push(this.inserirSubstituicaoVisitante); //Insere
      
      console.log("Subs Visitante Atualizado");
      console.log(this.substituicoesVisitante);

      this.inicializaInserirSubstituicaoVisitante(); //Reinicia a inserção para um novo registro
      return;
    }
    console.log("Erro nas substituição")
    alert("Verifique os campos de inserção e tente novamente"); //Informa o erro
  }
/*==============================================================================
ON CLICK - EXCLUIR ATLETA DA EQUIPE VISITANTE
==============================================================================*/
  onClickExcluirSubstituicaoVisitante(numero: any)
  {
    //Busca substituição pelo número de quem entrou
    const excluirSubstituicao: Substituicao = 
      this.substituicoesVisitante.find(subs => subs.entra == numero);
    //Busca a posição no array
    const index: number = this.substituicoesVisitante.indexOf(excluirSubstituicao);

    if(index >= 0) //Encontrado
    {
      this.substituicoesVisitante.splice(index, 1);
    }
  } 
/*==============================================================================
ATUALIZA PLACAR FINAL
==============================================================================*/
  atualizaPlacarFinal()
  {
    this.placarMandante = 0; //Zera
    //Para cada atleta na relação incrementa a variável final
    this.relacoesMandante.forEach(relacao => { 
    this.placarMandante += relacao.gol;
    });

    this.placarVisitante = 0; //Zera
    //Para cada atleta na relação incrementa a variável final
    this.relacoesVisitante.forEach(relacao => {
      this.placarVisitante += relacao.gol;
    })
     
  }
/*==============================================================================
INICIA SÚMULA
==============================================================================*/
  inicializaSumula()
  {
    const arbitroLogado: Arbitro = this.loginService.getArbitroLogado();

    this.placarMandante = 0; //Placar
    this.placarVisitante = 0;

    this.sumula.clubeMandante = "null"; //Equipes
    this.sumula.clubeVisitante = "null";

    this.sumula.relatorioExpulsoes = "Nada a Relatar"; //Relatórios
    this.sumula.relatorioObservacoes = "Nada a Relatar";

    this.arbitro = arbitroLogado; //Arbitragem
    this.sumula.arbitro = arbitroLogado.id;
    this.sumula.assistente1 = "null";
    this.sumula.assistente2 = "null";

    this.inicializaComissoes();

    this.inicializaInserirRelacaoMandante(); //Inserção de atletas
    this.inicializaInserirRelacaoVisitante();

    this.relacoesMandante = []; //Listas de atletas
    this.relacoesVisitante = [];

    this.inicializaInserirSubstituicaoMandante(); //Inserção de Substituições
    this.inicializaInserirSubstituicaoVisitante();

    this.substituicoesMandante = []; //Listas de Substituições
    this.substituicoesVisitante = [];
  }
/*==============================================================================
INICIALIZA NOVO ATLETA MANDANTE
==============================================================================*/
  inicializaInserirRelacaoMandante()
  {
    this.inserirRelacaoMandante = new Relacao(); //Inicializa classe
    this.inserirRelacaoMandante.nome = "Selecionar"; //Inicializa variáveis de inserção
    this.inserirRelacaoMandante.ca = false;
    this.inserirRelacaoMandante.cvd = false;
    this.inserirRelacaoMandante.doisCa = false;
    this.inserirRelacaoMandante.gol = 0;
    this.inserirRelacaoMandante.numero = 0;
    this.inserirRelacaoMandante.titular = "Titular";
    this.inserirRelacaoMandante.idAtleta = "null";
  }
/*==============================================================================
INICIALIZA NOVO ATLETA VISITANTE
==============================================================================*/
  inicializaInserirRelacaoVisitante()
  {
    this.inserirRelacaoVisitante = new Relacao(); //Inicializa Classe
    this.inserirRelacaoVisitante.nome = "Selecionar"; //Inicializa variáveis de inserção
    this.inserirRelacaoVisitante.ca = false;
    this.inserirRelacaoVisitante.cvd = false;
    this.inserirRelacaoVisitante.doisCa = false;
    this.inserirRelacaoVisitante.gol = 0;
    this.inserirRelacaoVisitante.numero = 0;
    this.inserirRelacaoVisitante.titular = "Titular";
    this.inserirRelacaoVisitante.idAtleta = "null";
  }
/*==============================================================================
INICIALIZA NOVA SUBSTITUIÇÃO MANDANTE
==============================================================================*/
  inicializaInserirSubstituicaoMandante()
  {
    this.inserirSubstituicaoMandante = new Substituicao(); //Inicializa classe
    this.inserirSubstituicaoMandante.entra = 0; //Inicializa variáveis de inserção
    this.inserirSubstituicaoMandante.equipeMandante = false;
    this.inserirSubstituicaoMandante.periodo = "1T";
    this.inserirSubstituicaoMandante.sai = 0;
    this.inserirSubstituicaoMandante.tempo = 0;
  }
/*==============================================================================
INICIALIZA NOVA SUBSTITUIÇÃO VISITANTE
==============================================================================*/
  inicializaInserirSubstituicaoVisitante()
  {
    this.inserirSubstituicaoVisitante = new Substituicao(); //Inicializa classe
    this.inserirSubstituicaoVisitante.entra = 0; //Inicializa variáveis de inserção
    this.inserirSubstituicaoVisitante.equipeMandante = false;
    this.inserirSubstituicaoVisitante.periodo = "1T";
    this.inserirSubstituicaoVisitante.sai = 0;
    this.inserirSubstituicaoVisitante.tempo = 0;
  }
/*==============================================================================
INICIALIZA COMISSOES
==============================================================================*/
inicializaComissoes()
{
  this.comissaoMandante.auxTecnico = "";
  this.comissaoMandante.id = 0;
  this.comissaoMandante.massagista = "";
  this.comissaoMandante.medico = "";
  this.comissaoMandante.prepGoleiro = "";
  this.comissaoMandante.tecnico = "";

  this.comissaoVisitante.auxTecnico = "";
  this.comissaoVisitante.id = 0;
  this.comissaoVisitante.massagista = "";
  this.comissaoVisitante.medico = "";
  this.comissaoVisitante.prepGoleiro = "";
  this.comissaoVisitante.tecnico = "";
}
/*==============================================================================
BUSCA OS CLUBES
==============================================================================*/
  getClubes()
  {
    this.clubeService.getClubes().subscribe(dados =>
      {
        this.clubes = dados;
        console.log("Clubes recebidos do service")
        console.log(this.clubes);
      })
  }
/*==============================================================================
BUSCA OS ASSISTENTES
==============================================================================*/
  getAssistentes()
  {
    this.arbitroService.getArbitrosAssistentes().subscribe(dados => 
      {
        this.arbitros = dados;
        console.log("Arbitros assistentes recebidos do service")
        console.log(this.arbitros);
      });
  }
/*==============================================================================
BUSCA OS ATLETAS PELO CLUBE 
==============================================================================*/
  getAtletasByClubes()
  {
    //Busca os atletas filtrando por equipe

    console.log("Buscando Ateltas Mandante = " + this.sumula.clubeMandante
    + " Visitante = " + this.sumula.clubeVisitante);

    if(this.sumula.clubeMandante != "null")
    {
      this.atletaService.getAtletasByClube(this.sumula.clubeMandante).subscribe(dados =>
        this.atletasMandante = dados);
    }

    if(this.sumula.clubeVisitante != "null")
    {
      this.atletaService.getAtletasByClube(this.sumula.clubeVisitante).subscribe(dados =>
        this.atletasVisitante = dados);
      }
  }
/*==============================================================================
BUSCA CIDADE DA PARTIDA
==============================================================================*/
  buscaCep(clube: Clube)
  {
    this.endereco = new Cep();
      this.cepService.cunsultaCep(clube.cep).subscribe(dados => this.endereco = dados); 
      return;
  }
/*==============================================================================
INSERE CARTÕES NA EQUIPE MANDANTE
==============================================================================*/
  inserirCartoesMandante()
  {
    //Insere um texto com base nos boleanos de cartões, considera todas as possibilidades
    if(!this.inserirRelacaoMandante.ca && !this.inserirRelacaoMandante.doisCa && !this.inserirRelacaoMandante.cvd)
    {
      this.inserirRelacaoMandante.cartoes = "Sem Cartão";
      return;
    }

    if((this.inserirRelacaoMandante.ca && this.inserirRelacaoMandante.doisCa && this.inserirRelacaoMandante.cvd)
    ||
    (this.inserirRelacaoMandante.ca && !this.inserirRelacaoMandante.doisCa && this.inserirRelacaoMandante.cvd))
    {
      this.inserirRelacaoMandante.cartoes = "CA + CVD";
      return;
    }

    if(this.inserirRelacaoMandante.ca && !this.inserirRelacaoMandante.doisCa && !this.inserirRelacaoMandante.cvd)
    {
      this.inserirRelacaoMandante.cartoes = "CA";
      return;
    }

    if((this.inserirRelacaoMandante.ca && this.inserirRelacaoMandante.doisCa && !this.inserirRelacaoMandante.cvd)
    ||
    (!this.inserirRelacaoMandante.ca && this.inserirRelacaoMandante.doisCa && !this.inserirRelacaoMandante.cvd))
    {
      this.inserirRelacaoMandante.cartoes = "2CA";
      return;
    }

    if((!this.inserirRelacaoMandante.ca && !this.inserirRelacaoMandante.doisCa && this.inserirRelacaoMandante.cvd)    
    ||
    (!this.inserirRelacaoMandante.ca && this.inserirRelacaoMandante.doisCa && this.inserirRelacaoMandante.cvd))
    {
      this.inserirRelacaoMandante.cartoes = "CVD";
      return;
    }
  }
/*==============================================================================
INSERE CARTÕES NA EQUIPE VISITANTE
==============================================================================*/
  inserirCartoesVisitante()
  {
    //Insere um texto com base nos boleanos de cartões, considera todas as possibilidades
    if(!this.inserirRelacaoVisitante.ca && !this.inserirRelacaoVisitante.doisCa && !this.inserirRelacaoVisitante.cvd)
    {
      this.inserirRelacaoVisitante.cartoes = "Sem Cartão";
      return;
    }

    if((this.inserirRelacaoVisitante.ca && this.inserirRelacaoVisitante.doisCa && this.inserirRelacaoVisitante.cvd)
    ||
    (this.inserirRelacaoVisitante.ca && !this.inserirRelacaoVisitante.doisCa && this.inserirRelacaoVisitante.cvd))
    {
      this.inserirRelacaoVisitante.cartoes = "CA + CVD";
      return;
    }

    if(this.inserirRelacaoVisitante.ca && !this.inserirRelacaoVisitante.doisCa && !this.inserirRelacaoVisitante.cvd)
    {
      this.inserirRelacaoVisitante.cartoes = "CA";
      return;
    }

    if((this.inserirRelacaoVisitante.ca && this.inserirRelacaoVisitante.doisCa && !this.inserirRelacaoVisitante.cvd)
    ||
    (!this.inserirRelacaoVisitante.ca && this.inserirRelacaoVisitante.doisCa && !this.inserirRelacaoVisitante.cvd))
    {
      this.inserirRelacaoVisitante.cartoes = "2CA";
      return;
    }

    if(!this.inserirRelacaoVisitante.ca && !this.inserirRelacaoVisitante.doisCa && this.inserirRelacaoVisitante.cvd)
    {  
      this.inserirRelacaoVisitante.cartoes = "CVD";
      return;
    }

  }
/*==============================================================================
VALIDA SÚMULA
==============================================================================*/
  validaSumula()
  {
    
    if(this.sumula.clubeMandante == "null") return false;
    if(this.sumula.clubeVisitante == "null") return false;
    if(this.sumula.assistente1 == "null") return false;
    if(this.sumula.assistente2 == "null") return false;
    
    return true;
  }
/*==============================================================================
CADASTRA SUMULA
==============================================================================*/
  cadastraSumula()
  {  
    //Ponto de inicio para salvar todos os itens na ordem correta
    this.salvaComissaoMandante();
  }
/*==============================================================================
SALVA COMISSAO MANDANTE
==============================================================================*/
  salvaComissaoMandante()
  {
    this.sumulaService.cadastraComissao(this.comissaoMandante).subscribe(dados =>
      {
        this.sumula.comissaoMandante = "" + dados;    
        this.salvaComissaoVisitante();
      }, 
      dados => this.informaErroCadastro(dados)) 
  }
/*==============================================================================
SALVA COMISSÃO VISITNATE
==============================================================================*/
  salvaComissaoVisitante()
  {
    this.sumulaService.cadastraComissao(this.comissaoVisitante).subscribe(dados =>
      {
        this.sumula.comissaoVisitante = "" + dados;
        this.salvaCronologia();             
      }, 
      dados => this.informaErroCadastro(dados)) 
  }
/*==============================================================================
SALVA CRONOLOIGA
==============================================================================*/
  salvaCronologia()
  { 
    this.sumulaService.cadastraCronologia(this.cronologia).subscribe(dados =>
      {
        this.sumula.cronologia = "" + dados;
        this.salvaEscalcaoMandante();        
      },
      dados => this.informaErroCadastro(dados))     
  }
/*==============================================================================
SALVA ESCALAÇÃO MANDANTE
==============================================================================*/
  salvaEscalcaoMandante()
  { 
    this.sumulaService.cadastraEscalacao(this.createEscalacao).subscribe(dados =>
      {
        this.sumula.escalacaoMandante = "" + dados;
        this.salvaEscalcaoVisitante();
      },
      dados => this.informaErroCadastro(dados))
  }
/*==============================================================================
SALVA ESCALAÇÃO VISITANTE
==============================================================================*/
  salvaEscalcaoVisitante()
  {    
    this.sumulaService.cadastraEscalacao(this.createEscalacao).subscribe(dados =>
      {
        this.sumula.escalacaoVisitante = "" + dados; 
        this.salvaRelacaoMandante();                                                  
        },
      dados => this.informaErroCadastro(dados))
  }
/*==============================================================================
SALVA RELAÇÃO MANDANTE
==============================================================================*/
  salvaRelacaoMandante()
  {
    this.sumulaService.cadastraRelacao(this.toCreateRelacao(this.relacoesMandante, this.sumula.escalacaoMandante)).subscribe(dados =>
      {
        this.salvaRelacaoVisitante();
      },
      dados => this.informaErroCadastro(dados)) 
  }
/*==============================================================================
SALVA RELAÇÃO VISITANTE
==============================================================================*/
  salvaRelacaoVisitante()
  {
    this.sumulaService.cadastraRelacao(this.toCreateRelacao(this.relacoesVisitante, this.sumula.escalacaoVisitante)).subscribe(dados =>
      {
        this.salvaSumula();
      },
      dados => this.informaErroCadastro(dados)) 
  }
  /*==============================================================================
SALVA SUMULA
==============================================================================*/
  salvaSumula()
  {
    this.sumulaService.cadastraSumula(this.sumula).subscribe(dados =>
      {
        this.sumula.id = "" + dados;
        this.salvaSubstituicaoMandante();
      },
      dados => this.informaErroCadastro(dados)) 
  }
/*==============================================================================
SALVA SUBSTITUIÇÃO MANDANTE
==============================================================================*/
  salvaSubstituicaoMandante()
  {
    this.substituicoesMandante.forEach(sub =>{
      sub.equipeMandante = true;
      sub.sumula = this.sumula.id;
    });

    this.sumulaService.cadastraSubstituicao(this.substituicoesMandante).subscribe(dados => {
      this.salvaSubstituicaoVisitante();                                   
    },
    dados => this.informaErroCadastro(dados));
  }
/*==============================================================================
SALVA SUBSTITUIÇÃO VISITANTE
==============================================================================*/
  salvaSubstituicaoVisitante()
  {
    this.substituicoesVisitante.forEach(sub =>{
      sub.equipeMandante = false;
      sub.sumula = this.sumula.id;
    });

    this.sumulaService.cadastraSubstituicao(this.substituicoesVisitante).subscribe(dados => {
      alert("Súmula Cadastrada com Sucesso")
      this.router.navigate(['/sumula']); //Volta para a página inicial de súmulas
    },
    dados => this.informaErroCadastro(dados));
  }
}



