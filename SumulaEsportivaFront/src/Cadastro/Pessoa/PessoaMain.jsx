import React, { Component } from 'react';
import ListagemPessoa from './ListagemPessoa'
import FormularioPessoa from './FormularioPessoa';

class PessoaMain extends Component {
	constructor(props) {
		super(props);
		this.state = {
			tela: 'listagem'
		}
	}

	onTelaChange = (tela, id) => {
		this.setState({
			tela: tela,
			id: id
		});
	}

	render() {
		let tela = <ListagemPessoa onTelaChange={this.onTelaChange}/>;
		if (this.state.tela === 'formulario') {
			tela = <FormularioPessoa onTelaChange={this.onTelaChange} id={this.state.id}/>;
		}
		return (
			<div>
				<h3>Cadastro de pessoas</h3>
				<hr/>
				{tela}
			</div>
		);
	}
}

export default PessoaMain;