#include "Context.h"
#include "MainFactory.h"
#include "MainWorkflowParser.h"
#include "WorkflowExecutor.h"
#include "CONSTSPACE.h"

#include "ReaderCreator.h"
#include "WriterCreator.h"
#include "DumpCreator.h"
#include "GrepCreator.h"
#include "SortCreator.h"
#include "ReplaceCreator.h"

int main(int argc, char** argv) {

	std::map<std::wstring, Interfaces::Creator*> command_creator;
	command_creator[CONSTSPACE::READ_COMMAND] = new MainWorkerCreators::ReaderCreator;
	command_creator[CONSTSPACE::WRITE_COMMAND] = new MainWorkerCreators::WriterCreator;
	command_creator[CONSTSPACE::GREP_COMMAND] = new MainWorkerCreators::GrepCreator;
	command_creator[CONSTSPACE::SORT_COMMAND] = new MainWorkerCreators::SortCreator;
	command_creator[CONSTSPACE::REPLACE_COMMAND] = new MainWorkerCreators::ReplaceCreator;
	command_creator[CONSTSPACE::DUMP_COMMAND] = new MainWorkerCreators::DumpCreator;

	MainContext::Context context;

	Factory::MainFactory factory(command_creator, context);

	Workflow::MainWorkflowParser parser(argv[CONSTSPACE::MAIN_FILE_NAME_ARG], factory);
	
	try {
		parser.open_file();
	}
	catch (std::exception& ex) {
		std::cout << ex.what() << std::endl;

		for (auto x : command_creator) {
			delete x.second;
		}

		return EXIT_FAILURE;
	}
	Workflow::WorkflowExecutor executor(parser);
	try
	{
		executor.run_workflow();
	}
	catch (std::exception& ex)
	{
		std::cout << ex.what() << std::endl;
		for (auto x : command_creator) {
			delete x.second;
		}
		return EXIT_FAILURE;
	}
	
	
	for (auto x : command_creator) {
		delete x.second;
	}

	return EXIT_SUCCESS;
}