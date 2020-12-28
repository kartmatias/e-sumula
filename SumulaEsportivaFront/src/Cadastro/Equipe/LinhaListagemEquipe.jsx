import React, { Component } from 'react';
import LinhaListagem from './../../Components/LinhaListagem.jsx';

class LinhaListagemEquipe extends Component {
	onRegistroSelect = () => {
		this.props.onRegistroSelect(this.props.equipe.id);
	};

	onRegistroDeselect = () => {
		this.props.onRegistroDeselect(this.props.equipe.id);
	};

	render() {
		return (
			<LinhaListagem onRegistroSelect={this.onRegistroSelect} onRegistroDeselect={this.onRegistroDeselect}>
				<td>{this.props.equipe.nome}</td>
			</LinhaListagem>
		);
	}
}

export default LinhaListagemEquipe;