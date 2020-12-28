import React, { Component } from 'react';
import Toggle from 'react-toggle';
import './css/SexoToggle.css'

class SexoToggle extends Component {
	render() {
		return (
			<Toggle 
				className="switch-sexo" 
				icons={{
					checked: <span className="switch-sexo-item">Masculino</span>,
					unchecked: <span className="switch-sexo-item">Feminino</span>
				}}
				checked={this.props.value === 'M'}
				onChange={(sexo) => {this.props.onChange(sexo.target.checked);}}
			/>
		);
	}
}

export default SexoToggle;