import React, { Component } from 'react';
import { 
	Navbar,
	Nav,
	NavDropdown,
	MenuItem,
	NavItem
} from 'react-bootstrap';
import './Menu.css'

class Menu extends Component {

	onMenuSelect = (event) => {
		this.props.onSelectMenu(event.target.id);
	};

	render() {
		return (
			<Navbar inverse collapseOnSelect>
				<Navbar.Header>
					<Navbar.Brand>
						<a onClick={this.onMenuSelect} id="home">Súmula App</a>
					</Navbar.Brand>
					<Navbar.Toggle />
				</Navbar.Header>
				<Navbar.Collapse>
					<Nav>
						<NavDropdown title="Cadastros" id="menu-cadastros">
							<MenuItem onClick={this.onMenuSelect} id="cadastro-pessoa">Pessoa</MenuItem>
							<MenuItem onClick={this.onMenuSelect} id="cadastro-equipe">Equipe</MenuItem>
							<MenuItem onClick={this.onMenuSelect} id="cadastro-modalidade">Modalidade</MenuItem>
							<MenuItem onClick={this.onMenuSelect} id="cadastro-liga">Liga</MenuItem>
							<MenuItem onClick={this.onMenuSelect} id="cadastro-calendario">Calendário</MenuItem>
							<MenuItem onClick={this.onMenuSelect} id="cadastro-partida">Partida</MenuItem>
							<MenuItem divider />
							<MenuItem onClick={this.onMenuSelect} id="cadastro-sumula">Súmula</MenuItem>
						</NavDropdown>
						<NavDropdown title="Operações" id="menu-operacoes">
							<MenuItem onClick={this.onMenuSelect} id="operacao-sorteio">Sorteio Liga</MenuItem>
							<MenuItem onClick={this.onMenuSelect} id="operacao-sumula">Preencher Súmula</MenuItem>
						</NavDropdown>
						<NavDropdown title="Relatórios" id="menu-relatorios">
							<MenuItem onClick={this.onMenuSelect} id="relatorio-sumula">Súmula</MenuItem>
							<MenuItem onClick={this.onMenuSelect} id="relatorio-artilheiro">Artilheiro</MenuItem>
							<MenuItem onClick={this.onMenuSelect} id="relatorio-tabela">Tabela de Classificação</MenuItem>
						</NavDropdown>
					</Nav>
					<Nav pullRight>
						<NavItem>Usuário</NavItem>
					</Nav>
				</Navbar.Collapse>
			</Navbar>
		);
	}
}

export default Menu;