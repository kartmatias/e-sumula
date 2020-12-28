import React, { Component } from 'react';
import FontAwesome from 'react-fontawesome';
import { 
	Button
} from 'react-bootstrap';

class PaginationControl extends Component {

	firstPage = () => {
		this.loadPage(this.props.links.first.href);
	};

	previousPage = () => {
		this.loadPage(this.props.links.prev.href);
	};

	nextPage = () => {
		this.loadPage(this.props.links.next.href);
	};

	lastPage = () => {
		this.loadPage(this.props.links.last.href);
	};

	loadPage = (link) => {
		fetch(link).then(
			(response) => {
				return response.json();
			}
		).then((response) => {
			this.props.onPageChange(response);
		});
	};

	render() {
		if (!this.props.page || !this.props.links) {
			return <div/>;
		}
		let thisPage = this.props.page.number + 1;
		return (
			<div className="row">
				<div className="col-xs-12 pagination-control">

					<Button onClick={this.firstPage} disabled={!this.props.links.prev}>
						<FontAwesome name="angle-double-left"/>
					</Button>

					<Button onClick={this.previousPage} disabled={!this.props.links.prev}>
						<FontAwesome name="angle-left"/>
					</Button>

					<Button onClick={this.nextPage} disabled={!this.props.links.next}>
						<FontAwesome name="angle-right"/>
					</Button>

					<Button onClick={this.lastPage} disabled={!this.props.links.next}>
						<FontAwesome name="angle-double-right"/>
					</Button>

					<span>Página {thisPage} de {this.props.page.totalPages}. Total de registros: {this.props.page.totalElements}.</span>

				</div>
			</div>
		);
	}
}

export default PaginationControl;