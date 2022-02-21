#include "WorkflowExecutor.h"

namespace Workflow {

	WorkflowExecutor::WorkflowExecutor(Interfaces::WorkflowParser& parser) : _parser(parser) {}

	void WorkflowExecutor::_clear_map(std::map<int, Interfaces::Worker*>& m) {
		for (auto x : m) {
			delete x.second;
		}
	}

	void WorkflowExecutor::run_workflow() {

		std::map<int, Interfaces::Worker*> workers;
		std::vector<int> ids_queue;
		try {
			workers = _parser.parse_file();
			ids_queue = _parser.get_ids_queue();
		}
		catch (std::exception& ex) {
			throw ex;
		}

		try {
			for (auto id : ids_queue) {
				workers[id]->work();
			}
		}
		catch (std::exception& ex) {
			_clear_map(workers);
			throw ex;
		}
		_clear_map(workers);
	}
}