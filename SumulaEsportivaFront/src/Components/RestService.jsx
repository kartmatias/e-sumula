class RestService {

	constructor(url) {
		this.url = url;
	};

	getFirstPage() {
		return fetch(this.url).then(
			(response) => {
				return response.json();
			}
		);
	};

	post(data) {
		return fetch(this.url, {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(data)
		}).then((response) => {
			return response.json();
		});
	};

	getById(id) {
		return fetch(this.url + '/' + id).then(
			(response) => {
				return response.json();
			}
		);
	}

	delete(id) {
		return fetch(this.url + '/' + id, {
			method: 'DELETE'
		}).then(
			(response) => {
				return new Promise((resolve, reject) => {
					if (!response.ok || response.status !== 204) {
						reject();
						return;
					}
					resolve();
				});
			}
		);
	}

	findAll() {
		return fetch(this.url + '?size=1000').then(
			(response) => {
				return response.json();
			}
		);
	}

	getFromUrl(url) {
		return fetch(url).then(
			(response) => {
				return response.json();
			}
		);
	}
}

export default RestService;