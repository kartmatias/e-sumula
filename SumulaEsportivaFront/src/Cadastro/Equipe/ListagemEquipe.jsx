import React, { Component } from 'react';
import Service from './EquipeService';
import LinhaListagemEquipe from './LinhaListagemEquipe.jsx';
import HeaderEquipe from './HeaderEquipe.jsx';
import {
	Button,
	Glyphicon
} from 'react-bootstrap';
import PaginationControl from './../../Components/PaginationControl.jsx';
import GenericList from './../../Components/GenericList.jsx';

class ListagemEquipe extends Component {
	constructor(props) {
		super(props);
		this.state = {
			registros: {},
			registrosSelecionados: []
		};
		let service = new Service();
		service.getFirstPage().then(this.setRegistros);
	}

	setRegistros = (registros) => {
		this.setState({
			registros: registros,
			registrosSelecionados: []
		});
	};

	goToForm = () => {
		this.props.onTelaChange('formulario');
	};

	goToEditForm = () => {
		this.props.onTelaChange('formulario', this.state.registrosSelecionados[0]);
	};

	onRegistroSelect = (id) => {
		const equalsId = (selecionado) => {
			return selecionado === id;
		}

		let selecionados = this.state.registrosSelecionados;
		if (selecionados.some(equalsId)) {
			return;
		}
		selecionados.push(id);
		this.setState({ registrosSelecionados: selecionados });
	};

	onRegistroDeselect = (id) => {
		let selecionados = this.state.registrosSelecionados.filter((selecionado) => {
			return selecionado !== id;
		});
		this.setState({ registrosSelecionados: selecionados });
	};

	removerRegistros = () => {
		let service = new Service();
		service.delete(this.state.registrosSelecionados[0]).then(() => {
			service.getFirstPage().then(this.setRegistros);
		});
	};

	render() {
		let listagemRegistros = [];
		if (this.state.registros._embedded){
			listagemRegistros = this.state.registros._embedded.equipe.map((equipe) => {
				return <LinhaListagemEquipe key={equipe.id} equipe={equipe} onRegistroSelect={this.onRegistroSelect} onRegistroDeselect={this.onRegistroDeselect}/>;
			});
		}
		let header = <HeaderEquipe/>;
		return (
			<div>
				<div className="row control-bar">
					<div className="col-xs-12">
						<div className="pull-right">
							<Button bsStyle="danger" className="btn-alterar" onClick={this.removerRegistros} disabled={this.state.registrosSelecionados.length !== 1}><Glyphicon glyph="trash" className="texted-right-icon"/>Remover</Button>
							<Button bsStyle="info" className="btn-alterar" onClick={this.goToEditForm} disabled={this.state.registrosSelecionados.length !== 1}><Glyphicon glyph="pencil" className="texted-right-icon"/>Alterar</Button>
							<Button bsStyle="primary" onClick={this.goToForm}><Glyphicon glyph="plus" className="texted-right-icon"/>Inserir</Button>
						</div>
					</div>
				</div>
				<GenericList header={header} listagemRegistros={listagemRegistros}/>
				<PaginationControl page={this.state.registros.page} links={this.state.registros._links} onPageChange={this.setRegistros}/>
			</div>
		);
	}
}

export default ListagemEquipe;