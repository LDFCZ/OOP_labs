#include "MainFactory.h"

namespace Factory {

	MainFactory::MainFactory(std::map<std::wstring, Interfaces::Creator*>& creator_container, 
		                     MainContext::Context& conext) :
		                     _creator_container(creator_container), 
	                         _context(conext) {}

	void MainFactory::_clear_map(std::map<int, Interfaces::Worker*>& m) {
		for (auto x : m) {
			delete x.second;
		}
	}

	std::map<int, Interfaces::Worker*> MainFactory::produce_workres(std::vector<std::vector<std::wstring>>& args_lines) {
		
		std::map<int, Interfaces::Worker*> workers;


		for (size_t i = 0; i < args_lines.size(); i++) {
			// :_)

			int id = stoi(Helpers::convert_to_string(args_lines[i][CONSTSPACE::NUMBER_ARG]));
			if (workers.find(id) != workers.end()) {
				_clear_map(workers);
				std::string error_text = CONSTSPACE::BAD_ID_WORKFLOW_ERROR + std::to_string(id); 
				throw ErrorsSpace::BadSyntax(error_text.c_str());
			}
			if (_creator_container.find(args_lines[i][CONSTSPACE::COMMAND_ARG]) == _creator_container.end()) {
				_clear_map(workers);
				std::string error_text = CONSTSPACE::BAD_SYNTAX_WORKFLOW_ERROR  + Helpers::convert_to_string(args_lines[i][CONSTSPACE::COMMAND_ARG]);
				throw ErrorsSpace::BadSyntax(error_text.c_str());
			}
			workers[id] = _creator_container[args_lines[i][CONSTSPACE::COMMAND_ARG]]->create_worker(args_lines[i], _context);
		}

		return workers;
	}
}