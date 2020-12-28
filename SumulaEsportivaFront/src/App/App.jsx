import React, { Component } from 'react';
import './App.css'
import Menu from './../Menu/Menu.jsx'
import Home from './../Home/Home'
import PessoaMain from './../Cadastro/Pessoa/PessoaMain.jsx';
import LigaMain from './../Cadastro/Liga/LigaMain.jsx';
import MainEquipe from './../Cadastro/Equipe/MainEquipe.jsx';
import MainModalidade from './../Cadastro/Modalidade/MainModalidade.jsx';
import ListagemCalendario from './../Cadastro/Calendario/ListagemCalendario.jsx';
import ListagemPartida from './../Cadastro/Partida/ListagemPartida.jsx';
import ListagemSumula from './../Cadastro/Sumula/ListagemSumula.jsx';
import SorteioLiga from './../Operacao/Sorteio/SorteioLiga.jsx';
import PreenchimentoSumula from './../Operacao/Sumula/PreenchimentoSumula.jsx';
import RelatorioSumula from './../Relatorio/Sumula/RelatorioSumula.jsx';
import RelatorioArtilheiro from './../Relatorio/Artilheiro/RelatorioArtilheiro.jsx';
import RelatorioClassificacao from './../Relatorio/Classificacao/RelatorioClassificacao.jsx';

class App extends Component {

	constructor(props) {
		super(props);
		this.state = {
			selectedMenu: 'home'
		};
	}

	onSelectMenu = (menu) => {
		this.setState({ selectedMenu: menu });
	};

	getTelaByMenuSelecionado = () => {
		const selectedMenu = this.state.selectedMenu;
		switch (selectedMenu) {
			case 'cadastro-pessoa':
				return <PessoaMain/>;
			case 'cadastro-equipe':
				return <MainEquipe/>
			case 'cadastro-modalidade':
				return <MainModalidade/>
			case 'cadastro-liga':
				return <LigaMain/>
			case 'cadastro-calendario':
				return <ListagemCalendario/>
			case 'cadastro-partida':
				return <ListagemPartida/>
			case 'cadastro-sumula':
				return <ListagemSumula/>
			case 'operacao-sorteio':
				return <SorteioLiga/>
			case 'operacao-sumula':
				return <PreenchimentoSumula/>
			case 'relatorio-sumula':
				return <RelatorioSumula/>;
			case 'relatorio-artilheiro':
				return <RelatorioArtilheiro/>;
			case 'relatorio-tabela':
				return <RelatorioClassificacao/>;
			default:
				return <Home/>
		}
	};

	render() {
		let currentMenu = this.getTelaByMenuSelecionado();
		return (
			<div>
				<Menu onSelectMenu={this.onSelectMenu}/>
				<div className="container main-container">
					{currentMenu}
				</div>
			</div>
		);
	}
}

export default App;