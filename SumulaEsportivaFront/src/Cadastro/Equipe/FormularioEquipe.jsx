import React, { Component } from 'react';
import ModalidadeService from './../Modalidade/ModalidadeService';
import Service from './EquipeService';
import {
	FormGroup,
	ControlLabel,
	FormControl,
	Button
} from 'react-bootstrap';
import './Equipe.css';

class FormularioModalidade extends Component {
	constructor(props) {
		super(props);
		this.state = {
			nome: '',
			modalidade: '',
			modalidades: [],
			integrantes: []
		};
		this.buscaModalidades();
		if (props.id) {
			let service = new Service();
			service.getById(props.id).then(this.setEntity);
		}
	}

	setEntity = (entity) => {
		this.setState({
			id: entity.id,
			nome: entity.nome
		});
		let service = new Service();
		service.getFromUrl(entity._links.modalidade.href).then((modalidade) => {
			this.setState({ modalidade: modalidade._links.self.href });
		});
		service.getFromUrl(entity._links.integrantes.href).then((integrantes) => {
			this.setState({
				integrantes: integrantes._embedded.pessoa,
				buscaIntegrantesConcluida: true
			});
		});
	};

	buscaModalidades() {
		new ModalidadeService().findAll().then((modalidades) => {
			this.setState({ modalidades: modalidades._embedded.modalidade });
		});
	}

	save = () => {
		let service = new Service();
		service.post(this.state).then(() => {
			this.props.onTelaChange('listagem');
		});
	};

	calculaIdade = (dataNascimento) => {
		let now = new Date();
		let ano_atual = now.getFullYear();
		let mes_atual = now.getMonth();
		let dia_atual = now.getDate();

		let ano_aniversario = dataNascimento.getFullYear();
		let mes_aniversario = dataNascimento.getMonth();
		let dia_aniversario = dataNascimento.getDate() + 1;

		let idade = ano_atual - ano_aniversario;

		if (mes_atual < mes_aniversario) {
			return --idade;
		}

		if (dia_atual < dia_aniversario) {
			return --idade;
		}

		return idade;
	}

	render() {
		let modalidades = this.state.modalidades.map((modalidade) => {
			return <option key={modalidade._links.self.href} value={modalidade._links.self.href}>{modalidade.descricao}</option>;
		});
		modalidades.unshift(<option key="" value=""></option>);
		let tabelaIntegrantes;
		if (this.state.id) {
			let integrantes = this.state.integrantes.map((integrante) => {
				let idade = this.calculaIdade(new Date(integrante.dataNascimento));
				return (
					<tr key={integrante.id} className="background-white">
						<td>{integrante.nome}</td>
						<td>{idade}</td>
						<td>{integrante.telefone}</td>
					</tr>
				);
			});
			if (integrantes.length) {
				tabelaIntegrantes = (
					<div className="row">
						<div className="col-xs-12">
							<ControlLabel>Integrantes</ControlLabel>
							<table className="table table-striped table-bordered">
								<thead>
									<tr>
										<th>Nome</th>
										<th>Idade</th>
										<th>Telefone</th>
									</tr>
								</thead>
								<tbody>
									{integrantes}
								</tbody>
							</table>
						</div>
					</div>
				);
			} else {
				if (this.state.buscaIntegrantesConcluida) {
					tabelaIntegrantes = (
						<h3 className="center">Essa equipe ainda não possui integrantes.</h3>
					);
				}
			}
		}


		return (
			<form>
				<div className="row">
					<div className="col-xs-12">
						<FormGroup>
							<ControlLabel>Nome da equipe</ControlLabel>
							<FormControl
								type="text"
								value={this.state.nome}
								onChange={(nome) => {this.setState({ nome: nome.target.value });}}
							/>
						</FormGroup>
					</div>
				</div>
				<div className="row">
					<div className="col-xs-12">
						<FormGroup>
							<ControlLabel>Modalidade</ControlLabel>
							<FormControl
								componentClass="select"
								value={this.state.modalidade}
								onChange={(modalidade) => {this.setState({ modalidade: modalidade.target.value });}}
							>
								{modalidades}
							</FormControl>
						</FormGroup>
					</div>
				</div>
				{tabelaIntegrantes}
				<div className="row">
					<div className="col-xs-12">
						<div className="pull-right">
							<Button bsStyle="default" className="btn-margin-right" onClick={() => {this.props.onTelaChange('listagem');}}>Cancelar</Button>
							<Button bsStyle="primary" onClick={this.save}>Salvar</Button>
						</div>
					</div>
				</div>
			</form>
		);
	}
}

export default FormularioModalidade;