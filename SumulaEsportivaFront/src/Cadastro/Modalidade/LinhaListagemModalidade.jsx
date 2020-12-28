import React, { Component } from 'react';
import LinhaListagem from './../../Components/LinhaListagem.jsx';

class LinhaListagemModalidade extends Component {

	onRegistroSelect = () => {
		this.props.onRegistroSelect(this.props.modalidade.id);
	};

	onRegistroDeselect = () => {
		this.props.onRegistroDeselect(this.props.modalidade.id);
	};

	render() {
		return (
			<LinhaListagem onRegistroSelect={this.onRegistroSelect} onRegistroDeselect={this.onRegistroDeselect}>
				<td>{this.props.modalidade.descricao}</td>
			</LinhaListagem>
		);
	}
}

export default LinhaListagemModalidade;