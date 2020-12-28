import React, { Component } from 'react';
import HeaderListagem from './../../Components/HeaderListagem.jsx';

class HeaderPessoa extends Component {
	render() {
		return (
			<HeaderListagem>
				<th>Nome</th>
				<th>RG</th>
				<th>E-mail</th>
			</HeaderListagem>
		);
	}
}

export default HeaderPessoa;