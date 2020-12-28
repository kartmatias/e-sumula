import React, { Component } from 'react';
import HeaderListagem from './../../Components/HeaderListagem.jsx';

class HeaderEquipe extends Component {
	render() {
		return (
			<HeaderListagem>
				<th>Nome da equipe</th>
			</HeaderListagem>
		);
	}
}

export default HeaderEquipe;