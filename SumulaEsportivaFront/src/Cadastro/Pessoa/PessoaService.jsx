import RestService from './../../Components/RestService';

class PessoaService extends RestService {

	constructor() {
		super('/pessoa');
	}

}

export default PessoaService;