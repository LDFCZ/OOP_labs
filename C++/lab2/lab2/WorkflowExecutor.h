#pragma once

#include <list> 
#include <iostream>

#include "Worker.h"
#include "MainWorkflowParser.h"

namespace Workflow {
	class WorkflowExecutor {
	private:
		Interfaces::WorkflowParser& _parser;

		void _clear_map(std::map<int, Interfaces::Worker*>&);
	public:
		WorkflowExecutor(Interfaces::WorkflowParser&);

		// Runs all workers in list of workers in list order
		// Can safely exit the program if smth goes wrong. 
		void run_workflow();
	};
}

