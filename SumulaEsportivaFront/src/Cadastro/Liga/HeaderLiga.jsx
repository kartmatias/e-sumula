import React, { Component } from 'react';
import HeaderListagem from './../../Components/HeaderListagem.jsx';

class HeaderLiga extends Component {
	render() {
		return (
			<HeaderListagem>
				<th>Nome</th>
				<th>Cabeça de Chave</th>
			</HeaderListagem>
		);
	}
}

export default HeaderLiga;