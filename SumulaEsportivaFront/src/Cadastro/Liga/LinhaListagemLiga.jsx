import React, { Component } from 'react';
import LinhaListagem from './../../Components/LinhaListagem.jsx';
import './Liga.css';

class LinhaListagemLiga extends Component {

	onRegistroSelect = () => {
		this.props.onRegistroSelect(this.props.liga.id);
	};

	onRegistroDeselect = () => {
		this.props.onRegistroDeselect(this.props.liga.id);
	};

	render() {
		return (
			<LinhaListagem onRegistroSelect={this.onRegistroSelect} onRegistroDeselect={this.onRegistroDeselect}>
				<td>{this.props.liga.nome}</td>
				<td>{this.props.liga.cabecaDeChave.nome}</td>
			</LinhaListagem>
		);
	}
}

export default LinhaListagemLiga;