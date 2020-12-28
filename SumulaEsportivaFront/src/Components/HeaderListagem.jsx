import React, { Component } from 'react';
import './css/HeaderListagem.css';

class HeaderListagem extends Component {
	render() {
		return (
			<tr>
				<th className="checkbox-column"></th>
				{this.props.children}
			</tr>
		);
	}
}

export default HeaderListagem;