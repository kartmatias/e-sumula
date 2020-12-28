import React, { Component } from 'react';
import ListagemModalidade from './ListagemModalidade'
import FormularioModalidade from './FormularioModalidade'

class MainModalidade extends Component {
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
		let tela = <ListagemModalidade onTelaChange={this.onTelaChange}/>;
		if (this.state.tela === 'formulario') {
			tela = <FormularioModalidade onTelaChange={this.onTelaChange} id={this.state.id}/>;
		}
		return (
			<div>
				<h3>Cadastro de modalidade</h3>
				<hr/>
				{tela}
			</div>
		);
	}
}

export default MainModalidade;