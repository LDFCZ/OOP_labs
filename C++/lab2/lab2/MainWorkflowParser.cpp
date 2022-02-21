#include "MainWorkflowParser.h"

namespace Workflow {

	MainWorkflowParser::MainWorkflowParser(const std::string& file_name, Interfaces::Factory& factory) : _factory(factory) {
		_file_name = file_name;
		_ids_queue = {};
	}

	void MainWorkflowParser::open_file() {
		const std::locale utf8_locale = std::locale(std::locale(), new std::codecvt_utf8<wchar_t>());
		_file.open(_file_name);
		_file.imbue(utf8_locale);

		if (!_file.is_open()) {
			std::string error_text = CONSTSPACE::INPUT_FILE_WORKFLOW_ERROR + _file_name;
			throw ErrorsSpace::BadInputFile(error_text.c_str());
		}
	}

	MainWorkflowParser::~MainWorkflowParser() {
		_file.close();
	}

	std::vector<std::wstring> MainWorkflowParser::_parse_command(const std::wstring& command) {
		std::wstring word = CONSTSPACE::EMPTY_STR;
		std::vector<std::wstring> out;
		for (auto x : command)
		{
			if (x == CONSTSPACE::WCHAR_SPACE)
			{
				out.push_back(word);
				word = CONSTSPACE::EMPTY_STR;
			}
			else {
				word = word + x;
			}
		}
		out.push_back(word);
		return out;
	}

	void MainWorkflowParser::_parse_workflow(std::map<int, Interfaces::Worker*>& workers) {
		
		std::wstring code_line;
		int id;
		
		getline(_file, code_line);
		std::vector<std::wstring> workflow = _parse_command(code_line);

		Interfaces::Types prev_worker_out_type = Interfaces::Types::EMPTY;

		for (size_t i = 0; i < workflow.size(); i++) {
			if (i % 2 == 0) {
				try {
					id = stoi(workflow[i]);
				}
				catch (...) {
					_clear_map(workers);
					std::string error_text = CONSTSPACE::BAD_SYNTAX_WORKFLOW_ERROR + Helpers::convert_to_string(code_line);
					throw ErrorsSpace::BadSyntax(error_text.c_str());
				}
				if (workers.find(id) == workers.end()) {
					_clear_map(workers);
					std::string error_text = CONSTSPACE::BAD_ID_WORKFLOW_ERROR + std::to_string(id);
					throw ErrorsSpace::BadSyntax(error_text.c_str());
				}
				if (workers[id]->get_input_type() != prev_worker_out_type) {
					_clear_map(workers);
					std::string error_text = CONSTSPACE::INCOMPSTIBLE_WORKERS_WORKFLOW_ERROR + Helpers::convert_to_string(code_line);
					throw ErrorsSpace::BadSyntax(error_text.c_str());
				}
				prev_worker_out_type = workers[id]->get_output_type();
				_ids_queue.push_back(id);
			}
			else {
				if (workflow[i] != CONSTSPACE::WARROW) {
					_clear_map(workers);
					std::string error_text = CONSTSPACE::BAD_SYNTAX_WORKFLOW_ERROR + Helpers::convert_to_string(code_line);
					throw ErrorsSpace::BadSyntax(error_text.c_str());
				}
			}
		}
	}

	void MainWorkflowParser::_clear_map(std::map<int, Interfaces::Worker*>& m) {
		for (auto x : m) {
			delete x.second;
		}
	}

	std::vector<int> MainWorkflowParser::get_ids_queue() const {
		return _ids_queue;
	}

	std::map<int, Interfaces::Worker*> MainWorkflowParser::parse_file() {

		int str_number = 0;
		std::wstring code_line;

		if (!_file.is_open()) {
			std::string error_text = CONSTSPACE::INPUT_FILE_WORKFLOW_ERROR + _file_name;
			throw ErrorsSpace::BadInputFile(error_text.c_str());
		}

		getline(_file, code_line);
		str_number++;
		
		// Start reading workflow file
		if (code_line != CONSTSPACE::CODE_START) {
			std::string error_text = CONSTSPACE::BAD_SYNTAX_WORKFLOW_ERROR + std::to_string(str_number) + CONSTSPACE::SPACE + Helpers::convert_to_string(code_line);
			throw ErrorsSpace::BadSyntax(error_text.c_str());
		}

		std::vector<std::vector<std::wstring>> parsed_commands;
		int id;

		getline(_file, code_line);
		str_number++;
		// Reading blocks 
		while (code_line != CONSTSPACE::CODE_END || _file.eof())
		{
			if (_file.eof()) {
				std::string error_text = CONSTSPACE::NO_END_WORKFLOW_ERROR + std::to_string(str_number) + CONSTSPACE::SPACE + Helpers::convert_to_string(code_line);
				throw ErrorsSpace::BadSyntax(error_text.c_str());
			}
			std::vector<std::wstring> tmp = _parse_command(code_line);
			
			if (tmp[CONSTSPACE::EQUAL_ARG] != CONSTSPACE::WEQUAL) {
				std::string error_text = CONSTSPACE::BAD_SYNTAX_WORKFLOW_ERROR + std::to_string(str_number) + CONSTSPACE::SPACE + Helpers::convert_to_string(code_line);
				throw ErrorsSpace::BadSyntax(error_text.c_str());
			}
			
			try {
				id = stoi(Helpers::convert_to_string(tmp[CONSTSPACE::NUMBER_ARG])); // :_)
			}
			catch (...) {
				std::string error_text = CONSTSPACE::BAD_SYNTAX_WORKFLOW_ERROR + std::to_string(str_number) + CONSTSPACE::SPACE + Helpers::convert_to_string(code_line);
				throw ErrorsSpace::BadSyntax(error_text.c_str());
			}
			
			if (id < 0) {
				std::string error_text = CONSTSPACE::BAD_ID_WORKFLOW_ERROR + std::to_string(str_number) + CONSTSPACE::SPACE + Helpers::convert_to_string(code_line);
				throw ErrorsSpace::BadSyntax(error_text.c_str());
			}

			parsed_commands.push_back(tmp);
			getline(_file, code_line);
			str_number++;
		}

		std::map<int, Interfaces::Worker*> workers;
		try {
			workers = _factory.produce_workres(parsed_commands);
		}
		catch (std::exception& ex) {
			throw ErrorsSpace::BadSyntax(ex.what());
		}
		
		// Read last string anp parse it. Create ids list
		try {
			_parse_workflow(workers);
		}
		catch (const std::exception& ex) {
			throw ex;
		}
		
		return workers;
	}
}