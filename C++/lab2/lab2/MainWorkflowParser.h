#pragma once

#include <list>
#include <vector>
#include <codecvt>
#include <fstream> 
#include <string>
#include <map>

#include "Worker.h"
#include "WorkflowParser.h"
#include "CONSTSPACE.h"
#include "BadInputFile.h"
#include "BadSyntax.h"
#include "Helpers.h"
#include "Factory.h"
#include "Creator.h"

namespace Workflow {

	class MainWorkflowParser : public Interfaces::WorkflowParser {
	private:
		std::string _file_name;
		std::wifstream _file;
		std::vector<int> _ids_queue;

		Interfaces::Factory& _factory;


		std::vector<std::wstring> _parse_command(const std::wstring&);

		void _clear_map(std::map<int, Interfaces::Worker*>&);

		//parse last string
		void _parse_workflow(std::map<int, Interfaces::Worker*>&);
	public:
		MainWorkflowParser(const std::string&, Interfaces::Factory&);

		void open_file();

		~MainWorkflowParser();

		std::vector<int> get_ids_queue() const override;

		// File parser, reterns workers in the right order or throw exeptions if something goes wrong
		std::map<int, Interfaces::Worker*> parse_file() override;
	};
}
